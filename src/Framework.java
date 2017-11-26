import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Framework{
    JTextField paraEntryField;
    private JLayeredPane rightPanel;
    private JLabel paragraphImage;
    private ImageIcon imageIcon;
    static JLabel playerSkillLabel, playerStaminaLabel, playerLuckLabel, playerProvisionsLabel;
    static JTextArea battleInfo;

    private static int paragraphNumber = 0;

    static Player player;

    Framework(){
        JFrame jFrame = new JFrame("The Warlock of Firetop Mountain");
        jFrame.setSize(500, 720);
        jFrame.setResizable(false);

        jFrame.setLayout(null);

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
        UseProvisions useProvisionsListener = new UseProvisions();
        useProvisions.addActionListener(useProvisionsListener);

        JLabel paraPrompt = new JLabel("Page number:");
        paraPrompt.setForeground(Color.white);
        leftPanel.add(paraPrompt);

        // Add JTextField and ActionListener
        paraEntryField = new JTextField(4);
        leftPanel.add(paraEntryField);
        TextFieldEventHandler handler = new TextFieldEventHandler();
        paraEntryField.addActionListener(handler);

        // Add JButton and Listener to launch battle
        JButton battleButton = new JButton("Battle!");
        leftPanel.add(battleButton);
        LaunchBattle launchBattle = new LaunchBattle();
        battleButton.addActionListener(launchBattle);

        JButton testLuckButton = new JButton("Test Luck");
        leftPanel.add(testLuckButton);
        LuckTest luckTest = new LuckTest();
        testLuckButton.addActionListener(luckTest);

        JButton saveButton = new JButton("Save");
        leftPanel.add(saveButton);
        SaveProgress saveProgress = new SaveProgress();
        saveButton.addActionListener(saveProgress);


        JLabel backgroundImage = new JLabel(new ImageIcon("../TextGameImages/warlock.jpg"));
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


        jFrame.setVisible(true);
    }

    void frame(){
        playerSkillLabel.setText("Skill: " + player.getSkill());
        playerStaminaLabel.setText("Stamina: " + player.getStamina());
        playerLuckLabel.setText("Luck: " + player.getLuck());
        playerProvisionsLabel.setText("Provisions: " + player.getProvisions());
    }

    // Method which takes in an int as an argument and returns a file path in the form of a String
    private String imageLocation(int paragraphNumber){
        return "../TextGameImages/p" + paragraphNumber + ".PNG";
    }


    // ActionListener which changes paragraph image based on paragraph number entered
    private class TextFieldEventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            paragraphNumber = Integer.parseInt(paraEntryField.getText());
            System.out.println(paragraphNumber);
            if(paragraphNumber > 0 && paragraphNumber <= 400){
                rightPanel.remove(paragraphImage);
                rightPanel.repaint();
                imageIcon = new ImageIcon(imageLocation(paragraphNumber));
                paragraphImage = new JLabel(imageIcon);
                paragraphImage.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
                paragraphImage.setLocation(85, 5);
                rightPanel.setLayer(paragraphImage, 1);
                rightPanel.add(paragraphImage);
                rightPanel.setVisible(true);
                paraEntryField.setText("");
            }
        }
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
                JOptionPane.showMessageDialog(null, "Lucky!!", "Luck Test", JOptionPane.INFORMATION_MESSAGE);
                playerLuckLabel.setText("Luck: " + player.getLuck());
            }else{
                JOptionPane.showMessageDialog(null, "Unlucky :(", "Luck Test", JOptionPane.INFORMATION_MESSAGE);
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
            String compiledPlayerDetails = player.getSkill() + " " + player.getStamina() + " " + player.getLuck()
                    + " " + player.getGold() + " " + player.getPotions() + " " + player.getProvisions() + " " +
                    paragraphNumber + " " + player.getInitialSkill() + " " + player.getInitialStamina() + " " +
                    player.getInitialLuck();
            FileManager.writeToFile(compiledPlayerDetails);
        }
    }
}