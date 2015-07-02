package orbitSimulator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class OrbitProgram {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
					JFrame OrbitProgram = new OrbitMainFrame();
					OrbitProgram.setSize(1100, 630);
					OrbitProgram.setVisible(true);
					OrbitProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			}
		});

	}

}
