import java.awt.EventQueue;

import view.*;

public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new view.Start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
