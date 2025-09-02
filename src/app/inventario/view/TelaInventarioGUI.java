package app.inventario.view;

import app.inventario.model.Inventario;
import app.inventario.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaInventarioGUI {
    private JPanel mainPanel;
    private JTable tabelaProdutos;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnRemover;

    private Inventario inventario = new Inventario();
    private DefaultTableModel modelo;

    // Construtor correto (antes estava "void")
    public TelaInventarioGUI() {
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Quantidade"}, 0);
        tabelaProdutos.setModel(modelo);

        btnAdicionar.addActionListener(e -> abrirFormulario(null));
        btnEditar.addActionListener(e -> editarProduto());
        btnRemover.addActionListener(e -> removerProduto());

        atualizarTabela();
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
                    inventario.adicionarProduto(nome, qtd);
                } else {
                    inventario.editarProduto(produtoExistente.getId(), nome, qtd);
                }
                atualizarTabela();
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
            inventario.removerProduto(id);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para remover!");
        }
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Produto p : inventario.listarProdutos()) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getQuantidade()});
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
