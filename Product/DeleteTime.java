/*This class deletes a previously added tee time
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

public class DeleteTime extends JFrame implements ActionListener
{

  //declaring JButtons, RadioButtons, labels, panels, and fields for frame
  private JButton deleteButton;
  private JButton doneButton;
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JTextField timeField;
  private JLabel dayLabel;
  private JLabel timeLabel;
  private JLabel instructionLabel;
  private JPanel dayPanel;
  private JPanel buttonPanel;
  private JPanel northPanel;
  private JPanel timePanel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;

  public DeleteTime()
  {
    //initializing the frame
    super("Delete Tee Time");
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

    //constructing JLabels and texfields and setting font
    this.timeField = new JTextField(10);
    this.timeLabel = new JLabel("Enter time:");
    this.dayLabel = new JLabel("Select Day:");
    this.instructionLabel = new JLabel("Select a day, enter the time, and then "
        + "click Delete. You can only delete times with no assigned players!",
        SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);

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
    this.deleteButton = new JButton("Delete Tee Time");
    deleteButton.addActionListener(this);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(this);

    //constructing button and day panels and adding buttons, fields, and labels
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
    buttonPanel.add(deleteButton);
    buttonPanel.add(doneButton);
    this.timePanel = new JPanel(new FlowLayout());
    timePanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    timePanel.add(timeLabel);
    timePanel.add(timeField);
    this.northPanel = new JPanel(new GridLayout(2, 1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(dayPanel);
    northPanel.add(timePanel);

    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(instructionLabel, BorderLayout.CENTER);

    //setting the JMenuBar and frame to visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }

  //main method to test class
  public static void main(String[] args)
  {
    new DeleteTime();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets the source of the action
    Object command = e.getSource();
    instructionLabel.setText("Select a day, enter the time, and then click Delete. "
        + "You can only delete times with no assigned players!");
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>This frame is used to delete a tee time. Select a "
          + "day, enter a time in<br>military time (e.g. 2PM is 1400), and click "
          + "Delete.<html><center>");
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
    //launches ManageSchedule if done pressed
    else if (command == doneButton)
    {
      new ManageSchedule();
      this.dispose();
    }
    //deletes time if delete pressed
    else if (command == deleteButton)
    {
      //declaring variables to recieve input
      String day = "";
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
      //sets day to saturday if saturday selected
      else if (satButton.isSelected())
      {
        day = "Saturday";
      }
      //sets day to sunday if sunday selected
      else if (sunButton.isSelected())
      {
        day = "Sunday";
      }
      //try allows us to catch exceptions
      try
      {
        //accessing database connection
        String dbQuery = "";
        String dbName = "Golf";
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        //getting the tee time and declaring addedTeeTimes
        int teeTime = Integer.parseInt(timeField.getText());
        int addedTeeTimes = 0;
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        String[] columnNames =
        {
          "teeTimeId", "day", "teeTimeIndex", "teeTime"
        };
        //ensures teeTime is a valid time
        if (teeTime < 0)
        {
          throw new NumberFormatException();
        }
        //ensures that no players are entered for the current tee time
        dbQuery = "SELECT ghin FROM " + day + " WHERE teeTime = " + teeTime;
        rs = s.executeQuery(dbQuery);
        if (rs.next() == true)
        {
          throw new Error();
        }
        dbQuery = "SELECT teeTime FROM TeeTime WHERE teeTime = " + teeTime + 
            " AND day = '" + day + "'";
        rs = s.executeQuery(dbQuery);
        if (rs.next() == false)
        {
          throw new Exception();
        }
        //deletes time from teeTime table
        dbQuery = "DELETE FROM TeeTime WHERE teeTime = " + teeTime
            + " AND day = '" + day + "'";
        s.execute(dbQuery);
        //gets all the data from tee time table
        dataList = dbObj.getAllData("TeeTime", columnNames);
        TeeTime timeObj = new TeeTime();
        //sorts the indexes
        timeObj.setUnsortedIndex(dataList);
        dataList = timeObj.getSortedIndex();
        //clears tee time
        dbQuery = "DELETE FROM TeeTime";
        s.execute(dbQuery);
        //inserts sorted data into tee time
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
        //gets current addedTeeTimes
        dbQuery = "SELECT addedTeeTimes FROM DailyTotal WHERE day = '" + day + "'";
        rs = s.executeQuery(dbQuery);
        rs.next();
        addedTeeTimes = rs.getInt("addedTeeTimes");
        //reduces addedTeeTimes by 1
        addedTeeTimes = addedTeeTimes - 1;
        //re enters addedTeeTimes into table
        dbQuery = "UPDATE DailyTotal SET addedTeeTimes = ? WHERE day = ?";
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        ps.setInt(1, addedTeeTimes);
        ps.setString(2, day);
        ps.executeUpdate();
        instructionLabel.setText("Success!");
        timeField.setText("");
      }
      //catches errors associated with interacting with database
      catch (SQLException se)
      {
        new Warning("Error deleting time!");
      }
      //launches warning when player is assigned to tee time
      catch (Error er)
      {
        new Warning("Unable to delete tee time! There is a player assigned to "
            + "this tee time!");
      }
      //launches warning if invalid tee time entered
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid tee time entered!");
      }
      //launches warning if tee time not found
      catch (Exception ex)
      {
        new Warning("This is not a currently entered tee time!");
      }
    }
    this.validate();
    this.repaint();
  }
}
