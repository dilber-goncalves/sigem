package model;

import javax.persistence.*;

@Entity
@Table (name = "modalidades")
public class Modalidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModalidade;
    private String Descricao;

    public Modalidades() {

    }

    public Modalidades(String descricao) {
        Descricao = descricao;
    }

    public int getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(int idModalidade) {
        this.idModalidade = idModalidade;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
