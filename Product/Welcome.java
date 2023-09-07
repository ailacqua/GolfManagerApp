/*This frame welcomes the user to the project and is the home page
 */
//package golfmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class Welcome extends JFrame implements ActionListener
{
  //declaring colors
  public static final Color BUTTON_PANEL_COLOR = new Color(217,234,211);
  public static final Color ROSTER_COLOR = new Color(217,210,233);
  public static final Color SCHEDULE_COLOR = new Color(252,229,205);
  public static final Color SCORE_COLOR = new Color(201,218,248);
  public static final Color POP_COLOR = new Color(244,204,204);
  public static final Font HEADER_FONT = new Font("Kefa", Font.BOLD, 45);
  public static final Font BODY_FONT = new Font("Trebuchet MS", Font.PLAIN, 20);
  //declaring image
  public final URL WELCOME_PATH = getClass().getResource("welcome.jpeg");
  private final ImageIcon WELCOME_IMAGE = new ImageIcon(new ImageIcon
                    (WELCOME_PATH).getImage().getScaledInstance
                    (600,433,Image.SCALE_DEFAULT));
  //declaring frame GUI components
  private JButton rosterButton;
  private JButton scheduleButton;
  private JButton scoreButton;
  private JButton newButton;
  private JLabel welcomeLabel;
  private JLabel welcomeImage;
  private JLabel instructionLabel;
  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem helpItem;
  // constructor for class
  public Welcome()
  {
    //initializing the frame
    super("Welcome");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(ROSTER_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing JLabels and setting font
    this.welcomeLabel = new JLabel("Golf Manager", SwingConstants.CENTER);
    welcomeLabel.setFont(HEADER_FONT);
    this.instructionLabel = new JLabel("To begin, click schedule and then enter "
        + "total players.", SwingConstants.CENTER);
    instructionLabel.setFont(BODY_FONT);
    this.welcomeImage = new JLabel(WELCOME_IMAGE);
    //constructing JButtons, and adding actionlistener
    this.rosterButton = new JButton("Manage Rosters");
    rosterButton.addActionListener(this);
    this.scheduleButton = new JButton("Manage Schedule");
    scheduleButton.addActionListener(this);
    this.scoreButton = new JButton("Manage Scores");
    scoreButton.addActionListener(this);
    this.newButton = new JButton("Create New Week");
    newButton.addActionListener(this);
    
    //constructing panels and adding buttons and labels to them
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(BUTTON_PANEL_COLOR);
    buttonPanel.add(rosterButton);
    buttonPanel.add(scheduleButton);
    buttonPanel.add(scoreButton);
    buttonPanel.add(newButton);
    this.centerPanel = new JPanel(new FlowLayout());
    centerPanel.setBackground(ROSTER_COLOR);
    centerPanel.add(welcomeImage);
    centerPanel.add(instructionLabel);
    
    //constructing menu components and adding them
    this.mainBar = new JMenuBar();
    this.mainMenu = new JMenu("Menu");
    this.helpItem = new JMenuItem("Help");
    this.exitItem = new JMenuItem("Exit");
    mainMenu.add(helpItem);
    helpItem.addActionListener(this);
    mainMenu.add(exitItem);
    exitItem.addActionListener(this);
    mainBar.add(mainMenu);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(welcomeLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    
    //adding mainBar to frame and setting frame visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test the class
  public static void main(String[] args)
  {
    new Welcome();
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of action
    Object command = e.getSource();
    //opens help if help pressed
    if (command == helpItem)
    {
      new Help("<html><center>Select any of the functions below to use the program. "
          + "  To start: schedule>enter total players. <br>Add tee times after this. "
          + "  Then add players.</html></center>");
    }
    //exits if exit pressed
    else if (command == exitItem)
    {
      System.exit(0);
    }
    //opens manage rosters if manage rosters pushed
    else if (command == rosterButton)
    {
      new ManageRosters();
      this.dispose();
    }
    //opens manage schedule if manage schedule pressed
    else if (command == scheduleButton)
    {
      new ManageSchedule();
      this.dispose();
    }
    //opens manage scores if manage score presseed
    else if (command == scoreButton)
    {
      new ManageScores();
      this.dispose();
    }
    //asks user if they want to creeate a new week if new week pressed
    else if (command == newButton)
    {
      new Warning("newWeek");
    }
    this.validate();
    this.repaint();
  }
}