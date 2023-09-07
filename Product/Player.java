/*This class computes the registration ID
 */
//package golfmanager;

public class Player
{
  //declaring class data fields
  private int registrationId;
  private int ghinNumber;
  private String lastName;
  private String firstName;
  private String day;
  
  //constructor with ghin and day
  public Player(int ghinNumber, String day)
  {
    this.ghinNumber = ghinNumber;
    this.day = day;
    this.lastName = "";
    this.firstName = "";
  }
  //constructor with no parameters
  public Player()
  {
    this.registrationId = 0;
    this.ghinNumber = 0;
    this.day = "";
    this.lastName = "";
    this.firstName = "";
  }
  //gets the registration id
  public int getRegistrationId()
  {
    //declares array containing days of the week
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    
    //if the day is monday 0 concatenated to ghin
    if (day.equals(days[0]))
    {
      this.registrationId = (this.ghinNumber*10) + 0;
    }
    //if day is tuesday 1 concatenated to ghin
    else if (day.equals(days[1]))
    {
      this.registrationId = (this.ghinNumber*10) + 1;
    }
    //if day is wednesday 2 concatenated to ghin
    else if (day.equals(days[2]))
    {
      this.registrationId = (this.ghinNumber*10) + 2;
    }
    //if day is thursday 3 concatenated to ghin
    else if (day.equals(days[3]))
    {
      this.registrationId = (this.ghinNumber*10) + 3;
    }
    //if day is friday 4 concatenated to ghin
    else if (day.equals(days[4]))
    {
      this.registrationId = (this.ghinNumber*10) + 4;
    }
    //if day is saturday 5 concatenated to ghin
    else if (day.equals(days[5]))
    {
      this.registrationId = (this.ghinNumber*10) + 5;
    }
    //if day is sunday 6 concatenated to ghin
    else if (day.equals(days[6]))
    {
      this.registrationId = (this.ghinNumber*10) + 6;
    }
    return registrationId;
  }
  //mutators and accessors allow for encapsulation
  public void setRegistrationId(int registrationId)
  {
    this.registrationId = registrationId;
  }
  public int getGhinNumber()
  {
    return this.ghinNumber;
  }
  public void setGhinNumber(int ghinNumber)
  {
    this.ghinNumber = ghinNumber;
  }
  public String getLastName()
  {
    return this.lastName;
  }
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  public String getFirstName()
  {
    return this.firstName;
  }
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  public String getDay()
  {
    return this.day;
  }
  public void setDay(String day)
  {
    this.day = day;
  }
  //prints contents of object when called
  @Override
  public String toString()
  {
    return "Player{" + "registrationId=" + registrationId + ", ghinNumber=" 
        + ghinNumber + ", lastName=" + lastName + ", firstName=" + firstName 
        + ", day=" + day + '}';
  }
  //main method to test the class
  public static void main(String[] args)
  {
    Player playerObj = new Player();
    playerObj.setGhinNumber(1234567);
    playerObj.setDay("Wednesday");
    System.out.println(playerObj.toString());
  }
}