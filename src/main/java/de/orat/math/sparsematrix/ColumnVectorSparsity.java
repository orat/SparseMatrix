package de.orat.math.sparsematrix;

/**
 * MatrixSparsity of a column vector.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class ColumnVectorSparsity extends MatrixSparsity {
    
    public ColumnVectorSparsity(int n_row, int nonZeros, int[] row){
        super(n_row, 1, new int[]{nonZeros}, row);
    }
    public ColumnVectorSparsity(double[] vec){
        super(create2dim(vec));
    }
    private static double[][] create2dim(double[] vec){
        double[][] result = new double[vec.length][1];
        for (int i=0;i<vec.length;i++){
            result[i][0] = vec[i];
        }
        return result;
    }
    
    public RowVectorSparsity transpose(){
        // Beginn einer effizienteren Implementierung
        //TODO
        /*int[] new_colind = new int[getn_row()+1];
        for (int i=0;i<getn_row();i++){
            
        }
        return new RowVectorSparsity(getn_col(), new_colind);*/
        
        //TODO
        // sehr ineffiziente Implementierung
        double[] vec = new double[n_row];
        for (int i=0;i<row.length;i++){
            vec[row[i]] = 1;
        }
        return new RowVectorSparsity(vec);
    }
    
    
}
