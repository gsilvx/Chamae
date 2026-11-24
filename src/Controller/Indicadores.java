package Controller;

import model.Atendimento;
import java.time.Duration;
import java.util.List;


public class Indicadores {

    public double calcularTempoMedioEspera(List<Atendimento> atendimentos){
        long tempoTotal = 0;
        int quantidade = 0;

        for(Atendimento atendimento : atendimentos){
            if(atendimento.getInicio() != null){
                long espera = Duration.between(
                        //Ajustar esse get na implementacao
                        atendimento.getSenha().getHorarioChegada(),
                        atendimento.getInicio()
                ).toMinutes();

                tempoTotal += espera;
                quantidade++;
            }
        }
        if(quantidade == 0 ) {
            return 0;
        }
        return (double) tempoTotal / quantidade;
    }

    public double calcularDuracaoMedia(List<Atendimento> atendimentos){
       long total = 0;
       int quantidade = 0;

       for(Atendimento atendimento : atendimentos){
           if(atendimento.getFim() != null) {
               total += atendimento.calcularDuracao();
               quantidade++;
           }
       }
       if (quantidade == 0){
           return 0;
       }
       return (double) total / quantidade;
    }

    public int contarAtendimentos(List<Atendimento> atendimentos){
        return atendimentos.size();
    }

    public double calcularTaxaConclusao(List<Atendimento> atendimentos){

        int concluidos = 0;

        if(atendimentos.isEmpty()){
            return 0;
        }

        for(Atendimento atendimento : atendimentos  ){

            if(atendimento.getFim() != null){
                concluidos++;
            }
        }
        return (double) concluidos / atendimentos.size() * 100;
    }
}
