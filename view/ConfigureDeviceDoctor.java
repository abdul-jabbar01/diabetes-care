package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controller.BasicController;
import controller.DoctorController;
import controller.UserController;
import model.UserModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class ConfigureDeviceDoctor {

	private JFrame frame;
	private JTextField textFieldPatientFirstName;
	UserModel userDetails;

	BasicController basicController;
	UserController userObj;
	private JTextField textFieldPatientId;
	private JTextField textFieldPatientLastName;
	JRadioButton rdbtnNewRadioButton ;
	JRadioButton rdbtnManual ;
	JComboBox comboBoxSensitivityModel ;
	private JTextField textFieldMaxDose;


	//Parameterized Constructor
	public ConfigureDeviceDoctor(int patientId, String patientFirstName, String patientLastName) {
		initialize();

		userObj=new UserController();
		basicController=new BasicController();
		textFieldPatientId.setText(Integer.toString(patientId));
		textFieldPatientFirstName.setText(patientFirstName);
		textFieldPatientLastName.setText(patientLastName);		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 714, 499);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panel_2.setBounds(112, 59, 493, 335);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 525, 53);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setLayout(null);

		JLabel lblRegister = new JLabel("Configure Device");
		lblRegister.setBounds(122, 10, 232, 24);
		panel_1.add(lblRegister);
		lblRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		textFieldPatientFirstName = new JTextField();
		textFieldPatientFirstName.setEnabled(false);
		textFieldPatientFirstName.setColumns(10);
		textFieldPatientFirstName.setBounds(230, 103, 231, 31);
		panel_2.add(textFieldPatientFirstName);

		comboBoxSensitivityModel = new JComboBox();
		comboBoxSensitivityModel.setModel(new DefaultComboBoxModel(new String[] {"1500", "1700", "1800"}));
		comboBoxSensitivityModel.setBounds(230, 235, 231, 24);
		panel_2.add(comboBoxSensitivityModel);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(new DoctorController(userDetails).configureDevice(Integer.parseInt(textFieldPatientId.getText()), rdbtnNewRadioButton.isSelected(), Integer.parseInt(textFieldMaxDose.getText()),Integer.parseInt(comboBoxSensitivityModel.getSelectedItem().toString())))
					frame.dispose();
			}
		});

		btnSubmit.setBounds(138, 305, 114, 25);
		panel_2.add(btnSubmit);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		btnBack.setBounds(252, 303, 117, 29);
		panel_2.add(btnBack);

		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPatientId.setBounds(75, 71, 118, 31);
		panel_2.add(lblPatientId);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFirstName.setBounds(75, 103, 118, 31);
		panel_2.add(lblFirstName);

		textFieldPatientId = new JTextField();
		textFieldPatientId.setEnabled(false);
		textFieldPatientId.setColumns(10);
		textFieldPatientId.setBounds(230, 71, 231, 31);
		panel_2.add(textFieldPatientId);

		JLabel lblDeviceMode = new JLabel("Device Mode");
		lblDeviceMode.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDeviceMode.setBounds(75, 167, 118, 31);
		panel_2.add(lblDeviceMode);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblLastName.setBounds(75, 135, 118, 31);
		panel_2.add(lblLastName);

		textFieldPatientLastName = new JTextField();
		textFieldPatientLastName.setEnabled(false);
		textFieldPatientLastName.setColumns(10);
		textFieldPatientLastName.setBounds(230, 135, 231, 31);
		panel_2.add(textFieldPatientLastName);

		rdbtnNewRadioButton = new JRadioButton("Auto");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(230, 171, 76, 23);
		panel_2.add(rdbtnNewRadioButton);

		rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setBounds(321, 171, 92, 23);
		panel_2.add(rdbtnManual);

		ButtonGroup buttonGroup =	new ButtonGroup();	 
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnManual);

		JLabel lblMaxDose = new JLabel("Max Dose");
		lblMaxDose.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMaxDose.setBounds(75, 198, 118, 31);
		panel_2.add(lblMaxDose);

		textFieldMaxDose = new JTextField();
		textFieldMaxDose.setEnabled(true);
		textFieldMaxDose.setColumns(10);
		textFieldMaxDose.setBounds(230, 198, 231, 31);
		panel_2.add(textFieldMaxDose);


		//Validate Max Dose
		textFieldMaxDose.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);

			}
		});

		JLabel label = new JLabel("Sensitivity Model");
		label.setBounds(75, 235, 120, 24);
		panel_2.add(label);


		//		frame.pack();
		frame.setVisible(true);
	}
}
