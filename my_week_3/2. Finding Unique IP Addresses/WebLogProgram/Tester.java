
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
    
    public void testUniqueIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        System.out.println("unique IPs "+analyzer.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log"); 
        analyzer.printAllHigherThanNum(400);
    }
    
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        ArrayList<String> IPVisit1 = analyzer.uniqueIPVisitsOnDay("Mar 17");
        ArrayList<String> IPVisit2 = analyzer.uniqueIPVisitsOnDay("Sep 30");
        for(String s : IPVisit1){
            System.out.println("IPs on mar 17 "+s);
        }
        
        for(String s : IPVisit2){
            System.out.println("IPs on sep 30 "+s);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        System.out.println("# of IPs in range (200,299) "
                           + analyzer.countUniqueIPsInRange(200, 299));
        System.out.println("# of IPs in range (300,399) "
                           + analyzer.countUniqueIPsInRange(300, 399));                   
    }
}
