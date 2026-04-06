package view;

import dao.AlunosDao;
import dao.FinanceiroDao;
import dao.PresencasDao;
import model.Alunos;
import model.Financeiro;
import model.Presencas;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static view.GerenciadorAulas.retornaId;

public class GerenciadorFinanceiro {
    public static List<Financeiro> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            FinanceiroDao financeiroDao = new FinanceiroDao(em);
            return financeiroDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        FinanceiroDao financeiroDao = new FinanceiroDao(em);

        String resultado = GerenciadorAlunos.consultar();
        int idAluno = retornaId(resultado);
        Double valor = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor da parcela"));
        Date dataVencimento;
        String dataDigitada = JOptionPane.showInputDialog("insira a data de vencimento da aula do aluno");
        try {
            dataVencimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        Date dataPagamento;
        dataDigitada = JOptionPane.showInputDialog("insira a data do pagamento da aula");
        try {
            dataPagamento = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        String status = JOptionPane.showInputDialog("Insira o status do pagamento (Pago - Aberto)");

        Financeiro financeiro = new Financeiro(valor, dataVencimento, dataPagamento, idAluno, status);

        em.getTransaction().begin();
        financeiroDao.cadastrar(financeiro);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        EntityManager em = JPAUtil.getEntityManager();
        FinanceiroDao financeiroDao = new FinanceiroDao(em);
        AlunosDao alunosDao = new AlunosDao(em);

        List<Financeiro> todosFinanceiros = financeiroDao.buscarTodos();
        int tamanho = todosFinanceiros.size();
        String resultado = "ID\tALUNO\tVALOR\tDATA DO VENCIMENTO\tDATA PAGAMENTO\tSTATUS\n";
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < tamanho; i++) {
            Alunos alunos = alunosDao.buscarPorId(todosFinanceiros.get(i).getIdAluno());
            Financeiro financeiro = financeiroDao.buscarPorId(todosFinanceiros.get(i).getIdFinanceiro());

            resultado += todosFinanceiros.get(i).getIdFinanceiro() + "\t" +
                    alunos.getNomeAluno() + "\t" +
                    sdfData.format(financeiro.getDataVencimento()) + "\t\t" +
                    sdfData.format(financeiro.getDataPagamento()) + "\t\t" +
                    financeiro.getStatus()
                    + "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        FinanceiroDao financeiroDao = new FinanceiroDao(em);
        AlunosDao alunosDao = new AlunosDao(em);

        String botoes [] = {"Aluno", "Valor", "Data Vencimento", "Data do Pagamento", "Status"};
        String resultado;
        int opcao = JOptionPane.showOptionDialog(null, "Escolha a opção que deseja alterar", "ALTERAÇÕES", 0, 1, null, botoes, 0);
        JTextArea areaTexto = new JTextArea(10, 60);
        areaTexto.setEditable(false);
        JScrollPane painel = new JScrollPane(areaTexto);
        Financeiro financeiro = financeiroDao.buscarPorId(id);

        if (opcao == 0) {
            resultado = GerenciadorAlunos.consultar();
            areaTexto.setText(resultado);
            painel = new JScrollPane(areaTexto);
            JOptionPane.showMessageDialog(null, painel);

            em.getTransaction().begin();
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do aluno"));
            financeiro.setIdAluno(id);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 1) {
            Double valor = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor da mensalidade"));
            em.getTransaction().begin();
            financeiro.setValor(valor);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 2) {
            String dataDigitada = JOptionPane.showInputDialog("insira a data de vencimento");
            Date dataVencimento;
            try {
                dataVencimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            em.getTransaction().begin();
            financeiro.setDataVencimento(dataVencimento);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 3){
            String dataDigitada = JOptionPane.showInputDialog("insira a data de pagamento");
            Date dataPagamento;
            try {
                dataPagamento = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            em.getTransaction().begin();
            financeiro.setDataPagamento(dataPagamento);
            em.getTransaction().commit();
            em.close();

        } else {
            String status = JOptionPane.showInputDialog("Insira o status do pagamento (Pago - Aberto)");
            em.getTransaction().begin();
            financeiro.setStatus(status);
            em.getTransaction().commit();
            em.close();
        }
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        FinanceiroDao financeiroDao = new FinanceiroDao(em);

        Financeiro financeiro = financeiroDao.buscarPorId(id);
        em.getTransaction().begin();
        financeiroDao.excluir(financeiro);
        em.getTransaction().commit();
        em.close();
    }
}
