package de.orat.math.sparsematrix;

import java.util.ArrayList;
import java.util.List;

/**
 * MatrixSparsity of a row vector.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class RowVectorSparsity extends MatrixSparsity {
    
    public RowVectorSparsity(double[] vec){
        //TODO
        // sehr ineffiziente Implementierung
        super(create2dim(vec));
    }
    private static double[][] create2dim(double[] vec){
        double[][] result = new double[1][vec.length];
        System.arraycopy(vec, 0, result[0], 0, vec.length);
        return result;
    }
    
    /**
     * @param n_col count of columns
     * @param colind accumulated nonzeros of the columns. First value is zero.
     */
     public RowVectorSparsity(int n_col, int[] colind){
        super(1, n_col, colind, new int[colind[colind.length]]);
     }
     
     // not yet tested
     public ColumnVectorSparsity transpose(){
        //TODO
        // sehr ineffiziente Implementierung
        double[] vec = new double[n_col];
        for (int i=0;i<row.length;i++){
            vec[row[i]] = 1;
        }
        return new ColumnVectorSparsity(vec);
     }
}
