package br.com.ucsal.olimpiadas.model;

public abstract class Questao {
	protected long id;
	protected long provaId;
	protected String enunciado;

	public abstract boolean isRespostaCorreta(Object resposta);

	public abstract String[] getAlternativas();

	public abstract void setAlternativas(String[] alternativas);

	public abstract void setAlternativaCorreta(char correta);

	public abstract String getFenInicial();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProvaId() {
		return provaId;
	}

	public void setProvaId(long provaId) {
		this.provaId = provaId;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public static char normalizar(char c) {
		char up = Character.toUpperCase(c);
		if (up < 'A' || up > 'E') {
			throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
		}
		return up;
	}
}
