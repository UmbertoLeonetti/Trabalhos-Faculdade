package backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import backend.controller.Controller;

public class Reserva implements Item {

	private String nomeCliente;
	private int mesa;
	private LocalDateTime dataHorario;
	
	public Reserva() {
		mesa = 1;
		nomeCliente = "";
		dataHorario = LocalDateTime.now();
	}
    
    public Reserva(String nomeCliente) {
    	setNome(nomeCliente);
		dataHorario = LocalDateTime.now();
    }


	public Reserva(String nomeCliente, LocalDateTime dataHorario) {
		setNome(nomeCliente);
		setDataHorario(dataHorario);
	}
	
	public Reserva(String nomeCliente, LocalDate data, LocalTime horario) {
		setNome(nomeCliente);
		setDataHorario(dataHorario);
	}

	public void setDataHorario(LocalDateTime dataHorario) throws IllegalArgumentException {
		
		if (dataHorario.isBefore(LocalDateTime.now().withSecond(0).withNano(0)))
			throw new IllegalArgumentException("Data ou horário não podem ser anteriores.");
		
		this.dataHorario = dataHorario;
		
	}
	
	public void setDataHorario(LocalDate data, LocalTime horario) {
		
		LocalDateTime dataHorario = LocalDateTime.of(data, horario);
		
		if (dataHorario.isBefore(LocalDateTime.now().withSecond(0).withNano(0)))
			throw new IllegalArgumentException("Data ou horário não podem ser anteriores.");
		
		this.dataHorario = dataHorario;
		
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
	
	@Override
	public void setNome(String nome) {
		if (nome.length() < 2)
			throw new IllegalArgumentException("Nome de cliente deve ter pelo menos 2 letras");
		
		this.nomeCliente = nome;
	}

	@Override
	public String getNome() {
		return nomeCliente;
	}
	
	@Override
	public String toString() {
		
		return "Reserva [getNome()=" + getNome() + ", getData()=" + getData() + ", getHorario()="
		+ getHorario() + "]";
		//return nomeCliente + ", " + getData() + " às " + getHorario();
	}
	
	@Override
	public String getClassName() {
		return "Reserva";
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
	
}
