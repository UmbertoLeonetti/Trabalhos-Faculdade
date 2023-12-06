import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	public Main() {

		Aluno aluno = new AlunoUniversitario("Amanda", LocalDate.of(1989, 5, 8), 'V', 
				new Curso("SIS", "SISTEMAS"));
		
		alunos.add(aluno);
		
		aluno = new AlunoUniversitario("Pedro", LocalDate.of(1989, 5, 8), 'V', 
				new Curso("SIS", "SISTEMAS"));
		
		alunos.add(aluno);	

		aluno = new AlunoUniversitario("Joao", LocalDate.of(1989, 5, 8), 'V', 
				new Curso("SIS", "SISTEMAS"));
		
		alunos.add(aluno);	
		
		
		aluno = new AlunoEnsinoMedio("Marcelo", LocalDate.now(), 1);
		
		alunos.add(aluno);	

		
		alunos.sort(null);
		
		for (Aluno objeto : alunos) {
			System.out.println(objeto.mostra());
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}