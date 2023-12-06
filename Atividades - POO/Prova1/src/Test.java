import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		Servico s1 = new Servico(1023, "João", "Rua x, 200", "(47)99999-9999", "C", 
				"audi", 89.002f, 30, 7f, true);
		
		Reparo r1 = new Reparo("Óleo motor", "L", 3, 24.2f);
		Reparo r2 = new Reparo("velas", "P", 4, 10.8f);
		Reparo r3 = new Reparo("filtro", "P", 1, 34.2f);
		Reparo r4 = new Reparo("Óleo câmbio", "L", 3, 89.3f);
		Reparo r5 = new Reparo("Graxa", "Q", 2, 37.6f);
		
		s1.addReparo(r1);
		s1.addReparo(r2);
		s1.addReparo(r3);
		s1.addReparo(r4);
		s1.addReparo(r5);
		
		s1.ImprimirServico();
	}

}
