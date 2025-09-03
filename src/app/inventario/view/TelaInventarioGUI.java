package app.inventario.view;

import app.inventario.dao.ProdutoDAO;
import app.inventario.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaInventarioGUI {
    public JPanel mainPanel;
    public JTable tabelaProdutos;
    public JButton btnAdicionar;
    public JButton btnEditar;
    public JButton btnRemover;

    public DefaultTableModel modelo;

    // Construtor
    public TelaInventarioGUI() {
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Quantidade"}, 0);
        tabelaProdutos.setModel(modelo);

        btnAdicionar.addActionListener(e -> abrirFormulario(null));
        btnEditar.addActionListener(e -> editarProduto());
        btnRemover.addActionListener(e -> removerProduto());

        // Puxa os dados salvos do banco ao abrir
        carregarProdutos();
    }

    // Puxa os dados do banco e joga na tabela
    private void carregarProdutos() {
        modelo.setRowCount(0); // limpa a tabela

        for (Produto p : ProdutoDAO.listar()) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getQuantidade()
            });
        }
    }

    private void abrirFormulario(Produto produtoExistente) {
        JTextField txtNome = new JTextField();
        JTextField txtQtd = new JTextField();

        if (produtoExistente != null) {
            txtNome.setText(produtoExistente.getNome());
            txtQtd.setText(String.valueOf(produtoExistente.getQuantidade()));
        }

        Object[] campos = {"Nome:", txtNome, "Quantidade:", txtQtd};
        int opcao = JOptionPane.showConfirmDialog(null, campos, "Produto", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                String nome = txtNome.getText();
                int qtd = Integer.parseInt(txtQtd.getText());

                if (produtoExistente == null) {
                    ProdutoDAO.adicionar(nome, qtd);
                } else {
                    ProdutoDAO.atualizar(produtoExistente.getId(), nome, qtd);
                }
                carregarProdutos(); // atualiza tabela com dados do banco
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida!");
            }
        }
    }

    private void editarProduto() {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha != -1) {
            int id = (int) modelo.getValueAt(linha, 0);
            String nome = (String) modelo.getValueAt(linha, 1);
            int qtd = (int) modelo.getValueAt(linha, 2);
            abrirFormulario(new Produto(id, nome, qtd));
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para editar!");
        }
    }

    private void removerProduto() {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha != -1) {
            int id = (int) modelo.getValueAt(linha, 0);
            ProdutoDAO.remover(id);
            carregarProdutos(); // recarrega após remover
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para remover!");
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
