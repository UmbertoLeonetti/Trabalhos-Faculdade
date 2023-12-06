import java.time.LocalDate;

public class AlunoUniversitario extends Aluno {

	private char formaIngresso;
	private Curso curso;
	
	public AlunoUniversitario(String nome, LocalDate dataNascimento, char formaIngresso, Curso curso) {
		super(nome, dataNascimento);
		setFormaIngresso(formaIngresso);
		setCurso(curso);
	}
	
	public void setCurso(Curso curso) {
		if (curso == null) {
			throw new IllegalArgumentException("Curso não  pode ser nulo");
		}
		this.curso = curso;
	}
	
	public void setFormaIngresso(char formaIngresso) {
		this.formaIngresso = formaIngresso;
	}
	
	@Override
	public String mostra() {
		return getNome() + " é aluno universitário do curso de " + curso.getSigla() + " – " +curso.getNome()+ ", ingressando por " + this.formaIngresso + ".";
	}

}