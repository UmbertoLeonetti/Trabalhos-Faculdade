import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContribuinteTest {

	@Test
	void testSetUf_SC() {
		Contribuinte c = new Contribuinte("AAA", "123", "SC", 1000);
		assertEquals("SC", c.getUf());
	}

	@Test
	void testSetUf_RS() {
		Contribuinte c = new Contribuinte("AAA", "123", "RS", 1000);
		assertEquals("RS", c.getUf());
	}
	
	@Test
	void testSetUf_PR() {
		Contribuinte c = new Contribuinte("AAA", "123", "PR", 1000);
		assertEquals("PR", c.getUf());
	}
	
	@Test
	void testSetUf_SP_invalido() {
		Contribuinte c = new Contribuinte("AAA", "123", "PR", 1000);
		c.setUf("SP");
		assertEquals("PR", c.getUf());
	}
	
	@Test
	void testSetUf_RJ_invalido() {
		Contribuinte c = new Contribuinte("AAA", "123", "PR", 1000);
		c.setUf("RJ");
		assertEquals("PR", c.getUf());
	}
	
	@Test
	void testSetRendaAnualNegativa() {
		Contribuinte c = new Contribuinte("AAA", "123", "SC", 1000);

		c.setRendaAnual(-332.5f);

		assertEquals(1000, c.getRendaAnual());
	}

	@Test
	void testCalcularImposto_para_uma_renda_de_3000() {
		Contribuinte c = new Contribuinte("Gabriel", "123", "SC", 3000);

		float imposto = c.getImpostoAPagar();

		assertEquals(0.0, imposto);
	}

	@Test
	void testCalcularImposto_para_uma_renda_de_9000() {
		Contribuinte c = new Contribuinte("Gabriel", "123", "SC", 9000);

		double imposto = c.getImpostoAPagar();

		assertEquals(522.0, imposto);
	}

	@Test
	final void testCalcularImposto_para_uma_renda_de_10000() {
		Contribuinte c1 = new Contribuinte("Gabriel", "123", "SC", 10000);
		assertEquals(1500, c1.getImpostoAPagar());
	}

	@Test
	final void testCalcularImposto_para_uma_renda_de_34911_73() {
		Contribuinte c1 = new Contribuinte("Gabriel", "123", "SC", 34911.73f);
		assertEquals(9600.72, c1.getImpostoAPagar(), 0.007);
	}

}