package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alunos")
public class Alunos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAluno;
    private String nomeAluno, nivel, contatoAluno, nomeResponsavel;
    private Date dataNascimento;

    public Alunos() {
    }

    public Alunos(String nomeAluno, String nivel, String contatoAluno, String nomeResponsavel, Date dataNascimento) {
        this.nomeAluno = nomeAluno;
        this.nivel = nivel;
        this.contatoAluno = contatoAluno;
        this.nomeResponsavel = nomeResponsavel;
        this.dataNascimento = dataNascimento;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getContatoAluno() {
        return contatoAluno;
    }

    public void setContatoAluno(String contatoAluno) {
        this.contatoAluno = contatoAluno;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
