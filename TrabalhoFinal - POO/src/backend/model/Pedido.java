package backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import backend.controller.Controller;

public class Pedido implements Item {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private int mesa;
	LocalDateTime dataHorario;
	private Controller<Produto> produtos;
	
	public Pedido() {
		nomeCliente = "";
		mesa = 1;
		dataHorario = LocalDateTime.now().withSecond(0).withNano(0);
		produtos = new Controller<Produto>();
	}

	public Pedido(String nomeCliente, int mesa) {
		setNome(nomeCliente);
		setMesa(mesa);
		produtos = new Controller<Produto>();
		this.dataHorario = LocalDateTime.now();
	}
	
	public Controller<Produto> getProdutos(){
		return produtos;
	}

	@Override
	public String getNome() {
		return nomeCliente;
	}
	
	@Override
	public void setNome(String nome) {
		if (nome.length() < 2)
			throw new IllegalArgumentException("Nome de cliente deve ter pelo menos 2 letras");
		
		this.nomeCliente = nome;
	}
	
	public int getMesa() {
		return mesa;
	}

	public void setMesa(int mesa) {
		if (mesa <= 0) {
			throw new IllegalArgumentException("Número de mesa deve ser positivo");
		}
		this.mesa = mesa;
	}
	
	public LocalDate getData() {
		return dataHorario.toLocalDate();
	}

	public LocalTime getHorario() {
		return dataHorario.toLocalTime();
	}

	public LocalDateTime getDataHorario() {
		return dataHorario;
	}

	public void setDataHorario(LocalDateTime dataHorario) {
		this.dataHorario = dataHorario;
	}

	public void setProdutos(Controller<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String getClassName() {
		return "Pedido";
	}
	
	
}
