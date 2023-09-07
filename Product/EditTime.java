/*This frame allows the user to edit a previous entered tee time
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

public class EditTime extends JFrame implements ActionListener
{
  //declaring buttons, panels, lables, and textfields for frame
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JLabel dayLabel;
  private JLabel oldLabel;
  private JTextField oldField;
  private JLabel newLabel;
  private JLabel instructionLabel;
  private JTextField newField;
  private JPanel dayPanel;
  private JPanel inputPanel;
  private JPanel northPanel;
  private JPanel buttonPanel;
  private JButton updateButton;
  private JButton doneButton;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  
  public EditTime()
  {
    //initializing the frame
    super("Edit Tee Time");
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
    
    //constructing JButtons and button group and adding actionlistener
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
    this.updateButton = new JButton("Update Tee Time");
    updateButton.addActionListener(this);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    //constructing JLabels and Fields and setting font
    this.dayLabel = new JLabel("Day:");
    this.oldLabel = new JLabel("Old Tee Time:");
    this.oldField = new JTextField(10);
    this.newLabel = new JLabel("New Tee Time:");
    this.newField = new JTextField(10);
    this.instructionLabel = new JLabel("Select a day, then enter the old and "
        + "new tee time in military time and click Update.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    
    //constructing Jpanels and adding buttons, labels, and fields
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
    inputPanel.add(oldLabel);
    inputPanel.add(oldField);
    inputPanel.add(newLabel);
    inputPanel.add(newField);
    this.northPanel = new JPanel(new GridLayout(2,1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(dayPanel);
    northPanel.add(inputPanel);
    
    //adding components to frame
    this.add(northPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(instructionLabel, BorderLayout.CENTER);
    
    //setting default JMenuBar and setting frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new EditTime();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets the source of action
    Object command = e.getSource();
    instructionLabel.setText("Select a day, then enter the old and new tee time "
        + "in military time and click Update.");
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>This frame is used to update a tee time. Select "
          + "the day, then enter the new time and old time.<br>They must be in "
          + "military time, e.g. 2PM is 1400.<html><center>");
    }
    //exits program if exit pressed
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
    //opens manage schedule if done pressed
    else if(command == doneButton)
    {
      new ManageSchedule();
      this.dispose();
    }
    //updates tee time if update pressed
    else if(command == updateButton)
    { 
      try
      {
        //accessing database connection
        String dbQuery = "";
        String dbName = "Golf";
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        //declaring variables to store input
        String day = "";
        int oldTime = Integer.parseInt(oldField.getText());
        int newTime = Integer.parseInt(newField.getText());
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        String[] columnNames = {"teeTimeId", "day", "teeTimeIndex", "teeTime"};
        int teeTimeIndex; 
        int ghin;
        //checks for time validity
        if (((oldTime > 2460) || (oldTime < 100)) || ((newTime > 2460) || (newTime < 100)))
        { 
          throw new NumberFormatException();
        }
        //sets day to monday if monday selected
        if (monButton.isSelected())
        {
          day = "Monday";
        }
        //sets day to tuesday if tuesday selected
        else if (tueButton.isSelected())
        {
          day = "Tuesday";
        }
        //sets day to wednesday if wednesday selected
        else if (wedButton.isSelected())
        {
          day = "Wednesday";
        }
        //sets day to thursday if thursday selected
        else if (thuButton.isSelected())
        {
          day = "Thursday";
        }
        //sets day to friday if friday selected
        else if (friButton.isSelected())
        {
          day = "Friday";
        }
        //sets day to satruday if saturday selected
        else if (satButton.isSelected())
        {
          day = "Saturday";
        }
        //sets day to sunday if sunday selected
        else if (sunButton.isSelected())
        {
          day = "Sunday";
        }
        //ensures the user doesnt enter a duplicate tee time
        dbQuery = "SELECT teeTime FROM TeeTime WHERE teeTime = " + newTime + 
            " AND day = '" + "'";
        rs = s.executeQuery(dbQuery);
        if (rs.next() == true)
        {
          throw new Error();
        }
        //updates the current tee time to the new tee time
        dbQuery = "UPDATE TeeTime SET teeTime = ? WHERE day = ? AND teeTime = ?";
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        ps.setInt(1, newTime);
        ps.setString(2, day);
        ps.setInt(3, oldTime);
        ps.executeUpdate();
        //gets all the data from the table
        dataList = dbObj.getAllData("TeeTime", columnNames);
        TeeTime timeObj = new TeeTime();
        //sorts the times and indexes
        timeObj.setUnsortedIndex(dataList);
        dataList = timeObj.getSortedIndex();
        //clears the table
        dbQuery = "DELETE FROM TeeTime";
        s.execute(dbQuery);
        //repopulates table with sorted times
        for (int i=0; i<dataList.size(); i++)
        {
          dbQuery = "INSERT INTO TeeTime VALUES (?,?,?,?)";
          ps = myDbConn.prepareStatement(dbQuery);
          ps.setString(1, dataList.get(i).get(0));
          ps.setString(2, dataList.get(i).get(1));
          ps.setInt(3, Integer.parseInt(dataList.get(i).get(2)));
          ps.setInt(4, Integer.parseInt(dataList.get(i).get(3)));
          ps.executeUpdate();
        }
        //updates all day tables with new tee times
        dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + newTime + " AND day = '" + day + "'";
        rs = s.executeQuery(dbQuery);
        rs.next();
        teeTimeIndex = rs.getInt("teeTimeIndex");
        dbQuery = "SELECT ghin FROM " + day + " WHERE teeTime = " + oldTime;
        rs = s.executeQuery(dbQuery);
        while (rs.next())
        {
          ghin = rs.getInt("ghin");
          timeObj.setGhin(ghin);
          timeObj.setTeeTimeIndex(teeTimeIndex);
          dbQuery = "UPDATE " + day + " SET teeTime = ?, teeTimeIndex = ?, playTimeId = ? WHERE teeTime = ?";
          ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, newTime);
          ps.setInt(2, teeTimeIndex);
          ps.setInt(3, timeObj.getPlayTimeId());
          ps.setInt(4, oldTime);
          ps.executeUpdate();
        }
        oldField.setText("");
        newField.setText("");
        instructionLabel.setText("Success!");
      }
      //ensures the user enters integer tee times
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid tee times entered!");
      }
      //catches errors updating times
      catch (SQLException se)
      {
        new Warning("Error updating times!");
      }
      //ensures each tee time is unique
      catch (Error er)
      {
        new Warning("This is a duplicate tee time!");
      }
      //ensures duplicate tee time not entered
      
    }
    this.validate();
    this.repaint();
  }
}