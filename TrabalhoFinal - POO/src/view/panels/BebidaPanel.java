package view.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.Persiste;
import backend.Restaurante;
import backend.controller.Controller;
import backend.model.Ingrediente;
import backend.model.Bebida;
import backend.model.Produto;

import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

public class BebidaPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel01;
	private JTextField tfNome;
	private JTextField tfPreco;
	private JEditorPane jeObservacao;
	private JList<String> listBebida;
	private JSpinner spinnerPeso;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir01;
	private JButton btnAdd01;
	private JButton btnAdd02;
	private JButton btnExcluir02;
	private JList<String> listIngrediente;
	private JList<String> listBebidaIngrediente;
	
	private Restaurante restaurante;
	private Controller<Bebida> bebidas;
	private Controller<Produto> cardapio;
	private Controller<Ingrediente> ingredientes;
	private JCheckBox chckbxAlcoolica;
	
	private void atualizaLista(Controller<?> controller, JList<String> list) {
		ArrayList<String> elements = controller.getNomes();
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (String str : elements)
			model.addElement(str);
		
		list.setModel(model);
	}
	
	private void atualizaListaIngrediente(Bebida bebida) {
		
		DefaultListModel<String> model01 = new DefaultListModel<String>();
		DefaultListModel<String> model02 = new DefaultListModel<String>();
		
		for (String str : ingredientes.getNomes()) {
			model01.addElement(str);
		}
		
		for (String str : bebida.getIngredientes().getNomes()) {
			model01.removeElement(str);
			model02.addElement(str);
		}
		
		listIngrediente.setModel(model01);
		listBebidaIngrediente.setModel(model02);
		
	}

	private void adicionaBebida() {
		
		Bebida bebida = new Bebida();
		bebidas.add(bebida);
		cardapio.add(bebida);
		atualizaLista(bebidas, listBebida);
		Persiste.salva(restaurante, "restaurante.txt");
	}

	private void removeBebida() {
		ArrayList<String> selected = (ArrayList<String>) listBebida.getSelectedValuesList();
		
		int opcao;
		int tamanho = selected.size();
		
		if (tamanho == 1)
			opcao = JOptionPane.showConfirmDialog(panel01, "Você realmente deseja excluir o bebida \"" + selected.get(0) + "\"?");
		else
			opcao = JOptionPane.showConfirmDialog(panel01, "Você realmente deseja excluir os " + tamanho + " bebidas selecionados?");

		if (opcao != 0)
			return;
	
		for (String string : selected) {
			bebidas.remove(string);
			cardapio.remove(string);
		}
		
		atualizaLista(bebidas, listBebida);
		limpaSelecao();
		Persiste.salva(restaurante, "restaurante.txt");
	}
	
	private void selecionaBebida(Bebida selecionado) {
		mudaSalvarCancelar(true);
		tfNome.setText(selecionado.getNome());
		tfPreco.setText(String.format("%.2f", selecionado.getValor()));
		spinnerPeso.setValue(selecionado.getmL());
		jeObservacao.setText(selecionado.getDesc());
		chckbxAlcoolica.setSelected(selecionado.isAlcoolica());
		atualizaListaIngrediente(selecionado);
	}
	
	private void adicionaIngrediente(Bebida bebida) {
		int ingIndex = listIngrediente.getSelectedIndex();
		int bebidaIndex = listBebida.getSelectedIndex();
		
		if(ingIndex < 0 || bebidaIndex < 0) {
			JOptionPane.showMessageDialog(panel01, "Ingrediente e bebida devem ser selecionados.");
			return;
		}
		
		try {
			bebida.addIngrediente(ingredientes.get(listIngrediente.getSelectedValue()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "O ingrediente '" + ingredientes.get(ingIndex).getNome() + "' Já está no bebida '" + bebida.getNome() + "'");
		}
		atualizaListaIngrediente(bebida);
		Persiste.salva(restaurante, "restaurante.txt");
	}
	
	private void removeIngrediente(Bebida bebida) {
		int ingIndex = listBebidaIngrediente.getSelectedIndex();
		int bebidaIndex = listBebida.getSelectedIndex();
		
		if(ingIndex < 0 || bebidaIndex < 0) {
			JOptionPane.showMessageDialog(panel01, "Ingrediente e bebida devem ser selecionados.");
			return;
		}
		
		bebida.removeIngrediente(bebida.getIngredientes().get(ingIndex).getNome());
		atualizaListaIngrediente(bebida);
		Persiste.salva(restaurante, "restaurante.txt");
	}
	
	private void limpaCampos() {		
		tfNome.setText("");
		tfPreco.setText("");
		spinnerPeso.setValue(50);
		jeObservacao.setText("");
		chckbxAlcoolica.setSelected(false);
		listIngrediente.setModel(new DefaultListModel<String>());
		listBebidaIngrediente.setModel(new DefaultListModel<String>());
	}
	
	private void limpaSelecao() {
		listBebida.clearSelection();
		limpaCampos();
		mudaSalvarCancelar(false);
	}

	private void mudaSalvarCancelar(boolean mod) {
		btnSalvar.setEnabled(mod);
		btnCancelar.setEnabled(mod);
		btnExcluir01.setEnabled(mod);
		btnExcluir02.setEnabled(mod);
		tfNome.setEnabled(mod);
		tfPreco.setEnabled(mod);
		jeObservacao.setEnabled(mod);
		spinnerPeso.setEnabled(mod);
		chckbxAlcoolica.setEnabled(mod);
		btnAdd01.setEnabled(!mod);
		btnAdd02.setEnabled(mod);
	}
	
	private void salvaBebida(Bebida bebida) {

		Object mL = spinnerPeso.getValue();
		int mLInt;
		
		if (mL instanceof Double) {
			Double mLDouble = (Double) mL;
			mLInt = mLDouble.intValue();

		} else 
			mLInt = (Integer) mL;
		
		
		String precoString = tfPreco.getText();

		precoString = precoString.replace(",", ".");
		
		try {
			bebida.setNome(tfNome.getText());
			bebida.setValor(Float.parseFloat(precoString));
			bebida.setmL(mLInt);
			bebida.setAlcoolica(chckbxAlcoolica.isSelected());
			bebida.setDesc(jeObservacao.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			limpaSelecao();
			return;
		}
		
		atualizaLista(bebidas, listBebida);
		limpaSelecao();
		Persiste.salva(restaurante, "restaurante.txt");
		
	}

	public BebidaPanel(Restaurante restaurante) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				atualizaLista(bebidas, listBebida);
				limpaSelecao();
			}
		});
		
		this.bebidas = restaurante.bebidas;
		this.cardapio = restaurante.cardapio;
		this.ingredientes = restaurante.ingredientes;
		this.restaurante = restaurante;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel01 = new JPanel();
		GridBagConstraints gbc_panel01 = new GridBagConstraints();
		gbc_panel01.insets = new Insets(8, 8, 8, 8);
		gbc_panel01.fill = GridBagConstraints.BOTH;
		gbc_panel01.gridx = 0;
		gbc_panel01.gridy = 0;
		add(panel01, gbc_panel01);
		GridBagLayout gbl_panel01 = new GridBagLayout();
		gbl_panel01.columnWidths = new int[]{0, 0, 0};
		gbl_panel01.rowHeights = new int[]{0, 0};
		gbl_panel01.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel01.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel01.setLayout(gbl_panel01);
		
		JPanel panel02 = new JPanel();
		GridBagConstraints gbc_panel02 = new GridBagConstraints();
		gbc_panel02.insets = new Insets(0, 0, 0, 5);
		gbc_panel02.fill = GridBagConstraints.BOTH;
		gbc_panel02.gridx = 0;
		gbc_panel02.gridy = 0;
		panel01.add(panel02, gbc_panel02);
		GridBagLayout gbl_panel02 = new GridBagLayout();
		gbl_panel02.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel02.rowHeights = new int[]{30, 30, 30, 200, 0, 0};
		gbl_panel02.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel02.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel02.setLayout(gbl_panel02);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel02.add(lblNome, gbc_lblNome);
		
		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.gridwidth = 4;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 0;
		panel02.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preço:");
		GridBagConstraints gbc_lblPreco = new GridBagConstraints();
		gbc_lblPreco.anchor = GridBagConstraints.EAST;
		gbc_lblPreco.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreco.gridx = 0;
		gbc_lblPreco.gridy = 1;
		panel02.add(lblPreco, gbc_lblPreco);
		
		tfPreco = new JTextField();
		GridBagConstraints gbc_tfPreco = new GridBagConstraints();
		gbc_tfPreco.insets = new Insets(0, 0, 5, 5);
		gbc_tfPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPreco.gridx = 1;
		gbc_tfPreco.gridy = 1;
		panel02.add(tfPreco, gbc_tfPreco);
		tfPreco.setColumns(10);
		
		JLabel lblPeso = new JLabel("mL:");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 2;
		gbc_lblPeso.gridy = 1;
		panel02.add(lblPeso, gbc_lblPeso);
		
		spinnerPeso = new JSpinner();
		spinnerPeso.setModel(new SpinnerNumberModel(50, 50, 10000, 50));
		GridBagConstraints gbc_spinnerPeso = new GridBagConstraints();
		gbc_spinnerPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerPeso.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerPeso.gridx = 3;
		gbc_spinnerPeso.gridy = 1;
		panel02.add(spinnerPeso, gbc_spinnerPeso);
		
		chckbxAlcoolica = new JCheckBox("Alcoólica");
		GridBagConstraints gbc_chckbxAlcoolica = new GridBagConstraints();
		gbc_chckbxAlcoolica.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAlcoolica.gridx = 4;
		gbc_chckbxAlcoolica.gridy = 1;
		panel02.add(chckbxAlcoolica, gbc_chckbxAlcoolica);
		
		JLabel lblObs = new JLabel("Observações:");
		GridBagConstraints gbc_lblObs = new GridBagConstraints();
		gbc_lblObs.anchor = GridBagConstraints.NORTH;
		gbc_lblObs.insets = new Insets(0, 0, 5, 5);
		gbc_lblObs.gridx = 0;
		gbc_lblObs.gridy = 2;
		panel02.add(lblObs, gbc_lblObs);
		
		jeObservacao = new JEditorPane();
		jeObservacao.setBorder(UIManager.getBorder("TextField.border"));
		GridBagConstraints gbc_jeObservacao = new GridBagConstraints();
		gbc_jeObservacao.gridwidth = 4;
		gbc_jeObservacao.insets = new Insets(0, 0, 5, 0);
		gbc_jeObservacao.fill = GridBagConstraints.BOTH;
		gbc_jeObservacao.gridx = 1;
		gbc_jeObservacao.gridy = 2;
		panel02.add(jeObservacao, gbc_jeObservacao);
		
		JPanel panel03 = new JPanel();
		panel03.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)), "Ingredientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel03 = new GridBagConstraints();
		gbc_panel03.insets = new Insets(0, 0, 5, 0);
		gbc_panel03.gridwidth = 5;
		gbc_panel03.fill = GridBagConstraints.BOTH;
		gbc_panel03.gridx = 0;
		gbc_panel03.gridy = 3;
		panel02.add(panel03, gbc_panel03);
		GridBagLayout gbl_panel03 = new GridBagLayout();
		gbl_panel03.columnWidths = new int[]{0, 0, 0};
		gbl_panel03.rowHeights = new int[]{0, 0};
		gbl_panel03.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel03.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel03.setLayout(gbl_panel03);
		
		JPanel panel04 = new JPanel();
		GridBagConstraints gbc_panel04 = new GridBagConstraints();
		gbc_panel04.gridwidth = 2;
		gbc_panel04.insets = new Insets(5, 5, 0, 0);
		gbc_panel04.fill = GridBagConstraints.BOTH;
		gbc_panel04.gridx = 0;
		gbc_panel04.gridy = 0;
		panel03.add(panel04, gbc_panel04);
		GridBagLayout gbl_panel04 = new GridBagLayout();
		gbl_panel04.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel04.rowHeights = new int[]{0, 0, 0};
		gbl_panel04.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel04.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel04.setLayout(gbl_panel04);
		
		JScrollPane scrollPane02 = new JScrollPane();
		GridBagConstraints gbc_scrollPane02 = new GridBagConstraints();
		gbc_scrollPane02.gridheight = 2;
		gbc_scrollPane02.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane02.fill = GridBagConstraints.BOTH;
		gbc_scrollPane02.gridx = 0;
		gbc_scrollPane02.gridy = 0;
		panel04.add(scrollPane02, gbc_scrollPane02);
		
		listIngrediente = new JList<String>();
		scrollPane02.setViewportView(listIngrediente);
		
		btnAdd02 = new JButton(">>");
		btnAdd02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bebida bebida = bebidas.get((String)listBebida.getSelectedValue());
				if (bebida == null)
					return;
				adicionaIngrediente(bebida);
				Persiste.salva(restaurante, "restaurante.txt");
			}
		});
		GridBagConstraints gbc_btnAdd02 = new GridBagConstraints();
		gbc_btnAdd02.anchor = GridBagConstraints.SOUTH;
		gbc_btnAdd02.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd02.gridx = 1;
		gbc_btnAdd02.gridy = 0;
		panel04.add(btnAdd02, gbc_btnAdd02);
		
		JScrollPane scrollPane03 = new JScrollPane();
		GridBagConstraints gbc_scrollPane03 = new GridBagConstraints();
		gbc_scrollPane03.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane03.gridheight = 2;
		gbc_scrollPane03.fill = GridBagConstraints.BOTH;
		gbc_scrollPane03.gridx = 2;
		gbc_scrollPane03.gridy = 0;
		panel04.add(scrollPane03, gbc_scrollPane03);
		
		listBebidaIngrediente = new JList<String>();
		scrollPane03.setViewportView(listBebidaIngrediente);
		
		btnExcluir02 = new JButton("<<");
		btnExcluir02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bebida bebida = bebidas.get((String)listBebida.getSelectedValue());
				if (bebida == null)
					return;
				removeIngrediente(bebida);
				Persiste.salva(restaurante, "restaurante.txt");
			}
		});
		GridBagConstraints gbc_btnExcluir02 = new GridBagConstraints();
		gbc_btnExcluir02.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir02.anchor = GridBagConstraints.NORTH;
		gbc_btnExcluir02.gridx = 1;
		gbc_btnExcluir02.gridy = 1;
		panel04.add(btnExcluir02, gbc_btnExcluir02);
		
		JPanel panel05 = new JPanel();
		GridBagConstraints gbc_panel05 = new GridBagConstraints();
		gbc_panel05.gridwidth = 5;
		gbc_panel05.fill = GridBagConstraints.BOTH;
		gbc_panel05.gridx = 0;
		gbc_panel05.gridy = 4;
		panel02.add(panel05, gbc_panel05);
		GridBagLayout gbl_panel05 = new GridBagLayout();
		gbl_panel05.columnWidths = new int[]{0, 0, 0};
		gbl_panel05.rowHeights = new int[]{0, 0};
		gbl_panel05.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel05.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel05.setLayout(gbl_panel05);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaBebida(bebidas.get((String)listBebida.getSelectedValue()));
			}
		});
		btnSalvar.setEnabled(false);
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 0;
		gbc_btnSalvar.gridy = 0;
		panel05.add(btnSalvar, gbc_btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaSelecao();
			}
		});
		btnCancelar.setEnabled(false);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel05.add(btnCancelar, gbc_btnCancelar);
		
		JPanel panel06 = new JPanel();
		GridBagConstraints gbc_panel06 = new GridBagConstraints();
		gbc_panel06.fill = GridBagConstraints.BOTH;
		gbc_panel06.gridx = 1;
		gbc_panel06.gridy = 0;
		panel01.add(panel06, gbc_panel06);
		GridBagLayout gbl_panel06 = new GridBagLayout();
		gbl_panel06.columnWidths = new int[]{0, 0, 0};
		gbl_panel06.rowHeights = new int[]{0, 0, 0};
		gbl_panel06.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel06.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel06.setLayout(gbl_panel06);
		
		JScrollPane scrollPane01 = new JScrollPane();
		GridBagConstraints gbc_scrollPane01 = new GridBagConstraints();
		gbc_scrollPane01.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane01.gridwidth = 2;
		gbc_scrollPane01.fill = GridBagConstraints.BOTH;
		gbc_scrollPane01.gridx = 0;
		gbc_scrollPane01.gridy = 0;
		panel06.add(scrollPane01, gbc_scrollPane01);
		
		listBebida = new JList<String>();
		scrollPane01.setViewportView(listBebida);
		listBebida.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Bebida selecionado = bebidas.get((String)listBebida.getSelectedValue());
				if (selecionado == null) 
					return;
				selecionaBebida(selecionado);
				atualizaListaIngrediente(selecionado);
			}
		});
		
		btnAdd01 = new JButton("Adicionar");
		btnAdd01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionaBebida();
			}
		});
		GridBagConstraints gbc_btnAdd01 = new GridBagConstraints();
		gbc_btnAdd01.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd01.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd01.gridx = 0;
		gbc_btnAdd01.gridy = 1;
		panel06.add(btnAdd01, gbc_btnAdd01);
		
		btnExcluir01 = new JButton("Excluir");
		btnExcluir01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeBebida();
			}
		});
		btnExcluir01.setEnabled(false);
		GridBagConstraints gbc_btnExcluir01 = new GridBagConstraints();
		gbc_btnExcluir01.insets = new Insets(0, 0, 0, 1);
		gbc_btnExcluir01.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExcluir01.gridx = 1;
		gbc_btnExcluir01.gridy = 1;
		panel06.add(btnExcluir01, gbc_btnExcluir01);

	}

}
