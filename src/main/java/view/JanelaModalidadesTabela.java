package view;

import model.Modalidades;

import java.util.List;

public class JanelaModalidadesTabela extends TabelaCrud<Modalidades> {
    public JanelaModalidadesTabela() {
        super("Modalidades");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Descricao"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 240};
    }

    @Override
    protected List<Modalidades> carregarDados() {
        return GerenciadorModalidades.listarTodos();
    }

    @Override
    protected Object getValorColuna(Modalidades item, int coluna) {
        return coluna == 0 ? item.getIdModalidade() : item.getDescricao();
    }

    @Override
    protected Integer getId(Modalidades item) {
        return item.getIdModalidade();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorModalidades.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorModalidades.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorModalidades.excluir(id);
    }
}
