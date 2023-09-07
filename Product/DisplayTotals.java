/*This frame displays all people registered for a week
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class DisplayTotals extends JFrame implements ActionListener
{
  //declaring components of the frame
  private JPanel buttonPanel;
  private JButton backButton;
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  private JMenuItem homeItem;
  private JTable dbTable;
  private JTableHeader header;
  private JScrollPane scrollTable;
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  
  public DisplayTotals(String dbName, String tableName, String[] columnNames)
  {
    //initializing the frame
    super("Weekly Roster");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.ROSTER_COLOR);
    this.setLayout(new BorderLayout());
    
    //creating object of javadatabase allows us to get the data
    JavaDatabase objDb = new JavaDatabase(dbName);
    dataList = objDb.getAllData(tableName, columnNames);
    //transfers the data from an arraylist to a 2d array
    data = objDb.to2dArray(dataList);
    //constructing table and components and inserting data
    this.dbTable = new JTable(data, columnNames);
    dbTable.setBackground(Welcome.ROSTER_COLOR);
    header = dbTable.getTableHeader();
    header.setBackground(Welcome.BUTTON_PANEL_COLOR);
    header.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
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
    
    //constructing and adding backButton to buttonPanel
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(backButton);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.CENTER);
    
    //setting menubar and frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    String dbName = "Golf";
    String tableName = "DailyTotal";
    String[] columnNames = {"day", "totalPlayers", "playersAdded", 
      "requiredTeeTimes","addedTeeTimes"};
    new DisplayTotals(dbName, tableName, columnNames);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of event
    Object command = e.getSource();
    //if help is pressed help frame launched
    if (command == helpItem)
    {
      new Help("This frame displays the daily totals roster.");
    }
    //if exit is pressed system exited
    else if(command == exitItem)
    {
      System.exit(0);
    }
    //if home is pressed welcome opened
    else if(command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //if back is pressed manage rosters is opened
    else if(command == backButton)
    {
      new ManageRosters();
      this.dispose();
    }
    this.validate();
    this.repaint();
  }
}