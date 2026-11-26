import Controller.Indicadores;
import Controller.SistemaFila;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import visual.TelaPrincipal;

/** Ponto de entrada do Chamaê: cria os controllers e abre a tela principal. */
public class Main {

    public static void main(String[] args) {
        // Mesmo visual (Nimbus) que o NetBeans usa nos JFrame Form.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Se o Nimbus não existir, fica o visual padrão do Java.
        }

        SwingUtilities.invokeLater(() -> {
            // Os controllers são criados UMA vez só. Todas as telas recebem estas
            // mesmas instâncias, para enxergarem a mesma fila.
            SistemaFila sistemaFila = new SistemaFila();
            Indicadores indicadores = new Indicadores();
            new TelaPrincipal(sistemaFila, indicadores).setVisible(true);
        });
    }
}
