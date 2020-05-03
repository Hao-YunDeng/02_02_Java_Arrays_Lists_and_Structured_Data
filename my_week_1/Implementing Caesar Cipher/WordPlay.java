
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
    public boolean isVowel(Character ch){
        String vowels = "aeiouAEIOU";
        if(vowels.indexOf(ch) != -1){
            return true;
        }
        return false;
    }
    
    public void testIsVowel(){
        System.out.println(isVowel('a'));
        System.out.println(isVowel('F'));
    }
    
    public String replaceVowels(String phrase, Character ch){
        StringBuilder replaced = new StringBuilder(phrase);
        for(int i = 0; i < replaced.length(); i++){
            if(isVowel(replaced.charAt(i))){
                replaced.setCharAt(i, '*');
            }
        }
        return replaced.toString();
    }
    
    public void testReplaceVowels(){
        String test = "Hello World";
        System.out.println(replaceVowels(test, '*'));
    }
    
    public String emphasize(String phrase, Character ch){
        StringBuilder emphasized = new StringBuilder(phrase);
        for(int i = 0; i < emphasized.length(); i++){
            if( (emphasized.charAt(i) == Character.toUpperCase(ch) || 
            emphasized.charAt(i) == Character.toLowerCase(ch)) && i%2 == 1){
                emphasized.setCharAt(i, '*');
            }
            if( (emphasized.charAt(i) == Character.toUpperCase(ch) || 
            emphasized.charAt(i) == Character.toLowerCase(ch)) && i%2 == 0){
                emphasized.setCharAt(i, '+');
            }            
        }
        return emphasized.toString();
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}

