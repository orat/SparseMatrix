package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class StringMatrix implements iStringMatrix{
    
    private String[][] m;
    
    public StringMatrix(String[][] m){
        this.m = m;
    }
    
    // not yet tested
    //TODO gibts eine performantere Implementierungsmöglichkeit?
    public String[][] copyMatrix() {
        String[][] result = new String[m.length][m[0].length];
        // loop over rows
        for (int row=0;row<m.length;row++){
            // loop over columns
            System.arraycopy(m[row], 0, result[row], 0, m[0].length);
        }
        return result;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        // loop over rows
        for (int row=0;row<m.length-1;row++){
            sb.append("  {");
            // loop over columns
            for (int col=0;col<m[0].length-1;col++){
                sb.append(m[row][col]);
                sb.append(", ");
            }
            sb.append(m[row][m[0].length-1]);
            sb.append("},\n");
        }
        // last row
        sb.append("  {");
        // loop over columns
        for (int col=0;col<m[0].length-1;col++){
            sb.append(m[m.length-1][col]);
            sb.append(", ");
        }
        sb.append(m[m.length-1][m[0].length-1]);
        sb.append("}\n");
        sb.append("}");
        return sb.toString();
    }
}
