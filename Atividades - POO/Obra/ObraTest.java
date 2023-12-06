import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ObraTest {

	@Test
	void cria_obra_parecer() {
		
		List<Obra> obras = new ArrayList<Obra>();
		
		
		
		Obra obra1 = new Obra("A escalada do terror", "Fernandinho Beira mar");
		
		Parecer parecer1 = new Parecer("João de Abreu", LocalDate.of(2023, 1, 10), "Livro que não pode ser publicado, pois é um manual do crime");
	
		obra1.addParecer(parecer1);
		
		
		
		Obra obra2 = new Obra("Confins do Sertão", "Hector Babenco");
		
		
		obras.add(obra1);
		obras.add(obra2);
		
		assertEquals("A escalada do terror", obra1.getTitulo());
		assertEquals("Confins do Sertão", obra2.getTitulo());
		assertEquals("Hector Babenco", obra2.getAutor());
		
		assertEquals("João de Abreu", obra1.getParecer(0).getParecerista());
		
		
		Parecer parecer2 = new Parecer("Paula Prantos", LocalDate.of(2023, 1, 10), "Livro que não pode ser publicado, pois é um manual do crime");
		
		obra1.addParecer(parecer2);
		
		
		assertEquals(2, obra1.getQuantidadePareceres());
	}

}
