package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public abstract class DoubleVector implements iDoubleMatrix {
    
    protected double[] data;
    
    public DoubleVector(double[] data){
        this.data = data;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        // loop over columns
        for (int col=0;col<data.length-1;col++){
            sb.append(String.valueOf(data[col]));
            sb.append(", ");
        }
        sb.append(String.valueOf(data[data.length-1]));
        sb.append("}\n");
        return sb.toString();
    }
}
