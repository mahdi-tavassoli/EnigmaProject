import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class enigma{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> reflector = new HashMap<>();
        reflector = reflectorCreator();

        
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