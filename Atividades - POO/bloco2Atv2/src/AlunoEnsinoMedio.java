import java.time.LocalDate;

public class AlunoEnsinoMedio extends Aluno {

	private int ano;
	
	public AlunoEnsinoMedio(String nome, LocalDate dataNascimento, int ano) {
		super(nome, dataNascimento);
		setAno(ano);
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public int getAno() {
		return ano;
	}
	
	@Override
	public String mostra() {
		return getNome() + " está cursando o "+ getAno() + " o ano do ensino médio e tem " + getIdade() + " anos.";
	}
}