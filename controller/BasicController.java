package controller;

import java.awt.event.KeyEvent;

//Basic Controller that contains all the basic functions that can be reused in different controllers
public class BasicController {

	//Validate the Key Event if it is valid name
	public void validateName(KeyEvent event) {
		
		char c=event.getKeyChar();
		
		if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE))) {
			event.consume();
		}
	}
	
	//Validate the Key Event if it is valid number
	public void validateNumber(KeyEvent event) {

		char c=event.getKeyChar();
		
		if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE))) {
			event.consume();
		}
	}

	//Returns true if email is valid
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
}
