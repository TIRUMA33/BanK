package es.uma.taw.bank.service;
//Autor Pablo Robles Mansilla

import es.uma.taw.bank.dao.ConversacionRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.dto.ConversacionDTO;
import es.uma.taw.bank.dto.MensajeDTO;
import es.uma.taw.bank.entity.ConversacionEntity;
import es.uma.taw.bank.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversacionService {

    @Autowired
    private ConversacionRepository conversacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ConversacionDTO findConversacionAbiertaByUsuario(Integer id) {
        ConversacionEntity conver = this.conversacionRepository.findConversacionAbiertaByUsuario(id);
        if (conver != null) {
            return conver.toDTO();
        } else {
            return null;
        }
    }

    public void save(MensajeDTO mensajeDTO) {
        ConversacionEntity conver = new ConversacionEntity();
        conver.setFechaCreacion(new java.sql.Timestamp(System.currentTimeMillis()));
        conver.setTerminada((byte) 0);
        conver.setUsuarioByEmisor(this.usuarioRepository.findById(mensajeDTO.getEmisor()).orElse(null));
        conver.setUsuarioByReceptor(this.usuarioRepository.findAsistente());
        this.conversacionRepository.save(conver);
    }

    public void guardar(ConversacionDTO conversacionDTO) {
        ConversacionEntity conversacion = new ConversacionEntity();
        conversacion.setId(conversacionDTO.getId());
        conversacion.setFechaCreacion(new java.sql.Timestamp(System.currentTimeMillis()));
        conversacion.setTerminada((byte) 1);
        conversacion.setUsuarioByEmisor(this.usuarioRepository.findById(conversacionDTO.getEmisor()).orElse(null));
        conversacion.setUsuarioByReceptor(this.usuarioRepository.findById(conversacionDTO.getReceptor()).orElse(null));
        this.conversacionRepository.save(conversacion);
    }

    public ConversacionDTO buscarConversacion(Integer id) {
        ConversacionEntity conversacion = conversacionRepository.findById(id).orElse(null);
        if (conversacion != null) {
            return conversacion.toDTO();
        } else {
            return null;
        }
    }

    public List<ConversacionDTO> findAll() {
        List<ConversacionEntity> lista = this.conversacionRepository.findAll();
        return this.listaEntidadesADTO(lista);
    }

    public List<ConversacionDTO> filtrar(FiltroAsistente filtro) {
        List<ConversacionEntity> convers = this.conversacionRepository.findAll();
        if ((filtro.getEstado() == null) && (!filtro.isPendientes()) && (!filtro.isFecha())) {
            convers = this.conversacionRepository.findByNif(filtro.getDni());
        } else if ((filtro.getDni().isBlank()) && (!filtro.isPendientes()) && (!filtro.isFecha())) {
            convers = this.conversacionRepository.findByEstado(filtro.getEstado());
        } else if ((filtro.getDni().isBlank()) && (!filtro.isPendientes()) && (filtro.getEstado() == null)) {
            convers = this.conversacionRepository.orderByFecha();
        } else if ((filtro.getDni().isBlank()) && (filtro.getEstado() == null) && (!filtro.isFecha())) {
            convers = this.conversacionRepository.orderByEstado();
        } else if ((filtro.getDni().isBlank()) && (filtro.getEstado() == null)) {
            convers = this.conversacionRepository.orderByFechayEstado();
        } else if ((filtro.getEstado() == null) && (((!filtro.isFecha()) || (!filtro.isPendientes())) || (filtro.isFecha() && filtro.isPendientes()))) {
            convers = this.conversacionRepository.findByNifOrderByEstado(filtro.getDni());
        } else if ((filtro.getDni().isBlank()) && (((!filtro.isPendientes() || !filtro.isFecha())) || (filtro.isPendientes() && filtro.isFecha()))) {
            convers = this.conversacionRepository.findByEstadoOrderByFecha(filtro.getEstado());
        } else if ((!filtro.isFecha() || !filtro.isPendientes()) && (!filtro.isPendientes() || filtro.isFecha() || filtro.isPendientes())) {
            convers = this.conversacionRepository.findByNifAndEstado(filtro.getDni(), filtro.getEstado());
        } else {
            convers = this.conversacionRepository.findByNifAndEstadoOrderByFechayEstado(filtro.getDni(),
                    filtro.getEstado());
        }
        return this.listaEntidadesADTO(convers);
    }

    public List<ConversacionDTO> findAllByEmisor(Integer id) {
        List<ConversacionEntity> lista = this.conversacionRepository.findAllByEmisor(id);
        return this.listaEntidadesADTO(lista);
    }

    protected List<ConversacionDTO> listaEntidadesADTO(List<ConversacionEntity> lista) {
        List<ConversacionDTO> dtos = new ArrayList<>();

        lista.forEach((final ConversacionEntity conversacion) -> dtos.add(conversacion.toDTO()));

        return dtos;
    }
}
