import java.time.LocalDate;
//Umberto Leonetti
public class Evento {
	private String titulo;
	private float valor;
	private LocalDate data;
	private Avaliacao avaliacao;
	
	public Evento(String titulo, float valor, LocalDate data, Avaliacao avaliacao) {
		super();
		this.titulo = titulo;
		this.valor = valor;
		this.data = data;
		this.avaliacao = avaliacao;
	}
	 public String exibir() {
			return "Evento: " + this.getTitulo() + ", no dia " + data + ", ingressos a R$" + this.getValor()
            + ", teve " + avaliacao.getQntdPagantes() + " pagantes que acharam o evento " + getAvalicao().getOpiniaoGeral();
	    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Avaliacao getAvalicao() {
		return avaliacao;
	}

	public void setAvalicao(Avaliacao avalicao) {
		this.avaliacao = avalicao;
	}
}
