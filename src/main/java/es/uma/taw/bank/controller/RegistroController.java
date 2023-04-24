package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.RegistroEmpresa;
import es.uma.taw.bank.ui.RegistroEmpresaPersona;
import es.uma.taw.bank.ui.RegistroPersona;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/registro")
public class RegistroController {

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

    private void guardadoComun(ClienteEntity cliente, DireccionEntity direccion, boolean valida) {
        cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(5).orElse(null));
        this.clienteRepository.save(cliente);

        direccion.setValida((byte) (valida ? 1 : 0));
        direccion.setClienteByClienteId(cliente);
        this.direccionRepository.save(direccion);
    }

    @GetMapping("/")
    public String doRegistro() {
        return "registro";
    }

    @GetMapping("/empresa/")
    public String doRegistroEmpresa(Model model) {
        RegistroEmpresa registroEmpresa = new RegistroEmpresa();
        model.addAttribute("empresa", registroEmpresa);
        return "registroEmpresa";
    }

    @GetMapping("/persona/")
    public String doRegistrarpersona(Model model) {
        RegistroPersona registroPersona = new RegistroPersona();
        model.addAttribute("persona", registroPersona);
        return "registroPersona";
    }


    @PostMapping("/empresa/")
    public String doRegistrarEmpresa(@ModelAttribute("empresa") RegistroEmpresa registroEmpresa) {
        String urlTo;
        ClienteEntity cliente = registroEmpresa.getCliente();
        DireccionEntity direccion = registroEmpresa.getDireccion();
        EmpresaEntity empresa = registroEmpresa.getEmpresa();
        UsuarioEntity usuario = registroEmpresa.getUsuario();

        if (registroEmpresa.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroEmpresa.getValida());

            empresa.setId(cliente.getId());
            this.empresaRepository.save(empresa);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa.getCif());
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioRepository.findById(2).orElse(null));
            this.usuarioRepository.save(usuario);

            urlTo = "redirect:/registro/empresa/" + empresa.getId() + "/persona";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }

    @GetMapping("/empresa/{id}/persona")
    public String doRegistroEmpresaPersona(@PathVariable("id") String id, Model model) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();
        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionada = this.tipoPersonaRelacionadaRepository.findAll();
        List<Object[]> personas = this.personaRepository.personasPorEmpresa(Integer.parseInt(id));
        model.addAttribute("registroEmpresaPersona", registroEmpresaPersona);
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);
        model.addAttribute("empresa", this.empresaRepository.findById(Integer.parseInt(id)).orElse(null));
        model.addAttribute("personas", personas);
        return "registroEmpresaPersona";
    }

    @PostMapping("/empresa/{id}/persona/anadir")
    public String doRegistrarEmpresaPersona(@PathVariable("id") String id, @ModelAttribute("empresaPersona") RegistroEmpresaPersona registroEmpresaPersona, @ModelAttribute("fechaNacimiento") String fechaNacimiento) {
        String urlTo = "redirect:/registro/empresa/" + id + "/persona";
        ClienteEntity cliente = registroEmpresaPersona.getCliente();
        DireccionEntity direccion = registroEmpresaPersona.getDireccion();
        PersonaEntity persona = registroEmpresaPersona.getPersona();
        UsuarioEntity usuario = registroEmpresaPersona.getUsuario();
        EmpresaPersonaEntity empresaPersona = registroEmpresaPersona.getEmpresaPersona();
        EmpresaEntity empresa = this.empresaRepository.findById(Integer.parseInt(id)).orElse(null);

        if (registroEmpresaPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroEmpresaPersona.getValida());

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

    @Transactional
    @PostMapping("/empresa/{id}/persona/borrar")
    public String doBorrarEmpresaPersona(@PathVariable("id") String id, HttpServletRequest request) {
        for (String idPersona : request.getParameterValues("personaId")) {
            int personaId = Integer.parseInt(idPersona);
            this.empresaPersonaRepository.deleteByPersonaByIdPersona_Id(personaId);
            this.usuarioRepository.deleteById(personaId);
            this.personaRepository.deleteById(personaId);
            this.direccionRepository.deleteByClienteByClienteId_Id(personaId);
            this.clienteRepository.deleteById(personaId);
        }

        return "redirect:/registro/empresa/".concat(id).concat("/persona");
    }
}
