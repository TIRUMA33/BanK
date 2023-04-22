package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.RegistroEmpresa;
//import es.uma.taw.bank.ui.RegistroEmpresaPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class RegistroEmpresaController {
    private ClienteRepository clienteRepository;

    private DireccionRepository direccionRepository;

    private EmpresaRepository empresaRepository;

    private EstadoClienteRepository estadoClienteRepository;

    private TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository;

    private TipoUsuarioRepository tipoUsuarioRepository;

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Autowired
    public void setDireccionRepository(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Autowired
    public void setEstadoClienteRepository(EstadoClienteRepository estadoClienteRepository) {
        this.estadoClienteRepository = estadoClienteRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setTipoPersonaRelacionadaRepository(TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository) {
        this.tipoPersonaRelacionadaRepository = tipoPersonaRelacionadaRepository;
    }

    @Autowired
    public void setTipoUsuarioRepository(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @GetMapping("/")
    public String doRegistroEmpresa(Model model) {
        RegistroEmpresa registroEmpresa = new RegistroEmpresa();
        model.addAttribute("empresa", registroEmpresa);
        return "registroEmpresa";
    }

    @PostMapping("/")
    public String doRegistrarEmpresa(@ModelAttribute("empresa") RegistroEmpresa registroEmpresa) {
        String urlTo = "redirect:/empresa/persona";
        ClienteEntity cliente = registroEmpresa.getCliente();
        DireccionEntity direccion = registroEmpresa.getDireccion();
        EmpresaEntity empresa = registroEmpresa.getEmpresa();
        UsuarioEntity usuario = registroEmpresa.getUsuario();

        if (registroEmpresa.getRcontrasena().equals(usuario.getContrasena())) {
            cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
            cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(3).orElse(null));
            this.clienteRepository.save(cliente);

            direccion.setValida((byte) (registroEmpresa.getValida() ? 1 : 0));
            direccion.setClienteByClienteId(cliente);
            this.direccionRepository.save(direccion);

            empresa.setId(cliente.getId());
            this.empresaRepository.save(empresa);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa.getCif());
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioRepository.findById(2).orElse(null));
            this.usuarioRepository.save(usuario);
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }

    @GetMapping("/persona")
    public String doRegistroEmpresaPersona(Model model) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();
        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionada = this.tipoPersonaRelacionadaRepository.findAll();
        model.addAttribute("empresaPersona", registroEmpresaPersona);
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);
        return "registroEmpresaPersona";
    }

    // TODO
    @PostMapping("/persona")
    public String doRegistrarEmpresaPersona(@ModelAttribute("usuario") ClienteEntity cliente) {
        return "redirect:/";
    }
}
