import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Servico {

	private int numero;
	private String nome;
	private String endereco;
	private String telefone;
	private String tipo;
	private String marca;
	private float quilometragem;
	private int qtdEixos;
	private float mecanico = 85f;
	private boolean guincho;
	private float horasGastas;
	private List<Reparo> lista = new ArrayList<>();

	public Servico(int numero, String nome, String endereco, String telefone, String tipo, String marca,
			float quilometragem, int qtdEixos, float horasGastas, boolean guincho) {
		super();
		this.setNumero(numero);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setTipo(tipo);
		this.setMarca(marca);
		this.setQuilometragem(quilometragem);
		this.setQtdEixos(qtdEixos);
		this.setHorasGastas(horasGastas);
		this.setGuincho(guincho);
	}

	public void addReparo(Reparo reparo) {
		lista.add(reparo);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() > 2) {
			this.nome = nome;
		} else {
			throw new IllegalArgumentException("Nome muito pequeno");
		}
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo == "C" || tipo == "D" || tipo == "E") {
			this.tipo = tipo;
		} else {
			throw new IllegalArgumentException("Tipo não encontrado");
		}
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getQuilometragem() {
		return this.quilometragem;
	}

	public void setQuilometragem(float quilometragem) {
		if (quilometragem > 0) {
			this.quilometragem = quilometragem;
		} else {
			throw new IllegalArgumentException("Essa quilometragem é negativa");
		}
	}

	public int getQtdEixos() {
		return qtdEixos;
	}

	public void setQtdEixos(int qtdEixos) {
		this.qtdEixos = qtdEixos;
	}

	public float getMecanico() {
		return mecanico;
	}

	public void setMecanico(float hora) {
		this.mecanico = hora;
	}

	public boolean getGuincho() {
		return guincho;
	}

	public void setGuincho(boolean guincho) {
		this.guincho = guincho;
	}

	public float getHorasGastas() {
		return horasGastas;
	}

	public void setHorasGastas(float horasGastas) {
		this.horasGastas = horasGastas;
	}

	public int MedidaQuilo() {
		int cont = 0;
		for (Reparo r : lista) {
			if (r.getMedida() == "Q") {
				cont++;
			}
		}
		return cont;
	}

	public void ImprimirServico() {
		System.out.println("O cliente: " + this.getNome() + " possui um veículo do tipo: " + this.getTipo()
				+ " deixou seu veículo para revisão, com o número de telefone de contato: " + this.getTelefone()
				+ " gerando a ordem de serviço de número: " + this.getNumero() + " que totalizou em um valor de R$"
				+ TotalServico() + " sendo que o produto com maior valor é o " + DescricaoMaior());
	}

	public float CalcularProdutos() {
		float calc = 0;
		for (Reparo r : lista) {
			calc += r.getQtdProdutos() * r.getValor();
		}
		return calc;
	}

	public String[] Descricao() {
		String[] vetor = new String[2];
		float total = 0;
		String maiorD = "";
		String menorD = "";
		float maior = Integer.MIN_VALUE;
		float menor = Integer.MAX_VALUE;
		for (Reparo r : lista) {
			total = r.getQtdProdutos() * r.getValor();
			if (total > maior) {
				maior = total;
				maiorD = r.getDescricao();
			}
			if (total < menor) {
				menor = total;
				menorD = r.getDescricao();
			}
			if (r.getQtdProdutos() == 0) {
				return null;
			}
		}
		vetor[0] = maiorD;
		vetor[1] = menorD;
		return vetor;
	}

	public String DescricaoMaior() {
		float total = 0;
		String maiorD = "";
		float maior = Integer.MIN_VALUE;
		for (Reparo r : lista) {
			total = r.getQtdProdutos() * r.getValor();
			if (total > maior) {
				maior = total;
				maiorD = r.getDescricao();
			}
		}
		return maiorD;
	}

	public float TotalServico() {
		float vTotalS = 0;
		float totalPr = 0;
		float porcentagem = 0.12f;
		for (Reparo r : lista) {
			totalPr += r.getQtdProdutos() * r.getValor();
		}
		vTotalS = totalPr + (getMecanico() * getHorasGastas());
		if (this.getGuincho()) {
			vTotalS += vTotalS * porcentagem;
		}
		return vTotalS;
	}
}
