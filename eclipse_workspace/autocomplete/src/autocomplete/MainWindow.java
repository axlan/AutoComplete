package autocomplete;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class MainWindow extends JFrame {

	private JButton enableButton;
	
	private JButton addButton;
	
	final private EntryModel model=new EntryModel();
	final JTable myTable = new JTable(model);
	
	final MutableBoolean recording=new MutableBoolean();
	
	final String enableText="Enable";
	final String disableText="Disable";

	
	public MainWindow(String arg0) throws HeadlessException, AWTException {
		super(arg0);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		recording.bool=false;
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		enableButton=new JButton(enableText);
		
		getContentPane().add(enableButton);

		
		
 
        
		myTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		myTable.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(myTable);
 
        //Add the scroll pane to this panel.
        getContentPane().add(scrollPane);
        
        
        addButton=new JButton("Add Entry");
		
		getContentPane().add(addButton);
        
        
        pack();
		
        setVisible(true);
		
        
		enableButton.addActionListener(
				new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if(enableButton.getText().equals(enableText))
						{
							recording.bool=true;
							enableButton.setText(disableText);
						}
						else
						{
							recording.bool=false;
							enableButton.setText(enableText);
						}
						
					}
					
				}
		);
		
		addButton.addActionListener(
				new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						model.AddRow();
						myTable.updateUI();
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
        GlobalScreen.getInstance().addNativeKeyListener(new MyKeyListener());

		
	}

	
	class MyKeyListener implements NativeKeyListener
	{
		boolean active=false;

		StringBuilder typedText;
		
		Robot robot;
		
		int curIndex;
		
		int ignore;
		
		MyKeyListener() throws AWTException
		{
			robot=new Robot();
		}
		
		
		@Override
		public void nativeKeyPressed(NativeKeyEvent arg0) {
			
			
			
			if(recording.bool)
			{
				if(!active)
				{
					//ctrl+alt+a
					if((arg0.getModifiers()&NativeKeyEvent.CTRL_MASK)!=0
							&&(arg0.getModifiers()&NativeKeyEvent.ALT_MASK)!=0
							&&arg0.getKeyCode()==NativeKeyEvent.VK_A
						)
					{
						typedText=new StringBuilder();
						active=true;
						curIndex=0;
						ignore=0;
					}
				}
				else
				{
					if(ignore>0)
					{
						ignore--;
						return;
					}
					
					
					if(arg0.getKeyCode()==NativeKeyEvent.VK_ENTER)
					{
						active=false;
					}
					
					
					if(arg0.getKeyCode()==NativeKeyEvent.VK_RIGHT)
					{
						RobotHelper.TypeKey(NativeKeyEvent.VK_DOWN, robot);
						//System.out.println("Right");
						ignore=1;
					}
					
					
					
					
					/*
					if(arg0.getKeyCode()!=NativeKeyEvent.VK_BACK_SPACE)
					{
						robot.keyPress(NativeKeyEvent.VK_BACK_SPACE);
						robot.keyRelease(NativeKeyEvent.VK_BACK_SPACE);
						//System.out.println("backspace");
					}*/
					
				
				/*
				char c=(char)arg0.getKeyCode();
				
				
				if((arg0.getModifiers()&NativeKeyEvent.SHIFT_MASK)==0)
				{
					c=new String(""+c).toLowerCase().charAt(0);
				}
				
				
				typedText.append(c);
				*/

				/*
				try {
					
				
				
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
				*/
				}
			}
			else
			{
				active=false;
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
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6655819945837463099L;

	/**
	 * @param args
	 * @throws AWTException 
	 * @throws HeadlessException 
	 */
	public static void main(String[] args) throws HeadlessException, AWTException {
		new MainWindow("AutoComplete");

	}

}
