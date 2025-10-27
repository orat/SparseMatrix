package de.orat.math.sparsematrix;

/**
 * https://introcs.cs.princeton.edu/java/44st/SparseMatrix.java.html
 * 
 * https://spark.apache.org/docs/1.4.0/api/java/org/apache/spark/mllib/linalg/SparseMatrix.html
 * 
 * https://www.javatpoint.com/sparse-matrix
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseStringMatrix implements iStringMatrix {
    
    private final MatrixSparsity sparsity;
    private final String[] data;
    
    /**
     * @param m alle "0"-Komponenten als sparse werten
     */
    public SparseStringMatrix(String[][] m, boolean sparsify) {
        sparsity = new MatrixSparsity(m, sparsify);
        data = rollout(sparsity, m);
    }
    
    public SparseStringMatrix(MatrixSparsity sparsity, String[][] m){
        this.sparsity = sparsity;
        data = rollout(sparsity, m);
    }

    /**
     * Unroll the given matrix corresponding to the given sparsity.
     * 
     * scheint zu stimmen
     * @param sparsity
     * @param m
     * @return array with all non zero values
     */
    private static String[] rollout(MatrixSparsity sparsity, String[][] m){
        int[] colind = sparsity.getcolind();
        int[] row = sparsity.getrow();
        String[] result = new String[sparsity.getrow().length];
        // loop over columns
        int k=0; 
        for (int col=0;col<colind.length-1;col++){ // col ok
            // loop over all nonzeros in a column col
            for (int j=colind[col];j<colind[col+1];j++){
               //System.out.println("["+String.valueOf(k)+"]="+" col="+String.valueOf(col)+
               //        ", row="+String.valueOf(row[k])+" value="+m[row[k]][col]);
               result[k] = m[row[k++]][col]; //FIXME Index 3 out of bounds for length = 3
            }
        }
        return result;
    }
    
    public MatrixSparsity getSparsity(){
        return sparsity;
    }
    public String[] getData(){
        return data;
    }
    
    /**
     * For structural zeros the the corresponding result cell to null.
     * 
     * @return 
     */
    public String[][] toArr(){
        int[] colind = sparsity.getcolind();
        int[] row = sparsity.getrow();
		String[][] result = new String[sparsity.getn_row()][sparsity.getn_col()];
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
    
    public static String toString(String[][] m){
        StringBuilder sb = new StringBuilder();
        // mehr als eine Spalte
        if (m[0].length > 1){
            sb.append("{\n");
            // loop over rows
            for (int row=0;row<m.length-1;row++){
                sb.append("  {");
                // loop over columns
                for (int col=0;col<m[0].length-1;col++){
                    String value = m[row][col];
                    if (value != null){
                        sb.append(value);
                    } else {
                        sb.append(" ");
                    }
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
        // eine Spalte
        } else {
            sb.append("T{");
            // loop over rows
            for (int row=0;row<m.length-1;row++){
                sb.append(m[row][0]);
                sb.append(", ");
            }
            sb.append(m[m.length-1][0]);
            sb.append("}");
        }
        return sb.toString();
    }

	public static void replaceNullWith00(String[][] m) {
		for (int i = 0; i < m.length; ++i) {
			String[] n = m[i];
			for (int j = 0; j < n.length; ++j) {
				if (n[j] == null) {
					n[j] = "00";
				}
			}
		}
	}

    public String toString(boolean asMatrix){
        StringBuilder sb = new StringBuilder();
        if (asMatrix){
			String[][] m = toArr();
			replaceNullWith00(m);
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
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public SparseStringMatrix transpose() {
        //TODO
        // sehr ineffiziente Implementierung, da sparsity nicht genutzt wird
        return new SparseStringMatrix((new DenseStringMatrix(toArr())).transpose().toArr(), true);
    }
}
