package sample;


import javafx.scene.chart.XYChart.Series;

/**
 * Abstract Class for all numerical methods
 */
public abstract class NumericalMethod {
    double X0;
    double Y0;
    int N;
    double X;
    String name;

    public NumericalMethod() {
    }

    public NumericalMethod(double x0, double y0, int n, double x) {
        X0 = x0;
        Y0 = y0;
        N = n;
        X = x;
    }

    public void setN(int N) {
        this.N=N;
    }

    abstract Series<String, Number> computeApproximation();
}


