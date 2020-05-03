
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/1/2020)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;    
    private int mainKey2;    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) +
                          alphabet.substring(0,key1);   
        shiftedAlphabet2 = alphabet.substring(key2) +
                          alphabet.substring(0,key2); 
        mainKey1 = key1;                          
        mainKey2 = key2;                  
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            if(i % 2 == 0){
                char currChar = encrypted.charAt(i);
                if(Character.isLetter(currChar)){
                    int index = alphabet.indexOf(Character.toUpperCase(currChar));
                    char newChar = shiftedAlphabet1.charAt(index);
                    if(Character.isUpperCase(currChar)){
                        encrypted.setCharAt(i, newChar);
                    }
                    else{
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                }
            }
            else{
                char currChar = encrypted.charAt(i);
                if(Character.isLetter(currChar)){
                    int index = alphabet.indexOf(Character.toUpperCase(currChar));
                    char newChar = shiftedAlphabet2.charAt(index);
                    if(Character.isUpperCase(currChar)){
                        encrypted.setCharAt(i, newChar);
                    }
                    else{
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                }
            }            
        }
        return encrypted.toString();
    } 
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return  cc.encrypt(input);
    }    
}
