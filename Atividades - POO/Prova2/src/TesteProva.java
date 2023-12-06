
public class TesteProva {
public static void main(String[] args) {
	Cardapio c1 = new Cardapio("Cardápio de inverno", 1);
	c1.add("Casillero", 156.00, 2007, "Merlot");
	c1.add("Coca-Cola", 8.00, false);
	c1.add("Cerveja", 10.00, true);
	c1.add("Suco", 9.00, false);
	c1.add("Macarronada", 45.00, "Trigo, Molho de tomate", true);
	c1.add("Risoto", 30.00, "Arroz, filé mignon", false);
	
	c1.getListaProdutos();
	System.out.println(c1.getListaProdutos());
	System.out.println(c1.getMediaValor());
	}
}
