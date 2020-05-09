
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
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.keySet().contains(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(int num : counts.values()){
             if(num > max){
                 max = num;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> mostVst = new ArrayList<String>();
         int max = 0;
         for(int k : counts.values()){
             if(k > max){
                 max = k;
             }
         }
         for(String ip : counts.keySet()){
             if(counts.get(ip) == max){
                 mostVst.add(ip);
             }
         }
         return mostVst;
     }
     
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> daysIpList = new HashMap<String, ArrayList<String>>();
         
         for(LogEntry le : records){
             String date = le.getAccessTime().toString().substring(4,10);
             String ip = le.getIpAddress();
             if(!daysIpList.keySet().contains(date)){
                 ArrayList<String> ipList = new ArrayList<String>();
                 ipList.add(ip);
                 daysIpList.put(date, ipList);
             }
             else{
                 daysIpList.get(date).add(ip); 
             }
         }
         return daysIpList;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIpList){
         int max = 0;
         String dateMax = "";
         for(String date : daysIpList.keySet()){
             if(daysIpList.get(date).size() > max){
                 max = daysIpList.get(date).size();
                 dateMax = date;
             }
         }
         return dateMax;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIpList,String date){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(String ip : daysIpList.get(date)){
             if(!counts.containsKey(ip)){  
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return iPsMostVisits(counts);
     }
     
}
