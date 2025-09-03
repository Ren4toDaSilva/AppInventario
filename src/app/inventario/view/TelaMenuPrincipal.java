package app.inventario.view;

import javax.swing.*;

public class TelaMenuPrincipal {
    public JPanel mainPanel;
    public JButton btnJogar;
    public JButton btnSair;
    public JLabel txtTitulo;

    public TelaMenuPrincipal() {
        // Ao clicar em "Jogar" abre a TelaInventarioGUI
        btnJogar.addActionListener(e -> {
            JFrame frame = new JFrame("Inventário");
            TelaInventarioGUI telaInventario = new TelaInventarioGUI();
            frame.setContentPane(telaInventario.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // centralizar
            frame.setVisible(true);
        });

        // Ao clicar em "Sair"
        btnSair.addActionListener(e -> System.exit(0));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void showWindow() {
        JFrame frame = new JFrame("Catacombs Store");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // centralizar
        frame.setVisible(true);
    }

}


