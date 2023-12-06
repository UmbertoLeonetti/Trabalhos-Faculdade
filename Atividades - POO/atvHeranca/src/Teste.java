public class Teste {
	public static void main(String[] args) {
        Assalariado a1 = new Assalariado("João", 1, 2000.0f, 2000.0f);
        Assalariado a2 = new Assalariado("Gabriel", 3, 1500.0f, 1500.0f);
        PorHora h1 = new PorHora("Maria", 2, 1500.0f, 80, 30.50f);
        PorHora h2 = new PorHora("Jorge", 4, 2000.0f, 130, 20.0f);

        
        System.out.println("Funcionário assalariado:");
        System.out.println("Nome: " + a1.getNome());
        System.out.println("ID: " + a1.getId());
        System.out.println("Salário total: " + a1.calcularSalario());

        System.out.println();

        System.out.println("Funcionário por hora:");
        System.out.println("Nome: " + h1.getNome());
        System.out.println("ID: " + h1.getId());
        System.out.println("Salário total: " + h1.calcularSalario());
        
        System.out.println();
        
        System.out.println("Funcionário assalariado:");
        System.out.println("Nome: " + a2.getNome());
        System.out.println("ID: " + a2.getId());
        System.out.println("Salário total: " + a2.calcularSalario());
        
        System.out.println();
        
        System.out.println("Funcionário por hora:");
        System.out.println("Nome: " + h2.getNome());
        System.out.println("ID: " + h2.getId());
        System.out.println("Salário total: " + h2.calcularSalario());
    }
}
