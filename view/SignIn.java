package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import controller.BasicController;
import controller.DeviceController;
import controller.PatientController;
import controller.UserController;
import model.UserModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignIn {

	private JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;


	BasicController basicController;
	UserController userObj;


	// Default Constructor
	public SignIn() {
		initialize();

		userObj=new UserController();
		basicController=new BasicController();

	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 792, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		BackgroundPanel backgroundPanel = new BackgroundPanel(null);
		backgroundPanel.setSize(792, 488);
		backgroundPanel.setLocation(0, 0);
		frame.getContentPane().add(backgroundPanel);
		backgroundPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -13, 791, 515);
		panel.setOpaque(false);
		backgroundPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 70, 525, 375);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 525, 53);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setLayout(null);

		JLabel lblRegister = new JLabel("Sign In");
		lblRegister.setBounds(205, 12, 96, 24);
		panel_1.add(lblRegister);
		lblRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(110, 111, 118, 31);
		panel_2.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPassword.setBounds(110, 154, 118, 31);
		panel_2.add(lblPassword);

		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblUserType.setBounds(110, 197, 118, 31);
		panel_2.add(lblUserType);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(290, 111, 148, 31);
		panel_2.add(textFieldEmail);

		JComboBox comboBoxUserType = new JComboBox();
		comboBoxUserType.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient", "Nurse", "Care Taker"}));
		comboBoxUserType.setBounds(290, 200, 150, 31);
		panel_2.add(comboBoxUserType);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserModel userDetails=userObj.validateUser(textFieldEmail.getText(), passwordField.getText(),comboBoxUserType.getSelectedItem().toString());
				if(userDetails==null) {
					JOptionPane.showMessageDialog(null, "Invalid Login Credentials. Please try again");
				}
				else {
					System.out.println(userDetails.getUserType());

					if(userDetails.getUserType().equals("Patient")) {

						if(new DeviceController().checkDeviceSetup(new PatientController().getPatientDetailByUserId(userDetails.getUserId()).getPatientId()))
						{
							System.out.println("Device is already setup");
							new MainScreen(userDetails);
						}
						else {
							System.out.println("Device is not setup already");
							new MainScreenDefault(userDetails);
						}

					}
					else if(userDetails.getUserType().equals("Doctor")) {
						new MainScreenDoctor(userDetails);
					}
					
					else if(userDetails.getUserType().equals("Nurse")) {
						new MainScreenNurse(userDetails);
					}
					
					else if(userDetails.getUserType().equals("Care taker")) {
						new MainScreen(userDetails);
					}

					frame.dispose();


				}
			}
		});

		btnSubmit.setBounds(142, 269, 114, 25);
		panel_2.add(btnSubmit);

		passwordField = new JPasswordField();
		passwordField.setBounds(290, 154, 148, 31);
		panel_2.add(passwordField);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Main();
				frame.dispose();
			}
		});
		btnBack.setBounds(268, 267, 117, 29);
		panel_2.add(btnBack);
		frame.setVisible(true);
	}
}
