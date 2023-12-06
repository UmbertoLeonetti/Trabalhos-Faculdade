
import java.time.LocalDate;
import java.time.Period;

public abstract class Aluno implements Comparable<Aluno> {

	private String nome;
	private LocalDate dataNascimento;
		

	public Aluno(String nome, LocalDate dataNascimento) {
		setNome(nome);
		setDataNascimento(dataNascimento);
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		if (dataNascimento.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Data nascimento é maior que hoje!");
		}
		this.dataNascimento = dataNascimento;
	}
	
	
	public void setNome(String nome) {
		if (nome.length() < 2) {
			throw new IllegalArgumentException("Nome é menor que 2");
		}
		this.nome = nome;
	}
	
	public abstract String mostra();
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return Period.between(dataNascimento, LocalDate.now()).getYears();
	}
	
	@Override
	public int compareTo(Aluno o) {
		return this.nome.compareTo(o.nome);
	}
}