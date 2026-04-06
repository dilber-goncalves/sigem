package model;

import javax.persistence.*;

@Entity
@Table(name = "curso")
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCurso;
    private String descricao;

    public Cursos() {
    }

    public Cursos(String descricao) {
        this.descricao = descricao;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
