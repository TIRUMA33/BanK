package es.uma.taw.bank.controller;

import es.uma.taw.bank.DataGenerator;
import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
        List<ClienteEntity> listaclientes = this.clienteRepository.findAll();
        List<EmpresaEntity> listaEmpresa = this.empresaRepository.findAll();
        List<PersonaEntity> listaPersonas = this.personaRepository.findAll();

        model.addAttribute("listaclientes",listaclientes);
        model.addAttribute("listaempresas",listaEmpresa);
        model.addAttribute("listapersonas",listaPersonas);
        return "listaclientes";
    }

    @GetMapping("/infopersona")
    public String doInfopersona(@RequestParam("id") Integer idcliente, Model model){
    ClienteEntity cliente = this.clienteRepository.findById(idcliente).orElse(null);
    PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);
    List<CuentaBancoEntity> listacuentas = cliente.getCuentaBancosById();
    List<TransaccionEntity> listatransa = this.transaccionRepository.ordenarlistatransacciones();

    model.addAttribute("listatransa", listatransa);
    model.addAttribute("clientes", cliente);
    model.addAttribute("personas", persona);
    model.addAttribute("listacuentas",listacuentas);

    return "infopersona";
    }

    @GetMapping("/infoempresa")
    public String doInfoempresa(@RequestParam("id") Integer idcliente, Model model){
        ClienteEntity cliente = this.clienteRepository.findById(idcliente).orElse(null);
        EmpresaEntity empresa = this.empresaRepository.findById(idcliente).orElse(null);
        List<CuentaBancoEntity> listacuentas = cliente.getCuentaBancosById();
        List<TransaccionEntity> listatransa = this.transaccionRepository.ordenarlistatransacciones();

        model.addAttribute("listatransa", listatransa);
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
}
