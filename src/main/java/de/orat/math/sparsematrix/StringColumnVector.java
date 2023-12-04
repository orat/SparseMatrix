package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class StringColumnVector extends StringVector {

    public StringColumnVector(String[] vector) {
        super(vector);
    }

    @Override
    public String[][] toArr() {
        String[][] result = new String[data.length][1];
        for (int i=0;i<data.length;i++){
            result[i][1] = data[i];
        }
        return result;
    }

    @Override
    public StringRowVector transpose() {
         return new StringRowVector(data);
    }
    
}
