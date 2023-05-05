package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.ConversacionRepository;
import es.uma.taw.bank.dao.MensajeRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.entity.ConversacionEntity;
import es.uma.taw.bank.entity.MensajeEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
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

        String urlTo = "chat";
        ConversacionEntity conver = this.conversacionRepository.findConversacionAbiertaByUsuario(id);
        if(conver==null){
            conver = new ConversacionEntity();
            urlTo = "inicioconsulta";
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
        this.mensajeRepository.save(mensaje);

        return "redirect:/asistencia";
    }

    /*@GetMapping("/chat")
    public String doChat(@RequestParam("id")Integer id, Model model){
        return "chat";
    }*/

    @GetMapping("/conversaciones")
    public String doListarConversaciones(Model model){
        List<ConversacionEntity> lista = this.conversacionRepository.findAll();
        model.addAttribute("conversaciones", lista);
        return "conversaciones";
    }

    /*@GetMapping("/mensajes")
    public String doListarMensajes(@RequestParam("id")Integer id, Model model){
        List<MensajeEntity> msjs = this.mensajeRepository.findByConversacionById(id);
        return "mensajes";
    }*/

}
