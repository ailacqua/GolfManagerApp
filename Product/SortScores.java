/*This class allows the user to sort scores based on their preference, and calculates
the highest, lowest, times played, and average scores for the week
 */
//package golfmanager;

import java.util.ArrayList;

public class SortScores
{
  //declaring datafields
  private String filterType;
  private ArrayList<ArrayList<String>> unsortedScores;
  private ArrayList<ArrayList<String>> sortedScores;
  private ArrayList<ArrayList<String>> uncalculatedScores;
  private ArrayList<ArrayList<String>> calculatedScores;
  //constructor to find the sorted scores
  public SortScores(ArrayList<ArrayList<String>> unsortedScores, String filterType)
  {
    this.filterType = filterType;
    this.unsortedScores = unsortedScores;
  }
  //constructor with no parameters
  public SortScores()
  {
    this.filterType = "";
    this.unsortedScores = new ArrayList<ArrayList<String>>();
    this.sortedScores = new ArrayList<ArrayList<String>>();
    this.uncalculatedScores = new ArrayList<ArrayList<String>>();
    this.calculatedScores = new ArrayList<ArrayList<String>>();
  }
  
  //mutators and accessors to access encapsulated data
  public ArrayList<ArrayList<String>> getUncalculatedScores()
  {
    return this.uncalculatedScores;
  }
  public void setUncalculatedScores(ArrayList<ArrayList<String>> uncalculatedScores)
  {
    this.uncalculatedScores = uncalculatedScores;
  }
  public ArrayList<ArrayList<String>> getCalculatedScores()
  {
    //declaring variables to hold results
    this.calculatedScores = this.uncalculatedScores;
    int highest;
    int lowest = 0;
    int timesPlayed;
    int sum = 0;
    double average = 0;
    //loops for length of calculateed scores
    for (int i=0; i<calculatedScores.size(); i++)
    {
      timesPlayed = 0;
      sum = 0;
      highest = Integer.parseInt(calculatedScores.get(i).get(3));
      
      //finds lowest integer among days that is not 0
      for (int j=3; j<10; j++)
      {
        if (Integer.parseInt(calculatedScores.get(i).get(j)) > 0)
        {
          lowest = Integer.parseInt(calculatedScores.get(i).get(j));
          break;
        }
      }
      //loops through result for each day
      for (int j=3; j<10; j++)
      {
        //adds sum total
        sum = sum + Integer.parseInt(calculatedScores.get(i).get(j));
        //if new value is higher then high reset
        if (Integer.parseInt(calculatedScores.get(i).get(j)) > highest)
        {
          highest = Integer.parseInt(calculatedScores.get(i).get(j));
        }
        //if new value is lower then low reset
        if ((Integer.parseInt(calculatedScores.get(i).get(j)) < lowest) && 
            (Integer.parseInt(calculatedScores.get(i).get(j)) > 0))
        {
          lowest = Integer.parseInt(calculatedScores.get(i).get(j));
        }
        //increments timesPlayed for each score greater than 0
        if (Integer.parseInt(calculatedScores.get(i).get(j)) > 0)
        {
          timesPlayed++;
        }
      }
      //calculates the average and sets it by ensuring denominator > 0
      if (timesPlayed > 0)
      {
        average = sum / timesPlayed;
        //sets the highest, lowest, sum, and times played
        calculatedScores.get(i).set(12, Double.toString(average));
        calculatedScores.get(i).set(14, Integer.toString(highest));
        calculatedScores.get(i).set(13, Integer.toString(lowest));
        calculatedScores.get(i).set(11, Integer.toString(sum));
        calculatedScores.get(i).set(10, Integer.toString(timesPlayed));
      }
      else
      {
        calculatedScores.remove(i);
      }
    }
    return this.calculatedScores;
  }
  public void setCalculatedScores(ArrayList<ArrayList<String>> calculatedScores)
  {
    this.calculatedScores = calculatedScores;
  }
  public String getFilterType()
  {
    return this.filterType;
  }
  public void setFilterType(String filterType)
  {
    this.filterType = filterType;
  }
  public ArrayList<ArrayList<String>> getUnsortedScores()
  {
    return this.unsortedScores;
  }
  public void setUnsortedScores(ArrayList<ArrayList<String>> unsortedScores)
  {
    this.unsortedScores = unsortedScores;
  }
  public ArrayList<ArrayList<String>> getSortedScores()
  {
    //sets unsorted scores equal to sorted scores
    this.sortedScores = this.unsortedScores;
    ArrayList<String> temp = new ArrayList<String>();
    // filters by average if average selected
    if (this.filterType.equals("average"))
    {
      // declaring done to false
      boolean done = false;
      // while loop continues until sorting is complete
      while (done == false)
      {
        // sets done to true
        done = true;
        // for the length of the array -1 the for loop goes
        for (int i=0; i < this.sortedScores.size() - 1; i++)
        {
          // if the succeding average in array is less than the current they are swapped
          if (Double.parseDouble(this.sortedScores.get(i).get(12)) > 
              Double.parseDouble(this.sortedScores.get(i+1).get(12)))
          {
            // sets done to be false if items are moved and swaps i and i+1
            temp = this.sortedScores.get(i);
            this.sortedScores.set(i, this.sortedScores.get(i+1));
            this.sortedScores.set(i+1, temp);
            done = false;
          }
        }
      }
    }
    //filters by ghin if ghin selected
    else if (this.filterType.equals("ghin"))
    {
      // declaring done to false
      boolean done = false;
      // while loop continues until sorting is complete
      while (done == false)
      {
        // sets done to true
        done = true;
        // for the length of the array -1 the for loop goes
        for (int i=0; i < this.sortedScores.size() - 1; i++)
        {
          // if the succeding number in an array is less than the current they are swapped
          if (Integer.parseInt(this.sortedScores.get(i).get(0)) > 
              Integer.parseInt(this.sortedScores.get(i+1).get(0)))
          {
            // sets done to be false if items are moved and swaps i and i+1 of array
            temp = this.sortedScores.get(i);
            this.sortedScores.set(i, this.sortedScores.get(i+1));
            this.sortedScores.set(i+1, temp);
            done = false;
          }
        }
      }
    }
    //filters highest to lowest if highest selected
    else if (this.filterType.equals("highest"))
    {
      // declaring done to false
      boolean done = false;
      // while loop continues until sorting is complete
      while (done == false)
      {
        // sets done to true
        done = true;
        // for the length of the array -1 the for loop goes
        for (int i=0; i < this.sortedScores.size() - 1; i++)
        {
          // if the succeding number in an array is less than the current they are swapped
          if (Integer.parseInt(this.sortedScores.get(i).get(14)) < 
              Integer.parseInt(this.sortedScores.get(i+1).get(14)))
          {
            // sets done to be false if items are moved and swaps i and i+1 arrays
            temp = this.sortedScores.get(i);
            this.sortedScores.set(i, this.sortedScores.get(i+1));
            this.sortedScores.set(i+1, temp);
            done = false;
          }
        }
      }
    }
    //sorts lowest to highest if lowest selected
    else if (this.filterType.equals("lowest"))
    {
      // declaring done to false
      boolean done = false;
      // while loop continues until sorting is complete
      while (done == false)
      {
        // sets done to true
        done = true;
        // for the length of the array -1 the for loop goes
        for (int i=0; i < this.sortedScores.size() - 1; i++)
        {
          // if the succeding number in an array is less than the current they are swapped
          if (Integer.parseInt(this.sortedScores.get(i).get(13)) > 
              Integer.parseInt(this.sortedScores.get(i+1).get(13)))
          {
            // sets done to be false if items are moved and swaps array i and i+1
            temp = this.sortedScores.get(i);
            this.sortedScores.set(i, this.sortedScores.get(i+1));
            this.sortedScores.set(i+1, temp);
            done = false;
          }
        }
      }
    }
    return this.sortedScores;
  }
    
  public void setSortedScores(ArrayList<ArrayList<String>> sortedScores)
  {
    this.sortedScores = sortedScores;
  }

  @Override
  public String toString()
  {
    return "SortScores{" + "filterType=" + filterType + ", unsortedScores=" + 
        unsortedScores + ", sortedScores=" + sortedScores + ", uncalculatedScores=" 
        + uncalculatedScores + ", calculatedScores=" + calculatedScores + '}';
  }

  

  public static void main(String[] args)
  {
    ArrayList<ArrayList<String>> bigArray = new ArrayList<ArrayList<String>>();
    ArrayList<String> small1 = new ArrayList<String>();
    ArrayList<String> small2 = new ArrayList<String>();
    ArrayList<String> small3 = new ArrayList<String>();
    ArrayList<String> small4 = new ArrayList<String>();
    for (int i=0; i<9; i++)
    {
      small1.add("0");
      small2.add("0");
      small3.add("0");
      small4.add("0");
    }
    small1.set(6, "1");
    small2.set(6, "2");
    small3.set(6, "3");
    small4.set(6, "4");
    small1.set(1, "1");
    small2.set(1, "2");
    small3.set(1, "3");
    small4.set(1, "4");
    small1.set(7, "1");
    small2.set(7, "2");
    small3.set(7, "3");
    small4.set(7, "4");
    small1.set(8, "1");
    small2.set(8, "2");
    small3.set(8, "3");
    small4.set(8, "4");
    bigArray.add(small3);
    bigArray.add(small4);
    bigArray.add(small1);
    bigArray.add(small2);
    SortScores sortObj = new SortScores();
    sortObj.setFilterType("highest");
    sortObj.setUnsortedScores(bigArray);
    System.out.println(sortObj.toString());
  }
}