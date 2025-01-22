// Steve Thomas Period 3
// January 17th, 2025
// GeoGame - Countries, Quiz, and Review varous countries around the Earth.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main {
  // array of 10 Country objects
  private Country[] countryArray = new Country[10]; 
  // index of current shown country
  private int index = 0;

  // GUI elements
  private JFrame jFrame = new JFrame("Countries");
  private ImageIcon img;
  private JLabel imageLabel;
  private JLabel outputLabel;

  public static void main(String[] args) {
    // Create the GUI
    Main gui = new Main();
    gui.loadCountries();
    gui.showCountry();
  }

  /*
   * Pre-condition: CSV file 'countries-data.csv' exists in the specified path.
   * Post-condition: countryArray is filled with Country objects created from the CSV data.
   */
  public void loadCountries() {
    // Open the data file - do not change
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");
    Scanner scan = null;
    try {
      scan = new Scanner(file);
    } catch(FileNotFoundException e) {
      System.out.println("File not found");
    }
    
    // Write a for loop that goes through the countryArray.
    for (int i = 0; i < countryArray.length; i++) {
      // Do the following inside the loop
      String input = scan.nextLine();
      String[] data = input.split(",");
      System.out.println("Read in " + data[0]);
      // inside the loop, create a new Country using your constructor with 4 arguments and pass in data[0], data[1], data[2], data[3] as arguments.
      Country country = new Country(data[0], data[1], data[2], data[3]);
      // inside the loop, set countryArray[i] to the created Country object
      countryArray[i] = country;
    }
  }

  /*
   * Pre-condition: The index must be within the bounds of countryArray.
   * Post-condition: The imageLabel displays the image of the country at the current index.
   */
  public void showCountry() {
    // Get the country at index from countryArray
    Country c = countryArray[index];
    // Use its get method to get the its image file name and save it into imagefile variable below instead of worldmap.jpg.
    String imagefile = c.getImagefile();
    // Use the following code to create an new Image Icon and put it into the GUI
    img = new ImageIcon("/workspaces/Countries/workspace/" + imagefile);
    imageLabel.setIcon(img);
  }

  /*
   * Pre-condition: Button click event triggers this method.
   * Post-condition: Index is incremented, and the next country's image is displayed. 
   * If index goes out of bounds, it is reset to 0.
   */
  public void nextButtonClick() {
    index++;
    if (index >= countryArray.length) {
      index = 0;
    }
    outputLabel.setText("");
    showCountry();
  }

  /*
   * Pre-condition: Button click event triggers this method.
   * Post-condition: Displays the current country's information in the output label.
   */
  public void reviewButtonClick() {
    Country c = countryArray[index];
    String info = c.toString();
    System.out.println(info);
    outputLabel.setText(info);
  }

  /*
   * Pre-condition: Button click event triggers this method.
   * Post-condition: Prompts the user with a quiz question and shows whether the answer is correct or not.
   */
  public void quizButtonClick() {
    outputLabel.setText(""); // Clear the outputLabel
    Country c = countryArray[index];
    String question = "What country is this?";
    System.out.println(question);
    // Display the question in the output label
    outputLabel.setText(question);
    // Create a dialog box to get user's answer
    String answer = JOptionPane.showInputDialog(jFrame, question);
    
    if (answer != null && answer.equalsIgnoreCase(c.getName())) {
      System.out.println("Correct!");
      JOptionPane.showMessageDialog(jFrame, "Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
    } else {
      System.out.println("Incorrect. The correct answer is " + c.getName());
      JOptionPane.showMessageDialog(jFrame, "Incorrect. The correct answer is " + c.getName(), "Result", JOptionPane.ERROR_MESSAGE);
    }
  }

  /* Do NOT change anything below here */
  /* The Main() constructor is finished and will construct the GUI */
  public Main() {
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 360);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // buttons at the top
    JButton reviewButton = new JButton("Review");
    JButton quizButton = new JButton("Quiz");
    JButton newButton = new JButton("Next");
    jFrame.add(reviewButton);
    jFrame.add(quizButton);
    jFrame.add(newButton);

    // create a new image icon
    img = new ImageIcon("worldmap.jpg");
    // create a label to display image
    imageLabel = new JLabel(img);
    // and one for output
    outputLabel = new JLabel();
    jFrame.add(imageLabel);
    jFrame.add(outputLabel);
    jFrame.setVisible(true);
    // add event listener for button click
    reviewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reviewButtonClick();
      }
    });
    quizButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        quizButtonClick();
      }
    });
    newButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        nextButtonClick();
      }
    });
  }
}
