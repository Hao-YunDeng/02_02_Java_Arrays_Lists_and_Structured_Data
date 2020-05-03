
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/1/2020)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        String halfMessage = "";
        for(int k = start; k < message.length(); k += 2){
            halfMessage += message.charAt(k);
        }
        return halfMessage;
    }    

    private int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] +=1;
            }
        }
        return counts;
    }    
    
    private int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k < vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }                
        }
        return maxDex;
    }    
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        
        System.out.println(breakCaesarCipher(encrypted));
    }   
    
    private int getKey(String s){
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dkey = maxDex - 4;
        if(dkey < 0){
            dkey = dkey + 26;
        }
        return dkey;
    }    
    
    public String breakCaesarCipher(String input){
        String halfString1 = halfOfString(input, 0);
        String halfString2 = halfOfString(input, 1);
        int dkey1 = getKey(halfString1);
        int dkey2 = getKey(halfString2);
        System.out.println("The two keys used are " + dkey1 +", "+ dkey2);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - dkey1, 26 - dkey2); 
        return cc.encrypt(input); 
    }    
    
}
