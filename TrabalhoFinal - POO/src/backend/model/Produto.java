package backend.model;

import backend.controller.Controller;;

public abstract class Produto implements Item{
	private String nome;
	private String desc;
	private double valor;
	private Controller<Ingrediente> ingredientes;
	
	public Produto() {
		nome = "";
		ingredientes = new Controller<Ingrediente>();
		desc = "";
		valor = 0.00f;
	}
	
	public Produto(String nome, String desc, double valor) {
		setNome(nome);
		ingredientes = new Controller<Ingrediente>();
		setDesc(desc);
		setValor(valor);
	}
	
	public double calculaValor() {
		return valor;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Controller<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Controller<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return " [getNome()=" + getNome() + ", getDesc()=" + getDesc() + ", getValor()=" + getValor() +
			   ", getIngredientes()=" + getIngredientes();
	}

	public void setNome(String nome) {
		if (nome.length() < 2)
			throw new IllegalArgumentException("Nome de produto deve ter pelo menos 2 letras");
		
		this.nome = nome;		
	}
	
	public void addIngrediente(Ingrediente i) {
		ingredientes.add(i);
	}
	
	public void removeIngrediente(String nome) {
		ingredientes.remove(nome);
	}

	
}
