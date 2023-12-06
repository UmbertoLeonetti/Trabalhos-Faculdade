class Funcionario {
    private String nome;
    private int id;
    private float salarioBase;

    public Funcionario(String nome, int id, float salarioBase) {
        this.nome = nome;
        this.id = id;
        this.salarioBase = salarioBase;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public float calcularSalario() {
        return salarioBase;
    }
    
}

