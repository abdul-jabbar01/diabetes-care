package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Point;
import controller.NurseController;
import model.UserModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.ScrollPane;

public class MainScreenNurse extends JFrame{

	private JFrame frame;
	UserModel userDetails;
	private NurseController nurseController;
	private JTable tableInjectionHistory;
	JLabel labelNoInjectionHistory ;

	// Parameterized Constructor
	public MainScreenNurse(UserModel userDetails) {
		this.userDetails=userDetails;		
		nurseController=new NurseController(userDetails);
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		BackgroundPanel backgroundPanel = new BackgroundPanel("../media/background5.jpg");
		backgroundPanel.setSize(850, 684);
		backgroundPanel.setLocation(0, 0);
		frame.getContentPane().add(backgroundPanel);
		backgroundPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -13, 850, 643);
		panel.setOpaque(false);
		backgroundPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 12, 850, 51);
		panel_1.setBackground(new Color(255, 140, 0));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblInsulinAndGlucagon = new JLabel("Insulin and Glucagon Pump Simulation ");
		lblInsulinAndGlucagon.setForeground(Color.WHITE);
		lblInsulinAndGlucagon.setFont(new Font("Dialog", Font.BOLD, 22));
		lblInsulinAndGlucagon.setBounds(191, 12, 505, 27);
		panel_1.add(lblInsulinAndGlucagon);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 60, 850, 51);
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel clockPanel = new JPanel();
		clockPanel.setBackground(Color.WHITE);
		clockPanel.setBounds(179, 10, 534, 33);
		panel_2.add(clockPanel);
		Clock clockFrame = new Clock();
		clockPanel.add(clockFrame);

		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBounds(731, 10, 99, 41);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new SignIn();
				frame.dispose();
			}
		});


		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(220, 220, 220));
		panel_4.setBounds(6, 123, 838, 510);
		panel.add(panel_4);

		JLabel lblPatientsInjectionHistory = new JLabel("Patients Injection History");
		lblPatientsInjectionHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPatientsInjectionHistory.setBounds(312, 55, 344, 24);
		panel_4.add(lblPatientsInjectionHistory);

		  labelNoInjectionHistory = new JLabel("<html>There is no data related to the patient injection history. Please &nbsp &nbsp   refresh after some time </html>");
		labelNoInjectionHistory.setFont(new Font("Courier New", Font.PLAIN, 20));
		labelNoInjectionHistory.setBounds(223, 180, 416, 102);
		panel_4.add(labelNoInjectionHistory);
		
		JPanel panelPatientsHistory = new JPanel();
		panelPatientsHistory.setLayout(null);
		panelPatientsHistory.setBounds(5, 160, 826, 162);
		panel_4.add(panelPatientsHistory);
		
		try {
			tableInjectionHistory = new  JTable(nurseController.getPatientsHistory());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		tableInjectionHistory.setGridColor(Color.GRAY);
		tableInjectionHistory.setFont(new Font("Courier New", Font.PLAIN, 16));
		tableInjectionHistory.setBounds(0, 0, 826, 187);
		
		if(tableInjectionHistory.getRowCount() == 0) {
			labelNoInjectionHistory.setVisible(true);
			panelPatientsHistory.setVisible(false);	 
		}
		else {
			labelNoInjectionHistory.setVisible(false);
			panelPatientsHistory.setVisible(true);	 
		}
		tableInjectionHistory.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					new ViewHistoryPatient(Integer.parseInt(table.getValueAt( table.getSelectedRow(),0).toString()));
				}
			}
		});

		tableInjectionHistory.getTableHeader().repaint();
		
		panelPatientsHistory.add(tableInjectionHistory);
		
		ScrollPane scrollPane_1 = new ScrollPane();
		scrollPane_1.setBounds(0, 10, 782, 167);
		panelPatientsHistory.add(scrollPane_1);
		
		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					tableInjectionHistory.setModel(nurseController.getPatientsHistory());

					if(tableInjectionHistory.getRowCount() == 0) {
						labelNoInjectionHistory.setVisible(true);
						panelPatientsHistory.setVisible(false);	 
					}
					else {
						labelNoInjectionHistory.setVisible(false);
						panelPatientsHistory.setVisible(true);	 
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(715, 48, 117, 42);
		panel_4.add(button);

		BufferedImage img=null;
		try {
			img = ImageIO.read(MainScreenNurse.class.getResource("/media/battery2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.setVisible(true);
	}
}
