package view;

/**
 * Contrato que toda tela do CardLayout do MenuPrincipal segue.
 *
 * Assim o menu consegue tratar todas as telas do mesmo jeito: quando o
 * usuário abre uma tela, o menu chama atualizar() e mostra getTitulo() no topo.
 */
public interface Tela {

    /** Relê os dados do DadosMock e redesenha a tela. */
    void atualizar();

    /** Título mostrado no topo da janela quando esta tela está aberta. */
    String getTitulo();
}
