package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.ClienteRepository;
import es.uma.taw.bank.entity.ClienteEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    protected ClienteRepository clienteRepository;
    @GetMapping("/")
    public String doListar(Model model, HttpSession session){
        List<ClienteEntity> lista;
        lista = clienteRepository.findAll();
        model.addAttribute("clientes",lista);
        return "clientes";
    }
}
