import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		Cardapio c1 = new Cardapio("Cardápio de inverno", 1);
		c1.add("Casillero", 156.00, 2007, "Merlot");
		c1.add("Coca-Cola", 8.00, false);
		c1.add("Cerveja", 10.00, true);
		c1.add("Suco", 9.00, false);
		c1.add("Macarronada", 45.00, "Trigo, Molho de tomate", true);
		c1.add("Risoto", 30.00, "Arroz, filé mignon", false);
		
		assertEquals(44.25, c1.getMediaValor());
		
		c1.getListaProdutos();
	}
}
