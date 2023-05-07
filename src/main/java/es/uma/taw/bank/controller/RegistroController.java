package es.uma.taw.bank.controller;

import es.uma.taw.bank.DataGenerator;
import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.dto.ClienteDTO;
import es.uma.taw.bank.dto.PersonaDTO;
import es.uma.taw.bank.dto.UsuarioDTO;
import es.uma.taw.bank.dto.DireccionDTO;
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
/*
    private EmpresaClienteService empresaClienteService;

    private EmpresaPersonaService empresaPersonaService;

    private EmpresaService empresaService;
*/
/*
    private EntidadBancariaService entidadBancariaService;

    private EstadoClienteService estadoClienteService;
*/

    private EstadoCuentaService estadoCuentaService;

    private PersonaService personaService;

/*    private TipoClienteRelacionadoService tipoClienteRelacionadoService;

    private TipoPersonaRelacionadaService tipoPersonaRelacionadaService; */

    private TipoUsuarioService tipoUsuarioService;

    private UsuarioService usuarioService;
/*

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
    public void setEntidadBancariaDTO(EntidadBancariaService entidadBancariaService) {
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
*/

    private void guardadoComun(ClienteDTO cliente, DireccionDTO direccion, boolean valida) {
        cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        cliente.setEstadoid(5);
        clienteService.guardarCliente(cliente);

        direccion.setValida((byte) (valida ? 1 : 0));
        direccion.setClienteByClienteId(cliente);
        direccionService.guardarDireccion(direccion);
    }

    @GetMapping("/")
    public String doRegistro() {
        return "registro";
    }
/*
    @GetMapping("/empresa/")
    public String doRegistroEmpresa(Model model) {
        RegistroEmpresa registroEmpresa = new RegistroEmpresa();
        model.addAttribute("empresa", registroEmpresa);
        return "registroEmpresa";
    }
*/

    @GetMapping("/persona/")
    public String doRegistrarpersona(Model model) {
        RegistroPersona registroPersona = new RegistroPersona();
        model.addAttribute("persona", registroPersona);
        return "registroPersona";
    }

/*

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
            this.empresaService.save(empresa);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa.getCif());
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioService.findById(2).orElse(null));
            usuarioservice

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
    }
/*

    @GetMapping("/empresa/{id}/persona")
    public String doRegistroEmpresaPersona(@PathVariable("id") String id, Model model) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();
        List<TipoPersonaRelacionadaDTO> tipoPersonaRelacionada = this.tipoPersonaRelacionadaService.findAll();
        List<Object[]> personas = this.personaService.personasPorEmpresa(id);
        model.addAttribute("registroEmpresaPersona", registroEmpresaPersona);
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);
        model.addAttribute("personas", personas);
        model.addAttribute("empresaId", id);
        return "registroEmpresaPersona";
    }

    @PostMapping("/empresa/{id}/persona/anadir")
    public String doRegistrarEmpresaPersona(@PathVariable("id") String id,
                                            @ModelAttribute("empresaPersona") RegistroEmpresaPersona registroEmpresaPersona) {
        String urlTo = "redirect:/registro/empresa/" + id + "/persona";
        ClienteDTO cliente = registroEmpresaPersona.getCliente();
        DireccionDTO direccion = registroEmpresaPersona.getDireccion();
        PersonaDTO persona = registroEmpresaPersona.getPersona();
        UsuarioDTO usuario = registroEmpresaPersona.getUsuario();
        EmpresaPersonaDTO empresaPersona = registroEmpresaPersona.getEmpresaPersona();
        EmpresaClienteDTO empresaCliente = registroEmpresaPersona.getEmpresaCliente();
        EmpresaDTO empresa = this.empresaService.findById(Integer.parseInt(id)).orElse(null);

        if (registroEmpresaPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroEmpresaPersona.getValida());

            persona.setId(cliente.getId());
            this.personaService.save(persona);

            usuario.setId(cliente.getId());
            usuario.setNif(empresa != null ? empresa.getCif() : null);
            usuario.setTipoUsuarioByTipoUsuario(this.tipoUsuarioService.findById(2).orElse(null));
            this.usuarioService.save(usuario);

            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoService.findById(1).orElse(null));
            empresaCliente.setEmpresaByIdEmpresa(empresa);
            empresaCliente.setPersonaByIdPersona(persona);
            this.empresaClienteService.save(empresaCliente);

            empresaPersona.setEmpresaByIdEmpresa(empresa);
            empresaPersona.setPersonaByIdPersona(persona);
            this.empresaPersonaService.save(empresaPersona);
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
            this.empresaPersonaService.deleteByPersonaByIdPersona_Id(personaId);
            this.usuarioService.deleteById(personaId);
            this.personaService.deleteById(personaId);
            this.direccionService.deleteByClienteByClienteId_Id(personaId);
            this.clienteService.deleteById(personaId);
        }

        return "redirect:/registro/empresa/".concat(id).concat("/persona");
    }
    */
}