
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/1/2020)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                          alphabet.substring(0,key);
        mainKey = key;                  
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(Character.isLetter(currChar)){
                int index = alphabet.indexOf(Character.toUpperCase(currChar));
                char newChar = shiftedAlphabet.charAt(index);
                if(Character.isUpperCase(currChar)){
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return  cc.encrypt(input);
    }
}
