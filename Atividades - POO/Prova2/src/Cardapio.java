import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cardapio {
	private String nome;
	private int versao;
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	public Cardapio(String nome, int versao) {
		super();
		this.nome = nome;
		this.versao = versao;
	}
	
	public void add(String nome, double valor, boolean alcoolico) {
		try {
			Bebida b = new Bebida(alcoolico, nome, valor);
			produtos.add(b);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		}
	}
	
	public void add(String nome, double valor, int safra, String tipo) {
		try {
			Vinho v = new Vinho(safra, tipo, nome, valor);
			produtos.add(v);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		}
	}
	
	public void add(String nome, double valor, String ingredientes, boolean vegetariano) {
		try {
			Prato p = new Prato(ingredientes, vegetariano, nome, valor);
			produtos.add(p);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		}
	}
	
	public double getMediaValor() {
		double total = 0;
		double media = 0;
		for(Produto p : produtos) {
			total += p.getValor();
		}
		media = total / produtos.size();
		return media;
	}
	
	public String getListaProdutos() {
		String lista = "";
		for(Produto p : produtos) {
			lista += p.toString() + "\n";
		}
		return lista;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
}
