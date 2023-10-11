package backend.model;

public class Prato extends Produto {
	private double gramas;
	
	public Prato() {
		super();
		gramas = 50f;
	}
	
	public Prato(String nome, String desc, double valor, double gramas) {
		super(nome, desc, valor);
		setGramas(gramas);
	}

	public double getGramas() {
		return gramas;
	}

	public void setGramas(double gramas) {
		if (gramas <= 0) 
			throw new IllegalArgumentException("Valor em gramas deve ser positivo.");
		
		this.gramas = gramas;
	}

	@Override
	public String toString() {
		return "Prato" + super.toString() + ", getGramas()=" + getGramas() + "]";
		//return getNome() + ": " + gramas + " g";
	}

	@Override
	public String getClassName() {
		return "Prato";
	}
	
}
