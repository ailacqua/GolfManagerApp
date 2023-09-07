/*This frame provides the user with the option to add delete scores and generate
a score report
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

public class ManageScores extends JFrame implements ActionListener
{
  //declaring image
  public final URL SCORES_PATH = getClass().getResource("scores.jpeg");
  private final ImageIcon SCORES_IMAGE = new ImageIcon(new ImageIcon
                    (SCORES_PATH).getImage().getScaledInstance
                    (630,420,Image.SCALE_DEFAULT));
  //declaring buttons, panels, and labels for frame
  private JButton addButton;
  private JButton deleteButton;
  private JButton viewButton;
  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JLabel instructionLabel;
  private JLabel imageLabel;
  private JLabel headerLabel;
  //declaring menu components
  private JMenuBar mainBar;
  private JMenu mainMenu;
  private JMenuItem exitItem;
  private JMenuItem homeItem;
  
  public ManageScores()
  {
    //initializing the frame
    super("Manage Scores");
    this.setBounds(200, 200, 1000, 700);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.SCORE_COLOR);
    this.setLayout(new BorderLayout());
    
    //constructing JButtons and adding action listener
    this.addButton = new JButton("Add Scores");
    addButton.addActionListener(this);
    this.deleteButton = new JButton("Delete Scores");
    deleteButton.addActionListener(this);
    this.viewButton = new JButton("Generate Score Report");
    viewButton.addActionListener(this);
    
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
    
    //constructing JLabels and setting font
    this.imageLabel = new JLabel(SCORES_IMAGE);
    this.headerLabel = new JLabel("Manage Scores", SwingConstants.CENTER);
    headerLabel.setFont(Welcome.HEADER_FONT);
    this.instructionLabel = new JLabel("Use the buttons below to add and delete "
        + "scores, and see score reports.", SwingConstants.CENTER);
    instructionLabel.setFont(Welcome.BODY_FONT);

    //constructing buttonPanel and adding buttons to panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.BUTTON_PANEL_COLOR);
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(viewButton); 
    this.centerPanel = new JPanel(new FlowLayout());
    centerPanel.setBackground(Welcome.SCORE_COLOR);
    centerPanel.add(imageLabel);
    centerPanel.add(instructionLabel);
    
    //adding components to frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(headerLabel, BorderLayout.NORTH);
    
    //setting JMenuBar and frame to visible
    this.setJMenuBar(mainBar);
    this.setVisible(true);
  }
  //main method to test class
  public static void main(String[] args)
  {
    new ManageScores();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //gets source of command
    Object command = e.getSource();
    //exits program if exit pressed
    if(command == exitItem)
    {
      System.exit(0);
    }
    //launches welcome if home pressed
    else if(command == homeItem)
    {
      new Welcome();
      this.dispose();
    }
    //launches add score if add score pressed
    else if(command == addButton)
    {
      new AddScore();
      this.dispose();
    }
    //launches delete score if delete score pressed
    else if(command == deleteButton)
    {
      new DeleteScore();
      this.dispose();
    }
    //launches score report if generate score report pressed
    else if(command == viewButton)
    {
      String dbName = "Golf";
      String tableName = "WeekResult"; 
      String[] columnNames = {"ghin", "lastName", "firstName", "monday", "tuesday", 
          "wednesday", "thursday", "friday", "saturday", "sunday", "timesPlayed", 
          "totalScore", "averageScore", "lowestScore", "highestScore"};
      new ScoreReport(dbName, tableName, columnNames);
      this.dispose();
    }
    this.validate();
    this.repaint();
  }
}