package view.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.Persiste;
import backend.Restaurante;
import backend.controller.Controller;
import backend.model.Pedido;
import backend.model.Produto;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class PedidoPanel extends JPanel {
	
	private static final long serialVersionUID = -596700861050796160L;
	private JTextField tfNomeCliente;
	private JTextField tfMesa;
	private JTextField tfPesquisa;
	private JButton btnAdd01;
	private JButton btnAdd02;
	private JButton btnExcluir01;
	private JButton btnExcluir02;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private Restaurante restaurante;
	private JList<String> listCardapio;
	private JList<String> listPedidos;
	private Controller<Produto> cardapio;
	private JComboBox<String> cbProdutos;
	private Controller<Pedido> pedidos;
	
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
		private ArrayList<String> getProdutos(String nomePesquisa) {

			ArrayList<String> nomes = cardapio.getNomes();
			ArrayList<String> nomesPesquisa = new ArrayList<String>();

			for (String nome : nomes) {
				if (contem(nome, nomePesquisa)) {
					nomesPesquisa.add(nome);
				}
			}

			return nomesPesquisa;
		}
		
		private void atualizaLista(JList<String> list, Controller<?> controller) {
			DefaultListModel<String> model = new DefaultListModel<String>();
			
			ArrayList<String> elements = controller.getNomes();
			
			for (String string : elements)
				model.addElement(string);

			list.setModel(model);
		}
		
		private void atualizaLista(JList<String> list, ArrayList<String> elements) {
			DefaultListModel<String> model = new DefaultListModel<String>();
			
			for (String string : elements)
				model.addElement(string);

			list.setModel(model);
			
		}
		
		private void atualizaComboBox(Controller<?> controller) {
			DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>(); 
			cbModel.addAll(controller.getNomes());
			cbProdutos.setModel(cbModel);
			cbProdutos.setSelectedIndex(cbModel.getSize() - 1);
		}
		
		private void selecionaPedido(Pedido selecionado) {
			tfNomeCliente.setText(selecionado.getNome());
			tfMesa.setText("" + selecionado.getMesa());
			atualizaComboBox(selecionado.getProdutos());
			mudaSalvarCancelar(true);
		}
		
		private void adicionaPedido() {
			pedidos.add(new Pedido());
			atualizaLista(listPedidos, pedidos);
			Persiste.salva(restaurante, "restaurante.txt");
		}
		
		private void removePedido() {
			
			ArrayList<String> selected = (ArrayList<String>) listPedidos.getSelectedValuesList();
			
			int opcao;
			int tamanho = selected.size();
			
			if (tamanho == 1)
				opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir o pedido de \"" + selected.get(0) + "\"?");
			else
				opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir os " + tamanho + " pedidos selecionados?");

			if (opcao != 0)
				return;
		
			for (String string : selected) 
				pedidos.remove(string);
			
			
			atualizaLista(listPedidos, pedidos);
			limpaSelecao();
			Persiste.salva(restaurante, "restaurante.txt");
		}
		
		private void salvaPedido(Pedido pedido) {
			
			try {
				pedido.setNome(tfNomeCliente.getText());
				pedido.setMesa(Integer.parseInt(tfMesa.getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				limpaSelecao();
				return;
			}
			
			atualizaLista(listPedidos, pedidos);
			limpaSelecao();
			Persiste.salva(restaurante, "restaurante.txt");
			
		}
		
		private void adicionaProduto(Produto produto, Pedido pedido) {
			try {	
				pedido.getProdutos().add(produto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			atualizaComboBox(pedido.getProdutos());
			limpaSelecao();
			Persiste.salva(restaurante, "restaurante.txt");
		}
		
		private void removeProdutos(Pedido pedido) {

			int opcao = JOptionPane.showConfirmDialog(this, "Você realmente deseja retirar os produtos de \"" + pedido.getNome() + "\"?");
			if (opcao != 0)
				return;
			pedido.getProdutos().clear();
			atualizaComboBox(pedido.getProdutos());
		}
		
		private void limpaCampos() {		
			tfNomeCliente.setText("");
			tfMesa.setText("");
			cbProdutos.setModel(new DefaultComboBoxModel<>());
		}
		
		private void limpaSelecao() {
			listCardapio.clearSelection();
			listPedidos.clearSelection();
			limpaCampos();
			mudaSalvarCancelar(false);
		}
		
		private void mudaSalvarCancelar(boolean mod) {
			btnSalvar.setEnabled(mod);
			btnCancelar.setEnabled(mod);
			tfNomeCliente.setEnabled(mod);
			tfMesa.setEnabled(mod);
			cbProdutos.setEnabled(mod);
			btnExcluir01.setEnabled(mod);
			btnExcluir02.setEnabled(mod);
			btnAdd01.setEnabled(mod);
			btnAdd02.setEnabled(!mod);
		}
	
	public PedidoPanel(Restaurante restaurante) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				limpaSelecao();
				atualizaLista(listCardapio, cardapio);
				atualizaLista(listPedidos, pedidos);
			}
		});
		
		this.cardapio = restaurante.cardapio;
		this.pedidos = restaurante.pedidos;
		this.restaurante = restaurante;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{220, 0};
		gridBagLayout.rowHeights = new int[]{10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{327, 327, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel pnlCardapio = new JPanel();
		pnlCardapio.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)), "Card\u00E1pio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_pnlCardapio = new GridBagConstraints();
		gbc_pnlCardapio.insets = new Insets(5, 5, 5, 5);
		gbc_pnlCardapio.fill = GridBagConstraints.BOTH;
		gbc_pnlCardapio.gridx = 0;
		gbc_pnlCardapio.gridy = 0;
		panel.add(pnlCardapio, gbc_pnlCardapio);
		GridBagLayout gbl_pnlCardapio = new GridBagLayout();
		gbl_pnlCardapio.columnWidths = new int[]{0, 158, 0};
		gbl_pnlCardapio.rowHeights = new int[]{0, 0, 0};
		gbl_pnlCardapio.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCardapio.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlCardapio.setLayout(gbl_pnlCardapio);
		
		JLabel lblPesquisa = new JLabel("Pesquisa:");
		GridBagConstraints gbc_lblPesquisa = new GridBagConstraints();
		gbc_lblPesquisa.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesquisa.anchor = GridBagConstraints.EAST;
		gbc_lblPesquisa.gridx = 0;
		gbc_lblPesquisa.gridy = 0;
		pnlCardapio.add(lblPesquisa, gbc_lblPesquisa);
		
		tfPesquisa = new JTextField();
		GridBagConstraints gbc_tfPesquisa = new GridBagConstraints();
		gbc_tfPesquisa.insets = new Insets(0, 0, 5, 0);
		gbc_tfPesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPesquisa.gridx = 1;
		gbc_tfPesquisa.gridy = 0;
		pnlCardapio.add(tfPesquisa, gbc_tfPesquisa);
		tfPesquisa.setColumns(10);
		
		tfPesquisa.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				atualizaLista(listCardapio, getProdutos(tfPesquisa.getText()));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				atualizaLista(listCardapio, getProdutos(tfPesquisa.getText()));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		pnlCardapio.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_4.add(scrollPane_1, gbc_scrollPane_1);
		
		listCardapio = new JList<String>();
		scrollPane_1.setViewportView(listCardapio);
		
		JPanel pnlList = new JPanel();
		pnlList.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_pnlList = new GridBagConstraints();
		gbc_pnlList.insets = new Insets(5, 5, 5, 0);
		gbc_pnlList.fill = GridBagConstraints.BOTH;
		gbc_pnlList.gridx = 1;
		gbc_pnlList.gridy = 0;
		panel.add(pnlList, gbc_pnlList);
		GridBagLayout gbl_pnlList = new GridBagLayout();
		gbl_pnlList.columnWidths = new int[]{0, 0};
		gbl_pnlList.rowHeights = new int[]{0, 0, 0};
		gbl_pnlList.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlList.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlList.setLayout(gbl_pnlList);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(5, 2, 5, 2);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		pnlList.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNomeCliente = new JLabel("Nome cliente:");
		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeCliente.anchor = GridBagConstraints.EAST;
		gbc_lblNomeCliente.gridx = 0;
		gbc_lblNomeCliente.gridy = 0;
		panel_2.add(lblNomeCliente, gbc_lblNomeCliente);
		
		tfNomeCliente = new JTextField();
		GridBagConstraints gbc_tfNomeCliente = new GridBagConstraints();
		gbc_tfNomeCliente.gridwidth = 2;
		gbc_tfNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_tfNomeCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeCliente.gridx = 1;
		gbc_tfNomeCliente.gridy = 0;
		panel_2.add(tfNomeCliente, gbc_tfNomeCliente);
		tfNomeCliente.setColumns(10);
		
		JLabel lblMesa = new JLabel("Mesa:");
		GridBagConstraints gbc_lblMesa = new GridBagConstraints();
		gbc_lblMesa.anchor = GridBagConstraints.EAST;
		gbc_lblMesa.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesa.gridx = 0;
		gbc_lblMesa.gridy = 1;
		panel_2.add(lblMesa, gbc_lblMesa);
		
		tfMesa = new JTextField();
		GridBagConstraints gbc_tfMesa = new GridBagConstraints();
		gbc_tfMesa.gridwidth = 2;
		gbc_tfMesa.insets = new Insets(0, 0, 5, 5);
		gbc_tfMesa.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMesa.gridx = 1;
		gbc_tfMesa.gridy = 1;
		panel_2.add(tfMesa, gbc_tfMesa);
		tfMesa.setColumns(10);
		
		JLabel lblProdutos = new JLabel("Produtos:");
		GridBagConstraints gbc_lblProdutos = new GridBagConstraints();
		gbc_lblProdutos.anchor = GridBagConstraints.EAST;
		gbc_lblProdutos.insets = new Insets(0, 0, 0, 5);
		gbc_lblProdutos.gridx = 0;
		gbc_lblProdutos.gridy = 2;
		panel_2.add(lblProdutos, gbc_lblProdutos);
		
		cbProdutos = new JComboBox<String>(new DefaultComboBoxModel<String>());
		GridBagConstraints gbc_cbProdutos = new GridBagConstraints();
		gbc_cbProdutos.insets = new Insets(0, 0, 0, 5);
		gbc_cbProdutos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbProdutos.gridx = 1;
		gbc_cbProdutos.gridy = 2;
		panel_2.add(cbProdutos, gbc_cbProdutos);
		
		btnExcluir01 = new JButton("X");
		GridBagConstraints gbc_btnExcluir01 = new GridBagConstraints();
		gbc_btnExcluir01.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExcluir01.gridx = 2;
		gbc_btnExcluir01.gridy = 2;
		panel_2.add(btnExcluir01, gbc_btnExcluir01);
		btnExcluir01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeProdutos(pedidos.get(listPedidos.getSelectedIndex()));
			}
		});
		btnExcluir01.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(2, 2, 2, 2);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		pnlList.add(scrollPane, gbc_scrollPane);
		
		listPedidos = new JList<String>();
		scrollPane.setViewportView(listPedidos);
		listPedidos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				String pedidoNome = (String)listPedidos.getSelectedValue();
				Pedido pedido = pedidos.get(pedidoNome);
				if (pedido == null)
					return;
				selecionaPedido(pedido);
			}
		});
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnAdd01 = new JButton("Adicionar produto");
		btnAdd01.setEnabled(false);
		btnAdd01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String produtoNome = (String)listCardapio.getSelectedValue();
				if (produtoNome == null)
					return;
				Produto produto = cardapio.get(produtoNome);
				String pedidoNome = (String)listPedidos.getSelectedValue();
				if (pedidoNome == null)
					return;
				Pedido pedido = pedidos.get(pedidoNome);
				
				adicionaProduto(produto, pedido);
				
			}
		});
		GridBagConstraints gbc_btnAdd01 = new GridBagConstraints();
		gbc_btnAdd01.anchor = GridBagConstraints.WEST;
		gbc_btnAdd01.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd01.gridx = 0;
		gbc_btnAdd01.gridy = 0;
		panel_1.add(btnAdd01, gbc_btnAdd01);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				salvaPedido(pedidos.get(listPedidos.getSelectedIndex()));
			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.anchor = GridBagConstraints.EAST;
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 0;
		panel_3.add(btnSalvar, gbc_btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaSelecao();
			}
		});
		btnCancelar.setEnabled(false);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 0;
		panel_3.add(btnCancelar, gbc_btnCancelar);
		
		btnAdd02 = new JButton("Adicionar");
		btnAdd02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionaPedido();
			}
		});
		GridBagConstraints gbc_btnAdd02 = new GridBagConstraints();
		gbc_btnAdd02.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd02.gridx = 3;
		gbc_btnAdd02.gridy = 0;
		panel_3.add(btnAdd02, gbc_btnAdd02);
		
		btnExcluir02 = new JButton("Excluir");
		btnExcluir02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				removePedido();
			}
		});
		btnExcluir02.setEnabled(false);
		GridBagConstraints gbc_btnExcluir02 = new GridBagConstraints();
		gbc_btnExcluir02.anchor = GridBagConstraints.WEST;
		gbc_btnExcluir02.gridx = 4;
		gbc_btnExcluir02.gridy = 0;
		panel_3.add(btnExcluir02, gbc_btnExcluir02);
	}

}
