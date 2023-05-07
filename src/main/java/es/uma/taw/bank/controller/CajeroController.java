package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.dto.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.service.*;
import es.uma.taw.bank.ui.Cambio;
import es.uma.taw.bank.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.TransformService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cajero")
public class CajeroController {
    @Autowired
    protected ClienteService clienteService;
    @Autowired
    protected TransaccionService transaccionService;
    @Autowired
    protected PersonaService personaService;
    @Autowired
    protected CuentaService cuentaService;
    @Autowired
    protected DivisaService divisaService;
    @GetMapping("/listar")
    public String doListar(Model model, @RequestParam("cliente") Integer idCliente ){
        ClienteDTO cliente = clienteService.buscarCliente(idCliente);
        PersonaDTO persona = personaService.buscarPersona(idCliente);
        List<CuentaDTO> cuentas = cuentaService.cuentasPorCliente(idCliente);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("cliente",cliente);
        model.addAttribute("persona",persona);
        return "cajero";
    }
    @GetMapping("/cuenta")
    public String doCuentaCajero(Model model, @RequestParam("cuenta") Integer idCuenta){
        CuentaDTO cuenta = cuentaService.buscarCuenta(idCuenta);
        model.addAttribute("cuenta",cuenta);

        return "cuenta";
    }
    @GetMapping("/datos")
    public String doDatos(Model model, @RequestParam("cliente") Integer idCliente){
        PersonaDTO persona = personaService.buscarPersona(idCliente);
        model.addAttribute("persona",persona);

        return "datos";
    }
    @PostMapping("/editar")
    public String editar(Model model, @ModelAttribute("persona") PersonaDTO persona){
        personaService.guardarPersona(persona, persona.getId());
        return "redirect:/cajero/listar?cliente="+persona.getId();
    }
    @GetMapping("/transferencia")
    public String transferencia(Model model, @RequestParam("cuenta") Integer idCuenta){
        TransaccionDTO t = transaccionService.iniciarTransaccion(idCuenta);
        List<CuentaDTO> cuentas = cuentaService.cuentasSinMi(idCuenta);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("transaccion",t);
        return "transferencia";
    }

    @PostMapping("/transferir")
    public String transferir(Model model, @ModelAttribute("transaccion") TransaccionDTO transaccion){
        transaccionService.guardarTransaccion(transaccion);
        CuentaDTO origen = cuentaService.buscarCuenta(transaccion.getCuentaOrigen());
        CuentaDTO destino = cuentaService.buscarCuenta(transaccion.getCuentaDestino());
        Double equivalencia = origen.getDivisaEquivalencia()/destino.getDivisaEquivalencia();
        cuentaService.sumarSaldo(transaccion.getCuentaOrigen(), -(transaccion.getCantidad()));
        cuentaService.sumarSaldo(transaccion.getCuentaDestino(), transaccion.getCantidad()*equivalencia);
        return "redirect:/cajero/cuenta?cuenta="+transaccion.getCuentaOrigen();
    }

    @GetMapping("/retirada")
    public String retirada(Model model, @RequestParam("cuenta") Integer idCuenta){
        CuentaDTO c = cuentaService.buscarCuenta(idCuenta);
        model.addAttribute("cuenta",c);

        return "retirada";
    }
    @PostMapping("/retirar")
    public String retirar(Model model, @RequestParam("cuenta") Integer idCuenta, @RequestParam("cantidad") Double cantidad){
        CuentaDTO c = cuentaService.buscarCuenta(idCuenta);
        cuentaService.sumarSaldo(idCuenta,-cantidad);
        return "redirect:/cajero/cuenta?cuenta="+idCuenta;
    }
    @GetMapping("/operaciones")
    public String operaciones(Model model, @RequestParam("cuenta") Integer idCuenta){
        return procesarFiltrado(model, idCuenta, null);
    }
    @PostMapping("/operaciones/filtrar")
    public String filtrarOperaciones(Model model,@RequestParam("cuenta") Integer idCuenta, @ModelAttribute("filtro") FiltroOperaciones filtro){
        return procesarFiltrado(model,idCuenta,filtro);
    }
    public String procesarFiltrado(Model model, Integer idCuenta, FiltroOperaciones filtro){
        List<TransaccionDTO> operaciones = transaccionService.listarTransaccionesPorCuenta(idCuenta);
        List<CuentaDTO> cuentas = transaccionService.rellenarCuentas(idCuenta,operaciones);
        if(filtro==null){
            filtro=new FiltroOperaciones();
        }
        else{
            operaciones = this.transaccionService.filtrar(idCuenta,filtro);
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("idCuenta",idCuenta);
        model.addAttribute("operaciones",operaciones);
        model.addAttribute("iban",cuentaService.buscarCuenta(idCuenta).getIbanCuenta());
        return "operaciones";
    }
    @GetMapping("/cambioDivisa")
    public String cambioDivisa(Model model, @RequestParam("cuenta") Integer idCuenta){
        CuentaDTO c = cuentaService.buscarCuenta(idCuenta);
        DivisaDTO divisaInicial = divisaService.buscarDivisaPorNombre(c.getDivisaNombre());
        List<DivisaDTO> divisas = divisaService.listarDivisas();
        divisas.removeIf(d -> (d.getNombre().equals(divisaInicial.getNombre())));
        List<Cambio> lista = new ArrayList<>();
        Double enDolares = divisaInicial.getEquivalencia();
        for(DivisaDTO d:divisas){
            Cambio cambio = new Cambio(d.getNombre(),enDolares/d.getEquivalencia());
            lista.add(cambio);
        }
        model.addAttribute("cuenta",c);
        model.addAttribute("divisas",divisas);
        model.addAttribute("cambios",lista);
        return "cambioDivisa";
    }
    @PostMapping("/cambiarA")
    public String cambiarA(Model model, @RequestParam("cuenta") Integer idCuenta, @RequestParam("moneda") String monedaDestino){
        CuentaDTO c = cuentaService.buscarCuenta(idCuenta);
        DivisaDTO origen = divisaService.buscarDivisaPorNombre(c.getDivisaNombre());
        DivisaDTO destino = divisaService.buscarDivisaPorNombre(monedaDestino);
        Double equivalencia = origen.getEquivalencia()/destino.getEquivalencia();
        model.addAttribute("cuenta",c);
        model.addAttribute("origen",c.getDivisaNombre());
        model.addAttribute("destino",monedaDestino);
        model.addAttribute("equivalencia",equivalencia);
        return "cambiarA";
    }
    @PostMapping("/retirarCambio")
    public String retirarCambio(Model model, @RequestParam("cuenta") Integer idCuenta, @RequestParam("cantidad") Double cantidad){
        cuentaService.sumarSaldo(idCuenta,-cantidad);
        return "redirect:/cajero/cuenta?cuenta="+idCuenta;
    }
    @GetMapping("desbloquear")
    public String solicitarDesbloqueo(Model model, @RequestParam("cuenta") Integer idCuenta){
        //Solicitar desbloqueo al gestor
        cuentaService.pedirDesbloqueo(idCuenta);
        return "redirect:/cajero/cuenta?cuenta="+idCuenta;
    }
}
