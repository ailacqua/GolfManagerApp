/*This class displays the daily roster for each tableName
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Connection;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class DisplayPreferences extends JFrame implements ActionListener
{
  //declaring radiobuttons, buttons, labels, and panels for frame
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private JButton backButton;
  private ButtonGroup dayGroup;
  private JPanel buttonPanel;
  private JLabel dayLabel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  //declaring table components
  private JTable dbTable;
  private JTableHeader header;
  private JScrollPane scrollTable;
  private ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
  private Object[][] data;
  
  public DisplayPreferences(String dbName, String tableName, String[] columnNames)
  {
    //initializing the frame
    super("Tee Time Preferences");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.ROSTER_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing table and components and adding data to table
    this.data = new Object[1][8];
    this.dbTable = new JTable(data, columnNames);
    dbTable.setBackground(Welcome.ROSTER_COLOR);
    header = dbTable.getTableHeader();
    header.setBackground(Welcome.BUTTON_PANEL_COLOR);
    header.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
    dbTable.setRowHeight(25);
    
    //put table into scroll pane
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);
    
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
    
    //constructing JLabels
    this.dayLabel = new JLabel("Select Day:");
    
    //constructing buttons, adding to panel, and adding actionlistener
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
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);
    this.dayGroup = new ButtonGroup();
    dayGroup.add(monButton);
    dayGroup.add(tueButton);
    dayGroup.add(wedButton);
    dayGroup.add(thuButton);
    dayGroup.add(friButton);
    dayGroup.add(satButton);
    dayGroup.add(sunButton);
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(dayLabel);
    buttonPanel.add(monButton);
    buttonPanel.add(tueButton);
    buttonPanel.add(wedButton);
    buttonPanel.add(thuButton);
    buttonPanel.add(friButton);
    buttonPanel.add(satButton);
    buttonPanel.add(sunButton);
    buttonPanel.add(backButton);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.CENTER);
    
    //setting menu bar and frame to visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test the class
  public static void main(String[] args)
  {
    String dbName = "Golf";
    String tableName = "";
    String[] columnNames = {"playTimeId","ghin","lastName","firstName","teeTimeIndex",
      "teeTimeRanking","teeTime","finalTeeTime"};
    new DisplayPreferences(dbName, tableName, columnNames);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of command
    Object command = e.getSource();
    String tableName = "";
    String dbName = "Golf";
    String[] columnNames = {"playTimeId","ghin","lastName","firstName",
      "teeTimeIndex","teeTimeRanking","teeTime","finalTeeTime"};
    String dbQuery = "";
    int totalPlayers;
    int playersAdded;
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("This table displays the rosters for a specific day. To see the "
          + "roster, click the day at the bottom.");
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
    //launches manage rosters if back pressed
    else if (command == backButton)
    {
      new ManageRosters();
      this.dispose();
    }
    //displays monday if monday selected
    else if (monButton.isSelected())
    {
      tableName = "Monday";
    }
    //displays tuesday if tuesday selected
    else if (tueButton.isSelected())
    {
      tableName = "Tuesday";
    }
    //displays wednesday if wednesday selected
    else if (wedButton.isSelected())
    {
      tableName = "Wednesday";
    }
    //displays thursday if thursday selected
    else if (thuButton.isSelected())
    {
      tableName = "Thursday";
    }
    //displays friday if friday selected
    else if (friButton.isSelected())
    {
      tableName = "Friday";
    }
    //displays saturday if saturday selected
    else if (satButton.isSelected())
    {
      tableName = "Saturday";
    }
    //displays sunday if sunday selected
    else if (sunButton.isSelected())
    {
      tableName = "Sunday";
    }
    
    //if the table name is not nothing then day displayed
    if (!tableName.equals(""))
    {
      try
      {
        dataList.clear();
        //removes frame components
        this.remove(scrollTable);
        scrollTable.remove(dbTable);
        //creating object of javadatabase allows us to get the data
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();
        Statement s = myDbConn.createStatement();
        ResultSet rs = null;
        //ensures that the total players is equal to the players addeed
        dbQuery = "SELECT totalPlayers, playersAdded FROM DailyTotal WHERE day = '" + tableName + "'";
        rs = s.executeQuery(dbQuery);
        rs.next();
        playersAdded = rs.getInt("playersAdded");
        totalPlayers = rs.getInt("totalPlayers");
        if (playersAdded != totalPlayers)
        {
          throw new Error();
        }
        //gets all the data from the table
        dataList = objDb.getAllData(tableName, columnNames);
        //transfers the data from an arraylist to a 2d array
        data = objDb.to2dArray(dataList);
        //adds data, table, scroll pane to frame
        this.dbTable = new JTable(data, columnNames);
        dbTable.setBackground(Welcome.ROSTER_COLOR);
        header = dbTable.getTableHeader();
        header.setBackground(Welcome.BUTTON_PANEL_COLOR);
        header.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        dbTable.setRowHeight(25);

        //put table into scroll pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        this.add(scrollTable, BorderLayout.CENTER);
      }
      //pops warning when sqlexception thrown
      catch (SQLException se)
      {
        new Warning("Error displaying day!");
      }
      //pops warning if totalPlayers is not equal to added
      catch (Error er)
      {
        new Warning("Be sure the total players equals the added players!");
      }
      //catches index out of bounds exception when no players added
      catch (IndexOutOfBoundsException iob)
      {
        new Warning("No players added to this day!");
      }
    }
    this.validate();
    this.repaint();
  }
}