
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
        for(String line: fr.lines()){
            records.add(WebLogParser.parseEntry(line));
        }  
    }
    
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr)){   // equality check!!
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
            System.out.println(le.getAccessTime().toString());
        }
    }   

    public void printAllHigherThanNum(int num){
        for(LogEntry le: records){
            int n = le.getStatusCode();            
            if(n > num){
                System.out.println(n);
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> arr = new ArrayList<String>();
        for(LogEntry le: records){                 
            String str = le.getIpAddress();
            if(!arr.contains(str)){                 // unique?
                String day = le.getAccessTime().toString();
                if(day.contains(someday)){          // contain?
                    arr.add(str); 
                    System.out.println(le.toString());                   
                }                
            }
        }
        return arr;
    }

    public int countUniqueIPsInRange (int low, int high){
        int count = 0;
        ArrayList<String> ar = new ArrayList<String>();
        for(LogEntry le: records){
            String str = le.getIpAddress();
            int n = le.getStatusCode();
            if(!ar.contains(str)){                
                if(n>=low && n<=high){
                    count += 1;
                    ar.add(str);
                }
            }
        }
        return count;
    }

    public HashMap<String, Integer> countVisitPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le: records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }else{
                counts.put(ip, counts.get(ip)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> mp){
        int maxNum = 0;
        for(String k: mp.keySet()){
            int freq = mp.get(k);
            if(freq > maxNum){
                maxNum = freq;
            }
        }
        return maxNum;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> mp){
        int maxValue = mostNumberVisitsByIP(mp);
        ArrayList<String> al = new ArrayList<String>();
        for(String k: mp.keySet()){
            int value = mp.get(k);
            if(value == maxValue){
                al.add(k);
            }
        }
        return al;
    }    

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> m = new HashMap<String,ArrayList<String>>();            
        for (LogEntry le: records){
            String day = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
            ArrayList<String> al = new ArrayList<String>();
            if(!m.containsKey(day)){                
                al.add(ip);  
                m.put(day,al);
            }else{  
                ArrayList<String> a = m.get(day); 
                a.add(ip);       
                m.put(day,a);
            }
        }
        return m;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> m){
        int maxVisit = 0;
        String maxVisitDay = "";
        for(String day: m.keySet()){
            if(m.get(day).size() > maxVisit){
                maxVisit = m.get(day).size();
                maxVisitDay = day;
            }
        }
        return maxVisitDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> pm, String someday){
        HashMap<String,Integer> m = new HashMap<String,Integer>();
        for(String day: pm.keySet()){
            if(day.equals(someday)){
                for(String ip: pm.get(day)){
                    if(!m.containsKey(ip)){
                        m.put(ip,1);
                    }else{
                        m.put(ip,m.get(ip)+1);
                    }
                }               
            }
        }
        return iPsMostVisits(m);
    }
}
