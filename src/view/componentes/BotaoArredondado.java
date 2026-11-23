package view.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Botão com cantos arredondados, desenhado com Graphics2D.
 *
 * Uso: new BotaoArredondado("Emitir senha", BotaoArredondado.Tipo.PRIMARIO)
 *
 * O Swing desenha o texto do botão; esta classe só pinta o fundo
 * arredondado por baixo, com uma cor para cada tipo e estado (hover,
 * selecionado, desabilitado).
 */
public class BotaoArredondado extends JButton {

    /** Os estilos de botão do Chamaê. */
    public enum Tipo {
        /** Ação principal da tela (fundo azul). Ex.: "Emitir senha". */
        PRIMARIO,
        /** Ação secundária (fundo branco com borda). Ex.: "Limpar". */
        SECUNDARIO,
        /** Ação destrutiva (fundo vermelho). Ex.: "Excluir", "Cancelar". */
        PERIGO,
        /** Item da sidebar do MenuPrincipal. */
        MENU
    }

    private final Tipo tipo;
    private boolean hover = false;       // o mouse está em cima do botão?
    private boolean selecionado = false; // só usado no tipo MENU

    public BotaoArredondado(String texto, Tipo tipo) {
        super(texto);
        this.tipo = tipo;

        // Desliga o desenho padrão do Swing: quem pinta o fundo somos nós.
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFont(Tema.FONTE_TEXTO);

        if (tipo == Tipo.MENU) {
            // Itens do menu: texto à esquerda e ocupando toda a largura da sidebar.
            setHorizontalAlignment(SwingConstants.LEFT);
            setBorder(new EmptyBorder(Tema.ESPACO_P + 2, Tema.ESPACO_M,
                    Tema.ESPACO_P + 2, Tema.ESPACO_M));
            setAlignmentX(LEFT_ALIGNMENT);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        } else {
            setBorder(new EmptyBorder(Tema.ESPACO_P + 2, Tema.ESPACO_G,
                    Tema.ESPACO_P + 2, Tema.ESPACO_G));
        }

        atualizarCorTexto();

        // Liga e desliga o hover quando o mouse entra e sai do botão.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    /**
     * Destaca (ou não) o item ativo da sidebar. Só faz sentido no tipo MENU;
     * nos outros tipos não muda nada.
     */
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        atualizarCorTexto();
        repaint();
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    /** Cor do texto de acordo com o tipo (e, no MENU, se está selecionado). */
    private void atualizarCorTexto() {
        switch (tipo) {
            case SECUNDARIO:
                setForeground(Tema.TEXTO);
                break;
            case MENU:
                setForeground(selecionado ? Tema.TEXTO_CLARO : Tema.TEXTO_SIDEBAR);
                break;
            default: // PRIMARIO e PERIGO
                setForeground(Tema.TEXTO_CLARO);
        }
    }

    /** Cor do fundo de acordo com o tipo e o estado. null = não pinta fundo. */
    private Color corFundo() {
        if (!isEnabled()) {
            return Tema.BORDA;
        }
        switch (tipo) {
            case PRIMARIO:
                return hover ? Tema.PRIMARIA_HOVER : Tema.PRIMARIA;
            case PERIGO:
                return hover ? Tema.PERIGO_HOVER : Tema.PERIGO;
            case SECUNDARIO:
                return hover ? Tema.SUPERFICIE_HOVER : Tema.SUPERFICIE;
            default: // MENU: transparente, a não ser selecionado ou com hover
                if (selecionado) {
                    return Tema.PRIMARIA;
                }
                return hover ? Tema.SIDEBAR_HOVER : null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Trabalhamos numa cópia do Graphics para não bagunçar o original.
        Graphics2D g2 = (Graphics2D) g.create();
        Tema.antialias(g2);

        Color fundo = corFundo();
        if (fundo != null) {
            g2.setColor(fundo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), Tema.RAIO, Tema.RAIO);
        }

        // O secundário tem fundo branco, então ganha uma borda para aparecer.
        if (tipo == Tipo.SECUNDARIO && isEnabled()) {
            g2.setColor(Tema.BORDA);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, Tema.RAIO, Tema.RAIO);
        }

        g2.dispose();

        // Por último, o Swing desenha o texto por cima do nosso fundo.
        super.paintComponent(g);
    }
}
