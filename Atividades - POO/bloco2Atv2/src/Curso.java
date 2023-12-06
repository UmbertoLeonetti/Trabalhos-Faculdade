public class Curso {

	private String sigla;
	private String nome;
	
	public Curso(String sigla, String nome) {
		super();
		this.sigla = sigla;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
}
