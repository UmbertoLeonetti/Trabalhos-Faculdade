import java.time.LocalDate;

public class ShowMusical extends Evento{
	//Umberto Leonetti
	private String nomeBanda;
	private String estiloMusical;
	
	public ShowMusical(String titulo,String estiloMusical, String nomeBanda, float valor, LocalDate data, Avaliacao avalicao) {
		super(titulo, valor, data, avalicao);
		this.setNomeBanda(nomeBanda);
		this.setEstiloMusical(estiloMusical);
	}
	@Override
	public String exibir() {
		 String opiniao = "";
		 if (super.getAvalicao().getOpiniaoGeral() != null) {
	            opiniao = "Opinião: " + super.getAvalicao().getOpiniaoGeral();
	        } else {
	            opiniao = "Ainda não avaliado.";
	        }
		return "Show Musical " + super.getTitulo() + " de " + this.estiloMusical + " de " + this.nomeBanda + 
        		" com ingressos a R$" + super.getValor() + ", dia " + super.getData() + ". " + opiniao;
	}

	public String getNomeBanda() {
		return nomeBanda;
	}

	public void setNomeBanda(String nomeBanda) {
		this.nomeBanda = nomeBanda;
	}

	public String getEstiloMusical() {
		return estiloMusical;
	}

	public void setEstiloMusical(String estiloMusical) {
		this.estiloMusical = estiloMusical;
	}
}