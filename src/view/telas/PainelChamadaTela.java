package view.telas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import view.componentes.Tema;

/**
 * Painel de chamada (a tela da TV) — PROVISÓRIO.
 *
 * É uma janela separada: não entra no CardLayout e não implementa Tela.
 * Só existe para o botão "Abrir painel" do MenuPrincipal já funcionar.
 * O conteúdo de verdade vem na branch de painel de chamada (Vinhal).
 */
public class PainelChamadaTela extends JFrame {

    public PainelChamadaTela() {
        super("Chamaê — Painel de chamada");
        // Fechar o painel fecha só esta janela, não o sistema inteiro.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(960, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Tema.FUNDO_SIDEBAR);

        JLabel aviso = new JLabel("Painel em construção", SwingConstants.CENTER);
        aviso.setFont(Tema.TITULO);
        aviso.setForeground(Tema.TEXTO_CLARO);
        add(aviso);
    }
}
