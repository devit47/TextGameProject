import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Framework{
    JTextField jTextField;
    JFrame jFrame;
    JLabel paragraphImage;
    ImageIcon imageIcon;

    public void frame(){
        jFrame = new JFrame("The Warlock of Firetop Mountain");
        jFrame.setSize(500, 800);
        jFrame.setResizable(false);

        // jFrame.setLayout(null);

//        FlowLayout flowLayout = new FlowLayout();
//        jFrame.setLayout(flowLayout);

        JLabel paraPrompt = new JLabel("Enter page number:");
        paraPrompt.setLocation(10,10);
        paraPrompt.setSize(150,20);
        paraPrompt.setBackground(Color.GREEN);
        jFrame.add(paraPrompt);

        jTextField = new JTextField(15);
        jTextField.setLocation(160,10);
        jTextField.setSize(100,20);
        jFrame.add(jTextField);

        imageIcon = new ImageIcon("../TextGameImages/p1.PNG");
        paragraphImage = new JLabel(imageIcon);
        jFrame.add(paragraphImage);

        jFrame.setVisible(true);

        TextFieldEventHandler handler = new TextFieldEventHandler();
        jTextField.addActionListener(handler);

    }

    public String imageLocation(int paragraphNumber){
        return "../TextGameImages/p" + paragraphNumber + ".PNG";
    }

    private class TextFieldEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int paragraphNumber = Integer.parseInt(jTextField.getText());
            System.out.println(paragraphNumber);
            if(paragraphNumber > 0 && paragraphNumber < 300){
                jFrame.remove(paragraphImage);
                jFrame.repaint();
                jFrame.setVisible(true);
                imageIcon = new ImageIcon(imageLocation(paragraphNumber));
                paragraphImage = new JLabel(imageIcon);
                jFrame.add(paragraphImage);
                jFrame.setVisible(true);
                jTextField.setText("");
            }
        }
    }
}