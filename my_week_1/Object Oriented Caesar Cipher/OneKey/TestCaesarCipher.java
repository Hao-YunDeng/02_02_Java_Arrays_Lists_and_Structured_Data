
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/1/2020)
 */
import edu.duke.*;
public class TestCaesarCipher {
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
        CaesarCipher cc = new CaesarCipher(15);
        
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        
        System.out.println(breakCaesarCipher(encrypted));
    }
    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(dkey < 0){
            dkey = dkey + 26;
        } 
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
}
