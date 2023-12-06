
public class Reparo {
	private String descricao;
	private String medida;
	private int qtdProdutos;
	private float valor;
	
	public Reparo(String descricao, String medida, int qtdProdutos, float valor) {
		super();
		this.setDescricao(descricao);
		this.setMedida(medida);
		this.setQtdProdutos(qtdProdutos);
		this.setValor(valor);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		if(this.medida == "L" || this.medida == "P" || this.medida == "Q") {			
			this.medida = medida;
		}
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
