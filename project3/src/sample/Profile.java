package sample;

/**
 Profile class to associate a name to an account
 @author Shyam Patel, Drew Taylor
 */
public class Profile
{
    private String fname;
    private String lname;
    public Profile(String fname, String lname)
    {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     Getter method to access fname
     @return first name of a person
     */
    public String getFName()
    {
        return this.fname;
    }
    
    /**
     Getter method to access lname
     @return last name of a person
     */
    public String getLName() 
    {
        return this.lname;
    }

    /**
     Checks if Profile are equal by checking their fields
     @Override equals method from Object class
     @param profile Profile to check if the profile's are equal
     @return true if fields are equal and false if not
     */
    public boolean equals(Profile profile)
    {
        return (profile.getFName().equals(fname)) && (profile.getLName().equals(lname));
    }

    /**
     toString method to turn fields into a string
     @Override toString method from Object class
     @return Fields of class as a string
     */
    public String toString()
    {
        return fname + " " + lname;
    }
}