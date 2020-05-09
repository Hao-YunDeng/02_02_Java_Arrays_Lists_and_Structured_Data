
/**
 * Write a description of tester here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/7/2020)
 */
import edu.duke.*;
import java.util.*;
public class tester {
    public void testEncrypt(){
        FileResource fr = new FileResource("titus-small.txt");
        VigenereCipher vc = new VigenereCipher(new int[]{17,14,12,4});
        
        System.out.println(vc.encrypt(fr.asString()));
    }
    
    public void testDecrypt(){
        //FileResource fr = new FileResource("secretmessage1.txt");
        //String message = fr.asString();
        String message = "Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm";
        int[] keys = {3, 20, 10, 4};
        VigenereCipher vc = new VigenereCipher(keys);    
        System.out.println(vc.decrypt(message));
    }
    
    public void testToString(){
        int[] keys = new int[]{17,14,12,4};
        VigenereCipher vc = new VigenereCipher(keys); 
        System.out.println(vc.toString());
    }
    
    public void testSliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource("secretmessage1.txt");
        String encrypted = fr.asString(); 
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(encrypted, 4, 'e');
        
        System.out.println(Arrays.toString(keys));
    }
    
    public void testBreakVigenere (){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere ();
    }
    
}
