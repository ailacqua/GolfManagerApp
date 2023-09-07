/*This frame allows the user to add scores for different score
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

public class AddScore extends JFrame implements ActionListener
{
  //declaring radio buttons, textfields, labels, buttons, and panels for frame
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JLabel dayLabel;
  private JLabel scoreLabel;
  private JLabel ghinLabel;
  private JLabel instructionLabel;
  private JTextField ghinField;
  private JTextField scoreField;
  private JButton addButton;
  private JButton doneButton;
  private JPanel scorePanel;
  private JPanel dayPanel;
  private JPanel buttonPanel;
  private JPanel northPanel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  
  
  public AddScore()
  {
    //initializing the frame
    super("Add Score");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.SCORE_COLOR);
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
    this.addButton = new JButton("Add Score");
    addButton.addActionListener(this);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    //constructing JLabels and textfields
    this.dayLabel = new JLabel("Day:");
    this.ghinLabel = new JLabel("GHIN:");
    this.ghinField = new JTextField(10);
    this.scoreLabel = new JLabel("Score:");
    this.scoreField = new JTextField(5);
    this.instructionLabel = new JLabel("Select a day and insert the player's GHIN "
        + "number and score. Then, click Add Score.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    
    //constructing Jpanels and adding labels and buttons
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
    this.scorePanel = new JPanel(new FlowLayout());
    scorePanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    scorePanel.add(ghinLabel);
    scorePanel.add(ghinField);
    scorePanel.add(scoreLabel);
    scorePanel.add(scoreField);
    this.northPanel = new JPanel(new GridLayout(2,1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(dayPanel);
    northPanel.add(scorePanel);
    
    //adding components to frame
    this.add(northPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(instructionLabel, BorderLayout.CENTER);
    
    //setting the JMenuBar and setting frame to visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new AddScore();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of action
    Object command = e.getSource();
    instructionLabel.setText("Select a day and insert the player's GHIN number "
        + "and score. Then, click Add Score.");
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>This frame is used to add scores for players. "
          + "Select a day, enter the player's<br>GHIN number and their score. "
          + "Then, click Add Score.</center></html>");
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
    //launches manage scores if done pressed
    else if(command == doneButton)
    {
      new ManageScores();
      this.dispose();
    }
    //adds button to database if add pressed
    else if(command == addButton)
    {
      try
      {
        //declaring variables to recieve input
        String day = "";
        int score = Integer.parseInt(scoreField.getText());
        int ghin = Integer.parseInt(ghinField.getText());
        String dbQuery = "";
        String dbName = "Golf";
        String lastName;
        String firstName;
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        //establishing db connection
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        String tableName = "WeekResult"; 
        String[] columnNames = {"ghin", "lastName", "firstName", "monday", "tuesday", 
          "wednesday", "thursday", "friday", "saturday", "sunday", "timesPlayed", 
          "totalScore", "averageScore", "lowestScore", "highestScore"};
        //verifies formatting of the ghin
        if ((ghin < 1000000) || (ghin > 9999999))
        {
          throw new NumberFormatException();
        }
        //ensures the score is positive
        else if (score < 0)
        {
          throw new NumberFormatException();
        }
        //sets day to monday if monday selected
        if (monButton.isSelected())
        {
          day = "monday";
        }
        //sets day to tuesday if tuesday selected
        else if (tueButton.isSelected())
        {
          day = "tuesday";
        }
        //sets day to wednesday if wednesday selected
        else if (wedButton.isSelected())
        {
          day = "wednesday";
        }
        //sets day to thursday if thursday selected
        else if (thuButton.isSelected())
        {
          day = "thursday";
        }
        //sets day to friday if friday selected
        else if (friButton.isSelected())
        {
          day = "friday";
        }
        //sets day to saturday if saturday selected
        else if (satButton.isSelected())
        {
          day = "saturday";
        }
        //sets day to sunday if sunday selected
        else if (sunButton.isSelected())
        {
          day = "sunday";
        }
        //verifies to see if score has already been added for this day
        dbQuery = "SELECT ghin FROM WeekResult WHERE ghin = " + ghin;
        rs = s.executeQuery(dbQuery);
        //if yes then new score added to this day and updated
        if (rs.next() == true)
        {
          dbQuery = "UPDATE WeekResult SET " + day + " = ? WHERE ghin = ?";
          PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, score);
          ps.setInt(2, ghin);
          ps.executeUpdate();
        }
        else
        {
          //gets player info from the weekroster
          dbQuery = "SELECT lastName, firstName FROM WeekRoster WHERE ghin = " + ghin;
          rs = s.executeQuery(dbQuery);
          if (rs.next() == false)
          {
            throw new Error();
          }
          lastName = rs.getString("lastName");
          firstName = rs.getString("firstName");
          //inserts the player data and blank scores into the weekresult
          dbQuery = "INSERT INTO WeekResult VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, ghin);
          ps.setString(2, lastName);
          ps.setString(3, firstName);
          ps.setInt(4, 0);
          ps.setInt(5, 0);
          ps.setInt(6, 0);
          ps.setInt(7, 0);
          ps.setInt(8, 0);
          ps.setInt(9, 0);
          ps.setInt(10, 0);
          ps.setInt(11, 0);
          ps.setInt(12, 0);
          ps.setDouble(13, 0);
          ps.setInt(14, 0);
          ps.setInt(15, 0);
          ps.executeUpdate();
          //updates the score with the new score
          dbQuery = "UPDATE WeekResult SET " + day + " = ? WHERE ghin = ?";
          ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, score);
          ps.setInt(2, ghin);
          ps.executeUpdate();
        }
        //gets all data from table and sorts the scores
        dataList = dbObj.getAllData(tableName, columnNames);
        SortScores scoresObj = new SortScores();
        scoresObj.setUncalculatedScores(dataList);
        dataList = scoresObj.getCalculatedScores();
        //deletes everything in the table
        dbQuery = "DELETE FROM WeekResult";
        s.execute(dbQuery);
        //inserts the sorted values into the table
        for (int i=0; i<dataList.size(); i++)
        {
          dbQuery = "INSERT INTO WeekResult VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, Integer.parseInt(dataList.get(i).get(0)));
          ps.setString(2, dataList.get(i).get(1));
          ps.setString(3, dataList.get(i).get(2));
          ps.setInt(4, Integer.parseInt(dataList.get(i).get(3)));
          ps.setInt(5, Integer.parseInt(dataList.get(i).get(4)));
          ps.setInt(6, Integer.parseInt(dataList.get(i).get(5)));
          ps.setInt(7, Integer.parseInt(dataList.get(i).get(6)));
          ps.setInt(8, Integer.parseInt(dataList.get(i).get(7)));
          ps.setInt(9, Integer.parseInt(dataList.get(i).get(8)));
          ps.setInt(10, Integer.parseInt(dataList.get(i).get(9)));
          ps.setInt(11, Integer.parseInt(dataList.get(i).get(10)));
          ps.setInt(12, Integer.parseInt(dataList.get(i).get(11)));
          ps.setDouble(13, Double.parseDouble(dataList.get(i).get(12)));
          ps.setInt(14, Integer.parseInt(dataList.get(i).get(13)));
          ps.setInt(15, Integer.parseInt(dataList.get(i).get(14)));
          ps.executeUpdate();
        }
        //shows success
        ghinField.setText("");
        scoreField.setText("");
        instructionLabel.setText("Success!");
      }
      //opens warning if sql issues presents
      catch (SQLException nfe)
      {
        new Warning("Error adding score!");
      }
      //opens warning if duplicate score
      catch (Error er)
      {
        new Warning("This player is not registered on this day!");
      }
      //opens warning if score or ghin not int
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid GHIN or score entered!");
      }
    }
    this.validate();
    this.repaint();
  }
}