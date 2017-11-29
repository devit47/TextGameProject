import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WelcomeMenuGUI{

    private JFrame welcomeJFrame;

    WelcomeMenuGUI(){
        welcomeJFrame = new JFrame("The Warlock of Firetop Mountain");
        welcomeJFrame.setSize(400, 400);
        welcomeJFrame.setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        welcomeJFrame.setLocation((dim.width / 2) - (welcomeJFrame.getWidth() / 2),
                (dim.height / 2) - (welcomeJFrame.getHeight() / 2));

        welcomeJFrame.setLayout(null);
        welcomeJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.setSize(welcomeJFrame.getSize());
        welcomeJFrame.add(jLayeredPane);

        JLabel backgroundImage = new JLabel(new ImageIcon("../TextGameImages/mainMenuImage.jpg"));
        backgroundImage.setSize(welcomeJFrame.getWidth(), welcomeJFrame.getHeight());
        jLayeredPane.setLayer(backgroundImage, 1);
        jLayeredPane.add(backgroundImage);

        JLabel title = new JLabel("The Warlock of Firetop Mountain");
        title.setSize(200,100);
        title.setLocation(100, 100);
        jLayeredPane.setLayer(title, 2);
        jLayeredPane.add(title);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setSize(180,30);
        newGameButton.setLocation(110, 250);
        NewGameLauncher newGameLauncher = new NewGameLauncher();
        newGameButton.addActionListener(newGameLauncher);
        jLayeredPane.setLayer(newGameButton, 2);
        jLayeredPane.add(newGameButton);

        JButton loadGameButton = new JButton("Load Previous Game");
        loadGameButton.setSize(180,30);
        loadGameButton.setLocation(110, 305);
        LoadGameLauncher loadGameLauncher = new LoadGameLauncher();
        loadGameButton.addActionListener(loadGameLauncher);
        jLayeredPane.setLayer(loadGameButton, 2);
        jLayeredPane.add(loadGameButton);

        welcomeJFrame.setVisible(true);
    }

    // Calls the launchNewGame method and disposes of the JFrame
    private class NewGameLauncher implements ActionListener {
        public void actionPerformed(ActionEvent e){
            GameLauncher.launchNewGame();
            welcomeJFrame.dispose();
        }
    }

    // Calls the launchLoadedGame method and disposes of the JFrame
    private class LoadGameLauncher implements ActionListener {
        public void actionPerformed(ActionEvent e){
            GameLauncher.launchLoadedGame();
            welcomeJFrame.dispose();
        }
    }
}