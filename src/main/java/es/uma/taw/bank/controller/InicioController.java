package es.uma.taw.bank.controller;

/**
 * @author Óscar Fernández Díaz
 */

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    @GetMapping("/")
    public String doIniciar() {
        return "inicio";
    }

    @GetMapping("/cerrarSesion")
    public String doCerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
