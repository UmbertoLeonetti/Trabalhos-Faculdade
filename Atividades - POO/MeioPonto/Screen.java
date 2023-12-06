//Umberto Leonetti e Gabriel Utyama

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

public class Screen {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtData;
	private JTextField txtEndereco;
	private JTextField txtRamoAtv;
	private JTextField txtQtdOcorrencia;
	private JTextField txtTelefone;
	
	private JRadioButton rdbNao = new JRadioButton("Sem internet");
	private JRadioButton rdbSim = new JRadioButton("Com internet");
	private JRadioButton rdbResidencial = new JRadioButton("Residencial");
	private JRadioButton rdbEspecializado = new JRadioButton("Especializado");
	private JRadioButton rdbComercial = new JRadioButton("Comercial");
	
	private int dateToInt;
	
	private ArrayList<TipoDeLinha> listaTelefonica = new ArrayList<>(); 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Screen() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 489, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setForeground(Color.BLACK);
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNome.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lbNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(10, 30, 164, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lbTelefone = new JLabel("Telefone:");
		lbTelefone.setForeground(Color.BLACK);
		lbTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTelefone.setBounds(209, 13, 72, 14);
		frame.getContentPane().add(lbTelefone);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtData.setBounds(51, 107, 77, 20);
		frame.getContentPane().add(txtData);
		txtData.setColumns(10);
		
		JLabel lbEndereco = new JLabel("Endere\u00E7o:");
		lbEndereco.setForeground(Color.BLACK);
		lbEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEndereco.setBounds(10, 61, 77, 14);
		frame.getContentPane().add(lbEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEndereco.setBounds(10, 78, 318, 20);
		frame.getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		
		rdbComercial.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(rdbComercial.isSelected()) {
					
					txtRamoAtv.setEnabled(true);
					
					rdbEspecializado.setSelected(false);
					rdbResidencial.setSelected(false);
				}else {
					txtRamoAtv.setEnabled(false);
					txtRamoAtv.setText(null);
				}
			}
		});
		rdbComercial.setForeground(Color.BLACK);
		rdbComercial.setBackground(Color.WHITE);
		rdbComercial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbComercial.setBounds(10, 265, 109, 23);
		frame.getContentPane().add(rdbComercial);
		
		rdbEspecializado.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(rdbEspecializado.isSelected()) {
					
					txtQtdOcorrencia.setEnabled(true);
					
					rdbComercial.setSelected(false);
					rdbResidencial.setSelected(false);
				}else {
					txtQtdOcorrencia.setEnabled(false);
					txtQtdOcorrencia.setText(null);
				}
			}
		});
		rdbEspecializado.setForeground(Color.BLACK);
		rdbEspecializado.setBackground(Color.WHITE);
		rdbEspecializado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbEspecializado.setBounds(10, 291, 109, 23);
		frame.getContentPane().add(rdbEspecializado);
		
		
		rdbSim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbNao.setSelected(false);
			}
		});
		rdbSim.setForeground(Color.BLACK);
		rdbSim.setBackground(Color.WHITE);
		rdbSim.setEnabled(false);
		rdbSim.setBounds(10, 201, 131, 23);
		frame.getContentPane().add(rdbSim);
		
		rdbNao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbSim.setSelected(false);
			}
		});
		rdbNao.setForeground(Color.BLACK);
		rdbNao.setBackground(Color.WHITE);
		rdbNao.setEnabled(false);
		rdbNao.setBounds(10, 227, 131, 23);
		frame.getContentPane().add(rdbNao);
		
		txtRamoAtv = new JTextField();
		txtRamoAtv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRamoAtv.setEnabled(false);
		txtRamoAtv.setBounds(125, 266, 136, 20);
		frame.getContentPane().add(txtRamoAtv);
		txtRamoAtv.setColumns(10);
		
		txtQtdOcorrencia = new JTextField();
		txtQtdOcorrencia.setEnabled(false);
		txtQtdOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQtdOcorrencia.setBounds(125, 292, 136, 20);
		frame.getContentPane().add(txtQtdOcorrencia);
		txtQtdOcorrencia.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarDados();
			}
		});
		btnConsultar.setBackground(Color.ORANGE);
		btnConsultar.setForeground(SystemColor.text);
		btnConsultar.setBounds(329, 31, 94, 20);
		frame.getContentPane().add(btnConsultar);
		
		JButton btnFatura = new JButton("Faturamento");
		btnFatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularFaturamento();
			}
		});
		btnFatura.setForeground(SystemColor.text);
		btnFatura.setBackground(Color.ORANGE);
		btnFatura.setBounds(280, 230, 131, 20);
		frame.getContentPane().add(btnFatura);
		
		JLabel lbData = new JLabel("Data:");
		lbData.setForeground(Color.BLACK);
		lbData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbData.setBounds(10, 109, 46, 14);
		frame.getContentPane().add(lbData);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(209, 30, 119, 20);
		frame.getContentPane().add(txtTelefone);
		
		
		rdbResidencial.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(rdbResidencial.isSelected()) {
					rdbSim.setEnabled(true);
					rdbNao.setEnabled(true);
					
					rdbComercial.setSelected(false);
					rdbEspecializado.setSelected(false);
				}else {
					rdbSim.setEnabled(false);
					rdbNao.setEnabled(false);
					
					rdbSim.setSelected(false);
					rdbNao.setSelected(false);
				}
			}
		});
		rdbResidencial.setForeground(Color.BLACK);
		rdbResidencial.setBackground(Color.WHITE);
		rdbResidencial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbResidencial.setBounds(10, 166, 109, 23);
		frame.getContentPane().add(rdbResidencial);		
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().equals("")|| txtTelefone.getText().equals("") || txtEndereco.getText().equals("") || txtData.getText().equals("")  ) {
					JOptionPane.showMessageDialog(btnCadastrar, "Data inv�lida");
				} else {
					addNewCliente();
					limparCampos();
				}
				
			}
		});
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(Color.ORANGE);
		btnCadastrar.setBounds(280, 201, 131, 23);
		frame.getContentPane().add(btnCadastrar);
	}
	
	public void calcularFaturamento() {
		Double valorTotal = 0.0;
		Double percent = 0.0;
		
		for(TipoDeLinha lista : listaTelefonica) {
			valorTotal += lista.calcularTarifa();
			if (lista.getCliente().getEndereco().contains("Blumenau")) {
	            percent += valorTotal * 1.09;
	            String formatted = String.format("%.2f", percent);
	            JOptionPane.showMessageDialog(null, "Valor básico a pagar: R$" + valorTotal + 
			            " + 9% R$: " + formatted);
			}else {
				 JOptionPane.showMessageDialog(null, "Valor básico a pagar: R$" + valorTotal);
			}
		}
	}
	
	public void consultarDados() {
		
		for(TipoDeLinha lista: listaTelefonica) {

			if(txtTelefone.getText().equals(lista.getTelefone())) {
				if(lista.getTipoLinha() == "Telefone Residencial") {
					showMessageTipoResidencial(lista);
				}
				if(lista.getTipoLinha() == "Telefone Comercial") {
					showMessageTipoComercial(lista);
				}else {
					showMessageTipoEspecializado(lista);
				}
			}
		}
		
	}
	
	public void showMessageTipoEspecializado(TipoDeLinha lista) {
		JOptionPane.showMessageDialog(null, 
				" Cliente: "+lista.getCliente().getNome()+
				"\n Telefone: "+lista.getTelefone()+
				"\n Endere�o: "+lista.getCliente().getEndereco()+
				"\n Data de Instala��o: "+lista.getData()+
				"\n Tipo de Linha: "+lista.getTipoLinha()+
				"\n Quantidade de ocorr�ncia "+lista.getQtdOcorrencias()+
				"\n Valor da tarifa: "+lista.calcularTarifa());
	}
	
	public void showMessageTipoComercial(TipoDeLinha lista) {
		JOptionPane.showMessageDialog(null, 
				" Cliente: "+lista.getCliente().getNome()+
				"\n Telefone: "+lista.getTelefone()+
				"\n Endere�o: "+lista.getCliente().getEndereco()+
				"\n Data de Instala��o: "+lista.getData()+
				"\n Tipo de Linha: "+lista.getTipoLinha()+
				"\n Ramo de Atividade: "+lista.getRamoAtv()+
				"\n Valor da tarifa: "+lista.calcularTarifa());
	}
	
	public void showMessageTipoResidencial(TipoDeLinha lista) {
		String conexaoInternet = null;
		
		if(lista.getInternet()) {
			conexaoInternet = "Sim";
		}else {
			conexaoInternet = "N�o";
		}
		
		JOptionPane.showMessageDialog(null, 
				" Cliente: "+lista.getCliente().getNome()+
				"\n Telefone: "+lista.getTelefone()+
				"\n Endere�o: "+lista.getCliente().getEndereco()+
				"\n Data de Instala��o: "+lista.getData()+
				"\n Tipo de Linha: "+lista.getTipoLinha()+
				"\n Conex�o com Internet: "+conexaoInternet+
				"\n Valor da tarifa: "+lista.calcularTarifa());
	}
	
	public void addNewCliente() {
		Cliente cliente = new Cliente(txtNome.getText(),
				txtEndereco.getText());
		
		TipoDeLinha tpLinha = new TipoDeLinha(cliente, 
				txtTelefone.getText(),txtData.getText());
	
		if(rdbResidencial.isSelected()) {
			tpLinha.setTipoLinha("Telefone Residencial");
			if(rdbSim.isSelected()){
				tpLinha.setinternet(true);						
			} else {
				tpLinha.setinternet(false);
			}
		}
		if(rdbComercial.isSelected()) {
			tpLinha.setTipoLinha("Telefone Comercial");
			tpLinha.setRamoAtv(txtRamoAtv.getText());
			
		}
		if(rdbEspecializado.isSelected()) {
			tpLinha.setTipoLinha("Telefone Especializado");
			tpLinha.setRamoAtv(txtRamoAtv.getText());
			tpLinha.setQtdOcorrencias(Integer.parseInt(txtQtdOcorrencia.getText()));
		}
		
		listaTelefonica.add(tpLinha);
	}
	
	public void limparCampos() {
		
		txtNome.setText(null);
		txtTelefone.setText(null);
		txtData.setText(null);
		txtEndereco.setText(null);
		txtQtdOcorrencia.setText(null);
		txtRamoAtv.setText(null);
		
		rdbResidencial.setSelected(false);
		rdbSim.setSelected(false);
		rdbNao.setSelected(false);
		rdbComercial.setSelected(false);
		rdbEspecializado.setSelected(false);
		
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
	}
}
