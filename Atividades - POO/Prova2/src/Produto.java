
public class Produto {
	private String nomeProduto;
	protected double valor;
	
	public Produto(String nomeProduto, double valor) {
		super();
		this.nomeProduto = nomeProduto;
		this.valor = valor;
	}
	public void Produto() {
		
	}
	public double getValor() {
		return valor;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public void setValor(double valor) {
		if(valor > 0) {
			this.valor = valor;			
		}else {
			throw new IllegalArgumentException("Valor deve ser maior que zero");
		}
	}
}
