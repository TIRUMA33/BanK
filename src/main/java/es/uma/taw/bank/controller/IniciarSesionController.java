package es.uma.taw.bank.controller;

import es.uma.taw.bank.dto.UsuarioDTO;
import es.uma.taw.bank.service.EmpresaService;
import es.uma.taw.bank.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/iniciarSesion")
public class IniciarSesionController {
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private UsuarioService usuarioService;

/*
    @Autowired
    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
*/

    @GetMapping("/")
    public String doIniciarSesion() {
        return "iniciarSesion";
    }

    @PostMapping("/")
    public String doAutenticar(@RequestParam("nif") String nif, @RequestParam("contrasena") String contrasena,
                               Model model, HttpSession session) {
        String urlTo;
        UsuarioDTO usuario = this.usuarioService.autenticar(nif, contrasena);

        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "iniciarSesion";
        } else {
            session.setAttribute("usuario", usuario);
            if (usuario.getTipoUsuarioByTipoUsuario().getId().equals(3)){
                urlTo = "redirect:/asistencia/conversaciones";
            }/*else if (this.empresaService.buscarEmpresa(usuario.getId())!=null) { //he puesto un null en vez de ispresent porque no funcionaba
                urlTo = "redirect:/empresa/" + usuario.getId();
            } else if (usuario.getTipoUsuarioByTipoUsuario().getId().equals(2)) {
                urlTo = "redirect:/empresa/" + Objects.requireNonNull(this.empresaService.buscarEmpresaPorCif(usuario.getNif())).getId() + "/persona";
            }*/ else {
                urlTo = "redirect:/persona/";
            }
        }

        return urlTo;
    }
}
