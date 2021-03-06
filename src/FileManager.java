import java.io.*;

class FileManager{

    private static final String FILENAME = "../TextGameParagraphs/file.txt";

    // Ref #1 start
    /**
     * Writes a string to a text file and overwrites its previous contents
     */
    static void writeToFile(String input){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))){

            bw.write(input);
            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Appends a string to a text file
     */
    static void appendToFile(String input){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))){

            bw.write(input);
            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads in the last saved game details
     */
    static String readFile(){
        BufferedReader br = null;
        String line2 = "";

        try{
            br = new BufferedReader(new FileReader(FILENAME));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        String line;
        try{
            while((line = br.readLine()) != null){
                line2 = line;
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return line2;
    }
    // Ref #1 end
}