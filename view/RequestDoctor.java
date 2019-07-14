package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controller.BasicController;
import controller.PatientController;
import controller.UserController;
import model.DoctorModel;
import model.UserModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RequestDoctor {

	private JFrame frame;
	private ImageIcon imageIcon;
	UserModel userDetails;

	BasicController basicController;
	UserController userObj;

	// Parameterized Constructor
	public RequestDoctor(UserModel userDetails) {
		initialize();

		userObj=new UserController();
		basicController=new BasicController();
		this.userDetails=userDetails;		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 667, 397);
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
		panel_2.setBounds(71, 49, 491, 261);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 525, 53);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setLayout(null);

		JLabel lblRegister = new JLabel("Request Doctor");
		lblRegister.setBounds(160, 15, 171, 24);
		panel_1.add(lblRegister);
		lblRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		JLabel lblEmail = new JLabel("Doctor Email");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(75, 125, 118, 31);
		panel_2.add(lblEmail);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(237, 126, 231, 27);
		java.sql.ResultSet doctors=new DoctorModel().getAllDoctors();
		try {
			while ((doctors.next())) {
				comboBox.addItem(doctors.getString(2)+" "+doctors.getString(3)+"-"+doctors.getString(4));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panel_2.add(comboBox);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(new PatientController().requestDoctor(userDetails, comboBox.getSelectedItem().toString().split("-")[1]))
					frame.dispose();
			}
		});

		btnSubmit.setBounds(114, 189, 114, 25);
		panel_2.add(btnSubmit);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		btnBack.setBounds(254, 187, 117, 29);
		panel_2.add(btnBack);

		JLabel lblEnterTheDoctors = new JLabel("<html>Enter the doctor's email address and request will be sent to the doctor to configure the device. </html>");
		lblEnterTheDoctors.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblEnterTheDoctors.setBounds(65, 65, 403, 44);
		panel_2.add(lblEnterTheDoctors);

		frame.setVisible(true);
	}
}
