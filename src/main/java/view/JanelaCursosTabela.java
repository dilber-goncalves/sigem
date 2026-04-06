package view;

import model.Cursos;

import java.util.List;

public class JanelaCursosTabela extends TabelaCrud<Cursos> {
    public JanelaCursosTabela() {
        super("Cursos");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Descricao"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 320};
    }

    @Override
    protected List<Cursos> carregarDados() {
        return GerenciadorCursos.listarTodos();
    }

    @Override
    protected Object getValorColuna(Cursos item, int coluna) {
        return coluna == 0 ? item.getIdCurso() : item.getDescricao();
    }

    @Override
    protected Integer getId(Cursos item) {
        return item.getIdCurso();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorCursos.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorCursos.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorCursos.excluir(id);
    }
}
