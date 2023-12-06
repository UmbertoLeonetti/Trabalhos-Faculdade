package atvAula;

public class Candidato {

	private String nome;
	private String chapa;
	private int numeroIdentificacao;
	private int quantidadeVotos;

	public Candidato(String nome, String chapa, int numeroIdentificacao) {
		this.nome = nome;
		this.chapa = chapa;
		this.numeroIdentificacao = numeroIdentificacao;
	}

	public String getNome() {
		return nome;
	}

	public String getChapa() {
		return chapa;
	}

	public int getNumeroIdentificacao() {
		return numeroIdentificacao;
	}

	public int getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void votar() {
		quantidadeVotos++;
	}
}