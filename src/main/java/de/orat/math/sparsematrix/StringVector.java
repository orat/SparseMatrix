package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class StringVector {
    
    private String[] vector;
    
    public StringVector(String[] vector){
        this.vector = vector;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        // loop over columns
        for (int col=0;col<vector.length-1;col++){
            sb.append(vector[col]);
            sb.append(", ");
        }
        sb.append(vector[vector.length-1]);
        sb.append("},\n");
        return sb.toString();
    }
}
