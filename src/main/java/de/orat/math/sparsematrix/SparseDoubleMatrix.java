package de.orat.math.sparsematrix;

/**
 * TODO
 * Mit Generic types erweitern, sodass ich sowohl String, Double als auch duale Zahlen
 * oder complexe Zahlen verwenden kann.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseDoubleMatrix implements iDoubleMatrix {
    
    private final MatrixSparsity sparsity;
    private double[] data;
    
    public SparseDoubleMatrix(double[][] m){
        System.out.println(toString(m));
        sparsity = new MatrixSparsity(m);
        System.out.println(sparsity.toString());
        data = rollout(sparsity, m);
    }
    public SparseDoubleMatrix(MatrixSparsity sparsity, double[][] m){
        this.sparsity = sparsity;
        data = rollout(sparsity, m);
    }
    
    /**
     * Unroll the given matrix corresponding to the given sparsity.
     * 
     * scheint zu stimmen
     * @param sparsity
     * @param m
     * @return 
     */
    private static double[] rollout(MatrixSparsity sparsity, double[][] m){
        int[] colind = sparsity.getcolind();
        int[] row = sparsity.getrow();
        double[] result = new double[sparsity.getrow().length];
        // loop over columns
        int k=0; 
        for (int col=0;col<colind.length-1;col++){ // col ok
            // loop over all nonzeros in a column col
            for (int j=colind[col];j<colind[col+1];j++){
               System.out.println("["+String.valueOf(k)+"]="+" col="+String.valueOf(col)+
                       ", row="+String.valueOf(row[k])+" value="+String.valueOf( m[row[k]][col]));
               result[k] = m[row[k++]][col]; //FIXME Index 3 out of bounds for length = 3
            }
        }
        return result;
    }
    
    public MatrixSparsity getSparsity(){
        return sparsity;
    }
    public double[] getData(){
        return data;
    }
    
    public double[][] toArr(){
        int[] colind = sparsity.getcolind();
        int[] row = sparsity.getrow();
        double[][] result = new double[sparsity.getn_row()][sparsity.getn_col()];
        // loop over columns
        int k=0;
        for (int col=0;col<colind.length-1;col++){
            // loop over all nonzeros in a column col
            for (int j=colind[col];j<colind[col+1];j++){ // Index 5 out of bounds for length == 5
                result[row[k]][col] = data[k++];
            }
        }
        return result;
    }
    
    public static String toString(double[][] m){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        // loop over rows
        for (int row=0;row<m.length-1;row++){
            sb.append("  {");
            // loop over columns
            for (int col=0;col<m[0].length-1;col++){
                sb.append(String.valueOf(m[row][col]));
                sb.append(", ");
            }
            sb.append(String.valueOf(m[row][m[0].length-1]));
            sb.append("},\n");
        }
        // last row
        sb.append("  {");
        // loop over columns
        for (int col=0;col<m[0].length-1;col++){
            sb.append(String.valueOf(m[m.length-1][col]));
            sb.append(", ");
        }
        sb.append(String.valueOf(m[m.length-1][m[0].length-1]));
        sb.append("}\n");
        sb.append("}");
        return sb.toString();
    }
    public String toString(boolean asMatrix){
        StringBuilder sb = new StringBuilder();
        if (asMatrix){
            double[][] m = toArr();
            sb.append(toString(m));
        } else {
            sb.append(sparsity.toString());
            sb.append("data=[");
            for (int i=0;i<data.length-1;i++){
                sb.append(String.valueOf(data[i]));
                sb.append(",");
            }
            sb.append(String.valueOf(data[data.length-1]));
            sb.append("]");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public iDoubleMatrix transpose() {
        //TODO
        // sehr ineffiziente Implementierung, da sparsity nicht genutzt wird
        return new SparseDoubleMatrix((new DenseDoubleMatrix(toArr())).transpose().toArr());
    }

}
