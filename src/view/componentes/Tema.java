package view.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.border.EmptyBorder;

/**
 * Identidade visual do Chamaê: todas as cores, fontes e medidas ficam aqui.
 *
 * Regra do time: nenhuma outra classe faz "new Color(...)" ou "new Font(...)".
 * Quem precisar de uma cor ou fonte nova ACRESCENTA uma constante aqui,
 * sem alterar as que já existem.
 */
public final class Tema {

    // Ninguém precisa criar um objeto Tema: tudo aqui é static.
    private Tema() {
    }

    // ===================== CORES =====================

    /** Cor principal da marca: botões principais e destaques. */
    public static final Color PRIMARIA = new Color(0x2563EB);
    /** Fundo da área de conteúdo das telas. */
    public static final Color FUNDO = new Color(0xF3F5F9);
    /** Fundo da barra lateral (escura). */
    public static final Color FUNDO_SIDEBAR = new Color(0x1E293B);
    /** Fundo de cards, tabelas e formulários (o "papel" sobre o FUNDO). */
    public static final Color SUPERFICIE = Color.WHITE;
    /** Texto principal. */
    public static final Color TEXTO = new Color(0x1F2937);
    /** Texto de apoio: legendas, rótulos e informações menos importantes. */
    public static final Color TEXTO_SECUNDARIO = new Color(0x6B7280);
    /** Linhas e contornos discretos. */
    public static final Color BORDA = new Color(0xE2E8F0);
    /** Verde: disponível, concluído, deu certo. */
    public static final Color SUCESSO = new Color(0x16A34A);
    /** Laranja: atendimento prioritário. */
    public static final Color PRIORITARIO = new Color(0xEA580C);
    /** Vermelho: erros, cancelar, excluir. */
    public static final Color PERIGO = new Color(0xDC2626);
    /** Azul claro: informações e "em atendimento". */
    public static final Color INFO = new Color(0x0EA5E9);

    // Cores extras usadas pelo BotaoArredondado (hover e sidebar).
    /** Texto claro, usado sobre fundos escuros ou coloridos. */
    public static final Color TEXTO_CLARO = Color.WHITE;
    /** PRIMARIA um pouco mais escura, para o hover. */
    public static final Color PRIMARIA_HOVER = new Color(0x1D4ED8);
    /** PERIGO um pouco mais escuro, para o hover. */
    public static final Color PERIGO_HOVER = new Color(0xB91C1C);
    /** SUPERFICIE levemente acinzentada, para o hover do botão secundário. */
    public static final Color SUPERFICIE_HOVER = new Color(0xF1F5F9);
    /** Texto dos itens da sidebar. */
    public static final Color TEXTO_SIDEBAR = new Color(0xCBD5E1);
    /** Fundo de um item da sidebar com o mouse em cima. */
    public static final Color SIDEBAR_HOVER = new Color(0x334155);

    // ===================== FONTES =====================

    // "Segoe UI" é a fonte do Windows. Se o computador não tiver,
    // o Java troca sozinho por uma fonte padrão.
    private static final String FAMILIA = "Segoe UI";

    public static final Font TITULO = new Font(FAMILIA, Font.BOLD, 20);
    public static final Font SUBTITULO = new Font(FAMILIA, Font.PLAIN, 16);
    /** Texto comum. Chama FONTE_TEXTO porque o nome TEXTO já é da cor. */
    public static final Font FONTE_TEXTO = new Font(FAMILIA, Font.PLAIN, 14);
    public static final Font PEQUENA = new Font(FAMILIA, Font.PLAIN, 12);
    /** Número da senha nos cards (ex.: "P001"). */
    public static final Font SENHA_GRANDE = new Font(FAMILIA, Font.BOLD, 48);
    /** Número da senha no painel da TV. */
    public static final Font SENHA_PAINEL = new Font(FAMILIA, Font.BOLD, 120);

    // ===================== MEDIDAS =====================

    /** Arredondamento dos cantos (usado no fillRoundRect). */
    public static final int RAIO = 12;
    /** Espaçamento pequeno. */
    public static final int ESPACO_P = 8;
    /** Espaçamento médio. */
    public static final int ESPACO_M = 16;
    /** Espaçamento grande. */
    public static final int ESPACO_G = 24;

    // ===================== UTILITÁRIOS =====================

    /**
     * Liga a suavização (antialiasing) para formas e textos não ficarem
     * "serrilhados". Chame no começo de todo paintComponent.
     */
    public static void antialias(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /** Uma borda vazia com o mesmo espaço nos quatro lados. Ex.: margem(ESPACO_M). */
    public static EmptyBorder margem(int espaco) {
        return new EmptyBorder(espaco, espaco, espaco, espaco);
    }
}
