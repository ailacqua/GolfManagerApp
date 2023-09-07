/*This class allows the user to add, edit, and delete players and view rosters
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ManageRosters extends JFrame implements ActionListener
{
  //declaring roster image
  public final URL ROSTER_PATH = getClass().getResource("roster.jpeg");
  private final ImageIcon ROSTER_IMAGE = new ImageIcon(new ImageIcon
                    (ROSTER_PATH).getImage().getScaledInstance
                    (854,456,Image.SCALE_DEFAULT));
  //declaring gui frame components
  private JButton addButton;
  private JButton editButton;
  private JButton deleteButton;
  private JButton viewButton;
  private JButton dailyButton;
  private JButton totalButton;
  private JButton prefButton;
  private JLabel instructionLabel;
  private JLabel rostersImage;
  private JLabel headingLabel;
  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem homeItem;
  
  //creating a constructor for the frame
  public ManageRosters()
  {
    //initializing the frame
    super("Manage Rosters");
    this.setBounds(200, 200, 1025, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.ROSTER_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing JLabels and setting their font
    this.instructionLabel = new JLabel("Use the functions below to add, edit, "
        + "delete, or view players.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);
    this.rostersImage = new JLabel(ROSTER_IMAGE);
    this.headingLabel = new JLabel("Manage Rosters", SwingConstants.CENTER);
    headingLabel.setFont(Welcome.HEADER_FONT);
    
    //constructing JButtons and adding action listener
    this.addButton = new JButton("Add Player");
    addButton.addActionListener(this);
    this.editButton = new JButton("Edit Player");
    editButton.addActionListener(this);
    this.deleteButton = new JButton("Delete Player");
    deleteButton.addActionListener(this);
    this.viewButton = new JButton("View Weekly Roster");
    viewButton.addActionListener(this);
    this.dailyButton = new JButton("View Daily Rosters");
    dailyButton.addActionListener(this);
    this.totalButton = new JButton("View Daily Totals");
    totalButton.addActionListener(this);
    this.prefButton = new JButton("View Preferences");
    prefButton.addActionListener(this);
    
    //constructing panels and adding buttons and labels
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(editButton);
    buttonPanel.add(viewButton);
    buttonPanel.add(dailyButton);
    buttonPanel.add(totalButton);
    buttonPanel.add(prefButton);
    this.centerPanel = new JPanel(new FlowLayout());
    centerPanel.setBackground(Welcome.ROSTER_COLOR);
    centerPanel.add(rostersImage);
    centerPanel.add(instructionLabel);
    
    //constructing menu components and adding them
    this.mainBar = new JMenuBar();
    this.mainMenu = new JMenu("Menu");
    this.exitItem = new JMenuItem("Exit");
    this.homeItem = new JMenuItem("Home");
    mainMenu.add(exitItem);
    exitItem.addActionListener(this);
    mainMenu.add(homeItem);
    homeItem.addActionListener(this);
    mainBar.add(mainMenu);
    
    //adding buttonPanel to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(headingLabel, BorderLayout.NORTH);
    
    //adding mainBar to frame
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new ManageRosters();
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of action
    Object command = e.getSource();
    //exits if exit pressed
    if (command == exitItem)
    {
      System.exit(0);
    }
    //goes to welcome if home pressed
    else if (command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //goes to AddPlayer if add pressed
    else if (command == addButton)
    {
      new AddPlayer();
      this.dispose();
    }
    //goes to edit player if edit pressed
    else if (command == editButton)
    {
      new EditPlayer();
      this.dispose();
    }
    //goes to delete player if delete pressed
    else if (command == deleteButton)
    {
      new DeletePlayer();
      this.dispose();
    }
    //goes to view weekly roster if view weekly pressed
    else if (command == viewButton)
    {
      String dbName = "Golf";
      String tableName = "WeekRoster";
      String[] columnNames = {"registrationID", "ghin", "lastName", "firstName", "day"};
      new DisplayWeek(dbName, tableName, columnNames);
      this.dispose();
    }
    //goes to view daily roster if view daily pressed
    else if(command == dailyButton)
    {
      String dbName = "Golf";
      String tableName = "";
      String[] columnNames = {"playTimeId","ghin", "lastName", "firstName","teeTimeIndex", "teeTime"};
      new DisplayDay(dbName, tableName, columnNames);
      this.dispose();
    }
    //displays the DailyTotal table
    else if (command == totalButton)
    {
      String dbName = "Golf";
      String tableName = "DailyTotal";
      String[] columnNames = {"day", "totalPlayers", "playersAdded", 
        "requiredTeeTimes", "addedTeeTimes"};
      new DisplayTotals(dbName, tableName, columnNames);
      this.dispose();
    }
    else if (command == prefButton)
    {
      String dbName = "Golf";
      String tableName = "";
      String[] columnNames = {"playTimeId","ghin","lastName","firstName","teeTimeIndex",
        "teeTimeRanking","teeTime","finalTeeTime"};
      new DisplayPreferences(dbName, tableName, columnNames);
    }
    this.validate();
    this.repaint();
  }
}