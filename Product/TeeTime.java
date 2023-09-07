/*This class finds the teeTimeindexes, needed times, and finds playtime ids
 */
//package golfmanager;

import java.util.ArrayList;

public class TeeTime
{
  //declaring datafields to hold data
  private String day;
  private int teeTimeIndex;
  private int time;
  private int playTimeId;
  private int ghin;
  private ArrayList<ArrayList<String>> unsortedIndex;
  private ArrayList<ArrayList<String>> sortedIndex;
  private int addedPlayers;
  private int neededTimes;
  
  //constructor with index and ghin
  public TeeTime(int teeTimeIndex, int ghin)
  {
    this.teeTimeIndex = teeTimeIndex;
    this.ghin = ghin;
  }
  //constructor with addedPlayers
  public TeeTime(int addedPlayers)
  {
    this.addedPlayers = addedPlayers;
  }
  //constructor with no parameters
  public TeeTime()
  {
    this.day = "";
    this.teeTimeIndex = 0;
    this.time = 0;
    this.unsortedIndex = new ArrayList<ArrayList<String>>();
    this.sortedIndex = new ArrayList<ArrayList<String>>();
  }
  
  //mutators and accessors to access encapsulated data
  public int getAddedPlayers()
  {
    return this.addedPlayers;
  }
  public void setAddedPlayers(int addedPlayers)
  {
    this.addedPlayers = addedPlayers;
  }
  public int getNeededTimes()
  {
    //if the remainder of addedplayers and 4 is 0 then times is players / 4
    if ((this.addedPlayers % 4) == 0)
    {
      this.neededTimes = this.addedPlayers / 4;
    }
    //if the remainder of addedplayers and 4 is 0 then times is players / 4 plus 1
    else 
    {
      this.neededTimes = (this.addedPlayers / 4) + 1;
    }
    return neededTimes;
  }

  public void setNeededTimes(int neededTimes)
  {
    this.neededTimes = neededTimes;
  }
  public int getPlayTimeId()
  {
    //shifts all numbers of ghin one place value left
    this.playTimeId = this.ghin * 10;
    //adds tee time index
    playTimeId = playTimeId + this.teeTimeIndex;
    return this.playTimeId;
  }
  public void setPlayTimeId(int playTimeId)
  {
    this.playTimeId = playTimeId;
  }
  public int getGhin()
  {
    return this.ghin;
  }
  public void setGhin(int ghin)
  {
    this.ghin = ghin;
  }
  public String getDay()
  {
    return this.day;
  }
  public void setDay(String day)
  {
    this.day = day;
  }
  public int getTeeTimeIndex()
  {
    return this.teeTimeIndex;
  }
  public void setTeeTimeIndex(int teeTimeIndex)
  {
    this.teeTimeIndex = teeTimeIndex;
  }
  public int getTime()
  {
    return this.time;
  }
  public void setTime(int time)
  {
    this.time = time;
  }
  public ArrayList<ArrayList<String>> getUnsortedIndex()
  {
    return this.unsortedIndex;
  }
  public void setUnsortedIndex(ArrayList<ArrayList<String>> unsortedIndex)
  {
    this.unsortedIndex = unsortedIndex;
  }
  public ArrayList<ArrayList<String>> getSortedIndex()
  {
    //declaring arraylists to hold inputs
    ArrayList<ArrayList<ArrayList<String>>> dayArray = new ArrayList<ArrayList<ArrayList<String>>>(7);
    ArrayList<ArrayList<String>> monArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> tueArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> wedArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> thuArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> friArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> satArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> sunArray = new ArrayList<ArrayList<String>>();
    this.sortedIndex = this.unsortedIndex;
    
    //loops for length of sorted index
    for (int i=0; i<this.sortedIndex.size(); i++)
    {
      //if day is monday index set to MON
      if (this.sortedIndex.get(i).get(1).equals("Monday"))
      {
        this.sortedIndex.get(i).set(0, "MON");
        monArray.add(this.sortedIndex.get(i));
      }
      //if day is tuesday index set to TUE
      else if (this.sortedIndex.get(i).get(1).equals("Tuesday"))
      {
        this.sortedIndex.get(i).set(0, "TUE");
        tueArray.add(this.sortedIndex.get(i));
      }
      //if day is wednesday index set to WED
      else if (this.sortedIndex.get(i).get(1).equals("Wednesday"))
      {
        this.sortedIndex.get(i).set(0, "WED");
        wedArray.add(this.sortedIndex.get(i));
      }
      //if day is wednesday index set to THU
      else if (this.sortedIndex.get(i).get(1).equals("Thursday"))
      {
        this.sortedIndex.get(i).set(0, "THU");
        thuArray.add(this.sortedIndex.get(i));
      }
      //if day is wednesday index set to FRI
      else if (this.sortedIndex.get(i).get(1).equals("Friday"))
      {
        this.sortedIndex.get(i).set(0, "FRI");
        friArray.add(this.sortedIndex.get(i));
      }
      //if day is wednesday index set to SAT
      else if (this.sortedIndex.get(i).get(1).equals("Saturday"))
      {
        this.sortedIndex.get(i).set(0, "SAT");
        satArray.add(this.sortedIndex.get(i));
      }
      //if day is wednesday index set to SUN
      else if (this.sortedIndex.get(i).get(1).equals("Sunday"))
      {
        this.sortedIndex.get(i).set(0, "SUN");
        sunArray.add(this.sortedIndex.get(i));
      }
    }
    //adds all arrays to day array
    dayArray.add(monArray);
    dayArray.add(tueArray);
    dayArray.add(wedArray);
    dayArray.add(thuArray);
    dayArray.add(friArray);
    dayArray.add(satArray);
    dayArray.add(sunArray);
    //sorts tee times based on time
    for (int i=0; i<dayArray.size(); i++)
    {
      // declaring done to false
      boolean done = false;
      ArrayList<String> temp;
      // while loop continues until sorting is complete
      while (done == false)
      {
        // sets done to true
        done = true;
        // for the length of the array -1 the for loop goes
        for (int j=0; j < dayArray.get(i).size() - 1; j++)
        {
          // if the succeding number in an array is less than the current they are swapped
          if (Integer.parseInt(dayArray.get(i).get(j).get(3)) > 
              Integer.parseInt(dayArray.get(i).get(j+1).get(3)))
          {
            // sets done to be false if items are moved
            temp = dayArray.get(i).get(j);
            dayArray.get(i).set(j, dayArray.get(i).get(j+1));
            dayArray.get(i).set(j+1, temp);
            done = false;
          }
        }
      }
    }
    this.sortedIndex.clear();
    for (int i=0; i<dayArray.size(); i++)
    {
      //concatenates the day of week index with the index number
      for (int j=0; j<dayArray.get(i).size(); j++)
      {
        dayArray.get(i).get(j).set(2, Integer.toString(j));
        dayArray.get(i).get(j).set(0, dayArray.get(i).get(j).get(0) + Integer.toString(j));
        this.sortedIndex.add(dayArray.get(i).get(j));
      }
    }
    //returns sorted index
    return this.sortedIndex;
  }
  public void setSortedIndex(ArrayList<ArrayList<String>> sortedIndex)
  {
    this.sortedIndex = sortedIndex;
  }

  @Override
  public String toString()
  {
    return "TeeTime{" + "day=" + this.day + ", teeTimeIndex=" + this.teeTimeIndex 
        + ", time=" + this.time + ", playTimeId=" + this.playTimeId + ", ghin=" 
        + this.ghin + ", unsortedIndex=" + this.unsortedIndex + ", sortedIndex=" 
        + this.sortedIndex + ", addedPlayers=" + this.addedPlayers + ", neededTimes=" 
        + this.neededTimes + '}';
  }
  
  //main method used to test the class
  public static void main(String[] args)
  {
    ArrayList<ArrayList<String>> bigArray = new ArrayList<ArrayList<String>>();
    ArrayList<String> small1 = new ArrayList<String>();
    ArrayList<String> small2 = new ArrayList<String>();
    ArrayList<String> small3 = new ArrayList<String>();
    ArrayList<String> small4 = new ArrayList<String>();
    ArrayList<String> small5 = new ArrayList<String>();
    ArrayList<String> small6 = new ArrayList<String>();
    ArrayList<String> small7 = new ArrayList<String>();
    ArrayList<String> small8 = new ArrayList<String>();
    ArrayList<String> small9 = new ArrayList<String>();
    ArrayList<String> small10 = new ArrayList<String>();
    ArrayList<String> small11 = new ArrayList<String>();
    ArrayList<String> small12 = new ArrayList<String>();
    ArrayList<String> small13 = new ArrayList<String>();
    ArrayList<String> small14 = new ArrayList<String>();
    ArrayList<String> small15 = new ArrayList<String>();
    ArrayList<String> small16 = new ArrayList<String>();
    for (int i=0; i<4; i++)
    {
      small1.add("0");
      small2.add("0");
      small3.add("0");
      small4.add("0");
      small5.add("0");
      small6.add("0");
      small7.add("0");
      small8.add("0");
      small9.add("0");
      small10.add("0");
      small11.add("0");
      small12.add("0");
      small13.add("0");
      small14.add("0");
      small15.add("0");
      small16.add("0");
    }
    
    small1.set(1,"Monday");
    small2.set(1,"Monday");
    small3.set(1,"Monday");
    small4.set(1,"Monday");
    small5.set(1,"Tuesday");
    small6.set(1,"Tuesday");
    small7.set(1,"Tuesday");
    small8.set(1,"Tuesday");
    small9.set(1,"Tuesday");
    small10.set(1,"Tuesday");
    small11.set(1,"Wednesday");
    small12.set(1,"Wednesday");
    small13.set(1,"Wednesday");
    small14.set(1,"Wednesday");
    small15.set(1,"Wednesday");
    small16.set(1,"Wednesday");
    small1.set(3,"1100");
    small2.set(3,"1300");
    small3.set(3,"900");
    small4.set(3,"800");
    small5.set(3,"830");
    small6.set(3,"200");
    small7.set(3,"400");
    small8.set(3,"1200");
    small9.set(3,"1300");
    small10.set(3,"800");
    small11.set(3,"1100");
    small12.set(3,"1300");
    small13.set(3,"900");
    small14.set(3,"800");
    small15.set(3,"830");
    small16.set(3,"200");
    bigArray.add(small1);
    bigArray.add(small2);
    bigArray.add(small3);
    bigArray.add(small4);
    bigArray.add(small5);
    bigArray.add(small6);
    bigArray.add(small7);
    bigArray.add(small8);
    bigArray.add(small9);
    bigArray.add(small10);
    bigArray.add(small11);
    bigArray.add(small12);
    bigArray.add(small13);
    bigArray.add(small14);
    bigArray.add(small15);
    bigArray.add(small16);
    TeeTime timeObj = new TeeTime();
    timeObj.setUnsortedIndex(bigArray);
    timeObj.getSortedIndex();
    System.out.println(timeObj.toString());
  }
}