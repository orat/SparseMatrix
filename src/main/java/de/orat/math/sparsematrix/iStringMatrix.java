package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public interface iStringMatrix {
    
     public String[][] toArr();
     public String toString();
     public iStringMatrix transpose();
}
