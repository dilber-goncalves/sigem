package view;

import model.Professores;

import java.text.SimpleDateFormat;
import java.util.List;

public class JanelaProfessoresTabela extends TabelaCrud<Professores> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public JanelaProfessoresTabela() {
        super("Professores");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Nome", "Contato", "Nascimento"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 220, 180, 110};
    }

    @Override
    protected List<Professores> carregarDados() {
        return GerenciadorProfessores.listarTodos();
    }

    @Override
    protected Object getValorColuna(Professores item, int coluna) {
        return switch (coluna) {
            case 0 -> item.getIdProfessor();
            case 1 -> item.getNomeProfessor();
            case 2 -> item.getContatoProfessor();
            case 3 -> item.getDataNascProfessor() == null ? "" : sdf.format(item.getDataNascProfessor());
            default -> "";
        };
    }

    @Override
    protected Integer getId(Professores item) {
        return item.getIdProfessor();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorProfessores.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorProfessores.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorProfessores.excluir(id);
    }
}
