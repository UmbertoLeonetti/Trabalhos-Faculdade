package backend.model;

public class Bebida extends Produto {
	
	private double mL;
	private boolean alcoolica;
	
	 public Bebida() {
		 super();
		 mL = 50;
	}
	
	public Bebida(String nome, String desc, double valor, double mL, boolean alcoolica) {
		
		super(nome, desc, valor);
		setmL(mL);
		this.alcoolica = alcoolica;
	}
	
	public void setmL(double mL) {
		if (mL <= 0) {
			throw new IllegalArgumentException("Valor em mL deve ser positivo.");
		}
		
		this.mL = mL;
	}
	
	@Override
	public String toString() {
		
		return "Bebida" + super.toString() + ", getmL()=" + getmL() + ", isAlcoolica()=" + isAlcoolica() + "]";
		//return getNome() + ": " + mL + " mL, " + alcoolicaString();
	}
	
	public double getmL() {
		return mL;
	}
	
	public void setAlcoolica(boolean alcoolica) {
		this.alcoolica = alcoolica;
	}
	
	public boolean isAlcoolica() {
		return alcoolica;
	}
	
	public String alcoolicaString() {
		
		if (alcoolica) return "alcoólica";
		return "não alcoólica";
	}
	
	@Override
	public String getClassName() {
		return "Bebida";
	}

}
