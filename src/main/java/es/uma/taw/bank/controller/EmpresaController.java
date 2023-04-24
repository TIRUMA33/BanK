package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private EmpresaRepository empresaRepository;

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping("/{id}")
    public String doEmpresa(@PathVariable("id") String id, Model model) {
        model.addAttribute("empresa", this.empresaRepository.findById(Integer.parseInt(id)).orElse(null));
        return "inicioEmpresa";
    }
}
