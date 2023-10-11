package backend.model;

public class Funcionario implements Item {
    private String nome;
    private String cargo;
    private String horarioTrabalho;
    
    public Funcionario() {
    	nome = "";
    	cargo = "";
    	horarioTrabalho = "00:00";
	}

    public Funcionario(String nome, String cargo, String horarioTrabalho) {
        this.nome = nome;
        this.cargo = cargo;
        this.horarioTrabalho = horarioTrabalho;
    }
    
	public void setNome(String nome) {
		if (nome.length() < 2)
			throw new IllegalArgumentException("Nome de funcionário deve ter pelo menos 2 letras");
		this.nome = nome;
	}

	public void setHorarioTrabalho(String horarioTrabalho) {
		this.horarioTrabalho = horarioTrabalho;
	}

	public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getHorarioTrabalho() {
		return horarioTrabalho;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

	public String getClassName() {
		return "Funcionário";
	}

	public void exibirInformacoes() {
		System.out.println("Nome: " + nome);
        System.out.println("Cargo: " + cargo);
        System.out.println("Horário de Trabalho: " + horarioTrabalho);
	}
}