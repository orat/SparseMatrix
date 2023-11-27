package de.orat.math.sparsematrix;

/**
 * Sparsity of a column vector.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class ColumnVectorSparsity extends Sparsity {
    
     public ColumnVectorSparsity(int n_row, int nonZeros, int[] row){
         super(n_row, 1, new int[]{nonZeros}, row);
     }
}
