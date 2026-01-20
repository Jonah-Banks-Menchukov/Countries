//Jonah Banks
//This class has main method, gui construction, and button clickes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
public class Main 
{

  // array of 10 Country objects
  private Country[] countryArray = new Country[10];  
  private String[] questions={"What country is this?","What is the capital of this country?","What language do they speak here?"};
  // index of current shown country
  private int index = (int)(Math.random()*10);

  // GUI elements
  private JFrame jFrame = new JFrame("Countries");
  private ImageIcon img;
  private JLabel imageLabel;
  private JLabel outputLabel;
  private JTextArea userInput; 

  Scanner s=new Scanner(System.in);
  
  public static void main(String[] args) {
    // Create the GUI
    Main gui = new Main();
    gui.loadCountries();
    gui.showCountry();
  }

  /* loadCountries() reads in the data from the countries-data.csv file and fills in the countryArray with data. You need to add the loop that reads in the country data into the array. */
  public void loadCountries()
  {
    // Open the data file. Please note that the file structure we're working with requires the full file path as shown here unlike what you saw in runestone where the file name was sufficient.
    try{
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");
    Scanner s=new Scanner(file);
    for(int i=0;i<10;i++){
      String e=s.nextLine();
      String[] sArray=e.split(",");
      Country c=new Country(sArray[0],sArray[1],sArray[2],sArray[3]);
      countryArray[i]=c;
    }
    s.close();
  } catch(IOException e){
    System.out.println("Exception: "+e.toString());
  }
    //create a scanner and a loop to read from the file until you've read everything.
    // inside the loop you'll need to read in a line from the file and use "split" to break up the data into destinct parts.
    // create a new Country using your constructor with 4 arguments (each of the arguments is a different part of the line you've read in)
    // inside the loop, set countryArray[i] to the created Country object
    //after running this method your array should contain all 10 countries from inside the countries-data file.
  }

  /* showCountry() will show the image associated with the current country. It should get the country at index from the countryArray. It should use its get method to get its image file name and use the code below to put the image in the GUI.
  */
  public void showCountry() {
    // Get the country at index from countryArray
    Country myCountry=countryArray[index];
    // Use its get method to get the its image file name and save it into imagefile variable below instead of worldmap.jpg.
    String imagefile = myCountry.getImageFile();
    // Use the following code to create an new Image Icon and put it into the GUI
    img = new ImageIcon("/workspaces/Countries/workspace/"+imagefile);
    imageLabel.setIcon(img);
  }
  
  /* nextButton should increment index. If the index is greater than 9, reset it back to 0. Clear the outputLabel to empty string using setText, and call showCountry();*/
  public void nextButtonClick()
  {
    index++;
    if (index>9){
      index=0;
    }
    outputLabel.setText("");
    showCountry();
  }
  
  /* reviewButton should get the country at index from 
  *the countryArray, call its toString() method and save 
  *the result, print it out with System.out.println and 
  *as an argument to outputLabel.setText( text to print 
  *out ); */
  public void reviewButtonClick()
  {
    String c=countryArray[index].toString();
     System.out.println(c);
     outputLabel.setText(c);
  }

  /* quizButton should clear the outputLabel 
  (outputLabel.setText to empty string), get the 
  country at index from countryArray, print out a 
  question about it like What country is this? and/or 
  What's this country's capital?. Get the user's answer 
  using scan.nextLine() and check if it is equal to the 
  country's data using its get methods and print out 
  correct or incorrect.
  */
  public void quizButtonClick()
  {
    String userAns="";
  
    Country thisCountry=countryArray[index];
    String question=questions[(int)(Math.random()*3)];
    System.out.println(question);
  boolean askAgain = true;
  while(askAgain){
    System.out.println("here");
    try{
      userAns=s.nextLine();
      askAgain = false;
  }catch(NoSuchElementException e){
    askAgain = true;
  }
}
  System.out.println(userAns);
   if(question.equals("What country is this?")&&userAns.equals(thisCountry.getCapital())){
      System.out.println("Correct");
   }else if(question.equals("What country is this?")&&userAns.equals(thisCountry.getCapital())){
    System.out.println("Correct");
   }else if(question.equals("What language do they speak here?")&&userAns.equals(thisCountry.getLanguage())){
    System.out.println("Correct");
   }else{
    System.out.println("Incorrect");
   }
  }



  /* You are not required to change anythign below here. You do so at your own risk! */
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
        userInput = new JTextArea(1, 40);
        jFrame.add(imageLabel);
        jFrame.add(outputLabel);
        jFrame.add(userInput);
        jFrame.setVisible(true);
        // add event listener for button click
        reviewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      reviewButtonClick();
    }
        });
    quizButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      quizButtonClick();
    }
    });
   
   newButton.addActionListener(new ActionListener()  {
    public void actionPerformed(ActionEvent e) 
    {
      nextButtonClick();
    }
   });
}
}