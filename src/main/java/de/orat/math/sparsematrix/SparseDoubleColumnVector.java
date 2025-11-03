package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseDoubleColumnVector extends /*DoubleVector*/ SparseDoubleMatrix {
    
    //private final ColumnVectorSparsity sparsity;
    
    // empty double column vector
    public SparseDoubleColumnVector(int size){
        //super(new double[]{});
        //this.sparsity = ColumnVectorSparsity.empty(size);
        super(ColumnVectorSparsity.empty(size), new double[]{});
    }
    
    public SparseDoubleColumnVector(ColumnVectorSparsity sparsity, double[] nonzeros){
        //super(nonzeros);
        //this.sparsity = sparsity;
        super(sparsity, nonzeros);
        // 5 versus 7
        if (sparsity.rows.length != nonzeros.length) throw 
                new IllegalArgumentException("rows.length = "+
                        String.valueOf(sparsity.rows.length)+" != nonzeros.length ="+ String.valueOf(nonzeros.length));
        
    }
    
    public SparseDoubleColumnVector(SparseDoubleMatrix mv){
        this(ColumnVectorSparsity.instance(mv.getSparsity()), mv.nonzeros());
    }
   
    // ungetested
    public SparseDoubleColumnVector(double[] m){
        //super(determineNonzeros(m));
        //sparsity = new ColumnVectorSparsity(m.length, determineRows(m));
        super(new ColumnVectorSparsity(m.length, determineRows(m)), determineNonzeros(m));
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
    /*@Override
    public double[][] toMatrix() {
        double[][] result = new double[sparsity.getn_row()][1];
        int[] row = sparsity.getrow();
        for (int i=0;i<data.length;i++){
            result[row[i]][0] = data[i];
        }
        return result;
    }*/

    @Override
    public SparseDoubleRowVector transpose() {
        throw new RuntimeException ("not yet implemented");
        //return new SparseDoubleRowVector(sparsity.transpose(), toMatrix());
    }
    public ColumnVectorSparsity getSparsity(){
        return (ColumnVectorSparsity) sparsity;
    }
    
    // very slow implementation
    public SparseDoubleColumnVector add(SparseDoubleColumnVector b){
        ColumnVectorSparsity sparsity_b = b.getSparsity();
        ColumnVectorSparsity resultSparsity = getSparsity().union(sparsity_b);
        //System.out.println(resultSparsity);
        double[] nonzeros = new double[resultSparsity.getrow().length/*n_row*/];
        // data from columnVector a and b to nonzeros addieren
        for (int i=0;i<resultSparsity.getrow().length/*getn_row()*/;i++){
            int result_row = resultSparsity.getrow()[i];
            //System.out.println("add: row="+String.valueOf(result_row));
            int index_a = getSparsity().determineIndexOfRow(result_row);
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
    
    public double[] toArray(){
        double[] result = new double[sparsity.getn_row()];
        int[] row = sparsity.getrow();
        for (int i=0;i<data.length;i++){
            result[row[i]] = data[i];
        }
        return result;
    }
}
