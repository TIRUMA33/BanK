package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.DireccionEntity;
import es.uma.taw.bank.entity.EmpresaEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
import es.uma.taw.bank.ui.RegistroEmpresa;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private DireccionRepository direccionRepository;

    private EmpresaRepository empresaRepository;

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setDireccionRepository(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{id}")
    public String doEmpresa(@PathVariable("id") String id, Model model) {
        model.addAttribute("empresa", this.empresaRepository.findById(Integer.parseInt(id)).orElse(null));
        return "inicioEmpresa";
    }

    @GetMapping("/{id}/persona")
    public String doEmpresaPersona(@PathVariable("id") String id, Model model) {
        model.addAttribute("empresa", this.empresaRepository.findById(Integer.parseInt(id)).orElse(null));
        return "inicioEmpresaPersona";
    }

    private RegistroEmpresa recuperarInformacion(String id) {
        EmpresaEntity empresa = this.empresaRepository.findById(Integer.parseInt(id)).orElse(null);
        RegistroEmpresa registroEmpresa = new RegistroEmpresa();

        registroEmpresa.setDireccion(this.direccionRepository.findByClienteByClienteId_Id(empresa.getId()).orElse(null));
        registroEmpresa.setUsuario(this.usuarioRepository.findById(empresa.getId()).orElse(null));
        registroEmpresa.setValida(registroEmpresa.getDireccion().getValida() != 0);
        registroEmpresa.setEmpresa(empresa);

        return registroEmpresa;
    }

    @GetMapping("/{id}/editar")
    public String doEditar(@PathVariable("id") String id, Model model) {
        model.addAttribute("registroEmpresa", recuperarInformacion(id));
        return "editarEmpresa";
    }

    @PostMapping("/{id}/editar/guardar")
    public String doGuardar(@PathVariable("id") String id, @ModelAttribute("registroEmpresa") RegistroEmpresa edicionEmpresa) {
        RegistroEmpresa registroEmpresa = recuperarInformacion(id);
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
}
