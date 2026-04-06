package view;

import model.Alunos;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JanelaPresencasTabela extends TabelaCrud<JanelaPresencasTabela.PresencaLinha> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public JanelaPresencasTabela() {
        super("Presencas");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Data", "Aluno", "Conteudo", "Status"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 110, 200, 320, 80};
    }

    @Override
    protected List<PresencaLinha> carregarDados() {
        Map<Integer, String> alunos = GerenciadorAlunos.listarTodos().stream()
                .collect(Collectors.toMap(Alunos::getIdAluno, Alunos::getNomeAluno, (a, b) -> a, HashMap::new));

        return GerenciadorPresencas.listarTodos().stream()
                .map(presenca -> new PresencaLinha(
                        presenca.getIdPresenca(),
                        presenca.getData() == null ? "" : sdf.format(presenca.getData()),
                        alunos.getOrDefault(presenca.getIdAluno(), "Aluno " + presenca.getIdAluno()),
                        presenca.getConteudo(),
                        presenca.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    protected Object getValorColuna(PresencaLinha item, int coluna) {
        return switch (coluna) {
            case 0 -> item.getId();
            case 1 -> item.getData();
            case 2 -> item.getAluno();
            case 3 -> item.getConteudo();
            case 4 -> item.getStatus();
            default -> "";
        };
    }

    @Override
    protected Integer getId(PresencaLinha item) {
        return item.getId();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorPresencas.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorPresencas.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorPresencas.excluir(id);
    }

    static class PresencaLinha {
        private final int id;
        private final String data;
        private final String aluno;
        private final String conteudo;
        private final String status;

        private PresencaLinha(int id, String data, String aluno, String conteudo, String status) {
            this.id = id;
            this.data = data;
            this.aluno = aluno;
            this.conteudo = conteudo;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getData() {
            return data;
        }

        public String getAluno() {
            return aluno;
        }

        public String getConteudo() {
            return conteudo;
        }

        public String getStatus() {
            return status;
        }
    }
}
