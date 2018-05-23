import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Driver {

	
	public static void main(String[] args)  {
		
		JFrame environment = new JFrame();

		Start start = new Start();
		
		environment.setSize(700,1000);
		environment.setTitle("Brick Busters");
		environment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		environment.add(start);
		environment.setVisible(true);
		
	}
}
