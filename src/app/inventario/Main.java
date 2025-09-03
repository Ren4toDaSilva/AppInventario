package app.inventario;

import app.inventario.view.TelaInventarioGUI;
import app.inventario.view.TelaMenuPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaMenuPrincipal tela = new TelaMenuPrincipal();
            tela.showWindow();
        });
    }
}