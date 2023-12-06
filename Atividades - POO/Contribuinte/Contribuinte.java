public class Contribuinte {

	private String nome;
	private String cpf;
	private String uf;
	private float rendaAnual;
	
	/**
	 * Construtor da classe. Deve ser informado todos os campos do contribuinte.
	 * @param nome - Nome da pessoa
	 * @param cpf - CPF da pessoa
	 * @param uf - Unidade Federativa 
	 * @param rendaAnual - valor da renda anual
	 */
	public Contribuinte(String nome, String cpf, 
			String uf, float rendaAnual) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setUf(uf);
		this.setRendaAnual(rendaAnual);
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getUf() {
		return uf;
	}

	public float getRendaAnual() {
		return rendaAnual;
	}
	
	/**
	 * Set de UF
	 * Permite somente SC, PR ou RS
	 * @param uf
	 */
    public void setUf(String uf){
        if (uf.equals("SC") || uf.equals("PR") || uf.equals("RS")) {
            this.uf = uf;
        }
    }
    
    public void setRendaAnual(float renda){
        if (renda >= 0){
            this.rendaAnual = renda;
        }
    }
    
    public void setNome(String nome) {
		this.nome = nome;
	}
    
    public void setCpf(String cpf) {
    	this.cpf = cpf;
    }
	
	/**
	 * Método utilizado para calcular o imposto a pagar, conforme regra definida no exercício.
	 * @return um imposto a pagar ou zero se não paga imposto
	 */
	public float getImpostoAPagar() {
		if (this.rendaAnual > 4000 && this.rendaAnual <= 9000) {
			return this.rendaAnual * 0.058f;
		}
		if (this.rendaAnual > 9000 && this.rendaAnual <= 25000) {
			return this.rendaAnual * 0.15f;
		}
		if (this.rendaAnual > 25000 && this.rendaAnual <= 35000) {
			return this.rendaAnual * 0.275f;
		}
		if (this.rendaAnual > 35000) {
			return this.rendaAnual * 0.30f;
		}		
		return 0;
	}
}