package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public abstract class SparseDoubleVector implements iDoubleMatrix {

    protected double[] data;
    public double[] nonzeros(){
        return data;
    }
}
