/*This frame provides the user with help if they need it
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Help extends JFrame implements ActionListener
{
  //declaring font
  public static final Font HELP_FONT = new Font("Trebuchet MS", Font.PLAIN, 14);
  //declaring image
  public final URL HELP_PATH = getClass().getResource("help.png");
  private final ImageIcon HELP_IMAGE = new ImageIcon(new ImageIcon
                    (HELP_PATH).getImage().getScaledInstance
                    (335,150,Image.SCALE_DEFAULT));
  //declaring buttons, panel, and labels for frame
  private JButton closeButton;
  private JButton exitButton;
  private JPanel buttonPanel;
  private JLabel helpLabel;
  private JLabel imageLabel;
  
  public Help(String message)
  {
    //initializing the frame
    super("Help");
    this.setBounds(150, 150, 700, 500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.POP_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing JLabels and setting font
    this.helpLabel = new JLabel(message, SwingConstants.CENTER);
    helpLabel.setFont(HELP_FONT);
    this.imageLabel = new JLabel(HELP_IMAGE);
    
    //constructing JButtons and adding ActionListener
    this.closeButton = new JButton("Close Frame");
    closeButton.addActionListener(this);
    this.exitButton = new JButton("Exit Program");
    exitButton.addActionListener(this);
    
    //constructing buttonPanel and adding buttons
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(exitButton);
    buttonPanel.add(closeButton);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(imageLabel, BorderLayout.CENTER);
    this.add(helpLabel, BorderLayout.NORTH);
    
    //setting frame visible
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new Help("Help!");
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of command
    Object command = e.getSource();
    //closes frame if close pressed
    if (command == closeButton)
    {
      this.dispose();
    }
    //exits program if exit pressed
    else if (command == exitButton)
    {
      System.exit(0);
    }
    this.validate();
    this.repaint();
  }
}