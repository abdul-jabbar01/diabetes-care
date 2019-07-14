package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import controller.DeviceController;
import model.DeviceModel;
import model.UserModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame{

	private JFrame frame;
	UserModel userDetails;

	JLabel labelGlucagonRemPercentage;
	JLabel labelInsulinRemPercentage ;
	JLabel labelBatteryLevel;
	JPanel barGlucagon ;
	JPanel barInsulin ;



	// Parameterized Constructor
	public MainScreen(UserModel userDetails) {
		initialize();
		this.userDetails=userDetails;
		DeviceModel deviceInfo=new DeviceController().getDeviceInformationByPatientId(userDetails.patientDetails.getPatientId());
		labelGlucagonRemPercentage.setText(Integer.toString(deviceInfo.getRemGlucagonLevel())+ " %");
		labelInsulinRemPercentage.setText(Integer.toString(deviceInfo.getRemInsulinLevel())+ " %");
		labelBatteryLevel.setText(Integer.toString(deviceInfo.getRemBatteryLevel())+ " %");

		System.out.println(107*deviceInfo.getRemGlucagonLevel()/100);

		barGlucagon.setBounds(0,0,(107*deviceInfo.getRemGlucagonLevel()/100),27);
		barInsulin.setBounds(0,0,(107*deviceInfo.getRemInsulinLevel()/100),27);

		if(deviceInfo.getRemInsulinLevel() > 50){
			barInsulin.setBackground(new Color(50, 205, 50));
		}
		else if (deviceInfo.getRemInsulinLevel() > 25){
			barInsulin.setBackground(new Color(255, 140, 0));
		}
		else {
			barInsulin.setBackground(Color.RED);
		}

		if(deviceInfo.getRemGlucagonLevel() > 50){
			barGlucagon.setBackground(new Color(50, 205, 50));
		}
		else if (deviceInfo.getRemGlucagonLevel() > 25){
			barGlucagon.setBackground(new Color(255, 140, 0));
		}
		else {
			barGlucagon.setBackground(Color.RED);
		}


		barGlucagon.validate();
		barInsulin.validate();

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
		panel.setBounds(0, -13, 850, 590);
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

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/media/battery2.png")));
		lblNewLabel.setBounds(12, 12, 50, 27);
		panel_2.add(lblNewLabel);

		labelBatteryLevel = new JLabel("25 %");
		labelBatteryLevel.setFont(new Font("Dialog", Font.BOLD, 17));
		labelBatteryLevel.setBounds(64, 12, 66, 27);
		panel_2.add(labelBatteryLevel);

		JPanel clockPanel = new JPanel();
		clockPanel.setBackground(Color.WHITE);
		clockPanel.setBounds(179, 10, 534, 33);
		panel_2.add(clockPanel);
		Clock clockFrame = new Clock();
		clockPanel.add(clockFrame);

		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBounds(745, 8, 99, 41);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new SignIn();
				frame.dispose();
			}
		});


		JPanel panel_3 = new JPanel();
		panel_3.setBounds(262, 123, 303, 171);
		panel_3.setBackground(new Color(220, 220, 220));
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel label_1 = new JLabel("112");
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		label_1.setBounds(99, 42, 101, 74);
		panel_3.add(label_1);

		JLabel lblNewLabel_2 = new JLabel("mmool/L");
		lblNewLabel_2.setBounds(184, 73, 69, 24);
		panel_3.add(lblNewLabel_2);

		JLabel lblLastCheckedBlood = new JLabel("Last Checked Blood Sugar Level");
		lblLastCheckedBlood.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLastCheckedBlood.setBounds(27, 6, 270, 24);
		panel_3.add(lblLastCheckedBlood);

		JLabel lblCheckedAt = new JLabel("Checked at:");
		lblCheckedAt.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCheckedAt.setBounds(27, 128, 93, 16);
		panel_3.add(lblCheckedAt);

		JLabel lblAprilMonday = new JLabel("25 April, Monday, 12:25 PM");
		lblAprilMonday.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAprilMonday.setBounds(123, 128, 191, 16);
		panel_3.add(lblAprilMonday);

		JLabel lblNextCheckAt = new JLabel("Next Check at:");
		lblNextCheckAt.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNextCheckAt.setBounds(27, 149, 93, 16);
		panel_3.add(lblNextCheckAt);

		JLabel label_15 = new JLabel("25 April, Monday, 12:25 PM");
		label_15.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_15.setBounds(123, 149, 191, 16);
		panel_3.add(label_15);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(114, 306, 303, 171);
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(220, 220, 220));
		panel.add(panel_4);

		JLabel label_3 = new JLabel("23");
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		label_3.setBounds(99, 42, 101, 74);
		panel_4.add(label_3);

		JLabel label_4 = new JLabel("mmool/L");
		label_4.setBounds(173, 73, 69, 24);
		panel_4.add(label_4);

		JLabel lblLastInjectedInsulin = new JLabel("Last Injected Insulin");
		lblLastInjectedInsulin.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLastInjectedInsulin.setBounds(69, 6, 157, 24);
		panel_4.add(lblLastInjectedInsulin);

		JLabel lblInjectedAt = new JLabel("Injected at:");
		lblInjectedAt.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblInjectedAt.setBounds(27, 149, 93, 16);
		panel_4.add(lblInjectedAt);

		JLabel label_11 = new JLabel("25 April, Monday, 12:25 PM");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_11.setBounds(105, 149, 191, 16);
		panel_4.add(label_11);



		JPanel panel_5 = new JPanel();
		panel_5.setBounds(429, 306, 303, 171);
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(220, 220, 220));
		panel.add(panel_5);

		JLabel label_9 = new JLabel("45");
		label_9.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		label_9.setBounds(99, 42, 101, 74);
		panel_5.add(label_9);

		JLabel label_10 = new JLabel("mmool/L");
		label_10.setBounds(173, 73, 69, 24);
		panel_5.add(label_10);

		JLabel lblLastInjectedGlucagon = new JLabel("Last Injected Glucagon");
		lblLastInjectedGlucagon.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLastInjectedGlucagon.setBounds(61, 6, 191, 24);
		panel_5.add(lblLastInjectedGlucagon);

		JLabel label_13 = new JLabel("Injected at:");
		label_13.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_13.setBounds(27, 149, 93, 16);
		panel_5.add(label_13);

		JLabel label_14 = new JLabel("25 April, Monday, 12:25 PM");
		label_14.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_14.setBounds(105, 149, 191, 16);
		panel_5.add(label_14);




		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 521, 850, 69);
		panel.add(panel_6);
		panel_6.setLayout(null);

		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ViewHistoryPatient(userDetails.patientDetails.getPatientId());
			}
		});
		btnViewHistory.setBounds(3, 18, 166, 41);
		panel_6.add(btnViewHistory);

		JButton btnShowDeviceConfiguration = new JButton("Device Configuration");
		btnShowDeviceConfiguration.setToolTipText("Configure the device");
		btnShowDeviceConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ConfigureDevicePatient window = new ConfigureDevicePatient(userDetails);
			}
		});

		btnShowDeviceConfiguration.setBounds(510, 18, 166, 41);
		panel_6.add(btnShowDeviceConfiguration);

		JButton btnRequestDoctor = new JButton("Request Doctor");
		btnRequestDoctor.setToolTipText("Request Doctor to setup the device");
		btnRequestDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(null, "Device is already setup by the doctor");
			}						
		});

		btnRequestDoctor.setBounds(170, 18, 166, 41);
		panel_6.add(btnRequestDoctor);

		JButton btnManualInject = new JButton("Manual Inject");
		btnManualInject.setToolTipText("Manually Inject the Dose");
		btnManualInject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(new DeviceController().checkManualInjectAllowed( userDetails.patientDetails.getPatientId())){

					// Inject manually
				}
				else {

					JOptionPane.showMessageDialog(null, "<html>Manual Injection is not allowed for you. <br/> Please request/consult your doctor to inject manually</html>");
				}
			}
		});
		btnManualInject.setBounds(340, 18, 166, 41);
		panel_6.add(btnManualInject);

		JButton btnNotifyCareTaker = new JButton("Notify Care Taker");
		btnNotifyCareTaker.setForeground(new Color(204, 0, 0));
		btnNotifyCareTaker.setToolTipText("Notify the care taker about emergency");
		btnNotifyCareTaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Message has been Sent to Care Taker");
			}


		});
		btnNotifyCareTaker.setBounds(683, 18, 161, 41);
		panel_6.add(btnNotifyCareTaker);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(0, 577, 850, 50);
		backgroundPanel.add(panel_7);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(12, 12, 50, 27);
		panel_7.add(label_2);

		JLabel label_6 = new JLabel("Insulin Catridge");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_6.setBounds(12, 12, 129, 27);
		panel_7.add(label_6);

		JLabel label_7 = new JLabel("Gluagon Catridge");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_7.setBounds(461, 12, 143, 27);
		panel_7.add(label_7);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(604, 12, 107, 27);
		panel_7.add(panel_8);

		labelGlucagonRemPercentage = new JLabel("22 %");
		labelGlucagonRemPercentage.setBounds(41, 0, 56, 27);
		panel_8.add(labelGlucagonRemPercentage);
		labelGlucagonRemPercentage.setFont(new Font("Dialog", Font.BOLD, 17));

		barGlucagon = new JPanel();
		barGlucagon.setBackground(Color.RED);
		barGlucagon.setBounds(0, 0, 107, 27);
		panel_8.add(barGlucagon);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(139, 12, 107, 27);
		panel_7.add(panel_10);

		labelInsulinRemPercentage = new JLabel("72 %");
		labelInsulinRemPercentage.setBounds(41, 0, 54, 27);
		panel_10.add(labelInsulinRemPercentage);
		labelInsulinRemPercentage.setFont(new Font("Dialog", Font.BOLD, 17));

		barInsulin = new JPanel();
		barInsulin.setBackground(new Color(255, 140, 0));
		barInsulin.setBounds(0, 0, 107, 27);
		panel_10.add(barInsulin);

		JButton btnNewButton = new JButton("Refill");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeviceController().refillGlucagon(userDetails.patientDetails.getPatientId());
				barGlucagon.setBounds(0,0,107,27);
				barGlucagon.setBackground(new Color(50, 205, 50));
				labelGlucagonRemPercentage.setText("100 %");
				barGlucagon.validate();
			}
		});
		btnNewButton.setIcon(new ImageIcon(MainScreen.class.getResource("/media/refill.png")));
		btnNewButton.setBounds(723, 8, 97, 38);
		panel_7.add(btnNewButton);

		JButton button = new JButton("Refill");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeviceController().refillInsulin(userDetails.patientDetails.getPatientId());
				barInsulin.setBounds(0,0,107,27);
				barInsulin.setBackground(new Color(50, 205, 50));
				labelInsulinRemPercentage.setText("100 %");
				barInsulin.validate();

			}
		});
		button.setIcon(new ImageIcon(MainScreen.class.getResource("/media/refill.png")));
		button.setBounds(258, 8, 97, 38);
		panel_7.add(button);

		JPanel panel_12 = new JPanel();
		panel_12.setBounds(415, 6, 4, 33);
		panel_7.add(panel_12);


		BufferedImage img=null;
		try {
			img = ImageIO.read(MainScreen.class.getResource("/media/battery2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.setVisible(true);
	}
}
