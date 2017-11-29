class GameLauncher{

    // Creates a new Player object using the no arguments constructor and launches the GUI
    static void launchNewGame(){
        Player player = new Player();

        Framework.player = player;
        Battle.player = player;
        Framework framework = new Framework();

        framework.frame();
    }

    /*
    Reads in the previous game session details and inserts them into the second Player constructor.
    Launches the GUI and displays the last paragraph the user was at when the save was made.
     */
    static void launchLoadedGame(){
        String previousSession = FileManager.readFile();
        System.out.println(previousSession);

        String[] playerAttributes = previousSession.split(" ");
        int[] playerAttributesAsInts = new int[playerAttributes.length];
        for(int i = 0; i < playerAttributes.length; i++){
            playerAttributesAsInts[i] = Integer.parseInt(playerAttributes[i]);
        }

        Player loadedPlayer = new Player(playerAttributesAsInts[0], playerAttributesAsInts[1],
                playerAttributesAsInts[2], playerAttributesAsInts[3], playerAttributesAsInts[4],
                playerAttributesAsInts[5], playerAttributesAsInts[6], playerAttributesAsInts[7],
                playerAttributesAsInts[8]);

        Framework.player = loadedPlayer;
        Battle.player = loadedPlayer;
        Framework framework = new Framework();

        framework.frame();

        framework.changeParagraphImage(playerAttributesAsInts[9]);
    }
}