package com.engineGui.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Handles the main gui of the engine.
 * Creates a user-friendly way to create
 * new games using the engine.
 * 
 * @author Ryan Hochmuth
 *
 */
public class MainGui implements ActionListener
{	
	// Constructor data
	private int windowX;
	private int windowY;
	private String title;
	
	// Window sizing
	private int centerX;
	private int centerY;
	
	// Main gui components
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JLabel titleLbl = new JLabel();
	private JButton newBtn = new JButton();
	
	// New game gui components
	private JPanel ngPanel = new JPanel();
	private JLabel ngTitle = new JLabel();
	private JLabel gameTitleLbl = new JLabel();
	private JTextField gameTitleTxt = new JTextField();
	private JLabel windowDimLbl = new JLabel();
	private JTextField windowXTxt = new JTextField();
	private JTextField windowYTxt = new JTextField();
	private JLabel fpsTpsLbl = new JLabel();
	private JTextField fpsTxt = new JTextField();
	private JTextField tpsTxt = new JTextField();
	private JButton ngOkBtn = new JButton();
	
	/**
	 * Create a new MainGui.
	 * @param windowX
	 * @param windowY
	 * @param title
	 */
	public MainGui(int windowX, int windowY, String title)
	{
		this.windowX = windowX;
		this.windowY = windowY;
		this.title = title;
		
		centerX = windowX / 2;
		centerY = windowY / 2;
		
		// Frame
		frame = new JFrame();
		frame.setSize(windowX, windowY);
		frame.setTitle(title);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		buildNewGameGui();
	}
	
	/**
	 * Build the gui.
	 */
	public void buildGui()
	{
		// Panel
		panel.setLayout(null);
		panel.setVisible(true);
		
		// Title label
		titleLbl.setText(title);
		titleLbl.setFont(new Font("Verdana", Font.PLAIN, 24));
		titleLbl.setVisible(true);
		
		// New button
		newBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		newBtn.setText("New Game");
		newBtn.addActionListener(this);
		newBtn.setVisible(true);
	}
	
	/**
	 * Show the gui.
	 */
	public void showGui()
	{
		changeWindowSize(300, 200);
		
		// Panel
		panel.setBounds(0, 0, windowX, windowY);
		frame.add(panel);
		
		// Title label
		titleLbl.setBounds(centerX - 80, 10, 200, 50);
		panel.add(titleLbl);
		
		// New game button
		newBtn.setBounds(centerX - 60, 100, 120, 30);
		panel.add(newBtn);
		
		frame.setLocationRelativeTo(null);
		frame.repaint();
	}
	
	/**
	 * Hide the gui.
	 */
	public void hideGui()
	{
		frame.remove(panel);
		frame.remove(titleLbl);
		frame.remove(newBtn);
		frame.repaint();
	}
	
	/**
	 * Build the new game gui.
	 */
	private void buildNewGameGui()
	{
		// Panel
		ngPanel.setLayout(null);
		ngPanel.setVisible(true);
		
		// Title label
		ngTitle.setText("Create a new game");
		ngTitle.setFont(new Font("Verdana", Font.PLAIN, 24));
		ngTitle.setVisible(true);
		
		// Game title label
		gameTitleLbl.setText("Game Title:");
		gameTitleLbl.setFont(new Font("Verdana", Font.PLAIN, 14));
		gameTitleLbl.setVisible(true);
		
		// Window dimension label
		windowDimLbl.setText("Window Size:");
		windowDimLbl.setFont(new Font("Verdana", Font.PLAIN, 14));
		windowDimLbl.setVisible(true);
		
		// Fps/Tps label
		fpsTpsLbl.setText("FPS/TPS:");
		fpsTpsLbl.setFont(new Font("Verdana", Font.PLAIN, 14));
		fpsTpsLbl.setVisible(true);
		
		// Ok button
		ngOkBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		ngOkBtn.setText("Ok");
		ngOkBtn.addActionListener(this);
		ngOkBtn.setVisible(true);
	}
	
	/**
	 * Show the new game gui.
	 */
	private void showNewGameGui()
	{
		changeWindowSize(400, 300);
		
		// New game panel
		ngPanel.setBounds(0, 0, windowX, windowY);
		frame.add(ngPanel);
		
		// New game title label
		ngTitle.setBounds(centerX - 125, 10, 250, 50);
		ngPanel.add(ngTitle);
		
		// Game title
		gameTitleLbl.setBounds(10, 80, 100, 30);
		ngPanel.add(gameTitleLbl);
		gameTitleTxt.setText("");
		gameTitleTxt.setBounds(120, 80, 155, 30);
		ngPanel.add(gameTitleTxt);
		
		// Window size
		windowDimLbl.setBounds(10, 120, 100, 30);
		ngPanel.add(windowDimLbl);
		windowXTxt.setText("");
		windowXTxt.setBounds(120, 120, 75, 30);
		ngPanel.add(windowXTxt);
		windowYTxt.setText("");
		windowYTxt.setBounds(200, 120, 75, 30);
		ngPanel.add(windowYTxt);
		
		// FPS/TPS
		fpsTpsLbl.setBounds(10, 160, 100, 30);
		ngPanel.add(fpsTpsLbl);
		fpsTxt.setText("");
		fpsTxt.setBounds(120, 160, 75, 30);
		ngPanel.add(fpsTxt);
		tpsTxt.setText("");
		tpsTxt.setBounds(200, 160, 75, 30);
		ngPanel.add(tpsTxt);
		
		// Ok button
		ngOkBtn.setBounds(centerX - 50, windowY - 80, 100, 30);
		ngPanel.add(ngOkBtn);
		
		frame.setLocationRelativeTo(null);
		frame.repaint();
	}
	
	/**
	 * Hide the new game gui.
	 */
	private void hideNewGameGui()
	{
		frame.remove(ngPanel);
		frame.remove(ngTitle);
		frame.remove(gameTitleLbl);
		frame.remove(gameTitleTxt);
		frame.remove(windowDimLbl);
		frame.remove(windowXTxt);
		frame.remove(windowYTxt);
		frame.remove(fpsTpsLbl);
		frame.remove(fpsTxt);
		frame.remove(tpsTxt);
		frame.remove(ngOkBtn);
		frame.repaint();
	}
	
	/**
	 * Change the size of the window.
	 */
	private void changeWindowSize(int windowX, int windowY)
	{
		this.windowX = windowX;
		this.windowY = windowY;
		
		this.centerX = windowX / 2;
		this.centerY = windowY / 2;
		
		frame.setSize(windowX, windowY);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == newBtn)
		{
			hideGui();
			showNewGameGui();
		}
		
		if (e.getSource() == ngOkBtn)
		{
			String path;
			
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose the game's base directory");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    
		    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
		    {
		    	path = chooser.getSelectedFile().getAbsolutePath();
		    }
		    else
		    {
		    	return;
		    }
		    
		    hideNewGameGui();
		    
		    new NewGame(path, gameTitleTxt.getText(), windowXTxt.getText(), windowYTxt.getText(),
		    		fpsTxt.getText(), tpsTxt.getText());
		}
	}
}