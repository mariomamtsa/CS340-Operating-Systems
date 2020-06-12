import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Table 
{
    

    private static final String HORIZONTAL_SEPERATOR = "-";
    
    private String verticalSeperator;
    private String joinSeperator;
    private String[] headers;
    
    private List<String[]> rows;
    private boolean rightAlign;

    public Table(String... headers) 
    {
        
        this();
        
        this.setHeaders(headers);
        
    }
    
    public Table() 
    {
        
        this.setShowVerticalLines(true);
        
        this.clearData();
        
    }
    
    public void setRightAlign(boolean rightAlign) 
    {
        
        this.rightAlign = rightAlign;
        
    }

    public void setShowVerticalLines(boolean showVerticalLines) 
    {
        
        this.verticalSeperator = showVerticalLines ? "|" : "";
        this.joinSeperator = showVerticalLines ? "+" : " ";
        
    }

    public void setHeaders(String... headers) 
    {
        
        this.headers = headers;
        
    }
    
    public void clearData()
    {
        
        this.rows = new ArrayList<>();
        
    }

    public void addRow(String... cells) 
    {
        
        this.rows.add(cells);
        
    }

    public void print() 
    {
        
        int[] maxWidths = this.headers != null ? Arrays.stream(this.headers).mapToInt(String::length).toArray() : null;

        for(String[] cells : this.rows) 
        {
            
            if(maxWidths == null) 
            {
                
                maxWidths = new int[cells.length];
                
            }
            
            if(cells.length != maxWidths.length) 
            {
                
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
                
            }
            
            for(int i = 0; i < cells.length; ++i) 
            {
                
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
                
            }
            
        }

        if(this.headers != null) 
        {
            
            this.printLine(maxWidths);
            
            this.printRow(this.headers, maxWidths);
            
            this.printLine(maxWidths);
            
        }
        
        for(String[] cells : rows) 
        {
            
            this.printRow(cells, maxWidths);
            
        }
        
        if(this.headers != null) 
        {
            
            this.printLine(maxWidths);
            
        }
        
    }

    private void printLine(int[] columnWidths) 
    {
        
        for(int i = 0; i < columnWidths.length; ++i) 
        {
            
            String line = String.join("", Collections.nCopies(columnWidths[i] + this.verticalSeperator.length() + 1, HORIZONTAL_SEPERATOR));
            
            System.out.print(this.joinSeperator + line + (i == columnWidths.length - 1 ? this.joinSeperator : ""));
            
        }
        
        System.out.println();
        
    }

    private void printRow(String[] cells, int[] maxWidths) 
    {
        
        for(int i = 0; i < cells.length; i++) 
        {
            
            String s = cells[i];
            
            String verStrTemp = i == cells.length - 1 ? this.verticalSeperator : "";
            
            if(rightAlign) 
            {
                
                System.out.printf("%s %" + maxWidths[i] + "s %s", this.verticalSeperator, s, verStrTemp);
                
            } 
            else 
            {
                
                System.out.printf("%s %-" + maxWidths[i] + "s %s", this.verticalSeperator, s, verStrTemp);
                
            }
            
        }
        
        System.out.println();
        
    }
    
}