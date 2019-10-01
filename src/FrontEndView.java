/*
 * FrontEndView - GUI for front end of user authentication system
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Edited JinH
 */
public class FrontEndView extends JFrame {
    private FrontEnd aFrontEnd = null;              // authentication system front end
    private BackEnd aBackEnd = null;                // authentication system back end
    private JTextArea displayArea = null;           // results display from database query
    private JTextField userBox = null;              // text box for input - username
    private JTextField passBox = null;              // text box for input - password
    private JRadioButton nonPreparedButton = null;  // radio button to use regular statement
    private JRadioButton preparedButton = null;     // radio button to use prepared statement
    private ButtonGroup buttonGroup = null;         // button group for above two buttons
    private JCheckBox metaCharFilterBox = null;     // check box for meta character filtering
    
    /**
     * FrontEndView - constructor
     */
    public FrontEndView(FrontEnd someFrontEnd) {
        super();
        initialize();
        aFrontEnd = someFrontEnd; 
    }   // end - constructor
    
    /**
     * Initialize - set up the graphical display
     */
    private void initialize() {
        JPanel sqlInjectionApplicationContentPane = new JPanel();
        JLabel title = new JLabel();
        JLabel userLabel = new JLabel();
        JLabel passLabel = new JLabel();
        userBox = new JTextField("");
        passBox = new JTextField("");
        Button authButton = new Button("Authenticate User");
        displayArea = new JTextArea(10, 35);
        preparedButton  = new JRadioButton("Prepared Statement", false);
        nonPreparedButton  = new JRadioButton("Regular Statement", true);
        buttonGroup = new ButtonGroup();
        metaCharFilterBox = new JCheckBox("MetaCharacter Filtering", false);
        
        try {
            // set up the application frame
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setSize(920, 500);
            setTitle("SQL Injection Testing Application");
    
            // construct the title label
            title.setFont(new java.awt.Font("Arial", 1, 30));
            title.setText("SQL Injection Testing Application");
            title.setBounds(150, 21, 600, 34);
            title.setForeground(java.awt.Color.blue);
            title.setHorizontalAlignment(SwingConstants.CENTER);
            
            // construct the radio buttons and group them
            preparedButton.setBounds(50, 60, 150, 20);
            nonPreparedButton.setBounds(50, 90, 150, 20);
            buttonGroup.add(preparedButton);
            buttonGroup.add(nonPreparedButton);
            metaCharFilterBox.setBounds(50, 120, 160, 20);
            
            // construct the text labels
            userLabel.setFont(new java.awt.Font("Arial", 1, 18));
            userLabel.setText("Username:");
            userLabel.setBounds(20, 150, 200, 35);
            passLabel.setForeground(java.awt.Color.black);

            passLabel.setFont(new java.awt.Font("Arial", 1, 18));
            passLabel.setText("Password:");
            passLabel.setBounds(20, 250, 200, 35);
            passLabel.setForeground(java.awt.Color.black);          

            // construct the text fields
            userBox.setBounds(20, 200, 300, 35);
            userBox.setEditable(true);
            
            passBox.setBounds(20, 300, 300, 35);
            passBox.setEditable(true);
            
            // construct the display text area
            displayArea.setBounds(360, 75, 500, 360);

            // construct the button 
            authButton.setBounds(80, 380, 180, 35);
            authButton.addActionListener( new ActionListener(){
                public void actionPerformed(ActionEvent e){                         
                    // execute the authentication
                    String resultString = aFrontEnd.processInput(userBox.getText(), 
                                                                 passBox.getText(),
                                                                 preparedButton.isSelected(),
                                                                 metaCharFilterBox.isSelected());
                    // display results
                    displayArea.setText(resultString);
                } } );

            // --- construct the highest level content-pane
            sqlInjectionApplicationContentPane.setLayout(null);
            sqlInjectionApplicationContentPane.add(title);
            sqlInjectionApplicationContentPane.add(displayArea);
            sqlInjectionApplicationContentPane.add(preparedButton);
            sqlInjectionApplicationContentPane.add(nonPreparedButton);
            sqlInjectionApplicationContentPane.add(metaCharFilterBox);
            sqlInjectionApplicationContentPane.add(userLabel);
            sqlInjectionApplicationContentPane.add(passLabel);
            sqlInjectionApplicationContentPane.add(userBox);
            sqlInjectionApplicationContentPane.add(passBox);
            sqlInjectionApplicationContentPane.add(authButton);
                
            // --- finally, set the content pane
            setContentPane(sqlInjectionApplicationContentPane);

            //this.setVisible(true);

        } catch (java.lang.Throwable ivjExc) {
            System.err.println("Exception occurred in initialize() of sqlInjectionApplication");
            ivjExc.printStackTrace(System.out);
        }
    }   // end - method initialize
    
    
    /**
     * Starts the application.
     * @param args an array of command-line arguments
     */
    public static void main(java.lang.String[] args) {
        
        try {           
            BackEnd theBackEnd = new BackEnd();
            FrontEnd theFrontEnd = new FrontEnd(theBackEnd);
            FrontEndView aFrontEndView = new FrontEndView(theFrontEnd);
    
            aFrontEndView.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent e) {
                    System.exit(0);
                };
            });
    
            aFrontEndView.setVisible(true);

        
        } catch (Throwable exception) {
            System.err.println("Exception occurred in main() of FrontEndView");
            exception.printStackTrace(System.out);
        }
    }   // end - method main

}   // end - class FrontEndView
