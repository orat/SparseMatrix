package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class DenseDoubleColumnVector extends DoubleVector {

    public DenseDoubleColumnVector(double[] data) {
        super(data);
    }
    public DenseDoubleColumnVector(int n_rows, double[] nonzeros, int[] rows){
        super(createDoubleArray(n_rows, nonzeros, rows));
    }
    private static double[] createDoubleArray(int n_rows, double[] nonzeros, int[] rows){
        double[] result = new double[n_rows];
        for (int i=0;i<rows.length;i++){
            result[rows[i]] = nonzeros[i];
        }
        return result;
    }
    

    @Override
    public double[][] toMatrix() {
        double[][] result = new double[data.length][1];
        for (int i=0;i<data.length;i++){
            result[i][0] = data[i];
        }
        return result;
    }
    
    public double[] toArray(){
        double[] result = new double[data.length];
        System.arraycopy(data, 0, result, 0, data.length);
        return result;
    }

    @Override
    public DenseDoubleRowVector transpose() {
        return new DenseDoubleRowVector(data);
    }
    
}
