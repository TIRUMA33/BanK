package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.EmpresaRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.entity.EmpresaEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/iniciarSesion")
public class IniciarSesionController {

    private EmpresaRepository empresaRepository;

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String doIniciarSesion() {
        return "iniciarSesion";
    }

    @PostMapping("/")
    public String doAutenticar(@RequestParam("nif") String nif, @RequestParam("contrasena") String contrasena, Model model, HttpSession session) {
        String urlTo;
        UsuarioEntity usuario = this.usuarioRepository.autenticar(nif, contrasena);

        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "iniciarSesion";
        } else {
            session.setAttribute("usuario", usuario);
            if (this.empresaRepository.findById(usuario.getId()).isPresent()) {
                urlTo = "redirect:/empresa/" + usuario.getId();
            } else if (usuario.getTipoUsuarioByTipoUsuario().getId().equals(2)) {
                urlTo = "redirect:/empresa/" + this.empresaRepository.findByCif(usuario.getNif()).orElse(null).getId() + "/persona";
            } else {
                urlTo = "redirect:/persona/" + usuario.getId();
            }
        }

        return urlTo;
    }
}
