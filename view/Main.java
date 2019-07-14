package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 792, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		BackgroundPanel backgroundPanel = new BackgroundPanel(null);
		backgroundPanel.setSize(792, 494);
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

		JLabel lblRegister = new JLabel("Insulin and Glucagon Pump Simulation");
		lblRegister.setBounds(35, 19, 484, 24);
		panel_1.add(lblRegister);
		lblRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegister.setForeground(new Color(255, 255, 255));

		JButton btnSubmit = new JButton("Register");
		
		// Action listener for the Signup Button
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new SignUp();
				frame.dispose();

			}
		});
		btnSubmit.setHorizontalAlignment(SwingConstants.TRAILING);
		btnSubmit.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnSubmit.setIcon(new ImageIcon(Main.class.getResource("/media/register4.png")));
		btnSubmit.setBounds(65, 115, 180, 163);
		panel_2.add(btnSubmit);

		JButton btnLogin = new JButton("Login");
		
		// Action Listener for the Login button
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignIn();
				frame.dispose();
			}
		});
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnLogin.setIcon(new ImageIcon(Main.class.getResource("/media/login2.png")));
		btnLogin.setBounds(287, 115, 180, 163);
		panel_2.add(btnLogin);
		frame.setVisible(true);
	}
}
