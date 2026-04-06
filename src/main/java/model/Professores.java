package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "professores")
public class Professores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfessor;
    private String nomeProfessor, contatoProfessor;
    private Date dataNascProfessor;

    public Professores() {    }

    public Professores(String nomeProfessor, String contatoProfessor, Date dataNascProfessor) {
        this.nomeProfessor = nomeProfessor;
        this.contatoProfessor = contatoProfessor;
        this.dataNascProfessor = dataNascProfessor;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getContatoProfessor() {
        return contatoProfessor;
    }

    public void setContatoProfessor(String contatoProfessor) {
        this.contatoProfessor = contatoProfessor;
    }

    public Date getDataNascProfessor() {
        return dataNascProfessor;
    }

    public void setDataNascProfessor(Date dataNascProfessor) {
        this.dataNascProfessor = dataNascProfessor;
    }
}
