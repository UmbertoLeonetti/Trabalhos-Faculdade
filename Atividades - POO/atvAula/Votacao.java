package atvAula;

import java.util.ArrayList;
import java.util.List;

public class Votacao {

	private List<Candidato> candidatos;
	private int votosNulos;

	public Votacao() {
		candidatos = new ArrayList<>();
		votosNulos = 0;
	}

	public void cadastrar(String nome, String chapa, int numeroIdentificacao) {
		Candidato candidato = new Candidato(nome, chapa, numeroIdentificacao);
		candidatos.add(candidato);
	}

	public void votar(int numeroIdentificacao) {
		boolean encontrado = false;
		for (Candidato candidato : candidatos) {
			if (candidato.getNumeroIdentificacao() == numeroIdentificacao) {
				candidato.votar();
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			votosNulos++;
		}
	}

	public Candidato getVencedor() {
		Candidato vencedor = null;
		for (Candidato candidato : candidatos) {
			if (vencedor == null || candidato.getQuantidadeVotos() > vencedor.getQuantidadeVotos()) {
				vencedor = candidato;
			}
			if(vencedor.getQuantidadeVotos() == 0) {
				return null;
			}
		}
		return vencedor;
	}

	public int getVotosNulos() {
		return votosNulos;
	}
}
