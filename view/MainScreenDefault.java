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

public class MainScreenDefault extends JFrame{

	private JFrame frame;
	UserModel userDetails;

	JLabel labelGlucagonRemPercentage;
	JLabel labelInsulinRemPercentage ;
	JLabel labelBatteryLevel;
	JPanel barGlucagon ;
	JPanel barInsulin ;


	// Parameterized Constructor
	public MainScreenDefault(UserModel userDetails) {

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
		lblNewLabel.setIcon(new ImageIcon(MainScreenDefault.class.getResource("/media/battery2.png")));
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
		btnNewButton_1.setBounds(740, 5, 99, 41);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new SignIn();
				frame.dispose();
			}
		});

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(220, 161, 404, 258);
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(220, 220, 220));
		panel.add(panel_5);

		JLabel lblYouHaveLogged = new JLabel("<html>You have logged into your device for the very first time. So, you need to contact your doctor to set the configurations for your device. Click the following button to request configurations  settings for device.</html> ");
		lblYouHaveLogged.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblYouHaveLogged.setBounds(27, 44, 371, 98);
		panel_5.add(lblYouHaveLogged);

		JButton button_1 = new JButton("Request Doctor");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RequestDoctor(userDetails);
			}
		});
		button_1.setBounds(139, 154, 135, 41);
		panel_5.add(button_1);

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
		btnShowDeviceConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigureDevicePatient window = new ConfigureDevicePatient(userDetails);

			}
		});
		btnShowDeviceConfiguration.setBounds(510, 18, 166, 41);
		panel_6.add(btnShowDeviceConfiguration);

		JButton btnRequestDoctor = new JButton("Request Doctor");
		btnRequestDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new RequestDoctor(userDetails);
			}


		});
		btnRequestDoctor.setBounds(170, 18, 166, 41);
		panel_6.add(btnRequestDoctor);

		JButton btnManualInject = new JButton("Manual Inject");
		btnManualInject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(new DeviceController().checkManualInjectAllowed( userDetails.patientDetails.getPatientId())){

				}
				else {

					JOptionPane.showMessageDialog(null, "<html>Manual Injection is not allowed for you. <br/> Please request consult your doctor to inject manually</html>");
				}
			}
		});
		btnManualInject.setBounds(340, 18, 166, 41);
		panel_6.add(btnManualInject);

		JButton btnNotifyCareTaker = new JButton("Notify Care Taker");
		btnNotifyCareTaker.setForeground(new Color(204, 0, 0));
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
		btnNewButton.setIcon(new ImageIcon(MainScreenDefault.class.getResource("/media/refill.png")));
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
		button.setIcon(new ImageIcon(MainScreenDefault.class.getResource("/media/refill.png")));
		button.setBounds(258, 8, 97, 38);
		panel_7.add(button);

		JPanel panel_12 = new JPanel();
		panel_12.setBounds(415, 6, 4, 33);
		panel_7.add(panel_12);

		BufferedImage img=null;
		try {
			img = ImageIO.read(MainScreenDefault.class.getResource("/media/battery2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.setVisible(true);
	}
}
