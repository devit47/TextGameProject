import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Framework{
    JTextField paraEntryField;
    JFrame jFrame;
    JLayeredPane rightPanel;
    JLabel paragraphImage;
    ImageIcon imageIcon;
    static JLabel playerSkillLabel, playerStaminaLabel, playerLuckLabel;

    static Player player;

     public Framework(){
         jFrame = new JFrame("The Warlock of Firetop Mountain");
         jFrame.setSize(500,720);
         jFrame.setResizable(false);

         jFrame.setLayout(null);

         JPanel leftPanel = new JPanel();
         leftPanel.setBackground(Color.black);
         leftPanel.setSize(100, 720);
         jFrame.add(leftPanel);

         rightPanel = new JLayeredPane();
         rightPanel.setBackground(Color.red);
         rightPanel.setSize(400, 720);
         rightPanel.setLocation(100,0);
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

         JLabel bkgrdImage = new JLabel(new ImageIcon("../TextGameImages/skeleton.jpg"));
         bkgrdImage.setSize(rightPanel.getWidth(), rightPanel.getHeight());
         rightPanel.setLayer(bkgrdImage, 0);
         rightPanel.add(bkgrdImage);

         imageIcon = new ImageIcon("../TextGameImages/p1.PNG");
         paragraphImage = new JLabel(imageIcon);
         paragraphImage.setLocation(85, 5);
         paragraphImage.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
         rightPanel.setLayer(paragraphImage, 1);
         rightPanel.add(paragraphImage);

         jFrame.setVisible(true);
     }

    public void frame(){
         playerSkillLabel.setText("Skill: " + player.getSkill());
         playerStaminaLabel.setText("Stamina: " + player.getStamina());
         playerLuckLabel.setText("Luck: " + player.getLuck());
    }

    public String imageLocation(int paragraphNumber){
        return "../TextGameImages/player" + paragraphNumber + ".PNG";
    }

    private class TextFieldEventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int paragraphNumber = Integer.parseInt(paraEntryField.getText());
            System.out.println(paragraphNumber);
            if(paragraphNumber > 0 && paragraphNumber <= 400){
                rightPanel.remove(paragraphImage);
                rightPanel.repaint();
                imageIcon = new ImageIcon(imageLocation(paragraphNumber));
                paragraphImage = new JLabel(new ImageIcon(imageLocation(paragraphNumber)));
                paragraphImage.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
                paragraphImage.setLocation(85, 5);
                rightPanel.setLayer(paragraphImage, 1);
                rightPanel.add(paragraphImage);
                rightPanel.setVisible(true);
                paraEntryField.setText("");
            }
        }
    }

    private class LaunchBattle implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Battle.manageBattle();
        }
    }

    private class LuckTest implements ActionListener{
        public void actionPerformed(ActionEvent e){
            GameDriver.testLuckForButton(player);
            playerLuckLabel.setText("Luck: " + player.getLuck());
        }
    }
}