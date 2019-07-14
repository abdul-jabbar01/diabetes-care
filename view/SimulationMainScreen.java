package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import controller.BasicController;
import controller.PatientController;
import controller.Simulation;
import model.UserModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class SimulationMainScreen extends JFrame{

	private JFrame frame;
	UserModel userDetails;
	private JTextField textFieldStartingBGL;
	private JTextField textFieldBreakfastCarb;
	private JTextField textFieldLunchCarb;
	private JTextField textFieldDinnerCarb;
	private JTextField textFieldMaxDose;

	BasicController basicController;
	private JTextField textFieldSimulationDelay;

	//Launch the application.
	public static void main(String[] args) {
		try {


			UserModel userInfo=new UserModel();

			userInfo.setUserId(22);
			userInfo.setUserFirstName("abdul");
			userInfo.setUserLastName( "jabbar");
			userInfo.setUserEmail("abdul@gmail.com");
			userInfo.setUserType("Patient");	 

			userInfo.patientDetails=new PatientController().getPatientDetailByUserId(userInfo.getUserId());

			SimulationMainScreen window = new SimulationMainScreen(userInfo);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Create the application.
	public SimulationMainScreen(UserModel userDetails) {
		initialize();
		basicController=new BasicController();
		this.userDetails=userDetails;
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 883, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		BackgroundPanel backgroundPanel = new BackgroundPanel("../media/background5.jpg");
		backgroundPanel.setSize(883, 684);
		backgroundPanel.setLocation(0, 0);
		frame.getContentPane().add(backgroundPanel);
		backgroundPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -13, 884, 640);
		panel.setOpaque(false);
		backgroundPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 12, 884, 51);
		panel_1.setBackground(new Color(255, 140, 0));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblInsulinAndGlucagon = new JLabel("Insulin and Glucagon Pump Simulation ");
		lblInsulinAndGlucagon.setForeground(Color.WHITE);
		lblInsulinAndGlucagon.setFont(new Font("Dialog", Font.BOLD, 22));
		lblInsulinAndGlucagon.setBounds(191, 12, 505, 27);
		panel_1.add(lblInsulinAndGlucagon);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 60, 884, 51);
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel clockPanel = new JPanel();
		clockPanel.setBackground(Color.WHITE);
		clockPanel.setBounds(179, 10, 534, 33);
		panel_2.add(clockPanel);
		Clock clockFrame = new Clock();
		clockPanel.add(clockFrame);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.GRAY));
		panel_3.setBounds(20, 123, 850, 505);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblStartingBgl = new JLabel("Starting BGL");
		lblStartingBgl.setBounds(16, 65, 83, 24);
		panel_3.add(lblStartingBgl);

		textFieldStartingBGL = new JTextField();
		textFieldStartingBGL.setBounds(139, 60, 150, 32);
		panel_3.add(textFieldStartingBGL);
		textFieldStartingBGL.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_4.setBounds(6, 260, 835, 160);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY));
		panel_5.setBounds(6, 6, 275, 150);
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblSelectFood = new JLabel("Select Food");
		lblSelectFood.setBounds(6, 56, 77, 16);
		panel_5.add(lblSelectFood);

		JComboBox comboBoxBreakfast = new JComboBox();
		comboBoxBreakfast.setBounds(111, 52, 152, 27);
		panel_5.add(comboBoxBreakfast);
		comboBoxBreakfast.setModel(new DefaultComboBoxModel(new String[] {"White wheat bread-75", "Specialty grain bread-53", "Unleavened wheat bread-70", "Wheat roti-62", "White rice, boiled-73", "Brown rice, boiled-68", "Apple, raw†-36", "Orange, raw†-43", "Banana, raw†-51", "Pineapple, raw-59", "Mango, raw†-51", "Watermelon, raw-76", "Dates, raw-42", "Peaches, canned†-43", "Strawberry jam/jelly-49", "Apple juice-41", "Orange juice-50", "Potato, boiled-78", "Potato, instant mash-87", "Potato, french fries-63", "Carrots, boiled\t39", "Sweet potato, boiled-63", "Pumpkin, boiled-64", "Plantain/green banana-55", "Taro, boiled-53", "Vegetable soup-48", "Milk, full fat-39", "Milk, skim-37", "Ice cream-51", "Yogurt, fruit-41", "Soy milk-34", "Rice milk-86", "Chickpeas-28", "Kidney beans-24", "Lentils-32", "Soya beans-16", "Chocolate-40", "Popcorn-65", "Potato crisps-56", "Soft drink/soda-59", "Fructos-15", "Sucrose-65", "Glucose-103", "Honey-61"}));

		JLabel lblCarbohydrates = new JLabel("Carbohydrates");
		lblCarbohydrates.setBounds(6, 92, 100, 16);
		panel_5.add(lblCarbohydrates);

		textFieldBreakfastCarb = new JTextField();
		textFieldBreakfastCarb.setColumns(10);
		textFieldBreakfastCarb.setBounds(112, 86, 151, 32);
		panel_5.add(textFieldBreakfastCarb);

		JPanel panel_13 = new JPanel();
		panel_13.setBounds(0, 0, 275, 31);
		panel_5.add(panel_13);
		panel_13.setBackground(Color.DARK_GRAY);

		JLabel lblBreakfast = new JLabel("Breakfast (8:00)");
		panel_13.add(lblBreakfast);
		lblBreakfast.setForeground(Color.WHITE);
		lblBreakfast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(Color.GRAY));
		panel_9.setBounds(284, 6, 268, 150);
		panel_4.add(panel_9);
		panel_9.setLayout(null);

		JLabel label_1 = new JLabel("Select Food");
		label_1.setBounds(6, 56, 77, 16);
		panel_9.add(label_1);

		JComboBox comboBoxLunch = new JComboBox();
		comboBoxLunch.setModel(new DefaultComboBoxModel(new String[] {"White wheat bread-75", "Specialty grain bread-53", "Unleavened wheat bread-70", "Wheat roti-62", "White rice, boiled-73", "Brown rice, boiled-68", "Apple, raw†-36", "Orange, raw†-43", "Banana, raw†-51", "Pineapple, raw-59", "Mango, raw†-51", "Watermelon, raw-76", "Dates, raw-42", "Peaches, canned†-43", "Strawberry jam/jelly-49", "Apple juice-41", "Orange juice-50", "Potato, boiled-78", "Potato, instant mash-87", "Potato, french fries-63", "Carrots, boiled\t39", "Sweet potato, boiled-63", "Pumpkin, boiled-64", "Plantain/green banana-55", "Taro, boiled-53", "Vegetable soup-48", "Milk, full fat-39", "Milk, skim-37", "Ice cream-51", "Yogurt, fruit-41", "Soy milk-34", "Rice milk-86", "Chickpeas-28", "Kidney beans-24", "Lentils-32", "Soya beans-16", "Chocolate-40", "Popcorn-65", "Potato crisps-56", "Soft drink/soda-59", "Fructos-15", "Sucrose-65", "Glucose-103", "Honey-61"}));
		comboBoxLunch.setBounds(111, 52, 152, 27);
		panel_9.add(comboBoxLunch);

		textFieldLunchCarb = new JTextField();
		textFieldLunchCarb.setColumns(10);
		textFieldLunchCarb.setBounds(112, 86, 151, 32);
		panel_9.add(textFieldLunchCarb);

		JLabel label_3 = new JLabel("Carbohydrates");
		label_3.setBounds(6, 92, 100, 16);
		panel_9.add(label_3);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.DARK_GRAY);
		panel_14.setBounds(0, 0, 275, 31);
		panel_9.add(panel_14);

		JLabel lblLunch = new JLabel("Lunch (13:00)");
		lblLunch.setForeground(Color.WHITE);
		lblLunch.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_14.add(lblLunch);

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(Color.GRAY));
		panel_11.setLayout(null);
		panel_11.setBounds(555, 6, 275, 150);
		panel_4.add(panel_11);

		JLabel label_4 = new JLabel("Select Food");
		label_4.setBounds(6, 56, 77, 16);
		panel_11.add(label_4);

		JComboBox comboBoxDinner = new JComboBox();
		comboBoxDinner.setModel(new DefaultComboBoxModel(new String[] {"White wheat bread-75", "Specialty grain bread-53", "Unleavened wheat bread-70", "Wheat roti-62", "White rice, boiled-73", "Brown rice, boiled-68", "Apple, raw†-36", "Orange, raw†-43", "Banana, raw†-51", "Pineapple, raw-59", "Mango, raw†-51", "Watermelon, raw-76", "Dates, raw-42", "Peaches, canned†-43", "Strawberry jam/jelly-49", "Apple juice-41", "Orange juice-50", "Potato, boiled-78", "Potato, instant mash-87", "Potato, french fries-63", "Carrots, boiled\t39", "Sweet potato, boiled-63", "Pumpkin, boiled-64", "Plantain/green banana-55", "Taro, boiled-53", "Vegetable soup-48", "Milk, full fat-39", "Milk, skim-37", "Ice cream-51", "Yogurt, fruit-41", "Soy milk-34", "Rice milk-86", "Chickpeas-28", "Kidney beans-24", "Lentils-32", "Soya beans-16", "Chocolate-40", "Popcorn-65", "Potato crisps-56", "Soft drink/soda-59", "Fructos-15", "Sucrose-65", "Glucose-103", "Honey-61"}));
		comboBoxDinner.setBounds(111, 52, 152, 27);
		panel_11.add(comboBoxDinner);

		textFieldDinnerCarb = new JTextField();
		textFieldDinnerCarb.setColumns(10);
		textFieldDinnerCarb.setBounds(112, 86, 151, 32);
		panel_11.add(textFieldDinnerCarb);

		JLabel label_5 = new JLabel("Carbohydrates");
		label_5.setBounds(6, 92, 100, 16);
		panel_11.add(label_5);

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.DARK_GRAY);
		panel_15.setBounds(0, 0, 275, 31);
		panel_11.add(panel_15);

		JLabel lblDinner = new JLabel("Dinner (18:00)");
		lblDinner.setForeground(Color.WHITE);
		lblDinner.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_15.add(lblDinner);

		JLabel lblMgdl = new JLabel("mg/dl");
		lblMgdl.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblMgdl.setBounds(301, 65, 61, 16);
		panel_3.add(lblMgdl);

		textFieldMaxDose = new JTextField();
		textFieldMaxDose.setColumns(10);
		textFieldMaxDose.setBounds(139, 101, 150, 32);
		panel_3.add(textFieldMaxDose);

		JComboBox comboBoxGlucoseSensitivity = new JComboBox();
		comboBoxGlucoseSensitivity.setModel(new DefaultComboBoxModel(new String[] {"1500", "1700", "1800"}));
		comboBoxGlucoseSensitivity.setBounds(139, 141, 150, 24);
		panel_3.add(comboBoxGlucoseSensitivity);


		JTextField textFieldTotalDays = new JTextField();
		textFieldTotalDays.setColumns(10);
		textFieldTotalDays.setBounds(139, 177, 150, 32);
		panel_3.add(textFieldTotalDays);

		//Validate Total Days
		textFieldTotalDays.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);	
			}
		});


		//Validate start bgl
		textFieldStartingBGL.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);

			}
		});


		//Validate Max Dose
		textFieldMaxDose.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);			
			}
		});


		//Validate Breakfast Carbohydrates
		textFieldBreakfastCarb.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);			
			}
		});


		//Validate Lunch Carbohydrates
		textFieldLunchCarb.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);			
			}
		});

		//Validate Dinner Carbohydrates
		textFieldDinnerCarb.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyTyped(KeyEvent arg0) {
				basicController.validateNumber(arg0);			
			}
		});



		JButton btnRunSimulation = new JButton("Run Simulation");

		//Action Listener for Submit button
		btnRunSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Simulation simulation;

				if(textFieldStartingBGL.equals("")) {

					JOptionPane.showMessageDialog(null, "Please enter the Starting BGL Value");
				}

				else if(textFieldMaxDose.equals("")) {

					JOptionPane.showMessageDialog(null, "Please enter the Max Dose");
				}

				else if(textFieldTotalDays.equals("")) {

					JOptionPane.showMessageDialog(null, "Please enter the total days for simulation");
				}

				else if(textFieldSimulationDelay.equals("")) {

					JOptionPane.showMessageDialog(null, "Please enter the Simulation delay between each checkup of BGL");
				}

				else {
					simulation=new Simulation(Integer.parseInt(textFieldStartingBGL.getText()));
					simulation.setMaxDose(Integer.parseInt(textFieldMaxDose.getText()));
					simulation.setTotalDays(Integer.parseInt(textFieldTotalDays.getText()));
					simulation.setSensitivityModel(Integer.parseInt(comboBoxGlucoseSensitivity.getSelectedItem().toString()));
					simulation.setSimulationDelay((int) (Double.parseDouble((textFieldSimulationDelay.getText().toString())) *1000));


					if(textFieldBreakfastCarb.getText().equals("")) {
						simulation.setBreakfast(0, 0);
					}
					else {
						int breakFastGlycemicIndex=Integer.parseInt(comboBoxBreakfast.getSelectedItem().toString().split("-")[1]);
						int breakFastCarbohydrates=Integer.parseInt(textFieldBreakfastCarb.getText());
						simulation.setBreakfast(breakFastCarbohydrates, breakFastGlycemicIndex);
					}

					if(textFieldLunchCarb.getText().equals("")) {
						simulation.setLunch(0, 0);
					}
					else {
						int lunchGlycemicIndex=Integer.parseInt(comboBoxLunch.getSelectedItem().toString().split("-")[1]);
						int lunchCarbohydrates=Integer.parseInt(textFieldLunchCarb.getText());
						simulation.setLunch(lunchCarbohydrates, lunchGlycemicIndex);
					}

					if(textFieldDinnerCarb.getText().equals("")) {
						simulation.setDinner(0, 0);
					}
					else {
						int dinnerGlycemicIndex=Integer.parseInt(comboBoxDinner.getSelectedItem().toString().split("-")[1]);
						int dinnerCarbohydrates=Integer.parseInt(textFieldDinnerCarb.getText());
						simulation.setDinner(dinnerCarbohydrates, dinnerGlycemicIndex);
					}

					simulation.simulate();

				}		

			}
		});
		btnRunSimulation.setBounds(347, 440, 139, 45);
		panel_3.add(btnRunSimulation);

		JLabel lblMaxDoseper = new JLabel("Max Dose ");
		lblMaxDoseper.setBounds(16, 105, 120, 24);
		panel_3.add(lblMaxDoseper);



		JLabel lblUnits = new JLabel("units/day");
		lblUnits.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblUnits.setBounds(301, 107, 61, 16);
		panel_3.add(lblUnits);

		JLabel lblSensitivityModel = new JLabel("Sensitivity Model");
		lblSensitivityModel.setBounds(16, 141, 120, 24);
		panel_3.add(lblSensitivityModel);

		JLabel lblTotalDays = new JLabel("Total Days");
		lblTotalDays.setBounds(16, 177, 120, 24);
		panel_3.add(lblTotalDays);

		JLabel lblSimulationDelay = new JLabel("Simulation Delay");
		lblSimulationDelay.setBounds(16, 220, 120, 24);
		panel_3.add(lblSimulationDelay);

		textFieldSimulationDelay = new JTextField();
		textFieldSimulationDelay.setColumns(10);
		textFieldSimulationDelay.setBounds(139, 216, 150, 32);
		panel_3.add(textFieldSimulationDelay);

		JLabel lblSeconds = new JLabel("seconds");
		lblSeconds.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblSeconds.setBounds(301, 224, 61, 16);
		panel_3.add(lblSeconds);

		JLabel lblNewLabel = new JLabel("Simulation Screen");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(347, 15, 207, 29);
		panel_3.add(lblNewLabel);

		BufferedImage img=null;
		try {
			img = ImageIO.read(SimulationMainScreen.class.getResource("/media/battery2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setVisible(true);
	}
}
