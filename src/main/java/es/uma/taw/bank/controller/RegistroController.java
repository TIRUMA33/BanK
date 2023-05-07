package es.uma.taw.bank.controller;

import es.uma.taw.bank.dto.*;
import es.uma.taw.bank.service.*;
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

    private ClienteService clienteService;

    private CuentaService cuentaService;

    private DireccionService direccionService;

    private DivisaService divisaService;

    private EmpresaClienteService empresaClienteService;

    private EmpresaPersonaService empresaPersonaService;

    private EmpresaService empresaService;

    private EntidadBancariaService entidadBancariaService;

    private EstadoClienteService estadoClienteService;

    private EstadoCuentaService estadoCuentaService;

    private PersonaService personaService;

    private TipoClienteRelacionadoService tipoClienteRelacionadoService;

    private TipoPersonaRelacionadaService tipoPersonaRelacionadaService;

    private TipoUsuarioService tipoUsuarioService;

    private UsuarioService usuarioService;

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Autowired
    public void setCuentaBancoService(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @Autowired
    public void setDireccionService(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @Autowired
    public void setDivisaService(DivisaService divisaService) {
        this.divisaService = divisaService;
    }

    @Autowired
    public void setEmpresaClienteService(EmpresaClienteService empresaClienteService) {
        this.empresaClienteService = empresaClienteService;
    }

    @Autowired
    public void setEmpresaPersonaService(EmpresaPersonaService empresaPersonaService) {
        this.empresaPersonaService = empresaPersonaService;
    }

    @Autowired
    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Autowired
    public void setEntidadBancariaService(EntidadBancariaService entidadBancariaService) {
        this.entidadBancariaService = entidadBancariaService;
    }

    @Autowired
    public void setEstadoClienteService(EstadoClienteService estadoClienteService) {
        this.estadoClienteService = estadoClienteService;
    }

    @Autowired
    public void setEstadoCuentaService(EstadoCuentaService estadoCuentaService) {
        this.estadoCuentaService = estadoCuentaService;
    }

    @Autowired
    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setTipoClienteRelacionadoService(TipoClienteRelacionadoService tipoClienteRelacionadoService) {
        this.tipoClienteRelacionadoService = tipoClienteRelacionadoService;
    }

    @Autowired
    public void setTipoPersonaRelacionadaService(TipoPersonaRelacionadaService tipoPersonaRelacionadaService) {
        this.tipoPersonaRelacionadaService = tipoPersonaRelacionadaService;
    }

    @Autowired
    public void setTipoUsuarioService(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    private void guardadoComun(ClienteDTO cliente, DireccionDTO direccion, boolean valida) {
        cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        cliente.setEstadoCliente(5);
        clienteService.guardarCliente(cliente);

        direccion.setValida((byte) (valida ? 1 : 0));
        direccion.setCliente(cliente.getId());
        direccionService.guardarDireccion(direccion);
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
        ClienteDTO cliente = registroEmpresa.getCliente();
        DireccionDTO direccion = registroEmpresa.getDireccion();
        EmpresaDTO empresa = registroEmpresa.getEmpresa();
        UsuarioDTO usuario = registroEmpresa.getUsuario();

        if (registroEmpresa.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroEmpresa.getValida());

            empresa.setId(cliente.getId());
            this.empresaService.guardarEmpresa(empresa);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa.getCif());
            usuario.setTipoUsuario(2);
            this.usuarioService.guardarUsuario(usuario);

            urlTo = "redirect:/registro/empresa/" + empresa.getId() + "/persona";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }
*/

    @PostMapping("/persona/")
    public String doRegistrarPersona(@ModelAttribute("persona") RegistroPersona registroPersona) {
        String urlTo;
        ClienteDTO cliente = registroPersona.getCliente();
        DireccionDTO direccion = registroPersona.getDireccion();
        PersonaDTO persona = registroPersona.getPersona();
        UsuarioDTO usuario = registroPersona.getUsuario();

        if (registroPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroPersona.getValida());

            persona.setId(cliente.getId());
            personaService.guardarPersona(persona);

            usuario.setId(cliente.getId());
            usuario.setNif(persona.getDni());
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioService.buscarTipoUsuario(1));
            usuarioService.guardarUsuario(usuario);

            urlTo = "redirect:/persona/";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }*/

    @GetMapping("/empresa/{id}/persona")
    public String doRegistroEmpresaPersona(@PathVariable("id") Integer id, Model model) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();
        List<TipoPersonaRelacionadaDTO> tipoPersonaRelacionada = this.tipoPersonaRelacionadaService.listarTipoPersonaRelacionada();
        List<Object[]> personas = this.personaService.buscarPersonasPorEmpresa(id);
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
        ClienteDTO cliente = registroEmpresaPersona.getCliente();
        DireccionDTO direccion = registroEmpresaPersona.getDireccion();
        PersonaDTO persona = registroEmpresaPersona.getPersona();
        UsuarioDTO usuario = registroEmpresaPersona.getUsuario();
        EmpresaPersonaDTO empresaPersona = registroEmpresaPersona.getEmpresaPersona();
        EmpresaClienteDTO empresaCliente = registroEmpresaPersona.getEmpresaCliente();
        EmpresaDTO empresa = this.empresaService.buscarEmpresa(id);

        if (registroEmpresaPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroEmpresaPersona.getValida());

            persona.setId(cliente.getId());
            this.personaService.guardarPersona(persona);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa != null ? empresa.getCif() : null);
            usuario.setTipoUsuario(2);
            this.usuarioService.guardarUsuario(usuario);

            empresaCliente.setTipoClienteRelacionado(1);
            empresaCliente.setEmpresa(empresa.getId());
            empresaCliente.setPersona(persona.getId());
            this.empresaClienteService.guardarEmpresaCliente(empresaCliente);

            empresaPersona.setEmpresa(empresa.getId());
            empresaPersona.setPersona(persona.getId());
            this.empresaPersonaService.guardarEmpresaPersona(empresaPersona);
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
            this.empresaPersonaService.borrarEmpresaPersonaPorPersona(personaId);
            this.usuarioService.borrarUsuario(personaId);
            this.personaService.borrarPersona(personaId);
            this.direccionService.borrarDireccionPorCliente(personaId);
            this.clienteService.borrarCliente(personaId);
        }

        return "redirect:/registro/empresa/".concat(String.valueOf(id)).concat("/persona");
    }
}