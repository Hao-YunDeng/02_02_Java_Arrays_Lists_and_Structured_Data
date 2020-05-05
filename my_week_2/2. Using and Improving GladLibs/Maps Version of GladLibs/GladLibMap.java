import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWord;
    private ArrayList<String> usedCategory;
    private int totalReplaced;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        usedCategory = new ArrayList<String>(); 
        myRandom = new Random();
        usedWord = new ArrayList();
        totalReplaced = 0;
        
        initializeFromSource(dataSourceDirectory);
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective",
                           "name", "color", "timeframe", "verb", "fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!usedCategory.contains(label)) {
            usedCategory.add(label);
        }        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if(myMap.containsKey(label)){
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }       
    
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWord.contains(sub) && !sub.equals("**UNKNOWN**")){
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWord.add(sub);
        totalReplaced++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int totalWords = 0;
        for(String s : myMap.keySet()){
            totalWords += myMap.get(s).size();
        }
        return totalWords;
    }
    
    private int totalWordsConsidered(){
        int totalConsidered = 0;
        //for(String s : usedCategory){  
        //    totalConsidered += myMap.get(s).size();
        //    这里不能这样的原因是，s 可能等于"number", 而myMap里面没有这一项。
        //}
        for(String s : myMap.keySet()){
            if(usedCategory.contains(s)){
                totalConsidered += myMap.get(s).size();
            }
        }    
        return totalConsidered;     
    }
    
    public void makeStory(){
        usedWord.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\n=================================");
        System.out.println("total number of random words used: "+totalReplaced);
        System.out.println("total number of words to choose from: "+totalWordsInMap());
        System.out.println("total number of words considered: "+totalWordsConsidered());
    }
    


}
