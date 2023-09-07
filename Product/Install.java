/*class to download the database and create tables
 */
//package golfmanager;

public class Install
{
  public static void main(String[] args)
  {
    try
    {
      //declaring the database name and queries to create tables
      String dbName = "Golf";
      String weekRosterTable = "CREATE TABLE WeekRoster (registrationId int, "
          + "ghin int, lastName varchar(100), firstName varchar(100), day varchar(9))";
      String weekResultTable = "CREATE TABLE WeekResult (ghin int, lastName varchar(100), "
          + "firstName varchar(100), monday int, tuesday int, wednesday int, "
          + "thursday int, friday int, saturday int, sunday int, timesPlayed int, "
          + "totalScore int, averageScore double, lowestScore int, highestScore int)";
      String mondayTable = "CREATE TABLE Monday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String tuesdayTable = "CREATE TABLE Tuesday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String wednesdayTable = "CREATE TABLE Wednesday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String thursdayTable = "CREATE TABLE Thursday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String fridayTable = "CREATE TABLE Friday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String saturdayTable = "CREATE TABLE Saturday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String sundayTable = "CREATE TABLE Sunday (playTimeId int, ghin int, "
          + "lastName varchar(100), firstName varchar(100), teeTimeIndex int, "
          + "teeTimeRanking int, teeTime int, finalTeeTime int)";
      String dailyTotalTable = "CREATE TABLE DailyTotal (day varchar(10), "
          + "totalPlayers int, playersAdded int, requiredTeeTimes int, addedTeeTimes int)";
      String teeTimeTable = "CREATE TABLE TeeTime (teeTimeId varchar(4), "
          + "day varchar(9), teeTimeIndex int, teeTime int)";
      //creating object of JavaDatabase to create tables
      JavaDatabase dbObj = new JavaDatabase();
      //creating the db
      dbObj.createDb(dbName);
      //creating all tables
      dbObj.createTable(weekRosterTable, dbName);
      dbObj.createTable(weekResultTable, dbName);
      dbObj.createTable(mondayTable, dbName);
      dbObj.createTable(tuesdayTable, dbName);
      dbObj.createTable(wednesdayTable, dbName);
      dbObj.createTable(thursdayTable, dbName);
      dbObj.createTable(fridayTable, dbName);
      dbObj.createTable(saturdayTable, dbName);
      dbObj.createTable(sundayTable, dbName);
      dbObj.createDailyTotalTable(dailyTotalTable, dbName);
      dbObj.createTable(teeTimeTable, dbName);
    }
    //pops warning if exception thrown
    catch (Exception e)
    {
      new Warning("Error starting the program!");
    }
  }
}
