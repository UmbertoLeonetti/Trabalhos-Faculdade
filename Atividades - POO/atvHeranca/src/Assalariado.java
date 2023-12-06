class Assalariado extends Funcionario {
    private float salarioMensal;

    public Assalariado(String nome, int id, float salarioBase, float salarioMensal) {
        super(nome, id, salarioBase);
        this.salarioMensal = salarioMensal;
    }

    public float getSalarioMensal() {
        return salarioMensal;
    }

    @Override
    public float calcularSalario() {
        return salarioMensal;
    }

	@Override
	public String toString() {
		return "Assalariado [salarioMensal=" + salarioMensal + "]";
	}
    
}

