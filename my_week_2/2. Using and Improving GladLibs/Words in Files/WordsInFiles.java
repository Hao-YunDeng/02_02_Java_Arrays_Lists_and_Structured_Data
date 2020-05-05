
/**
 * Write a description of wordsInFiles here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/3/2020)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(!map.keySet().contains(word)){
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(f.getName());
                map.put(word, fileNames);
            }
            else{
                if(!map.get(word).contains(f.getName())){
                    map.get(word).add(f.getName());
                }
            }
        }
    }
    
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int max = 0;
        for(String word : map.keySet()){
            if(map.get(word).size() > max){
                max = map.get(word).size();
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for(String word : map.keySet()){
            if(map.get(word).size() == number){
                wordList.add(word);
            }
        }
        return wordList;
    }
    
    private void printFilesIn(String word){
        for(String file : map.get(word)){
            System.out.println(word + ":\t"+file);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println("maximum number of files a word appears in: "
                            +maxNumber());
                            
        ArrayList<String> wordList = wordsInNumFiles(maxNumber());
        System.out.println("# of words that appear max times "+wordList.size());
        
        ArrayList<String> wordList1 = wordsInNumFiles(4);
        System.out.println("# of words that appear 4 times "+wordList1.size());
        
        System.out.println("sea appears in: ");
        printFilesIn("sea");
        
        System.out.println("tree appears in: ");
        printFilesIn("tree");        
        
        //System.out.println("all the words that are in the" 
        //             +" maximum number of files with the cooresp. files:");
        //for(String word : wordList){
        //    printFilesIn(word);
        //}
        
        //System.out.println("all the words with their cooresp. files:");        
        //for(String word : map.keySet()){
        //    printFilesIn(word);
        //}
    }
}
