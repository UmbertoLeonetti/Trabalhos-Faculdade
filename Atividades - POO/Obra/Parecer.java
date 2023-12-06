import java.time.LocalDate;

public class Parecer {

	private String parecerista;
	private LocalDate data;
	private String conteudo;
	
	public Parecer(String parecerista, LocalDate data, String conteudo) {
		this.parecerista = parecerista;
		setData(data);
		this.conteudo = conteudo;
	}
	
	public void setData(LocalDate data) {
		if (data.isBefore(LocalDate.now())) {
			System.out.println("Data inválida");
		} else {
			this.data = data;
		}
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public String getParecerista() {
		return parecerista;
	}
}
