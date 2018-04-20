package sample;

import javafx.scene.chart.XYChart;

/**
 * Implements Runge-Kutta method of approximation
 */
public class RungeKutta extends NumericalMethod {
    @Override
    public XYChart.Series<String, Number> computeApproximation() {
        double x[] = new double[N];
        double y[] = new double[N];
        double h = (X - X0) / N;

        XYChart.Series rungeKuttaAproximation = new XYChart.Series();
        rungeKuttaAproximation.setName(name);

        x[0]=X0;
        y[0]=Y0;

        for (int i = 1; i < N; i++) {
            x[i] = x[i-1] + h;
        }

        for (int i = 1; i < N; i++) {
            double k1i = Math.cos(x[i-1]) - y[i-1];
            double k2i = Math.cos(x[i-1] + 0.5*h) - (y[i-1] + 0.5*h*k1i);
            double k3i = Math.cos(x[i-1] + 0.5*h) - (y[i-1] + 0.5*h*k2i);
            double k4i = Math.cos(x[i-1] + h) - (y[i-1] + h*k3i);

            y[i]=y[i-1] + h*(k1i + 2*k2i + 2*k3i + k4i)/6;
        }

        for (int i = 0; i < N; i++) {
            rungeKuttaAproximation.getData().add(new XYChart.Data(x[i], y[i]));
        }
        rungeKuttaAproximation.setName("Runge-Kutta method");
        return rungeKuttaAproximation;
    }

    public RungeKutta(double x0, double y0, int n, double x) {
        super(x0, y0, n, x);
        name = "Runge-Kutta";
    }
}
