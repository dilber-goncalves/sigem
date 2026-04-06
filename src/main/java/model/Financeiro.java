package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "financeiro")
public class Financeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFinanceiro;
    private  double valor;
    private Date dataVencimento, dataPagamento;
    private int idAluno;
    private String status;

    public Financeiro() {
    }

    public Financeiro(double valor, Date dataVencimento, Date dataPagamento, int idAluno, String status) {
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.idAluno = idAluno;
        this.status = status;
    }

    public int getIdFinanceiro() {
        return idFinanceiro;
    }

    public void setIdFinanceiro(int idFinanceiro) {
        this.idFinanceiro = idFinanceiro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
