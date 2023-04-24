package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.ConversacionRepository;
import es.uma.taw.bank.dao.MensajeRepository;
import es.uma.taw.bank.entity.MensajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/chat")
    public String doChat(Model model){
        List<MensajeEntity> mensajes = mensajeRepository.findAll();
        model.addAttribute("mensajes", mensajes);
        return "chatcliente";
    }


}
