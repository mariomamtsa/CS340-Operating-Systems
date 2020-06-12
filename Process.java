import java.util.Random;

public class Process 
{
    
    private static Random random = new Random();

    private boolean active;
    private boolean complete = false;
    
    private int timeArrival;
    private int timeTotalCPU;
    private int timeRemainingCPU;
    
    private int timeCompletion;
    private int timeTurnaround;
    private int timeWaiting;
    
    public Process(int k, int d, int v)
    {
        
        this.initTimeArrival(k);
        
        this.initTimeTotalCPU(d, v);
        
    }
    
    private void initTimeArrival(int k)
    {
        
        this.timeArrival = (int) (random.nextDouble() * k);
        
        this.setIsActive(this.timeArrival == 0);
        
    }
    
    private void initTimeTotalCPU(int d, int v)
    {
     
        this.timeTotalCPU = (int) (random.nextGaussian() * v) + d;
        
        this.timeRemainingCPU = this.timeTotalCPU;
        
    }
    
    public void step(int t)
    {
        
        --this.timeRemainingCPU;
        
        if(this.timeRemainingCPU == 0)
        {
            
            this.terminate(t);
            
        }
        
    }
    
    public void terminate(int t)
    {
        
        this.active = false;
        this.complete = true;
        
        this.timeCompletion = t + 1;
        this.timeTurnaround = this.timeCompletion - this.timeArrival;
        this.timeWaiting = this.timeTurnaround - this.getTimeTotalCPU();
        
    }
    
    public int getTimeArrival()
    {
                
        return this.timeArrival;
        
    }

    public int getTimeCompletion()
    {
                
        return this.timeCompletion;
        
    }

    public int getTimeTurnaround()
    {
                
        return this.timeTurnaround;
        
    }

    public int getTimeWaiting()
    {
                
        return this.timeWaiting;
        
    }
    
    public int getTimeTotalCPU()
    {
        
        return this.timeTotalCPU;
        
    }
    
    public int getTimeRemainingCPU()
    {
        
        return this.timeRemainingCPU;
        
    }

    public boolean getIsComplete()
    {
        
        return this.complete;
        
    }

    public boolean getIsActive()
    {
        
        return this.active;
        
    }
    
    public void setIsActive(boolean value)
    {
        
        this.active = value;
        
    }

}
