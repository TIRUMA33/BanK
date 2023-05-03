package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.FiltroEmpresaPersona;
import es.uma.taw.bank.ui.RegistroEmpresa;
import es.uma.taw.bank.ui.RegistroEmpresaPersona;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private CuentaRepository cuentaRepository;

    private DireccionRepository direccionRepository;

    private EmpresaClienteRepository empresaClienteRepository;

    private EmpresaPersonaRepository empresaPersonaRepository;

    private EmpresaRepository empresaRepository;

    private PersonaRepository personaRepository;

    private TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository;

    private TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository;

    private TransaccionRepository transaccionRepository;

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setCuentaRepository(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Autowired
    public void setDireccionRepository(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
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
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
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
    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{id}")
    public String doEmpresa(@PathVariable("id") String id, Model model, HttpSession session) {
        String urlTo = "inicioEmpresa";

        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario == null) {
            urlTo = "iniciarSesion";
        } else {
            model.addAttribute("empresa", this.empresaRepository.findById(Integer.parseInt(id)).orElse(null));
        }
        return urlTo;
    }

    @GetMapping("/{id}/persona")
    public String doEmpresaPersona(@PathVariable("id") String id, Model model, HttpSession session) {
        String urlTo = "inicioEmpresaPersona";

        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario == null) {
            urlTo = "iniciarSesion";
        } else {
            model.addAttribute("empresa", this.empresaRepository.findById(Integer.parseInt(id)).orElse(null));
        }

        return urlTo;
    }

    private RegistroEmpresa recuperarInfoEmpresa(int id) {
        RegistroEmpresa registroEmpresa = new RegistroEmpresa();

        registroEmpresa.setDireccion(this.direccionRepository.findByClienteByClienteId_Id(id).orElse(null));
        registroEmpresa.setUsuario(this.usuarioRepository.findById(id).orElse(null));
        registroEmpresa.setValida(registroEmpresa.getDireccion().getValida() != 0);
        registroEmpresa.setEmpresa(this.empresaRepository.findById(id).orElse(null));

        return registroEmpresa;
    }

    @GetMapping("/{id}/")
    public String doEditarEmpresa(@PathVariable("id") String id, Model model) {
        model.addAttribute("registroEmpresa", recuperarInfoEmpresa(Integer.parseInt(id)));
        return "editarEmpresa";
    }

    @PostMapping("/{id}/guardar")
    public String doGuardarEmpresa(@PathVariable("id") String id,
                                   @ModelAttribute("registroEmpresa") RegistroEmpresa edicionEmpresa) {
        RegistroEmpresa registroEmpresa = recuperarInfoEmpresa(Integer.parseInt(id));
        EmpresaEntity empresa = registroEmpresa.getEmpresa();
        EmpresaEntity empresaForm = edicionEmpresa.getEmpresa();
        DireccionEntity direccion = registroEmpresa.getDireccion();
        DireccionEntity direccionForm = edicionEmpresa.getDireccion();
        UsuarioEntity usuario = registroEmpresa.getUsuario();
        UsuarioEntity usuarioForm = edicionEmpresa.getUsuario();

        empresa.setCif(empresaForm.getCif());
        empresa.setNombre(empresaForm.getNombre());
        this.empresaRepository.save(empresa);

        direccion.setCalle(direccionForm.getCalle());
        direccion.setNumero(direccionForm.getNumero());
        direccion.setPlantaPuertaOficina(direccionForm.getPlantaPuertaOficina());
        direccion.setCiudad(direccionForm.getCiudad());
        direccion.setRegion(direccionForm.getRegion());
        direccion.setCodigoPostal(direccionForm.getCodigoPostal());
        direccion.setPais(direccionForm.getPais());
        direccion.setValida((byte) (edicionEmpresa.getValida() ? 1 : 0));
        this.direccionRepository.save(direccion);

        usuario.setNif(empresaForm.getCif());
        if (!(usuarioForm.getContrasena().isBlank() || registroEmpresa.getRcontrasena().isBlank())) {
            if (usuarioForm.getContrasena().equals(registroEmpresa.getRcontrasena())) {
                usuario.setContrasena(usuarioForm.getContrasena());
            }
        }
        this.usuarioRepository.save(usuario);

        return "redirect:/empresa/".concat(id).concat("/persona");
    }

    private RegistroEmpresaPersona recuperarInfoEmpresaPersona(int id) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();

        registroEmpresaPersona.setDireccion(this.direccionRepository.findByClienteByClienteId_Id(id).orElse(null));
        registroEmpresaPersona.setEmpresaPersona(this.empresaPersonaRepository.findByPersonaByIdPersona_Id(id).orElse(null));
        registroEmpresaPersona.setPersona(this.personaRepository.findById(id).orElse(null));
        registroEmpresaPersona.setUsuario(this.usuarioRepository.findById(id).orElse(null));
        registroEmpresaPersona.setValida(registroEmpresaPersona.getDireccion().getValida() != 0);

        return registroEmpresaPersona;
    }

    @GetMapping("/{id}/persona/{personaId}/editar")
    public String doEditarEmpresaPersona(@PathVariable("id") String id, @PathVariable("personaId") String personaId,
                                         Model model) {
        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionada = this.tipoPersonaRelacionadaRepository.findAll();

        model.addAttribute("registroEmpresaPersona", recuperarInfoEmpresaPersona(Integer.parseInt(personaId)));
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);

        return "editarEmpresaPersona";
    }

    @PostMapping("/{id}/persona/{personaId}/guardar")
    public String doGuardarEmpresaPersona(@PathVariable("id") String id, @PathVariable String personaId,
                                          @ModelAttribute("registroEmpresaPersona") RegistroEmpresaPersona edicionEmpresaPersona) {
        RegistroEmpresaPersona registroEmpresaPersona = recuperarInfoEmpresaPersona(Integer.parseInt(personaId));
        DireccionEntity direccion = registroEmpresaPersona.getDireccion();
        DireccionEntity direccionForm = edicionEmpresaPersona.getDireccion();
        EmpresaPersonaEntity empresaPersona = registroEmpresaPersona.getEmpresaPersona();
        EmpresaPersonaEntity empresaPersonaForm = edicionEmpresaPersona.getEmpresaPersona();
        PersonaEntity persona = registroEmpresaPersona.getPersona();
        PersonaEntity personaForm = edicionEmpresaPersona.getPersona();
        UsuarioEntity usuario = registroEmpresaPersona.getUsuario();
        UsuarioEntity usuarioForm = edicionEmpresaPersona.getUsuario();

        direccion.setCalle(direccionForm.getCalle());
        direccion.setNumero(direccionForm.getNumero());
        direccion.setPlantaPuertaOficina(direccionForm.getPlantaPuertaOficina());
        direccion.setCiudad(direccionForm.getCiudad());
        direccion.setRegion(direccionForm.getRegion());
        direccion.setCodigoPostal(direccionForm.getCodigoPostal());
        direccion.setPais(direccionForm.getPais());
        direccion.setValida((byte) (edicionEmpresaPersona.getValida() ? 1 : 0));
        this.direccionRepository.save(direccion);

        empresaPersona.setTipoPersonaRelacionadaByIdTipo(empresaPersonaForm.getTipoPersonaRelacionadaByIdTipo());
        this.empresaPersonaRepository.save(empresaPersona);

        persona.setDni(personaForm.getDni());
        persona.setNombre(personaForm.getNombre());
        persona.setApellido1(personaForm.getApellido1());
        persona.setApellido2(personaForm.getApellido2());
        persona.setFechaNacimiento(personaForm.getFechaNacimiento());
        this.personaRepository.save(persona);

        usuario.setNif(registroEmpresaPersona.getEmpresaPersona().getEmpresaByIdEmpresa().getCif());
        if (!(usuarioForm.getContrasena().isBlank() || registroEmpresaPersona.getRcontrasena().isBlank())) {
            if (usuarioForm.getContrasena().equals(registroEmpresaPersona.getRcontrasena())) {
                usuario.setContrasena(usuarioForm.getContrasena());
            }
        }
        this.usuarioRepository.save(usuario);

        return "redirect:/empresa/".concat(id).concat("/persona");
    }

    private String procesarFiltrado(String empresaId, String personaId, FiltroEmpresaPersona filtro, Model model) {
        List<Object[]> personas;
        String urlTo = "listadoEmpresaPersonas";

        if (filtro == null) {
            personas = this.personaRepository.distintasPersonasPorEmpresa(empresaId, personaId);
            filtro = new FiltroEmpresaPersona();
        } else if (!filtro.getFechaNacimiento() && (filtro.getTipo() == null || filtro.getTipo().isEmpty())) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorDniNombre(empresaId, personaId,
                    filtro.getTexto());
        } else if (filtro.getTexto().isBlank() && (filtro.getTipo() == null || filtro.getTipo().isEmpty())) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorFechaNacimiento(empresaId, personaId);
        } else if (filtro.getTexto().isBlank() && !filtro.getFechaNacimiento()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTipo(empresaId, personaId, filtro.getTipo());
        } else if (filtro.getTipo() == null || filtro.getTipo().isEmpty()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorDniNombreFechaNacimiento(empresaId,
                    personaId, filtro.getTexto());
        } else if (!filtro.getFechaNacimiento()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorDniNombreTipo(empresaId, personaId,
                    filtro.getTexto(), filtro.getTipo());
        } else if (filtro.getTexto().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorFechaNacimientoTipo(empresaId, personaId,
                    filtro.getTipo());
        } else {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorDniNombreFechaNacimientoTipo(empresaId,
                    personaId, filtro.getTexto(), filtro.getTipo());
        }

        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionada = this.tipoPersonaRelacionadaRepository.findAll();

        model.addAttribute("personas", personas);
        model.addAttribute("filtro", filtro);
        model.addAttribute("tipoPersonaRelacionada", tipoPersonaRelacionada);

        return urlTo;
    }

    @GetMapping("{id}/persona/{personaId}/listar")
    public String doListarEmpresaPersonas(@PathVariable("id") String id, @PathVariable("personaId") String personaId,
                                          Model model) {
        return this.procesarFiltrado(id, personaId, null, model);
    }

    @PostMapping("{id}/persona/{personaId}/filtrar")
    public String doFiltrarEmpresaPersona(@PathVariable("id") String id, @PathVariable("personaId") String personaId,
                                          @ModelAttribute("filtro") FiltroEmpresaPersona filtro, Model model) {
        return this.procesarFiltrado(id, personaId, filtro, model);
    }

    @GetMapping("{id}/persona/{personaId}/permiso/{seleccionadoId}")
    public String doPermisoEmpresaPersona(@PathVariable("id") String id, @PathVariable("personaId") String personaId,
                                          @PathVariable("seleccionadoId") String seleccionadoId, @ModelAttribute(
                                                  "filtro") FiltroEmpresaPersona filtro, Model model) {
        EmpresaClienteEntity empresaCliente = this.empresaClienteRepository.buscarTipoPorPersona(seleccionadoId);

        if (empresaCliente.getTipoClienteRelacionadoByIdTipo().getId() == 1) {
            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(2).orElse(null));
        } else {
            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(1).orElse(null));
        }

        this.empresaClienteRepository.save(empresaCliente);

        model.addAttribute("empresaId", id);
        model.addAttribute("personaId", personaId);

        return this.procesarFiltrado(id, personaId, filtro, model);
    }

    @GetMapping("{id}/persona/{personaId}/transferencia")
    public String doTransferenciaEmpresa(@PathVariable("id") String id, @PathVariable("personaId") String personaId,
                                         Model model) {
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setCuentaBancoByCuentaOrigen(this.cuentaRepository.buscarPorCliente(Integer.parseInt(id)).get(0));
        List<CuentaBancoEntity> cuentas =
                this.cuentaRepository.buscarSinMi(transaccion.getCuentaBancoByCuentaOrigen().getId());

        model.addAttribute("cuentas", cuentas);
        model.addAttribute("transaccion", transaccion);
        model.addAttribute("empresaId", id);
        model.addAttribute("personaId", personaId);

        return "transferenciaEmpresa";
    }

    @PostMapping("{id}/persona/{personaId}/transferencia/realizar")
    public String doRealizarTransferenciaEmpresa(@PathVariable("id") String id,
                                                 @PathVariable("personaId") String personaId, @ModelAttribute(
                                                         "transaccion") TransaccionEntity transaccion) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaccion.setFechaEjecucion(timestamp);
        transaccion.setFechaInstruccion(timestamp);

        CuentaBancoEntity origen =
                this.cuentaRepository.findById(transaccion.getCuentaBancoByCuentaOrigen().getId()).orElse(null);
        CuentaBancoEntity destino =
                this.cuentaRepository.findById(transaccion.getCuentaBancoByCuentaDestino().getId()).orElse(null);

        if (origen != null && destino != null) {
            origen.setSaldo(origen.getSaldo() - transaccion.getCantidad());
            destino.setSaldo(destino.getSaldo() + transaccion.getCantidad());

            this.transaccionRepository.save(transaccion);
            this.cuentaRepository.save(origen);
            this.cuentaRepository.save(destino);
        }

        return "redirect:/empresa/".concat(id).concat("/persona");
    }
}
