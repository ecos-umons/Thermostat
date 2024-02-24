 package mythermostat;

import java.awt.EventQueue;
import mythermostat.thermostat.ThermostatStatemachine.State;
import mythermostat.thermostat.IThermostatStatemachine.SCInterfaceOperationCallback;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Timer;

import mythermostat.thermostat.ThermostatStatemachine;

public class MainWindow  {

	private JFrame frmThermostat;

	private String font = "Tahoma";

	private static long outTemp = -5; // This represents the outside temperature
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create ThermostatStatemachine object 
					ThermostatStatemachine thermostat= new ThermostatStatemachine();
					
					// Create a callback for the operation outTemp used by the statemachine but defined in Java
					SCInterfaceOperationCallback callback = new SCInterfaceOperationCallback() {
						@Override
						public long outTemp() { return outTemp; }
					};
					thermostat.getSCInterface().setSCInterfaceOperationCallback(callback);
					
					//Create Swing GUI window controlled by thermostat statemachine 
					MainWindow window = new MainWindow(thermostat);
					window.frmThermostat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	private static final int ONE_MSSECOND = 500;
	private static final String GRAD_CEL="\u00B0C";
	
	//view components
	private JLabel lblinT 			= new JLabel();
	private JLabel lblNightT 		= new JLabel();
	private JLabel lblDayT 			= new JLabel();
	private JLabel lblCycle 		= new JLabel("");
	private JLabel lblStatePointer 	= new JLabel(""); // State pointer
	private JLabel lblOn 			= new JLabel("ON");
	private JLabel lblOff 			= new JLabel("OFF");
	private JLabel lblPause 		= new JLabel("PAUSE");
	private JLabel lblWinPointer 	= new JLabel(""); // window pointer
	private JLabel lblWinOpen 		= new JLabel("");
	private JLabel lblWinClosed 	= new JLabel("");
	private JLabel lblMin = new JLabel();
	private JLabel lblMax = new JLabel();
    private JLabel lblTime          = new JLabel("23:59:59");

    private JButton btnMode;
    private JButton btnSet;
	
	// containerPannel contain all thermostat components
	JPanel containerPanel = new JPanel();
	
	private JButton bntWindowsActions = new JButton("Close");
	
	//Thermostat Statechart
	private ThermostatStatemachine  stm;
	

	public MainWindow(ThermostatStatemachine  thermostat) {
		stm=thermostat;
		stm.setTimer(new TimerService());
		stm.init();
		stm.enter();
		initialize();		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		lblOn.setForeground(Color.LIGHT_GRAY);
		lblOn.setFont(new Font(font, Font.BOLD, 11));
		lblOn.setBounds(222, 11, 30, 14);
		lblOff.setForeground(Color.LIGHT_GRAY);
		lblOff.setFont(new Font(font, Font.BOLD, 11));
		lblOff.setBounds(175, 11, 37, 14);


		Timer timer = new Timer(ONE_MSSECOND, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	
			stm.runCycle();		
			RefreshGUI();
		}

		    // function called every 500 ms, permitting the refresh of the GUI.
			private void RefreshGUI() {
				lblinT.setText(stm.getInT() + GRAD_CEL);
				lblDayT.setText(stm.getDayT() + GRAD_CEL);
				lblNightT.setText(stm.getNightT() + GRAD_CEL);
				lblOn.setForeground(Color.LIGHT_GRAY);
				lblOff.setForeground(Color.LIGHT_GRAY);
				lblPause.setForeground(Color.LIGHT_GRAY);
				
				
				//check thermostat active state and display the correct info.
				if (stm.isStateActive(State.controller_Status)) { // if Status is the active composite state
					if (stm.isStateActive(State.controller_Status_r1_On_r1_Day)) {
						lblOn.setForeground(Color.BLACK);
						lblStatePointer.setBounds(lblOn.getX(), 28, 21, 14);
						lblCycle.setIcon(new ImageIcon(MainWindow.class.getResource("/media/day.png")));
					    }
					else if (stm.isStateActive(State.controller_Status_r1_On_r1_Night)) {
						lblOn.setForeground(Color.BLACK);
						lblStatePointer.setBounds(lblOn.getX(), 28, 21, 14);
						lblCycle.setIcon(new ImageIcon(MainWindow.class.getResource("/media/night.png")));
						}
					else if (stm.isStateActive(State.controller_Status_r1_Off)) {
						lblCycle.setIcon(null);
						lblStatePointer.setBounds(lblOff.getX(), 28, 21, 14);
						lblOff.setForeground(Color.BLACK);
						}
					else if (stm.isStateActive(State.controller_Status_r1_Pause)) {
						lblCycle.setIcon(null);
						lblStatePointer.setBounds(lblPause.getX() +6 , 28, 21, 14);
						lblPause.setForeground(Color.BLACK);				
						}
					}
				else { // if Status is not the active state of the statemachine (i.e. it is in the Settings)
					lblCycle.setIcon(null);
				}
                if(stm.isStateActive(State.controller_Settings)) {
                    btnMode.setText("Reset");
                    btnSet.setText("Next");
                } else {
                    btnMode.setText("Mode");
                    btnSet.setText("Set");
                }

				//clear listeners assigned to close & open window button.
				bntWindowsActions.removeActionListener(openWindow);
				bntWindowsActions.removeActionListener(closeWindow);
				
				//check window active state and display the correct infos
				//getWindowState added manually to the code generated by the thermostat.
				if (stm.getIsOpen())
					{ //if window is open:
					  lblWinPointer.setBounds(lblWinOpen.getX()+12, 65, 20, 14);
					  bntWindowsActions.setText("Close");
					  bntWindowsActions.addActionListener(CloseWindow());				
					}
				else
				   { //if window is closed:
					 lblWinPointer.setBounds(lblWinClosed.getX() + 12, 65, 20, 14);
					 bntWindowsActions.setText("Open");
					 bntWindowsActions.addActionListener(OpenWindow());
				   }
                long time = stm.getTime();
                long hours = time / 3600;
                time = time % 3600;
                long mins = time / 60;
                long secs = time % 60;
                lblTime.setText(String.format("%02d :%02d :%02d",hours,mins,secs));
			}    
		});
		// start the timer, it runs every 500ms
		timer.start();
		
		// frmThermostat properties
		frmThermostat = new JFrame();
		frmThermostat.setTitle("Thermostat GUI");
		frmThermostat.getContentPane().setBackground(Color.WHITE);
		frmThermostat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThermostat.setBounds(100, 100, 450, 225);
		frmThermostat.getContentPane().setLayout(null);

		//container panel properties
		containerPanel.setBackground(Color.WHITE);
		containerPanel.setBounds(10, 10, 414, 131);
		containerPanel.setLayout(null);
		containerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		frmThermostat.getContentPane().add(containerPanel);

		//Window pointer properties
		lblWinPointer.setSize(18, 20);
		lblWinPointer.setLocation(171, 60);
		lblWinPointer.setIcon(new ImageIcon(MainWindow.class.getResource("/media/botNar.png")));

		// temperature label properties
		lblinT.setBounds(10, 0, 155, 65);
		lblinT.setFont(new Font(font, Font.BOLD, 30));
		lblinT.setIcon(new ImageIcon(MainWindow.class.getResource("/media/home.png")));
		lblPause.setForeground(Color.LIGHT_GRAY);
		lblPause.setFont(new Font(font, Font.BOLD, 11));

		//lblPause properties
		lblPause.setBounds(262, 11, 41, 14);
        lblTime.setBounds(330, 11, 80, 14);

		//Day temperature label
		lblDayT.setFont(new Font(font, Font.BOLD, 16));
		lblDayT.setIcon(new ImageIcon(MainWindow.class.getResource("/media/day.png")));
		lblDayT.setBounds(10, 70, 84, 26);

		//Night temperature label
		lblNightT.setFont(new Font(font, Font.BOLD, 16));
		lblNightT.setIcon(new ImageIcon(MainWindow.class.getResource("/media/night.png")));
		lblNightT.setBounds(10, 97, 84, 26);

		//window icons
		lblWinOpen.setIcon(new ImageIcon(MainWindow.class.getResource("/media/openWin.png")));
		lblWinOpen.setBounds(203, 80, 41, 42);
		
		lblWinClosed.setIcon(new ImageIcon(MainWindow.class.getResource("/media/closeWin.png")));
		lblWinClosed.setBounds(160, 80, 41, 42);

		//min & max data
		lblMin.setText("Min  :" + stm.getMinT() + GRAD_CEL);
		lblMin.setFont(new Font(font, Font.BOLD, 12));
		lblMin.setBounds(325, 88, 79, 18);

		lblMax.setText("Max :" + stm.getMaxT()+ GRAD_CEL);
		lblMax.setFont(new Font(font, Font.BOLD, 12));
		lblMax.setBounds(325, 105, 79, 18);

		// lblCycle displays night or day
		lblCycle.setBounds(339, 27, 30, 26);

		lblStatePointer.setIcon(new ImageIcon(MainWindow.class.getResource("/media/topNar.png")));
		
		//display the outside temperature (currently represented as a static variable elsewhere in this main class)
		JLabel lblOuT = new JLabel("OutT: " + outTemp + GRAD_CEL);
		lblOuT.setFont(new Font(font, Font.BOLD, 12));
		lblOuT.setBounds(246, 105, 79, 18);
		containerPanel.add(lblOuT);
		
		btnMode = new JButton("Mode");
		btnMode.addActionListener(e -> {
			stm.raiseMode();
			stm.runCycle();
		});
		
		
		//Button Mode
		btnMode.setBounds(10, 152, 89, 23);
		frmThermostat.getContentPane().add(btnMode);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(e -> {
			stm.raiseSet();
			stm.runCycle();

		});
		
		//Button Set
		btnSet.setBounds(102, 152, 89, 23);
		frmThermostat.getContentPane().add(btnSet);
		
		
		bntWindowsActions.setBounds(305, 152, 89, 23);
		frmThermostat.getContentPane().add(bntWindowsActions);
		
		// add all thermostat components to container
		addComponentsToContainer();
		
	}

	private void addComponentsToContainer() {
		containerPanel.add(lblPause);
        containerPanel.add(lblTime);
		containerPanel.add(lblWinPointer);	
		containerPanel.add(lblOn);
		containerPanel.add(lblOff);
		containerPanel.add(lblinT);
		containerPanel.add(lblDayT);
		containerPanel.add(lblNightT);
		containerPanel.add(lblWinOpen);
		containerPanel.add(lblWinClosed);
		containerPanel.add(lblMax);
		containerPanel.add(lblMin);
		containerPanel.add(lblCycle);
		containerPanel.add(lblStatePointer);
	}

	
	private ActionListener closeWindow;
	private ActionListener CloseWindow() {
		 closeWindow =(closeWindow!=null) ? closeWindow: e -> {
			 stm.raiseCloseWindow();
			 stm.runCycle();
		 };
		return closeWindow;
	}
	 
	private ActionListener openWindow;
	private ActionListener OpenWindow() {
		 openWindow =(openWindow!=null) ? openWindow: e -> {
			 stm.raiseOpenWindow();
			 stm.runCycle();
		 };
		return openWindow;
	}

}
