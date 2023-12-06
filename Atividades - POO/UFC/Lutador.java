import java.util.Locale.Category;

public class Lutador {
	private String nome;
	private String nacionalidade;
	private int idade;
	private float altura;
	private float peso;
	private String catergoria;
	private int vitoria;
	private int derrotas;
	private int empates;

	public void apresentar() {
		System.out.println("--------------------------------------");
		System.out.println("APRESENTANDO O LUTADOS: " + getNome());
		System.out.println("Diretamente de: " + getNacionalidade());
		System.out.println("Com: " + getIdade() + " anos e " + getAltura());
		System.out.println("Pesando: " + getPeso() + "KG");
		System.out.println(getVitoria() + " vitórias");
		System.out.println(getDerrotas() + " derrotas");
		System.out.println(getEmpates() + " empates");
	}

	public void status() {
		System.out.println(getNome() + " é um peso " + getCatergoria());
		System.out.println("Ganhou " + getVitoria() + " vezes");
		System.out.println("Perdeu " + getDerrotas() + " vezes");
		System.out.println("Empatou " + getEmpates() + " vezes");
	}

	public void ganharLuta() {
		setVitoria(getVitoria() + 1);
	}

	public void perderLuta() {
		setDerrotas(getDerrotas() + 1);
	}

	public void empatarLuta() {
		setEmpates(getEmpates() + 1);
	}

	public Lutador(String nome, String nacionalidade, int idade, float altura, float peso,
			int vitoria, int derrotas, int empates) {
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.idade = idade;
		this.altura = altura;
		this.setPeso(peso);
		this.vitoria = vitoria;
		this.derrotas = derrotas;
		this.empates = empates;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
		setCatergoria();
	}

	public String getCatergoria() {
		return catergoria;
	}

	private void setCatergoria() {
		if(getPeso() < 52.2) {
			catergoria = "inválido";
		}else if(getPeso() <= 70.3) {
			catergoria = "Leve";
		} else if(getPeso() <= 83.9) {
			catergoria = "Médio";
		} else if(getPeso() <= 120.2) {
			catergoria = "Pesado";
		} else {
			catergoria = "Inválido";
		}
	}

	public int getVitoria() {
		return vitoria;
	}

	public void setVitoria(int vitoria) {
		this.vitoria = vitoria;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

}
