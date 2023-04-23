package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
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
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected PersonaRepository personaRepository;
    @Autowired
    protected TransaccionRepository transaccionRepository;
    @Autowired
    protected DivisaRepository divisaRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;
    @GetMapping("/listar")
    public String doListar(Model model, @RequestParam("cliente") Integer idCliente ){
        ClienteEntity cliente = clienteRepository.findById(idCliente).get();
        PersonaEntity p = personaRepository.findById(idCliente).get();
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarPorCliente(idCliente);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("cliente",cliente);
        model.addAttribute("persona",p);
        return "cajero";
    }
    @GetMapping("/cuenta")
    public String doCuentaCajero(Model model, @RequestParam("cuenta") Integer idCuenta){
        CuentaBancoEntity cuenta = cuentaRepository.findById(idCuenta).get();
        model.addAttribute("cuenta",cuenta);

        return "cuenta";
    }
    @GetMapping("/datos")
    public String doDatos(Model model, @RequestParam("cliente") Integer idCliente){
        PersonaEntity persona = personaRepository.findById(idCliente).get();
        model.addAttribute("persona",persona);

        return "datos";
    }
    @PostMapping("/editar")
    public String editar(Model model, @ModelAttribute("persona") PersonaEntity persona){
        personaRepository.save(persona);
        return "redirect:/cajero/listar?cliente="+persona.getId();
    }
    @GetMapping("/transferencia")
    public String transferencia(Model model, @RequestParam("cuenta") Integer idCuenta){
        TransaccionEntity t = new TransaccionEntity();
        t.setCuentaBancoByCuentaOrigen(cuentaRepository.findById(idCuenta).get());
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarSinMi(idCuenta);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("transaccion",t);
        return "transferencia";
    }

    @PostMapping("/transferir")
    public String transferir(Model model, @ModelAttribute("transaccion") TransaccionEntity transaccion){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaccion.setFechaEjecucion(timestamp);
        transaccion.setFechaInstruccion(timestamp);
        transaccionRepository.save(transaccion);


        CuentaBancoEntity origen=  cuentaRepository.findById(transaccion.getCuentaBancoByCuentaOrigen().getId()).get();
        CuentaBancoEntity destino=  cuentaRepository.findById(transaccion.getCuentaBancoByCuentaDestino().getId()).get();

        origen.setSaldo(origen.getSaldo()-transaccion.getCantidad());
        destino.setSaldo(destino.getSaldo()+transaccion.getCantidad());

        cuentaRepository.save(origen);
        cuentaRepository.save(destino);
        return "redirect:/cajero/cuenta?cuenta="+origen.getId();
    }

    @GetMapping("/retirada")
    public String retirada(Model model, @RequestParam("cuenta") Integer idCuenta){
        CuentaBancoEntity c = cuentaRepository.findById(idCuenta).get();
        model.addAttribute("cuenta",c);

        return "retirada";
    }
    @PostMapping("/retirar")
    public String retirar(Model model, @RequestParam("cuenta") Integer idCuenta, @RequestParam("cantidad") Double cantidad){
        CuentaBancoEntity c = cuentaRepository.findById(idCuenta).get();
        c.setSaldo(c.getSaldo()-cantidad);
        cuentaRepository.save(c);
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
        List<TransaccionEntity> operaciones = transaccionRepository.operacionesPorCuenta(idCuenta);
        List<CuentaBancoEntity> cuentas = new ArrayList<>();
        for(TransaccionEntity t: operaciones){
            if(!cuentas.contains(t.getCuentaBancoByCuentaDestino()))if(t.getCuentaBancoByCuentaDestino().getId()!=idCuenta)cuentas.add(t.getCuentaBancoByCuentaDestino());
            if(!cuentas.contains(t.getCuentaBancoByCuentaOrigen()))if(t.getCuentaBancoByCuentaOrigen().getId()!=idCuenta)cuentas.add(t.getCuentaBancoByCuentaOrigen());
        }
        if(filtro==null){
            filtro=new FiltroOperaciones();
        }
        else if(filtro.getCantidad() && filtro.getFechaEjecucion() && filtro.getFechaInstruccion() && filtro.getCuentaFiltro()!=null && filtro.getCuentaFiltro()!=""){
            operaciones=transaccionRepository.todoFiltrado(idCuenta,filtro.getCuentaFiltro());
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("idCuenta",idCuenta);
        model.addAttribute("operaciones",operaciones);
        return "operaciones";
    }
    @GetMapping("/cambioDivisa")
    public String cambioDivisa(Model model, @RequestParam("cuenta") Integer idCuenta){
        CuentaBancoEntity c = cuentaRepository.findById(idCuenta).get();
        DivisaEntity divisaInicial = divisaRepository.buscarPorNombre(c.getMoneda());
        List<DivisaEntity> divisas = divisaRepository.findAll();
        divisas.removeIf(d -> (d.getNombre().equals(divisaInicial.getNombre())));
        List<Cambio> lista = new ArrayList<>();
        Double enDolares = divisaInicial.getEquivalencia();
        for(DivisaEntity d:divisas){
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
        CuentaBancoEntity c = cuentaRepository.findById(idCuenta).get();
        DivisaEntity origen = divisaRepository.buscarPorNombre(c.getMoneda());
        DivisaEntity destino = divisaRepository.buscarPorNombre(monedaDestino);
        Double equivalencia = origen.getEquivalencia()/destino.getEquivalencia();
        model.addAttribute("cuenta",c);
        model.addAttribute("origen",c.getMoneda());
        model.addAttribute("destino",monedaDestino);
        model.addAttribute("equivalencia",equivalencia);
        return "cambiarA";
    }
    @PostMapping("/retirarCambio")
    public String retirarCambio(Model model, @RequestParam("cuenta") Integer idCuenta, @RequestParam("cantidad") Double cantidad){
        CuentaBancoEntity c = cuentaRepository.findById(idCuenta).get();
        c.setSaldo(c.getSaldo()-cantidad);
        cuentaRepository.save(c);
        return "redirect:/cajero/cuenta?cuenta="+idCuenta;
    }
    @GetMapping("desbloquear")
    public String solicitarDesbloqueo(Model model, @RequestParam("cuenta") Integer idCuenta){
        //Solicitar desbloqueo al gestor
        CuentaBancoEntity c = cuentaRepository.findById(idCuenta).get();
        EstadoCuentaEntity activado = estadoCuentaRepository.findById(1).get();
        c.setEstadoCuentaByEstadoCuentaId(activado);
        cuentaRepository.save(c);
        return "redirect:/cajero/cuenta?cuenta="+idCuenta;
    }
}
