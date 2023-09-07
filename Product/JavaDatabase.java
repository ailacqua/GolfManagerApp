/*This class contains the necessary functions to connect the program to the database
 */
//package golfmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaDatabase
{
  //declaring the dbname, conn, and data
  private String dbName;
  private Connection dbConn;
  private ArrayList<ArrayList<String>> data;
  
  //constructor for when dbname is established
  public JavaDatabase(String dbName)
  {
    setDbName(dbName);
    setDbConn();
    data = null;
  }
  
  //constructor for when there is no db prev established
  public JavaDatabase()
  {
    dbName = "";
    dbConn = null;
    data = null;
  }
  
  //gets the name of the db
  public String getDbName()
  {
    return this.dbName;
  }
  //sets the name of the db
  public void setDbName(String dbName)
  {
    this.dbName = dbName;
  }
  //gets the dbconn
  public Connection getDbConn()
  {
    return this.dbConn;
  }
  //sets the dbconn
  public void setDbConn()
  {
    String connectionURL = "jdbc:derby:" + this.dbName;
    this.dbConn = null;
    try
    {
      //uses forname to set the connection and establish db conn
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
    }
    //warns the user of sql connection error
    catch (SQLException se)
    {
      new Warning("SQL connection error!");
    }
    //warns the user if the driver isnt found
    catch (ClassNotFoundException ex)
    {
      new Warning("Database driver not found!");
    }
  }
  //method gets the data from the database and stores it in an arraylist of arraylists
  public ArrayList<ArrayList<String>> getAllData(String tableName, String[] columnNames)
  {
    //declaring the statement, resultset, dbquery, and data
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName;
    this.data = new ArrayList<>();
    
    //try increases robustness of the program
    try
    {
      //creates statement and generates a resultset by selecting database
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);
      //while there is a next piece of data
      while(rs.next())
      {
        
        ArrayList<String> row = new ArrayList<>();
        //each cell in a row is read and added to row
        for (int i=0; i<columnNames.length; i++)
        {
          String cell = rs.getString(columnNames[i]);
          row.add(cell);
        }
        //after the row is exhausted the row is data to data arraylist
        this.data.add(row);
      }
    }
    //warns user if data couldn't be retrieved
    catch (SQLException se)
    {
      new Warning("Couldn't retrieve data!");
    }
    return data;
  }
  
  
  //methods gets data for a day
  public ArrayList<ArrayList<String>> getData(String[] columnNames, String dbQuery)
  {
    //declaring the statement, resultset, dbquery, and data
    Statement s = null;
    ResultSet rs = null;
    this.data = new ArrayList<>();
    
    //try increases robustness of the program
    try
    {
      //creates statement and generates a resultset by selecting database
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);
      //while there is a next piece of data
      while(rs.next())
      {
        
        ArrayList<String> row = new ArrayList<>();
        //each cell in a row is read and added to row
        for (int i=0; i<columnNames.length; i++)
        {
          String cell = rs.getString(columnNames[i]);
          row.add(cell);
        }
        //after the row is exhausted the row is data to data arraylist
        this.data.add(row);
      }
    }
    //warns user if data couldn't be retrieved
    catch (SQLException se)
    {
      new Warning("Couldn't retrieve data!");
    }
    return data;
  }
  
  //sets the data
  public void setData(ArrayList<ArrayList<String>> data)
  {
    this.data = data;
  }
  //closes the db connection
  public void closeDbConn()
  {
    try
    {
      this.dbConn.close();
    }
    //warns the userif the connection couldn't be closed
    catch (Exception e)
    {
      new Warning("Error closing DB connection");
    }
  }
  
  //creates a new db with db name
  public void createDb(String newDbName)
  {
    //dets db name and declares connection url and conn
    setDbName(newDbName);
    String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
    this.dbConn = null;
    try
    {
      //establishes the dbconn and creates a new db with dbname
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
    }
    //warns the user if the db wasnt made
    catch (SQLException se)
    {
      new Warning("SQL connection error, new DB not made!");
    }
    //warns the user if the driver was not found
    catch (ClassNotFoundException ex)
    {
      new Warning("Database driver not found!");
    }
  }
  
  //method creates a new table in a database
  public void createDailyTotalTable(String newTable, String dbName)
  {
    //setting the dbname, connection, and declaring statement
    setDbName(dbName);
    setDbConn();
    Statement s;
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    String dbQuery;
    try
    {
      //creates a new table and closes the db connection
      s = this.dbConn.createStatement();
      s.execute(newTable);
      //inserts the days into dailytotal table while there are more days
      for (int i=0; i<days.length; i++)
      {
        dbQuery = "INSERT INTO DailyTotal VALUES (?,?,?,?,?)";
        PreparedStatement ps = this.dbConn.prepareStatement(dbQuery);
        ps.setString(1, days[i]);
        ps.setInt(2, 0);
        ps.setInt(3, 0);
        ps.setInt(4, 0);
        ps.setInt(5, 0);
        ps.executeUpdate();
      }
      //closes dbconncection
      this.dbConn.close();
    }
    //catches errors associated with running the table
    catch (SQLException se)
    {
    }
  }
  
  //method creates a new table in a database
  public void createTable(String newTable, String dbName)
  {
    //setting the dbname, connection, and declaring statement
    setDbName(dbName);
    setDbConn();
    Statement s;
    try
    {
      //creates a new table and closes the db connection
      s = this.dbConn.createStatement();
      s.execute(newTable);
      this.dbConn.close();
    }
    //catches exceptions in the event that the table isnt created
    catch (SQLException se)
    {
    }
  }
  
  //method converts array list of array list to 2d array
  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    //returns empty 2d array id arraylist is empty
    if(data.size() == 0)
    {
      Object[][] dataArray = new Object[0][0];
      return dataArray;
    }
    else
    {
      //declares the column count of the array list, and the 2d array 
      int columnCount = data.get(0).size();
      Object[][] dataArray = new Object[data.size()][columnCount];
      //while there are more rows arraylists in the data arraylist
      for(int r = 0; r < data.size(); r++)
      {
        //gets a row located out of the arraylist of rows
        ArrayList<String> row = data.get(r);
        //reads the value at each column into the array
        for(int c=0; c < columnCount; c++)
        {
          //sets the array at row r to the data at column c of row r
          dataArray[r][c] = row.get(c);
        }
      }
      //returns the dataArray
      return dataArray;
    }
  }
  
  //main method tests the class for success
  public static void main(String[] args)
  {
    // declaring db info
    String dbName = "Golf";
    String tableName = "WeekRoster";
    String[] columnNames = {"registrationId","ghin","lastName","firstName", "day"};
    String dbQuery = "INSERT INTO WeekRoster VALUES (?,?,?,?,?)";
    //setting and getting db connection
    JavaDatabase dbObj = new JavaDatabase(dbName);
    Connection myDbConn = dbObj.getDbConn();
    // declaring values to insert
    int ghin = 1234567;
    String lastName = "Smith";
    String firstName = "Bob";
    int registrationId = 11234567;
    String day = "Monday";
    try
    {
        // declaring prepared statement
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        // entering data into the query
        ps.setInt(1, registrationId);
        ps.setInt(2, ghin);
        ps.setString(3, lastName);
        ps.setString(4, firstName);
        ps.setString(5, day);
        ps.executeUpdate();
        System.out.println("Data inserted successfully!");
    }
    //warns the user of sqlexception
    catch (SQLException se)
    {
        System.out.println("Error inserting data!");
    }
    ArrayList<ArrayList<String>> myData = dbObj.getAllData(tableName, columnNames);
    System.out.println(myData);
  }
}