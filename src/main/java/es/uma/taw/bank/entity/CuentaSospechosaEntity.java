package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CUENTA_SOSPECHOSA", schema = "taw", catalog = "")
public class CuentaSospechosaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaSospechosaEntity that = (CuentaSospechosaEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
