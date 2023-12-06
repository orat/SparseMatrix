package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public interface iDoubleMatrix {
    
    // always copy the data
    public double[][] toMatrix();
    public String toString();
    public iDoubleMatrix transpose();
}
