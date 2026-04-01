package br.com.ucsal.olimpiadas.model;

import java.util.Arrays;

public class QuestaoMultiplaEscolha extends Questao implements QuestaoComTabuleiro {
    
    private String[] alternativas;
    private char alternativaCorreta;
    private String fenInicial;

    @Override
    public boolean isRespostaCorreta(Object resposta) {
        if (resposta instanceof Character) {
            return Questao.normalizar((char) resposta) == alternativaCorreta;
        }
        return false;
    }

    @Override
    public void setAlternativas(String[] alternativas) {
        if (alternativas == null || alternativas.length != 5) {
            throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
        }
        this.alternativas = Arrays.copyOf(alternativas, 5);
    }

    @Override
    public void setAlternativaCorreta(char alternativaCorreta) {
        char c = Questao.normalizar(alternativaCorreta); // 

        if (c < 'A' || c > 'E') {
            throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
        }

        this.alternativaCorreta = c;
    }

    @Override
    public String[] getAlternativas() { 
        return alternativas; 
    }
    
    public char getAlternativaCorreta() { 
        return alternativaCorreta; 
    }
    
    @Override
    public String getFenInicial() { 
        return fenInicial; 
    }
    
    @Override
    public void setFenInicial(String fenInicial) { 
        this.fenInicial = fenInicial; 
    }
}
