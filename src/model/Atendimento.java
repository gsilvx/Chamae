package model;


import java.time.Duration;
import java.time.LocalDateTime;

public class Atendimento {
    //tirar esse ID futuramente
    private int id;
    private LocalDateTime inicio, fim;
    private long duracao;
    private Senha senha;


    public Atendimento(int id, Senha senha) {
        this.id = id;
        this.senha = senha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Senha getSenha() {
        return senha;
    }

    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public void iniciar(){
        if(inicio !=null){
            throw new IllegalArgumentException(" Atendimento ja Iniciado!");
        }
        this.inicio = LocalDateTime.now();
    }

    public void finalizar(){
        if(inicio == null){
            throw new IllegalArgumentException("Atendimento nao iniciado!");
        }

        if(fim != null){
            throw new IllegalArgumentException("Atendimento ja finalizado!");
        }

        this.fim = LocalDateTime.now();
        this.duracao = calcularDuracao();
    }
    public long calcularDuracao(){
        if(inicio == null || fim == null){
            return 0;
        }
        return Duration.between(inicio , fim).toMinutes();
    }
}
