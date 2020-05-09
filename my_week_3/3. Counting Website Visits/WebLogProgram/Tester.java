
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        analyzer.printAll();
    }
    
    public void testCountUniqueIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        System.out.println("unique IPs "+analyzer.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log"); 
        analyzer.printAllHigherThanNum(400);
    }
    
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        ArrayList<String> IPVisit1 = analyzer.uniqueIPVisitsOnDay("Mar 17");
        ArrayList<String> IPVisit2 = analyzer.uniqueIPVisitsOnDay("Sep 27");
        for(String s : IPVisit1){
            System.out.println("IPs on mar 17 "+s);
        }
        
        for(String s : IPVisit2){
            System.out.println("IPs on sep 27 "+s);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        System.out.println("# of IPs in range (200,299) "
                           + analyzer.countUniqueIPsInRange(200, 299));
        System.out.println("# of IPs in range (400,499) "
                           + analyzer.countUniqueIPsInRange(400, 499));                   
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");  
        System.out.println("All IP visits: " + analyzer.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log"); 
        System.out.println("Max visits from single IP: "
                           + analyzer.mostNumberVisitsByIP(analyzer.countVisitsPerIP()));         
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log"); 
        System.out.println("Most visits: " 
                           + analyzer.iPsMostVisits(analyzer.countVisitsPerIP()));
    }
    
    public void testIPsForDays(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log"); 
        System.out.println("visits everyday: ");
        for(String s : analyzer.iPsForDays().keySet()){
            System.out.println(s + "\t" + analyzer.iPsForDays().get(s));
        }
        
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        System.out.println("day with most visits: " 
                            + analyzer.dayWithMostIPVisits(analyzer.iPsForDays()));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log"); 
        System.out.println("ip with visits on sep 30: "
                           +analyzer.iPsWithMostVisitsOnDay(analyzer.iPsForDays(),"Sep 30"));        
    }
}
