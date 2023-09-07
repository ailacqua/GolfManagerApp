/*This class creates the final rosters for each day by assigning players to 
a specific tee time
 */
//package golfmanager;

import java.util.ArrayList;

public class SortPlayers
{
  //declaring datafields for the class
  private ArrayList<ArrayList<String>> unsortedPlayers;
  private ArrayList<ArrayList<String>> sortedPlayers;
  private int addedPlayers;
  
  //constructor with addedplayers and unsortedplayers
  public SortPlayers(int addedPlayers, ArrayList<ArrayList<String>> unsortedPlayers)
  {
    this.unsortedPlayers = unsortedPlayers;
    this.addedPlayers = addedPlayers;
    this.sortedPlayers = new ArrayList<ArrayList<String>>();
  }
  //constructor with no parameters
  public SortPlayers()
  {
    this.addedPlayers = 0;
    this.unsortedPlayers = new ArrayList<ArrayList<String>>();
    this.sortedPlayers = new ArrayList<ArrayList<String>>();
  }
  
  //mutators and accessors to access encapsulated data
  public ArrayList<ArrayList<String>> getUnsortedPlayers()
  {
    return this.unsortedPlayers;
  }
  public void setUnsortedPlayers(ArrayList<ArrayList<String>> unsortedPlayers)
  {
    this.unsortedPlayers = unsortedPlayers;
  }
  public ArrayList<ArrayList<String>> getSortedPlayers()
  {
    //declaring variables to hold calculated values
    int timesNeeded;
    int remainder = this.addedPlayers % 4;
    int index = 0;
    ArrayList<ArrayList<String>> tempArray = new ArrayList<ArrayList<String>>();
    int[] timePlayerCount;
    int timeIndex;
    boolean done = false;
    //finds the amount of needed times
    TeeTime timeObj = new TeeTime(this.addedPlayers);
    timesNeeded = timeObj.getNeededTimes();
    timePlayerCount = new int[timesNeeded];
    
    //continues looping for one less than the unsortedplayers size
    while (index<unsortedPlayers.size()-1)
    {
      tempArray = new ArrayList<ArrayList<String>>();
      //adds index of unsorted players to temp array
      tempArray.add(unsortedPlayers.get(index));
      for (int j=index+1; j<unsortedPlayers.size(); j++)
      {
        //if more ghins equal to current ghin those are added to temp array
        if (unsortedPlayers.get(j).get(1).equals(unsortedPlayers.get(index).get(1)))
        {
          tempArray.add(unsortedPlayers.get(j));
          index++;
        }
        //else the index is equal to j and loop broken
        else
        {
          index = j;
          break;
        }
      }
      // declaring done to false
      done = false;
      ArrayList<String> temp;
      // while loop continues until sorting is complete to sort players by preference
      while (done == false)
      {
        // sets done to true
        done = true;
        // for the length of the array -1 the for loop goes
        for (int i=0; i < tempArray.size() - 1; i++)
        {
          // if the succeding number in an array is less than the current they are swapped
          if (Integer.parseInt(tempArray.get(i).get(5)) > Integer.parseInt(tempArray.get(i+1).get(5)))
          {
            // sets done to be false if items are moved and swaps
            temp = tempArray.get(i);
            tempArray.set(i, tempArray.get(i+1));
            tempArray.set(i+1, temp);
            done = false;
          }
        }
      }
      //if remainder is 0 players added until size is 4
      if (remainder == 0)
      {
        for (int i=0; i<tempArray.size(); i++)
        {
          timeIndex = Integer.parseInt(tempArray.get(i).get(4));
          if (timePlayerCount[timeIndex] < 4)
          {
            //sets final tee time to 1 
            tempArray.get(i).set(7, "1");
            timePlayerCount[timeIndex]++;
            break;
          }
        }
      }
      //if remainder is 0 players added until size is 4 until last 3 for size 3
      else if (remainder == 1)
      {
        for (int i=0; i<tempArray.size(); i++)
        {
          timeIndex = Integer.parseInt(tempArray.get(i).get(4));
          //adds players until size is 4 until the last 3 times
          if (timeIndex < timesNeeded - 3)
          {
            if (timePlayerCount[timeIndex] < 4)
            {
              //sets final tee time to 1 
              tempArray.get(i).set(7, "1");
              timePlayerCount[timeIndex]++;
              break;
            }
          }
          else
          {
            //adds players until size 3 for the last 3
            if (timePlayerCount[timeIndex] < 3)
            {
              //sets final tee time to 1 
              tempArray.get(i).set(7, "1");
              timePlayerCount[timeIndex]++;
              break;
            }
          }
        }
      }
      //if remainder is 0 players added until size is 4 until last 2 where size is 3
      else if (remainder == 2)
      {
        for (int i=0; i<tempArray.size(); i++)
        {
          timeIndex = Integer.parseInt(tempArray.get(i).get(4));
          //adds players until size is 4 until the last 2 times
          if (timeIndex < timesNeeded - 2)
          {
            if (timePlayerCount[timeIndex] < 4)
            {
              //sets final tee time to 1 
              tempArray.get(i).set(7, "1");
              timePlayerCount[timeIndex]++;
              break;
            }
          }
          else
          {
            //adds players until size 3 for the last 2
            if (timePlayerCount[timeIndex] < 3)
            {
              //sets final tee time to 1 
              tempArray.get(i).set(7, "1");
              timePlayerCount[timeIndex]++;
              break;
            }
          }
        }
      }
      //if remainder is 0 players added until size is 4 until last one where size is 1
      else if (remainder == 3)
      {
        for (int i=0; i<tempArray.size(); i++)
        {
          timeIndex = Integer.parseInt(tempArray.get(i).get(4));
          //adds players until size is 4 until the last 1 time
          if (timeIndex < timesNeeded - 1)
          {
            if (timePlayerCount[timeIndex] < 4)
            {
              //sets final tee time to 1 
              tempArray.get(i).set(7, "1");
              timePlayerCount[timeIndex]++;
              break;
            }
          }
          else
          {
            //adds players until size 3 for the last 1
            if (timePlayerCount[timeIndex] < 3)
            {
              //sets final tee time to 1 
              tempArray.get(i).set(7, "1");
              timePlayerCount[timeIndex]++;
              break;
            }
          }
        }
      }
      //adds all the contents of temp array to sorted players
      for(int i=0; i<tempArray.size(); i++)
      {
        this.sortedPlayers.add(tempArray.get(i));
      }
    }
    //if the addedplayers is less than 4 then unsorted players at index added
    if (this.addedPlayers < 5)
    {
      this.unsortedPlayers.get(index).set(7, "1");
      this.sortedPlayers.add(this.unsortedPlayers.get(index));
    }
    //returns sorted players
    return this.sortedPlayers;
  }
  public void setSortedPlayers(ArrayList<ArrayList<String>> sortedPlayers)
  {
    this.sortedPlayers = sortedPlayers;
  }
  public int getAddedPlayers()
  {
    return this.addedPlayers;
  }
  public void setAddedPlayers(int addedPlayers)
  {
    this.addedPlayers = addedPlayers;
  }

  @Override
  public String toString()
  {
    return "SortPlayers{" + "unsortedPlayers=" + unsortedPlayers + ", sortedPlayers=" 
        + sortedPlayers + ", addedPlayers=" + addedPlayers + '}';
  }
  
  //main method to test class
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
    for (int i=0; i<8; i++)
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
    small1.set(1,"1");
    small2.set(1,"2");
    small3.set(1,"3");
    small4.set(1,"2");
    small5.set(1,"3");
    small6.set(1,"3");
    small7.set(1,"4");
    small8.set(1,"4");
    small9.set(1,"5");
    small10.set(1,"5");
    small11.set(1,"6");
    small12.set(1,"6");
    small13.set(1,"7");
    small14.set(1,"7");
    small15.set(1,"8");
    small16.set(1,"8");
    
    small1.set(5,"1");
    small2.set(5,"1");
    small3.set(5,"1");
    small4.set(5,"1");
    small5.set(5,"2");
    small6.set(5,"1");
    small7.set(5,"2");
    small8.set(5,"1");
    small9.set(5,"2");
    small10.set(5,"1");
    small11.set(5,"1");
    small12.set(5,"2");
    small13.set(5,"1");
    small14.set(5,"2");
    small15.set(5,"1");
    small16.set(5,"2");
    
    small1.set(4,"0");
    small2.set(4,"0");
    small3.set(4,"0");
    small4.set(4,"1");
    small5.set(4,"0");
    small6.set(4,"1");
    small7.set(4,"0");
    small8.set(4,"1");
    small9.set(4,"0");
    small10.set(4,"1");
    small11.set(4,"0");
    small12.set(4,"1");
    small13.set(4,"0");
    small14.set(4,"1");
    small15.set(4,"0");
    small16.set(4,"1");
    
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
    
    System.out.println(bigArray);
    SortPlayers sortObj = new SortPlayers();
    sortObj.setAddedPlayers(3);
    sortObj.setUnsortedPlayers(bigArray);
    System.out.println(sortObj.toString());
  }
}