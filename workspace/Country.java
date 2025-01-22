public class Country
{
 // add private instance variables for the name, capital, language, and image file.
 private String name;
 private String capital;
 private String primaryLanguage;
 private String imagefile;
 // add constructors
 public Country() {}


 // Constructor with 4 arguments
 public Country(String name, String capital, String primaryLanguage, String imagefile) {
     this.name = name;
     this.capital = capital;
     this.primaryLanguage = primaryLanguage;
     this.imagefile = imagefile;
 }
 // Write accessor/get methods for each instance variable that returns it.
 public String getName() { return name; }
 public String getCapital() { return capital; }
 public String getPrimaryLanguage() { return primaryLanguage; }
 public String getImagefile() { return imagefile; }


 // toString method
 public String toString() {
     return name + "'s capital is " + capital + " and its primary language is " + primaryLanguage + ".";
 }
}
