package autocomplete;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;




public class MainWindow extends JFrame {

	private JButton testButton;
	
	final StringBuilder typedText=new StringBuilder();
	final MutableBoolean recording=new MutableBoolean();
	
	public MainWindow(String arg0) throws HeadlessException {
		super(arg0);

		setSize(800, 400);
		setVisible(true);
		
		recording.bool=true;
		
		
		
		testButton=new JButton("Test");
		
		add(testButton);
		
		testButton.addActionListener(
				new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							Robot robot=new Robot();
						
						
							try {
								Thread.sleep(5*1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							recording.bool=false;
							RobotHelper.TypeString("Testing 123\nTest 345\n", robot);
							
							RobotHelper.TypeString(typedText.toString(), robot);
							typedText.delete(0,typedText.length());
							recording.bool=true;
						
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
		);
		
		try {
             GlobalScreen.registerNativeHook();
	     }
	     catch (NativeHookException ex) {
	             System.err.println("There was a problem registering the native hook.");
	             System.err.println(ex.getMessage());
	             System.exit(1);
	     }

		//Construct the example object and initialze native hook.
        GlobalScreen.getInstance().addNativeKeyListener(
        		new NativeKeyListener()
        		{

					@Override
					public void nativeKeyPressed(NativeKeyEvent arg0) {
						
						if(recording.bool)
						{
							char c=(char)arg0.getKeyCode();
							
							
							if((arg0.getModifiers()&NativeKeyEvent.SHIFT_MASK)==0)
							{
								c=new String(""+c).toLowerCase().charAt(0);
							}
							
							
							typedText.append(c);
						}
					}

					@Override
					public void nativeKeyReleased(NativeKeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void nativeKeyTyped(NativeKeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
        			
        		}
        		
        		
        		
        		);

		
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6655819945837463099L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MainWindow("AutoComplete");

	}

}
