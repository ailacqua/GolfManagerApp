/*This frame allows the user to view all the tee times they have entered
 */
//package golfmanager;

import java.awt.BorderLayout;
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

public class DisplayTimes extends JFrame implements ActionListener
{

  //declaring radiobuttons, buttons, panels, and labels for frame
  private JRadioButton monButton;
  private JRadioButton tueButton;
  private JRadioButton wedButton;
  private JRadioButton thuButton;
  private JRadioButton friButton;
  private JRadioButton satButton;
  private JRadioButton sunButton;
  private ButtonGroup dayGroup;
  private JButton backButton;
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
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;

  public DisplayTimes(String dbName, String tableName, String[] columnNames)
  {
    //initializing the frame
    super("View Tee Times");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.ROSTER_COLOR);
    this.setLayout(new BorderLayout());

    //constructing table and components and adding data
    Object[][] data = new Object[1][3];
    this.dbTable = new JTable(data, columnNames);
    dbTable.setBackground(Welcome.ROSTER_COLOR);
    header = dbTable.getTableHeader();
    header.setBackground(Welcome.BUTTON_PANEL_COLOR);
    header.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
    dbTable.setRowHeight(25);

    //putting table into scroll pane
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

    //constructing JButtons, adding to panel, and adding actionlistener
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

  //main method to test frame
  public static void main(String[] args)
  {
    String dbName = "";
    String tableName = "";
    String columnNames[] =
    {
      "teeTimeId", "Day", "Tee Time"
    };
    new DisplayTimes(dbName, tableName, columnNames);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of action
    Object command = e.getSource();
    String tableName = "TeeTime";
    String dbName = "Golf";
    String day = "";
    String[] columnNames =
    {
      "teeTimeId", "teeTimeIndex", "teeTime"
    };
    String dbQuery = "";
    //launches help if help pressed
    if (command == helpItem)
    {
      new Help("This frame is used to view tee times. Select a day at the bottom "
          + "to view the times for that day.");
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
    //launches manage schedule if back pressed
    else if (command == backButton)
    {
      new ManageSchedule();
      this.dispose();
    }
    //displays monday if monday selected
    else if (monButton.isSelected())
    {
      day = "Monday";
    }
    //displays tuesday if tuesday selected
    else if (tueButton.isSelected())
    {
      day = "Tuesday";
    }
    //displays wednesday if wednesday selected
    else if (wedButton.isSelected())
    {
      day = "Wednesday";
    }
    //displays thursday if thursday selected
    else if (thuButton.isSelected())
    {
      day = "Thursday";
    }
    //displays friday if friday selected
    else if (friButton.isSelected())
    {
      day = "Friday";
    }
    //displays saturday if saturday selected
    else if (satButton.isSelected())
    {
      day = "Saturday";
    }
    //displays sunday if sunday selected
    else if (sunButton.isSelected())
    {
      day = "Sunday";
    }

    if (!day.equals(""))
    {
      //removes components from day table
      this.remove(scrollTable);
      scrollTable.remove(dbTable);
      //creating object of javadatabase allows us to get the data
      JavaDatabase objDb = new JavaDatabase(dbName);
      //gets tee time info from database
      dbQuery = "SELECT teeTimeId, teeTimeIndex, teeTime FROM TeeTime WHERE day = '" 
          + day + "'";
      dataList = objDb.getData(columnNames, dbQuery);
      //transfers the data from an arraylist to a 2d array
      data = objDb.to2dArray(dataList);
      //adds data to  JTable
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
    this.validate();
    this.repaint();
  }
}