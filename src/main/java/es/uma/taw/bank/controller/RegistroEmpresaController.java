package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.RegistroEmpresa;
import es.uma.taw.bank.ui.RegistroEmpresaPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class RegistroEmpresaController {
    private ClienteRepository clienteRepository;

    private DireccionRepository direccionRepository;

    private EmpresaPersonaRepository empresaPersonaRepository;

    private EmpresaRepository empresaRepository;

    private EstadoClienteRepository estadoClienteRepository;

    private PersonaRepository personaRepository;

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
    public void setEmpresaPersonaRepository(EmpresaPersonaRepository empresaPersonaRepository) {
        this.empresaPersonaRepository = empresaPersonaRepository;
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
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
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
        String urlTo;
        ClienteEntity cliente = registroEmpresa.getCliente();
        DireccionEntity direccion = registroEmpresa.getDireccion();
        EmpresaEntity empresa = registroEmpresa.getEmpresa();
        UsuarioEntity usuario = registroEmpresa.getUsuario();

        if (registroEmpresa.getRcontrasena().equals(usuario.getContrasena())) {
            cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
            cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(5).orElse(null));
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

            urlTo = "redirect:/empresa/" + registroEmpresa.getEmpresa().getId() + "/persona";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }

    @GetMapping("/{id}/persona")
    public String doRegistroEmpresaPersona(@PathVariable("id") String id, Model model) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();
        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionada = this.tipoPersonaRelacionadaRepository.findAll();
        List<Object[]> personas = this.personaRepository.personasPorEmpresa(Integer.parseInt(id));
        model.addAttribute("empresaPersona", registroEmpresaPersona);
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);
        model.addAttribute("empresaId", Integer.parseInt(id));
        model.addAttribute("personas", personas);
        return "registroEmpresaPersona";
    }

    @PostMapping("/{id}/persona/anadir")
    public String doRegistrarEmpresaPersona(@PathVariable("id") String id, @ModelAttribute("empresaPersona") RegistroEmpresaPersona registroEmpresaPersona, @ModelAttribute("fechaNacimiento") String fechaNacimiento) {
        String urlTo = "redirect:/empresa/" + id + "/persona";
        ClienteEntity cliente = registroEmpresaPersona.getCliente();
        DireccionEntity direccion = registroEmpresaPersona.getDireccion();
        PersonaEntity persona = registroEmpresaPersona.getPersona();
        UsuarioEntity usuario = registroEmpresaPersona.getUsuario();
        EmpresaPersonaEntity empresaPersona = registroEmpresaPersona.getEmpresaPersona();
        EmpresaEntity empresa = this.empresaRepository.findById(Integer.parseInt(id)).orElse(null);

        if (registroEmpresaPersona.getRcontrasena().equals(usuario.getContrasena())) {
            cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
            cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(5).orElse(null));
            this.clienteRepository.save(cliente);

            direccion.setValida((byte) (registroEmpresaPersona.getValida() ? 1 : 0));
            direccion.setClienteByClienteId(cliente);
            this.direccionRepository.save(direccion);

            persona.setId(cliente.getId());
            persona.setFechaNacimiento(Timestamp.valueOf(fechaNacimiento + " 00:00:00"));
            this.personaRepository.save(persona);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa != null ? empresa.getCif() : null);
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioRepository.findById(2).orElse(null));
            this.usuarioRepository.save(usuario);

            empresaPersona.setEmpresaByIdEmpresa(empresa);
            empresaPersona.setPersonaByIdPersona(persona);
            this.empresaPersonaRepository.save(empresaPersona);
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }

    // TODO Botón Borrar en listaEmpresaPersonas
}
