package view;

import model.Alunos;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JanelaFinanceiroTabela extends TabelaCrud<JanelaFinanceiroTabela.FinanceiroLinha> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public JanelaFinanceiroTabela() {
        super("Financeiro");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Aluno", "Valor", "Vencimento", "Pagamento", "Status"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 220, 90, 110, 110, 100};
    }

    @Override
    protected List<FinanceiroLinha> carregarDados() {
        Map<Integer, String> alunos = GerenciadorAlunos.listarTodos().stream()
                .collect(Collectors.toMap(Alunos::getIdAluno, Alunos::getNomeAluno, (a, b) -> a, HashMap::new));

        return GerenciadorFinanceiro.listarTodos().stream()
                .map(financeiro -> new FinanceiroLinha(
                        financeiro.getIdFinanceiro(),
                        alunos.getOrDefault(financeiro.getIdAluno(), "Aluno " + financeiro.getIdAluno()),
                        financeiro.getValor(),
                        financeiro.getDataVencimento() == null ? "" : sdf.format(financeiro.getDataVencimento()),
                        financeiro.getDataPagamento() == null ? "" : sdf.format(financeiro.getDataPagamento()),
                        financeiro.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    protected Object getValorColuna(FinanceiroLinha item, int coluna) {
        return switch (coluna) {
            case 0 -> item.getId();
            case 1 -> item.getAluno();
            case 2 -> item.getValor();
            case 3 -> item.getVencimento();
            case 4 -> item.getPagamento();
            case 5 -> item.getStatus();
            default -> "";
        };
    }

    @Override
    protected Integer getId(FinanceiroLinha item) {
        return item.getId();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorFinanceiro.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorFinanceiro.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorFinanceiro.excluir(id);
    }

    static class FinanceiroLinha {
        private final int id;
        private final String aluno;
        private final double valor;
        private final String vencimento;
        private final String pagamento;
        private final String status;

        private FinanceiroLinha(int id, String aluno, double valor, String vencimento, String pagamento, String status) {
            this.id = id;
            this.aluno = aluno;
            this.valor = valor;
            this.vencimento = vencimento;
            this.pagamento = pagamento;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getAluno() {
            return aluno;
        }

        public double getValor() {
            return valor;
        }

        public String getVencimento() {
            return vencimento;
        }

        public String getPagamento() {
            return pagamento;
        }

        public String getStatus() {
            return status;
        }
    }
}
