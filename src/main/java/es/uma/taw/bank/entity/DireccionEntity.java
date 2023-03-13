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
    @Column(name = "Calle", nullable = false, length = 45)
    private String calle;
    @Basic
    @Column(name = "Numero", nullable = false)
    private Integer numero;
    @Basic
    @Column(name = "PlantaPuertaOficina", nullable = true, length = 45)
    private String plantaPuertaOficina;
    @Basic
    @Column(name = "Ciudad", nullable = false, length = 45)
    private String ciudad;
    @Basic
    @Column(name = "Region", nullable = false, length = 45)
    private String region;
    @Basic
    @Column(name = "CodigoPostal", nullable = false)
    private Integer codigoPostal;
    @Basic
    @Column(name = "Pais", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "Valida", nullable = true)
    private Byte valida;

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
}
