
public class Assignment1 
{
    
    private static Simulation simulation;

    public static void main(String args[]) throws Exception
    {
        
        if(args.length != 4)
        {
            
            String message = "Invalid parameters. Make sure to specify all parameters, e.g.: ";
            message += "Assignment1 n k d v";
            
            System.out.println(message);
                        
            throw new Exception(message);
            
        }

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);
        int v = Integer.parseInt(args[3]);
        
        System.out.println("Starting the simulation with values: n=" + n + "; k=" + k + "; d=" + d + "; v=" + v);  
        
        simulation = new Simulation(n, k, d, v);
        
        simulation.begin();
        
    }
    
}
