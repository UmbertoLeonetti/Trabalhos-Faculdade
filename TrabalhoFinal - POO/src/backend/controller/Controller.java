package backend.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import backend.model.Item;
import backend.model.Pedido;
import backend.model.Prato;
import backend.model.Reserva;

public class Controller<T extends Item> implements Serializable {
	private ArrayList<T> list;
	
	public Controller(){
		list = new ArrayList<T>();
	}
	
	@SuppressWarnings("unchecked")
	public <S extends Item> Controller<S> geInstanciasDe(S classe){
		Controller<S> control = new Controller<S>();
		
		for (T t : list)
			if(t.getClass() == classe.getClass())
				control.add((S) t);
		
		if(control.list.isEmpty())
			return null;
		
		return control;				
	}
	
	private String defaultName(T obj) {
		
		String className = obj.getClassName();
		
		
		String defaultName = "";
		
		int n = 1;
		boolean hasName = false;	
		do {
			
			hasName = false;
			defaultName = className + " " + n;
			for (T t : list) {
				
				if (t.getNome().equals(defaultName)) {
					hasName = true;
					n++;
					break;
				}
			}
		} while (hasName); 
		return defaultName;
	}
	
	public ArrayList<T> getLista() {
		return list;
	}
	
	public int getTamanho() {
		return list.size();
	}
	
	public T get(int index) {
		return list.get(index);
	}
	
	public T get(String nome) {
		if(nome == null)
			return null;
		
		for (T obj : list) {
			if (obj.getNome().compareTo(nome) == 0)
				return obj;
		}

		return null;
	}
	
	public void add(T obj) throws IllegalArgumentException {
		
		if(obj.getNome().equals("")) {
			obj.setNome(defaultName(obj));
			list.add(obj);
			return;
		}
		
		if(get(obj.getNome()) == null || obj instanceof Reserva || obj instanceof Pedido)
			list.add(obj);
		else
			throw new IllegalArgumentException(obj.getClassName() + " com nome '" + obj.getNome() + "' já existe");
	}
	
	public void add(T obj, int index) {
		if(get(obj.getNome()) == null)
			list.add(index, obj);
		else
			throw new IllegalArgumentException("Item com nome '" + obj.getNome() + "' já existe");
	}
	
	public ArrayList<String> getNomes(){
		ArrayList<String> str = new ArrayList<String>();

		for (T obj : list)
			str.add(obj.getNome());

		return str;
	}
	
	public String[] getNomesArray() {
		return getNomes().toArray(new String[getNomes().size()]);
	}
	
	public ArrayList<Pedido> getPedidosData(LocalDate inicio, LocalDate fim) {
		
		if (list.size() == 0) 
			return null;
		
		
		if (!(list.get(0) instanceof Pedido)) 
			return null;
		
		if (inicio.isAfter(fim))
			throw new IllegalArgumentException("Intervalo inválido.");
		
		ArrayList<Pedido> pedidoList = new ArrayList<Pedido>();
		
		
		
		for (T obj : list) {
			
			Pedido pedido = (Pedido) obj;
			LocalDate data = pedido.getData();
			
			if(!(data.isBefore(inicio) || data.isAfter(fim))) 
				pedidoList.add(pedido);
		}
		return pedidoList;
	}
	
	
	public ArrayList<Reserva> getReservasData(LocalDate inicio, LocalDate fim) {
		
		if (list.size() == 0) 
			return null;
		
		if (!(list.get(0) instanceof Reserva)) 
			return null;
		
		if (inicio.isAfter(fim))
			throw new IllegalArgumentException("Intervalo inválido.");
		
		ArrayList<Reserva> reservaList = new ArrayList<Reserva>();
		
		for (T obj : list) {
			
			Reserva reserva = (Reserva) obj;
			LocalDate data = reserva.getData();
			
			if(!(data.isBefore(inicio) || data.isAfter(fim))) 
				reservaList.add(reserva);
		}
		return reservaList;
	}
	
	public void remove(String nome) {
		list.remove(get(nome));
	}
	
	public void remove(int index) {
		list.remove(index);
	}
	
	public void clear() {
		list.clear();
	}
	
	@Override
	public String toString() {
		
		String itens = "{";
		if (list.size() == 0) return null;
		
		for (T obj : list) {
			
			itens += obj.getNome() + ", ";
		}
		
		itens = itens.substring(0, itens.length() - 2) + '}';
		return itens;
		
	}
}
