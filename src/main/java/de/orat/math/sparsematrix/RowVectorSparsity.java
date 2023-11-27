package de.orat.math.sparsematrix;

/**
 * Sparsity of a row vector.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class RowVectorSparsity extends Sparsity {
    
    /**
     * @param n_col count of columns
     * @param colind accumulated nonzeros of the columns. First value is zero.
     */
     public RowVectorSparsity(int n_col, int[] colind){
        super(1, n_col, colind, new int[colind[colind.length]]);
     }
}
