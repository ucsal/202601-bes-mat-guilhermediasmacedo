package br.com.ucsal.olimpiadas.view;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.controller.GerenciadorParticipante;
import br.com.ucsal.olimpiadas.controller.GerenciadorProva;
import br.com.ucsal.olimpiadas.controller.GerenciadorQuestao;
import br.com.ucsal.olimpiadas.controller.GerenciadorTentativa;

public class Menu {

	private static final Scanner in = new Scanner(System.in);

	public static void iniciar() {
		while (true) {
			System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
			System.out.println("1) Cadastrar participante");
			System.out.println("2) Cadastrar prova");
			System.out.println("3) Cadastrar questão (A–E) em uma prova");
			System.out.println("4) Aplicar prova (selecionar participante + prova)");
			System.out.println("5) Listar tentativas (resumo)");
			System.out.println("0) Sair");
			System.out.print("> ");

			switch (in.nextLine()) {
			case "1" -> GerenciadorParticipante.cadastrarParticipante();
			case "2" -> GerenciadorProva.cadastrarProva();
			case "3" -> GerenciadorQuestao.cadastrarQuestao();
			case "4" -> GerenciadorTentativa.aplicarProva();
			case "5" -> GerenciadorTentativa.listarTentativas();
			case "0" -> {
				System.out.println("Saindo...");
				return;
			}
			default -> System.out.println("Opção inválida!");
			}
		}
	}
}
