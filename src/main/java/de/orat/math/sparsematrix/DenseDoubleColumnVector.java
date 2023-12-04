package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class DenseDoubleColumnVector extends DoubleVector {

    public DenseDoubleColumnVector(double[] data) {
        super(data);
    }

    @Override
    public double[][] toArr() {
        double[][] result = new double[data.length][1];
        for (int i=0;i<data.length;i++){
            result[i][1] = data[i];
        }
        return result;
    }

    @Override
    public DenseDoubleRowVector transpose() {
        return new DenseDoubleRowVector(data);
    }
    
}
