import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for(int k = whichSlice; k < message.length(); k+=totalSlices){
            answer.append(message.charAt(k));
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] slices = new String[klength]; 
        for(int k = 0; k < klength; k++){
            slices[k] = sliceString(encrypted, k, klength);
            
            CaesarCracker cc = new CaesarCracker();
            key[k] = cc.getKey(slices[k]);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource frMessg = new FileResource("secretmessage4.txt");
        String encrypted = frMessg.asString(); 
        
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        String[] languageList = {"Danish", "Dutch", "English", "French", "German", 
                                              "Italian", "Portuguese", "Spanish"};
        for(String s : languageList){
            FileResource frDict = new FileResource("dictionaries/"+s);
            HashSet<String> dictionary = readDictionary(frDict);
            languages.put(s, dictionary);
        }

        String decrypted = breakForAllLangs(encrypted,languages);
        
        System.out.println(decrypted.split("\n")[0]);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String word : fr.lines()){
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int max = 0;
        int[] key = null;
        char mostCommChar = mostCommonCharIn(dictionary);
        StringBuilder decrypted = new StringBuilder();
        for(int klength = 1; klength < 100; klength++){
            int[] tryKey = tryKeyLength(encrypted, klength, mostCommChar);
            VigenereCipher vc = new VigenereCipher(tryKey);           
            String tryDecrypted = vc.decrypt(encrypted);
            int count = countWords(tryDecrypted, dictionary);            
            if(count > max){
                max = count;
                key = tryKey;
                decrypted = new StringBuilder(tryDecrypted);
            }
        }        
        //System.out.println(Arrays.toString(key));
        //System.out.println(key.length);
        //System.out.println(max);
        return decrypted.toString();       
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charCounts = new HashMap<Character,Integer>();
        for(String word : dictionary){
            for(int k = 0; k < word.length(); k++){
                char letter = word.charAt(k);
                if(!charCounts.containsKey(letter)){
                    charCounts.put(letter, 1);
                }
                else{
                    charCounts.put(letter, charCounts.get(letter)+1);
                }
            }    
        }
        int maxCount = 0;
        char maxLetter = '\0';
        for(char letter : charCounts.keySet()){
            if(charCounts.get(letter) > maxCount){
                maxCount = charCounts.get(letter);
                maxLetter = letter;
            }
        }
        return maxLetter;    
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int max = 0;
        StringBuilder decrypted = new StringBuilder();
        StringBuilder language = new StringBuilder();
        for(String tryLanguage : languages.keySet()){
            HashSet<String> dictionary = languages.get(tryLanguage);
            String tryDecrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(tryDecrypted, dictionary);
            if(count > max){
                max = count;
                decrypted = new StringBuilder(tryDecrypted);
                language = new StringBuilder(tryLanguage);
            }
        }
        return "The language: "+ language + ", and the message:\t"+ decrypted.toString();
    }
    
}
