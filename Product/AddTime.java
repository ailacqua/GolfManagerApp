/*This frame allows the user to create tee times
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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

public class AddTime extends JFrame implements ActionListener
{

  //declaring radio buttons, buttons, textfields, labels, and panels for frame
  private JTextField[] fieldArray;
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JButton addButton;
  private JButton doneButton;
  private JLabel timeLabel;
  private JLabel dayLabel;
  private JLabel instructionLabel;
  private JPanel dayPanel;
  private JPanel northPanel;
  private JPanel inputPanel;
  private JPanel buttonPanel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;

  public AddTime()
  {
    //initializing the frame
    super("Add Tee Time");
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
    this.addButton = new JButton("Add Tee Time");
    addButton.addActionListener(this);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(this);

    //constructing JLabels and setting font
    this.dayLabel = new JLabel("Day:");
    this.timeLabel = new JLabel("Enter Times:");
    this.instructionLabel = new JLabel("<html><center>Select a day, then enter "
        + "a time in a 24 hour format (2PM is 1400) in each field.<br>Add times "
        + "before selecting a new day.</center></html>", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);

    //constructing Jpanels and adding buttons, labels, and textfields
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
    buttonPanel.add(addButton);
    buttonPanel.add(doneButton);
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    this.northPanel = new JPanel(new GridLayout(2, 1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(dayPanel);
    northPanel.add(inputPanel);

    //adding components to frame
    this.add(northPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(instructionLabel, BorderLayout.CENTER);

    //setting the JMenuBar and frame to visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }

  //main method to test class
  public static void main(String[] args)
  {
    new AddTime();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //getting source of command
    Object command = e.getSource();
    instructionLabel.setText("<html><center>Select a day, then enter a time in "
        + "a 24 hour format (2PM is 1400) in each field.<br>Add times before "
        + "selecting a new day.</center></html>");
    //declaring variables to receive input
    String day = "";
    int length = 0;
    ArrayList<Integer> timeArray = new ArrayList<Integer>();
    String dbQuery = "";
    String dbName = "Golf";
    int requiredTeeTimes = 0;
    int addedTeeTimes = 0;
    boolean daySelected = false;
    ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
    String[] columnNames =
    {
      "teeTimeId", "day", "teeTimeIndex", "teeTime"
    };
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>This frame permits the addition of new times. "
          + "Select a day then fill all the fields with<br>tee times in military "
          + "time, e.g. 1400. Then select Add Tee Time.</html></center>");
    }
    //exits program if exit pressed
    else if (command == exitItem)
    {
      System.exit(0);
    }
    //launches welcome if home pressed
    else if (command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //launches manage schedule if done pressed
    else if (command == doneButton)
    {
      new ManageSchedule();
      this.dispose();
    }
    //if monday selected day set to monday and length pulled from database
    else if (monButton.isSelected())
    {
      day = "Monday";
      daySelected = true;
    }
    //if tuesday selected day set to monday and length pulled from database
    else if (tueButton.isSelected())
    {
      day = "Tuesday";
      daySelected = true;
    }
    //if wednesday selected day set to wednesday and length pulled from database
    else if (wedButton.isSelected())
    {
      day = "Wednesday";
      daySelected = true;
    }
    //if thursday selected day set to thursday and length pulled from database
    else if (thuButton.isSelected())
    {
      day = "Thursday";
      daySelected = true;
    }
    //if friday selected day set to friday and length pulled from database
    else if (friButton.isSelected())
    {
      day = "Friday";
      daySelected = true;
    }
    //if saturday selected day set to saturday and length pulled from database
    else if (satButton.isSelected())
    {
      day = "Saturday";
      daySelected = true;
    }
    //if sunday selected day set to sunday and length pulled from database
    else if (sunButton.isSelected())
    {
      day = "Sunday";
      daySelected = true;
    }

    //if add pressed times added to db
    if (command == addButton)
    {
      //if length is more than 0 then fields shown
      if (fieldArray.length > 0)
      {
        //declaring dbquery, reading ghin, and declaring dbname
        int addedTimes = 0;
        try
        {
          //establishing db conn
          JavaDatabase dbObj = new JavaDatabase(dbName);
          Connection myDbConn = dbObj.getDbConn();
          Statement s = myDbConn.createStatement();
          ResultSet rs = null;
          //gets all tee times already entered
          dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
          rs = s.executeQuery(dbQuery);
          while (rs.next())
          {
            timeArray.add(rs.getInt("teeTime"));
          }
          //entered times read into array
          for (int i = 0; i < fieldArray.length; i++)
          {
            timeArray.add(Integer.parseInt(fieldArray[i].getText()));
            if ((timeArray.get(i) > 2460) || (timeArray.get(i) < 100))
            {
              throw new NumberFormatException();
            }
          }
          //checks to make sure no times are duplicate
          for (int i = 0; i < timeArray.size(); i++)
          {
            for (int j = 0; j < timeArray.size(); j++)
            {
              if (((int)timeArray.get(i) == (int)timeArray.get(j)) && (i != j))
              {
                throw new Error();
              }
            }
          }
          //gets the amount of current addedTeeTimes
          dbQuery = "SELECT addedTeeTimes FROM DailyTotal WHERE day = '" + day + "'";
          rs = s.executeQuery(dbQuery);
          rs.next();
          addedTimes = rs.getInt("addedTeeTimes");
          //inserts tee times into table
          for (int i = 0; i < timeArray.size(); i++)
          {
            dbQuery = "INSERT INTO TeeTime VALUES (?,?,?,?)";
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setString(1, "");
            ps.setString(2, day);
            ps.setInt(3, 0);
            ps.setInt(4, timeArray.get(i));
            ps.executeUpdate();
            addedTimes++;
          }
          //gets data from tee time
          dataList = dbObj.getAllData("TeeTime", columnNames);
          //finds and sorts times based on tee time index
          TeeTime timeObj = new TeeTime();
          timeObj.setUnsortedIndex(dataList);
          dataList = timeObj.getSortedIndex();
          //clears table
          dbQuery = "DELETE FROM TeeTime";
          s.execute(dbQuery);
          //inserts sorted data into table
          for (int i = 0; i < dataList.size(); i++)
          {
            dbQuery = "INSERT INTO TeeTime VALUES (?,?,?,?)";
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setString(1, dataList.get(i).get(0));
            ps.setString(2, dataList.get(i).get(1));
            ps.setInt(3, Integer.parseInt(dataList.get(i).get(2)));
            ps.setInt(4, Integer.parseInt(dataList.get(i).get(3)));
            ps.executeUpdate();
          }
          // updates the amount of added tee times
          dbQuery = "UPDATE DailyTotal SET addedTeeTimes = ? WHERE day = ?";
          PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, addedTimes);
          ps.setString(2, day);
          ps.executeUpdate();
          daySelected = false;
          instructionLabel.setText("Success!");
        }
        //ensures times inserted with no error
        catch (SQLException se)
        {
          new Warning("Error inserting times!");
        }
        //ensures the user entered a valid tee time
        catch (NumberFormatException nfe)
        {
          new Warning("Enter a valid tee time between 0100 and 2459 in military time!");
        }
        //ensures duplicate tee time not entered
        catch (Error er)
        {
          new Warning("A time you have entered is duplicate!");
        }
      }
    }
    // if a day is selected then the fields added
    else if (daySelected == true)
    {
      try
      {
        //establishing db conn
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        //gets the added and required times from the table and stores them
        dbQuery = "SELECT requiredTeeTimes, addedTeeTimes FROM DailyTotal WHERE day = '"
            + day + "'";
        rs = s.executeQuery(dbQuery);
        rs.next();
        requiredTeeTimes = rs.getInt("requiredTeeTimes");
        addedTeeTimes = rs.getInt("addedTeeTimes");
        //finds the necessary amount of tee times to add
        length = requiredTeeTimes - addedTeeTimes;
        if (length == 0)
        {
          new Warning("Maximum tee times added for " + day);
        }
        if (length < 0)
        {

        }

        //northPanel formatted to added necessary textfields
        northPanel.remove(inputPanel);
        this.inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
        inputPanel.add(timeLabel);
        this.fieldArray = new JTextField[length];
        //adding and constructing fieldArray
        for (int i = 0; i < fieldArray.length; i++)
        {
          fieldArray[i] = new JTextField(5);
        }
        for (int i = 0; i < fieldArray.length; i++)
        {
          inputPanel.add(fieldArray[i]);
        }
        //adding northPanel to frame and inputPanel to northPanel
        northPanel.add(inputPanel);
        this.add(northPanel, BorderLayout.NORTH);
      }
      //pops warning if sql exception thrown
      catch (SQLException se)
      {
        new Warning("Error adding times!");
      }
    }
    this.validate();
    this.repaint();
  }
}
