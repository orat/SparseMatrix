package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public abstract class StringVector implements iStringMatrix {
    
    protected String[] data;
    
    public StringVector(String[] vector){
        this.data = vector;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        // loop over columns
        for (int col=0;col<data.length-1;col++){
            sb.append(data[col]);
            sb.append(", ");
        }
        sb.append(data[data.length-1]);
        sb.append("},\n");
        return sb.toString();
    }
}
