package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class DenseStringMatrix implements iStringMatrix {
    
    protected String[][] data;
    
    public DenseStringMatrix(String[][] m){
        data = m;
    }
    
    // not yet tested
    //TODO gibts eine performantere Implementierungsmöglichkeit?
    public String[][] toArr() {
        String[][] result = new String[data.length][data[0].length];
        // loop over rows
        for (int row=0;row<data.length;row++){
            // loop over columns
            System.arraycopy(data[row], 0, result[row], 0, data[0].length);
        }
        return result;
    }
    
    /**
     * 
     * @param row
     * @param col
     * @return a basis-blade-name eventuelle with an additional sign "-" as a prefix, "0" or "1"/"-1"
     */
    public String get(int row, int col){
        return data[row][col];
    }
    public int getRow(){
        return data.length;
    }
    public int getCol(){
        return data[0].length;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        // loop over rows
        for (int row=0;row<data.length-1;row++){
            sb.append("  {");
            // loop over columns
            for (int col=0;col<data[0].length-1;col++){
                sb.append(data[row][col]);
                sb.append(", ");
            }
            sb.append(data[row][data[0].length-1]);
            sb.append("},\n");
        }
        // last row
        sb.append("  {");
        // loop over columns
        for (int col=0;col<data[0].length-1;col++){
            sb.append(data[data.length-1][col]);
            sb.append(", ");
        }
        sb.append(data[data.length-1][data[0].length-1]);
        sb.append("}\n");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public DenseStringMatrix transpose() {
        String[][] result = new String[data[0].length][data.length];
        for (int i=0;i<data.length;i++){
            for (int j=0;j<data[0].length;j++){
                result[j][i] = data[i][j];
            }
        }
        return new DenseStringMatrix(result);
    }
}
