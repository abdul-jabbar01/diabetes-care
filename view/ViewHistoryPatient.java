package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import controller.BasicController;
import controller.PatientController;
import controller.UserController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.ScrollPane;

public class ViewHistoryPatient {

	private JFrame frame;	
	BasicController basicController;
	UserController userObj;
	private JTable tableHistory;
	int patientId;

	// Parameterized Constructor
	public ViewHistoryPatient(int patientId) {
		this.patientId=patientId;
		initialize();

		userObj=new UserController();
		basicController=new BasicController();
	}


	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 714, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);


		BackgroundPanel backgroundPanel = new BackgroundPanel(null);
		backgroundPanel.setSize(792, 488);
		backgroundPanel.setLocation(0, 0);
		frame.getContentPane().add(backgroundPanel);
		backgroundPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(23, 17, 768, 485);
		panel.setOpaque(false);
		backgroundPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(94, 59, 533, 335);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 533, 53);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setLayout(null);

		JLabel lblRegister = new JLabel("Patient History");
		lblRegister.setBounds(6, 10, 484, 24);
		panel_1.add(lblRegister);
		lblRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		JButton btnBack = new JButton("back");
		
		// Action listener for back button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		btnBack.setBounds(201, 295, 117, 39);
		panel_2.add(btnBack);


		try {
			tableHistory = new JTable(new PatientController().getPatientHistory(this.patientId));
			tableHistory.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tableHistory.setBorder(null);
			
			    final TableColumnModel columnModel = tableHistory.getColumnModel();
			    for (int column = 0; column < tableHistory.getColumnCount(); column++) {
			        int width = 15; // Min width
			        for (int row = 0; row < tableHistory.getRowCount(); row++) {
			            TableCellRenderer renderer = tableHistory.getCellRenderer(row, column);
			            Component comp = tableHistory.prepareRenderer(renderer, row, column);
			            width = Math.max(comp.getPreferredSize().width +1 , width);
			        }
			        if(width > 300)
			            width=300;
			        columnModel.getColumn(column).setPreferredWidth(width);
			    }
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.add(tableHistory);
		scrollPane.setBounds(10, 94, 515, 199);
		panel_2.add(scrollPane);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblTime.setBounds(90, 60, 61, 16);
		panel_2.add(lblTime);

		JLabel lblBglValue = new JLabel("BGL Value");
		lblBglValue.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblBglValue.setBounds(265, 60, 83, 16);
		panel_2.add(lblBglValue);
		
		JLabel lblInjectedUnits = new JLabel("Injected Units");
		lblInjectedUnits.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblInjectedUnits.setBounds(365, 61, 107, 16);
		panel_2.add(lblInjectedUnits);

		frame.setVisible(true);
	}
}
