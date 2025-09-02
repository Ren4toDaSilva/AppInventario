package app.inventario.model;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    public List<Produto> produtos = new ArrayList<>();
    public int proximoId = 1;

    public void adicionarProduto(String nome, int quantidade) {
        produtos.add(new Produto(proximoId++, nome, quantidade));
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public void editarProduto(int id, String nome, int qtd) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                p.setNome(nome);
                p.setQuantidade(qtd);
                return;
            }
        }
    }

    public void removerProduto(int id) {
        produtos.removeIf(p -> p.getId() == id);
    }
}
