package autocomplete;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotHelper {

	public static void TypeString(String text,Robot robot)
	{
		for(int i=0;i<text.length();i++)
		{
			char c = text.charAt(i);
			
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			
			if (keyCode==KeyEvent.VK_UNDEFINED)
			{
				continue;
			}
			
	        if (Character.isUpperCase(c)) {
	            robot.keyPress(KeyEvent.VK_SHIFT);
	        }
	        
			
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
			
			if (Character.isUpperCase(c)) {
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	        }
		}
	}
	
	
	
}
