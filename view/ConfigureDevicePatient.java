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
import controller.UserController;
import model.DeviceModel;
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
import javax.swing.border.LineBorder;

public class ConfigureDevicePatient {

	private JFrame frame;
	private JTextField textFieldPatientFirstName;
	UserModel userDetails;
	BasicController basicController;
	UserController userObj;
	private JTextField textFieldPatientId;
	private JTextField textFieldPatientLastName;
	JRadioButton rdbtnNewRadioButton ;
	JRadioButton rdbtnManual ;
	private JTextField textFieldMaxDose;
	private JTextField textFieldBreakFastCarb;
	private JTextField textFieldLunchCarb;
	private JTextField textFieldDinnerCarb;
	private JTextField textFieldSensitivityModel;
	private JComboBox comboBoxDinner ;
	private JComboBox comboBoxLunch ;
	private JComboBox comboBoxBreakFast ;

	// Parameterized Constructor
	public ConfigureDevicePatient(UserModel userDetails) {
		initialize();

		userObj=new UserController();
		basicController=new BasicController();
		this.userDetails=userDetails;
		textFieldPatientId.setText(Integer.toString(userDetails.patientDetails.getPatientId()));
		textFieldPatientFirstName.setText(userDetails.getUserFirstName());
		textFieldPatientLastName.setText(userDetails.getUserLastName());

		DeviceModel deviceInfo=new DeviceController().getDeviceInformationByPatientId(userDetails.patientDetails.getPatientId());
		textFieldMaxDose.setText(Integer.toString(deviceInfo.getMaxDose()));
		textFieldSensitivityModel.setText(Integer.toString(deviceInfo.getSensitivityModel()));

		textFieldBreakFastCarb.setText(Integer.toString(deviceInfo.getBreakFastCarb()));
		textFieldLunchCarb.setText(Integer.toString(deviceInfo.getLunchCarb()));
		textFieldDinnerCarb.setText(Integer.toString(deviceInfo.getDinnerCarb()));

		comboBoxBreakFast.setSelectedItem(deviceInfo.getBreakFast());
		comboBoxLunch.setSelectedItem(deviceInfo.getLunch());
		comboBoxDinner.setSelectedItem(deviceInfo.getDinner());

	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 853, 548);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		BackgroundPanel backgroundPanel = new BackgroundPanel(null);
		backgroundPanel.setSize(853, 540);
		backgroundPanel.setLocation(0, 0);
		frame.getContentPane().add(backgroundPanel);
		backgroundPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 868, 532);
		panel.setOpaque(false);
		backgroundPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(26, 25, 815, 489);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 815, 53);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setLayout(null);

		JLabel lblRegister = new JLabel("Configure Device");
		lblRegister.setBounds(6, 6, 809, 41);
		panel_1.add(lblRegister);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		textFieldPatientFirstName = new JTextField();
		textFieldPatientFirstName.setEnabled(false);
		textFieldPatientFirstName.setColumns(10);
		textFieldPatientFirstName.setBounds(232, 114, 231, 31);
		panel_2.add(textFieldPatientFirstName);

		JButton btnSubmit = new JButton("Submit");


		btnSubmit.setBounds(230, 448, 114, 35);
		panel_2.add(btnSubmit);

		JButton btnBack = new JButton("back");
		
		//Action on the click of Back button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		btnBack.setBounds(344, 448, 117, 35);
		panel_2.add(btnBack);

		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPatientId.setBounds(75, 71, 118, 31);
		panel_2.add(lblPatientId);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFirstName.setBounds(77, 114, 118, 31);
		panel_2.add(lblFirstName);

		textFieldPatientId = new JTextField();
		textFieldPatientId.setEnabled(false);
		textFieldPatientId.setColumns(10);
		textFieldPatientId.setBounds(230, 71, 231, 31);
		panel_2.add(textFieldPatientId);

		JLabel lblDeviceMode = new JLabel("Device Mode");
		lblDeviceMode.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDeviceMode.setBounds(77, 214, 118, 31);
		panel_2.add(lblDeviceMode);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblLastName.setBounds(77, 161, 118, 31);
		panel_2.add(lblLastName);

		textFieldPatientLastName = new JTextField();
		textFieldPatientLastName.setEnabled(false);
		textFieldPatientLastName.setColumns(10);
		textFieldPatientLastName.setBounds(232, 161, 231, 31);
		panel_2.add(textFieldPatientLastName);

		rdbtnNewRadioButton = new JRadioButton("Auto");
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(232, 218, 76, 23);
		panel_2.add(rdbtnNewRadioButton);

		rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setEnabled(false);
		rdbtnManual.setBounds(323, 218, 92, 23);
		panel_2.add(rdbtnManual);

		ButtonGroup buttonGroup =	new ButtonGroup();	 
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnManual);

		JLabel lblMaxDose = new JLabel("Max Dose");
		lblMaxDose.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMaxDose.setBounds(77, 257, 118, 31);
		panel_2.add(lblMaxDose);

		textFieldMaxDose = new JTextField();
		textFieldMaxDose.setEnabled(false);
		textFieldMaxDose.setColumns(10);
		textFieldMaxDose.setBounds(232, 257, 231, 31);
		panel_2.add(textFieldMaxDose);


		//Validate Max Dose
		textFieldMaxDose.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);

			}
		});

		JLabel label = new JLabel("Sensitivity Model");
		label.setBounds(75, 311, 120, 24);
		panel_2.add(label);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(494, 67, 275, 123);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(Color.GRAY));

		JLabel label_1 = new JLabel("Select Food");
		label_1.setBounds(10, 47, 77, 16);
		panel_4.add(label_1);

		comboBoxBreakFast = new JComboBox();
		comboBoxBreakFast.setModel(new DefaultComboBoxModel(new String[] {"White wheat bread-75", "Specialty grain bread-53", "Unleavened wheat bread-70", "Wheat roti-62", "White rice, boiled-73", "Brown rice, boiled-68", "Apple, raw†-36", "Orange, raw†-43", "Banana, raw†-51", "Pineapple, raw-59", "Mango, raw†-51", "Watermelon, raw-76", "Dates, raw-42", "Peaches, canned†-43", "Strawberry jam/jelly-49", "Apple juice-41", "Orange juice-50", "Potato, boiled-78", "Potato, instant mash-87", "Potato, french fries-63", "Carrots, boiled\t39", "Sweet potato, boiled-63", "Pumpkin, boiled-64", "Plantain/green banana-55", "Taro, boiled-53", "Vegetable soup-48", "Milk, full fat-39", "Milk, skim-37", "Ice cream-51", "Yogurt, fruit-41", "Soy milk-34", "Rice milk-86", "Chickpeas-28", "Kidney beans-24", "Lentils-32", "Soya beans-16", "Chocolate-40", "Popcorn-65", "Potato crisps-56", "Soft drink/soda-59", "Fructos-15", "Sucrose-65", "Glucose-103", "Honey-61"}));
		comboBoxBreakFast.setBounds(115, 43, 152, 27);
		panel_4.add(comboBoxBreakFast);

		JLabel label_2 = new JLabel("Carbohydrates");
		label_2.setBounds(10, 81, 100, 16);
		panel_4.add(label_2);

		textFieldBreakFastCarb = new JTextField();
		textFieldBreakFastCarb.setColumns(10);
		textFieldBreakFastCarb.setBounds(116, 75, 151, 32);
		panel_4.add(textFieldBreakFastCarb);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		panel_5.setBounds(0, 0, 275, 31);
		panel_4.add(panel_5);

		JLabel label_3 = new JLabel("Breakfast (8:00)");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_5.add(label_3);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(494, 200, 275, 115);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(Color.GRAY));

		JLabel label_4 = new JLabel("Select Food");
		label_4.setBounds(10, 47, 77, 16);
		panel_6.add(label_4);

		comboBoxLunch = new JComboBox();
		comboBoxLunch.setModel(new DefaultComboBoxModel(new String[] {"White wheat bread-75", "Specialty grain bread-53", "Unleavened wheat bread-70", "Wheat roti-62", "White rice, boiled-73", "Brown rice, boiled-68", "Apple, raw†-36", "Orange, raw†-43", "Banana, raw†-51", "Pineapple, raw-59", "Mango, raw†-51", "Watermelon, raw-76", "Dates, raw-42", "Peaches, canned†-43", "Strawberry jam/jelly-49", "Apple juice-41", "Orange juice-50", "Potato, boiled-78", "Potato, instant mash-87", "Potato, french fries-63", "Carrots, boiled\t39", "Sweet potato, boiled-63", "Pumpkin, boiled-64", "Plantain/green banana-55", "Taro, boiled-53", "Vegetable soup-48", "Milk, full fat-39", "Milk, skim-37", "Ice cream-51", "Yogurt, fruit-41", "Soy milk-34", "Rice milk-86", "Chickpeas-28", "Kidney beans-24", "Lentils-32", "Soya beans-16", "Chocolate-40", "Popcorn-65", "Potato crisps-56", "Soft drink/soda-59", "Fructos-15", "Sucrose-65", "Glucose-103", "Honey-61"}));
		comboBoxLunch.setBounds(115, 43, 152, 27);
		panel_6.add(comboBoxLunch);

		textFieldLunchCarb = new JTextField();
		textFieldLunchCarb.setColumns(10);
		textFieldLunchCarb.setBounds(116, 73, 151, 32);
		panel_6.add(textFieldLunchCarb);

		JLabel label_5 = new JLabel("Carbohydrates");
		label_5.setBounds(10, 77, 100, 16);
		panel_6.add(label_5);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.DARK_GRAY);
		panel_7.setBounds(0, 0, 275, 31);
		panel_6.add(panel_7);

		JLabel label_6 = new JLabel("Lunch (13:00)");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_7.add(label_6);

		JPanel panel_8 = new JPanel();
		panel_8.setBounds(494, 325, 275, 123);
		panel_2.add(panel_8);
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(Color.GRAY));

		JLabel label_7 = new JLabel("Select Food");
		label_7.setBounds(6, 56, 77, 16);
		panel_8.add(label_7);

		comboBoxDinner = new JComboBox();
		comboBoxDinner.setModel(new DefaultComboBoxModel(new String[] {"White wheat bread-75", "Specialty grain bread-53", "Unleavened wheat bread-70", "Wheat roti-62", "White rice, boiled-73", "Brown rice, boiled-68", "Apple, raw†-36", "Orange, raw†-43", "Banana, raw†-51", "Pineapple, raw-59", "Mango, raw†-51", "Watermelon, raw-76", "Dates, raw-42", "Peaches, canned†-43", "Strawberry jam/jelly-49", "Apple juice-41", "Orange juice-50", "Potato, boiled-78", "Potato, instant mash-87", "Potato, french fries-63", "Carrots, boiled\t39", "Sweet potato, boiled-63", "Pumpkin, boiled-64", "Plantain/green banana-55", "Taro, boiled-53", "Vegetable soup-48", "Milk, full fat-39", "Milk, skim-37", "Ice cream-51", "Yogurt, fruit-41", "Soy milk-34", "Rice milk-86", "Chickpeas-28", "Kidney beans-24", "Lentils-32", "Soya beans-16", "Chocolate-40", "Popcorn-65", "Potato crisps-56", "Soft drink/soda-59", "Fructos-15", "Sucrose-65", "Glucose-103", "Honey-61"}));
		comboBoxDinner.setBounds(111, 52, 152, 27);
		panel_8.add(comboBoxDinner);

		textFieldDinnerCarb = new JTextField();
		textFieldDinnerCarb.setColumns(10);
		textFieldDinnerCarb.setBounds(112, 86, 151, 32);
		panel_8.add(textFieldDinnerCarb);

		JLabel label_8 = new JLabel("Carbohydrates");
		label_8.setBounds(6, 92, 100, 16);
		panel_8.add(label_8);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.DARK_GRAY);
		panel_9.setBounds(0, 0, 275, 31);
		panel_8.add(panel_9);

		JLabel label_9 = new JLabel("Dinner (18:00)");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_9.add(label_9);

		textFieldSensitivityModel = new JTextField();
		textFieldSensitivityModel.setText("0");
		textFieldSensitivityModel.setEnabled(false);
		textFieldSensitivityModel.setColumns(10);
		textFieldSensitivityModel.setBounds(232, 311, 231, 31);
		panel_2.add(textFieldSensitivityModel);

		//Action on the click of submit button
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(textFieldBreakFastCarb.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter the total carbohydrates for BreakFast");
				}

				else if(textFieldLunchCarb.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter the total carbohydrates for Lunch");
				}

				else if(textFieldDinnerCarb.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter the total carbohydrates for Dinner");
				}
				else {
					new DeviceController().setFood(userDetails.patientDetails.getPatientId(),comboBoxBreakFast.getSelectedItem().toString() ,Integer.parseInt( textFieldBreakFastCarb.getText()), comboBoxLunch.getSelectedItem().toString(), Integer.parseInt(textFieldLunchCarb.getText()), comboBoxDinner.getSelectedItem().toString(), Integer.parseInt(textFieldDinnerCarb.getText()));
				}

			}
		});


		//		frame.pack();
		frame.setVisible(true);
	}
}
