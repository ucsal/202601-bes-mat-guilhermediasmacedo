package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.BancoDeDados;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Repositorio;

public class GerenciadorProva {

    private static final Scanner in = new Scanner(System.in);
    private static final Repositorio repositorio = new BancoDeDados();

    public static void cadastrarProva() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        var prova = new Prova();
        prova.setId(BancoDeDados.proximaProvaId++);
        prova.setTitulo(titulo);

        repositorio.salvar(prova);
        System.out.println("Prova criada: " + prova.getId());
    }

    public static Long escolherProva() {
        System.out.println("\nProvas:");
        for (var p : BancoDeDados.provas) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        System.out.print("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = BancoDeDados.provas.stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }
}
