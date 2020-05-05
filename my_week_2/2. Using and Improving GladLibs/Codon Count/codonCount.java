
/**
 * Write a description of CodonCount here.
 * 
 * @author (Haoyun Deng) 
 * @version (5/3/2020)
 */
import java.util.*;
import edu.duke.*;
public class codonCount {
    private HashMap<String, Integer> map;
    public codonCount(){
        map = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        map.clear();
        for(int k = start; k < dna.length() - 2; k+=3){
            String codon = dna.substring(k, k+3);
            if(map.keySet().contains(codon)){
                map.put(codon, map.get(codon)+1);
            }
            else{
                map.put(codon, 1);
            }
        }
    }
    
    private void printCodonCounts(int start, int end){
        for(String s : map.keySet()){
            if(map.get(s) >= start && map.get(s) <= end ){
                System.out.println(s.toUpperCase() + "\t" + map.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toLowerCase().trim();
        int max = 0;
        String mostCommCodon = "";
        for(int k = 0; k < 3; k++){
            buildCodonMap(k, dna);
            System.out.println("Reading frame starting with "
                               +k+" results in "+map.size()+" unique codons");
            for(String s : map.keySet()){
                if(map.get(s) > max){
                    max = map.get(s);
                    mostCommCodon = s;
                }
            }
            System.out.println("and most common codon is "+mostCommCodon.toUpperCase()
                                +" with count "+max);   
            System.out.println("Counts of codons between 7 and 7 inclusive are: "); 
            printCodonCounts(7, 7);
            System.out.print("==============================\n");
        }
    }
}
