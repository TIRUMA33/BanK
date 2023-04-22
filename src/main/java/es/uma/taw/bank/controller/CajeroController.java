package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.ClienteRepository;
import es.uma.taw.bank.dao.CuentaRepository;
import es.uma.taw.bank.entity.ClienteEntity;
import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cajero")
public class CajeroController {
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @GetMapping("/listar")
    public String doListar(Model model, @RequestParam("cliente") Integer idCliente ){
        ClienteEntity cliente = clienteRepository.findById(idCliente).get();
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarPorCliente(idCliente);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("cliente",cliente);
        return "cajero";
    }
    @GetMapping("/cuenta")
    public String doCuentaCajero(Model model, @RequestParam("cuenta") Integer idCuenta){
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarSinMi(idCuenta);
        CuentaBancoEntity cuenta = cuentaRepository.findById(idCuenta).get();
        model.addAttribute("cuenta",cuenta);
        model.addAttribute("cuentas",cuentas);

        return "cuenta";
    }
    @GetMapping("/datos")
    public String doDatos(Model model, @RequestParam("cliente") Integer idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).get();

        return "datos";
    }
}
