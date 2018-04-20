package sample;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 08.04.2018.
 */
public class Model {


    private double X0 =1;
    private double Y0 = 1;
    private int N=100;
    private double X = 9.5;
    private int minN = 10;
    private int maxN = 100;

    boolean exactRequired;
    boolean eulerRequired;
    boolean ieulerRequired;
    boolean rungeKuttaRequired;


    public Model(double x0, double y0, int n, double x) {
        this.X0 = x0;
        this.Y0 = y0;
        N = n;
        this.X = x;
    }

    public void setN(int N){this.N = N;}

    public void setMinN(int minN) {this.minN = minN;}
    public void setMaxN(int maxN) {this.maxN = maxN;}


    public void setRequiredCharts(boolean exact, boolean euler, boolean ieuler, boolean rungeKutta){
        exactRequired = exact;
        eulerRequired = euler;
        ieulerRequired = ieuler;
        rungeKuttaRequired = rungeKutta;
    }

    public ArrayList<Series<String, Number>> getFunctionSeries(){
        ArrayList<Series<String,Number>> aproximationSeries = new ArrayList<>();
        if(exactRequired){
            aproximationSeries.add(new ExactMethod(X0, Y0, N, X).computeApproximation());
        }
        if(eulerRequired){
            aproximationSeries.add(new EulerMethod(X0, Y0, N, X).computeApproximation());
        }
        if (ieulerRequired){
            aproximationSeries.add(new IEulerMethod(X0, Y0, N, X).computeApproximation());
        }
        if (rungeKuttaRequired){
            aproximationSeries.add(new RungeKutta(X0, Y0, N, X).computeApproximation());
        }

        return aproximationSeries;
    }


    public ArrayList<Series<String, Number>> getErrorSeries (){
        ArrayList<Series<String,Number>> errorSeries = new ArrayList<>();

        if(eulerRequired){
            errorSeries.add(computeError(new ExactMethod(X0, Y0, N, X), new EulerMethod(X0, Y0, N, X)));
        }
        if (ieulerRequired){
            errorSeries.add(computeError(new ExactMethod(X0, Y0, N, X), new IEulerMethod(X0, Y0, N, X)));
        }
        if (rungeKuttaRequired){
            errorSeries.add(computeError(new ExactMethod(X0, Y0, N, X), new RungeKutta(X0, Y0, N, X)));
        }

        return errorSeries;
    }

    public ArrayList<Series<String, Number>> getTruncationErrorSeries (){
        ArrayList<Series<String,Number>> errorSeries = new ArrayList<>();

        Series<String, Number> exact = new ExactMethod(X0, Y0, N, X).computeApproximation();

        if(eulerRequired){
            errorSeries.add(computeTruncationError(new EulerMethod(X0, Y0, N, X)));
        }
        if (ieulerRequired){
            errorSeries.add(computeTruncationError(new IEulerMethod(X0, Y0, N, X)));
        }
        if (rungeKuttaRequired){
            errorSeries.add(computeTruncationError(new RungeKutta(X0, Y0, N, X)));
        }

        return errorSeries;
    }

    /**
     * Computes error difference between actual function and it's approximation
     */
    private Series<String, Number> computeError(ExactMethod exactMethod, NumericalMethod numericalMethod){
        int N = exactMethod.N;

        Double errorYValues[] = new Double[N];
        Double errorXValues[] = new Double[N];

        Series<String, Number> exactFunct = exactMethod.computeApproximation();
        Series<String, Number> aproxFunct = numericalMethod.computeApproximation();

        double h = (X - X0) /N;

        errorXValues[0] = X0;
        for (int i = 1; i <N; i++) {
            errorXValues[i] = errorXValues[i-1] + h;
        }

        for (int i = 0; i < N; i++) {
            errorYValues[i] = Math.abs((double)exactFunct.getData().get(i).getYValue()
                    - (double)aproxFunct.getData().get(i).getYValue());
        }

        Series errorSeries = new Series<String,Number>();


        for (int i = 0; i < N; i++) {
            errorSeries.getData().add(new XYChart.Data(errorXValues[i], errorYValues[i]));
        }

        errorSeries.setName(aproxFunct.getName());
        return errorSeries;

    }

    /**
     * computes the max error value for certain N
     * N e [minN, maxN]
     * @return
     */
    private Series<String, Number> computeTruncationError(NumericalMethod method){
        Series truncationSeries = new Series <String, Number>();

        for (int i = minN; i <= maxN; i++) { //compute maxError for certain N
            method.setN(i);
            Series<String, Number> errorSeries = computeError(new ExactMethod(X0, Y0, i, X), method); //computing errorSeries for certain Exact and Approximated series

            double maxError = 0;

            for (int j = 1; j < i; j++) {
                double temp = (double) errorSeries.getData().get(j).getYValue();
                if (temp>maxError) maxError=temp;
            }

            truncationSeries.getData().add(new XYChart.Data(i, maxError));
        }
        truncationSeries.setName(method.name);

        return truncationSeries;
    }

}
