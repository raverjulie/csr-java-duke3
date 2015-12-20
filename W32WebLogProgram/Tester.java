
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

    public void test_printAll(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        
        System.out.println(new Date());
    }

    public void test_printAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void test_uniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ar = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Sep 27: "+ ar.size());
    }

    public void test_countUniqueIPs(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }

    public void test_countUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int n1 = la.countUniqueIPsInRange(400,499);
        System.out.println("400~499: "+n1);
    }

    public void test_countVisitPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitPerIP();
        System.out.println(counts);
    }

    public void test_mostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> m = la.countVisitPerIP();
        int maxValue = la.mostNumberVisitsByIP(m);
        System.out.println(maxValue);
    }

    public void test_iPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> m = la.countVisitPerIP();
        ArrayList<String> al = la.iPsMostVisits(m);
        for(String ip: al){
            System.out.println(ip);
        }
    }

    public void test_iPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> m = la.iPsForDays();
        for(String day: m.keySet()){
            System.out.println("day: " + day + " - counts: " + m.get(day).size());
            for(String ip: m.get(day)){
                System.out.println("ip: " + ip);
            }
        }
    }

    public void test_dayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> m = la.iPsForDays();
        String maxVisitDay = la.dayWithMostIPVisits(m);
        System.out.println(maxVisitDay);
    }

    public void test_iPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> m = la.iPsForDays();
        ArrayList<String> al = la.iPsWithMostVisitsOnDay(m,"Sep 29");
        for(String s: al){
            System.out.println(s);
        }
    }
}
