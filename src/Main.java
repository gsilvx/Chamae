import javax.swing.SwingUtilities;
import view.MenuPrincipal;

/** Ponto de entrada do Chamaê: só abre a janela principal. */
public class Main {

    public static void main(String[] args) {
        // invokeLater cria a janela na thread da interface do Swing,
        // que é a única thread que deve mexer em componentes visuais.
        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}
