package view.telas;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.Tela;
import view.componentes.Tema;

/**
 * Tela "Simulação" — PROVISÓRIA.
 *
 * Só existe para o MenuPrincipal já ter todas as telas registradas no
 * CardLayout. O conteúdo de verdade vem na branch de simulação (Vinhal).
 */
public class SimulacaoTela extends JPanel implements Tela {

    public SimulacaoTela() {
        super(new BorderLayout());
        setBackground(Tema.FUNDO);

        JLabel aviso = new JLabel("Tela em construção", SwingConstants.CENTER);
        aviso.setFont(Tema.SUBTITULO);
        aviso.setForeground(Tema.TEXTO_SECUNDARIO);
        add(aviso, BorderLayout.CENTER);
    }

    @Override
    public void atualizar() {
        // Ainda não há dados para reler.
    }

    @Override
    public String getTitulo() {
        return "Simulação";
    }
}
