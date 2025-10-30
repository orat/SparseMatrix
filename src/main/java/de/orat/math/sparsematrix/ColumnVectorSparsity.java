package de.orat.math.sparsematrix;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * MatrixSparsity of a column vector.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class ColumnVectorSparsity extends MatrixSparsity {
    
    public ColumnVectorSparsity(int n_row, int[] rows){
        super(n_row, 1, new int[]{0,rows.length}, rows);
    }
    
    public static ColumnVectorSparsity instance(MatrixSparsity sparsity){
        int cols = sparsity.getn_col();
        if (cols != 1) throw new IllegalArgumentException("col = "+String.valueOf(cols)+" != 1 is not allowed!");
        return new ColumnVectorSparsity(sparsity.getn_row(), sparsity.getrow());
    }
    
    // very slow implementation
    /**
     * 
     * @param rowIndex
     * @return -1 if the given index is structural zero
     * @throws IllegalArgumentException if the given index is out of the columns vector length
     */
    public int determineIndexOfRow(int rowIndex){
        if (rowIndex >= getn_row()) throw new IllegalArgumentException("row =="+String.valueOf(rowIndex)+
                " is >= nrow="+String.valueOf(n_row));
        for (int i=0;i<rows.length;i++){
            if (rows[i] == rowIndex) return i;
        }
        return -1;
    }
    
    /**
     * @param vec only the non-zero values define the sparsity
     */
    public ColumnVectorSparsity(double[] vec, boolean sparsify) {
        super(create2dim(vec), sparsify);
    }
    private static double[][] create2dim(double[] vec){
        double[][] result = new double[vec.length][1];
        for (int i=0;i<vec.length;i++){
            result[i][0] = vec[i];
        }
        return result;
    }
    
    public static ColumnVectorSparsity empty(int rows){
        return new ColumnVectorSparsity(rows, new int[]{});
    }
    public static ColumnVectorSparsity dense(int rows){
        return new ColumnVectorSparsity(rows, createDenseRows(rows));
    }
    protected static int[] createDenseRows(int rows){
        int[] result = new int[rows];
        for (int i=0;i<rows;i++){
            result[i] = i;
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
        for (int i=0;i<rows.length;i++){
            vec[rows[i]] = 1;
        }
        return new RowVectorSparsity(vec, true);
    }

    // Vereinigungsmenge
    public ColumnVectorSparsity union(ColumnVectorSparsity other) {
        final int n_row_this = this.getn_row();
        final int n_row_other = other.getn_row();
        if (n_row_this != n_row_other) {
            throw new IllegalArgumentException(String.format(
                "n_rows differ between this and other: %s != %s",
                n_row_this,
                n_row_other));
        }
        final int n_row_both = n_row_this; // Dimension does not change.

        var a = IntStream.of(this.getrow());
        var b = IntStream.of(other.getrow());

        // Works because there is only one column.
        int[] rows_union = IntStream.concat(a, b).distinct().sorted().toArray();
        // Postcondition: Sorted. No duplicates.

        //TODO
        // hier werden sparsity-Objekte erzeugt, diese sollte ich aber cachen
        return new ColumnVectorSparsity(n_row_both, rows_union);
    }

    // Schnittmenge
    public ColumnVectorSparsity intersection(ColumnVectorSparsity other) {
        final int n_row_this = this.getn_row();
        final int n_row_other = other.getn_row();
        if (n_row_this != n_row_other) {
            throw new IllegalArgumentException(String.format(
                "n_rows differ between this and other: %s != %s",
                n_row_this,
                n_row_other));
        }
        final int n_row_both = n_row_this; // Dimension does not change.

        int[] a = this.getrow(); // Precondition: Sorted. No duplicates.
        int[] b = other.getrow(); // Precondition: Sorted. No duplicates.

        // Cardinality of intersection is always at most the cardinality of the smaller set.
        int[] buf = new int[Math.min(a.length, b.length)];
        int ibuf = 0;
        int ia = 0;
        int ib = 0;
        // Runtime: O(min(a.length, b.length))
        while ((ia < a.length) && (ib < b.length)) {
            int a_i = a[ia];
            int b_i = b[ib];
            if (a_i == b_i) {
                buf[ibuf] = a_i;
                ++ibuf;
                ++ia;
                ++ib;
            } else if (a_i < b_i) {
                ++ia;
            } else { // a_i > b_i
                ++ib;
            }
        }
        // If Lists backed by int arrays would be used, copying could be avoided.
        int[] rows_intersection = Arrays.copyOf(buf, ibuf + 1); // Postcondition: Sorted. No duplicates.

        //TODO
        // hier werden sparsity-Objekte erzeugt, diese sollte ich aber cachen
        return new ColumnVectorSparsity(n_row_both, rows_intersection);
    }
}
