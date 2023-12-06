
public class Avaliacao {
	//Umberto Leonetti
	private int qntdPagantes;
	private String opiniaoGeral;
	
	public Avaliacao(int qntdPagantes, String opiniaoGeral) {
		super();
		this.qntdPagantes = qntdPagantes;
		this.opiniaoGeral = opiniaoGeral;
	}

	public int getQntdPagantes() {
		return qntdPagantes;
	}

	public void setQntdPagantes(int qntdPagantes) {
		this.qntdPagantes = qntdPagantes;
	}

	public String getOpiniaoGeral() {
		return opiniaoGeral;
	}

	public void setOpiniaoGeral(String opiniaoGeral) {
		this.opiniaoGeral = opiniaoGeral;
	}
}
