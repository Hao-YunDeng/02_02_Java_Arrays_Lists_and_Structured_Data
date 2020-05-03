
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        String shiftedAlphabetUpper = alphabetUpper.substring(key)+
        alphabetUpper.substring(0,key);  
        String shiftedAlphabetLower = alphabetLower.substring(key)+
        alphabetLower.substring(0,key); 
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idxUpper = alphabetUpper.indexOf(currChar);
            if(idxUpper != -1){
                char newChar = shiftedAlphabetUpper.charAt(idxUpper);
                encrypted.setCharAt(i, newChar);
            }
            
            int idxLower = alphabetLower.indexOf(currChar);
            if(idxLower != -1){
                char newChar = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 8;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //String message = "First Legion";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }    
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        
        String shiftedAlphabetUpper1 = alphabetUpper.substring(key1)+
        alphabetUpper.substring(0,key1);  
        String shiftedAlphabetLower1 = alphabetLower.substring(key1)+
        alphabetLower.substring(0,key1); 
        
        String shiftedAlphabetUpper2 = alphabetUpper.substring(key2)+
        alphabetUpper.substring(0,key2);  
        String shiftedAlphabetLower2 = alphabetLower.substring(key2)+
        alphabetLower.substring(0,key2);       
        
        for(int i = 0; i < encrypted.length(); i++) {
            if(i % 2 == 0){
                char currChar = encrypted.charAt(i);
                int idxUpper = alphabetUpper.indexOf(currChar);
                if(idxUpper != -1){
                    char newChar = shiftedAlphabetUpper1.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }
            
                int idxLower = alphabetLower.indexOf(currChar);
                if(idxLower != -1){
                    char newChar = shiftedAlphabetLower1.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                char currChar = encrypted.charAt(i);
                int idxUpper = alphabetUpper.indexOf(currChar);
                if(idxUpper != -1){
                    char newChar = shiftedAlphabetUpper2.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }
            
                int idxLower = alphabetLower.indexOf(currChar);
                if(idxLower != -1){
                    char newChar = shiftedAlphabetLower2.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
            }            
        }
        return encrypted.toString();
    }

    public void testEncryptTwoKeys() {
        int key1 = 8;
        int key2 = 21;        
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String message = "First Legion";
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("keys are " + key1 + ", " + key2 + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }     
}
