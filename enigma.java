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
        String secretString = scanner.next();
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
                    System.out.println(Decipher(secretString,plugBord,Rotor1,Rotor2,Rotor3,reflector));
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
    // the function that rotate the value of a map 
    public static Map<String,String> rotateRotor(Map<String,String> test){
        String lastindex = test.get("z");
        for (int i=122; i>97 ; i--){
            String value = test.get(Character.toString((char) i-1));
            test.replace(Character.toString((char)i),value);
        }
        test.replace("a",lastindex);
        return test;
    }
    // a function that a return the key of a value in a map
    public static String getKey(Map<String,String> map,String letter){
        String key = null;
        for (int i=97 ; i<123 ;i++){
            String letter2 = map.get(Character.toString((char)i));
            if (letter.compareTo(letter2)==0){
                key = Character.toString((char)i);
                return key;
            }
        }
        return key;
    }
    // the most important function that claculate the answer
    public static String Decipher(String main,Map<String,String> plugBord,Map<String,String> rotor1,Map<String,String> rotor2,Map<String,String> rotor3,Map<String,String> reflector){
        int rotateCounter1 = 0;
        int rotateCounter2 = 0;
        int rotateCounter3 = 0;
        String answer = "";
        for (int i=0 ; i<main.length() ; i++){
            String Current = Character.toString(main.charAt(i));
            Current = plugBord.get(Current);
            Current = rotor3.get(Current);
            Current = rotor2.get(Current);
            Current = rotor1.get(Current);
            Current = reflector.get(Current);
            Current = getKey(rotor1,Current);
            Current = getKey(rotor2,Current);
            Current = getKey(rotor3,Current);
            Current = plugBord.get(Current);
            answer+=Current;
            rotateCounter3++;
            rotor3=rotateRotor(rotor3);
            if (rotateCounter3==26){
                rotateCounter3 = 0;
                rotor2 = rotateRotor(rotor2);
                rotateCounter2++;
            }
            if (rotateCounter2==26){
                rotateCounter2 = 0;
                rotor1 = rotateRotor(rotor1);
                rotateCounter1++;
            }
            if (rotateCounter1==26)
                rotateCounter1 = 0;
        }

        return answer;
    }
}