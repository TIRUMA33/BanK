package es.uma.taw.bank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DIVISA", schema = "taw", catalog = "")
public class DivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 3)
    private String id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 40)
    private String nombre;
    @Basic
    @Column(name = "EQUIVALENCIA", nullable = false, precision = 0)
    private Double equivalencia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Double equivalencia) {
        this.equivalencia = equivalencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisaEntity that = (DivisaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (equivalencia != null ? !equivalencia.equals(that.equivalencia) : that.equivalencia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (equivalencia != null ? equivalencia.hashCode() : 0);
        return result;
    }
}
