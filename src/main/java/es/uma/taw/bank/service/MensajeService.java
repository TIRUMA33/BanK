package es.uma.taw.bank.service;
//Autor Pablo Robles Mansilla
import es.uma.taw.bank.dao.ConversacionRepository;
import es.uma.taw.bank.dao.MensajeRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.dto.ConversacionDTO;
import es.uma.taw.bank.dto.MensajeDTO;
import es.uma.taw.bank.entity.ConversacionEntity;
import es.uma.taw.bank.entity.MensajeEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ConversacionRepository conversacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void save(MensajeDTO mensajeDTO){
        MensajeEntity mensaje = new MensajeEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mensaje.setFecha(timestamp);
        mensaje.setContenido(mensajeDTO.getContenido());
        mensaje.setConversacionByConversacion(this.conversacionRepository.findById(mensajeDTO.getConversacion()).orElse(null));
        mensaje.setUsuarioByEmisor(this.usuarioRepository.findById(mensajeDTO.getEmisor()).orElse(null));
        this.mensajeRepository.save(mensaje);
    }

    public List<MensajeDTO> findMensajesByConversacion(Integer id){
        List<MensajeEntity> mensajes = this.mensajeRepository.findMensajesByConversacion(id);
        List<MensajeDTO> mensajesDTO = new ArrayList<>();
        for(MensajeEntity m : mensajes){
            mensajesDTO.add(m.toDTO());
        }
        return mensajesDTO;
    }

    public MensajeDTO setMensaje(Integer id, ConversacionDTO conversacionDTO){
        MensajeEntity msj = new MensajeEntity();
        UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        ConversacionEntity conver;
        if(conversacionDTO == null){
            conver = new ConversacionEntity();
            conver.setFechaCreacion(new java.sql.Timestamp(System.currentTimeMillis()));
            conver.setTerminada((byte) 0);
            conver.setUsuarioByEmisor(usuario);
            conver.setUsuarioByReceptor(this.usuarioRepository.findAsistente());
            this.conversacionRepository.save(conver);
        }else{
            conver = this.conversacionRepository.findById(conversacionDTO.getId()).orElse(null);
        }
        msj.setUsuarioByEmisor(usuario);
        msj.setConversacionByConversacion(conver);
        msj.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        return msj.toDTO();
    }
}
