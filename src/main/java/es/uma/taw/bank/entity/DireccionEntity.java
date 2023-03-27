package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "direccion", schema = "taw", catalog = "")
public class DireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "CALLE", nullable = false, length = 45)
    private String calle;
    @Basic
    @Column(name = "NUMERO", nullable = false)
    private Integer numero;
    @Basic
    @Column(name = "PLANTA_PUERTA_OFICINA", nullable = true, length = 45)
    private String plantaPuertaOficina;
    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 45)
    private String ciudad;
    @Basic
    @Column(name = "REGION", nullable = false, length = 45)
    private String region;
    @Basic
    @Column(name = "CODIGO_POSTAL", nullable = false)
    private Integer codigoPostal;
    @Basic
    @Column(name = "PAIS", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "VALIDA", nullable = true)
    private Byte valida;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByClienteId;
    @ManyToOne
    @JoinColumn(name = "TIPO_DIRECCION_ID", referencedColumnName = "ID", nullable = false)
    private TipoDireccionEntity tipoDireccionByTipoDireccionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPlantaPuertaOficina() {
        return plantaPuertaOficina;
    }

    public void setPlantaPuertaOficina(String plantaPuertaOficina) {
        this.plantaPuertaOficina = plantaPuertaOficina;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Byte getValida() {
        return valida;
    }

    public void setValida(Byte valida) {
        this.valida = valida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DireccionEntity that = (DireccionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(calle, that.calle) && Objects.equals(numero, that.numero) && Objects.equals(plantaPuertaOficina, that.plantaPuertaOficina) && Objects.equals(ciudad, that.ciudad) && Objects.equals(region, that.region) && Objects.equals(codigoPostal, that.codigoPostal) && Objects.equals(pais, that.pais) && Objects.equals(valida, that.valida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida);
    }

    public ClienteEntity getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteEntity clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public TipoDireccionEntity getTipoDireccionByTipoDireccionId() {
        return tipoDireccionByTipoDireccionId;
    }

    public void setTipoDireccionByTipoDireccionId(TipoDireccionEntity tipoDireccionByTipoDireccionId) {
        this.tipoDireccionByTipoDireccionId = tipoDireccionByTipoDireccionId;
    }
}
