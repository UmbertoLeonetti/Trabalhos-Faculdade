package view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import backend.Persiste;
import backend.Restaurante;
import view.panels.BebidaPanel;
import view.panels.FuncionarioPanel;
import view.panels.IngredientePanel;
import view.panels.PedidoPanel;
import view.panels.PratoPanel;
import view.panels.RelatorioPanel;
import view.panels.ReservaPanel;

public class Main extends JFrame {

	private JPanel contentPane;
	private Restaurante restaurante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		restaurante = (Restaurante) Persiste.carrega("restaurante.txt");
		if(restaurante == null)
			restaurante = new Restaurante();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panel_1.add(tabbedPane, gbc_tabbedPane);
				
				JPanel panel_3 = new IngredientePanel(restaurante);
				tabbedPane.addTab("Ingredientes", null, panel_3, null);
				
				JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Produtos", null, tabbedPane_1, null);
				
				JPanel panel_2 = new PratoPanel(restaurante);
				tabbedPane_1.addTab("Pratos", null, panel_2, null);
				
				JPanel panel_4 = new BebidaPanel(restaurante);
				tabbedPane_1.addTab("Bebidas", null, panel_4, null);

		JPanel pnlPedido = new PedidoPanel(restaurante);
		tabbedPane.addTab("Pedidos", null, pnlPedido, null);

		JPanel pnlReserva = new ReservaPanel(restaurante);
		tabbedPane.addTab("Reservas", null, pnlReserva, null);

		JPanel pnlRelatorios = new RelatorioPanel(restaurante);
		tabbedPane.addTab("Relatórios", null, pnlRelatorios, null);
		
		JPanel pnlFuncionarios = new FuncionarioPanel(restaurante);
		tabbedPane.addTab("Funcionários", null, pnlFuncionarios, null);
	}

}
