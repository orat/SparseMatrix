package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseDoubleColumnVector extends SparseDoubleVector {
    
    private final ColumnVectorSparsity sparsity;
    
    /**
     * Creates a double column vector with the given value at first element and
     * all others zero.
     * 
     * @param value
     * @param size 
     */
    public SparseDoubleColumnVector(double value, int size){
        this.sparsity = new ColumnVectorSparsity(size, new int[]{0});
        this.data = new double[]{value};
    }
    
    public SparseDoubleColumnVector(ColumnVectorSparsity sparsity, double[] data){
        this.sparsity = sparsity;
        this.data = data;
    }
    
    // ungetested
    public SparseDoubleColumnVector(double[] m){
        int nonZeros = 0;
        for (int i=0;i<m.length;i++){
            if (m[i] != 0d){
                nonZeros++;
            }
        }
        int[] row = new int[nonZeros];
        data = new double[nonZeros];
        int j=0;
        for (int i=0;i<m.length;i++){
            if (m[i] != 0d){
                data[j] = m[i];
                row[j] = j++;
            }
        }
        sparsity = new ColumnVectorSparsity(m.length, /*nonZeros,*/ row);
    }

    // not yet tested
    @Override
    public double[][] toMatrix() {
        double[][] result = new double[sparsity.getn_row()][1];
        int[] row = sparsity.getrow();
        for (int i=0;i<data.length;i++){
            result[row[i]][1] = data[i];
        }
        return result;
    }

    @Override
    public SparseDoubleRowVector transpose() {
        throw new RuntimeException ("not yet implemented");
        //return new SparseDoubleRowVector(sparsity.transpose(), toMatrix());
    }
}
