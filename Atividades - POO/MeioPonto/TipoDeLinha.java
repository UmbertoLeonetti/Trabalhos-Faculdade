import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TipoDeLinha {
	private Cliente cliente;
	private String data;
	private String telefone;
	private String tipoLinha;
	private String ramoAtv;
	private int qtdOcorrencias;
	private Boolean internet;
	private int numero;
	DecimalFormat formato = new DecimalFormat("#.00");

	public TipoDeLinha(Cliente cliente, String numero, String data) {
		setCliente(cliente);
		setTelefone(numero);
		setData(data);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipoLinha() {
		return tipoLinha;
	}

	public void setTipoLinha(String tipoLinha) {
		this.tipoLinha = tipoLinha;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getInternet() {
		return internet;
	}

	public void setinternet(Boolean conexaoInt) {
		this.internet = conexaoInt;
	}

	public String getRamoAtv() {
		return ramoAtv;
	}

	public void setRamoAtv(String ramoAtv) {
		this.ramoAtv = ramoAtv;
	}

	public int getQtdOcorrencias() {
		return qtdOcorrencias;
	}

	public void setQtdOcorrencias(int qtdOcorrencias) {
		this.qtdOcorrencias = qtdOcorrencias;
	}

	public float calcularTarifa() {
		if (tipoLinha.equals("Telefone Residencial")) {
			return 25.0f;
		}
		if (tipoLinha.equals("Telefone Comercial")) {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			f.setLenient(false);
			java.util.Date d1 = null;
			try {
				d1 = f.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date dataComp = new Date("02/01/2018");

			if (d1.compareTo(dataComp) > 0) {
				return 37.50f;
			} else {
				return 27.50f;
			}
		}
			if (qtdOcorrencias >= 1 && qtdOcorrencias <= 1000) {
				numero = (int) 56.40f;
				return numero;
			} else if (qtdOcorrencias >= 1001 && qtdOcorrencias <= 5000) {
				numero = (int) 67.80f;
				return numero;
			} else if (qtdOcorrencias >= 5001 && qtdOcorrencias <= 10000) {
				numero = (int) 98.50f;
				return numero;
			} else if (qtdOcorrencias >= 10001 && qtdOcorrencias <= 50000) {
				numero = (int) 123.90f;
				return numero;
			} else {
				numero = (int) 187.82f;
				return numero;
			}
	}
}
