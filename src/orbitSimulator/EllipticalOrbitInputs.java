package orbitSimulator;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;

public class EllipticalOrbitInputs extends JPanel implements ActionListener {
	// input text fields - NB need to add each new tf to getUserInputs() and a private parameter below  
	private  JTextField tfArgOfPeri;
	private  JTextField tfPeriapsis;
	private  JTextField tfApoapsis;
	private  JTextField tfSemimajorAxis;
	private  JTextField tfEccentricity;
	private  JTextField tfPeriod;
	private  JTextField tfRAAN;
	
	private double ArgOfPeri;
	private double Periapsis;
	private double Apoapsis;
	private double SemimajorAxis; 
	private double Eccentricity;
	private double OrbitalPeriod;
	private double RAAN;
	private double Period;
	
	private boolean ArgOfPeriAdded;
	private boolean PeriapsisAdded;
	private boolean ApoapsisAdded;
	private boolean SemimajorAxisAdded; 
	private boolean EccentricityAdded;
	private boolean OrbitalPeriodAdded;
	private boolean RAANAdded;
	private boolean PeriodAdded;
	
	
	private JButton btnCalculateEllipticalOrbit;
	private MainFrameListenerElliptical newGraphicsListener;
	private JTextField tfSME;
	private JTextField tfVelocity;
	private JTextField tfInclination;
	private DocumentListener tfListener;
	

	EllipticalOrbitInputs()
	{
		setLayout(new MigLayout("", "[22.00][18.00][29.00][83.00][59.00][73.00][81.00]", "[][][][12.00][][14.00][][center][][][][]"));
		
		JLabel lblEllipticalOrbitInputs = new JLabel("Elliptical Orbit Inputs");
		lblEllipticalOrbitInputs.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		add(lblEllipticalOrbitInputs, "cell 0 0 4 1");
		
		JLabel lblArgumentOfPeriapsis = new JLabel("Arg of Periapsis");
		lblArgumentOfPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblArgumentOfPeriapsis, "cell 1 1 2 1");
		
		tfArgOfPeri = new JTextField();
		tfArgOfPeri.setBackground(Color.WHITE);
		tfArgOfPeri.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfArgOfPeri, "cell 3 1,growx");
		tfArgOfPeri.setColumns(10);
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblRadius, "cell 1 2 2 1");
		
		JLabel lblPeriapsis = new JLabel("Periapsis");
		lblPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblPeriapsis, "cell 2 3,alignx left");
		
		tfPeriapsis = new JTextField();
		tfPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfPeriapsis, "cell 3 3,growx");
		tfPeriapsis.setColumns(10);
		
		JLabel lblApoapsis = new JLabel("Apoapsis");
		lblApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblApoapsis, "cell 4 3,alignx left");
		
		tfApoapsis = new JTextField();
		tfApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfApoapsis, "cell 5 3,growx");
		tfApoapsis.setColumns(10);
		
		JLabel lblSemimajorAxis = new JLabel("Semimajor Axis");
		lblSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSemimajorAxis, "cell 1 4 2 1");
		
		tfSemimajorAxis = new JTextField();
		tfSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfSemimajorAxis, "cell 3 4,growx");
		tfSemimajorAxis.setColumns(10);
		
		JLabel lblEccentricity = new JLabel("Eccentricity");
		lblEccentricity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblEccentricity, "cell 1 5 2 1");
		
		tfEccentricity = new JTextField();
		tfEccentricity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfEccentricity, "cell 3 5,growx");
		tfEccentricity.setColumns(10);
		
		JLabel lblInclination = new JLabel("Inclination");
		lblInclination.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblInclination, "cell 1 6 2 1,alignx left");
		
		tfInclination = new JTextField();
		tfInclination.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfInclination, "cell 3 6,growx,aligny top");
		tfInclination.setColumns(10);
		
		JLabel lblVelecity = new JLabel("Velocity @");
		lblVelecity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblVelecity, "cell 1 7 2 1,alignx left");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(comboBox, "cell 3 7,growx");
		
		tfVelocity = new JTextField();
		tfVelocity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfVelocity, "cell 4 7 2 1,growx");
		tfVelocity.setColumns(10);
		
		JLabel lblSME = new JLabel("SME");
		lblSME.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSME, "cell 1 8 2 1");
		
		tfSME = new JTextField();
		tfSME.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfSME, "cell 3 8,growx");
		tfSME.setColumns(10);
		
		JLabel lblRaan = new JLabel("RAAN");
		lblRaan.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblRaan, "cell 1 9 2 1");
		
		tfRAAN = new JTextField();
		tfRAAN.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfRAAN, "cell 3 9,growx");
		tfRAAN.setColumns(10);
		
		JLabel lblOrbitalPeriod = new JLabel("Orbital Period");
		lblOrbitalPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblOrbitalPeriod, "cell 1 10 2 1,alignx left");
		
		tfPeriod = new JTextField();
		tfPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfPeriod, "cell 3 10,growx,aligny top");
		tfPeriod.setColumns(10);
		
		btnCalculateEllipticalOrbit = new JButton("Calculate Orbit");
		btnCalculateEllipticalOrbit.addActionListener(this);
		add(btnCalculateEllipticalOrbit, "cell 4 11 3 1");
		
		// Listen for changes to textFields
		tfArgOfPeri.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
			     
			  }
			});
		tfPeriapsis.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfApoapsis.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfSemimajorAxis.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfEccentricity.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfInclination.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfVelocity.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfSME.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfPeriod.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		tfRAAN.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     System.out.println("tf edited");
				  }
				});
		
		
		
	} // END CONSTRUCTOR
	
	// listener model view controller architecture
		public void setNewGraphics(MainFrameListenerElliptical listener)
		{
			System.out.println("setNewGraphics()");
			this.newGraphicsListener = listener;
		}
		
		/*@Override -  I'm not sure why this isnt required as it is in CircularOrbitInputs using the same architecture. 
		               The only possibility I can think of is that circularPanel has a button which may mean that it needs 
		               overriding.*/
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("in actionPerformed(ActionEvent e)");
			//getOrbitRenderScale();
			calculateEllipticalOrbit();
			newGraphicsListener.setNewGraphics();
		}
	
		private void getOrbitRenderScale() {
				// TODO Auto-generated method stub
				
			}
	
		private void calculateEllipticalOrbit() {
			System.out.println("in calculateEllipticalOrbit()");	
			// get all the inputs and set them to private parameters 
				getUserInputs();
				// calculations (multiple methods)
				// write calculated values to relevant text fields
				
			}
		
		private void getUserInputs() {
			
			if (isNumeric(tfArgOfPeri.getText()) == true) {
				ArgOfPeri = Double.parseDouble(tfArgOfPeri.getText());
				ArgOfPeriAdded = true;
				
			}
			else {
				ArgOfPeriAdded = false;
			}
			//System.out.println("ArgOfPeri = " + ArgOfPeri);
			
			if (isNumeric(tfPeriapsis.getText()) == true) {
				Periapsis = Double.parseDouble(tfPeriapsis.getText());
				PeriapsisAdded = true;
			}
			else {
				PeriapsisAdded = false;
			}
			//System.out.println("Periapsis = " + Periapsis);
			
			if (isNumeric(tfApoapsis.getText()) == true) {
				Apoapsis = Double.parseDouble(tfApoapsis.getText());
				ApoapsisAdded = true;
			}
			else {
				ApoapsisAdded = false;
			}
			//System.out.println("Apoapsis = " + Apoapsis);
			
			if (isNumeric(tfSemimajorAxis.getText()) == true) {
				SemimajorAxis = Double.parseDouble(tfSemimajorAxis.getText());
				SemimajorAxisAdded = true;
			}
			else {
				SemimajorAxisAdded = false;
			}
			//System.out.println("SemimajorAxis = " + SemimajorAxis);
			
			if (isNumeric(tfEccentricity.getText()) == true) {
				Eccentricity = Double.parseDouble(tfEccentricity.getText());
				EccentricityAdded = true;
			}
			else {
				EccentricityAdded = false;
			}
			//System.out.println("Eccentricity = "  + Eccentricity);
			
			if (isNumeric(tfPeriod.getText()) == true) {
				OrbitalPeriod = Double.parseDouble(tfPeriod.getText());
				OrbitalPeriodAdded = true;
			}
			else {
				OrbitalPeriodAdded = false;
			}
			//System.out.println("OrbitalPeriod = " + OrbitalPeriod);
			
			if (isNumeric(tfRAAN.getText()) == true) {
				RAAN = Double.parseDouble(tfRAAN.getText());
				RAANAdded = true;
			}
			else {
				RAANAdded = false;
			}
			//System.out.println("RANN = " + RAAN);
			
			if (isNumeric(tfPeriod.getText()) == true) {
				Period = Double.parseDouble(tfPeriod.getText());
				PeriodAdded = true;
			}			
			else {
				PeriodAdded = false;
			}
			//System.out.println("Period = " + Period);
		}
		

		public static void resetEllipticalPanel() {
			// TODO Auto-generated method stub
			
		}
		
		public static boolean isNumeric(String str)  
		{  
		  try  
		  {  
		    double d = Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;  
		}
	
}
