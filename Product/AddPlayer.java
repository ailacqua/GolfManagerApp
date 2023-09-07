/*This frame allows the user to add players to the database
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

public class AddPlayer extends JFrame implements ActionListener
{
  //declaring JCheckbox
  private JCheckBox monBox;
  private JCheckBox tueBox;
  private JCheckBox wedBox;
  private JCheckBox thuBox;
  private JCheckBox friBox;
  private JCheckBox satBox;
  private JCheckBox sunBox;
  //declaring JLabel
  private JLabel ghinLabel;
  private JLabel lastLabel;
  private JLabel firstLabel;
  private JLabel dayLabel;
  private JLabel monLabel;
  private JLabel tueLabel;
  private JLabel wedLabel;
  private JLabel thuLabel;
  private JLabel friLabel;
  private JLabel satLabel;
  private JLabel sunLabel;
  private JLabel prefLabel;
  private JLabel instructionLabel;
  //declaring JLabel[]
  private JLabel[] monLabelArray;
  private JLabel[] tueLabelArray;
  private JLabel[] wedLabelArray;
  private JLabel[] thuLabelArray;
  private JLabel[] friLabelArray;
  private JLabel[] satLabelArray;
  private JLabel[] sunLabelArray;
  //declaring JTextField[]
  private JTextField[] monFieldArray;
  private JTextField[] tueFieldArray;
  private JTextField[] wedFieldArray;
  private JTextField[] thuFieldArray;
  private JTextField[] friFieldArray;
  private JTextField[] satFieldArray;
  private JTextField[] sunFieldArray;
  //declaring JTextFields
  private JTextField ghinField;
  private JTextField lastField;
  private JTextField firstField;
  //declaring JButtons
  private JButton nextButton;
  private JButton cancelButton;
  private JButton addButton;
  private JButton backButton;
  //declaring JPanels
  private JPanel boxDayPanel;
  private JPanel playerPanel;
  private JPanel buttonPanel;
  private JPanel northPanel;
  //declaring JPanel[]
  private JPanel[] dayPanelArray;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  //declaring arraylist to contain added components
  ArrayList<String> addedDays = new ArrayList<>();
  
  public AddPlayer()
  {
    //initializing the frame
    super("Add Player");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.ROSTER_COLOR);
    this.setLayout(new BorderLayout());
    addedDays.clear();
    
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
    
    //constructing checkboxes and adding to panel
    this.dayLabel = new JLabel("Days:");
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
    this.boxDayPanel = new JPanel(new FlowLayout());
    boxDayPanel.setBackground(Welcome.ROSTER_COLOR);
    boxDayPanel.add(dayLabel);
    boxDayPanel.add(monBox);
    boxDayPanel.add(tueBox);
    boxDayPanel.add(wedBox);
    boxDayPanel.add(thuBox);
    boxDayPanel.add(friBox);
    boxDayPanel.add(satBox);
    boxDayPanel.add(sunBox);
    
    //constructing JLabels and textfields and setting font
    this.ghinLabel = new JLabel("GHIN:");
    this.ghinField = new JTextField(10);
    this.lastLabel = new JLabel("Last Name:");
    this.lastField = new JTextField(10);
    this.firstLabel = new JLabel("First Name:");
    this.firstField = new JTextField(10);
    this.monLabel = new JLabel("Monday:");
    this.tueLabel = new JLabel("Tuesday:");
    this.wedLabel = new JLabel("Wednesday:");
    this.thuLabel = new JLabel("Thursday:");
    this.friLabel = new JLabel("Friday:");
    this.satLabel = new JLabel("Saturday:");
    this.sunLabel = new JLabel("Sunday:");
    this.prefLabel = new JLabel("Rank Time Preference", SwingConstants.CENTER);
    prefLabel.setFont(Welcome.HEADER_FONT);
    this.instructionLabel = new JLabel("Enter player data, select days to play, "
        + "then click next.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    
    
    //constructing JButtons and adding actionlistener
    this.nextButton = new JButton("Next");
    nextButton.addActionListener(this);
    this.cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    this.addButton = new JButton("Add Player");
    addButton.addActionListener(this);
    this.backButton = new JButton("Add Another Player");
    backButton.addActionListener(this);
    
    //constructing JPanels and adding components
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(nextButton);
    buttonPanel.add(cancelButton);
    this.playerPanel = new JPanel(new FlowLayout());
    playerPanel.setBackground(Welcome.ROSTER_COLOR);
    playerPanel.add(ghinLabel);
    playerPanel.add(ghinField);
    playerPanel.add(lastLabel);
    playerPanel.add(lastField);
    playerPanel.add(firstLabel);
    playerPanel.add(firstField);
    this.northPanel = new JPanel(new GridLayout(2,1));
    northPanel.setBackground(Welcome.ROSTER_COLOR);
    northPanel.add(playerPanel);
    northPanel.add(boxDayPanel);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(instructionLabel, BorderLayout.CENTER);
    
    //setting the jmenubar and the frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test the class
  public static void main(String[] args)
  {
    new AddPlayer();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of command
    Object command = e.getSource();
    this.dayPanelArray = new JPanel[7];
    int[] timeLength = new int[7];
    int ghin = 0;
    String firstName = "";
    String lastName = "";
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    //opens help frame if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>Make sure that the GHIN number is a 7 digit set of "
          + "numbers. Be sure to select the<br>days on which the individual wants "
          + "to play by checking the box.</center></html>");
      
    }
    //exits program if exit pressed
    else if(command == exitItem)
    {
      System.exit(0);
    }
    //opens welcome if home pressed
    else if(command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //opens manage rosters if cancelButton selected
    else if(command == cancelButton)
    {
      new ManageRosters();
      this.dispose();
    }
    //creates new add player frame if back pressed
    else if(command == backButton)
    {
      new AddPlayer();
      this.dispose();
    }
    //opens the second part of addplayer if next pressed
    else if(command == nextButton)
    {
      instructionLabel.setText("Rank each time starting from highest preference "
          + "with 1, on each day. Then, select Add Player.");
      //constructing dayPanelArray components and setting color
      try
      {
        //declaring vars to hold data and access db
        String dbQuery = "";
        String dbName = "Golf";
        int[] playersAdded = new int[7];
        int[] totalPlayers = new int[7];
        int[] requiredTeeTimes = new int[7];
        int[] addedTeeTimes = new int[7];
        ghin = Integer.parseInt(ghinField.getText());
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        String day = "";
        ArrayList<String> teeTimes = new ArrayList<>();
        //verifies formatting of ghin
        if ((ghin < 1000000) || (ghin > 9999999))
        {
          throw new NumberFormatException();
        }
        //gets the total players and addedplayers for the day being added to
        for (int i=0; i<days.length; i++)
        {
          dbQuery = "SELECT totalPlayers, playersAdded, requiredTeeTimes, "
              + "addedTeeTimes FROM DailyTotal WHERE day = '" + days[i] + "'";
          rs = s.executeQuery(dbQuery);
          rs.next();
          
          totalPlayers[i] = rs.getInt("totalPlayers");
          playersAdded[i] = rs.getInt("playersAdded");
          requiredTeeTimes[i] = rs.getInt("requiredTeeTimes");
          addedTeeTimes[i] = rs.getInt("addedTeeTimes");
          //if they are not equal error thrown
          if (addedTeeTimes[i] != requiredTeeTimes[i])
          {
            throw new Error();
          }
        }
        //contructs and sets font of day array
        for (int i=0; i<dayPanelArray.length; i++)
        {
          dayPanelArray[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
          dayPanelArray[i].setBackground(Welcome.ROSTER_COLOR);
        }
        //creates new northPanel and removes and adds buttons to buttonPanel
        this.remove(northPanel);
        this.northPanel = new JPanel(new GridLayout(8,1));
        northPanel.setBackground(Welcome.ROSTER_COLOR);
        northPanel.add(prefLabel);
        buttonPanel.remove(nextButton);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        //if monBox selected textfields and times added to row for monday
        if(monBox.isSelected())
        {
          //ensures space still available for monday
          if (playersAdded[0] >= totalPlayers[0])
          {
            new Warning("Add more total players for Monday!");
          }
          //gets tee times for monday
          else
          {
            teeTimes.clear();
            day = "Monday";
            //gets tee times for day
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            //adds tee times to array
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            //constructs labels and fields for times and adds to frame
            dayPanelArray[0].add(monLabel);
            timeLength[0] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.monLabelArray = new JLabel[timeLength[0]];
            this.monFieldArray = new JTextField[timeLength[0]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<monLabelArray.length; i++)
            {
              monLabelArray[i] = new JLabel(teeTimes.get(i));
              monFieldArray[i] = new JTextField(3);
              dayPanelArray[0].add(monLabelArray[i]);
              dayPanelArray[0].add(monFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[0]);
          }
        }
        //if tueBox selected textfields and times added to row for tuesday
        if(tueBox.isSelected())
        {
          //verifies space still available for tuesday
          if (playersAdded[1] >= totalPlayers[1])
          {
            new Warning("Add more total players for Tuesday!");
          }
          else
          {
            teeTimes.clear();
            day = "Tuesday";
            //gets tee times for tuesday
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            dayPanelArray[1].add(tueLabel);
            timeLength[1] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.tueLabelArray = new JLabel[timeLength[1]];
            this.tueFieldArray = new JTextField[timeLength[1]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<tueLabelArray.length; i++)
            {
              tueLabelArray[i] = new JLabel(teeTimes.get(i));
              tueFieldArray[i] = new JTextField(3);
              dayPanelArray[1].add(tueLabelArray[i]);
              dayPanelArray[1].add(tueFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[1]);
          }
        }
        //if wedBox selected textfields and times added to row for wednesday
        if(wedBox.isSelected())
        {
          if (playersAdded[2] >= totalPlayers[2])
          {
            new Warning("Add more total players for Wednesday!");
          }
          else
          {
            teeTimes.clear();
            day = "Wednesday";
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            dayPanelArray[2].add(wedLabel);
            timeLength[2] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.wedLabelArray = new JLabel[timeLength[2]];
            this.wedFieldArray = new JTextField[timeLength[2]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<wedLabelArray.length; i++)
            {
              wedLabelArray[i] = new JLabel(teeTimes.get(i));//FIXTHIS
              wedFieldArray[i] = new JTextField(3);
              dayPanelArray[2].add(wedLabelArray[i]);
              dayPanelArray[2].add(wedFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[2]);
          }
        }
        //if thuBox selected textfields and times added to row for thursday
        if(thuBox.isSelected())
        {
          if (playersAdded[3] >= totalPlayers[3])
          {
            new Warning("Add more total players for Thursday!");
          }
          else
          {
            teeTimes.clear();
            day = "Thursday";
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            dayPanelArray[3].add(thuLabel);
            timeLength[3] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.thuLabelArray = new JLabel[timeLength[3]];
            this.thuFieldArray = new JTextField[timeLength[3]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<thuLabelArray.length; i++)
            {
              thuLabelArray[i] = new JLabel(teeTimes.get(i));
              thuFieldArray[i] = new JTextField(3);
              dayPanelArray[3].add(thuLabelArray[i]);
              dayPanelArray[3].add(thuFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[3]);
          }
        }
        //if friBox selected textfields and times added to row for friday
        if(friBox.isSelected())
        {
          if (playersAdded[4] >= totalPlayers[4])
          {
            new Warning("Add more total players for Friday!");
          }
          else
          {
            teeTimes.clear();
            day = "Friday";
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            dayPanelArray[4].add(friLabel);
            timeLength[4] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.friLabelArray = new JLabel[timeLength[4]];
            this.friFieldArray = new JTextField[timeLength[4]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<friLabelArray.length; i++)
            {
              friLabelArray[i] = new JLabel(teeTimes.get(i));//FIXTHIS
              friFieldArray[i] = new JTextField(3);
              dayPanelArray[4].add(friLabelArray[i]);
              dayPanelArray[4].add(friFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[4]);
          }
        }
        //if satBox selected textfields and times added to row for saturday
        if(satBox.isSelected())
        {
          if (playersAdded[5] >= totalPlayers[5])
          {
            new Warning("Add more total players for Saturday!");
          }
          else
          {
            teeTimes.clear();
            day = "Saturday";
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            dayPanelArray[5].add(satLabel);
            timeLength[5] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.satLabelArray = new JLabel[timeLength[5]];
            this.satFieldArray = new JTextField[timeLength[5]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<satLabelArray.length; i++)
            {
              satLabelArray[i] = new JLabel(teeTimes.get(i));
              satFieldArray[i] = new JTextField(3);
              dayPanelArray[5].add(satLabelArray[i]);
              dayPanelArray[5].add(satFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[5]);
          }
        }
        //if sunBox selected textfields and times added to row for sunday
        if(sunBox.isSelected())
        {
          if (playersAdded[6] >= totalPlayers[6])
          {
            new Warning("Add more total players for Sunday!");
          }
          else
          {
            teeTimes.clear();
            day = "Sunday";
            dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
            rs = s.executeQuery(dbQuery);
            while (rs.next())
            {
              teeTimes.add(rs.getString("teeTime"));
            }
            dayPanelArray[6].add(sunLabel);
            timeLength[6] = teeTimes.size();
            if (teeTimes.size() > 0)
            {
              addedDays.add(day);
            }
            this.sunLabelArray = new JLabel[timeLength[6]];
            this.sunFieldArray = new JTextField[timeLength[6]];
            //constructs labels and textfields according to tee times entered
            for (int i=0; i<sunLabelArray.length; i++)
            {
              sunLabelArray[i] = new JLabel(teeTimes.get(i));
              sunFieldArray[i] = new JTextField(3);
              dayPanelArray[6].add(sunLabelArray[i]);
              dayPanelArray[6].add(sunFieldArray[i]);
            }
            //adds day row to northPanel
            northPanel.add(dayPanelArray[6]);
          }
        }
        for (int i=0; i < addedDays.size(); i++)
        {
          dbQuery = "SELECT ghin FROM WeekRoster WHERE ghin = " + ghin + 
              " AND day = '" + addedDays.get(i) + "'";
          rs = s.executeQuery(dbQuery);
          if (rs.next() == true)
          {
            new Warning("Can not add duplicate player to " + addedDays.get(i) + "!");
            northPanel.removeAll();
            break;
          }
        }
        //adding components to frame
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
      }
      //pops warning if sql exception found
      catch (SQLException se)
      {
        new Warning("Error receiving times!");
      }
      //pops warning if number format exception found
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid GHIN entered!");
      }
      //pops warning if error thrown
      catch (Error er)
      {
        new Warning("Be sure the added tee times is equal to the required for each day!");
      }
    }
    //if addButton pressed player and times added to db
    else if(command == addButton)
    {
      try
      {
        //declaring variables to access db adn hold data
        String dbQuery = "";
        String dbName = "Golf";
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        int rankSum = 0;
        int neededSum = 0;
        int playersAdded = 0;
        ArrayList<String> teeTimes = new ArrayList<>();
        ghin = Integer.parseInt(ghinField.getText());
        lastName = lastField.getText();
        firstName = firstField.getText();
        int teeTimeIndex;
        int teeTime;
        for (int i=0; i<addedDays.size(); i++)
        {
          //inserts player into week roster
          Player playerObj = new Player(ghin, addedDays.get(i));
          dbQuery = "INSERT INTO WeekRoster VALUES (?,?,?,?,?)";
          PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, playerObj.getRegistrationId());
          ps.setInt(2, ghin);
          ps.setString(3, lastName);
          ps.setString(4, firstName);
          ps.setString(5, addedDays.get(i));
          ps.executeUpdate();
          //updates the total players added
          dbQuery = "SELECT playersAdded FROM DailyTotal WHERE day = '" + 
              addedDays.get(i) + "'";
          rs = s.executeQuery(dbQuery);
          if (rs.next())
          {
            playersAdded = rs.getInt("playersAdded");
          }
          playersAdded = playersAdded + 1;
          //updates the total players added
          dbQuery = "UPDATE DailyTotal SET playersAdded = ? WHERE day = ?";
          ps = myDbConn.prepareStatement(dbQuery);
          ps.setInt(1, playersAdded);
          ps.setString(2, addedDays.get(i));
          ps.executeUpdate();

          //adds player to monday if monday selected
          if (addedDays.get(i).equals("Monday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<monLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(monFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //inserts player into monday
            for (int j=0; j<monLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + monLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Monday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());//FIXTHIS
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(monFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(monLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
          //adds player to tuesday if tuesday selected
          else if (addedDays.get(i).equals("Tuesday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<tueLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(tueFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //adds player to tuesday
            for (int j=0; j<tueLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " 
                  + tueLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Tuesday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(tueFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(tueLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
          //adds player to wednesday if wednesday selected
          else if (addedDays.get(i).equals("Wednesday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<wedLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(wedFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //adds player to wednesday
            for (int j=0; j<wedLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + wedLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Wednesday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(wedFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(wedLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
          //adds player to thursday if thursday selected
          else if (addedDays.get(i).equals("Thursday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<thuLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(thuFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //adds player to thursday
            for (int j=0; j<thuLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + thuLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Thursday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(thuFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(thuLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
          else if (addedDays.get(i).equals("Friday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<friLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(friFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //adds player to friday if selected
            for (int j=0; j<friLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + friLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Friday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(friFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(friLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
          //adds player to saturday if selected
          else if (addedDays.get(i).equals("Saturday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<satLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(satFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //inserts player into saturday table
            for (int j=0; j<satLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + satLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Saturday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(satFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(satLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
          //adds player to sunday if selected
          else if (addedDays.get(i).equals("Sunday"))
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are correct
            for (int j=0; j<sunLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(sunFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //inserts player into sunday table
            for (int j=0; j<sunLabelArray.length; j++)
            {
              dbQuery = "SELECT teeTimeIndex FROM TeeTime WHERE teeTime = " + sunLabelArray[j].getText() + " AND day = '" + addedDays.get(i) + "'";
              rs = s.executeQuery(dbQuery);
              rs.next();
              teeTimeIndex = rs.getInt("teeTimeIndex");
              //finds the play time id
              TeeTime timeObj = new TeeTime(teeTimeIndex, ghin);
              dbQuery = "INSERT INTO Sunday VALUES (?,?,?,?,?,?,?,?)";
              ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, timeObj.getPlayTimeId());
              ps.setInt(2, ghin);
              ps.setString(3, lastName);
              ps.setString(4, firstName);
              ps.setInt(5, teeTimeIndex);
              ps.setInt(6, Integer.parseInt(sunFieldArray[j].getText()));
              ps.setInt(7, Integer.parseInt(sunLabelArray[j].getText()));
              ps.setInt(8, 0);
              ps.executeUpdate();
            }
          }
        }
        instructionLabel.setText("Success!");
        this.repaint();
      }
      //pops warning if sqlexception
      catch (SQLException se)
      {
        new Warning("Error adding players!");
      }
      //pops warning if number format exception found
      catch (NumberFormatException nfe)
      {
        new Warning("The GHIN and rankings should contain only integers!");
      }
      //pops warning if ranks not entered sequentially
      catch (UnsupportedOperationException uoe)
      {
        new Warning("Ranks not entered sequentially!");
      }
    }
    this.validate();
    this.repaint();
  }
}