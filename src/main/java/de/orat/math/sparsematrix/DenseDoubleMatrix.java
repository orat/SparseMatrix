package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class DenseDoubleMatrix implements iDoubleMatrix {

    protected final double[][] data;
    
    public DenseDoubleMatrix(int n_row, int n_col){
        data = new double[n_row][n_col];
    }
    public DenseDoubleMatrix(double[][] m){
        this.data = m;
    }
    @Override
    public double[][] toMatrix() {
        double[][] result = new double[data.length][data[0].length];
        for (int i=0;i<data.length;i++){
            System.arraycopy(data[i], 0, result[i], 0, data[0].length);
        }
        return result;
    }

    public void setValue(int row, int col, double value){
        data[row][col] = value;
    }
    @Override
    public iDoubleMatrix transpose() {
        double[][] result = new double[data[0].length][data.length];
        for (int i=0;i<data.length;i++){
            for (int j=0;j<data[0][j];j++){
                result[j][i] = data[i][j];
            }
        }
        return new DenseDoubleMatrix(result);
    }
}
