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

import java.util.List;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private ClienteService clienteService;

    private DireccionService direccionService;

    private EmpresaClienteService empresaClienteService;

    private EmpresaPersonaService empresaPersonaService;

    private EmpresaService empresaService;

    private PersonaService personaService;

    private TipoPersonaRelacionadaService tipoPersonaRelacionadaService;

    private UsuarioService usuarioService;

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Autowired
    public void setDireccionService(DireccionService direccionService) {
        this.direccionService = direccionService;
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
    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setTipoPersonaRelacionadaService(TipoPersonaRelacionadaService tipoPersonaRelacionadaService) {
        this.tipoPersonaRelacionadaService = tipoPersonaRelacionadaService;
    }

    private void guardadoComun(ClienteDTO cliente, DireccionDTO direccion, boolean valida) {
        clienteService.guardarCliente(cliente);

        direccionService.guardarDireccion(direccion, this.clienteService.ultimoCliente().getId(), valida);
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

            ClienteDTO c = this.clienteService.ultimoCliente();
            EmpresaDTO e = this.empresaService.ultimaEmpresa();

            this.empresaService.guardarEmpresa(empresa, c.getId());

            this.usuarioService.guardarUsuario(usuario, c.getId(), empresa.getCif(), 2);

            urlTo = "redirect:/registro/empresa/" + e.getId() + "/persona";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;
    }

    @PostMapping("/persona/")
    public String doRegistrarPersona(@ModelAttribute("persona") RegistroPersona registroPersona) {
        String urlTo;
        ClienteDTO cliente = registroPersona.getCliente();
        DireccionDTO direccion = registroPersona.getDireccion();
        PersonaDTO persona = registroPersona.getPersona();
        UsuarioDTO usuario = registroPersona.getUsuario();

        if (registroPersona.getRcontrasena().equals(usuario.getContrasena())) {
            guardadoComun(cliente, direccion, registroPersona.getValida());

            ClienteDTO c = this.clienteService.ultimoCliente();
            PersonaDTO p = this.personaService.ultimaPersona();

            this.personaService.guardarPersona(persona, c.getId());
            this.usuarioService.guardarUsuario(usuario, c.getId(), persona.getDni(), 1);

            urlTo = "redirect:/persona/";
        } else {
            urlTo = "contrasenaNoCoincide";
        }

        return urlTo;


    }

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

            this.personaService.guardarPersona(persona, cliente.getId());

            this.usuarioService.guardarUsuario(usuario, cliente.getId(), empresa.getCif(), 2);

            this.empresaClienteService.guardarEmpresaCliente(empresaCliente, 1, empresa.getId(), persona.getId());

            this.empresaPersonaService.guardarEmpresaPersona(empresaPersona, empresa.getId(), persona.getId());
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