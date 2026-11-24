package model;

import enums.StatusAtendente;

public class Atendente {
    private String nome;
    private int id;
    private StatusAtendente statusAtendente;

    public Atendente(String nome, int id, StatusAtendente statusAtendente) {
        this.nome = nome;
        this.id = id;
        this.statusAtendente = statusAtendente.DISPONIVEL;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome nao pode estar vazio ou nulo");
        }
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Id nao pode estar vazio");
        }
        this.id = id;
    }

    public StatusAtendente getStatusAtendente() {
        return statusAtendente;
    }

    private void setStatusAtendente(StatusAtendente statusAtendente) {
        this.statusAtendente = statusAtendente;
    }

    public Atendimento iniciarAtendimento(Senha senha) {
        if(statusAtendente == statusAtendente.EM_ATENDIMENTO){
            throw new IllegalArgumentException("Atendente ocupado");
        }

        //ARRUMAR ID DEPOIS NA PARTE DE CRIAR SENHA E ATENDIMENTO
        Atendimento atendimento = new Atendimento(1, senha);

        atendimento.iniciar();

        statusAtendente = StatusAtendente.EM_ATENDIMENTO;

        return atendimento;
    }

    public void finalizarAtendimento( Atendimento atendimento){
        if (statusAtendente != StatusAtendente.EM_ATENDIMENTO){
            throw new IllegalArgumentException("Atendente nao esta ocupado!");
        }

        atendimento.finalizar();

        statusAtendente = StatusAtendente.DISPONIVEL;
    }
}
