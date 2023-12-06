public class Comodo {
	
	private String descricao;
	private float largura;
	private float comprimento;
	private int qtPortas;
	private int qtJanelas;
	private float ventilacao;
	
	public Comodo(String descricao, float largura, float comprimento, int qtPortas, int qtJanelas, float ventilacao) {
		this.setDescricao(descricao);
		this.setLargura(largura);
		this.setComprimento(comprimento);
		this.setQtPortas(qtPortas);
		this.setQtJanelas(qtJanelas);
		this.setVentilacao(ventilacao);
	}
	
	public float getVentilacao() {
		return ventilacao;
	}

	public void setVentilacao(float ventilacao) {
		this.ventilacao = ventilacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if(descricao != null) {
			this.descricao = descricao;
		}else {
			throw new IllegalArgumentException("Descricao Inválida");
		}
	}

	public float getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public int getQtPortas() {
		return qtPortas;
	}

	public void setQtPortas(int qtPortas) {
		this.qtPortas = qtPortas;
	}

	public int getQtJanelas() {
		System.out.println(qtJanelas);
		return qtJanelas;
	}

	public void setQtJanelas(int qtJanelas) {
		this.qtJanelas = qtJanelas;
	}
	
	public float getAreaComodo() {
		System.out.println(largura * comprimento);
		return (largura * comprimento);
	}
	
	public float getindiceVentilacao() {
		if(getQtJanelas() == 0) {
			return 1;
		}else {
		return (getAreaComodo()/getQtJanelas())*0.08f;
		}
	}
}
