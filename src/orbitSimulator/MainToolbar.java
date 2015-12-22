package orbitSimulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class MainToolbar extends JPanel implements MainFrameListener {
	
	private MainFrameListener newParamListener;
	// Parameterized components for comboboxes stored in respecive arraylists 
		// OrbitTypes  
		private String selectType = "Select Type"; // first entry
		private String circular = "Circular"; // second entry
		private String elliptical = "Elliptical"; // third entry
		private String hyperbolic = "Hyperbolic"; // fourth entry
		// OrbitingBodies
		private String selectOrbitingBody = "Select Orbiting Body";
		private String earth = "Earth";
		private String moon = "Moon";
		private String mars = "Mars";
		// Maneuvres
		private String selectManoeuvre = "Select Manoeuvre";
		private String orbit = "Orbit";
		private String hohmannTransfer = "Hohmann Transfer";
	// BUTTONS
		private JButton btnSelectOrbitType;
		private JButton btnSelectOrbiting;
		private JButton btnSelectManoeuvre;
	// combobox
		private JComboBox comboBoxOrbitType;
		
		
	MainToolbar()
	{
		
		JLabel lblWhatOrbit = new JLabel("Orbit type:");
		lblWhatOrbit.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblWhatOrbit, "cell 0 0,alignx left,gapright 0,aligny center");
		
		ArrayList<String> orbitTypes = new ArrayList<String>();
		orbitTypes.add(new String(selectType));
		orbitTypes.add(new String(circular));
		orbitTypes.add(new String(elliptical));
		orbitTypes.add(new String(hyperbolic));
		comboBoxOrbitType = new JComboBox(orbitTypes.toArray());
		comboBoxOrbitType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		
		//comboBoxOrbitType.addActionListener(this);
		
		
		add(comboBoxOrbitType, "cell 1 0,growx");
		
		JLabel lblOrbitingBody = new JLabel("Orbiting?");
		lblOrbitingBody.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblOrbitingBody, "cell 0 1,alignx left,aligny center");
		
		ArrayList<String> orbitingBody = new ArrayList<String>();
		orbitingBody.add(new String(selectOrbitingBody));
		orbitingBody.add(new String(earth));
		orbitingBody.add(new String(moon));
		orbitingBody.add(new String(mars));
		JComboBox comboBoxOrbitingBody = new JComboBox(orbitingBody.toArray());
		comboBoxOrbitingBody.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(comboBoxOrbitingBody, "cell 1 1,growx");
		
		JLabel lblManoeuvreType = new JLabel("Manoeuvre type?");
		lblManoeuvreType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblManoeuvreType, "cell 0 2,alignx left,aligny center");
		
		ArrayList<String> manoeuvreTypes = new ArrayList<String>();
		manoeuvreTypes.add(new String(selectManoeuvre));
		manoeuvreTypes.add(new String(orbit));
		manoeuvreTypes.add(new String(hohmannTransfer));
		JComboBox comboBoxMaoeuvreType = new JComboBox(manoeuvreTypes.toArray());
		comboBoxMaoeuvreType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(comboBoxMaoeuvreType, "cell 1 2,growx");
		
		// BUTTONS
		
		ActionListener ToolbarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if(clicked == btnSelectOrbitType)
				{
					System.out.println("works!");
					inputPanelChanger(comboBoxOrbitType.getSelectedItem().toString());
				} 
				else if (clicked == btnSelectOrbiting)
				{
					System.out.println("really works!!");
				} 
				else if (clicked == btnSelectManoeuvre)
				{
					System.out.println("really really works!!!");
				}
				else
				{
					System.out.println("really really works!!! and some kind of wierd error lol");
				}
			}
		};
		
		btnSelectOrbitType = new JButton("Select");
		btnSelectOrbitType.addActionListener(ToolbarListener); 
		add(btnSelectOrbitType, "cell 2 0");
		
		btnSelectOrbiting = new JButton("Select");
		btnSelectOrbiting.addActionListener(ToolbarListener);
		add(btnSelectOrbiting, "cell 2 1");
		
		btnSelectManoeuvre = new JButton("Select");
		btnSelectManoeuvre.addActionListener(ToolbarListener);
		add(btnSelectManoeuvre, "cell 2 2");
	}

	public void setMainFrameListener(MainFrameListener listener)
	{
		this.newParamListener = listener;
	}


	
	//@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == btnSelectOrbitType)
		{
			if(newParamListener != null)
			{
				System.out.println("new orbit type");
			}
		}
		if(clicked == btnSelectOrbiting)
		{
			if(newParamListener != null)
			{
				System.out.println("selected somewhere new to orbit");
			}
		}
		if(clicked == btnSelectManoeuvre)
		{
			if(newParamListener != null)
			{
				System.out.println("new manoeuvre selected");
			}
		}
	}

	//@Override
	public void newParameterSelected(String newParam) {
		// TODO Auto-generated method stub
		
	}
	
	protected void inputPanelChanger(String newParam) {
//		switch(newParam)
//		{
//		case "Selected Type":
//			// error message 
//			break;
//		case "Circular":
//			circularPanel.setVisible(true);
//			ellipticalPanel.setVisible(false);
//			break;
//		case "Elliptical":
//			ellipticalPanel.setVisible(true);
//			circularPanel.setVisible(false);
//			break;
//		default :
//			// error message
//			break;
//		}
		
	}

	@Override
	public void setNewGraphics(double r, double v, double T, double epsilon,
			String renderScale, double i, ArrayList<Double> canvasUArray, ArrayList<Double> canvasVArray) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void setNewGraphics() {
//		// TODO Auto-generated method stub
//		
//	}
	
}
