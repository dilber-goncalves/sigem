package model;

import javax.persistence.*;

@Entity
@Table (name = "salas")
public class Salas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSala;
    private String nomeSala;

    public Salas() { }

    public Salas(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }


}
