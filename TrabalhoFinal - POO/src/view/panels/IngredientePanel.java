package view.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.Persiste;
import backend.Restaurante;
import backend.controller.Controller;
import backend.model.Ingrediente;

public class IngredientePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfPreco;
	private JTextField tfPesquisa;
	private JSpinner spinnerQuantidade;
	private JList<String> list;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnAdd;

	private Restaurante restaurante;
	private Controller<Ingrediente> ingredientes;


	// Método contém que verifica se a String a contém a String b
	private boolean contem(String a, String b) {

		int q1 = a.length();
		int q2 = b.length();

		if (q1 < q2) {
			return false;
		}

		for (int i = 0; i < q2; i++) {

			char charA = a.toUpperCase().charAt(i);
			char charB = b.toUpperCase().charAt(i);

			if (charA != charB) {
				return false;
			}
		}
		return true;
	}

	// Método retorna os nomes de ingredientes que possuem a String nomePesquisa
	public ArrayList<String> getIngredientes(String nomePesquisa) {

		ArrayList<String> nomes = ingredientes.getNomes();
		ArrayList<String> nomesPesquisa = new ArrayList<String>();

		for (String nome : nomes) {
			if (contem(nome, nomePesquisa)) {
				nomesPesquisa.add(nome);
			}
		}

		return nomesPesquisa;
	}

	private void novoIngrediente() {
		ingredientes.add(new Ingrediente());
		atualizaLista();
		limpaSelecao();
		Persiste.salva(restaurante, "restaurante.txt");
	}

	private void excluiIngrediente() {
		ArrayList<String> selected = (ArrayList<String>) list.getSelectedValuesList();
		
		int opcao;
		int tamanho = selected.size();
		
		if (tamanho == 1)
			opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir o ingrediente \"" + selected.get(0) + "\"?");
		else
			opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir os " + tamanho + " ingredientes selecionados?");

		if (opcao != 0)
			return;
	
		for (String string : selected) {
			ingredientes.remove(string);
		}

		atualizaLista();
		limpaSelecao();
		Persiste.salva(restaurante, "restaurante.txt");
	}

	private int selecionaIngrediente() {

		String nome = (String) list.getSelectedValue();
		Ingrediente selecionado = ingredientes.get(nome);

		if (selecionado == null)
			return -1;

		tfNome.setText(selecionado.getNome());
		tfPreco.setText(String.format("%.2f", selecionado.getPreco()));
		spinnerQuantidade.setValue(selecionado.getQuantidade());

		return list.getSelectedIndex();
	}

	private void atualizaLista() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		ArrayList<String> elements = getIngredientes(tfPesquisa.getText());

		for (String string : elements)
			model.addElement(string);

		list.setModel(model);
	}
	
	private void limpaSelecao() {
		list.clearSelection();
		limpaCampos();
		mudaSalvarCancelar(false);
	}

	private void limpaCampos() {
		tfNome.setText("");
		tfPreco.setText("");
		spinnerQuantidade.setValue(0);
	}

	private void mudaSalvarCancelar(boolean mod) {
		btnSalvar.setEnabled(mod);
		btnCancelar.setEnabled(mod);
		tfNome.setEnabled(mod);
		tfPreco.setEnabled(mod);
		spinnerQuantidade.setEnabled(mod);
		btnExcluir.setEnabled(mod);
		btnAdd.setEnabled(!mod);
	}

	private void salvaIngrediente(Ingrediente selecionado) {
		try {
			String nome = tfNome.getText();
			float preco = Float.parseFloat(tfPreco.getText().replace(',', '.'));
			int qtd = Integer.parseInt(spinnerQuantidade.getValue().toString());
			selecionado.setNome(nome);
			selecionado.setPreco(preco);
			selecionado.setQuantidade(qtd);	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			limpaSelecao();
			return;
		}

		Persiste.salva(restaurante, "restaurante.txt");
		atualizaLista();
		limpaSelecao();
	}

	public IngredientePanel(Restaurante restaurante) {
		this.ingredientes = restaurante.ingredientes;
		this.restaurante = restaurante;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel01 = new JPanel();
		GridBagConstraints gbc_panel01 = new GridBagConstraints();
		gbc_panel01.insets = new Insets(5, 5, 5, 5);
		gbc_panel01.fill = GridBagConstraints.BOTH;
		gbc_panel01.gridx = 0;
		gbc_panel01.gridy = 0;
		add(panel01, gbc_panel01);
		GridBagLayout gbl_panel01 = new GridBagLayout();
		gbl_panel01.columnWidths = new int[] { 0, 0 };
		gbl_panel01.rowHeights = new int[] { 0, 0 };
		gbl_panel01.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel01.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel01.setLayout(gbl_panel01);

		JPanel panel02 = new JPanel();
		GridBagConstraints gbc_panel02 = new GridBagConstraints();
		gbc_panel02.fill = GridBagConstraints.BOTH;
		gbc_panel02.gridx = 0;
		gbc_panel02.gridy = 0;
		panel01.add(panel02, gbc_panel02);
		GridBagLayout gbl_panel02 = new GridBagLayout();
		gbl_panel02.columnWidths = new int[] { 0, 75, 348, 0 };
		gbl_panel02.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel02.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel02.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel02.setLayout(gbl_panel02);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		GridBagConstraints gbc_lblPesquisa = new GridBagConstraints();
		gbc_lblPesquisa.insets = new Insets(5, 0, 0, 5);
		gbc_lblPesquisa.anchor = GridBagConstraints.EAST;
		gbc_lblPesquisa.gridx = 0;
		gbc_lblPesquisa.gridy = 0;
		panel02.add(lblPesquisa, gbc_lblPesquisa);

		tfPesquisa = new JTextField();
		GridBagConstraints gbc_tfPesquisa = new GridBagConstraints();
		gbc_tfPesquisa.anchor = GridBagConstraints.NORTH;
		gbc_tfPesquisa.insets = new Insets(7, 0, 0, 5);
		gbc_tfPesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPesquisa.gridx = 1;
		gbc_tfPesquisa.gridy = 0;
		panel02.add(tfPesquisa, gbc_tfPesquisa);
		tfPesquisa.setColumns(10);

		tfPesquisa.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				atualizaLista();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				atualizaLista();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		JPanel panel03 = new JPanel();
		GridBagConstraints gbc_panel03 = new GridBagConstraints();
		gbc_panel03.gridwidth = 2;
		gbc_panel03.insets = new Insets(7, 0, 0, 5);
		gbc_panel03.fill = GridBagConstraints.BOTH;
		gbc_panel03.gridx = 0;
		gbc_panel03.gridy = 1;
		panel02.add(panel03, gbc_panel03);
		GridBagLayout gbl_panel03 = new GridBagLayout();
		gbl_panel03.columnWidths = new int[] { 0, 0 };
		gbl_panel03.rowHeights = new int[] { 0, 17, 0 };
		gbl_panel03.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel03.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel03.setLayout(gbl_panel03);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel03.add(scrollPane, gbc_scrollPane);

		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selecionaIngrediente();
				mudaSalvarCancelar(true);
			}
		});
		
		atualizaLista();

		JPanel panel04 = new JPanel();
		GridBagConstraints gbc_panel04 = new GridBagConstraints();
		gbc_panel04.insets = new Insets(0, 0, 1, 0);
		gbc_panel04.fill = GridBagConstraints.BOTH;
		gbc_panel04.gridx = 0;
		gbc_panel04.gridy = 1;
		panel03.add(panel04, gbc_panel04);
		GridBagLayout gbl_panel04 = new GridBagLayout();
		gbl_panel04.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel04.rowHeights = new int[] { 0, 0 };
		gbl_panel04.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel04.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel04.setLayout(gbl_panel04);

		btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoIngrediente();
				atualizaLista();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel04.add(btnAdd, gbc_btnAdd);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluiIngrediente();
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExcluir.gridx = 1;
		gbc_btnExcluir.gridy = 0;
		panel04.add(btnExcluir, gbc_btnExcluir);

		JPanel panel05 = new JPanel();
		panel05.setBorder(
				new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)),
						"Novo Ingrediente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel05 = new GridBagConstraints();
		gbc_panel05.gridheight = 2;
		gbc_panel05.fill = GridBagConstraints.BOTH;
		gbc_panel05.gridx = 2;
		gbc_panel05.gridy = 0;
		panel02.add(panel05, gbc_panel05);
		GridBagLayout gbl_panel05 = new GridBagLayout();
		gbl_panel05.columnWidths = new int[] { 0, 0 };
		gbl_panel05.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel05.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel05.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel05.setLayout(gbl_panel05);

		JPanel panel06 = new JPanel();
		GridBagConstraints gbc_panel06 = new GridBagConstraints();
		gbc_panel06.insets = new Insets(5, 5, 5, 5);
		gbc_panel06.fill = GridBagConstraints.BOTH;
		gbc_panel06.gridx = 0;
		gbc_panel06.gridy = 0;
		panel05.add(panel06, gbc_panel06);
		GridBagLayout gbl_panel06 = new GridBagLayout();
		gbl_panel06.columnWidths = new int[] { 0, 33, 0, 81, 0 };
		gbl_panel06.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel06.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel06.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel06.setLayout(gbl_panel06);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel06.add(lblNome, gbc_lblNome);

		tfNome = new JTextField();
		tfNome.setEnabled(false);
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.gridwidth = 3;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 0;
		panel06.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);

		JLabel lblPreco = new JLabel("Preço:");
		GridBagConstraints gbc_lblPreco = new GridBagConstraints();
		gbc_lblPreco.anchor = GridBagConstraints.EAST;
		gbc_lblPreco.insets = new Insets(0, 0, 0, 5);
		gbc_lblPreco.gridx = 0;
		gbc_lblPreco.gridy = 1;
		panel06.add(lblPreco, gbc_lblPreco);

		tfPreco = new JTextField();
		tfPreco.setEnabled(false);
		GridBagConstraints gbc_tfPreco = new GridBagConstraints();
		gbc_tfPreco.insets = new Insets(0, 0, 0, 5);
		gbc_tfPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPreco.gridx = 1;
		gbc_tfPreco.gridy = 1;
		panel06.add(tfPreco, gbc_tfPreco);
		tfPreco.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantidade.gridx = 2;
		gbc_lblQuantidade.gridy = 1;
		panel06.add(lblQuantidade, gbc_lblQuantidade);

		spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setEnabled(false);
		spinnerQuantidade.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		GridBagConstraints gbc_spinnerQuantidade = new GridBagConstraints();
		gbc_spinnerQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerQuantidade.gridx = 3;
		gbc_spinnerQuantidade.gridy = 1;
		panel06.add(spinnerQuantidade, gbc_spinnerQuantidade);

		JPanel panel07 = new JPanel();
		GridBagConstraints gbc_panel07 = new GridBagConstraints();
		gbc_panel07.insets = new Insets(5, 5, 5, 5);
		gbc_panel07.fill = GridBagConstraints.BOTH;
		gbc_panel07.gridx = 0;
		gbc_panel07.gridy = 1;
		panel05.add(panel07, gbc_panel07);
		GridBagLayout gbl_panel07 = new GridBagLayout();
		gbl_panel07.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel07.rowHeights = new int[] { 0, 0 };
		gbl_panel07.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel07.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel07.setLayout(gbl_panel07);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaIngrediente(ingredientes.get(list.getSelectedIndex()));
			}
		});
		btnSalvar.setEnabled(false);
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 0;
		gbc_btnSalvar.gridy = 0;
		panel07.add(btnSalvar, gbc_btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaSelecao();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel07.add(btnCancelar, gbc_btnCancelar);

	}

}
