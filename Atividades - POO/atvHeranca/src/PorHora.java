class PorHora extends Funcionario {
    private int horasTrabalhadas;
    private float taxaHoraria;

    public PorHora(String nome, int id, float salarioBase, int horasTrabalhadas, float taxaHoraria) {
        super(nome, id, salarioBase);
        this.horasTrabalhadas = horasTrabalhadas;
        this.taxaHoraria = taxaHoraria;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public float getTaxaHoraria() {
        return taxaHoraria;
    }

    @Override
    public float calcularSalario() {
        return horasTrabalhadas * taxaHoraria;
    }

	@Override
	public String toString() {
		return "PorHora [horasTrabalhadas=" + horasTrabalhadas + ", taxaHoraria=" + taxaHoraria + "]";
	}
    
}
