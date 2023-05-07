package es.uma.taw.bank.controller;

import es.uma.taw.bank.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import es.uma.taw.bank.dto.*;
import es.uma.taw.bank.service.*;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/asistencia")
public class ConversacionController {

    @Autowired
    protected ConversacionService conversacionService;


    @Autowired
    protected MensajeService mensajeService;

    @Autowired
    protected UsuarioService usuarioService;

    @GetMapping("/")
    public String doAsistencia(@RequestParam("id")Integer id, Model model){

        ConversacionDTO conver = this.conversacionService.findConversacionAbiertaByUsuario(id);
        String urlTo = "inicioconsulta";
        if(conver!=null){
            urlTo="redirect:/asistencia/chat?id="+conver.getId();
        }

        MensajeDTO msj = this.mensajeService.setMensaje(id, conver);
        model.addAttribute("mensaje", msj);
        model.addAttribute("conversacion", conver);
        return urlTo;
    }

    @PostMapping("/consultar")
    public String doConsultar(@ModelAttribute("mensaje")MensajeDTO mensaje, Model model){
        mensaje.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        this.mensajeService.save(mensaje);

        return "redirect:/asistencia/?id="+mensaje.getEmisor();
    }

    @GetMapping("/chat")
    public String doChat(@RequestParam("id") Integer id, Model model){
        ConversacionDTO conver = this.conversacionService.buscarConversacion(id);
        List<MensajeDTO> msjs = this.mensajeService.findMensajesByConversacion(conver.getId());
        model.addAttribute("mensajes", msjs);
        if (conver.getTerminada()==0){
            MensajeDTO msj = this.mensajeService.setMensaje(conver.getEmisor(), conver);
            model.addAttribute("mensaje", msj);
            model.addAttribute("conversacion", conver);
        }
        return "chatcliente";
    }

    @GetMapping("/asistir")
    public String doAsistir(@RequestParam("id") Integer id, Model model){
        String urlTo = "chatasistente";
        ConversacionDTO conver = this.conversacionService.buscarConversacion(id);
        List<MensajeDTO> msjs = this.mensajeService.findMensajesByConversacion(conver.getId());
        model.addAttribute("mensajes", msjs);
        if (conver.getTerminada()==0){
            MensajeDTO msj = this.mensajeService.setMensaje(conver.getReceptor(), conver);
            model.addAttribute("mensaje", msj);
        }else{
            urlTo = "redirect:/asistencia/conversaciones";
        }
        return urlTo;
    }

    @PostMapping("/enviar")
    public String doEnviarMensaje(@ModelAttribute("mensaje") MensajeDTO mensaje, Model model){
        String urlTo = "redirect:/asistencia/chat?id="+mensaje.getConversacion();
        this.mensajeService.save(mensaje);
        if(this.usuarioService.buscarUsuario(mensaje.getEmisor()).getTipoUsuario()==3){
            urlTo = "redirect:/asistencia/asistir?id="+mensaje.getConversacion();
        }
        return urlTo;
    }

    @GetMapping("/conversaciones")
    public String doListarConversaciones(Model model){
        return doFiltrar(null, model);
    }

    @GetMapping("/cerrar")
    public String doCerrarConversacion(@RequestParam("id") Integer id){
        ConversacionDTO conver = this.conversacionService.buscarConversacion(id);
        this.conversacionService.guardar(conver);
        return "redirect:/persona/";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")FiltroAsistente filtro, Model model){
        List<ConversacionDTO> convers = this.conversacionService.findAll();
        if(filtro==null){
            filtro = new FiltroAsistente();
        }else{
            convers = this.conversacionService.filtrar(filtro);
        }
        model.addAttribute("conversaciones", convers);
        model.addAttribute("filtro", filtro);

        return "conversaciones";
    }

    @GetMapping("/mensajes")
    public String doListarMensajes(@RequestParam("id")Integer id, Model model){
        List<ConversacionDTO> convers = this.conversacionService.findAllByEmisor(id);
        List<MensajeDTO> allmsjs = new ArrayList<>();
        for (ConversacionDTO c: convers) {
            List<MensajeDTO> msjs = this.mensajeService.findMensajesByConversacion(c.getId());
            allmsjs.addAll(msjs);
        }
        model.addAttribute("todomensajes", allmsjs);
        return "mensajes";
    }

}
