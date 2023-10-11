package view.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.Persiste;
import backend.Restaurante;
import backend.controller.Controller;
import backend.controller.FuncionarioManager;
import backend.model.Funcionario;

import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FuncionarioPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller<Funcionario> funcionarios;
	private FuncionarioManager importData;
	private JPanel panel;
	private Restaurante restaurante;
	private JTextField tfNome;
	private JTextField tfCargo;
	private JSpinner spinnerHora;
	private JSpinner spinnerMinuto;
	private JList<String> list;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnAdd;
	private JButton btnImportar;
	
	private void atualizaLista() {
		
		ArrayList<String> elements = funcionarios.getNomes();
		
		DefaultListModel<String> model = new DefaultListModel<String>();

		for (String str : elements)
			model.addElement(str);

		list.setModel(model);
	}
	
	private void salvaFuncionario(Funcionario funcionario) {
		
		String nome = tfNome.getText();
		String cargo =  tfCargo.getText();
		
		Object hora = spinnerHora.getValue();
		int horaInt;
		
		if (hora instanceof Double) {
			Double horaDouble = (Double) hora;
			horaInt = horaDouble.intValue();

		} else 
			horaInt = (Integer) hora;
		
		Object minuto = spinnerMinuto.getValue();
		int minutoInt;
		
		if (minuto instanceof Double) {
			Double minutoDouble = (Double) minuto;
			minutoInt = minutoDouble.intValue();

		} else 
			minutoInt = (Integer) minuto;
		
		String horaString = "";
		String minutoString = "";
		
		if (horaInt < 9) 
			horaString = "0" + horaInt;
		else
			horaString = "" + horaInt;
		
		if (minutoInt < 9)
			minutoString = "0" + minutoInt;
		else
			minutoString = "" + minutoInt;
		try {
			funcionario.setNome(nome);
			funcionario.setCargo(cargo);

			funcionario.setHorarioTrabalho(horaString + ":" + minutoString);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		Persiste.salva(restaurante,"restaurante.txt");
		atualizaLista();
		mudaSalvarCancelar(false);
		limpaSelecao();
	}
	
	private void adicionaFuncionario() {
		funcionarios.add(new Funcionario());
		Persiste.salva(restaurante,"restaurante.txt");
		atualizaLista();
		limpaSelecao();
	}
	
	private void importaFuncionarios() {
		importData = new FuncionarioManager();
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JavaScript Object Notation (*.json)", "json");
		fc.setFileFilter(filter);
		int opcao = fc.showOpenDialog(this);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
           try {
				importData.adicionarFuncionarioFromJSON(fc.getSelectedFile().toString());
				ArrayList<Funcionario> dadosImportados = importData.getFuncionarios();
				
				for (Funcionario funcionario : dadosImportados) 
					funcionarios.add(funcionario);
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(panel, "Ocorreu um erro ao importar o arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
        }
		Persiste.salva(restaurante,"restaurante.txt");
		limpaSelecao();
		atualizaLista();
	}
	
	private void removeFuncionario() {
		
		ArrayList<String> selected = (ArrayList<String>) list.getSelectedValuesList();		
		int opcao;
		int tamanho = selected.size();
		
		if (tamanho == 1)
			opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir o funcionário \"" + selected.get(0) + "\"?");
		else
			opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir os " + tamanho + " funcinários selecionados?");

		if (opcao != 0)
			return;
	
		for (String string : selected) 
			funcionarios.remove(string);

		Persiste.salva(restaurante,"restaurante.txt");
		atualizaLista();
		limpaSelecao();
	}
	
	private void limpaSelecao() {
		list.clearSelection();
		limpaCampos();
		mudaSalvarCancelar(false);
	}
	
	private void limpaCampos() {
		tfNome.setText("");
		tfCargo.setText("");
		spinnerHora.setValue(0);
		spinnerMinuto.setValue(0);
	}
	
	private void selecionaFuncionario(Funcionario selecionado) {
		tfNome.setText(selecionado.getNome());
		tfCargo.setText(selecionado.getCargo());
		spinnerHora.setValue(Integer.parseInt(selecionado.getHorarioTrabalho().substring(0, 2)));
		spinnerMinuto.setValue(Integer.parseInt(selecionado.getHorarioTrabalho().substring(3)));
		mudaSalvarCancelar(true);
	}
	
	private void mudaSalvarCancelar(boolean mod) {
		btnSalvar.setEnabled(mod);
		btnCancelar.setEnabled(mod);
		tfNome.setEnabled(mod);
		tfCargo.setEnabled(mod);
		spinnerHora.setEnabled(mod);
		spinnerMinuto.setEnabled(mod);
		btnExcluir.setEnabled(mod);
		btnAdd.setEnabled(!mod);
		btnImportar.setEnabled(!mod);
	}

	public FuncionarioPanel(Restaurante restaurante) {
		this.restaurante = restaurante;
		this.funcionarios = restaurante.funcionarios;
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				atualizaLista();
				limpaSelecao();
			}
		});
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNomeFuncionario = new JLabel("Nome:");
		GridBagConstraints gbc_lblNomeFuncionario = new GridBagConstraints();
		gbc_lblNomeFuncionario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeFuncionario.gridx = 0;
		gbc_lblNomeFuncionario.gridy = 0;
		panel_1.add(lblNomeFuncionario, gbc_lblNomeFuncionario);
		
		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 0;
		panel_1.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo:");
		GridBagConstraints gbc_lblCargo = new GridBagConstraints();
		gbc_lblCargo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCargo.gridx = 0;
		gbc_lblCargo.gridy = 1;
		panel_1.add(lblCargo, gbc_lblCargo);
		
		tfCargo = new JTextField();
		GridBagConstraints gbc_tfCargo = new GridBagConstraints();
		gbc_tfCargo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCargo.insets = new Insets(0, 0, 5, 0);
		gbc_tfCargo.gridx = 1;
		gbc_tfCargo.gridy = 1;
		panel_1.add(tfCargo, gbc_tfCargo);
		tfCargo.setColumns(10);
		
		JLabel lblHorario = new JLabel("Horário:");
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.insets = new Insets(0, 0, 0, 5);
		gbc_lblHorario.gridx = 0;
		gbc_lblHorario.gridy = 2;
		panel_1.add(lblHorario, gbc_lblHorario);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.WEST;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 2;
		panel_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		spinnerHora = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		GridBagConstraints gbc_spinnerHora = new GridBagConstraints();
		gbc_spinnerHora.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerHora.gridx = 0;
		gbc_spinnerHora.gridy = 0;
		panel_4.add(spinnerHora, gbc_spinnerHora);
		
		JLabel lblDoisPontos = new JLabel(":\r\n");
		GridBagConstraints gbc_lblDoisPontos = new GridBagConstraints();
		gbc_lblDoisPontos.insets = new Insets(0, 0, 0, 5);
		gbc_lblDoisPontos.gridx = 1;
		gbc_lblDoisPontos.gridy = 0;
		panel_4.add(lblDoisPontos, gbc_lblDoisPontos);
		
		spinnerMinuto = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		GridBagConstraints gbc_spinnerMinuto = new GridBagConstraints();
		gbc_spinnerMinuto.gridx = 2;
		gbc_spinnerMinuto.gridy = 0;
		panel_4.add(spinnerMinuto, gbc_spinnerMinuto);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_2.add(scrollPane, gbc_scrollPane);
		
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				String funcionarioNome = list.getSelectedValue();
				Funcionario funcionario = funcionarios.get(funcionarioNome);
				if (funcionario == null)
					return;
				
				selecionaFuncionario(funcionario);
			}
		});
		
		scrollPane.setViewportView(list);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 0;
		gbc_btnSalvar.gridy = 0;
		panel_3.add(btnSalvar, gbc_btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaSelecao();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_3.add(btnCancelar, gbc_btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeFuncionario();
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 2;
		gbc_btnExcluir.gridy = 0;
		panel_3.add(btnExcluir, gbc_btnExcluir);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionaFuncionario();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 0;
		panel_3.add(btnAdd, gbc_btnAdd);
		
		btnImportar = new JButton("Importar\r\n");
		GridBagConstraints gbc_btnImportar = new GridBagConstraints();
		gbc_btnImportar.anchor = GridBagConstraints.EAST;
		gbc_btnImportar.gridx = 4;
		gbc_btnImportar.gridy = 0;
		panel_3.add(btnImportar, gbc_btnImportar);
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importaFuncionarios();
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaFuncionario(funcionarios.get(list.getSelectedIndex()));
			}
		});

	}

}
