package orbitSimulator;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class EllipticalOrbitInputs extends JPanel implements ActionListener {
	
	// input text fields - NB need to add each new tf to getUserInputs() and a private parameter below
	private String renderScale;
	private  JTextField tfArgOfPeri;
	private  JTextField tfPeriapsis;
	private static  JTextField tfApoapsis;
	private  JTextField tfSemimajorAxis;
	private  JTextField tfEccentricity;
	private  JTextField tfPeriod;
	private  JTextField tfTrueAnomaly;
	private JTextField tfSME;
	private JTextField tfRadiusForVelocity;
	private JTextField tfVelocityAtRadius;
	private JTextField tfVelocityAtApoapsis;
	private JTextField tfVelocityAtPeriapsis;
	private JTextField tfInclination;
	
	private Double ArgOfPeri = null;
	private Double Periapsis = null;
	private Double Apoapsis = null;
	private Double SemimajorAxis = null; 
	private Double Eccentricity = null;
	private Double OrbitalPeriod = null;
	private Double TrueAnomaly = null;
	private Double Period = null;
	private Double SME = null;
	private Double RadiusForVelocity = null;
	private Double VelocityAtRadius = null;
	private Double VelocityAtApoapsis = null;
	private Double VelocityAtPeriapsis = null;
	private Double Inclination = null;
	
	private boolean ArgOfPeriAdded;
	private boolean PeriapsisAdded;
	private boolean ApoapsisAdded;
	private boolean SemimajorAxisAdded; 
	private boolean EccentricityAdded;
	private boolean OrbitalPeriodAdded;
	private boolean TrueAnomalyAdded;
	private boolean PeriodAdded;
	private boolean SMEAdded;
	private boolean VelocityAtRadiusAdded;
	private boolean RadiusForVelocityAdded;
	private boolean VelocityAtApoapsisAdded;
	private boolean VelocityAtPeriapsisAdded;
	private boolean InclinationAdded;
	
	private double mu;
	
	private double pi = Math.PI;
	
	
	private JButton btnCalculateEllipticalOrbit;
	private MainFrameListenerElliptical newGraphicsListener;
	
	private DocumentListener tfListener;
	
	private JLabel lblEllipticalInputWarning;
	JCheckBox chckbxNewVelocityAtRadius;
	Color defaultBackground = Color.WHITE;
	Color warningBackground = Color.decode("#FF9A9A");
	private JLabel lblUnitsApoPeriRadius;
	private JLabel lblUnitsSemimajorAxis;
	private JLabel lblUnitDegrees;
	private JLabel lblUnitDegree1;
	private JLabel lblUnitHr;
	private JLabel lblUnitVelocutyAtRadiusKm;
	private JLabel lblUnitVelocityAtRadius1;
	private JLabel lblUnitSME;
	private JLabel lblub;
	private JLabel lblKm;
	private JLabel lblKm_1;
	private JLabel lblNewLabel;
	private JLabel lblTrueAnomaly;
	private JRadioButton rdbtnIllustrative;
	private JRadioButton rdbtnAccurate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	EllipticalOrbitInputs()
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		setLayout(new MigLayout("", "[22.00][18.00][29.00][74.00,grow][60.00][37.00,grow][79.00][44.00]", "[][][][12.00][][14.00][][center][][][][][][][]"));
		
		JLabel lblEllipticalOrbitInputs = new JLabel("Elliptical Orbit Inputs");
		lblEllipticalOrbitInputs.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		add(lblEllipticalOrbitInputs, "cell 0 0 4 1");
		
		lblEllipticalInputWarning = new JLabel("numbers only (3.4, +3.4, -3.4)");
		lblEllipticalInputWarning.setForeground(Color.decode("#FF9A9A"));
		lblEllipticalInputWarning.setVisible(false);
		add(lblEllipticalInputWarning, "cell 4 0 4 1");
		
		
		JLabel lblArgumentOfPeriapsis = new JLabel("Arg of Periapsis");
		lblArgumentOfPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblArgumentOfPeriapsis, "cell 1 1 2 1");
		
		tfArgOfPeri = new JTextField();
		tfArgOfPeri.setBackground(Color.WHITE);
		tfArgOfPeri.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfArgOfPeri, "cell 3 1,growx");
		tfArgOfPeri.setColumns(10);
		
		lblub = new JLabel("\u00B0");
		add(lblub, "cell 4 1");
		
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
		
		lblKm_1 = new JLabel("Km                ");
		lblKm_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblKm_1, "cell 4 3");
		
		JLabel lblApoapsis = new JLabel("Apoapsis");
		lblApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblApoapsis, "cell 5 3,alignx right");
		
		tfApoapsis = new JTextField();
		tfApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfApoapsis, "cell 6 3,growx");
		tfApoapsis.setColumns(10);
		tfApoapsis.getDocument().putProperty("owner", tfApoapsis);
		tfApoapsis.setName("apoapsis");
		
		
		lblUnitsApoPeriRadius = new JLabel("Km");
		lblUnitsApoPeriRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitsApoPeriRadius, "cell 7 3");
		
		JLabel lblSemimajorAxis = new JLabel("Semimajor Axis");
		lblSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSemimajorAxis, "cell 1 4 2 1");
		
		tfSemimajorAxis = new JTextField();
		tfSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfSemimajorAxis, "cell 3 4,growx");
		tfSemimajorAxis.setColumns(10);
		
		lblUnitsSemimajorAxis = new JLabel("Km");
		lblUnitsSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitsSemimajorAxis, "cell 4 4");
		
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
		
		lblUnitDegrees = new JLabel(" \u00B0");
		lblUnitDegrees.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitDegrees, "cell 4 6");
		
		JLabel lblVelecity = new JLabel("Velocity at:");
		lblVelecity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblVelecity, "flowx,cell 1 7 2 1,alignx left");
		
		final JLabel lblUnitRadiusForVelocity = new JLabel("Radius =");
		lblUnitRadiusForVelocity.setForeground(Color.LIGHT_GRAY);
		lblUnitRadiusForVelocity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitRadiusForVelocity, "cell 3 7,alignx right");
		
		tfRadiusForVelocity = new JTextField();
		tfRadiusForVelocity.setEditable(false);
		tfRadiusForVelocity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfRadiusForVelocity, "cell 4 7,growx");
		tfRadiusForVelocity.setColumns(10);

		
		final JLabel lblUnitkmAndArrow = new JLabel("Km \u21D2");
		lblUnitkmAndArrow.setForeground(Color.LIGHT_GRAY);
		lblUnitkmAndArrow.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitkmAndArrow, "flowx,cell 5 7");
		
		
		tfVelocityAtRadius = new JTextField();
		tfVelocityAtRadius.setEditable(false);
		tfVelocityAtRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfVelocityAtRadius, "cell 6 7");
		tfVelocityAtRadius.setColumns(10);
		
		lblUnitVelocityAtRadius1 = new JLabel("Km/s");
		lblUnitVelocityAtRadius1.setForeground(Color.LIGHT_GRAY);
		lblUnitVelocityAtRadius1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitVelocityAtRadius1, "cell 7 7");
		
		lblTrueAnomaly = new JLabel("True Anomaly");
		lblTrueAnomaly.setForeground(Color.LIGHT_GRAY);
		lblTrueAnomaly.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblTrueAnomaly, "cell 3 8,alignx right");
		
		tfTrueAnomaly = new JTextField();
		tfTrueAnomaly.setEditable(false);
		tfTrueAnomaly.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfTrueAnomaly, "cell 4 8,growx");
		tfTrueAnomaly.setColumns(10);
		
		lblUnitDegree1 = new JLabel(" \u00B0");
		lblUnitDegree1.setForeground(Color.LIGHT_GRAY);
		lblUnitDegree1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitDegree1, "cell 5 8");
		
		JLabel lblVelocityAtRP = new JLabel("V at: Periapsis");
		lblVelocityAtRP.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblVelocityAtRP, "cell 1 9 2 1,alignx trailing");
		
		tfVelocityAtPeriapsis = new JTextField();
		tfVelocityAtPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfVelocityAtPeriapsis, "cell 3 9,growx");
		tfVelocityAtPeriapsis.setColumns(10);
		
		
		lblKm = new JLabel("Km/s");
		lblKm.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblKm, "cell 4 9");
		
		JLabel lblVelocityAtApoapsis = new JLabel("Apoapsis");
		lblVelocityAtApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblVelocityAtApoapsis, "cell 5 9,alignx trailing");
		
		tfVelocityAtApoapsis = new JTextField();
		tfVelocityAtApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfVelocityAtApoapsis, "cell 6 9,growx");
		tfVelocityAtApoapsis.setColumns(10);
		
		
		
		lblUnitVelocutyAtRadiusKm = new JLabel("Km/s");
		lblUnitVelocutyAtRadiusKm.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitVelocutyAtRadiusKm, "cell 7 9");
		
		JLabel lblSME = new JLabel("SME");
		lblSME.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSME, "cell 1 10 2 1");
		
		tfSME = new JTextField();
		tfSME.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfSME, "cell 3 10,growx");
		tfSME.setColumns(10);
		
		Border defaultBorder = tfSME.getBorder();
		
		lblUnitSME = new JLabel("KmKg/s");
		lblUnitSME.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitSME, "cell 4 10");
		
		tfPeriod = new JTextField();
		tfPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfPeriod, "cell 3 11,growx");
		tfPeriod.setColumns(10);
		
		
		// Listen for changes to textFields
		tfArgOfPeri.getDocument().putProperty("owner", tfArgOfPeri); 
		tfArgOfPeri.setName("argofperi");
		tfPeriapsis.getDocument().putProperty("owner", tfPeriapsis);
		tfPeriapsis.setName("periapsis");
		tfSemimajorAxis.getDocument().putProperty("owner", tfSemimajorAxis);
		tfSemimajorAxis.setName("semimajoraxis");
		tfEccentricity.getDocument().putProperty("owner", tfEccentricity);
		tfEccentricity.setName("eccentricity");
		tfInclination.getDocument().putProperty("owner", tfInclination);
		tfInclination.setName("inclination");
		tfPeriod.getDocument().putProperty("owner", tfPeriod);
		tfPeriod.setName("period");
		tfRadiusForVelocity.getDocument().putProperty("owner", tfRadiusForVelocity);
		tfRadiusForVelocity.setName("radiusforvelocity");
		tfSME.getDocument().putProperty("owner", tfSME);
		tfSME.setName("sme");
		tfVelocityAtRadius.getDocument().putProperty("owner", tfVelocityAtRadius);
		tfVelocityAtRadius.setName("velocityatradius");
		tfVelocityAtApoapsis.getDocument().putProperty("owner", tfVelocityAtApoapsis);
		tfVelocityAtApoapsis.setName("velocityatapoapsis");
		tfVelocityAtPeriapsis.getDocument().putProperty("owner", tfVelocityAtPeriapsis);
		tfVelocityAtPeriapsis.setName("velocityatperiapsis");
		tfTrueAnomaly.getDocument().putProperty("owner", tfTrueAnomaly);
		tfTrueAnomaly.setName("trueanomaly");
		
		
		btnCalculateEllipticalOrbit = new JButton("Calculate Orbit");
		btnCalculateEllipticalOrbit.addActionListener(this);
		
		lblUnitHr = new JLabel("hr");
		lblUnitHr.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblUnitHr, "cell 4 11");
		
		lblNewLabel = new JLabel("Select scale");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblNewLabel, "cell 1 12 2 1");
		
		rdbtnIllustrative = new JRadioButton("Illustrative");
		buttonGroup.add(rdbtnIllustrative);
		rdbtnIllustrative.setSelected(true);
		rdbtnIllustrative.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(rdbtnIllustrative, "cell 3 12");
		
		rdbtnAccurate = new JRadioButton("Accurate");
		buttonGroup.add(rdbtnAccurate);
		rdbtnAccurate.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(rdbtnAccurate, "cell 4 12");
		add(btnCalculateEllipticalOrbit, "cell 5 13 3 2");
		
		JLabel lblOrbitalPeriod = new JLabel("Orbital Period");
		lblOrbitalPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblOrbitalPeriod, "cell 1 11 2 1,alignx left");
		
		chckbxNewVelocityAtRadius = new JCheckBox("");
		chckbxNewVelocityAtRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(chckbxNewVelocityAtRadius, "cell 2 7,alignx right");
		chckbxNewVelocityAtRadius.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tfRadiusForVelocity.setEditable(true);
					tfVelocityAtRadius.setEditable(true);
					tfTrueAnomaly.setEditable(true);
					lblUnitRadiusForVelocity.setForeground(Color.BLACK);
					lblUnitkmAndArrow.setForeground(Color.BLACK);
					lblUnitVelocityAtRadius1.setForeground(Color.BLACK);
					lblTrueAnomaly.setForeground(Color.BLACK);
					lblUnitDegree1.setForeground(Color.BLACK);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					tfRadiusForVelocity.setEditable(false);
					tfVelocityAtRadius.setEditable(false);
					tfTrueAnomaly.setEditable(false);
					lblUnitRadiusForVelocity.setForeground(Color.LIGHT_GRAY);
					lblUnitkmAndArrow.setForeground(Color.LIGHT_GRAY);
					lblUnitVelocityAtRadius1.setForeground(Color.LIGHT_GRAY);
					lblTrueAnomaly.setForeground(Color.LIGHT_GRAY);
					lblUnitDegree1.setForeground(Color.LIGHT_GRAY);
				}	
			}
		});
	
		
		
		DocumentListener docListener = new DocumentListener() {

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        event(e);
		    }

		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	event(e);
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	event(e);
		    }

		    private void event(DocumentEvent e) {
		        Object owner = e.getDocument().getProperty("owner");
		        String tfName = ((JTextField) owner).getName();
		       System.out.println("the textfield that changed is: " + tfName);
		       // READ BEFORE STARTING - will be best to put these methods at bottom in helper method section including the switch above  
		       // General logic
		       String tfVal = "unassigned";
		       switch(tfName) {
		       case "argofperi":
			       tfVal = tfArgOfPeri.getText();
		    	   break;
		       case "periapsis":
		    	   tfVal = tfPeriapsis.getText();
		    	   break;
		       case "apoapsis":
		    	   tfVal = tfApoapsis.getText();
		    	   break;
		       case "semimajoraxis":
		    	   tfVal = tfSemimajorAxis.getText();
		    	   break;
		       case "eccentricity":
		    	   tfVal = tfEccentricity.getText();
		    	   break;
		       case "inclination":
		    	   tfVal = tfInclination.getText();
		    	   break;
		       case "radiusforvelocity":
		    	   tfVal = tfRadiusForVelocity.getText();
		    	   break;
		       case "velocityatradius":
		    	   tfVal = tfVelocityAtRadius.getText();
		    	   break;
		       case "velocityatapoapsis":
		    	   tfVal = tfVelocityAtApoapsis.getText();
		    	   break;
		       case "velocityatperiapsis":
		    	   tfVal = tfVelocityAtPeriapsis.getText();
		    	   break;
		       case "sme":
		    	   tfVal = tfSME.getText();
		    	   break;	
		       case "period":
		    	   tfVal = tfPeriod.getText();
		    	   break;
		       case "trueanomaly":
		    	   tfVal = tfTrueAnomaly.getText();
		    	   break;

		       }
		     
		    // check for sign 
		      // char sign = getSign(tfVal);
		    // check val is numeric
		       String currentTextFieldIsEmpty = "unassigned"; // can be "unassigned" "empty" or "populated" - for error checking methods
		       Color background = Color.WHITE;
		       if (isNumeric(tfVal) == true || tfVal.isEmpty() == true) {
		    	   background = defaultBackground;
		    	   // make label warning invisible
		    	   lblEllipticalInputWarning.setVisible(false);
		    	   currentTextFieldIsEmpty = "empty";
		       } else if (isNumeric(tfVal) == false) {
		    	   // make warning sign visible
		    	   lblEllipticalInputWarning.setVisible(true);
		    	   // make background of current tf red
		    	   background = warningBackground;
		    	   // need to know the field is populated for setTextFieldCombinations() below
		    	   currentTextFieldIsEmpty = "populated";
			   }
		       
		       switch(tfName) {
		       case "argofperi":
		    	   tfArgOfPeri.setBackground(background);
		    	   break;
		       case "periapsis":
		    	   tfPeriapsis.setBackground(background);
		    	   break;
		       case "apoapsis":
		    	   tfApoapsis.setBackground(background);
		    	   break;
		       case "semimajoraxis":
		    	   tfSemimajorAxis.setBackground(background);
		    	   break;
		       case "eccentricity":
		    	   tfEccentricity.setBackground(background);
		    	   break;
		       case "inclination":
		    	   tfInclination.setBackground(background);
		    	   break;
		       case "radiusforvelocity":
		    	   tfRadiusForVelocity.setBackground(background);
		    	   break;
		       case "velocityatradius":
		    	   tfVelocityAtRadius.setBackground(background);
		    	   break;
		       case "velocityatapoapsis":
		    	   tfVelocityAtApoapsis.setBackground(background);
		    	   break;
		       case "velocityatperiapsis":
		    	   tfVelocityAtPeriapsis.setBackground(background);
		    	   break;
		       case "sme":
		    	   tfSME.setBackground(background);
		    	   break;	
		       case "period":
		    	   tfPeriod.setBackground(background);
		    	   break;
		       case "trueanomaly":
		    	   tfTrueAnomaly.setBackground(background);
		    	   break;

		       }
		       // call relevant method to make sure the relevant textfields are changed so the user cant edit them
		      // setTextFieldCombinations(tfName, currentTextFieldIsEmpty);
		    }
		};
		
		tfArgOfPeri.getDocument().addDocumentListener(docListener);
		tfPeriapsis.getDocument().addDocumentListener(docListener);
		tfSemimajorAxis.getDocument().addDocumentListener(docListener);
		tfEccentricity.getDocument().addDocumentListener(docListener);
		tfInclination.getDocument().addDocumentListener(docListener);
		tfPeriod.getDocument().addDocumentListener(docListener);
		tfRadiusForVelocity.getDocument().addDocumentListener(docListener);
		tfVelocityAtRadius.getDocument().addDocumentListener(docListener);
		tfApoapsis.getDocument().addDocumentListener(docListener);
		tfTrueAnomaly.getDocument().addDocumentListener(docListener);
		tfSME.getDocument().addDocumentListener(docListener);
		tfVelocityAtApoapsis.getDocument().addDocumentListener(docListener);
		tfVelocityAtPeriapsis.getDocument().addDocumentListener(docListener);
		
		// kept this to remind me how to make a listener for individual tf's
		/*tfArgOfPeri.getDocument().addDocumentListener(new DocumentListener() {
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
		*/
	} // ========================================== END CONSTRUCTOR ======================================================
	//====================================================================================================================
	
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
			getScaleForRendering();
			mu = OrbitMainFrame.getOrbitingBodyData("mu");
			calculateEllipticalOrbit();
			newGraphicsListener.setNewGraphics(ArgOfPeri, Apoapsis, Periapsis, SemimajorAxis, Eccentricity, Inclination, 
					VelocityAtRadius, RadiusForVelocity, VelocityAtApoapsis, VelocityAtPeriapsis, SME, TrueAnomaly, Period, renderScale);
		}
	//-----------------------------------------------
	// FUNCTION METHODS	
		private void getOrbitRenderScale() {
				// TODO Auto-generated method stub
			}
	
		private void calculateEllipticalOrbit() {
			System.out.println("in calculateEllipticalOrbit()");	
			// get all the inputs and set them to private parameters 
				getUserInputs();
				// calculations (multiple methods)
				int loopCounter = 1;
				boolean finished = false;
				do {
					boolean calculationRan = false;
					//-------------------TEST ---------------------
					System.out.println("========= TEST FOR LOOP " + loopCounter + " ==========="); 
					System.out.println("Apoapsis = " + (Apoapsis == null) + " = " + Apoapsis);
					System.out.println("Periapsis = " + (Periapsis == null) + " = " + Periapsis);
					System.out.println("a = " + (SemimajorAxis == null) + " = " + SemimajorAxis);
					System.out.println("e = " + (Eccentricity == null) + " = " + Eccentricity);
					System.out.println("i = " + (Inclination == null) + " = " + Inclination);
					System.out.println("V at R = " + (VelocityAtRadius == null) + " = " + VelocityAtRadius);
					System.out.println("R for V = " + (RadiusForVelocity == null) + " = " + RadiusForVelocity);
					System.out.println("V at A = " + (VelocityAtApoapsis == null) + " = " + VelocityAtApoapsis);
					System.out.println("V at P = " + (VelocityAtPeriapsis == null) + " = " + VelocityAtPeriapsis);
					System.out.println("SME = " + (SME == null) + " = " + SME);
					System.out.println("T = " + (Period == null) + " = " + Period);
					System.out.println("=============================================");
					
					//------------------END TEST-------------------
					// if both rp and ra is defined/calculated
					if (Periapsis != null && Apoapsis != null) {
						// calculate a
						System.out.println("in peri true & apo true");
						if (SemimajorAxis == null ) {
							System.out.println("call ()");
							calcSimimajorAxisWithPeriapsisAndApoasis(Apoapsis, Periapsis);
							calculationRan = true;
						}
						// calculate e
						System.out.println("got to the ECCENTRICITY IF IN RP AND RA == TRUE");
						if (Eccentricity == null) {
							System.out.println("call calcEccentricityWithPeriAndApo()");
							calcEccentricityWithPeriapsisAndApoasis(Apoapsis, Periapsis);
							System.out.println("e = " + Eccentricity);
							calculationRan = true;
						}
					}
					// if a and rp are defined/calculated
					if (Apoapsis != null && SemimajorAxis != null) {
						// calc rp
						if (Periapsis == null) {
							System.out.println("call ()");
							calcPeriapsisWithApoapsisAndSemimajorAxis(SemimajorAxis, Apoapsis);
							calculationRan= true;
						}
					}
					// if a and ra are defined/calculated
					if (Periapsis != null && SemimajorAxis != null) {
						// calc ra
						if (Apoapsis == null) {
							System.out.println("call ()");
							calcApoapsisWithPeriapsisAndSemimajorAxis(SemimajorAxis, Periapsis);
							calculationRan = true;
						}
					}
					// if a is defined/calculated 
					if (SemimajorAxis != null) {
						// calc epsilon
						if (SME == null) {
							calcEpsilonWithSemimajorAxis(SemimajorAxis);
							calculationRan = true;
						}
						// calc period
						if (Period == null) {
							calcPeriodWithSemimajorAxis(SemimajorAxis);
							calculationRan = true;
						}
					}
					// if e and ra are defined/ calculated 
					// if e and rp are defined/ calculated
					// if e a and ra are defined/ calculated 
					// if e a and rp are defined/ calculated 
					// if SME (epsilon) is provided by the user
					if (SME != null) {
						// calculate a
						System.out.println("call calcSemimajorAxisWithEpsilon()");
						if (SemimajorAxis == null) {
							calcSemimajorAxisWithEpsilon(SME);
							calculationRan = true;
						}
					}
					// if period is provided by the user
					if (Period != null) {
						// calculate a
						if (SemimajorAxis == null) {
							calcSemimajorAxisWithPeriod(Period);
							calculationRan = true;
						}
					}
					// VELOCITY
					// standard vp and va
					if (Periapsis != null) {
						if (SME != null && VelocityAtPeriapsis == null) {
							VelocityAtPeriapsis = EllipticalOrbitVelocityAtRadius(Periapsis);
							calculationRan = true;
						}
					} else {
						if (SME != null && VelocityAtPeriapsis != null && Periapsis == null) {
							Periapsis = EllipticalOrbitRadiusAtVelocity(VelocityAtPeriapsis);
							calculationRan = true;
						}
					}
					if (Apoapsis != null && VelocityAtApoapsis == null) {
						if (SME != null) {
							VelocityAtApoapsis = EllipticalOrbitVelocityAtRadius(Apoapsis);
							calculationRan = true;
						}
					} else {
						if (SME != null && VelocityAtApoapsis != null && Apoapsis == null) {
							Apoapsis = EllipticalOrbitRadiusAtVelocity(VelocityAtApoapsis);
							calculationRan = true;
						}
					}
					// user specific v at r OR r at v and true anomaly
					if (chckbxNewVelocityAtRadius.isSelected() == true) {
						if (SME != null) {
							if (RadiusForVelocity != null && VelocityAtRadius == null) {
								VelocityAtRadius = EllipticalOrbitVelocityAtRadius(RadiusForVelocity);
								calculationRan = true;
							} 
							if (VelocityAtRadius != null && RadiusForVelocity == null) {
								RadiusForVelocity = EllipticalOrbitRadiusAtVelocity(VelocityAtRadius);
								calculationRan = true;
							}
						}
						if (TrueAnomaly != null && Eccentricity != null && SemimajorAxis != null && RadiusForVelocity == null) {
							RadiusForVelocity = EllipticalOrbitRadiusAtVelocity(TrueAnomaly, Eccentricity, SemimajorAxis);
							calculationRan = true;
						}
						// true anomaly
						if (RadiusForVelocity != null && SemimajorAxis != null && Eccentricity != null && TrueAnomaly == null) {
							TrueAnomaly = EllipticalOrbitTrueAnomaly(RadiusForVelocity, SemimajorAxis, Eccentricity);
							calculationRan = true;
						}
					}
					
					
					loopCounter++;
					System.out.println("loopCounter = " + loopCounter);
					// if there is nothing more that can be done then the loop is finished
					if (calculationRan == true) {
						// let it loop again 
						System.out.println("calculationRan = true");
					} else if (calculationRan == false){
						finished = true;
						System.out.println("calculationRan = false");
					}
					if (loopCounter == 100) {
						finished = true;
						System.out.println("STOPPED BY loopCounter");
					}
						
					
				}  while (finished == false); 
				
				// write calculated values to relevant text fields
				setCalculationsToRelevantTextFields();
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
			if (isNumeric(tfInclination.getText()) == true) {
				Inclination = Double.parseDouble(tfInclination.getText());
				InclinationAdded = true;
			}
			else {
				InclinationAdded = false;
			}
			if (isNumeric(tfVelocityAtRadius.getText()) == true) {
				VelocityAtRadius = Double.parseDouble(tfVelocityAtRadius.getText());
				VelocityAtRadiusAdded = true;
			}
			else {
				VelocityAtRadiusAdded = false;
			}
			if (isNumeric(tfRadiusForVelocity.getText()) == true) {
				RadiusForVelocity = Double.parseDouble(tfRadiusForVelocity.getText());
				RadiusForVelocityAdded = true;
			}
			else {
				RadiusForVelocityAdded = false;
			}
			if (isNumeric(tfVelocityAtApoapsis.getText()) == true) {
				VelocityAtApoapsis = Double.parseDouble(tfVelocityAtApoapsis.getText());
				VelocityAtApoapsisAdded = true;
			}
			else {
				VelocityAtApoapsisAdded = false;
			}
			if (isNumeric(tfVelocityAtPeriapsis.getText()) == true) {
				VelocityAtPeriapsis = Double.parseDouble(tfVelocityAtPeriapsis.getText());
				VelocityAtPeriapsisAdded = true;
			}
			else {
				VelocityAtPeriapsisAdded = false;
			}
			//System.out.println("OrbitalPeriod = " + OrbitalPeriod);
			
			if (isNumeric(tfTrueAnomaly.getText()) == true) {
				TrueAnomaly = Double.parseDouble(tfTrueAnomaly.getText());
				TrueAnomalyAdded = true;
			}
			else {
				TrueAnomalyAdded = false;
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
			
			
		}
		
		private void setCalculationsToRelevantTextFields() {
			
//				System.out.println(" IN TRY BLOCK");
//				System.out.println("rp = " + Periapsis);
//				System.out.println("ra = " + Apoapsis) ;
//				System.out.println("a = " + SemimajorAxis);
//				System.out.println("e = " + Eccentricity);
//				System.out.println("R for V = " + RadiusForVelocity);
//				System.out.println("V at R = " + VelocityAtRadius);
//				System.out.println("V at A = " + VelocityAtApoapsis);
//				System.out.println("V at P = " + VelocityAtPeriapsis);
//				System.out.println("SME = " + SME);
//				System.out.println("T = " + Period);
				
			//tfArgOfPeri.setText();
			//tfInclination.setText(Inclination.toString());
			//tfRAAN.setText(RAAN.toString());
			tfPeriapsis.setText(String.format("%.1f", Periapsis));
			tfApoapsis.setText(String.format("%.1f", Apoapsis));
			tfSemimajorAxis.setText(String.format("%.1f", SemimajorAxis));
			tfEccentricity.setText(String.format("%.5f", Eccentricity));
			
			// the following 2 text fields may not be in use. the if checks so as not to throw a null pointer exception
			if (chckbxNewVelocityAtRadius.isSelected() == true) {
				tfRadiusForVelocity.setText(String.format("%.1f", RadiusForVelocity));
				tfVelocityAtRadius.setText(String.format("%.5f", VelocityAtRadius));
				tfTrueAnomaly.setText(String.format("%.5f", TrueAnomaly));
			}
			
			tfVelocityAtApoapsis.setText(String.format("%.5f", VelocityAtApoapsis));
			tfVelocityAtPeriapsis.setText(String.format("%.5f", VelocityAtPeriapsis));
			tfSME.setText(String.format("%.5f", SME));
			tfPeriod.setText(String.format("%.5f", Period/60/60));
			
			
			
		}
		
		private void getScaleForRendering() {
			
			if(rdbtnIllustrative.isSelected() == true)
			{
				renderScale = "illustrative";
			}
			else if(rdbtnAccurate.isSelected() == true)
			{
				renderScale = "Accurate";
			}
			
		}
		
		//===================================================
				// MAIN MATHS METHODS
		
		private void calcSemimajorAxisWithPeriod(double T) {
			SemimajorAxis = Math.cbrt((Math.pow((T / (2*pi)), 2) * mu));
			System.out.println("a = " + SemimajorAxis + " with T = " + T);
			
		}

		private void calcSemimajorAxisWithEpsilon(double epsilon) {
			SemimajorAxis = - mu / (2 * epsilon);
			System.out.println("a = " + SemimajorAxis + " with epsilon = " + epsilon);
		}

		private void calcPeriodWithSemimajorAxis(double a) {
			Period = 2 * pi * Math.sqrt(a*a*a / mu);
			System.out.println("T = " + Period + " with a = " + a);
		}

		private void calcEpsilonWithSemimajorAxis(double a) {
			SME = - mu / (2 * a);
			System.out.println("SME = " + SME + " with a = " + a);
		}

		private void calcApoapsisWithPeriapsisAndSemimajorAxis(double a, double rp) {
			Apoapsis = (2 * a) - rp;
			System.out.println("ra = " + Apoapsis + " with rp = " + rp + " and a = " + a);
		}

		private void calcPeriapsisWithApoapsisAndSemimajorAxis(double a, double ra ) {
			Periapsis = (2 * a) - ra;
			System.out.println("rp = " + Periapsis + " with ra = " + ra + " and a = " + a);
		}

		private void calcEccentricityWithPeriapsisAndApoasis(double ra, double rp ) {
			Eccentricity = (ra - rp) / (ra + rp);
			System.out.println("e = " + Eccentricity + " with ra = " + ra + " and rp = " + rp);
		}

		private void calcSimimajorAxisWithPeriapsisAndApoasis(double ra, double rp) {
			SemimajorAxis = (ra + rp) / 2;
			System.out.println("a = " + SemimajorAxis + " with ra = " + ra + " and rp = " + rp);
		}

		private Double EllipticalOrbitVelocityAtRadius(Double r) {
			Double v = Math.sqrt(2 * ((mu / r) + SME));
			return v;
		}
		
		private Double EllipticalOrbitRadiusAtVelocity(Double v) {
			Double r = mu / (((v * v) / 2) - SME);
			return r;
		}
		private Double EllipticalOrbitTrueAnomaly(Double r,	Double a, Double e) {
			Double thetaInRadians = Math.acos((a * (1 - (e * e)) - r) / (r * e));
			Double thetaInDegrees = thetaInRadians * 180 / Math.PI;
			
			return thetaInDegrees;
		}
		private Double EllipticalOrbitRadiusAtVelocity(Double theta, Double e, Double a) {
			Double r = a * (1 - (e * e)) / (1 + e * Math.cos(theta * Math.PI / 180));
			
			return r;
		}

		//-------------------------------------------------
		// HELPER METHODS
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
		
		public static char getSign(String var) {
			
			char possSignVal = var.charAt(0);
			char sign = '0';
			if (possSignVal == '-') {
				sign = '-';
			}
			else /*possSignVal therefore = '+' || a number which means it is +ve */ {
				sign = '+';
			}
			
			return sign;
		}
		//-----------------------------------------------------------------------
		// ERROR CHECKING 
		public static void setTextFieldCombinations(String tfName, String currentTextFieldIsEmpty) {
			switch(tfName) {
			case "argofperi": // NOT REQUIRED
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "periapsis":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "apoapsis":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "semimajoraxis":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "eccentricity":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "inclination":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "velocity":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "sme":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "period":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			case "trueanomaly":
				if (currentTextFieldIsEmpty == "populated") /* make it so relevant tf's can't be edited */ {
					
				} else if (currentTextFieldIsEmpty == "empty") /* make all relevant fields editable */ {
					
				}
				break;
			}
		}
	
}
