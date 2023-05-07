package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.FiltroEmpresaPersona;
import es.uma.taw.bank.ui.FiltroOperacionesEmpresa;
import es.uma.taw.bank.ui.RegistroEmpresa;
import es.uma.taw.bank.ui.RegistroEmpresaPersona;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private CuentaRepository cuentaRepository;

    private DireccionRepository direccionRepository;

    private DivisaRepository divisaRepository;

    private EmpresaClienteRepository empresaClienteRepository;

    private EmpresaPersonaRepository empresaPersonaRepository;

    private EmpresaRepository empresaRepository;

    private EstadoCuentaRepository estadoCuentaRepository;

    private OperacionRepository operacionRepository;

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
    public void setEstadoCuentaRepository(EstadoCuentaRepository estadoCuentaRepository) {
        this.estadoCuentaRepository = estadoCuentaRepository;
    }

    @Autowired
    public void setOperacionRepository(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
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

    @GetMapping("/{id}/editar")
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
        } else if (!filtro.getFechaNacimiento() && filtro.getTipo().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTexto(empresaId, personaId,
                    filtro.getTexto());
        } else if (filtro.getTexto().isBlank() && filtro.getTipo().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorFechaNacimiento(empresaId, personaId);
        } else if (filtro.getTexto().isBlank() && !filtro.getFechaNacimiento()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTipo(empresaId, personaId, filtro.getTipo());
        } else if (filtro.getTipo().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTextoFechaNacimiento(empresaId,
                    personaId, filtro.getTexto());
        } else if (!filtro.getFechaNacimiento()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTextoTipo(empresaId, personaId,
                    filtro.getTexto(), filtro.getTipo());
        } else if (filtro.getTexto().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorFechaNacimientoTipo(empresaId, personaId,
                    filtro.getTipo());
        } else {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTextoFechaNacimientoTipo(empresaId,
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
            OperacionEntity operacion = new OperacionEntity();

            origen.setSaldo(origen.getSaldo() - transaccion.getCantidad());
            destino.setSaldo(destino.getSaldo() + transaccion.getCantidad());
            operacion.setPersonaByPersonaId(this.personaRepository.findById(Integer.parseInt(personaId)).orElse(null));
            operacion.setTransaccionByTransaccionId(transaccion);

            this.transaccionRepository.save(transaccion);
            this.cuentaRepository.save(origen);
            this.cuentaRepository.save(destino);
            this.operacionRepository.save(operacion);
        }

        return "redirect:/empresa/".concat(id).concat("/persona");
    }

    @GetMapping("{id}/cuentas")
    public String doCuentas(@PathVariable("id") String id, Model model) {
        List<CuentaBancoEntity> cuentas = this.cuentaRepository.buscarPorCliente(Integer.parseInt(id));

        model.addAttribute("cuentas", cuentas);

        return "estadoCuentaEmpresa";
    }

    @GetMapping("{id}/cuenta/{cuentaId}/permiso/{seleccionadoId}")
    public String doPermisoCuentas(@PathVariable("id") String id, @PathVariable("cuentaId") String cuentaId,
                                   @PathVariable("seleccionadoId") String seleccionadoId,
                                   @ModelAttribute("filtro") FiltroEmpresaPersona filtro, Model model) {
        CuentaBancoEntity cuentaBanco = this.cuentaRepository.findById(Integer.parseInt(seleccionadoId)).orElse(null);

        if (cuentaBanco.getEstadoCuentaByEstadoCuentaId().getTipo().equals("Activa")) {
            cuentaBanco.setEstadoCuentaByEstadoCuentaId(this.estadoCuentaRepository.findById(4).orElse(null));
        } else if (cuentaBanco.getEstadoCuentaByEstadoCuentaId().getTipo().equals("Bloqueada")) {
            cuentaBanco.setEstadoCuentaByEstadoCuentaId(this.estadoCuentaRepository.findById(5).orElse(null));
        }

        this.cuentaRepository.save(cuentaBanco);

        model.addAttribute("empresaId", id);
        model.addAttribute("cuentaId", cuentaId);

        return "redirect:/empresa/".concat(id).concat("/cuentas");
    }

    private String procesarFiltradoOperaciones(Integer empresaId, FiltroOperacionesEmpresa filtro, Model model) {
        List<CuentaBancoEntity> cuentas = this.cuentaRepository.buscarPorCliente(empresaId);
        List<OperacionEntity> ops = null;
        List<OperacionEntity> operaciones = this.operacionRepository.buscarPorCuentasYEmpresa(cuentas, empresaId);
        List<EmpresaEntity> empresas = this.empresaRepository.findAll();
        List<PersonaEntity> personas = this.personaRepository.findAll();
        String urlTo = "listadoOperacionesEmpresa";

        if (filtro == null) {
            filtro = new FiltroOperacionesEmpresa();
        } else if (!filtro.getCantidad() && !filtro.getFechaEjecucion()) {
            ops =
                    this.operacionRepository.buscarPorCuenta(filtro.getCuenta());
        } else if (filtro.getCuenta().isBlank() && !filtro.getFechaEjecucion()) {
            ops = this.operacionRepository.ordenarPorCantidad();
        } else if (filtro.getCuenta().isBlank() && !filtro.getCantidad()) {
            ops = this.operacionRepository.ordenarPorFechaEjecucion();
        } else if (filtro.getCuenta().isBlank()) {
            ops = this.operacionRepository.ordenarPorCantidadYFechaEjecucion();
        } else if (!filtro.getCantidad()) {
            ops = this.operacionRepository.buscarPorCuentaYOrdenarPorFechaEjecucion(filtro.getCuenta());
        } else if (!filtro.getFechaEjecucion()) {
            ops =
                    this.operacionRepository.buscarPorCuentaYOrdenarPorCantidad(filtro.getCuenta());
        } else {
            ops =
                    this.operacionRepository.buscarPorCuentaYOrdenarPorCantidadYFechaEjecucion(filtro.getCuenta());
        }

        if (ops != null) {
            operaciones.retainAll(ops);
        }

        model.addAttribute("operaciones", operaciones);
        model.addAttribute("filtro", filtro);
        model.addAttribute("cuentas", this.operacionRepository.listarTodasCuentasTransaccion(cuentas));
        model.addAttribute("empresas", empresas);
        model.addAttribute("personas", personas);

        return urlTo;
    }

    @GetMapping("{id}/operaciones")
    public String doOperaciones(@PathVariable("id") Integer id, Model model) {
        return this.procesarFiltradoOperaciones(id, null, model);
    }

    @PostMapping("{id}/operaciones/filtrar")
    public String doFiltrarOperaciones(@PathVariable("id") Integer id,
                                       @ModelAttribute("filtro") FiltroOperacionesEmpresa filtro, Model model) {
        return this.procesarFiltradoOperaciones(id, filtro, model);
    }

    @GetMapping("{id}/cuenta/cambioDivisa")
    public String doCambioDivisa(@PathVariable("id") String id, Model model) {
        CuentaBancoEntity cuenta = this.cuentaRepository.buscarPorCliente(Integer.parseInt(id)).get(0);
        List<DivisaEntity> divisas = this.divisaRepository.buscarSinMi(cuenta.getDivisaByDivisaId().getId());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        List<String> cambios =
                divisas.stream().map(d -> decimalFormat.format(cuenta.getSaldo() * cuenta.getDivisaByDivisaId().getEquivalencia() / d.getEquivalencia()) + " " + d.getNombre()).collect(Collectors.toList());

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("divisas", divisas);
        model.addAttribute("cambios", cambios);
        model.addAttribute("cuentaId", cuenta.getId());

        return "cambioDivisaEmpresa";
    }

    @PostMapping("{id}/cuenta/{cuentaId}/cambioDivisa/realizar")
    public String doCambioDivisaRealizar(@PathVariable("id") String id, @PathVariable("cuentaId") String cuentaId,
                                         @ModelAttribute("divisaSelect") Integer divisaId) {
        DivisaEntity divisa = this.divisaRepository.findById(divisaId).orElse(null);
        CuentaBancoEntity cuenta = this.cuentaRepository.findById(Integer.parseInt(cuentaId)).orElse(null);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        cuenta.setSaldo(Double.parseDouble(decimalFormat.format((cuenta.getDivisaByDivisaId().getEquivalencia() * cuenta.getSaldo()) / divisa.getEquivalencia()).replace(",", ".")));
        cuenta.setDivisaByDivisaId(divisa);
        this.cuentaRepository.save(cuenta);

        return "redirect:/empresa/".concat(id).concat("/persona");
    }
}
