package app.inventario.view;

import javax.swing.*;
import java.awt.*;

public class TelaMenuPrincipal {
    private JPanel mainPanel;
    private JLabel txtTitulo;
    private JButton btnJogar;
    private JButton btnSair;

    public TelaMenuPrincipal() {
        // Carrega imagem do classpath (dentro de resources/images)
        ImageIcon backgroundIcon = new ImageIcon(
                getClass().getResource("/images/catacombs_store.jpeg")
        );
        Image backgroundImage = backgroundIcon.getImage();

        // Painel com fundo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Define a fonte "Ink Free" Bold Italic para os botões
        Font inkFreeFont = new Font("Ink Free", Font.BOLD | Font.ITALIC, 20);
        if (btnJogar != null) btnJogar.setFont(inkFreeFont);
        if (btnSair != null) btnSair.setFont(inkFreeFont);

        // Adiciona o painel do .form por cima da imagem
        backgroundPanel.add(mainPanel);

        // Substitui referência
        this.mainPanel = backgroundPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Teste rápido
    public static void main(String[] args) {
        JFrame frame = new JFrame("Catacombs Store");
        frame.setContentPane(new TelaMenuPrincipal().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
