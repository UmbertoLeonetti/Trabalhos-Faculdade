import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Classe responsável por criar uma inteface gráfica do software e manipular os dados inseridos pelo usuário
 * Deve controlar os contribuintes inseridos utilizando uma estrutura de armazenamento permitindo inserir e consultar as informações
 * 
 * @author Gabriel
 *
 */
public class ContribuinteView {

	private JFrame frame;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfUf;
	private JTextField tfRendaAnual;
	//private Contribuinte[] contribuintes; //Array da classe Contribuinte.
	private ArrayList<Contribuinte> contribuintes;
	private HashMap<String, Contribuinte> contribuintesHash;
	private DecimalFormat df = new DecimalFormat("R$ 0.00");
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Cria a tela e mostra.
					ContribuinteView window = new ContribuinteView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Contrutor da tela. Não possui parâmetros.
	 */
	public ContribuinteView() {
		initialize();
	}

	/**
	 * Método padrão para criar os componentes da tela.
	 * Nesse método também criamos o array que armazena os dados de Contribuinte
	 * Esse método é executado quando o software é iniciado (chamado no construtor)
	 */
	private void initialize() {
		contribuintes = new ArrayList<>();
		contribuintesHash = new HashMap<>();
		//Instancia uma Frame (tela)
		frame = new JFrame();
		frame.setBounds(200, 200, 450, 274);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout((LayoutManager) new MigLayout());
		
		//Cria um Label (rótulo ou identificação da informação)
		JLabel lblNewLabel = new JLabel("Nome:");
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing,aligny center");
		
		// Cria um campo de texto. Esse campo permite ao usuário inserir informação.
		tfNome = new JTextField();
		frame.getContentPane().add(tfNome, "cell 1 0,growx");
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cpf:");
		frame.getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		tfCpf = new JTextField();
		frame.getContentPane().add(tfCpf, "cell 1 1,growx");
		tfCpf.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("UF:");
		frame.getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		tfUf = new JTextField();
		frame.getContentPane().add(tfUf, "cell 1 2,growx");
		tfUf.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Renda anual:");
		frame.getContentPane().add(lblNewLabel_3, "cell 0 3,alignx trailing");
		
		tfRendaAnual = new JTextField();
		frame.getContentPane().add(tfRendaAnual, "cell 1 3,growx");
		tfRendaAnual.setColumns(10);
		
		//Cria um botão para salvar os dados. Esse componente permite ao usuário executar uma ação.
		JButton btnSalvar = new JButton("Salvar");
		//Adiciona uma ação ao botão salvar
		btnSalvar.addActionListener(new ActionListener() {
			//Método que temos que implementar para executar a ação
			public void actionPerformed(ActionEvent e) {
				//AQUI devemos implementar a ação que deve ser executada ao clicar no botão.
				salvar(); //Foi criado um método exclusivo para salvar
			}
			
		});
		
		//Cria um botão para consultar os dados.
		JButton btnConsultar = new JButton("Consultar Renda");
		btnConsultar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//AQUI devemos implementar a ação que deve ser executada ao clicar no botão "consultar
				consultar(); //Método consultar
			}
			
		});
		//Adiciona no painel os dois botões
		frame.getContentPane().add(btnConsultar, "flowx,cell 1 4,alignx right");
		frame.getContentPane().add(btnSalvar, "cell 1 4,alignx right");
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel, "cell 0 5 2 1,grow");
		
		JLabel lblNewLabel_4 = new JLabel("Consulta CPF");
		panel.add(lblNewLabel_4);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpfBusca = textField.getText();
				Contribuinte achou = null;
				for (Contribuinte contribuinte : contribuintes) {
					if (contribuinte.getCpf().equals(cpfBusca)) {
						achou = contribuinte;
						break;
					}
				}
				
				if (achou != null) {
					JOptionPane.showMessageDialog(frame, "[LIST] - Nome: "+achou.getNome()+" - Imposto: "+achou.getImpostoAPagar());
				} else {
					JOptionPane.showMessageDialog(frame, "[LIST] - Não encontrou contribuinte com esse CPF.");
				}
				
				
				//uso do hashmap
				Contribuinte c = contribuintesHash.get(cpfBusca);
				if (c != null) {
					JOptionPane.showMessageDialog(frame, "[HASH] - Nome: "+c.getNome()+" - Imposto: "+c.getImpostoAPagar());
				} else {
					JOptionPane.showMessageDialog(frame, "[HASH] - Não encontrou contribuinte com esse CPF.");
				}
				
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Porcentagem Estado");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double totSC, totPR, totRS, totGeral, imposto;
				totSC = totPR = totRS = totGeral = 0;
				for (Contribuinte c: contribuintes) {
					imposto = c.getImpostoAPagar();
					if (c.getUf().equals("SC")) {
						totSC += imposto;
					}
					else if(c.getUf().equals("PR")) {
						totPR += imposto;
					}
					else if (c.getUf().equals("RS")){
						totRS += imposto;
					}
					totGeral += imposto;
				}
				String str="Participação % dos estados";
				str += "\nRS = "+ totRS+" = "+(totRS/totGeral)*100;
				str += "\nSC = "+ totSC+" = "+(totSC/totGeral)*100;
				str += "\nPR = "+ totPR+" = "+(totPR/totGeral)*100;
				str += "\nGeral = "+ totGeral;
				JOptionPane.showMessageDialog(null, str);
				
			}
		});
		frame.getContentPane().add(btnNewButton_1, "cell 1 6");
	}
	
	/**
	 * Método salvar
	 * Esse método deve ler todos os dados inseridos pelo usuário (componentes da tela), 
	 * instanciar um objeto de Contribuinte e inserir no array que armazena o Contribuinte.
	 */
	private void salvar() {
		
		//Validação da UF (somente SC, PR e RS podem ser gravados)
		String uf = tfUf.getText();
		if ("SC".equals(uf) || "PR".equals(uf) || "RS".equals(uf)) {
			
			//Converte o campo Renda Anual de String para Float. 
			//Utilizando JTextField, o getText() sempre retorna uma String, por isso devemos converter usando parseFloat.
			float rendaAnual = Float.parseFloat(tfRendaAnual.getText());
			//Instancia o objeto contribuinte com todos os campos no construtor.
			Contribuinte contribuinte = new Contribuinte(tfNome.getText(), 
					tfCpf.getText(), tfUf.getText(), rendaAnual);
			//Adiciona o objeto contribuinte na lista e no hash
			contribuintes.add(contribuinte);
			contribuintesHash.put(contribuinte.getCpf(), contribuinte);
			
			//Cálculo do imposto a pagar, chamando o método que criamos na classe Contribuinte
			float impostoAPagar = contribuinte.getImpostoAPagar();
			
			//Mostrar uma mensagem de aviso: Escrevemos uma mensagem e aparece ao usuário somente um botão OK.
			JOptionPane.showMessageDialog(frame, "O valor do imposto a pagar: "+df.format(impostoAPagar));
			
			//Limpar os campos, para que o usuário possa inserir um novo contribuinte
			tfCpf.setText("");
			tfNome.setText("");
			tfRendaAnual.setText("");
			tfUf.setText("");			
		} else {
			JOptionPane.showMessageDialog(frame, "UF não permitida. Somente SC, PR, RS.");
		}
	}
	
	/**
	 * Método consultar
	 * Esse método deve exibir um componente de texto para o usuário inserir um valor de consulta.
	 * O método deve percorrer todo o array "contribuintes" procurando um valor maior que o informado pelo usuário.
	 * Quando encontrar, deve armazenar em uma variável.
	 * No final, caso encontre alguém, deve exibir uma mensagem com os nomes encontrados.
	 */
	public void consultar() {
		//showInputDialog mostra uma mensagem com um componente para inserir um valor.
		//Para armazenar esse valor, o método retorna uma String, por isso criamos o valorConsultar
		String valorConsultar = JOptionPane.showInputDialog(frame, "Informe o valor a ser consultado.");
		
		//Só deve pesquisar se o usuário inseriu uma informação (não cancelou)
		if (valorConsultar != null && !valorConsultar.isBlank()) {
			//Converte o valor digitado pelo usuário em float.
			float valorConsutarF = Float.parseFloat(valorConsultar);
			//Cria uma variável para armazenar os nomes recuperados (será usada para imprimir a mensagem final)
			String nomes = "";
			for (Contribuinte contribuinte : contribuintes) {
				//Se o objeto não é nulo (existe um objeto criado na posição)
				if (contribuinte != null) {
					//Verifica se o valor a pagar do objeto é maior que o valor digitado pelo usuário
					if (contribuinte.getImpostoAPagar() > valorConsutarF) {
						//Se for maior, deve concatenar o nome e o CPF na variável que acumula os contribuintes
						nomes += contribuinte.getNome()+ " - CPF: " + contribuinte.getCpf() +" Valor: ("+df.format(contribuinte.getImpostoAPagar())+")\n";
					}
					
				}	
			}
			
			//Se existe algum nome informado (não ficou vazio)
			if (!nomes.isBlank()) {
				//Imprime uma mensagem
				JOptionPane.showMessageDialog(frame, "Foi identificado os seguintes contribuintes com valor maior que "+df.format(valorConsutarF)
				+":\n" + nomes);
			}		
		}
	}
	
}