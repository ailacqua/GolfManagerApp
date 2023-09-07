/*This class allows the user to edit and add tee times
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

public class ManageSchedule extends JFrame implements ActionListener
{
  //declaring image
  public final URL SCHEDULE_PATH = getClass().getResource("schedule.jpeg");
  private final ImageIcon SCHEDULE_IMAGE = new ImageIcon(new ImageIcon
                    (SCHEDULE_PATH).getImage().getScaledInstance
                    (747,432,Image.SCALE_DEFAULT));
  //declaring frame components
  private JButton totalButton;
  private JButton addButton;
  private JButton editButton;
  private JButton deleteButton;
  private JButton timesButton;
  private JLabel instructionLabel;
  private JLabel imageLabel;
  private JLabel headerLabel;
  private JPanel buttonPanel;
  private JPanel centerPanel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem homeItem;
  
  public ManageSchedule()
  {
    //initializing the frame
    super("Manage Schedule");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.SCHEDULE_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing buttons and adding action listener
    this.totalButton = new JButton("Enter Total Players");
    totalButton.addActionListener(this);
    this.addButton = new JButton("Add Tee Time");
    addButton.addActionListener(this);
    this.editButton= new JButton("Edit Tee Time");
    editButton.addActionListener(this);
    this.deleteButton = new JButton("Delete Tee Time");
    deleteButton.addActionListener(this);
    this.timesButton = new JButton("View Tee Times");
    timesButton.addActionListener(this);
    
    //constructing JLabels and setting font
    this.instructionLabel = new JLabel("Use the buttons below to add, edit, delete and view times and daily rosters.");
    instructionLabel.setFont(Welcome.BODY_FONT);
    this.imageLabel = new JLabel(SCHEDULE_IMAGE);
    this.headerLabel = new JLabel("Manage Schedule", SwingConstants.CENTER);
    headerLabel.setFont(Welcome.HEADER_FONT);
    
    //constructing JPanels and adding buttons and labels
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(totalButton);
    buttonPanel.add(addButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(timesButton);
    this.centerPanel = new JPanel(new FlowLayout());
    centerPanel.setBackground(Welcome.SCHEDULE_COLOR);
    centerPanel.add(imageLabel);
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
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(headerLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    
    //setting menubar and frame to be visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
    
  }
  //main method to test the class
  public static void main(String[] args)
  {
    new ManageSchedule();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets the source of the command
    Object command = e.getSource();
    //exits program if exit is pressed
    if(command == exitItem)
    {
      System.exit(0);
    }
    //launches welcome if home is pressed
    else if(command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //launches total players if enter total players pressed
    else if(command == totalButton)
    {
      new TotalPlayers();
      this.dispose();
    }
    //launches add time if add time pressed
    else if(command == addButton)
    {
      new AddTime();
      this.dispose();
    }
    //launches edit time if edit time pressed
    else if(command == editButton)
    {
      new EditTime();
      this.dispose();
    }
    //launches delete time if delete time pressed
    else if(command == deleteButton)
    {
      new DeleteTime();
      this.dispose();
    }
    //launches view times if view tee times pressed
    else if(command == timesButton)
    {
      String dbName = "";
      String tableName = "";
      String columnNames[] = {"Tee Time ID", "Tee Time Index", "Tee Time"};
      new DisplayTimes(dbName, tableName, columnNames);
      this.dispose();
    }
    this.validate();
    this.repaint();
  }
}