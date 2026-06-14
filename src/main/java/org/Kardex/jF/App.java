package org.Kardex.jF;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.Kardex.jF.view.MarcoPrincipalView;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new MarcoPrincipalView().setVisible(true));
    }
}
