package es.uma.taw.bank.controller;

/**
 * @author Óscar Fernández Díaz 75%
 * @author Pablo Ruiz Galianez 25%
 */

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

    private CuentaRepository cuentaRepository;

    private DireccionRepository direccionRepository;

    private DivisaRepository divisaRepository;

    private EmpresaClienteRepository empresaClienteRepository;

    private EmpresaPersonaRepository empresaPersonaRepository;

    private EmpresaRepository empresaRepository;

    private EntidadBancariaRepository entidadBancariaRepository;

    private EstadoClienteRepository estadoClienteRepository;

    private EstadoCuentaRepository estadoCuentaRepository;

    private PersonaRepository personaRepository;

    private TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository;

    private TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository;

    private TipoUsuarioRepository tipoUsuarioRepository;

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Autowired
    public void setCuentaBancoRepository(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Autowired
    public void setDireccionRepository(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Autowired
    public void setDivisaRepository(DivisaRepository divisaRepository) {
        this.divisaRepository = divisaRepository;
    }

    @Autowired
    public void setEmpresaClienteRepository(EmpresaClienteRepository empresaClienteRepository) {
        this.empresaClienteRepository = empresaClienteRepository;
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
    public void setEntidadBancariaEntity(EntidadBancariaRepository entidadBancariaRepository) {
        this.entidadBancariaRepository = entidadBancariaRepository;
    }

    @Autowired
    public void setEstadoClienteRepository(EstadoClienteRepository estadoClienteRepository) {
        this.estadoClienteRepository = estadoClienteRepository;
    }

    @Autowired
    public void setEstadoCuentaRepository(EstadoCuentaRepository estadoCuentaRepository) {
        this.estadoCuentaRepository = estadoCuentaRepository;
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
    public void setTipoClienteRelacionadoRepository(TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository) {
        this.tipoClienteRelacionadoRepository = tipoClienteRelacionadoRepository;
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

    @PostMapping("/persona/")
    public String doRegistrarPersona(@ModelAttribute("persona") RegistroPersona registroPersona) {
        String urlTo;
        ClienteEntity cliente = registroPersona.getCliente();
        DireccionEntity direccion = registroPersona.getDireccion();
        PersonaEntity persona = registroPersona.getPersona();
        UsuarioEntity usuario = registroPersona.getUsuario();

        if (registroPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroPersona.getValida());

            persona.setId(cliente.getId());
            this.personaRepository.save(persona);

            usuario.setId(cliente.getId());
            usuario.setNif(persona.getDni());
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioRepository.findById(1).orElse(null));
            this.usuarioRepository.save(usuario);

            urlTo = "redirect:/persona/";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }

    @GetMapping("/empresa/{id}/persona")
    public String doRegistroEmpresaPersona(@PathVariable("id") Integer id, Model model) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();
        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionada = this.tipoPersonaRelacionadaRepository.findAll();
        List<Object[]> personas = this.personaRepository.personasPorEmpresa(id);
        model.addAttribute("registroEmpresaPersona", registroEmpresaPersona);
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);
        model.addAttribute("personas", personas);
        model.addAttribute("empresaId", id);
        return "registroEmpresaPersona";
    }

    @PostMapping("/empresa/{id}/persona/anadir")
    public String doRegistrarEmpresaPersona(@PathVariable("id") Integer id,
                                            @ModelAttribute("empresaPersona") RegistroEmpresaPersona registroEmpresaPersona) {
        String urlTo = "redirect:/registro/empresa/" + id + "/persona";
        ClienteEntity cliente = registroEmpresaPersona.getCliente();
        DireccionEntity direccion = registroEmpresaPersona.getDireccion();
        PersonaEntity persona = registroEmpresaPersona.getPersona();
        UsuarioEntity usuario = registroEmpresaPersona.getUsuario();
        EmpresaPersonaEntity empresaPersona = registroEmpresaPersona.getEmpresaPersona();
        EmpresaClienteEntity empresaCliente = registroEmpresaPersona.getEmpresaCliente();
        EmpresaEntity empresa = this.empresaRepository.findById(id).orElse(null);

        if (registroEmpresaPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroEmpresaPersona.getValida());

            persona.setId(cliente.getId());
            this.personaRepository.save(persona);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa != null ? empresa.getCif() : null);
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioRepository.findById(2).orElse(null));
            this.usuarioRepository.save(usuario);

            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(1).orElse(null));
            empresaCliente.setEmpresaByIdEmpresa(empresa);
            empresaCliente.setPersonaByIdPersona(persona);
            this.empresaClienteRepository.save(empresaCliente);

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
    public String doBorrarEmpresaPersona(@PathVariable("id") Integer id, HttpServletRequest request) {
        for (String idPersona : request.getParameterValues("personaId")) {
            int personaId = Integer.parseInt(idPersona);
            this.empresaPersonaRepository.deleteByPersonaByIdPersona_Id(personaId);
            this.usuarioRepository.deleteById(personaId);
            this.personaRepository.deleteById(personaId);
            this.direccionRepository.deleteByClienteByClienteId_Id(personaId);
            this.clienteRepository.deleteById(personaId);
        }

        return "redirect:/registro/empresa/".concat(String.valueOf(id)).concat("/persona");
    }
}
