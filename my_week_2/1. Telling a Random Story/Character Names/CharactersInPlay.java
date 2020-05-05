
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/2/2020)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person){
        person = person.trim();
        //.trim()is a built-in function that eliminates leading and trailing spaces
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            counts.add(1);
        }
        else{
            int value = counts.get(index);
            counts.set(index, value + 1);
        }        
    }
    
    public void findAllCharacters(){
        names.clear();
        counts.clear();
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            int index = line.indexOf(".");
            if(index != -1){
                update(line.substring(0, index));
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        int maxNum = 0;
        int maxIndex = 0;
        int minNum = 5;
        System.out.println("======================================");
        for(int k = 0; k < names.size(); k++){
            if(counts.get(k) > minNum - 1){
                System.out.println("main characters "+names.get(k)+"\t"+counts.get(k));
            }
            if(counts.get(k) > maxNum){
                maxNum = counts.get(k);
                maxIndex = k;
            }
        }
        System.out.println("======================================");
        System.out.println("The character with the most speaking parts: "+names.get(maxIndex)+"\t"+counts.get(maxIndex));
        System.out.println("======================================");
        charactersWithNumParts(10, 20);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("characters with speaking parts between "+num1+" and " +num2);        
        for(int k = 0; k < names.size(); k++){
            if(counts.get(k) >= num1 && counts.get(k) <= num2){
                System.out.println(names.get(k)+"\t"+counts.get(k));
            }
        }        
    }
}
