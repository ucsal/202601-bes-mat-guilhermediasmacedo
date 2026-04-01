package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;
import br.com.ucsal.olimpiadas.model.*;

public class GerenciadorTentativa {

	private static final Scanner in = new Scanner(System.in);

	public static void aplicarProva() {
		if (BancoDeDados.participantes.isEmpty()) {
			System.out.println("cadastre participantes primeiro");
			return;
		}
		if (BancoDeDados.provas.isEmpty()) {
			System.out.println("cadastre provas primeiro");
			return;
		}

		var participanteId = GerenciadorParticipante.escolherParticipante();
		if (participanteId == null)
			return;

		var provaId = GerenciadorProva.escolherProva();
		if (provaId == null)
			return;

		var questoesDaProva = BancoDeDados.questoes.stream()
				.filter(q -> q.getProvaId() == provaId)
				.toList();

		if (questoesDaProva.isEmpty()) {
			System.out.println("esta prova não possui questões cadastradas");
			return;
		}

		var tentativa = new Tentativa();
		tentativa.setId(BancoDeDados.proximaTentativaId++);
		tentativa.setParticipanteId(participanteId);
		tentativa.setProvaId(provaId);

		System.out.println("\n--- Início da Prova ---");

		for (var q : questoesDaProva) {
			System.out.println("\nQuestão #" + q.getId());
			System.out.println(q.getEnunciado());

			if (q instanceof QuestaoMultiplaEscolha qm) {
				System.out.println("Posição inicial:");
				imprimirTabuleiroFen(qm.getFenInicial());

				for (var alt : qm.getAlternativas()) {
					System.out.println(alt);
				}
			}

			System.out.print("Sua resposta (A–E): ");
			char marcada;
			try {
				marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
			} catch (Exception e) {
				System.out.println("resposta inválida (marcando como errada)");
				marcada = 'X';
			}

			var r = new Resposta();
			r.setQuestaoId(q.getId());
			r.setAlternativaMarcada(marcada);
			r.setCorreta(q.isRespostaCorreta(marcada));

			tentativa.getRespostas().add(r);
		}

		BancoDeDados.tentativas.add(tentativa);

		int nota = calcularNota(tentativa);
		System.out.println("\n--- Fim da Prova ---");
		System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
	}

	public static int calcularNota(Tentativa tentativa) {
		int acertos = 0;
		for (var r : tentativa.getRespostas()) {
			if (r.isCorreta())
				acertos++;
		}
		return acertos;
	}

	public static void listarTentativas() {
		System.out.println("\n--- Tentativas ---");
		for (var t : BancoDeDados.tentativas) {
			System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
					t.getId(),
					t.getParticipanteId(),
					t.getProvaId(),
					calcularNota(t),
					t.getRespostas().size());
		}
	}

	public static void imprimirTabuleiroFen(String fen) {
		if (fen == null || fen.isBlank())
			return;

		String[] partes = fen.split(" ");
		String parteTabuleiro = partes[0];
		String[] ranks = parteTabuleiro.split("/");

		System.out.println();
		System.out.println("    a b c d e f g h");
		System.out.println("    -----------------");

		for (int r = 0; r < 8; r++) {
			String rank = ranks[r];
			System.out.print((8 - r) + " | ");
			for (char c : rank.toCharArray()) {
				if (Character.isDigit(c)) {
					int vazios = c - '0';
					for (int i = 0; i < vazios; i++) {
						System.out.print(". ");
					}
				} else {
					System.out.print(c + " ");
				}
			}
			System.out.println("| " + (8 - r));
		}

		System.out.println("    -----------------");
		System.out.println("    a b c d e f g h");
		System.out.println();
	}
}
