package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.FormaPagamento;
import model.Hospede;
import model.Reserva;
import servicos.HospedesService;
import servicos.ReservaService;
import utils.DateUtils;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private HospedesService hospedesService = new HospedesService();
	private ReservaService reservaService = new ReservaService();
	private String flagConsulta = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));

		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String argumentoDeBusca = txtBuscar.getText();
				
				
				if(argumentoDeBusca != null && argumentoDeBusca.length() > 0) {
                     try {
						try {
							modelo.setRowCount(0);
							Long reservaId = Long.parseLong(argumentoDeBusca);
							Reserva reserva = reservaService.consultarReserva(reservaId);
							modelo.addRow(new Object[] {reserva.getId().toString(), 
									reserva.getDataEntrada().toString(),
									reserva.getDataSaida().toString(),
									reserva.getValor().toString(),
									reserva.getFormaPagamento().toString().toUpperCase()});
							flagConsulta = "reserva";
						
						} catch (Exception e2) {
							
							modeloHospedes.setRowCount(0);
                            Hospede hospede = hospedesService.buscarPorSobreNome(argumentoDeBusca);
                            modeloHospedes.addRow(new Object[] {
                            		hospede.getId().toString(),
                            		hospede.getNome(),
                            		hospede.getSobrenome(),
                            		hospede.getDataNascimento().toString(),
                            		hospede.getNacionalidade(),
                            		hospede.getTelefone(),
                            		hospede.getReserva().getId().toString()
                            });
                            flagConsulta = "hospedes";
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro ao realizar consulta");
					}					
					
				}else {
					JOptionPane.showMessageDialog(null, "informe o argumento de busca");
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flagConsulta.equals("reserva")) {
					int indiceLinhaSelecionada = tbReservas.getSelectedRow();
					
					String id = (String) tbReservas.getValueAt(indiceLinhaSelecionada, 0);
					String dataCheckin = (String)tbReservas.getValueAt(indiceLinhaSelecionada, 1);
					String dataCheckout = (String)tbReservas.getValueAt(indiceLinhaSelecionada, 2);
					String formaPagamento = (String) tbReservas.getValueAt(indiceLinhaSelecionada, 4);
					BigDecimal valorAtualizado = reservaService.calcularTotalReserva(DateUtils.converter(dataCheckin), 
							DateUtils.converter(dataCheckout));
					Reserva reserva = new Reserva(
							Long.parseLong(id), 
							DateUtils.converter(dataCheckin), 
							DateUtils.converter(dataCheckout), 
						    valorAtualizado, 
						    FormaPagamento.valueOf(formaPagamento.toUpperCase()));
					
					if(reservaService.atualizarReserva(reserva.getId(), reserva)) {
						Sucesso sucesso = new Sucesso();
						sucesso.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar reserva");
					}
				    
				}else if(flagConsulta.equals("hospedes")) {
					try {
						int linhaSelecionada = tbHospedes.getSelectedRow();
						String idHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 0);
						String nomeDoHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 1);
						String sobrenomeHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 2);
						String dataNascimentoHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 3);
						String nacionalidadeHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 4);
						String telefoneHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 5);
						String idReservaHospede = (String) tbHospedes.getValueAt(linhaSelecionada, 6);
						Reserva reserva = new Reserva(Long.parseLong(idReservaHospede));
						Hospede hospede = new Hospede(Long.parseLong(idHospede),
								nomeDoHospede, 
								sobrenomeHospede, 
								LocalDate.parse(dataNascimentoHospede),
								nacionalidadeHospede, 
								telefoneHospede,
								reserva);
						if(hospedesService.atualizarHospedes(hospede)) {
							Sucesso sucesso = new Sucesso();
							sucesso.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao atualizar hospede");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "não há linha selecionada");
					}
					
				}
			}
		});
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if("reserva".equals(flagConsulta)) {
					int indiceLinhaSelecionada = tbReservas.getSelectedRow();
					String id = (String) tbReservas.getValueAt(indiceLinhaSelecionada, 0);
					
					if(reservaService.excluirReserva(Long.parseLong(id))){
						JOptionPane.showMessageDialog(null, "registro excluido");
					}else {
						JOptionPane.showMessageDialog(null, "Erro ao excluir");
					}
				    
				}else if("hospedes".equals(flagConsulta)) {
					int indiceLinhaSelecionada = tbHospedes.getSelectedRow();
					String id = (String) tbHospedes.getValueAt(indiceLinhaSelecionada, 6);
					
					if(hospedesService.excluirHospedes(Long.parseLong(id))){
						JOptionPane.showMessageDialog(null, "registro excluido");
					}else {
						JOptionPane.showMessageDialog(null, "Erro ao excluir");
					}
				}
			}
		});
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
