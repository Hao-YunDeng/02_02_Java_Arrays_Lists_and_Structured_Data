
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             //WebLogParser parser = new WebLogParser();
             //LogEntry le = parser.parseEntry(line);
             //没必要这样写，因为所引用的method是static，可以不必提前定义变量。
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         ArrayList<String> IPVisit = new ArrayList<String>();
         for(LogEntry le : records){
             if(le.getStatusCode() > num){
                 String ipAddr = le.getIpAddress();
                 System.out.println(le);
             }   
         } 
     }
     
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> IPVisit = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             if(date.contains(someday)){
                 String ipAddr = le.getIpAddress();
                 if(!IPVisit.contains(ipAddr)){
                     IPVisit.add(ipAddr);
                 }
             }   
         }
         return IPVisit;    
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             int code = le.getStatusCode();
             if(code >= low && code <= high && !uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
}
