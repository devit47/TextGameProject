import javax.swing.*;
import javax.swing.JMenuBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Framework{
    private JTextField paraEntryField;
    private static JLayeredPane rightPanel;
    private static JLabel paragraphImage;
    private static ImageIcon imageIcon;
    static JLabel playerSkillLabel, playerStaminaLabel, playerLuckLabel, playerProvisionsLabel, backgroundImage;
    static JTextArea battleInfo;
    private static JMenu fileMenu;

    private static int paragraphNumber = 1;

    static Player player;

    Framework(){
        JFrame jFrame = new JFrame("The Warlock of Firetop Mountain");
        jFrame.setSize(500, 720);
        jFrame.setResizable(false);

        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((dim.width / 2) - (jFrame.getWidth() / 2),
                (dim.height / 2) - (jFrame.getHeight() / 2));

        createFileMenu();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLocation(350,0);
        menuBar.setSize(50, 20);
        menuBar.add(fileMenu);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.black);
        leftPanel.setSize(100, 720);
        jFrame.add(leftPanel);

        rightPanel = new JLayeredPane();
        rightPanel.setBackground(Color.red);
        rightPanel.setSize(400, 720);
        rightPanel.setLocation(100, 0);
        jFrame.add(rightPanel);

        playerSkillLabel = new JLabel();
        playerSkillLabel.setForeground(Color.white);
        leftPanel.add(playerSkillLabel);

        playerStaminaLabel = new JLabel();
        playerStaminaLabel.setForeground(Color.white);
        leftPanel.add(playerStaminaLabel);

        playerLuckLabel = new JLabel();
        playerLuckLabel.setForeground(Color.white);
        leftPanel.add(playerLuckLabel);

        playerProvisionsLabel = new JLabel();
        playerProvisionsLabel.setForeground(Color.white);
        leftPanel.add(playerProvisionsLabel);

        JButton useProvisions = new JButton("Use Provisions");
        leftPanel.add(useProvisions);
        useProvisions.addActionListener(new UseProvisions());

        JLabel paraPrompt = new JLabel("Page number:");
        paraPrompt.setForeground(Color.white);
        leftPanel.add(paraPrompt);

        // Add JTextField and ActionListener
        paraEntryField = new JTextField(4);
        leftPanel.add(paraEntryField);
        paraEntryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(Misc.checkIfInteger(paraEntryField.getText())){
                    paragraphNumber = Integer.parseInt(paraEntryField.getText());
                    System.out.println(paragraphNumber);

                }
                changeParagraphImage(paragraphNumber);
                paraEntryField.setText("");
            }
        });

        // Add JButton and Listener to launch battle
        JButton battleButton = new JButton("Battle!");
        leftPanel.add(battleButton);
        battleButton.addActionListener(new LaunchBattle());

        JButton testLuckButton = new JButton("Test Luck");
        leftPanel.add(testLuckButton);
        testLuckButton.addActionListener(new LuckTest());

        backgroundImage = new JLabel(new ImageIcon("../TextGameImages/bkgrd3.jpg"));
        backgroundImage.setSize(rightPanel.getWidth(), rightPanel.getHeight());
        rightPanel.setLayer(backgroundImage, 0);
        rightPanel.add(backgroundImage);

        // Displays the initial paragraph image using an imageIcon and JLabel
        imageIcon = new ImageIcon("../TextGameImages/p1.PNG");
        paragraphImage = new JLabel(imageIcon);
        paragraphImage.setLocation(85, 5);
        paragraphImage.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        rightPanel.setLayer(paragraphImage, 1);
        rightPanel.add(paragraphImage);

        battleInfo = new JTextArea();
        JScrollPane scrPane = new JScrollPane(battleInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrPane.setLocation(0,600);
        scrPane.setSize(350, 90);
        rightPanel.setLayer(scrPane, 2);
        rightPanel.add(scrPane);

        rightPanel.setLayer(menuBar, 2);
        rightPanel.add(menuBar);

        jFrame.setVisible(true);
    }

    // Displays the player initial values of skill, stamina, luck and provisions
    void frame(){
        playerSkillLabel.setText("Skill: " + player.getSkill());
        playerStaminaLabel.setText("Stamina: " + player.getStamina());
        playerLuckLabel.setText("Luck: " + player.getLuck());
        playerProvisionsLabel.setText("Provisions: " + player.getProvisions());
    }

    // Method which takes in an int as an argument and returns a file path in the form of a String
    private static String imageLocation(int paragraphNumber){
        return "../TextGameImages/p" + paragraphNumber + ".PNG";
    }

    // ActionListener which changes paragraph image based on paragraph number entered
    private class TextFieldEventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(Misc.checkIfInteger(paraEntryField.getText())){
                paragraphNumber = Integer.parseInt(paraEntryField.getText());
                System.out.println(paragraphNumber);

            }
            changeParagraphImage(paragraphNumber);
            paraEntryField.setText("");
        }
    }

    // Changes the paragraph image than is displayed on the GUI
    void changeParagraphImage(int paragraphNumber){
        if(paragraphNumber > 0 && paragraphNumber <= 400){
            rightPanel.remove(paragraphImage);
            rightPanel.repaint();
            imageIcon = new ImageIcon(imageLocation(paragraphNumber));
            paragraphImage = new JLabel(imageIcon);
            paragraphImage.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            paragraphImage.setLocation(85, 5);
            rightPanel.setLayer(paragraphImage, 1);
            rightPanel.add(paragraphImage);
            changebkgrdImage();
            rightPanel.setVisible(true);
        }
    }

    // Changes the background image randomly when called
    void changebkgrdImage(){
        rightPanel.remove(backgroundImage);
        backgroundImage = new JLabel(new ImageIcon("../TextGameImages/bkgrd" + Misc.rollDice() + ".jpg"));
        backgroundImage.setSize(rightPanel.getWidth(), rightPanel.getHeight());
        rightPanel.setLayer(backgroundImage, 0);
        rightPanel.add(backgroundImage);
    }

    // ActionListener which launches the manageBattle method from the Battle class
    private class LaunchBattle implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Battle.manageBattle();
        }
    }

    // ActionListener which calls the testLuck method in the Player class if the player has enough Luck
    private class LuckTest implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(player.testLuck()){
                battleInfo.append("\nYou were lucky!!!");
                playerLuckLabel.setText("Luck: " + player.getLuck());
            }else{
                battleInfo.append("\nYou were unlucky :(");
                playerLuckLabel.setText("Luck: " + player.getLuck());
            }
        }
    }

    // ActionListener which allows the player to use provisions if they have any
    private class UseProvisions implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(player.hasProvisions()){
                player.useProvisions();
                playerProvisionsLabel.setText("Provisions: " + player.getProvisions());
                playerStaminaLabel.setText("Stamina: " + player.getStamina());
            }else{
                JOptionPane.showMessageDialog(null, "No provisions left");
            }
        }
    }

    // Saves player details to text file
    private class SaveProgress implements ActionListener{
        public void actionPerformed(ActionEvent e){
            FileManager.writeToFile(player.playerAttributeValues());
            FileManager.appendToFile(Integer.toString(paragraphNumber));
        }
    }

    // Exits the program when executed
    private class QuitGame implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    // Creates the file menu
    private void createFileMenu(){
        SaveProgress saveProgress = new SaveProgress();
        QuitGame quitGame = new QuitGame();

        // Create menu button
        fileMenu = new JMenu("File");

        // Declares a menu item (re-usable)
        JMenuItem item;

        // Add Save tab and ActionListener
        item = new JMenuItem("Save");
        item.addActionListener(saveProgress);
        fileMenu.add(item);

        // Add Quit tab and ActionListener
        item = new JMenuItem("Quit");
        item.addActionListener(quitGame);
        fileMenu.add(item);
    }
}