
public class Bebida extends Produto{
	private boolean alcoolico;
	
	public Bebida(boolean alcoolico, String nomeProduto, double valor) {
		super(nomeProduto, valor);
		this.alcoolico = alcoolico;
	}

	public void Bebida() {
		
	}

	public boolean isAlcoolico() {
		return alcoolico;
	}

	public void setAlcoolico(boolean alcoolico) {
		this.alcoolico = alcoolico;
	}

	@Override
	public String toString() {
		String resposta = "";
		if(alcoolico) {
			resposta = "SIM";
		}else {
			resposta = "NÃO";
		}
		return super.getNomeProduto() + " Valor: R$" + super.getValor() + " "
		+ ". Possui álcool: " + resposta + ".";
	}
}
