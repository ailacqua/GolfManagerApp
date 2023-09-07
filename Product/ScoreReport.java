/*This frame displays the weekly scores
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class ScoreReport extends JFrame implements ActionListener
{
  //declaring panels, buttons, labels for frame
  private JPanel buttonPanel;
  private JRadioButton highButton;
  private JRadioButton lowButton;
  private JRadioButton averageButton;
  private JRadioButton ghinButton;
  private ButtonGroup buttonGroup;
  private JButton backButton;
  private JLabel sortLabel;
  //declaring menu items
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  private JTable dbTable;
  private JTableHeader header;
  private JScrollPane scrollTable;
  //declaring items for data addition
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  
  public ScoreReport(String dbName, String tableName, String[] columnNames)
  {
    //initializing the frame
    super("Score Report");
    this.setBounds(200, 200, 1200, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Color.LIGHT_GRAY);
    this.setLayout(new BorderLayout());
    
    //creating object of javadatabase allows us to get the data
    JavaDatabase objDb = new JavaDatabase(dbName);
    dataList = objDb.getAllData(tableName, columnNames);
    //transfers the data from an arraylist to a 2d array
    data = objDb.to2dArray(dataList);
    //constructing table and components
    this.dbTable = new JTable(data, columnNames);
    dbTable.setBackground(Welcome.ROSTER_COLOR);
    header = dbTable.getTableHeader();
    header.setBackground(Welcome.BUTTON_PANEL_COLOR);
    header.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
    dbTable.setRowHeight(25);
    
    // put table into scroll pane
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
    
    //constructing JButtons and adding ActionListener
    this.highButton = new JRadioButton("Highest");
    highButton.addActionListener(this);
    this.lowButton = new JRadioButton("Lowest");
    lowButton.addActionListener(this);
    this.averageButton = new JRadioButton("Average");
    averageButton.addActionListener(this);
    this.ghinButton = new JRadioButton("GHIN");
    ghinButton.addActionListener(this);
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);
    
    //constructing JLabels
    this.sortLabel = new JLabel("Sort by:");
    
    //Constructing buttonPanel and adding buttons
    this.buttonGroup = new ButtonGroup();
    buttonGroup.add(highButton);
    buttonGroup.add(lowButton);
    buttonGroup.add(averageButton);
    buttonGroup.add(ghinButton);
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(sortLabel);
    buttonPanel.add(highButton);
    buttonPanel.add(lowButton);
    buttonPanel.add(averageButton);
    buttonPanel.add(ghinButton);
    buttonPanel.add(backButton);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.CENTER);
    
    //setting menu bar and frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  
  public static void main(String[] args)
  {
    String dbName = "Golf";
    String tableName = "WeekResult"; 
    String[] columnNames = {"ghin", "lastName", "firstName", "monday", "tuesday", 
        "wednesday", "thursday", "friday", "saturday", "sunday", "timesPlayed", 
        "totalScore", "averageScore", "lowestScore", "highestScore"};
    new ScoreReport(dbName, tableName, columnNames);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of command
    Object command = e.getSource();
    //declaring variables to interact with database
    String sortType = "";
    String dbName = "Golf";
    String tableName = "WeekResult"; 
    String[] columnNames = {"ghin", "lastName", "firstName", "monday", "tuesday", 
          "wednesday", "thursday", "friday", "saturday", "sunday", "timesPlayed", 
          "totalScore", "averageScore", "lowestScore", "highestScore"};
    //exits program if exit pressed
    if (command == exitItem)
    {
      System.exit(0);
    }
    //opens welcome if home pressed
    else if (command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //opens help if help pressed
    else if (command == helpItem)
    {
      new Help("This panel shows the score report. To sort the panel, "
          + "select one of the options at the bottom.");
    }
    //opens manage scores if back pressed
    else if (command == backButton)
    {
      new ManageScores();
      this.dispose();
    }
    //sorts scores from lowest to highest if lowest selected
    else if (lowButton.isSelected())
    {
      //try catches exceptions
      try
      {
        sortType = "lowest";
        //declaring vars to access table
        JavaDatabase objDb = new JavaDatabase(dbName);
        //removing components from frame
        this.remove(scrollTable);
        scrollTable.remove(dbTable);
        //getting all data from the weekly roster
        dataList = objDb.getAllData(tableName, columnNames);
        //sorting the scores based on parameter
        SortScores sortObj = new SortScores(dataList, sortType);
        dataList = sortObj.getSortedScores();
        //transfers data from arraylist to 2d object array
        data = objDb.to2dArray(dataList);
        //constructing and adding data to Jtable
        this.dbTable = new JTable(data, columnNames);
        dbTable.setBackground(Welcome.ROSTER_COLOR);
        header = dbTable.getTableHeader();
        header.setBackground(Welcome.BUTTON_PANEL_COLOR);
        header.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        dbTable.setRowHeight(25);
    
        // put table into scroll pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //adding scrollPane to frame
        this.add(scrollTable, BorderLayout.CENTER);
      }
      //pops warning if error displaying scores
      catch (Exception se)
      {
        new Warning("Error displaying lowest scores!");
      }
    }
    //sorts scores from highest to lowest if highest selected
    else if (highButton.isSelected())
    {
      try
      {
        sortType = "highest";
        //declaring vars to access table
        JavaDatabase objDb = new JavaDatabase(dbName);
        //removing components from frame
        this.remove(scrollTable);
        scrollTable.remove(dbTable);
        //getting all data from the weekly roster
        dataList = objDb.getAllData(tableName, columnNames);
        //sorting the scores based on parameter
        SortScores sortObj = new SortScores(dataList, sortType);
        dataList = sortObj.getSortedScores();
        //transfers data from arraylist to 2d object array
        data = objDb.to2dArray(dataList);
        //constructing and adding data to Jtable
        this.dbTable = new JTable(data, columnNames);
        dbTable.setBackground(Welcome.ROSTER_COLOR);
        header = dbTable.getTableHeader();
        header.setBackground(Welcome.BUTTON_PANEL_COLOR);
        header.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        dbTable.setRowHeight(25);
    
        // put table into scroll pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //adding scrollPane to frame
        this.add(scrollTable, BorderLayout.CENTER);
      }
      //pops warning if error displaying scores
      catch (Exception se)
      {
        new Warning("Error displaying highest scores!");
      }
    }
    //sorts averages lowest to highest if average selected
    else if (averageButton.isSelected())
    {
      try
      {
        sortType = "average";
        //declaring vars to access table
        JavaDatabase objDb = new JavaDatabase(dbName);
        //removing components from frame
        this.remove(scrollTable);
        scrollTable.remove(dbTable);
        //getting all data from the weekly roster
        dataList = objDb.getAllData(tableName, columnNames);
        //sorting the scores based on parameter
        SortScores sortObj = new SortScores(dataList, sortType);
        dataList = sortObj.getSortedScores();
        //transfers data from arraylist to 2d object array
        data = objDb.to2dArray(dataList);
        //constructing and adding data to Jtable
        this.dbTable = new JTable(data, columnNames);
        dbTable.setBackground(Welcome.ROSTER_COLOR);
        header = dbTable.getTableHeader();
        header.setBackground(Welcome.BUTTON_PANEL_COLOR);
        header.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        dbTable.setRowHeight(25);
    
        // put table into scroll pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //adding scrollPane to frame
        this.add(scrollTable, BorderLayout.CENTER);
      }
      //pops warning if error displaying scores
      catch (Exception se)
      {
        new Warning("Error displaying scores by average!");
      }
    }
    //sorts scores by ghin lowest to highest
    else if (ghinButton.isSelected())
    {
      try
      {
        sortType = "ghin";
        //declaring vars to access table
        JavaDatabase objDb = new JavaDatabase(dbName);
        //removing components from frame
        this.remove(scrollTable);
        scrollTable.remove(dbTable);
        //getting all data from the weekly roster
        dataList = objDb.getAllData(tableName, columnNames);
        //sorting the scores based on parameter
        SortScores sortObj = new SortScores(dataList, sortType);
        dataList = sortObj.getSortedScores();
        //transfers data from arraylist to 2d object array
        data = objDb.to2dArray(dataList);
        //constructing and adding data to Jtable
        this.dbTable = new JTable(data, columnNames);
        dbTable.setBackground(Welcome.ROSTER_COLOR);
        header = dbTable.getTableHeader();
        header.setBackground(Welcome.BUTTON_PANEL_COLOR);
        header.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        dbTable.setRowHeight(25);
    
        // put table into scroll pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //adding scrollPane to frame
        this.add(scrollTable, BorderLayout.CENTER);
      }
      //pops warning if error displaying scores
      catch (Exception ex)
      {
        new Warning("Error displaying scores by GHIN!");
      }
    }
    this.validate();
    this.repaint();
  }
}