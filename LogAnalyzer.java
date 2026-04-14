
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
        
        //Question 5
        
    }
    public LogAnalyzer(String filename)
    {
        hourCounts = new int [HOURS_PER_DAY];
        reader = new LogfileReader(filename);
    }
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        //for(int hour = 0; hour < hourCounts.length; hour++) {
         //   System.out.println(hour + ": " + hourCounts[hour]);
         
        int hour = 0;
        while(hour < hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
        }
    
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    
    public int numberOfAcess(){
        //question 6-7
        int total = 0;
        for(int hours = 0; total < hourCounts.length; hours++){
            total+= hourCounts[hours];
        }
        return total;
    }
    
    public int busiestHour(){
        int busiest = 0;
        for(int hours = 1; hours < hourCounts.length; hours++){
            if(hourCounts[hours] > hourCounts[busiest]){
                busiest = hours;
            }
        }
        return busiest;
    }
    
    public int quietestHour(){
        int quietest = 0;
        for(int hours = 1; hours < hourCounts.length; hours++){
            if(hourCounts[hours] < hourCounts[quietest]){
                quietest = hours;
            }
        }
        return quietest;
    }
    
    public int twoBusiestHours(){
        int busiest = 0;
        int consicutiveHour = hourCounts[0] + hourCounts[1];
        for(int hour = 1; hour < hourCounts.length - 1; hour++){
           int total = hourCounts[hour] + hourCounts[hour + 1];
           if(total > consicutiveHour){
               consicutiveHour = total;
               busiest = hour;
           }
           
        }
        return busiest;
    }
}
