package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class DenseDoubleRowVector extends DoubleVector {

    public DenseDoubleRowVector(double[] data) {
        super(data);
    }

    @Override
    public double[][] toArr() {
        double[][] result = new double[1][data.length];
        System.arraycopy(data, 0, result[1], 0, data.length);
        return result;
    }

    @Override
    public DenseDoubleColumnVector transpose() {
        return new DenseDoubleColumnVector(data);
    }
    
}
