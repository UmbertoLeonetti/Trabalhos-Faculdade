
public class Vinho extends Produto{
	private int safra;
	private String tipo;
	
	public Vinho(int safra, String tipo, String nomeProduto, double valor) {
		super(nomeProduto, valor);
		this.safra = safra;
		this.tipo = tipo;
	}

	public void Vinho() {
		
	}

	public int getSafra() {
		return safra;
	}

	public void setSafra(int safra) {
		if(safra > 1900) {
			this.safra = safra;			
		}else {
			throw new IllegalArgumentException("Safra abaixo do ano em estoque");
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return super.getNomeProduto() + ". Valor: R$" + super.getValor() 
		+ ". Safra de " + safra + ", " + tipo + ".";
	}
}
