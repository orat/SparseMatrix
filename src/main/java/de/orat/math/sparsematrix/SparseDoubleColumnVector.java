package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseDoubleColumnVector extends DoubleVector {
    
    private final ColumnVectorSparsity sparsity;
    
    /**
     * Creates a double column vector with the given value at first element and
     * all others zero.
     * 
     * @param value
     * @param size 
     */
    /*public SparseDoubleColumnVector(double value, int size){
        this.sparsity = new ColumnVectorSparsity(size, new int[]{0});
        this.data = new double[]{value};
    }*/
    
    public SparseDoubleColumnVector(ColumnVectorSparsity sparsity, double[] nonzeros){
        super(nonzeros);
        this.sparsity = sparsity;
    }
    
    // ungetested
    public SparseDoubleColumnVector(double[] m){
        super(determineNonzeros(m));
        sparsity = new ColumnVectorSparsity(m.length, determineRows(m));
    }

    private static double[] determineNonzeros(double[] m){
        int nonZeros = 0;
        for (int i=0;i<m.length;i++){
            if (m[i] != 0d){
                nonZeros++;
            }
        }
        //int[] row = new int[nonZeros];
        double[] result = new double[nonZeros];
        int j=0;
        for (int i=0;i<m.length;i++){
            if (m[i] != 0d){
                result[j] = m[i];
                //row[j] = j++;
            }
        }
        return result;
    }
    
    private static int[] determineRows(double[] m){
        int nonZeros = 0;
        for (int i=0;i<m.length;i++){
            if (m[i] != 0d){
                nonZeros++;
            }
        }
        int[] rows = new int[nonZeros];
        int j=0;
        for (int i=0;i<m.length;i++){
            if (m[i] != 0d){
                rows[j] = j++;
            }
        }
        return rows;
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
    public ColumnVectorSparsity getSparsity(){
        return sparsity;
    }
    
    // very slow implementation
    public SparseDoubleColumnVector add(SparseDoubleColumnVector b){
        ColumnVectorSparsity sparsity_b = b.getSparsity();
        ColumnVectorSparsity resultSparsity = sparsity.join(sparsity_b);
        System.out.println(resultSparsity);
        double[] nonzeros = new double[resultSparsity.n_row];
        // data from columnVector a and b to nonzeros addieren
        for (int i=0;i<resultSparsity.getn_row();i++){
            int result_row = resultSparsity.getrow()[i];
            int index_a = sparsity.determineIndexOfRow(result_row);
            if (index_a >=0) nonzeros[i] += data[index_a];
            int index_b = sparsity_b.determineIndexOfRow(result_row);
            if (index_b >=0) nonzeros[i] += data[index_b];
        }
        
        // data from columnVector a  to nonzeros addieren
        return new SparseDoubleColumnVector(resultSparsity, nonzeros);
    }
    
    public double[] nonzeros(){
        return data;
    }
}
