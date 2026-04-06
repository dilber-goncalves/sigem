package model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name ="aulas")
public class Aulas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAula;
    private  int idAluno, idProfessor, idCurso, idModalidade, idSala, idDiaSemana;
    private Date dataInicio;

    public Aulas() {
    }

    public Aulas(int idAluno, int idProfessor, int idCurso, int idModalidade, int idSala, int idDiaSemana, Date dataInicio) {
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.idCurso = idCurso;
        this.idModalidade = idModalidade;
        this.idSala = idSala;
        this.idDiaSemana = idDiaSemana;
        this.dataInicio = dataInicio;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(int idModalidade) {
        this.idModalidade = idModalidade;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdDiaSemana() {
        return idDiaSemana;
    }

    public void setIdDiaSemana(int idDiaSemana) {
        this.idDiaSemana = idDiaSemana;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
}
