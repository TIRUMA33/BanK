package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.ConversacionRepository;
import es.uma.taw.bank.dao.MensajeRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.entity.ConversacionEntity;
import es.uma.taw.bank.entity.MensajeEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
import es.uma.taw.bank.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/asistencia")
public class ConversacionController {

    @Autowired
    protected ConversacionRepository conversacionRepository;

    @Autowired
    protected MensajeRepository mensajeRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doAsistencia(@RequestParam("id")Integer id, Model model){

        ConversacionEntity conver = this.conversacionRepository.findConversacionAbiertaByUsuario(id);
        String urlTo = "inicioconsulta";
        if(conver!=null){
            urlTo = "redirect:/asistencia/chat?id="+conver.getId();
        }

        MensajeEntity msj = new MensajeEntity();
        msj.setUsuarioByEmisor(this.usuarioRepository.findById(id).orElse(null));
        msj.setConversacionByConversacion(conver);
        model.addAttribute("mensaje", msj);
        model.addAttribute("conversacion", conver);
        return urlTo;
    }

    @PostMapping("/consultar")
    public String doConsultar(@ModelAttribute("mensaje")MensajeEntity mensaje, Model model){
        mensaje.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        ConversacionEntity conver = new ConversacionEntity();
        conver.setUsuarioByEmisor(mensaje.getUsuarioByEmisor());
        conver.setUsuarioByReceptor(this.usuarioRepository.findAsistente());
        conver.setTerminada((byte) 0);
        conver.setFechaCreacion(new java.sql.Timestamp(System.currentTimeMillis()));
        this.conversacionRepository.save(conver);
        mensaje.setConversacionByConversacion(conver);
        this.mensajeRepository.save(mensaje);

        return "redirect:/asistencia/?id="+mensaje.getUsuarioByEmisor().getId();
    }

    @GetMapping("/chat")
    public String doChat(@RequestParam("id") Integer id, Model model){
        ConversacionEntity conver = this.conversacionRepository.findById(id).orElse(null);
        List<MensajeEntity> msjs = this.mensajeRepository.findMensajesByConversacion(conver.getId());
        model.addAttribute("mensajes", msjs);
        if (conver.getTerminada()==0){
            MensajeEntity msj = new MensajeEntity();
            msj.setUsuarioByEmisor(conver.getUsuarioByEmisor());
            msj.setConversacionByConversacion(conver);
            model.addAttribute("mensaje", msj);
            model.addAttribute("conversacion", conver);
        }
        return "chatcliente";
    }

    @GetMapping("/asistir")
    public String doAsistir(@RequestParam("id") Integer id, Model model){
        String urlTo = "chatasistente";
        ConversacionEntity conver = this.conversacionRepository.findById(id).orElse(null);
        List<MensajeEntity> msjs = this.mensajeRepository.findMensajesByConversacion(conver.getId());
        model.addAttribute("mensajes", msjs);
        if (conver.getTerminada()==0){
            MensajeEntity msj = new MensajeEntity();
            msj.setUsuarioByEmisor(conver.getUsuarioByReceptor());
            msj.setConversacionByConversacion(conver);
            model.addAttribute("mensaje", msj);
        }else{
            urlTo = "redirect:/asistencia/conversaciones";
        }
        return urlTo;
    }

    @PostMapping("/enviar")
    public String doEnviarMensaje(@ModelAttribute("mensaje") MensajeEntity mensaje, Model model){
        String urlTo = "redirect:/asistencia/chat?id="+mensaje.getConversacionByConversacion().getId();
        mensaje.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        this.mensajeRepository.save(mensaje);
        if(mensaje.getUsuarioByEmisor().getTipoUsuarioByTipoUsuario().getId()==3){
            urlTo = "redirect:/asistencia/asistir?id="+mensaje.getConversacionByConversacion().getId();
        }
        return urlTo;
    }

    @GetMapping("/conversaciones")
    public String doListarConversaciones(Model model){
        return doFiltrar(null, model);
    }

    @GetMapping("/cerrar")
    public String doCerrarConversacion(@RequestParam("id") Integer id){
        ConversacionEntity conver = this.conversacionRepository.findById(id).orElse(null);
        conver.setTerminada((byte) 1);
        this.conversacionRepository.save(conver);
        return "redirect:/persona/";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")FiltroAsistente filtro, Model model){
        List<ConversacionEntity> convers = this.conversacionRepository.findAll();
        if(filtro==null){
            filtro = new FiltroAsistente();
        }else if((filtro.getEstado()==null) && (!filtro.isPendientes()) && (!filtro.isFecha())){
            convers = this.conversacionRepository.findByNif(filtro.getDni());
        }else if((filtro.getDni().isBlank())&&(!filtro.isPendientes()) && (!filtro.isFecha())){
            convers = this.conversacionRepository.findByEstado(filtro.getEstado());
        }else if((filtro.getDni().isBlank())&&(!filtro.isPendientes()) && (filtro.getEstado()==null)) {
            convers = this.conversacionRepository.orderByFecha();
        }else if((filtro.getDni().isBlank()) && (filtro.getEstado()==null) && (!filtro.isFecha())) {
            convers = this.conversacionRepository.orderByEstado();
        }else if((filtro.getDni().isBlank()) && (filtro.getEstado()==null)){
            convers = this.conversacionRepository.orderByFechayEstado();
        }else if((filtro.getEstado()==null) && (((!filtro.isFecha()) || (!filtro.isPendientes())) || (filtro.isFecha() && filtro.isPendientes()))){
            convers = this.conversacionRepository.findByNifOrderByEstado(filtro.getDni());
        }else if((filtro.getDni().isBlank()) && (((!filtro.isPendientes() || !filtro.isFecha())) || (filtro.isPendientes() && filtro.isFecha()))){
            convers = this.conversacionRepository.findByEstadoOrderByFecha(filtro.getEstado());
        }else if((!filtro.isFecha() || !filtro.isPendientes())&& (!filtro.isPendientes() || filtro.isFecha() || filtro.isPendientes())){
            convers = this.conversacionRepository.findByNifAndEstado(filtro.getDni(), filtro.getEstado());
        }else{
            convers = this.conversacionRepository.findByNifAndEstadoOrderByFechayEstado(filtro.getDni(), filtro.getEstado());
        }
        model.addAttribute("conversaciones", convers);
        model.addAttribute("filtro", filtro);

        return "conversaciones";
    }

    /*@GetMapping("/mensajes")
    public String doListarMensajes(@RequestParam("id")Integer id, Model model){
        List<MensajeEntity> msjs = this.mensajeRepository.findByConversacionById(id);
        return "mensajes";
    }*/

}
