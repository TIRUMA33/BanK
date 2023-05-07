package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.dto.*;
import es.uma.taw.bank.DTO.*;
import es.uma.taw.bank.service.*;
import es.uma.taw.bank.ui.FiltroOperacionesEmpresa;
import es.uma.taw.bank.ui.FiltroOperacionesPersona;
import es.uma.taw.bank.ui.RegistroEmpresa;
import es.uma.taw.bank.ui.RegistroPersona;
import jakarta.persistence.criteria.CriteriaBuilder;
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
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    DireccionService direccionService;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    DivisaService divisaService;

    @GetMapping("/")
    public String doPersona(Model model, HttpSession session){
        String urlTo;
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        if (usuario == null) {
            urlTo = "iniciarSesion";
        } else {
            PersonaDTO persona = personaService.buscarPersonaPorDni(usuario.getNif());
            model.addAttribute("cuenta", cuentaService.cuentasPorCliente(persona.getId()).get(0));
            model.addAttribute("persona", persona);
            urlTo = "inicioPersona";
        }
        return urlTo;
    }

    private RegistroPersona recuperarInfoPersona(int id) {
        RegistroPersona registroPersona = new RegistroPersona();

        registroPersona.setDireccion(this.direccionService.buscarPorCliente(id));
        registroPersona.setUsuario(this.usuarioService.buscarUsuario(id));
        registroPersona.setValida(registroPersona.getDireccion().getValida() != 0);
        registroPersona.setPersona(this.personaService.buscarPersona(id));

        return registroPersona;
    }

    @GetMapping("/editar")
    public String doEditar(@RequestParam("id") Integer idpersona, Model model){
        model.addAttribute("registroPersona", recuperarInfoPersona(idpersona));
        return "editarPersona";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("registroPersona") RegistroPersona edicionPersona){
        RegistroPersona registroPersona = recuperarInfoPersona(edicionPersona.getPersona().getId());
        PersonaDTO personaActualizada = registroPersona.getPersona();
        PersonaDTO personaForm = edicionPersona.getPersona();
        DireccionDTO direccionActualizada = registroPersona.getDireccion();
        DireccionDTO direccionForm = edicionPersona.getDireccion();
        UsuarioDTO usuarioActualizado = registroPersona.getUsuario();
        UsuarioDTO usuarioForm = edicionPersona.getUsuario();

        personaActualizada.setDni(personaForm.getDni());
        personaActualizada.setNombre(personaForm.getNombre());
        personaActualizada.setApellido1(personaForm.getApellido1());
        personaActualizada.setApellido2(personaForm.getApellido2());
        personaActualizada.setFechaNacimiento(personaForm.getFechaNacimiento());
        personaService.guardarPersona(personaActualizada);

        direccionActualizada.setCalle(direccionForm.getCalle());
        direccionActualizada.setNumero(direccionForm.getNumero());
        direccionActualizada.setPlantaPuertaOficina(direccionForm.getPlantaPuertaOficina());
        direccionActualizada.setCiudad(direccionForm.getCiudad());
        direccionActualizada.setRegion(direccionForm.getRegion());
        direccionActualizada.setCodigoPostal(direccionForm.getCodigoPostal());
        direccionActualizada.setPais(direccionForm.getPais());
        direccionActualizada.setValida((byte) (edicionPersona.getValida() ? 1 : 0));
        direccionService.guardarDireccion(direccionActualizada);

        usuarioActualizado.setNif(personaForm.getDni());
        if (!(usuarioForm.getContrasena().isBlank() || registroPersona.getRcontrasena().isBlank())) {
            if(usuarioForm.getContrasena().equals(registroPersona.getRcontrasena())) {
                usuarioActualizado.setContrasena(usuarioForm.getContrasena());
            }
        }
        usuarioService.guardarUsuario(usuarioActualizado);
        return "redirect:/persona/";
    }

    @GetMapping("/transferencia")
    public String doTransferencia(@RequestParam("id") Integer idpersona, Model model){

        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setCuentaOrigen(this.cuentaService.buscarCuenta(idpersona).getId());
        List<CuentaDTO> cuentas = this.cuentaService.buscarSinMi(transaccion.getCuentaBancoByCuentaOrigen().getId());

        model.addAttribute("cuentas", cuentas);
        model.addAttribute("transaccion", transaccion);
        return "transferenciaPersona";
    }

    @PostMapping("/transferencia/realizar")
    public String doRealizar(@ModelAttribute("transaccion") TransaccionDTO transaccion) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaccion.setFechaEjecucion(timestamp);
        transaccion.setFechaInstruccion(timestamp);

        CuentaBancoDTO origen =
                this.cuentaService.findById(transaccion.getCuentaBancoByCuentaOrigen().getId()).orElse(null);
        CuentaBancoDTO destino =
                this.cuentaService.findById(transaccion.getCuentaBancoByCuentaDestino().getId()).orElse(null);

        if(origen != null && destino != null) {
            origen.setSaldo(origen.getSaldo() - transaccion.getCantidad());
            destino.setSaldo(destino.getSaldo() + transaccion.getCantidad());

            this.transaccionService.save(transaccion);
            this.cuentaService.save(origen);
            this.cuentaService.save(destino);
        }

        return "redirect:/persona/";
    }

    @GetMapping("/solicitar")
    public String doSolicitado(@ModelAttribute("id") Integer cuentaid) {
        EstadoCuentaDTO estado = new EstadoCuentaDTO();
        CuentaBancoDTO cuenta = cuentaService.findById(cuentaid).orElse(null);
        if (cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 1) {
            estado.setId(4);
        } else if(cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 2) {
            estado.setId(5);
        }
        cuenta.setEstadoCuentaByEstadoCuentaId(estado);
        this.cuentaService.save(cuenta);
        return "redirect:/persona/";
    }

    @GetMapping("/cambioDivisa")
    public String doCambio(@RequestParam("id") Integer cuentaid, Model model) {
        CuentaBancoDTO cuenta = cuentaService.findById(cuentaid).orElse(null);
        List<DivisaDTO> divisas = this.divisaService.buscarSinMi(cuenta.getDivisaByDivisaId().getId());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        List<String> cambios = divisas.stream().map(d->decimalFormat.format(cuenta.getSaldo() * cuenta.getDivisaByDivisaId().getEquivalencia() / d.getEquivalencia()) + " " + d.getNombre()).collect(Collectors.toList());

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("divisas", divisas);
        model.addAttribute("cambios", cambios);
        return "cambioDivisaPersona";
    }

    @PostMapping("/cambioDivisa/{cuentaId}/realizar")
    public String doCambioRealizado(@PathVariable("cuentaId") String cuentaid, @ModelAttribute("divisaSelect") Integer divisaid) {
        DivisaDTO divisa = this.divisaService.findById(divisaid).orElse(null);
        CuentaDTO cuenta = this.cuentaService.findById(Integer.parseInt(cuentaid)).orElse(null);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        cuenta.setSaldo(Double.parseDouble(decimalFormat.format(cuenta.getSaldo() * cuenta.getDivisaByDivisaId().getEquivalencia() / divisa.getEquivalencia()).replace(",", ".")));
        cuenta.setDivisaByDivisaId(divisa);
        this.cuentaService.save(cuenta);
        return "redirect:/persona/";
    }

    @GetMapping("/operaciones")
    public String doOperaciones(@RequestParam("id") Integer cuentaId,
                                Model model) {
        return this.procesarFiltradoOperaciones(cuentaId, null, model);
    }

    @PostMapping("/operaciones/{cuentaid}/filtrar")
    public String doFiltrarOperaciones(@PathVariable("cuentaid") Integer personaId,
                                       @ModelAttribute("filtro") FiltroOperacionesPersona filtro, Model model) {
        return this.procesarFiltradoOperaciones(personaId, filtro, model);
    }

    private String procesarFiltradoOperaciones(Integer cuentaid, FiltroOperacionesPersona filtro,Model model) {

        List<TransaccionDTO> operaciones = null;
        String urlTo = "operacionesPersona";

        if (filtro == null || filtro.getIban()=="" && !filtro.getFecha() && !filtro.getCantidad()) {
            operaciones = this.transaccionService.buscarporCuenta(cuentaid);
            filtro = new FiltroOperacionesPersona();
        }else if (filtro.getIban()=="" && !filtro.getFecha()) {
            operaciones = transaccionService.buscaryordporCuentaYCantidad(cuentaid);
        } else if (filtro.getIban()=="" && !filtro.getCantidad()) {
            operaciones = transaccionService.buscaryordporCuentaYFecha(cuentaid);
        } else if (!filtro.getFecha() && !filtro.getCantidad()){
            operaciones = transaccionService.buscarpordoblecuenta(cuentaid, filtro.getIban());
        } else if(!filtro.getFecha()) {
            operaciones = transaccionService.buscarporDobleCuentayCantidad(cuentaid, filtro.getIban());
        } else if (!filtro.getCantidad()) {
            operaciones = transaccionService.buscarporDobleCuentayFecha(cuentaid, filtro.getIban());
        } else if (filtro.getIban()==""){
            operaciones = transaccionService.buscaryordporCuentaFechaYCantidad(cuentaid);
        }else {
            operaciones = transaccionService.buscarporDobleCuentaFechaYCantidad(cuentaid, filtro.getIban());
        }
        model.addAttribute("operaciones", operaciones);
        model.addAttribute("filtro", filtro);
        model.addAttribute("cuentaid", cuentaid);
        return urlTo;
    }



}
