import java.util.ArrayList;
import java.util.List;

public class Imovel {
	public List<Comodo>lista = new ArrayList<>();
	
	private String tipo;
	private String uso;
	private String endereco;
	private String proprietario;
	private String finalidade;
	private float valor;
	
	public Imovel(String tipo, String uso, String endereco, String proprietario, String finalidade, float valor) {
		this.setTipo(tipo);
		this.setUso(uso);
		this.setEndereco(endereco);
		this.setProprietario(proprietario);
		this.setFinalidade(finalidade);
		this.setValor(valor);
	}

	public void addComodo(Comodo comodo) {
		lista.add(comodo);
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if(tipo == "C" || tipo == "A" || tipo == "G" || tipo == "T" || tipo == "O") {
			this.tipo = tipo;
		}else {
			throw new IllegalArgumentException("Tipo Inválido");
		}
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		if(uso == "R" || uso == "C" || uso == "I" || uso == "S" || uso == "O") {
			this.uso = uso;
		}else {
			throw new IllegalArgumentException("Uso Inválido");
		}
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		if(finalidade == "V" || finalidade == "L") {
			this.finalidade = finalidade;
		}else {
			throw new IllegalArgumentException("Finalidade Inválida");
		}
	}

	public double getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public float getIndiceVentilacao() {
		float janelas = 0;
		for(Comodo c: lista){
			janelas += c.getQtJanelas();
		}
		if(janelas == 0){
			return 1;
		}else {
			System.out.println("ventilação: " + (getAreaTotal()/janelas)*0.08f);
			return (getAreaTotal()/janelas)*0.08f;
		}
	}
	
	public float getAreaTotal() {
		float areaTotal = 0;
		for(Comodo c: lista){
			areaTotal= areaTotal+c.getAreaComodo();
		}
		return areaTotal;
	}
	
	public float getIndiceRentabilidadeLocacao() {
		float rende = 0;
		if(finalidade == "L") {
			rende = (valor / getAreaTotal())/ lista.size();
		}else
		if(finalidade == "V") {
			rende = -1;
		}
		return rende;
	}

}

