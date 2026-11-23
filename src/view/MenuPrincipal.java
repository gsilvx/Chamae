package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import view.componentes.BotaoArredondado;
import view.componentes.Tema;
import view.telas.AtendentesTela;
import view.telas.EmitirSenhaTela;
import view.telas.FilaTela;
import view.telas.IndicadoresTela;
import view.telas.InicioTela;
import view.telas.PainelChamadaTela;
import view.telas.ServicosTela;
import view.telas.SimulacaoTela;

/**
 * A janela principal do Chamaê.
 *
 * - Esquerda: a sidebar com o logo e o menu.
 * - Topo: o título da tela aberta e um relógio.
 * - Centro: um CardLayout, que mostra uma tela por vez.
 */
public class MenuPrincipal extends JFrame {

    // Nomes das telas. Use em irPara(...), ex.: irPara(MenuPrincipal.FILA).
    public static final String INICIO = "Início";
    public static final String EMITIR_SENHA = "Emitir senha";
    public static final String FILA = "Fila";
    public static final String ATENDENTES = "Atendentes";
    public static final String SERVICOS = "Serviços";
    public static final String INDICADORES = "Indicadores";
    public static final String SIMULACAO = "Simulação";

    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    // O CardLayout funciona como um baralho: todas as telas ficam no
    // painel "centro", mas só a carta de cima aparece.
    private final CardLayout cartoes = new CardLayout();
    private final JPanel centro = new JPanel(cartoes);

    // LinkedHashMap guarda na ordem de inserção: é a ordem do menu.
    private final Map<String, Tela> telas = new LinkedHashMap<>();
    private final Map<String, BotaoArredondado> itensMenu = new LinkedHashMap<>();

    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblRelogio = new JLabel();

    // Guardamos o painel da TV para nunca abrir duas janelas dele.
    private PainelChamadaTela painelChamada;

    public MenuPrincipal() {
        super("Chamaê");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 760);
        setLocationRelativeTo(null); // centraliza na tela
        setLayout(new BorderLayout());

        registrar(INICIO, new InicioTela());
        registrar(EMITIR_SENHA, new EmitirSenhaTela());
        registrar(FILA, new FilaTela());
        registrar(ATENDENTES, new AtendentesTela());
        registrar(SERVICOS, new ServicosTela());
        registrar(INDICADORES, new IndicadoresTela());
        registrar(SIMULACAO, new SimulacaoTela());

        JPanel direita = new JPanel(new BorderLayout());
        direita.add(criarTopo(), BorderLayout.NORTH);
        direita.add(centro, BorderLayout.CENTER);

        add(criarSidebar(), BorderLayout.WEST);
        add(direita, BorderLayout.CENTER);

        iniciarRelogio();
        irPara(INICIO);
    }

    /** Coloca uma tela no CardLayout com o nome dado. */
    private void registrar(String nome, Tela tela) {
        telas.put(nome, tela);
        // Toda tela é um JPanel; o cast é para poder adicioná-la ao painel.
        centro.add((JComponent) tela, nome);
    }

    /**
     * Mostra a tela com esse nome: destaca o item do menu, troca a carta do
     * CardLayout, muda o título do topo e pede para a tela reler os dados.
     */
    public void irPara(String nome) {
        Tela tela = telas.get(nome);
        if (tela == null) {
            return; // nome que não existe: não faz nada
        }
        for (Map.Entry<String, BotaoArredondado> item : itensMenu.entrySet()) {
            item.getValue().setSelecionado(item.getKey().equals(nome));
        }
        cartoes.show(centro, nome);
        lblTitulo.setText(tela.getTitulo());
        tela.atualizar();
    }

    private JPanel criarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Tema.FUNDO_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(Tema.margem(Tema.ESPACO_P));

        JLabel logo = new JLabel("Chamaê");
        logo.setFont(Tema.TITULO);
        logo.setForeground(Tema.TEXTO_CLARO);
        logo.setBorder(new EmptyBorder(Tema.ESPACO_M, Tema.ESPACO_M, 0, 0));
        logo.setAlignmentX(LEFT_ALIGNMENT);

        JLabel slogan = new JLabel("Sua vez, sem complicação.");
        slogan.setFont(Tema.PEQUENA);
        slogan.setForeground(Tema.TEXTO_SIDEBAR);
        slogan.setBorder(new EmptyBorder(2, Tema.ESPACO_M, Tema.ESPACO_G, 0));
        slogan.setAlignmentX(LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(slogan);

        // Um item de menu para cada tela registrada, na mesma ordem.
        for (String nome : telas.keySet()) {
            BotaoArredondado item = new BotaoArredondado(nome, BotaoArredondado.Tipo.MENU);
            item.addActionListener(e -> irPara(nome));
            itensMenu.put(nome, item);
            sidebar.add(item);
            sidebar.add(Box.createVerticalStrut(2));
        }

        JSeparator separador = new JSeparator();
        separador.setForeground(Tema.SIDEBAR_HOVER);
        separador.setBackground(Tema.FUNDO_SIDEBAR);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separador.setAlignmentX(LEFT_ALIGNMENT);

        BotaoArredondado abrirPainel =
                new BotaoArredondado("Abrir painel", BotaoArredondado.Tipo.MENU);
        abrirPainel.addActionListener(e -> abrirPainelChamada());

        sidebar.add(Box.createVerticalStrut(Tema.ESPACO_P));
        sidebar.add(separador);
        sidebar.add(Box.createVerticalStrut(Tema.ESPACO_P));
        sidebar.add(abrirPainel);
        sidebar.add(Box.createVerticalGlue()); // empurra o resto para cima

        return sidebar;
    }

    private JPanel criarTopo() {
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Tema.SUPERFICIE);
        // Linha fina embaixo + espaço interno.
        topo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Tema.BORDA),
                new EmptyBorder(Tema.ESPACO_M, Tema.ESPACO_G, Tema.ESPACO_M, Tema.ESPACO_G)));

        lblTitulo.setFont(Tema.TITULO);
        lblTitulo.setForeground(Tema.TEXTO);

        lblRelogio.setFont(Tema.SUBTITULO);
        lblRelogio.setForeground(Tema.TEXTO_SECUNDARIO);

        topo.add(lblTitulo, BorderLayout.WEST);
        topo.add(lblRelogio, BorderLayout.EAST);
        return topo;
    }

    /** Atualiza o relógio do topo a cada 1 segundo. */
    private void iniciarRelogio() {
        lblRelogio.setText(LocalTime.now().format(FORMATO_HORA));
        // javax.swing.Timer roda a ação na thread da interface, então pode
        // mexer no JLabel sem problema (o java.util.Timer não pode).
        Timer relogio = new Timer(1000,
                e -> lblRelogio.setText(LocalTime.now().format(FORMATO_HORA)));
        relogio.start();
    }

    /** Abre o painel da TV, ou traz para a frente se ele já estiver aberto. */
    private void abrirPainelChamada() {
        // isDisplayable() fica false depois que a janela é fechada.
        if (painelChamada == null || !painelChamada.isDisplayable()) {
            painelChamada = new PainelChamadaTela();
            painelChamada.setVisible(true);
        } else {
            painelChamada.toFront();
        }
    }
}
