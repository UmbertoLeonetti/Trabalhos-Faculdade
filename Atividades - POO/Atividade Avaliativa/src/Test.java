import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {

	@Test
	void test() {
		
		Imovel i1 = new Imovel("C", "R", "Nova Friburgo, 76", "João da Silva", "L", 750);
		
		Comodo c1 = new Comodo("Quarto", 5, 5.2f, 2, 2,1.04f);
		Comodo c2 = new Comodo("Corredor", 3, 4.8f, 3, 0,1);
		Comodo c3 = new Comodo("Suite Maior", 6, 6, 2, 1,2.88f);
		Comodo c4 = new Comodo("Quarto", 4, 7.2f, 1, 2,1.152f);
		Comodo c5 = new Comodo("Cozinha", 8, 5, 2, 2,1.6f);
		
		i1.addComodo(c1);
		i1.addComodo(c2);
		i1.addComodo(c3);
		i1.addComodo(c4);
		i1.addComodo(c5);
		
		assertEquals(1.0399999618530273, c1.getindiceVentilacao());
		assertEquals(145.1999969482422, i1.getAreaTotal());
		assertEquals(1.6594284772872925, i1.getIndiceVentilacao());
		assertEquals(1.0330579280853271, i1.getIndiceRentabilidadeLocacao());
	}

}
