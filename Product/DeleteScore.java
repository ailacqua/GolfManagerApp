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

public class DeleteScore extends JFrame implements ActionListener
{
  //declaring radio buttons, labels, panels, and buttons for frame
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JLabel dayLabel;
  private JLabel ghinLabel;
  private JLabel instructionLabel;
  private JTextField ghinField;
  private JButton deleteButton;
  private JButton doneButton;
  private JPanel playerPanel;
  private JPanel dayPanel;
  private JPanel buttonPanel;
  private JPanel northPanel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  
  public DeleteScore()
  {
    //initializing the frame
    super("Delete Scores");
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
    this.deleteButton = new JButton("Delete Score");
    deleteButton.addActionListener(this);
    this.doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    //constructing JLabels and textfields and setting font
    this.dayLabel = new JLabel("Day:");
    this.ghinLabel = new JLabel("GHIN:");
    this.ghinField = new JTextField(10);
    this.instructionLabel = new JLabel("Select a day and enter the player's GHIN. "
        + "Then click Delete Score.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    
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
    buttonPanel.add(deleteButton);
    buttonPanel.add(doneButton);
    this.playerPanel = new JPanel(new FlowLayout());
    playerPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    playerPanel.add(ghinLabel);
    playerPanel.add(ghinField);
    this.northPanel = new JPanel(new GridLayout(2,1));
    northPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    northPanel.add(dayPanel);
    northPanel.add(playerPanel);
    
    //adding components to frame
    this.add(northPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(instructionLabel, BorderLayout.CENTER);
    
    //setting JMenuBar and frame to visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new DeleteScore();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of action
    Object command = e.getSource();
    instructionLabel.setText("Select a day and enter the player's GHIN. Then "
        + "click Delete Score.");
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("This frame is used to delete a score. Select a day and enter a "
          + "GHIN to delete a score.");
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
    //deletes score if done pressed
    else if(command == deleteButton)
    {
      try
      {
        //declaring variables to receive input
        String day = "";
        int ghin = Integer.parseInt(ghinField.getText());
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        //establishing db conn
        String dbQuery = "";
        String dbName = "Golf";
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        String tableName = "WeekResult"; 
        String[] columnNames = {"ghin", "lastName", "firstName", "monday", "tuesday", 
          "wednesday", "thursday", "friday", "saturday", "sunday", "timesPlayed", 
          "totalScore", "averageScore", "lowestScore", "highestScore"};
        //sets day to monday if monday selected
        if ((ghin < 1000000) || (ghin > 9999999))
        {
          throw new NumberFormatException();
        }
        //sets day to monday if tuesday selected
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
        //makes sure the user is entered before deleting score
        dbQuery = "SELECT ghin FROM WeekResult WHERE ghin = " + ghin;
        rs = s.executeQuery(dbQuery);
        if (rs.next() == false)
        {
          throw new Error();
        }
        //updates the score
        dbQuery = "UPDATE WeekResult SET " + day + " = ? WHERE ghin = ?";
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        ps.setInt(1, 0);
        ps.setInt(2, ghin);
        ps.executeUpdate();
        //gets all data from table
        dataList = dbObj.getAllData(tableName, columnNames);
        SortScores scoresObj = new SortScores();
        scoresObj.setUncalculatedScores(dataList);
        dataList = scoresObj.getCalculatedScores();
        //clears the table
        dbQuery = "DELETE FROM WeekResult";
        s.execute(dbQuery);
        //inserts the calculated scores into the week result
        for (int i=0; i<dataList.size(); i++)
        {
          dbQuery = "INSERT INTO WeekResult VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          ps = myDbConn.prepareStatement(dbQuery);
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
        ghinField.setText("");
        instructionLabel.setText("Success!");
      }
      //pops warning frame if errors interacting with database
      catch (SQLException se)
      {
        new Warning("Error deleting score!");
      }
      //pops warning if ghin is invalid
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid GHIN entered!");
      }
      //pops warning if no scores added for player
      catch (Error er)
      {
        new Warning("No scores added for this player!");
      }
    }
    this.validate();
    this.repaint();
  }
}