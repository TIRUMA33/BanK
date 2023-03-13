package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "divisa", schema = "taw", catalog = "")
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
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(equivalencia, that.equivalencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, equivalencia);
    }
}
