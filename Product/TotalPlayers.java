/*This class allows the user to enter the total number of updatedPlayers for each day
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class TotalPlayers extends JFrame implements ActionListener
{
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JLabel dayLabel;
  private JLabel playersLabel;
  private JLabel instructionLabel;
  private JButton updateButton;
  private JButton doneButton;
  private JTextField playersField;
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  private JPanel dayPanel;
  private JPanel buttonPanel;
  private JPanel northPanel;
  private JPanel inputPanel;
  
  public TotalPlayers()
  {
    //initializing the frame
    super("Number of Players");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.SCHEDULE_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing menu components and adding them
    this.mainBar = new JMenuBar();
    this.mainMenu = new JMenu("Menu");
    this.helpItem = new JMenuItem("Help");
    this.exitItem = new JMenuItem("Exit");
    this.homeItem = new JMenuItem("Home");
    mainMenu.add(helpItem);
    helpItem.addActionListener(this);
    mainMenu.add(exitItem);
    exitItem.addActionListener(this);
    mainMenu.add(homeItem);
    homeItem.addActionListener(this);
    mainBar.add(mainMenu);
    
    //constructing JLabels and textfields and setting font
    this.dayLabel = new JLabel("Day:");
    this.playersLabel = new JLabel("Number of Players:");
    this.playersField = new JTextField(10);
    this.instructionLabel = new JLabel("<html><center>Select a day and enter "
        + "the total number of players. Then, click update before moving to "
        + "another day. <br>CAUTION: Changing the total players deletes all "
        + "currently added players for that day (If decreasing, tee times "
        + "deleted too).</center></html>", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    
    //constructing JButtons and button group and adding action listener
    this.monButton = new JRadioButton("Monday");
    monButton.addActionListener(this);
    monButton.setSelected(true);
    this.tueButton = new JRadioButton("Tuesday");
    tueButton.addActionListener(this);
    this.wedButton = new JRadioButton("Wednesday");
    wedButton.addActionListener(this);
    this.thuButton = new JRadioButton("Thursday");
    thuButton.addActionListener(this);
    this.friButton = new JRadioButton("Friday");
    friButton.addActionListener(this);
    this.satButton = new JRadioButton("Saturday");
    satButton.addActionListener(this);
    this.sunButton = new JRadioButton("Sunday");
    sunButton.addActionListener(this);
    this.dayGroup = new ButtonGroup();
    dayGroup.add(monButton);
    dayGroup.add(tueButton);
    dayGroup.add(wedButton);
    dayGroup.add(thuButton);
    dayGroup.add(friButton);
    dayGroup.add(satButton);
    dayGroup.add(sunButton);
    this.updateButton = new JButton("Update");
    updateButton.addActionListener(this);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    //constructing button and day panels and adding buttons
    this.dayPanel = new JPanel(new FlowLayout());
    dayPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    dayPanel.add(dayLabel);
    dayPanel.add(monButton);
    dayPanel.add(tueButton);
    dayPanel.add(wedButton);
    dayPanel.add(thuButton);
    dayPanel.add(friButton);
    dayPanel.add(satButton);
    dayPanel.add(sunButton);
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(updateButton);
    buttonPanel.add(doneButton);
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    inputPanel.add(playersLabel);
    inputPanel.add(playersField);
    this.northPanel = new JPanel(new GridLayout(2,1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(dayPanel);
    northPanel.add(inputPanel);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(instructionLabel,BorderLayout.CENTER);
    
    //setting JMenuBar and frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test the frame
  public static void main(String[] args)
  {
    new TotalPlayers();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets the source of command
    Object command = e.getSource();
    //launches help if help pressed
    instructionLabel.setText("<html><center>Select a day and enter the total "
        + "number of players. Then, click update before moving to another day. "
        + "<br>CAUTION: Changing the total players deletes all currently added "
        + "players for that day (If decreasing, tee times deleted too).</center></html>");
    if (command == helpItem)
    {
      new Help("<html><center>This frame is used to set the total number of "
          + "players for each day.<br>You must set this before adding times or "
          + "players.</html></center>");
    }
    //exits if exit pressed
    else if(command == exitItem)
    {
      System.exit(0);
    }
    //launches welcome if home pressed
    else if(command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //launches manage schedule if done pressed
    else if(command == doneButton)
    {
      new ManageSchedule();
      this.dispose();
    }
    //updates total updatedPlayers if update pressed
    else if(command == updateButton)
    {
      //declaring variables to store input
      String day = "";
      //if monday selected values added to monday
      if (monButton.isSelected())
      {
        day = "Monday";
      }
      // if tuesday selected values added to tuesday
      else if (tueButton.isSelected())
      {
        day = "Tuesday";
      }
      //if wednesday selected values added to wednesday
      else if (wedButton.isSelected())
      {
        day = "Wednesday";
      }
      //if thursday selected values added to thursday
      else if (thuButton.isSelected())
      {
        day = "Thursday";
      }
      //if friday selected values added to friday
      else if (friButton.isSelected())
      {
        day = "Friday";
      }
      //if saturday selected values added to saturday
      else if (satButton.isSelected())
      {
        day = "Saturday";
      }
      //if sunday selected values added to sunday
      else if (sunButton.isSelected())
      {
        day = "Sunday";
      }
      
      try
      {
        //declaring variables to read input and establishing db connection
        String dbQuery = "";
        String dbName = "Golf";
        int totalPlayers;
        int requiredTeeTimes;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        //getting the updatedPlayers from the field
        int updatedPlayers = Integer.parseInt(playersField.getText());
        //ensures the number of players is not less than 2 or equal to 5
        if ((updatedPlayers == 1) || (updatedPlayers == 2) || (updatedPlayers == 5))
        {
          throw new NumberFormatException();
        }
        //calculates the number of required times
        TeeTime timeObj = new TeeTime(updatedPlayers);
        requiredTeeTimes = timeObj.getNeededTimes();
        //getting the players added and totalPlayers from the table
        dbQuery = "SELECT totalPlayers FROM DailyTotal WHERE day = '" + day + "'";
        rs = s.executeQuery(dbQuery);
        rs.next();
        totalPlayers = rs.getInt("totalPlayers");
        //updates the totalPlayers and requiredTeeTimes
        dbQuery = "UPDATE DailyTotal SET totalPlayers = ?, requiredTeeTimes = ?, "
            + "playersAdded = ? WHERE day = ?";
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        ps.setInt(1, updatedPlayers);
        ps.setInt(2, requiredTeeTimes);
        ps.setInt(3, 0);
        ps.setString(4, day);
        ps.executeUpdate();
        //deletes currently addedPlayers on weekroster and day roster
        dbQuery = "DELETE FROM WeekRoster WHERE day = ?";
        ps = myDbConn.prepareStatement(dbQuery);
        ps.setString(1, day);
        ps.executeUpdate();
        dbQuery = "DELETE FROM " + day;
        s.execute(dbQuery);
        //deletes players where day is where total players updated
        if (updatedPlayers < totalPlayers)
        {
          dbQuery = "DELETE FROM TeeTime WHERE day = ?";
          ps = myDbConn.prepareStatement(dbQuery);
          ps.setString(1, day);
          ps.executeUpdate();
          dbQuery = "UPDATE DailyTotal SET addedTeeTimes = 0 WHERE day = '" + day + "'";
          s.execute(dbQuery);
        }
        playersField.setText("");
        instructionLabel.setText("Success!");
      }
      //catches SQLExceptions to ensure update runs smoothly
      catch (SQLException se)
      {
        new Warning("Error adding number of players!");
      }
      //makes sure the user entered an integer number of players
      catch (NumberFormatException nfe)
      {
        new Warning("The number of players can not be less than 2 or equal to 5!");
      }
    }
    this.validate();
    this.repaint();
  }
}