package backend;

import java.io.Serializable;

import backend.controller.Controller;
import backend.controller.FuncionarioManager;
import backend.model.Bebida;
import backend.model.Funcionario;
import backend.model.Ingrediente;
import backend.model.Pedido;
import backend.model.Prato;
import backend.model.Produto;
import backend.model.Reserva;

public class Restaurante implements Serializable {
	private static final long serialVersionUID = -9013641685562420877L;
	public Controller<Ingrediente>	ingredientes;
	public Controller<Prato>	 	pratos;
	public Controller<Produto>	 	cardapio;
	public Controller<Reserva>		reservas;
	public Controller<Pedido>		pedidos;
	public Controller<Bebida>		bebidas;
	public Controller<Funcionario> funcionarios;
	
	public Restaurante() {
		ingredientes	= new Controller<Ingrediente>();
		pratos 			= new Controller<Prato>();
		bebidas			= new Controller<Bebida>();
		reservas 		= new Controller<Reserva>();
		pedidos			= new Controller<Pedido>();
		cardapio		= new Controller<Produto>();
		funcionarios 	= new Controller<Funcionario>();
	}
}
