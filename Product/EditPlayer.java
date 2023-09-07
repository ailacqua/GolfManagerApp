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
import java.sql.SQLException;
import java.sql.Statement;
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
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class EditPlayer extends JFrame implements ActionListener
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
  //declaring JButtons
  private JButton nextButton;
  private JButton cancelButton;
  private JButton updateButton;
  private JButton backButton;
  private JButton showButton;
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
  
  public EditPlayer()
  {
    //initializing the frame
    super("Edit Player");
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
    
    //constructing checkboxes and adding to panel
    this.dayLabel = new JLabel("Days to Edit:");
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
    
    //constructing JLabels and textfields
    this.ghinLabel = new JLabel("GHIN:");
    this.ghinField = new JTextField(10);
    this.monLabel = new JLabel("Monday:");
    this.tueLabel = new JLabel("Tuesday:");
    this.wedLabel = new JLabel("Wednesday:");
    this.thuLabel = new JLabel("Thursday:");
    this.friLabel = new JLabel("Friday:");
    this.satLabel = new JLabel("Saturday:");
    this.sunLabel = new JLabel("Sunday:");
    this.prefLabel = new JLabel("Rank Time Preference", SwingConstants.CENTER);
    prefLabel.setFont(Welcome.HEADER_FONT);
    this.instructionLabel = new JLabel("Enter player data, select days to edit, "
        + "then click next.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    
    
    //constructing JButtons and adding actionListener
    this.nextButton = new JButton("Next");
    nextButton.addActionListener(this);
    this.cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    this.updateButton = new JButton("Update Player");
    updateButton.addActionListener(this);
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);
    this.showButton = new JButton("Show Days");
    showButton.addActionListener(this);
    
    //constructing JPanels and adding components
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(nextButton);
    buttonPanel.add(cancelButton);
    this.playerPanel = new JPanel(new FlowLayout());
    playerPanel.setBackground(Welcome.ROSTER_COLOR);
    playerPanel.add(ghinLabel);
    playerPanel.add(ghinField);
    playerPanel.add(showButton);
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
    new EditPlayer();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of command
    Object command = e.getSource();
    instructionLabel.setText("Enter player data, select days to edit, then click next.");
    //declaring components to read input
    ArrayList<String> dayArray = new ArrayList<>(7);
    this.dayPanelArray = new JPanel[7];
    int[] timeLength = new int[7];
    int ghin = 0;
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    if (command == helpItem)
    {
      //opens help frame if help pressed
      new Help("<html><center>Make sure that the GHIN number is a 7 digit set of "
          + "numbers. Be sure to select the<br>days which you want"
          + "to edit by checking the box.</center></html>");
      
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
    //creates new edit player frame if back pressed
    else if(command == backButton)
    {
      this.dispose();
      new EditPlayer();
    }
    //shows days to edit if show clicked
    else if (command == showButton)
    {
      try
      {
        //declaring variables to hold input and access database
        String dbQuery = "";
        String dbName = "Golf";
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        ghin = Integer.parseInt(ghinField.getText());
        String day = "";
        boolean added = false;
        //ensures that ghin is valid
        if ((ghin < 1000000) || (ghin > 9999999))
        {
          throw new NumberFormatException();
        }
        //updates GUI frame
        northPanel.remove(boxDayPanel);
        this.boxDayPanel = new JPanel(new FlowLayout());
        boxDayPanel.setBackground(Welcome.ROSTER_COLOR);
        dbQuery = "SELECT day FROM WeekRoster WHERE ghin = " + ghin;
        rs = s.executeQuery(dbQuery);
        //adds days to select based on current days for which player is entered
        while (rs.next())
        {
          day = rs.getString("day");
          if (day.equals(days[0]))
          {
            boxDayPanel.add(monBox);
          }
          else if (day.equals(days[1]))
          {
            boxDayPanel.add(tueBox);
          }
          else if (day.equals(days[2]))
          {
            boxDayPanel.add(wedBox);
          }
          else if (day.equals(days[3]))
          {
            boxDayPanel.add(thuBox);
          }
          else if (day.equals(days[4]))
          {
            boxDayPanel.add(friBox);
          }
          else if (day.equals(days[5]))
          {
            boxDayPanel.add(satBox);
          }
          else if (day.equals(days[6]))
          {
            boxDayPanel.add(sunBox);
          }
          added = true;
        }
        //if none added throws error
        if (added == false)
        {
          throw new Error();
        }
        //adds all components to frame
        northPanel.add(boxDayPanel);
        this.add(northPanel, BorderLayout.NORTH);
      }
      //pops warning if sql exception thrown
      catch (SQLException se)
      {
        new Warning("Error editing player!");
      }
      //pops warning if player not entered
      catch (Error er)
      {
        new Warning("Player not entered on any days!");
      }
      //pops warning if invalid ghin
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid GHIN entered!");
      }
      
    }
    //opens the second part of addplayer if next pressed
    else if(command == nextButton)
    {
      instructionLabel.setText("Rank each time starting from highest preference "
          + "with 1, on each day. Then, select Add Player.");
      try
      {
        //declaring items to access database
        String dbQuery = "";
        String dbName = "Golf";
        ghin = Integer.parseInt(ghinField.getText());
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        String day = "";
        ArrayList<String> teeTimes = new ArrayList<>();
        //checks for ghin validity
        if ((ghin < 1000000) || (ghin > 9999999))
        {
          throw new NumberFormatException();
        }
        //constructing dayPanelArray components and setting color
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
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);

        //if monBox selected textfields and times added to row for monday
        if(monBox.isSelected())
        {
          teeTimes.clear();
          day = "Monday";
          dbQuery = "SELECT teeTime FROM TeeTime WHERE day = '" + day + "'";
          rs = s.executeQuery(dbQuery);
          while (rs.next())
          {
            teeTimes.add(rs.getString("teeTime"));
          }
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
            monLabelArray[i] = new JLabel(teeTimes.get(i));//FIXTHIS
            monFieldArray[i] = new JTextField(3);
            dayPanelArray[0].add(monLabelArray[i]);
            dayPanelArray[0].add(monFieldArray[i]);
          }
          //adds day row to northPanel
          northPanel.add(dayPanelArray[0]);
        }
        //if tueBox selected textfields and times added to row for tuesday
        if(tueBox.isSelected())
        {
          teeTimes.clear();
          day = "Tuesday";
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
        //if wedBox selected textfields and times added to row for wednesday
        if(wedBox.isSelected())
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
        //if thuBox selected textfields and times added to row for thursday
        if(thuBox.isSelected())
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
        //if friBox selected textfields and times added to row for friday
        if(friBox.isSelected())
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
        //if satBox selected textfields and times added to row for saturday
        if(satBox.isSelected())
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
        //if sunBox selected textfields and times added to row for sunday
        if(sunBox.isSelected())
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
        //adding components to frame
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
      }
      //pops warning if sqlexception caught
      catch (SQLException se)
      {
        new Warning("Error receiving times!");
      }
      //ensures the entered ghin is valid
      catch (NumberFormatException nfe)
      {
        new Warning("Invalid GHIN entered!");
      }
    }
    
    
    //if addButton pressed player and times added to db
    else if(command == updateButton)
    {
      try
      {
        //declaring components to access database and hold data
        String dbQuery = "";
        String dbName = "Golf";
        JavaDatabase dbObj = new JavaDatabase(dbName);
        Connection myDbConn = dbObj.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        int rankSum = 0;
        int neededSum = 0;
        ArrayList<String> teeTimes = new ArrayList<>();
        ghin = Integer.parseInt(ghinField.getText());
        
        for (int i=0; i<addedDays.size(); i++)
        {
          //adds players to monday if monday selected
          if (addedDays.get(i) == "Monday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<monLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(monFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where monday added
            for (int j=0; j<monLabelArray.length; j++)
            {
              dbQuery = "UPDATE Monday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(monFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(monLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
          //adds players to tuesday if tuesday selected
          else if (addedDays.get(i) == "Tuesday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<tueLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(tueFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where tuesday added
            for (int j=0; j<tueLabelArray.length; j++)
            {
              dbQuery = "UPDATE Tuesday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(tueFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(tueLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
          //adds players to wednesday if wednesday selected
          else if (addedDays.get(i) == "Wednesday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<wedLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(wedFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where wednesday added
            for (int j=0; j<wedLabelArray.length; j++)
            {
              dbQuery = "UPDATE Wednesday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(wedFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(wedLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
          //adds players to wednesday if wednesday selected
          else if (addedDays.get(i) == "Thursday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<thuLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(thuFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where thursday added
            for (int j=0; j<thuLabelArray.length; j++)
            {
              dbQuery = "UPDATE Thursday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(thuFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(thuLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
          //adds players to friday if friday selected
          else if (addedDays.get(i) == "Friday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<friLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(friFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where friday added
            for (int j=0; j<friLabelArray.length; j++)
            {
              dbQuery = "UPDATE Friday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(friFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(friLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
          //adds players to saturday if saturday selected
          else if (addedDays.get(i) == "Saturday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<satLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(satFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where saturday added
            for (int j=0; j<satLabelArray.length; j++)
            {
              dbQuery = "UPDATE Saturday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(satFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(satLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
          //adds players to sunday if sunday selected
          else if (addedDays.get(i) == "Sunday")
          {
            rankSum = 0;
            neededSum = 0;
            //verifies that ranks are entered sequentially
            for (int j=0; j<sunLabelArray.length; j++)
            {
              rankSum = rankSum + Integer.parseInt(sunFieldArray[j].getText());
              neededSum = neededSum + (j+1);
            }
            if (rankSum != neededSum)
            {
              throw new UnsupportedOperationException();
            }
            //updates tee time prefs where sunday added
            for (int j=0; j<sunLabelArray.length; j++)
            {
              dbQuery = "UPDATE Sunday SET teeTimeRanking = ? WHERE teeTime = ? AND ghin = ?";
              PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
              ps.setInt(1, Integer.parseInt(sunFieldArray[j].getText()));
              ps.setInt(2, Integer.parseInt(sunLabelArray[j].getText()));
              ps.setInt(3, ghin);
              ps.executeUpdate();
            }
          }
        }
        instructionLabel.setText("Success!");
      }
      //pops warning for sql exception
      catch (SQLException se)
      {
        new Warning("Error adding players!");
      }
      //pops warning for number format exception
      catch (NumberFormatException nfe)
      {
        new Warning("The GHIN and rankings should contain only integers!");
      }
      //pops warning when ranks not entered sequentially
      catch (UnsupportedOperationException uoe)
      {
        new Warning("Ranks not entered sequentially!");
      }
    }
    this.validate();
    this.repaint();
  }
}