package sample;

import javafx.scene.chart.XYChart;

/**
 * Calculates the exact values of a function
 * y = 0.5(sinx + cosx) + C*e^-x
 */
public class ExactMethod extends NumericalMethod {


    @Override
    public XYChart.Series<String, Number> computeApproximation() {
        double constant = Math.exp(1)*(1- 0.5*(Math.cos(1) + Math.sin(1)));
        double x[] = new double[N];

        double y[] = new double[N];

        double h = (X - X0) / N;

        x[0]=X0;
        for (int i = 1; i < N; i++) {
            x[i] = x[i-1] + h;
        }

        for (int i = 0; i < N; i++) {
            y[i] = 0.5*(Math.sin(x[i]) + Math.cos(x[i])) + constant*Math.exp(-x[i]);
        }

        XYChart.Series ExactFunct = new XYChart.Series();

        for (int i=0; i<N; i++)
        {
            ExactFunct.getData().add(new XYChart.Data(x[i], y[i]));
        }
        ExactFunct.setName(name);
        return ExactFunct;
    }

    public ExactMethod(double x0, double y0, int n, double x) {
        super(x0, y0, n, x);
        name = "Exact function";
    }
}
