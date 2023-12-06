import java.util.Random;

public class Luta {
	private Lutador desafiado;
	private Lutador desafiante;
	private int rounds;
	private boolean aprovada;
	
	public void marcarLuta(Lutador l1, Lutador l2) {
		if(l1.getCatergoria() == l2.getCatergoria() && l1 != l2) {
			aprovada= true;
			desafiado = l1;
			desafiante = l2;
		} else {
			aprovada = false;
			desafiado = null;
			desafiante = null;
		}
	}
	
	public void lutar() {
		if(aprovada) {
			System.out.println("###DESAFIADO###");
			desafiado.apresentar();
			System.out.println("###DESAFIANTE###");
			desafiante.apresentar();
			
			Random aleatorio = new Random();
			int vencedor =  aleatorio.nextInt(3);
			System.out.println("====== RESULTADO DA LUTA =======");
			switch(vencedor){
			case 0:
				System.out.println("Empatou");
				desafiado.empatarLuta();
				desafiante.empatarLuta();
				break;
			case 1:
				System.out.println("Vitória do " + desafiado.getNome());
				desafiado.ganharLuta();
				desafiante.perderLuta();
				break;
			case 2:
				System.out.println("Vitória do " + desafiante.getNome());
				desafiado.perderLuta();
				desafiante.ganharLuta();
				break;
			}
			System.out.println("=========================");
		} else {
			System.out.println("A luta não pode acontecer!");
		}
	}

	public Lutador getDesafiado() {
		return desafiado;
	}

	public void setDesafiado(Lutador desafiado) {
		this.desafiado = desafiado;
	}

	public Lutador getDesafiante() {
		return desafiante;
	}

	public void setDesafiante(Lutador desafiante) {
		this.desafiante = desafiante;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public boolean isAprovada() {
		return aprovada;
	}

	public void setAprovada(boolean aprovada) {
		this.aprovada = aprovada;
	}
	
	
}
