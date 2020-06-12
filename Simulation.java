import java.util.ArrayList;
import java.util.List;

public class Simulation 
{
    
    final static boolean SHOW_TABLE_ON_STEP = false;
    final static boolean SHOW_TABLE_ON_COMPLETION = true;
    final static boolean DISPLAY_EXTRA_FIELDS = false;
    
    private Table table;
    
    private int step = 0;
    
    private List<Process> processes = new ArrayList<Process>();

    private int n;
    private int k;
    private int d;
    private int v;

    public Simulation(int n, int k, int d, int v)
    {
        
        this.n = n;
        this.k = k;
        this.d = d;
        this.v = v;

        if(DISPLAY_EXTRA_FIELDS)
        {
            
            this.table = new Table("Process", "Active", "Arrival Time", "Total CPU Time", "Remaining CPU Time", "Completion Time", "Turnaround Time", "Waiting Time");
            
        }
        else
        {
            
            this.table = new Table("Process", "Active", "Arrival Time", "Total CPU Time", "Remaining CPU Time", "Turnaround Time");
            
        }
        
        this.createProcesses();
        
    }
    
    private void createProcesses()
    {
        
        for(int i = 0; i < this.n; ++i)
        {
            
            processes.add(new Process(k, d, v));
            
        }
        
    }
    
    public void begin()
    {
        
        this.step();
        
    }
    
    private void step()
    {
        
        this.updateActiveProcesses();
        
        Process process = FirstComeFirstServe.getNextActiveProcess(this.processes);
        
        if(process != null)
        {
            
            process.step(this.step);
            
        }
        
        ++this.step;
        
        if(SHOW_TABLE_ON_STEP)
        {

            System.out.println("\nStep #" + (this.step) + ":");
            
            this.printTable();
            
        }
        
        if(FirstComeFirstServe.hasIncompleteProcess(this.processes))
        {
            
            this.step();
            
        }
        else
        {
            
            this.finish();
            
        }
        
    }
    
    private void finish()
    {

        if(SHOW_TABLE_ON_COMPLETION)
        {

            System.out.println("\nCompleted after " + (this.step) + " steps:");
            
            this.printTable();
            
        }
        
        float averageTurnaroundTime = this.getAverageTurnaroundTime();
        
        System.out.println("ATT - Average turnaround time: " + averageTurnaroundTime);
        
    }
    
    private float getAverageTurnaroundTime()
    {
        
        float result = 0;
        
        int sum = 0;
        
        for(int i = 0; i < this.processes.size(); ++i)
        {
            
            sum += this.processes.get(i).getTimeTurnaround();
            
        }
        
        result = sum / this.processes.size();
        
        return result;
        
    }
    
    private void updateActiveProcesses()
    {
        
        if(FirstComeFirstServe.hasIncompleteProcess(this.processes))
        {
            
            ArrayList<Process> newActiveProcesses = FirstComeFirstServe.getProcessesForArrivalTime(this.processes, this.step);
            
            if(newActiveProcesses != null)
            {
                
                for(int i = 0; i < newActiveProcesses.size(); ++i)
                {
                    
                    newActiveProcesses.get(i).setIsActive(true);
                    
                }
                
            }
            
        }
        
    }
    
    private void printTable()
    {
        
        this.table.clearData();
        
        for(int i = 0; i < this.processes.size(); ++i) 
        {
            
            Process process = this.processes.get(i);
            
            if(DISPLAY_EXTRA_FIELDS)
            {
                
                this.table.addRow("" + i, process.getIsActive() ? "1" : "0", "" + process.getTimeArrival(), "" + process.getTimeTotalCPU(), "" + process.getTimeRemainingCPU(), "" + process.getTimeCompletion(), "" + process.getTimeTurnaround(), "" + process.getTimeWaiting());
                
            }
            else
            {

                this.table.addRow("" + i, process.getIsActive() ? "1" : "0", "" + process.getTimeArrival(), "" + process.getTimeTotalCPU(), "" + process.getTimeRemainingCPU(), "" + process.getTimeTurnaround());
                
            }
            
        }
        
        this.table.print();
        
    }
    
}
