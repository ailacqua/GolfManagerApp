/*This frame is the warning frame to prompt the user when they have made an error
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Warning extends JFrame implements ActionListener
{
  //declaring image
  public final URL WARNING_PATH = getClass().getResource("warning.png");
  private final ImageIcon WARNING_IMAGE = new ImageIcon(new ImageIcon
                    (WARNING_PATH).getImage().getScaledInstance
                    (300,300,Image.SCALE_DEFAULT));
  //declaring buttons, label, and panel for frame
  private JLabel warningLabel;
  private JLabel imageLabel;
  private JButton closeButton;
  private JButton exitButton;
  private JButton yesButton;
  private JButton yesIncreaseButton;
  private JButton noButton;
  private JPanel buttonPanel;
  
  public Warning(String message)
  {
    //initializing the frame
    super("Warning");
    this.setBounds(150, 150, 700, 500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.POP_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing JLabels
    this.imageLabel = new JLabel(WARNING_IMAGE);
    this.warningLabel = new JLabel(message, SwingConstants.CENTER);
    warningLabel.setFont(Help.HELP_FONT);
    //constructing buttonPanel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    
    //if message is newWeek yes no button added and constructed and warningLabel changed
    if (message.equals("newWeek"))
    {
      warningLabel.setText("Are you sure you want to reset?");
      this.yesButton = new JButton("Yes");
      yesButton.addActionListener(this);
      this.noButton = new JButton("No");
      noButton.addActionListener(this);
      buttonPanel.add(yesButton);
      buttonPanel.add(noButton);
    }
    else
    {
      //constructing close and exit button
      this.closeButton = new JButton("Close Frame");
      closeButton.addActionListener(this);
      this.exitButton = new JButton("Exit Program");
      exitButton.addActionListener(this);
      //adding close and exit to buttonPanel
      buttonPanel.add(closeButton);
      buttonPanel.add(exitButton);
    }
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(warningLabel, BorderLayout.NORTH);
    this.add(imageLabel, BorderLayout.CENTER);
    
    //setting frame to be visible
    this.setVisible(true);
  }
  //main method to test the class
  public static void main(String[] args)
  {
    new Warning("newWeek");
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of event
    Object command = e.getSource();
    //exits program if exit pressed
    if (command == exitButton)
    {
      System.exit(0);
    }
    //closes frame if close pressed
    else if (command == closeButton)
    {
      this.dispose();
    }
    //closes frame if no pressed
    else if (command == noButton)
    {
      this.dispose();
    }
    //clears and tables and closes frame if yes pressed
    else if (command == yesButton)
    {
      //try allows to catch exceptions
      try
      {
        //declaring components to access database
        String dbName = "Golf";
        String dbQuery = "";
        String[] tableNames = {"WeekRoster","WeekResult","Monday","Tuesday",
          "Wednesday","Thursday","Friday","Saturday","Sunday","TeeTime"};
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();;
        
        //deletes contents of tables 
        for (int i=0; i<tableNames.length; i++)
        {
          dbQuery = "DELETE FROM " + tableNames[i];
          s.execute(dbQuery);
        }
        //sets all values in daily total to 0
        for (int i=0; i<days.length; i++)
        {
          dbQuery = "UPDATE DailyTotal SET totalPlayers = 0, playersAdded = 0, requiredTeeTimes = 0, addedTeeTimes = 0 WHERE day = '" + days[i] + "'";
          s.execute(dbQuery);
        }
        //disposes warning frame
        this.dispose();
      }
      //pops warning if error interacting with database
      catch (SQLException se)
      {
        new Warning("Error creating new week!");
      }
    }
    this.validate();
    this.repaint();
  }
}