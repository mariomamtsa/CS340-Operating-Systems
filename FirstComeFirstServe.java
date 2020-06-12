import java.util.ArrayList;
import java.util.List;

public class FirstComeFirstServe 
{
    
    public static Process getNextActiveProcess(List<Process> processes)
    {
        
        Process result = null;
        
        ArrayList<Process> activeProcesses = new ArrayList<Process>();
        
        for(int i = 0; i < processes.size(); ++i)
        {
            
            Process process = processes.get(i);
            
            if(process.getIsActive())
            {
                
                activeProcesses.add(process);
                
            }
            
        }
        
        if(activeProcesses.size() > 0)
        {
            
            result = activeProcesses.get(0);
            
            for(int i = 1; i < activeProcesses.size(); ++i)
            {
                
                Process currentProcess = activeProcesses.get(i);
                
                if(currentProcess.getTimeArrival() < result.getTimeArrival())
                {
                    
                    result = currentProcess;
                    
                }
                
            }
            
        }
        
        return result;
        
    }
    
    public static ArrayList<Process> getProcessesForArrivalTime(List<Process> processes, int t)
    {
        
        ArrayList<Process> result = new ArrayList<Process>();
        
        for(int i = 0; i < processes.size(); ++i)
        {
            
            Process process = processes.get(i);
            
            if(process.getTimeArrival() == t)
            {
                
                result.add(process);
                
            }
            
        }
        
        return result;
        
    }

    public static boolean hasIncompleteProcess(List<Process> processes)
    {

        for(int i = 0; i < processes.size(); ++i)
        {
            
            Process process = processes.get(i);
            
            if(!process.getIsComplete())
            {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
  
}
