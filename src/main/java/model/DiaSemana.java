package model;

import javax.persistence.*;

@Entity
@Table(name = "diasemana")
public class DiaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDiasemana;
    private String dia;

    public DiaSemana() {
    }

    public DiaSemana(String dia) {
        this.dia = dia;
    }

    public int getIdDiasemana() {
        return idDiasemana;
    }

    public void setIdDiasemana(int idDiasemana) {
        this.idDiasemana = idDiasemana;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}


