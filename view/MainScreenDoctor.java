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
import controller.DoctorController;
import model.UserModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.ScrollPane;

public class MainScreenDoctor extends JFrame{

	private JFrame frame;
	UserModel userDetails;
	private DoctorController doctorController;
	private JTable table;
	JLabel labelNoPendingConfiguration;
	private JTable tableInjectionHistory;
	JLabel labelNoInjectionHistory ;


	// Parameterized Constructor
	public MainScreenDoctor(UserModel userDetails) {
		this.userDetails=userDetails;		
		doctorController=new DoctorController(userDetails);
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

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(220, 220, 220));
		panel_3.setBounds(6, 130, 838, 243);
		panel.add(panel_3);

		JLabel lblPendingConfigurationRequests = new JLabel("Pending Configuration Requests");
		lblPendingConfigurationRequests.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPendingConfigurationRequests.setBounds(287, 19, 344, 24);
		panel_3.add(lblPendingConfigurationRequests);

		labelNoPendingConfiguration = new JLabel("<html>There is no pending requests by the patients to configure their device</html>");
		labelNoPendingConfiguration.setVisible(false);
		labelNoPendingConfiguration.setFont(new Font("Courier New", Font.PLAIN, 20));
		labelNoPendingConfiguration.setBounds(223, 78, 453, 102);
		panel_3.add(labelNoPendingConfiguration);

		JPanel panelPendingRequests = new JPanel();
		panelPendingRequests.setBounds(6, 75, 826, 162);
		panel_3.add(panelPendingRequests);
		panelPendingRequests.setLayout(null);

		try {
			System.out.println(this.userDetails.getUserId());
			table = new JTable(doctorController.getPendingRequests(this.userDetails.getUserEmail()));
			table.setGridColor(Color.GRAY);
			table.setFont(new Font("Courier New", Font.PLAIN, 16));

			if(table.getRowCount() == 0) {
				labelNoPendingConfiguration.setVisible(true);
				panelPendingRequests.setVisible(false);	 
			}
			else {
				labelNoPendingConfiguration.setVisible(false);
				panelPendingRequests.setVisible(true);	 
			}
			table.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable table =(JTable) mouseEvent.getSource();
					Point point = mouseEvent.getPoint();
					int row = table.rowAtPoint(point);
					if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
						new ConfigureDeviceDoctor(Integer.parseInt(table.getValueAt( table.getSelectedRow(),0).toString()), table.getValueAt( table.getSelectedRow(),1).toString(), table.getValueAt( table.getSelectedRow(),2).toString());

					}
				}
			});

			table.getTableHeader().repaint();


		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		table.setBounds(0, 0, 826, 187);
		panelPendingRequests.add(table);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(0, 10, 782, 167);
		panelPendingRequests.add(scrollPane);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					table.setModel(doctorController.getPendingRequests(userDetails.getUserEmail()));

					if(table.getRowCount() == 0) {
						labelNoPendingConfiguration.setVisible(true);
						panelPendingRequests.setVisible(false);

					}
					else {
						labelNoPendingConfiguration.setVisible(false);
						panelPendingRequests.setVisible(true);	 
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(715, 12, 117, 42);
		panel_3.add(btnRefresh);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(220, 220, 220));
		panel_4.setBounds(6, 390, 838, 243);
		panel.add(panel_4);

		JLabel lblPatientsInjectionHistory = new JLabel("Patients Injection History");
		lblPatientsInjectionHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPatientsInjectionHistory.setBounds(318, 18, 344, 24);
		panel_4.add(lblPatientsInjectionHistory);

		  labelNoInjectionHistory = new JLabel("<html>There is no data related to the patient injection history. Please &nbsp &nbsp   refresh after some time </html>");
		labelNoInjectionHistory.setVisible(false);
		labelNoInjectionHistory.setFont(new Font("Courier New", Font.PLAIN, 20));
		labelNoInjectionHistory.setBounds(223, 78, 416, 102);
		panel_4.add(labelNoInjectionHistory);
		
		JPanel panelPatientsHistory = new JPanel();
		panelPatientsHistory.setLayout(null);
		panelPatientsHistory.setBounds(5, 75, 826, 162);
		panel_4.add(panelPatientsHistory);
		
		try {
			tableInjectionHistory = new  JTable(doctorController.getPatientsHistory(this.userDetails.getUserEmail()));
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
					tableInjectionHistory.setModel(doctorController.getPatientsHistory(userDetails.getUserEmail()));

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
		button.setBounds(714, 11, 117, 42);
		panel_4.add(button);

		BufferedImage img=null;
		try {
			img = ImageIO.read(MainScreenDoctor.class.getResource("/media/battery2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.setVisible(true);
	}
}
