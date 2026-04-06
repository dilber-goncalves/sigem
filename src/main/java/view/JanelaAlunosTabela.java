package view;

import model.Alunos;

import java.text.SimpleDateFormat;
import java.util.List;

public class JanelaAlunosTabela extends TabelaCrud<Alunos> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public JanelaAlunosTabela() {
        super("Alunos");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Nome", "Nascimento", "Nivel", "Contato", "Responsavel"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 220, 110, 80, 150, 220};
    }

    @Override
    protected List<Alunos> carregarDados() {
        return GerenciadorAlunos.listarTodos();
    }

    @Override
    protected Object getValorColuna(Alunos item, int coluna) {
        return switch (coluna) {
            case 0 -> item.getIdAluno();
            case 1 -> item.getNomeAluno();
            case 2 -> item.getDataNascimento() == null ? "" : sdf.format(item.getDataNascimento());
            case 3 -> item.getNivel();
            case 4 -> item.getContatoAluno();
            case 5 -> item.getNomeResponsavel();
            default -> "";
        };
    }

    @Override
    protected Integer getId(Alunos item) {
        return item.getIdAluno();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorAlunos.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorAlunos.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorAlunos.excluir(id);
    }
}
