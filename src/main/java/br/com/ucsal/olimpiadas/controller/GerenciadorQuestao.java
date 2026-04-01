package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.BancoDeDados;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.QuestaoMultiplaEscolha;
import br.com.ucsal.olimpiadas.model.Repositorio;

public class GerenciadorQuestao {

    private static final Scanner in = new Scanner(System.in);
    private static final Repositorio repositorio = new BancoDeDados();

    public static void cadastrarQuestao() {
        if (BancoDeDados.provas.isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }

        var provaId = GerenciadorProva.escolherProva();
        if (provaId == null) {
            return;
        }

        System.out.println("Enunciado:");
        var enunciado = in.nextLine();

        var alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        var q = new QuestaoMultiplaEscolha();
        q.setId(BancoDeDados.proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        repositorio.salvar(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}

