package es.uma.taw.bank.controller;

import es.uma.taw.bank.dao.*;
import es.uma.taw.bank.entity.*;
import es.uma.taw.bank.ui.RegistroEmpresa;
import es.uma.taw.bank.ui.RegistroPersona;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DireccionRepository direccionRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    TransaccionRepository transaccionRepository;

    @GetMapping("/")
    public String doPersona(Model model, HttpSession session){
        String urlTo;
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

        if (usuario == null) {
            urlTo = "iniciarSesion";
        } else {
            PersonaEntity persona = personaRepository.findByDni(usuario.getNif()).orElse(null);
            model.addAttribute("cuenta", cuentaRepository.buscarPorCliente(persona.getId()).get(0));
            model.addAttribute("persona", persona);
            urlTo = "inicioPersona";
        }
        return urlTo;
    }

    private RegistroPersona recuperarInfoPersona(int id) {
        RegistroPersona registroPersona = new RegistroPersona();

        registroPersona.setDireccion(this.direccionRepository.findByClienteByClienteId_Id(id).orElse(null));
        registroPersona.setUsuario(this.usuarioRepository.findById(id).orElse(null));
        registroPersona.setValida(registroPersona.getDireccion().getValida() != 0);
        registroPersona.setPersona(this.personaRepository.findById(id).orElse(null));

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
        PersonaEntity personaActualizada = registroPersona.getPersona();
        PersonaEntity personaForm = edicionPersona.getPersona();
        DireccionEntity direccionActualizada = registroPersona.getDireccion();
        DireccionEntity direccionForm = edicionPersona.getDireccion();
        UsuarioEntity usuarioActualizado = registroPersona.getUsuario();
        UsuarioEntity usuarioForm = edicionPersona.getUsuario();

        personaActualizada.setDni(personaForm.getDni());
        personaActualizada.setNombre(personaForm.getNombre());
        personaActualizada.setApellido1(personaForm.getApellido1());
        personaActualizada.setApellido2(personaForm.getApellido2());
        personaActualizada.setFechaNacimiento(personaForm.getFechaNacimiento());
        this.personaRepository.save(personaActualizada);

        direccionActualizada.setCalle(direccionForm.getCalle());
        direccionActualizada.setNumero(direccionForm.getNumero());
        direccionActualizada.setPlantaPuertaOficina(direccionForm.getPlantaPuertaOficina());
        direccionActualizada.setCiudad(direccionForm.getCiudad());
        direccionActualizada.setRegion(direccionForm.getRegion());
        direccionActualizada.setCodigoPostal(direccionForm.getCodigoPostal());
        direccionActualizada.setPais(direccionForm.getPais());
        direccionActualizada.setValida((byte) (edicionPersona.getValida() ? 1 : 0));
        this.direccionRepository.save(direccionActualizada);

        usuarioActualizado.setNif(personaForm.getDni());
        if (!(usuarioForm.getContrasena().isBlank() || registroPersona.getRcontrasena().isBlank())) {
            if(usuarioForm.getContrasena().equals(registroPersona.getRcontrasena())) {
                usuarioActualizado.setContrasena(usuarioForm.getContrasena());
            }
        }
        this.usuarioRepository.save(usuarioActualizado);
        return "redirect:/persona/";
    }

    @GetMapping("/transferencia")
    public String doTransferencia(@RequestParam("id") Integer idpersona, Model model){

        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setCuentaBancoByCuentaOrigen(this.cuentaRepository.buscarPorCliente(idpersona).get(0));
        List<CuentaBancoEntity> cuentas = this.cuentaRepository.buscarSinMi(transaccion.getCuentaBancoByCuentaOrigen().getId());

        model.addAttribute("cuentas", cuentas);
        model.addAttribute("transaccion", transaccion);
        return "transferenciaPersona";
    }

    @PostMapping("/transferencia/realizar")
    public String doRealizar(@ModelAttribute("transaccion") TransaccionEntity transaccion) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaccion.setFechaEjecucion(timestamp);
        transaccion.setFechaInstruccion(timestamp);

        CuentaBancoEntity origen =
                this.cuentaRepository.findById(transaccion.getCuentaBancoByCuentaOrigen().getId()).orElse(null);
        CuentaBancoEntity destino =
                this.cuentaRepository.findById(transaccion.getCuentaBancoByCuentaDestino().getId()).orElse(null);

        if(origen != null && destino != null) {
            origen.setSaldo(origen.getSaldo() - transaccion.getCantidad());
            destino.setSaldo(destino.getSaldo() + transaccion.getCantidad());

            this.transaccionRepository.save(transaccion);
            this.cuentaRepository.save(origen);
            this.cuentaRepository.save(destino);
        }

        return "redirect:/persona/";
    }

    @GetMapping("/solicitar")
    public String doSolicitado(@ModelAttribute("cuenta") CuentaBancoEntity cuenta) {
        EstadoCuentaEntity estado = new EstadoCuentaEntity();
        if (cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 1) {
            estado.setId(4);
        } else if(cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 2) {
            estado.setId(5);
        }
        cuenta.setEstadoCuentaByEstadoCuentaId(estado);
        this.cuentaRepository.save(cuenta);
        return "redirect:/persona/";
    }
}
