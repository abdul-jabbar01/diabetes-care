package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

//Clock that shows the time live on the Main Screen
public class Clock extends JPanel{
	public Clock() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 3));
		ClockLabel lblNewLabel = new ClockLabel("date");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lblNewLabel);
		
		
		ClockLabel lblNewLabel_1 = new ClockLabel("time");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNewLabel_1);
		
		ClockLabel lblNewLabel_2 = new ClockLabel("day");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);

		this.add(lblNewLabel_2);
	}
}


class ClockLabel extends JLabel implements ActionListener {
	 
	  String type;
	  SimpleDateFormat sdf;
	 
	  public ClockLabel(String type) {
	    this.type = type;
	    setForeground(Color.black);
	 
	    switch (type) {
	      case "date" : sdf = new SimpleDateFormat("  MMMM dd yyyy");
	                    setFont(new Font("sans-serif", Font.PLAIN, 12));
	                    setHorizontalAlignment(SwingConstants.LEFT);
	                    break;
	      case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
	                    setFont(new Font("sans-serif", Font.PLAIN, 20));
	                    setHorizontalAlignment(SwingConstants.CENTER);
	                    break;
	      case "day"  : sdf = new SimpleDateFormat("EEEE  ");
	                    setFont(new Font("sans-serif", Font.PLAIN, 12));
	                    setHorizontalAlignment(SwingConstants.RIGHT);
	                    break;
	      default     : sdf = new SimpleDateFormat();
	                    break;
	    }
	 
	    Timer t = new Timer(1000, this);
	    t.start();
	  }
	 
	  public void actionPerformed(ActionEvent ae) {
	    Date d = new Date();
	    setText(sdf.format(d));
	  }
	}