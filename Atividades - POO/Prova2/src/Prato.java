import java.time.LocalDate;
import java.time.Month;

public class Prato extends Produto{
	private String ingredientes;
	private boolean vegetariano;
	
	public Prato(String ingredientes, boolean vegetariano, String nomeProduto, double valor) {
		super(nomeProduto, valor);
		this.ingredientes = ingredientes;
		this.vegetariano = vegetariano;
	}
	
	public void Prato() {
		
	}
	@Override
	public double getValor() {
		if(LocalDate.now().getMonth() == Month.MAY) {
			return super.getValor() + (super.getValor() * 0.10);
		}else {
			return super.getValor();
		}
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		if(ingredientes.length() > 2) {
			this.ingredientes = ingredientes;			
		}else {
			throw new IllegalArgumentException("Número de caracteres insuficiente");
		}
	}

	public boolean isVegetariano() {
		return vegetariano;
	}

	public void setVegetariano(boolean vegetariano) {
		this.vegetariano = vegetariano;
	}

	@Override
	public String toString() {
		return super.getNomeProduto() + ". Valor: " + getValor() 
		+ ". Ingredientes: " +  ingredientes + ".";
	}
}
