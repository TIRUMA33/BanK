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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/chat")
    public String doChat(@RequestParam("id")Integer id, Model model){

        UsuarioEntity user = this.usuarioRepository.findById(id).orElse(null);

        List<MensajeEntity> mensajescliente = this.mensajeRepository.findClienteMensajes();
        List<MensajeEntity> mensajesasistente = this.mensajeRepository.findAsistenteMensajes();

        model.addAttribute("mensajescliente", mensajescliente);
        model.addAttribute("mensajesasistente", mensajesasistente);
        return "chatcliente";
    }

    @PostMapping("/consultar")
    public String doConsultar(Model model){
        ConversacionEntity conver = new ConversacionEntity();
        model.addAttribute("conversacion", conver);
        return "redirect:/asistencia/chat";
    }


}
