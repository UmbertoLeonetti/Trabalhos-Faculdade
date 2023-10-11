package backend.model;

public class Ingrediente implements Item {
	private String nome;
	private int quantidade;
	private float preco;

	public Ingrediente() {
		nome = "";
		quantidade = 0;
		preco = 0.00f;
	}
	
	public Ingrediente(String nome, float preco, int quantidade) {
		setNome(nome);
		setQuantidade(quantidade);
		setPreco(preco);
	}

	@Override
	public String toString() {
		
		//return nome + ", " + quantidade + " unidades";
		return "Ingrediente [getNome()=" + getNome() + ", getPreco()=" + getPreco() + ", getQuantidade()="
				+ getQuantidade() + "]";
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() < 2)
			throw new IllegalArgumentException("Nome de ingrediente deve ter pelo menos 2 letras");
		
		this.nome = nome;
	}
	
	@Override
	public String getClassName() {
		return "Ingrediente";
	}
}
