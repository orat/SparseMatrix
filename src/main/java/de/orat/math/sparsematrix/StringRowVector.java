package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class StringRowVector extends StringVector {
    
    public StringRowVector(String[] data) {
        super(data);
    }

    @Override
    public String[][] toArr() {
        String[][] result = new String[1][data.length];
        for (int i=0;i<data.length;i++){
            result[1][i] = data[i];
        }
        return result;
    }

    @Override
    public StringColumnVector transpose() {
        return new StringColumnVector(data);
    }
    
}
