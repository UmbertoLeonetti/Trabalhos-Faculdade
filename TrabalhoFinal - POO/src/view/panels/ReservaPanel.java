package view.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import backend.Persiste;
import backend.Restaurante;
import backend.controller.Controller;
import backend.model.Reserva;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class ReservaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Restaurante restaurante;
	private Controller<Reserva> reservas;
	private JPanel panel01;
	private JPanel panel02;
	private JPanel panel03;
	private JLabel lblNomeCliente;
	private JTextField tfCliente;
	private JLabel lblMesa;
	private JTextField tfMesa;
	private JLabel lblData;
	private JPanel panel04;
	private JComboBox<Integer> cbDia;
	private JComboBox<Integer> cbMes;
	private JComboBox<Integer> cbAno;
	private JLabel lblHorario;
	private JPanel panel05;
	private JSpinner spinnerHora;
	private JLabel lblDoisPontos;
	private JSpinner spinnerMinuto;
	private JPanel panel06;
	private JScrollPane scrollPane;
	private JPanel panel07;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JList<String> list;
	private JButton btnAdd;

	private void atualizaLista(Controller<?> controller, JList<String> list) {
		
		ArrayList<String> elements = controller.getNomes();
		
		DefaultListModel<String> model = new DefaultListModel<String>();

		for (String str : elements)
			model.addElement(str);

		list.setModel(model);
	}
	
	private void removeReserva() {
		ArrayList<String> selected = (ArrayList<String>) list.getSelectedValuesList();
		
		int opcao;
		int tamanho = selected.size();
		
		if (tamanho == 1)
			opcao = JOptionPane.showConfirmDialog(panel01, "Você realmente deseja excluir a reserva de \"" + selected.get(0) + "\"?");
		else
			opcao = JOptionPane.showConfirmDialog(panel01, "Você realmente deseja excluir as " + tamanho + " reservas selecionadas?");

		if (opcao != 0)
			return;
	
		for (String string : selected)
			reservas.remove(string);
		
		atualizaLista(reservas, list);
		limpaSelecao();
		Persiste.salva(restaurante, "restaurante.txt");
		
	}
	
	private void adicionaReserva() {
		reservas.add(new Reserva());
		Persiste.salva(restaurante, "restaurante.txt");
		atualizaLista(reservas, list);
	}
	
	private void salvaReserva(Reserva reserva) {
		LocalDate data;
		try {
			data = LocalDate.of((int) cbAno.getSelectedItem(), cbMes.getSelectedIndex() + 1, cbDia.getSelectedIndex() + 1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Data inválida.", "ERRO", JOptionPane.ERROR_MESSAGE);
			limpaSelecao();
			return;
		}
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
		
		LocalTime horaLocalTime = LocalTime.of(horaInt, minutoInt);

		
		try {
			reserva.setDataHorario(data, horaLocalTime);
			reserva.setMesa(Integer.parseInt(tfMesa.getText()));
			reserva.setNome(tfCliente.getText());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			limpaSelecao();
			return;
		}
		atualizaLista(reservas, list);
		limpaSelecao();
		Persiste.salva(restaurante, "restaurante.txt");
	}
	
	private void limpaSelecao() {
		list.clearSelection();
		mudaSalvarCancelar(false);
		limpaCampos();
	}

	private void limpaCampos() {
		LocalDate hoje = LocalDate.now();
		LocalTime agora = LocalTime.now();
		tfCliente.setText("");
		tfMesa.setText("");
		cbDia.setSelectedIndex(hoje.getDayOfMonth() - 1);
		cbMes.setSelectedIndex(hoje.getMonthValue() - 1);
		cbAno.setSelectedIndex(0);

		int hora = agora.getHour();
		int minuto = agora.getMinute();
		spinnerHora.setModel(new SpinnerNumberModel(hora, 0, 23, 1));
		spinnerMinuto.setModel(new SpinnerNumberModel(minuto, 0, 59, 1));

	}
	
	private int selecionaReserva(Reserva selecionado) {
		
		tfCliente.setText(selecionado.getNome());
		tfMesa.setText("" + selecionado.getMesa());
		cbDia.setSelectedIndex(selecionado.getData().getDayOfMonth() - 1);
		cbMes.setSelectedIndex(selecionado.getData().getMonthValue() - 1);
		cbAno.setSelectedItem(selecionado.getData().getYear());
		
		spinnerHora.setModel(new SpinnerNumberModel(selecionado.getHorario().getHour(), 0, 23, 1));
		spinnerMinuto.setModel(new SpinnerNumberModel(selecionado.getHorario().getMinute(), 0, 59, 1));
		
		mudaSalvarCancelar(true);
		return list.getSelectedIndex();
	}

	private void mudaSalvarCancelar(boolean mod) {
		btnSalvar.setEnabled(mod);
		btnCancelar.setEnabled(mod);
		tfCliente.setEnabled(mod);
		tfMesa.setEnabled(mod);
		cbDia.setEnabled(mod);
		cbMes.setEnabled(mod);
		cbAno.setEnabled(mod);
		spinnerHora.setEnabled(mod);
		spinnerMinuto.setEnabled(mod);
		btnExcluir.setEnabled(mod);
		btnAdd.setEnabled(!mod);
	}

	public ReservaPanel(Restaurante restaurante) {
		
		this.restaurante = restaurante;
		this.reservas = restaurante.reservas;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				atualizaLista(reservas, list);
				limpaSelecao();
			}
		});
		
		LocalDate hoje = LocalDate.now();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		panel01 = new JPanel();
		GridBagConstraints gbc_panel01 = new GridBagConstraints();
		gbc_panel01.fill = GridBagConstraints.BOTH;
		gbc_panel01.gridx = 0;
		gbc_panel01.gridy = 0;
		add(panel01, gbc_panel01);
		GridBagLayout gbl_panel01 = new GridBagLayout();
		gbl_panel01.columnWidths = new int[]{0, 0};
		gbl_panel01.rowHeights = new int[]{0, 0, 0};
		gbl_panel01.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel01.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel01.setLayout(gbl_panel01);
		
		panel02 = new JPanel();
		GridBagConstraints gbc_panel02 = new GridBagConstraints();
		gbc_panel02.insets = new Insets(0, 0, 5, 0);
		gbc_panel02.fill = GridBagConstraints.BOTH;
		gbc_panel02.gridx = 0;
		gbc_panel02.gridy = 0;
		panel01.add(panel02, gbc_panel02);
		GridBagLayout gbl_panel02 = new GridBagLayout();
		gbl_panel02.columnWidths = new int[]{0, 0};
		gbl_panel02.rowHeights = new int[]{0, 0, 0};
		gbl_panel02.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel02.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel02.setLayout(gbl_panel02);
		
		panel03 = new JPanel();
		GridBagConstraints gbc_panel03 = new GridBagConstraints();
		gbc_panel03.fill = GridBagConstraints.BOTH;
		gbc_panel03.insets = new Insets(0, 0, 5, 0);
		gbc_panel03.gridx = 0;
		gbc_panel03.gridy = 0;
		panel02.add(panel03, gbc_panel03);
		GridBagLayout gbl_panel03 = new GridBagLayout();
		gbl_panel03.columnWidths = new int[]{0, 0, 0};
		gbl_panel03.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel03.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel03.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel03.setLayout(gbl_panel03);
		
		lblNomeCliente = new JLabel("  Nome cliente:");
		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.anchor = GridBagConstraints.EAST;
		gbc_lblNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeCliente.gridx = 0;
		gbc_lblNomeCliente.gridy = 0;
		panel03.add(lblNomeCliente, gbc_lblNomeCliente);
		
		tfCliente = new JTextField();
		tfCliente.setColumns(10);
		GridBagConstraints gbc_tfCliente = new GridBagConstraints();
		gbc_tfCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCliente.insets = new Insets(0, 0, 5, 0);
		gbc_tfCliente.gridx = 1;
		gbc_tfCliente.gridy = 0;
		panel03.add(tfCliente, gbc_tfCliente);
		
		lblMesa = new JLabel("Mesa:");
		GridBagConstraints gbc_lblMesa = new GridBagConstraints();
		gbc_lblMesa.anchor = GridBagConstraints.EAST;
		gbc_lblMesa.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesa.gridx = 0;
		gbc_lblMesa.gridy = 1;
		panel03.add(lblMesa, gbc_lblMesa);
		
		tfMesa = new JTextField();
		tfMesa.setColumns(10);
		GridBagConstraints gbc_tfMesa = new GridBagConstraints();
		gbc_tfMesa.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMesa.insets = new Insets(0, 0, 5, 0);
		gbc_tfMesa.gridx = 1;
		gbc_tfMesa.gridy = 1;
		panel03.add(tfMesa, gbc_tfMesa);
		
		lblData = new JLabel("Data:");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.EAST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 0;
		gbc_lblData.gridy = 2;
		panel03.add(lblData, gbc_lblData);
		
		panel04 = new JPanel();
		GridBagConstraints gbc_panel04 = new GridBagConstraints();
		gbc_panel04.fill = GridBagConstraints.BOTH;
		gbc_panel04.insets = new Insets(0, 0, 5, 0);
		gbc_panel04.gridx = 1;
		gbc_panel04.gridy = 2;
		panel03.add(panel04, gbc_panel04);
		GridBagLayout gbl_panel04 = new GridBagLayout();
		gbl_panel04.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel04.rowHeights = new int[]{0, 0};
		gbl_panel04.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel04.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel04.setLayout(gbl_panel04);
		
		cbDia = new JComboBox<Integer>();
		GridBagConstraints gbc_cbDia = new GridBagConstraints();
		gbc_cbDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDia.insets = new Insets(0, 0, 0, 5);
		gbc_cbDia.gridx = 0;
		gbc_cbDia.gridy = 0;
		panel04.add(cbDia, gbc_cbDia);
		
		cbMes = new JComboBox<Integer>();
		GridBagConstraints gbc_cbMes = new GridBagConstraints();
		gbc_cbMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbMes.insets = new Insets(0, 0, 0, 5);
		gbc_cbMes.gridx = 1;
		gbc_cbMes.gridy = 0;
		panel04.add(cbMes, gbc_cbMes);
		
		cbAno = new JComboBox<Integer>();
		GridBagConstraints gbc_cbAno = new GridBagConstraints();
		gbc_cbAno.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbAno.gridx = 2;
		gbc_cbAno.gridy = 0;
		panel04.add(cbAno, gbc_cbAno);
		
		DefaultComboBoxModel<Integer> dias = new DefaultComboBoxModel<Integer>();

		for (int i = 1; i <= 31; i++) {
			dias.addElement(i);
		}
		cbDia.setModel(dias);
		
		DefaultComboBoxModel<Integer> meses = new DefaultComboBoxModel<Integer>();

		for (int i = 1; i <= 12; i++) {
			meses.addElement(i);
		}
		cbMes.setModel(meses);
		
		DefaultComboBoxModel<Integer> anos = new DefaultComboBoxModel<Integer>();

		int lastYear = hoje.getYear() + 1;

		for (int i = hoje.getYear(); i <= lastYear; i++) {
			anos.addElement(i);
		}
		
		cbAno.setModel(anos);
		
		lblHorario = new JLabel("Horário:");
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.EAST;
		gbc_lblHorario.insets = new Insets(0, 0, 0, 5);
		gbc_lblHorario.gridx = 0;
		gbc_lblHorario.gridy = 3;
		panel03.add(lblHorario, gbc_lblHorario);
		
		panel05 = new JPanel();
		GridBagConstraints gbc_panel05 = new GridBagConstraints();
		gbc_panel05.fill = GridBagConstraints.BOTH;
		gbc_panel05.gridx = 1;
		gbc_panel05.gridy = 3;
		panel03.add(panel05, gbc_panel05);
		GridBagLayout gbl_panel05 = new GridBagLayout();
		gbl_panel05.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel05.rowHeights = new int[]{0, 0};
		gbl_panel05.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel05.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel05.setLayout(gbl_panel05);
		
		spinnerHora = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		GridBagConstraints gbc_spinnerHora = new GridBagConstraints();
		gbc_spinnerHora.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerHora.gridx = 0;
		gbc_spinnerHora.gridy = 0;
		panel05.add(spinnerHora, gbc_spinnerHora);
		
		lblDoisPontos = new JLabel(":");
		GridBagConstraints gbc_lblDoisPontos = new GridBagConstraints();
		gbc_lblDoisPontos.insets = new Insets(0, 0, 0, 5);
		gbc_lblDoisPontos.gridx = 1;
		gbc_lblDoisPontos.gridy = 0;
		panel05.add(lblDoisPontos, gbc_lblDoisPontos);
		
		spinnerMinuto = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		GridBagConstraints gbc_spinnerMinuto = new GridBagConstraints();
		gbc_spinnerMinuto.gridx = 2;
		gbc_spinnerMinuto.gridy = 0;
		panel05.add(spinnerMinuto, gbc_spinnerMinuto);
		
		panel06 = new JPanel();
		GridBagConstraints gbc_panel06 = new GridBagConstraints();
		gbc_panel06.fill = GridBagConstraints.BOTH;
		gbc_panel06.gridx = 0;
		gbc_panel06.gridy = 1;
		panel02.add(panel06, gbc_panel06);
		GridBagLayout gbl_panel06 = new GridBagLayout();
		gbl_panel06.columnWidths = new int[]{0, 0};
		gbl_panel06.rowHeights = new int[]{0, 0};
		gbl_panel06.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel06.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel06.setLayout(gbl_panel06);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel06.add(scrollPane, gbc_scrollPane);
		
		
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				int index = list.getSelectedIndex();
				if (index == -1)
					return;
				Reserva reserva = reservas.get(index);
				selecionaReserva(reserva);
			}
		});
		scrollPane.setViewportView(list);

		
		panel07 = new JPanel();
		GridBagConstraints gbc_panel07 = new GridBagConstraints();
		gbc_panel07.anchor = GridBagConstraints.NORTH;
		gbc_panel07.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel07.gridx = 0;
		gbc_panel07.gridy = 1;
		panel01.add(panel07, gbc_panel07);
		GridBagLayout gbl_panel07 = new GridBagLayout();
		gbl_panel07.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel07.rowHeights = new int[]{0, 0};
		gbl_panel07.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel07.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel07.setLayout(gbl_panel07);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaReserva(reservas.get(list.getSelectedIndex()));
			}
		});
		
		btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionaReserva();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel07.add(btnAdd, gbc_btnAdd);
		btnSalvar.setEnabled(false);
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.anchor = GridBagConstraints.EAST;
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 0;
		panel07.add(btnSalvar, gbc_btnSalvar);
		
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
		panel07.add(btnCancelar, gbc_btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeReserva();
				atualizaLista(reservas, list);
				limpaSelecao();
			}
		});
		btnExcluir.setEnabled(false);
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 0;
		panel07.add(btnExcluir, gbc_btnExcluir);
		
		limpaSelecao();

	}
}