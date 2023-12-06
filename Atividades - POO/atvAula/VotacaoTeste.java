package atvAula;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VotacaoTeste {

	@Test
	public void votacao_chapa01() {
		Votacao votacao = new Votacao();
		votacao.cadastrar("Gabriel", "chapa01", 9);
		votacao.cadastrar("João", "chapa02", 3);
		votacao.cadastrar("Susana", "chapa03", 5);

		votacao.votar(9);
		votacao.votar(2);
		votacao.votar(9);
		votacao.votar(3);
		votacao.votar(9);
		votacao.votar(3);
		votacao.votar(2);

		Candidato vencedor = votacao.getVencedor();
		assertEquals("Gabriel", vencedor.getNome());
		assertEquals(3, vencedor.getQuantidadeVotos());

		assertEquals(2, votacao.getVotosNulos());
	}

	@Test
	public void votacao_sem_vencedor() {
		Votacao votacao = new Votacao();
		votacao.cadastrar("Gabriel", "chapa01", 9);

		assertNull(votacao.getVencedor());
	}
}

