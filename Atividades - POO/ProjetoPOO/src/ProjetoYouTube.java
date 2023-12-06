
public class ProjetoYouTube {
	public static void main(String[] args) {
		Video v[] = new Video[3];
		v[0] = new Video("Como fazer um projeto em POO");
		v[1] = new Video("Aprendendo POO");
		v[2] = new Video("Furb melhor faculdade?");
		
		Gafanhoto g[] = new Gafanhoto[2];
		g[0] = new Gafanhoto("Umberto", 22, "M", "Bebeto");
		g[0] = new Gafanhoto("Eduarda", 17, "F", "Dudinha");
		
		Visualizacao vis[] = new Visualizacao[5];
		vis[0] = new Visualizacao(g[0], v[2]); //Umberto assite a Furb
		vis[0].avaliar();
		System.out.println(vis[0].toString());
		
		vis[1] = new Visualizacao(g[0], v[1]); // Umberto assite POO
		vis[0].avaliar(87.0f);
		System.out.println(vis[0].toString());
		
		/*System.out.println("VÍDEOSZ\n-----------------------------");
		System.out.println(v[0].toString());
		System.out.println(v[1].toString());
		System.out.println(v[2].toString());
		System.out.println("GAFANHOTOS\n-----------------------------");
		System.out.println(g[0].toString());
		System.out.println(g[1].toString());*/
	}
}
