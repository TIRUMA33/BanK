package es.uma.taw.bank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DIRECCION", schema = "taw", catalog = "")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DireccionEntity direccion = (DireccionEntity) o;

        if (id != null ? !id.equals(direccion.id) : direccion.id != null) return false;
        if (calle != null ? !calle.equals(direccion.calle) : direccion.calle != null) return false;
        if (numero != null ? !numero.equals(direccion.numero) : direccion.numero != null) return false;
        if (plantaPuertaOficina != null ? !plantaPuertaOficina.equals(direccion.plantaPuertaOficina) : direccion.plantaPuertaOficina != null)
            return false;
        if (ciudad != null ? !ciudad.equals(direccion.ciudad) : direccion.ciudad != null) return false;
        if (region != null ? !region.equals(direccion.region) : direccion.region != null) return false;
        if (codigoPostal != null ? !codigoPostal.equals(direccion.codigoPostal) : direccion.codigoPostal != null)
            return false;
        if (pais != null ? !pais.equals(direccion.pais) : direccion.pais != null) return false;
        if (valida != null ? !valida.equals(direccion.valida) : direccion.valida != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (calle != null ? calle.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (plantaPuertaOficina != null ? plantaPuertaOficina.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (codigoPostal != null ? codigoPostal.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (valida != null ? valida.hashCode() : 0);
        return result;
    }
}
