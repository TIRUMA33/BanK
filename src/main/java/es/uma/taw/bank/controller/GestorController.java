package es.uma.taw.bank.controller;

import es.uma.taw.bank.DataGenerator;
import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.FiltroOperaciones;
import es.uma.taw.bank.ui.FiltroOperacionesEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.uma.taw.bank.ui.FiltroCliente;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {
@Autowired
protected ClienteRepository clienteRepository;

@Autowired
protected EmpresaRepository empresaRepository;

@Autowired
protected PersonaRepository personaRepository;
@Autowired
protected TransaccionRepository transaccionRepository;
@Autowired
protected CuentaRepository cuentaRepository;
@Autowired
protected EntidadBancariaRepository entidadBancariaRepository;
@Autowired
protected EstadoCuentaRepository estadoCuentaRepository;
@Autowired
protected DivisaRepository divisaRepository;
@Autowired
protected EstadoClienteRepository estadoClienteRepository;
@Autowired
protected CuentasSospechosas cuentasSospechosas;

@GetMapping("/")
public String doInicioGestor(){
    return "gestor";
}


@GetMapping("/clientespendientes")
public String doPendientes(Model model) {
    List<ClienteEntity> listaPendientes = this.clienteRepository.listaPendientes();
    List<EmpresaEntity> listaEmpresa = this.empresaRepository.findAll();
    List<PersonaEntity> listaPersonas = this.personaRepository.findAll();
    List<EmpresaEntity> EmpresasPendientes = new ArrayList<EmpresaEntity>();
    List<PersonaEntity> PersonasPendientes = new ArrayList<PersonaEntity>();


    for (ClienteEntity cliente : listaPendientes) {
            for (EmpresaEntity empresa : listaEmpresa) {
                if (cliente.getId() == empresa.getId()) {
                    EmpresasPendientes.add(empresa);
                }
            }
            for (PersonaEntity persona : listaPersonas) {
                if (cliente.getId() == persona.getId()) {
                    PersonasPendientes.add(persona);
                    int paco = 0;
                }
            }
        }
    model.addAttribute("EmpresasPendientes",EmpresasPendientes);
    model.addAttribute("PersonasPendientes",PersonasPendientes);
    return "clientespendientes";
    }

    @GetMapping("/lista")
    public String doListarTodo(Model model) {
        return procesarFiltrado(null,model);
    }

    @PostMapping("/lista/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroCliente filtro, Model model){
        return this.procesarFiltrado(filtro, model);
    }

    protected String procesarFiltrado (FiltroCliente filtro, Model model) {
        List<ClienteEntity> listaclientes = this.clienteRepository.findAll();
        List<EmpresaEntity> listaEmpresa = this.empresaRepository.findAll();
        List<PersonaEntity> listaPersonas = this.personaRepository.findAll();
        List<CuentaSospechosaEntity> CuentasSospechosas = this.cuentasSospechosas.findAll();
        List<EmpresaEntity> listaEmpresaaux = new ArrayList<>();
        List<PersonaEntity> listaPersonasaux = new ArrayList<>();

        if (filtro == null || (filtro.getEstado() == false && filtro.getNombre() == false && filtro.getTexto().isEmpty())) {
            listaclientes = this.clienteRepository.findAll();
            filtro = new FiltroCliente();
        } else if (filtro.getNombre() == true && filtro.getEstado() == false && filtro.getTexto().isEmpty()) {
            listaEmpresa = this.clienteRepository.ordenadopornombreempresa();
            listaPersonas = this.clienteRepository.ordenadopornombrepersona();
        } else if (filtro.getNombre() == true && filtro.getEstado() == true && filtro.getTexto().isEmpty()) {
            listaclientes = this.clienteRepository.ordenadoporestado();
            listaEmpresa = this.clienteRepository.ordenadopornombreempresa();
            listaPersonas = this.clienteRepository.ordenadopornombrepersona();
            for (ClienteEntity c: listaclientes) { //lista clientes ordenada por estado
                for (PersonaEntity p:listaPersonas) { //lista personas desordenada
                    if(p.getId() == c.getId()){
                        listaPersonasaux.add(p);
                    }
                }
                for (EmpresaEntity e:listaEmpresa) {
                    if(e.getId() == c.getId()){
                        listaEmpresaaux.add(e);
                    }
                }
            }
            listaPersonas = listaPersonasaux;
            listaEmpresa = listaEmpresaaux;
        } else if (filtro.getEstado() == true && filtro.getNombre() == false && !filtro.getTexto().isEmpty()) {
            listaclientes = this.clienteRepository.ordenadoporestado();
            listaEmpresa = this.clienteRepository.buscarPorNombreEmpresa(filtro.getTexto());
            listaPersonas = this.clienteRepository.buscarPorNombrePersona(filtro.getTexto());

            //ordenar la lista de personas y de empresas en funcion a la de clientes
            for (ClienteEntity c: listaclientes) { //lista clientes ordenada por estado
                for (PersonaEntity p:listaPersonas) { //lista personas desordenada
                    if(p.getId() == c.getId()){
                        listaPersonasaux.add(p);
                    }
                }
                for (EmpresaEntity e:listaEmpresa) {
                    if(e.getId() == c.getId()){
                        listaEmpresaaux.add(e);
                    }
                }
            }
            listaPersonas = listaPersonasaux;
            listaEmpresa = listaEmpresaaux;
        } else if (filtro.getNombre() == false && filtro.getEstado() == false && !filtro.getTexto().isEmpty()) {
            listaEmpresa = this.clienteRepository.buscarPorNombreEmpresa(filtro.getTexto());
            listaPersonas = this.clienteRepository.buscarPorNombrePersona(filtro.getTexto());
        } else if (filtro.getNombre() == true && filtro.getEstado() == false && !filtro.getTexto().isEmpty()) {
            listaEmpresa = this.clienteRepository.buscarPorNombreEmpresaordenado(filtro.getTexto());
            listaPersonas = this.clienteRepository.buscarPorNombrePersonaordenado(filtro.getTexto());
        } else if (filtro.getEstado() == true && filtro.getNombre() == false && filtro.getTexto().isEmpty()) {
            listaclientes = this.clienteRepository.ordenadoporestado();
            for (ClienteEntity c: listaclientes) { //lista clientes ordenada por estado
                for (PersonaEntity p:listaPersonas) { //lista personas desordenada
                    if(p.getId() == c.getId()){
                        listaPersonasaux.add(p);
                    }
                }
                for (EmpresaEntity e:listaEmpresa) {
                    if(e.getId() == c.getId()){
                        listaEmpresaaux.add(e);
                    }
                }
            }
            listaPersonas = listaPersonasaux;
            listaEmpresa = listaEmpresaaux;
        } else {
            listaclientes = this.clienteRepository.ordenadoporestado();
            listaEmpresa = this.clienteRepository.buscarPorNombreEmpresaordenado(filtro.getTexto());
            listaPersonas = this.clienteRepository.buscarPorNombrePersonaordenado(filtro.getTexto());
            for (ClienteEntity c: listaclientes) { //lista clientes ordenada por estado
                for (PersonaEntity p:listaPersonas) { //lista personas desordenada
                    if(p.getId() == c.getId()){
                        listaPersonasaux.add(p);
                    }
                }
                for (EmpresaEntity e:listaEmpresa) {
                    if(e.getId() == c.getId()){
                        listaEmpresaaux.add(e);
                    }
                }
            }
            listaPersonas = listaPersonasaux;
            listaEmpresa = listaEmpresaaux;
        }

        model.addAttribute("listaclientes",listaclientes);
        model.addAttribute("filtro", filtro);
        model.addAttribute("listaempresas",listaEmpresa);
        model.addAttribute("listapersonas",listaPersonas);
        model.addAttribute("listasospechosos", CuentasSospechosas);


        return "listaclientes";
    }

    @GetMapping("/infopersona")
    public String doInfopersona(@RequestParam("id") Integer idcliente, Model model){
    return procesarFiltradoPersona(idcliente,null, model);
    }

    @PostMapping("/infopersona/filtrar")
    public String doFiltrarpersona(@RequestParam("id") Integer idcliente,@ModelAttribute("filtropersona") FiltroOperaciones filtropersona, Model model){
        return this.procesarFiltradoPersona(idcliente,filtropersona, model);
    }

    protected String procesarFiltradoPersona (@RequestParam("id") Integer idcliente,FiltroOperaciones filtropersona, Model model) {
            ClienteEntity cliente = this.clienteRepository.findById(idcliente).orElse(null);
            PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);
            List<CuentaBancoEntity> listacuentas = cliente.getCuentaBancosById();
            List<TransaccionEntity> listatransa = this.transaccionRepository.ordenarlistatransacciones();

            if(filtropersona == null || filtropersona.getCantidad() == false && filtropersona.getCuentaFiltro().isEmpty()){
                listatransa = this.transaccionRepository.findAll();
                filtropersona = new FiltroOperaciones();
            } else if (filtropersona.getCantidad() == true && filtropersona.getCuentaFiltro().isEmpty()) {
                listatransa = this.transaccionRepository.ordenarPorCantidad();
            } else if (filtropersona.getCantidad() == false && !filtropersona.getCuentaFiltro().isEmpty()) {
                listatransa = this.transaccionRepository.filtrarPorTexto(filtropersona.getCuentaFiltro());
            } else {
                listatransa = this.transaccionRepository.filtraPorTextoyordenarPorCantidad(filtropersona.getCuentaFiltro());
            }
            model.addAttribute("listatransa", listatransa);
            model.addAttribute("filtropersona", filtropersona);
            model.addAttribute("clientes", cliente);
            model.addAttribute("personas", persona);
            model.addAttribute("listacuentas",listacuentas);

            return "infopersona";
    }

    @GetMapping("/infoempresa")
    public String doInfoempresa(@RequestParam("id") Integer idcliente, Model model){
        return procesarFiltradoEmpresa(idcliente,null, model);
    }

    @PostMapping("/infoempresa/filtrar")
    public String doFiltrarEmpresa(@RequestParam("id") Integer idcliente,@ModelAttribute("filtroempresa") FiltroOperaciones filtroempresa, Model model){
        return this.procesarFiltradoEmpresa(idcliente,filtroempresa, model);
    }

    protected String procesarFiltradoEmpresa (@RequestParam("id") Integer idcliente,FiltroOperaciones filtroempresa, Model model) {
        ClienteEntity cliente = this.clienteRepository.findById(idcliente).orElse(null);
        EmpresaEntity empresa = this.empresaRepository.findById(idcliente).orElse(null);
        List<CuentaBancoEntity> listacuentas = cliente.getCuentaBancosById();
        List<TransaccionEntity> listatransa = this.transaccionRepository.ordenarlistatransacciones();

        if(filtroempresa == null || filtroempresa.getCantidad() == false && filtroempresa.getCuentaFiltro().isEmpty()){
            listatransa = this.transaccionRepository.findAll();
            filtroempresa = new FiltroOperaciones();
        } else if (filtroempresa.getCantidad() == true && filtroempresa.getCuentaFiltro().isEmpty()) {
            listatransa = this.transaccionRepository.ordenarPorCantidad();
        } else if (filtroempresa.getCantidad() == false && !filtroempresa.getCuentaFiltro().isEmpty()) {
            listatransa = this.transaccionRepository.filtrarPorTexto(filtroempresa.getCuentaFiltro());
        } else {
            listatransa = this.transaccionRepository.filtraPorTextoyordenarPorCantidad(filtroempresa.getCuentaFiltro());
        }
        model.addAttribute("listatransa", listatransa);
        model.addAttribute("filtroempresa", filtroempresa);
        model.addAttribute("clientes", cliente);
        model.addAttribute("empresa", empresa);
        model.addAttribute("listacuentas",listacuentas);

        return "infoempresa";
    }

    @GetMapping("/concesion")
    public String doConcesion(@RequestParam("id") Integer id,Model model){
    ClienteEntity cliente = this.clienteRepository.findById(id).get();
    List<PersonaEntity> personas = this.personaRepository.findAll();
    List<EmpresaEntity> empresas = this.empresaRepository.findAll();

        for (EmpresaEntity e : empresas) {
            if(e.getId() == cliente.getId()){
                EmpresaEntity empresa = this.empresaRepository.findById(id).get();
                model.addAttribute("empresa",empresa);
            }
        }
        for (PersonaEntity p : personas) {
            if(p.getId() == cliente.getId()){
                PersonaEntity persona = this.personaRepository.findById(id).get();
                model.addAttribute("persona",persona);
            }
        }


    model.addAttribute("cliente",cliente);

    return "concesion";
    }

    @GetMapping("/peticioncuenta")
    public String dopeticion(@ModelAttribute("id")Integer id){
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        CuentaBancoEntity cuentaBanco = new CuentaBancoEntity();

        cuentaBanco.setIbanCuenta(DataGenerator.randomIbanGenerator());
        cuentaBanco.setSaldo(0.0);
        cuentaBanco.setSwift(DataGenerator.randomSwiftGenerator());
        cuentaBanco.setPais("España");
        cuentaBanco.setFechaApertura(new Timestamp(System.currentTimeMillis()));
        cuentaBanco.setClienteByTitularId(cliente);
        cuentaBanco.setEntidadBancariaByEntidadBancariaId(this.entidadBancariaRepository.findById(1).orElse(null));
        cuentaBanco.setEstadoCuentaByEstadoCuentaId(this.estadoCuentaRepository.findById(1).orElse(null));
        cuentaBanco.setDivisaByDivisaId(this.divisaRepository.buscarPorNombre("EUR"));
        this.cuentaRepository.save(cuentaBanco);
        cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(1).orElse(null));
        this.clienteRepository.save(cliente);
        return "redirect:/gestor/clientespendientes";
    }

    @GetMapping("/rechazocuenta")
    public String dorechazo(){
        return "redirect:/gestor/clientespendientes";
    }

    @GetMapping("/listainactivos")
    public String doListarInactivos(Model model) {
        List<CuentaBancoEntity> listacuentasinactivas = this.transaccionRepository.listainactivos();
        List<ClienteEntity> listaclientes = this.clienteRepository.findAll();
        List<EmpresaEntity> listaEmpresa = this.empresaRepository.findAll();
        List<PersonaEntity> listaPersonas = this.personaRepository.findAll();
        List<ClienteEntity> listaclientesinactivos = new ArrayList<ClienteEntity>();

        for (ClienteEntity c:listaclientes ) {
            for (CuentaBancoEntity a:listacuentasinactivas) {
                if(a.getClienteByTitularId().getId() == c.getId() || c.getEstadoClienteByEstadoClienteId().getTipo().equals(2)){
                    listaclientesinactivos.add(c);
                }
            }
        }
        for (ClienteEntity c:listaclientesinactivos) {

        }

        model.addAttribute("listaclientes",listaclientesinactivos);
        model.addAttribute("listaempresas",listaEmpresa);
        model.addAttribute("listapersonas",listaPersonas);

        return "listaclientesinactivos";
    }

    @GetMapping("/desactivarcliente")
    public String doDesactivarcliente(@RequestParam("id") Integer id){
        ClienteEntity cliente = this.clienteRepository.findById(id).get();
        if(cliente.getEstadoClienteByEstadoClienteId().getId().equals(1)){
            cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(2).orElse(null));
        }else{
            cliente.setEstadoClienteByEstadoClienteId(this.estadoClienteRepository.findById(1).orElse(null));
        }
        this.clienteRepository.save(cliente);
        return "redirect:/gestor/listainactivos";
    }

    @GetMapping("/desactivarcuenta")
    public String doDesactivarcuenta(@RequestParam("id") Integer id){
        CuentaBancoEntity cuenta = this.cuentaRepository.findById(id).get();
        if(cuenta.getEstadoCuentaByEstadoCuentaId().getId().equals(1)){
            cuenta.setEstadoCuentaByEstadoCuentaId(this.estadoCuentaRepository.findById(2).orElse(null));
        }else{
            cuenta.setEstadoCuentaByEstadoCuentaId(this.estadoCuentaRepository.findById(1).orElse(null));
        }
        this.cuentaRepository.save(cuenta);
        return "redirect:/gestor/infopersona?id=" + id;
    }
}
