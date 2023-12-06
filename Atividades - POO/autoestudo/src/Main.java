import java.time.LocalDate;

public class Main {
	//Umberto Leonetti
	public static void main(String[] args) {
		Avaliacao a1 = new Avaliacao(1500, "Bom nível");
		Avaliacao a2 = new Avaliacao(0, null);
		Avaliacao a3 = new Avaliacao(54, "Com informações esclarecedoras sobre as artes plásticas comntemporâneas");
		Avaliacao a4 = new Avaliacao(265, "Opinião: Muuuito louco...");
		Evento e1 = new Evento("Teatro as 18h", 20f, LocalDate.of(2019, 9, 10), a1);
		
		ShowMusical s1 = new ShowMusical("Novos ouvintes", "pop rock", "Skank", 65f, LocalDate.of(2019, 11, 15), a2);
		Evento e2 = new Evento("Palestra com Tadeo GBerkji", 60, LocalDate.of(2019, 8, 05), a3);
		ShowMusical s2 = new ShowMusical("Tempo Passado", "Rock progerssivo", "Grupo GFD", 15f, LocalDate.of(2019, 9, 15), a4);
		
		System.out.println(e1.exibir());
		System.out.println(s1.exibir());
		System.out.println(e2.exibir());
		System.out.println(s2.exibir());
	}
}
