public class Obra {

	private String titulo;
	private String autor;
	private Parecer[] pareceres = new Parecer[3];
	private int index;
	
	public Obra(String titulo, String autor) {
		this.autor = autor;
		this.titulo = titulo;
	}
	
	public void addParecer(Parecer parecer) {
		if (parecer != null && this.index < this.pareceres.length) {
			this.pareceres[index] = parecer;
			index++;
		} else {
			System.out.println("Problemas com o parecer.");
		}
	}

	public int getQuantidadePareceres() {
		return this.index;
	}
	
	public Parecer getParecer(int posicao) {
		return this.pareceres[posicao];
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
}