
/**
 * Write a description of CaesarCipherTwoKeysDecrypt here.
 * 
 * @author (Hoayun Deng) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwoKeysDecrypt {
    public int[] countLetters(String message){
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
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k < vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }                
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(dkey < 0){
            dkey = dkey + 26;
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "abc ddddddd";
        System.out.println(decrypt(message));
    }
    
    public String halfOfString(String message, int start){
        String halfMessage = "";
        for(int k = start; k < message.length(); k += 2){
            halfMessage += message.charAt(k);
        }
        return halfMessage;
    }
    
    public void testHalfOfString(){
        String message = "abc";
        System.out.println(halfOfString(message, 1));
    }
    
    public int getKey(String s){
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dkey = maxDex - 4;
        if(dkey < 0){
            dkey = dkey + 26;
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        String halfString1 = halfOfString(encrypted, 0);
        String halfString2 = halfOfString(encrypted, 1);
        int key1 = getKey(halfString1);
        int key2 = getKey(halfString2);
        System.out.println("The two keys used are " + key1 +", "+ key2);
        
        CaesarCipher cc = new CaesarCipher(); 
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2); 
        //return cc.encryptTwoKeys(encrypted, 26 - 14, 26 - 24); 
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(decryptTwoKeys(message));        
    }
  
}
