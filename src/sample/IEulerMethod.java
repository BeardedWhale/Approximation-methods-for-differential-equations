package sample;

import javafx.scene.chart.XYChart;

/**
 * Implements Improved Euler method of approximation
 */
public class IEulerMethod extends NumericalMethod {


    @Override
    public XYChart.Series<String, Number> computeApproximation() {
        double x[] = new double[N];
        double y[] = new double[N];
        double h = (X - X0) / N;

        XYChart.Series iEulerApproximation = new XYChart.Series();
        iEulerApproximation.setName(name);

        x[0]=X0;
        y[0]=Y0;

        for (int i = 1; i < N; i++) {
            x[i] = x[i-1] + h;
        }

        for (int i = 1; i < N; i++) {
            double yi = y[i-1] + h*(Math.cos(x[i-1]) - y[i-1]); // y i-th approximation by euler method
            y[i]=y[i-1] + 0.5*h*((Math.cos(x[i-1]) - y[i-1]) + (Math.cos(x[i]) - yi));
        }

        for (int i = 0; i < N; i++) {
            iEulerApproximation.getData().add(new XYChart.Data(x[i], y[i]));
        }
        iEulerApproximation.setName("improved Euler method");
        return iEulerApproximation;
    }

    public IEulerMethod(double x0, double y0, int n, double x) {
        super(x0, y0, n, x);
        name = "Improved Euler";
    }
}
