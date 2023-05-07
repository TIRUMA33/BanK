package es.uma.taw.bank.controller;

import es.uma.taw.bank.dto.*;
import es.uma.taw.bank.service.*;
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

    private CuentaService cuentaService;

    private DireccionService direccionService;

    private DivisaService divisaService;

    private EmpresaClienteService empresaClienteService;

    private EmpresaPersonaService empresaPersonaService;

    private EmpresaService empresaService;

    private OperacionService operacionService;

    private PersonaService personaService;

    private TipoPersonaRelacionadaService tipoPersonaRelacionadaService;

    private TransaccionService transaccionService;

    private UsuarioService usuarioService;

    @Autowired
    public void setCuentaService(CuentaService cuentaService) {
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
    public void setOperacionService(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @Autowired
    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setTipoPersonaRelacionadaService(TipoPersonaRelacionadaService tipoPersonaRelacionadaService) {
        this.tipoPersonaRelacionadaService = tipoPersonaRelacionadaService;
    }

    @Autowired
    public void setTransaccionService(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public String doEmpresa(@PathVariable("id") Integer id, Model model, HttpSession session) {
        String urlTo = "inicioEmpresa";
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        if (usuario == null) {
            urlTo = "iniciarSesion";
        } else {
            model.addAttribute("empresa", this.empresaService.buscarEmpresa(id));
        }

        return urlTo;
    }

    @GetMapping("/{id}/persona")
    public String doEmpresaPersona(@PathVariable("id") Integer id, Model model, HttpSession session) {
        String urlTo = "inicioEmpresaPersona";
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        if (usuario == null) {
            urlTo = "iniciarSesion";
        } else {
            model.addAttribute("empresa", this.empresaService.buscarEmpresa(id));
        }

        return urlTo;
    }

    private RegistroEmpresa recuperarInfoEmpresa(int id) {
        RegistroEmpresa registroEmpresa = new RegistroEmpresa();

        registroEmpresa.setDireccion(this.direccionService.buscarPorCliente(id));
        registroEmpresa.setUsuario(this.usuarioService.buscarUsuario(id));
        registroEmpresa.setValida(registroEmpresa.getDireccion().getValida() != 0);
        registroEmpresa.setEmpresa(this.empresaService.buscarEmpresa(id));

        return registroEmpresa;
    }

    @GetMapping("/{id}/editar")
    public String doEditarEmpresa(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("registroEmpresa", recuperarInfoEmpresa(id));
        return "editarEmpresa";
    }

    @PostMapping("/{id}/guardar")
    public String doGuardarEmpresa(@PathVariable("id") Integer id,
                                   @ModelAttribute("registroEmpresa") RegistroEmpresa edicionEmpresa) {
        RegistroEmpresa registroEmpresa = recuperarInfoEmpresa(id);

        this.empresaService.guardarEmpresa(edicionEmpresa.getEmpresa(), id);
        this.direccionService.guardarDireccion(edicionEmpresa.getDireccion(),
                edicionEmpresa.getDireccion().getId(), edicionEmpresa.getValida());
        this.usuarioService.guardarUsuario(edicionEmpresa.getUsuario(), edicionEmpresa.getEmpresa().getCif(),
                registroEmpresa.getRcontrasena());

        return "redirect:/empresa/".concat(String.valueOf(id)).concat("/persona");
    }

    private RegistroEmpresaPersona recuperarInfoEmpresaPersona(int id) {
        RegistroEmpresaPersona registroEmpresaPersona = new RegistroEmpresaPersona();

        registroEmpresaPersona.setDireccion(this.direccionService.buscarPorCliente(id));
        registroEmpresaPersona.setEmpresaPersona(this.empresaPersonaService.buscarPorPersona(id));
        registroEmpresaPersona.setPersona(this.personaService.buscarPersona(id));
        registroEmpresaPersona.setUsuario(this.usuarioService.buscarUsuario(id));
        registroEmpresaPersona.setValida(registroEmpresaPersona.getDireccion().getValida() != 0);

        return registroEmpresaPersona;
    }

    @GetMapping("/{id}/persona/{personaId}/editar")
    public String doEditarEmpresaPersona(@PathVariable("id") Integer id, @PathVariable("personaId") Integer personaId,
                                         Model model) {
        List<TipoPersonaRelacionadaDTO> tipoPersonaRelacionada =
                this.tipoPersonaRelacionadaService.listarTipoPersonasRelacionada();

        model.addAttribute("registroEmpresaPersona", recuperarInfoEmpresaPersona(personaId));
        model.addAttribute("tipoPersonasRelacionadas", tipoPersonaRelacionada);

        return "editarEmpresaPersona";
    }

    @PostMapping("/{id}/persona/{personaId}/guardar")
    public String doGuardarEmpresaPersona(@PathVariable("id") Integer id, @PathVariable Integer personaId,
                                          @ModelAttribute("registroEmpresaPersona") RegistroEmpresaPersona edicionEmpresaPersona) {
        RegistroEmpresaPersona registroEmpresaPersona = recuperarInfoEmpresaPersona(personaId);

        this.direccionService.guardarDireccion(edicionEmpresaPersona.getDireccion(),
                edicionEmpresaPersona.getDireccion().getId(), edicionEmpresaPersona.getValida());
        this.empresaPersonaService.guardarEmpresaPersona(registroEmpresaPersona.getEmpresaPersona(),
                registroEmpresaPersona.getEmpresaPersona().getEmpresa(),
                registroEmpresaPersona.getEmpresaPersona().getPersona());
        this.personaService.guardarPersona(edicionEmpresaPersona.getPersona());
        this.usuarioService.guardarUsuario(edicionEmpresaPersona.getUsuario(),
                registroEmpresaPersona.getEmpresaPersona().getEmpresaCif(),
                registroEmpresaPersona.getRcontrasena());

        return "redirect:/empresa/".concat(String.valueOf(id)).concat("/persona");
    }

    private String procesarFiltrado(Integer empresaId, Integer personaId, FiltroEmpresaPersona filtro, Model model) {
        List<Object[]> personas;
        String urlTo = "listadoEmpresaPersonas";

        if (filtro == null) {
            personas = this.personaService.buscarPersonasDistintasPorEmpresa(empresaId, personaId);
            filtro = new FiltroEmpresaPersona();
        } else {
            personas = this.personaService.filtrar(empresaId, personaId, filtro);
        }

        List<TipoPersonaRelacionadaDTO> tipoPersonaRelacionada =
                this.tipoPersonaRelacionadaService.listarTipoPersonasRelacionada();

        model.addAttribute("personas", personas);
        model.addAttribute("filtro", filtro);
        model.addAttribute("tipoPersonaRelacionada", tipoPersonaRelacionada);

        return urlTo;
    }

    @GetMapping("{id}/persona/{personaId}/listar")
    public String doListarEmpresaPersonas(@PathVariable("id") Integer id, @PathVariable("personaId") Integer personaId,
                                          Model model) {
        return this.procesarFiltrado(id, personaId, null, model);
    }

    @PostMapping("{id}/persona/{personaId}/filtrar")
    public String doFiltrarEmpresaPersona(@PathVariable("id") Integer id, @PathVariable("personaId") Integer personaId,
                                          @ModelAttribute("filtro") FiltroEmpresaPersona filtro, Model model) {
        return this.procesarFiltrado(id, personaId, filtro, model);
    }

    @GetMapping("{id}/persona/{personaId}/permiso/{seleccionadoId}")
    public String doPermisoEmpresaPersona(@PathVariable("id") Integer id, @PathVariable("personaId") Integer personaId,
                                          @PathVariable("seleccionadoId") Integer seleccionadoId, @ModelAttribute(
            "filtro") FiltroEmpresaPersona filtro, Model model) {
        EmpresaClienteDTO empresaCliente = this.empresaClienteService.buscarTipoPorPersona(seleccionadoId);

        this.empresaClienteService.guardarEmpresaCliente(empresaCliente, empresaCliente.getTipoClienteRelacionado(),
                empresaCliente.getEmpresa(), empresaCliente.getPersona());

        model.addAttribute("empresaId", id);
        model.addAttribute("personaId", personaId);

        return this.procesarFiltrado(id, personaId, filtro, model);
    }

    @GetMapping("{id}/persona/{personaId}/transferencia")
    public String doTransferenciaEmpresa(@PathVariable("id") Integer id, @PathVariable("personaId") Integer personaId,
                                         Model model) {
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setCuentaOrigen(this.cuentaService.cuentasPorCliente(id).get(0).getId());
        List<CuentaDTO> cuentas =
                this.cuentaService.cuentasSinMi(transaccion.getCuentaOrigen());

        model.addAttribute("cuentas", cuentas);
        model.addAttribute("transaccion", transaccion);
        model.addAttribute("empresaId", id);
        model.addAttribute("personaId", personaId);

        return "transferenciaEmpresa";
    }

    @PostMapping("{id}/persona/{personaId}/transferencia/realizar")
    public String doRealizarTransferenciaEmpresa(@PathVariable("id") Integer id,
                                                 @PathVariable("personaId") Integer personaId, @ModelAttribute(
            "transaccion") TransaccionDTO transaccion) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaccion.setFechaEjecucion(timestamp);
        transaccion.setFechaInstruccion(timestamp);

        CuentaDTO origen =
                this.cuentaService.buscarCuenta(transaccion.getCuentaOrigen());
        CuentaDTO destino =
                this.cuentaService.buscarCuenta(transaccion.getCuentaDestino());

        if (origen != null && destino != null) {
            OperacionDTO operacion = new OperacionDTO();

            origen.setSaldo(origen.getSaldo() - transaccion.getCantidad());
            destino.setSaldo(destino.getSaldo() + transaccion.getCantidad());
            operacion.setPersona(this.personaService.buscarPersona(personaId).getId());
            operacion.setTransaccion(transaccion.getId());

            this.transaccionService.guardarTransaccion(transaccion);
            this.cuentaService.guardarCuenta(origen);
            this.cuentaService.guardarCuenta(destino);
            this.operacionService.guardarOperacion(operacion);
        }

        return "redirect:/empresa/".concat(String.valueOf(id)).concat("/persona");
    }

    @GetMapping("{id}/cuentas")
    public String doCuentas(@PathVariable("id") Integer id, Model model) {
        List<CuentaDTO> cuentas = this.cuentaService.cuentasPorCliente(id);

        model.addAttribute("cuentas", cuentas);

        return "estadoCuentaEmpresa";
    }

    @GetMapping("{id}/cuenta/{cuentaId}/permiso/{seleccionadoId}")
    public String doPermisoCuentas(@PathVariable("id") Integer id, @PathVariable("cuentaId") Integer cuentaId,
                                   @PathVariable("seleccionadoId") Integer seleccionadoId,
                                   @ModelAttribute("filtro") FiltroEmpresaPersona filtro, Model model) {
        CuentaDTO cuentaBanco = this.cuentaService.buscarCuenta(seleccionadoId);

        if (cuentaBanco.getEstado().equals(1)) {
            cuentaBanco.setEstado(4);
        } else if (cuentaBanco.getEstado().equals(2)) {
            cuentaBanco.setEstado(5);
        }

        this.cuentaService.guardarCuenta(cuentaBanco);

        model.addAttribute("empresaId", id);
        model.addAttribute("cuentaId", cuentaId);

        return "redirect:/empresa/".concat(String.valueOf(id)).concat("/cuentas");
    }

    private String procesarFiltradoOperaciones(Integer empresaId, FiltroOperacionesEmpresa filtro, Model model) {
        List<OperacionDTO> ops = null;
        List<OperacionDTO> operaciones = this.operacionService.buscarOperacionesPorCuentasYEmpresa(empresaId);
        List<EmpresaDTO> empresas = this.empresaService.listarEmpresas();
        List<PersonaDTO> personas = this.personaService.listarPersonas();
        String urlTo = "listadoOperacionesEmpresa";

        if (filtro == null) {
            filtro = new FiltroOperacionesEmpresa();
        } else {
            ops = this.operacionService.filtrar(filtro);
        }

        if (ops != null) {
            operaciones.retainAll(ops);
        }

        model.addAttribute("operaciones", operaciones);
        model.addAttribute("filtro", filtro);
        model.addAttribute("cuentas", this.cuentaService.listarTodasCuentasTransaccion(empresaId));
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
    public String doCambioDivisa(@PathVariable("id") Integer id, Model model) {
        CuentaDTO cuenta = this.cuentaService.cuentasPorCliente(id).get(0);
        List<DivisaDTO> divisas = this.divisaService.listarDivisasSinMi(cuenta.getDivisa());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        List<String> cambios =
                divisas.stream().map(d -> decimalFormat.format(cuenta.getSaldo() * cuenta.getDivisaEquivalencia() / d.getEquivalencia()) + " " + d.getNombre()).collect(Collectors.toList());

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("divisas", divisas);
        model.addAttribute("cambios", cambios);
        model.addAttribute("cuentaId", cuenta.getId());

        return "cambioDivisaEmpresa";
    }

    @PostMapping("{id}/cuenta/{cuentaId}/cambioDivisa/realizar")
    public String doCambioDivisaRealizar(@PathVariable("id") Integer id, @PathVariable("cuentaId") Integer cuentaId,
                                         @ModelAttribute("divisaSelect") Integer divisaId) {
        DivisaDTO divisa = this.divisaService.buscarDivisa(divisaId);
        CuentaDTO cuenta = this.cuentaService.buscarCuenta(cuentaId);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        cuenta.setSaldo(Double.parseDouble(decimalFormat.format((cuenta.getDivisaEquivalencia() * cuenta.getSaldo()) / divisa.getEquivalencia()).replace(",", ".")));
        cuenta.setDivisa(divisa.getId());
        this.cuentaService.guardarCuenta(cuenta);

        return "redirect:/empresa/".concat(String.valueOf(id)).concat("/persona");
    }
}
