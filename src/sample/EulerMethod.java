package sample;

import javafx.scene.chart.XYChart;

import javafx.scene.chart.XYChart.Series;

/**
 * Implements Euler method of approximation
 */
public class EulerMethod extends NumericalMethod {

    @Override
    public Series<String, Number> computeApproximation() {
        double x[] = new double[N];
        double y[] = new double[N];
        double h = (X - X0) / N;

        XYChart.Series eulerApproximation = new XYChart.Series();

        x[0]=X0;
        y[0]=Y0;

        for (int i = 1; i < N; i++) {
            x[i] = x[i-1] + h;
        }

        for (int i = 1; i < N; i++) {
            y[i]=y[i-1] + h*(Math.cos(x[i-1]) - y[i-1]);
        }

        for (int i = 0; i < N; i++) {
            eulerApproximation.getData().add(new XYChart.Data(x[i], y[i]));
        }
        eulerApproximation.setName(name);
        return eulerApproximation;
    }

    public EulerMethod(double x0, double y0, int n, double x) {
        super(x0, y0, n, x);
        name = "Euler";
    }
}
