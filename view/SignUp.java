package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controller.BasicController;
import controller.UserController;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class SignUp {

	private JFrame frame;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JPasswordField textFieldPassword;

	BasicController basicController;
	UserController userObj;

	// Default Constructor
	public SignUp() {
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

		frame.setVisible(true);
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

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(205, 12, 96, 24);
		panel_1.add(lblRegister);
		lblRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFirstName.setBounds(113, 84, 118, 31);
		panel_2.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblLastName.setBounds(113, 127, 118, 31);
		panel_2.add(lblLastName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(113, 170, 118, 31);
		panel_2.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPassword.setBounds(113, 213, 118, 31);
		panel_2.add(lblPassword);

		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblUserType.setBounds(113, 256, 118, 31);
		panel_2.add(lblUserType);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(293, 84, 148, 31);
		panel_2.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(293, 127, 148, 31);
		panel_2.add(textFieldLastName);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(293, 170, 148, 31);
		panel_2.add(textFieldEmail);

		JComboBox comboBoxUserType = new JComboBox();
		comboBoxUserType.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient", "Nurse", "Care Taker"}));
		comboBoxUserType.setBounds(293, 259, 150, 31);
		panel_2.add(comboBoxUserType);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Submit the SignUp Form

				if(userObj.addUser(textFieldFirstName.getText(), textFieldLastName.getText(), textFieldEmail.getText(), textFieldPassword.getText(),comboBoxUserType.getSelectedItem().toString())) {

					new SignIn();
					frame.dispose();

				}

			}
		});
		btnSubmit.setBounds(142, 328, 114, 25);
		panel_2.add(btnSubmit);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(293, 213, 148, 31);
		panel_2.add(textFieldPassword);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				frame.dispose();
			}
		});
		btnBack.setBounds(273, 328, 114, 25);
		panel_2.add(btnBack);

		//Validate keys of First Name
		textFieldFirstName.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateName(arg0);

			}
		});


		//Validate keys of Last Name
		textFieldLastName.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateName(arg0);

			}
		});

		frame.setVisible(true);
	}
}
