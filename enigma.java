import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class enigma{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> reflector = new HashMap<>();
        Map<String,String> plugBord = new HashMap<>();
        Map<String,String> Rotor1 = new HashMap<>();
        Map<String,String> Rotor2 = new HashMap<>();
        Map<String,String> Rotor3 = new HashMap<>();
        reflector = reflectorCreator();
        String date = scanner.next();
        File dateFile = new File("E:\\season-4-UNI\\data structures and algorithms\\Enigma\\src\\EnigmaFile.txt");
        try {
            Scanner fileReader = new Scanner(dateFile);
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                if (line.contains(date)){
                    String Str1= fileReader.nextLine().substring(12,42);
                    plugBord = plugBordCreator(Str1);
                    String rotorStr1 = fileReader.nextLine().substring(9,35);
                    Rotor1 = setRotor(rotorStr1);
                    String rotorStr2 = fileReader.nextLine().substring(9,35);
                    Rotor2 = setRotor(rotorStr2);
                    String rotorStr3 = fileReader.nextLine().substring(9,35);
                    Rotor3 = setRotor(rotorStr3);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // create plugbord map and map the values 
    public static Map<String,String> plugBordCreator(String str){
        Map<String,String> plugBord = new HashMap<>();
        String[] array = new String[8];
        array = str.split(",");
        String[] part = new String[2];
        for (int i=0 ; i<array.length ; i++){
            part = array[i].trim().split("");
            plugBord.put(part[0],part[1]);
            plugBord.put(part[1],part[0]);
        }
        for (int x=97 ; x<123 ; x++){
            char alfabet = (char) x;
            String charTostr = Character.toString(alfabet);
            if (plugBord.get(charTostr)==null)
                plugBord.put(charTostr,charTostr);
        }
        return plugBord;
    }
    // read from file and set the Rotores
    public static Map<String,String> setRotor(String str){
        Map<String,String> rotor = new HashMap<>();
        String[] alfabeta = new String[26];
        alfabeta = str.split("");
        for (int i=97 ; i<123 ; i++){
            char CHAR = (char) i;
            rotor.put(Character.toString(CHAR),alfabeta[i-97]);
        }
        return rotor;
    }
    //set reflector base on the Project
     public static Map<String,String> reflectorCreator(){
        Map<String,String> reflector = new HashMap<>();
        int inverse = 122;
        for (int i = 97; i<123 ; i++){
            char first = (char) i;
            char second = (char) inverse;
            inverse--;
            reflector.put(Character.toString(first),Character.toString(second));
        }
        return reflector;
    }
}