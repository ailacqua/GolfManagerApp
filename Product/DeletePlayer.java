/*This frame provides the user with the options to delete a player
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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DeletePlayer extends JFrame implements ActionListener
{
  private JButton deleteButton;
  private JButton backButton;
  private JCheckBox monBox;
  private JCheckBox tueBox;
  private JCheckBox wedBox;
  private JCheckBox thuBox;
  private JCheckBox friBox;
  private JCheckBox satBox;
  private JCheckBox sunBox;
  private JTextField ghinField;
  private JLabel instructionLabel;
  private JLabel ghinLabel;
  private JPanel buttonPanel;
  private JPanel northPanel;
  private JPanel dayPanel;
  private JPanel inputPanel;
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  
  public DeletePlayer()
  {
    //initializing the frame
    super("Delete Player");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.ROSTER_COLOR);
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
    
    //constructing checkboxes and adding action listener
    this.monBox = new JCheckBox("Monday");
    monBox.addActionListener(this);
    this.tueBox = new JCheckBox("Tuesday");
    tueBox.addActionListener(this);
    this.wedBox = new JCheckBox("Wednesday");
    wedBox.addActionListener(this);
    this.thuBox = new JCheckBox("Thursday");
    thuBox.addActionListener(this);
    this.friBox = new JCheckBox("Friday");
    friBox.addActionListener(this);
    this.satBox = new JCheckBox("Saturday");
    satBox.addActionListener(this);
    this.sunBox = new JCheckBox("Sunday");
    sunBox.addActionListener(this);
    
    //constructing dayPanel and adding checkboxes
    this.dayPanel = new JPanel(new FlowLayout());
    dayPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    dayPanel.add(monBox);
    dayPanel.add(tueBox);
    dayPanel.add(wedBox);
    dayPanel.add(thuBox);
    dayPanel.add(friBox);
    dayPanel.add(satBox);
    dayPanel.add(sunBox);

    //constructing buttons jpanel and adding to buttonpanel
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);
    this.deleteButton = new JButton("Delete Player");
    deleteButton.addActionListener(this);
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(deleteButton);
    buttonPanel.add(backButton);
    
    //constructing textfields and labels
    this.ghinLabel = new JLabel("GHIN:");
    this.instructionLabel = new JLabel("Enter the player's GHIN, select the days "
        + "to delete from, then click Delete Player.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    this.ghinField = new JTextField(10);
    
    //constructing inputPanel and adding ghin label and field
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    inputPanel.add(ghinLabel);
    inputPanel.add(ghinField);
    
    //constructing and adding components to northPanel
    this.northPanel = new JPanel(new GridLayout(2,1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(inputPanel);
    northPanel.add(dayPanel);
    
    //adding components to frame
    this.add(northPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(instructionLabel, BorderLayout.CENTER);
    
    //setting jmenu bar and frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new DeletePlayer();
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of action
    Object command = e.getSource();
    instructionLabel.setText("Enter the player's GHIN, select the days to delete"
        + " from, then click Delete Player.");
    //opens help frame if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>This frame is used to delete a player from the roster. "
          + "To do so, select enter their GHIN<br>and select the days from which "
          + "to delete them. Then, click Delete Player to finish.</center></html>.");
    }
    //exits program if exit pressed
    else if(command == exitItem)
    {
      System.exit(0);
    }
    //if home pressed opens Welcome
    else if(command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //opens manage rosters if back pressed
    else if(command == backButton)
    {
      new ManageRosters();
      this.dispose();
    }
    //deletes player if delete pressed
    else if (command == deleteButton)
    {
      //try ensures we can catch exceptions
      try
      {
        //declaring dbquery, reading ghin, and declaring dbname
        int ghin = Integer.parseInt(ghinField.getText());
        int playersAdded = 0;
        ArrayList<String> dayArray = new ArrayList<>();
        String dbQuery = "";
        String dbName = "Golf";
        //establishing db conn
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        //checks to see if the user entered an invalid ghin
        if ((ghin < 1000000) || (ghin > 9999999))
        {
          throw new NumberFormatException();
        }
        //compiles days from which to delete player
        if(monBox.isSelected())
        {
          dayArray.add("Monday");
        }
        if(tueBox.isSelected())
        {
          dayArray.add("Tuesday");
        }
        if(wedBox.isSelected())
        {
          dayArray.add("Wednesday");
        }
        if(thuBox.isSelected())
        {
          dayArray.add("Thursday");
        }
        if(friBox.isSelected())
        {
          dayArray.add("Friday");
        }
        if(satBox.isSelected())
        {
          dayArray.add("Saturday");
        }
        if(sunBox.isSelected())
        {
          dayArray.add("Sunday");
        }
        //deletes player from all days selected
        for (int i=0; i<dayArray.size(); i++)
        {
          //ensures that the player is entered from the day the user selected him to be deleted
          dbQuery = "SELECT ghin FROM WeekRoster WHERE ghin = " + ghin + 
              " AND day = '" + dayArray.get(i) + "'";
          rs = s.executeQuery(dbQuery);
          if (rs.next() == true)
          {
            //gets the currents playersAdded for the day
            dbQuery = "SELECT playersAdded FROM DailyTotal WHERE day = '" 
                + dayArray.get(i) + "'";
            rs = s.executeQuery(dbQuery);
            rs.next();
            playersAdded = rs.getInt("playersAdded");
            playersAdded = playersAdded - 1;
            //increments the playersAdded to account for the deletion of a player
            dbQuery = "UPDATE DailyTotal SET playersAdded = ? WHERE day = ?";
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, playersAdded);
            ps.setString(2, dayArray.get(i));
            ps.executeUpdate();
            //deletes the player from the day in which he was entered
            dbQuery = "DELETE FROM " + dayArray.get(i) + " WHERE ghin = ?";
            ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, ghin);
            ps.executeUpdate();
            //deletes the player from the weekroster for the days selected
            dbQuery = "DELETE FROM WeekRoster WHERE ghin = ? AND day = ?";
            ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, ghin);
            ps.setString(2, dayArray.get(i));
            ps.executeUpdate();
          }
          //throws error if player not entered
          else
          {
            throw new Error();
          }
        }
        ghinField.setText("");
        instructionLabel.setText("Success!");
      }
      //catches sql exception to make sure deletion happened
      catch (SQLException se)
      {
        new Warning("Error deleting data!");
      }
      //pops warning if ghin invalid
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid GHIN entered!");
      }
      //pops warning if player not entered
      catch (Error er)
      {
        new Warning("Player not entered for this day!");
      }
    }
    this.validate();
    this.repaint();
  }
}