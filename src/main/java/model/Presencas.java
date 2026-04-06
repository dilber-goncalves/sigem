package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="presencas")
public class Presencas {
    @Id
    @GeneratedValue
    private int idPresenca;
    private Date data;
    private int idAluno;
    private String conteudo, status;

    public Presencas() {
    }

    public Presencas(Date data, int idAluno, String conteudo, String status) {
        this.data = data;
        this.idAluno = idAluno;
        this.conteudo = conteudo;
        this.status = status;
    }

    public int getIdPresenca() {
        return idPresenca;
    }

    public void setIdPresenca(int idPresenca) {
        this.idPresenca = idPresenca;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
