package view;

import model.Salas;

import java.util.List;

public class JanelaSalasTabela extends TabelaCrud<Salas> {
    public JanelaSalasTabela() {
        super("Salas");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Sala"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 220};
    }

    @Override
    protected List<Salas> carregarDados() {
        return GerenciadorSalas.listarTodos();
    }

    @Override
    protected Object getValorColuna(Salas item, int coluna) {
        return coluna == 0 ? item.getIdSala() : item.getNomeSala();
    }

    @Override
    protected Integer getId(Salas item) {
        return item.getIdSala();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorSalas.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorSalas.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorSalas.excluir(id);
    }
}
