package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{






    private View V;
    private Model M;

                            /* Default Model settings */
    final private double x0=1;
    final private double y0 = 1;
    private int N=100;
    final private double x = 9.5;


    @Override
    @FXML
    /**
     * creates View and model
     */
    public void initialize(URL location, ResourceBundle resources) {
        V = new View(exact, euler, ieuler, rungeKutta, draw);
        M = new Model(x0, y0, N, x);
    }

                                /* Button Handlers */

    /**
     * This method adds charts (That are selected in checkboxes) to LineChart in Tab1
     * Handles action on button draw on tab1
     */
    public void drawChart(){
        changeN();
        M.setRequiredCharts(V.isExactRequired(), V.isEulerRquired(), V.isIEulerRequierd(), V.isRungeKuttaRequired());

        ArrayList<XYChart.Series<String, Number>> functSeries = M.getFunctionSeries();

        functionChart.getData().clear();
        functionChart.getData().addAll(functSeries);
    }

    /**
     * This method adds charts (That are sekected in chackboxes) to LineChart on Tab2
     * It handles action buttion "draw error" on Tab2
     */
    public void drawErrorChart(){
        changeN2();
        M.setRequiredCharts(V.isExactRequired(), V.isEulerRquired(), V.isIEulerRequierd(), V.isRungeKuttaRequired());

        ArrayList<XYChart.Series<String, Number>> errorSeries = M.getErrorSeries();

        errorChart.getData().clear();
        errorChart.getData().addAll(errorSeries);
    }

    /**
     * This method adds charts (that are selected in checkboxes) to lineChart on Tab3
     * It handles action on button "Truncation error" on tab3
     */
    public void drawTruncationChart(){

        changeMaxN();
        changeMinN();

        M.setRequiredCharts(V.isExactRequired(), V.isEulerRquired(), V.isIEulerRequierd(), V.isRungeKuttaRequired());

        ArrayList<XYChart.Series<String, Number>> truncationErrorSeries = M.getTruncationErrorSeries();

        truncationErrorChart.getData().clear();
        truncationErrorChart.getData().addAll(truncationErrorSeries);
    }


                                                /* TextField Handlers */

    /**
     * Changes value of N in Model object on values from textfield in Tab1
     * to recalculate charts
     */
    public void changeN(){
        N = (nValue.getText().isEmpty())? N : Integer.parseInt(nValue.getText());
        M.setN(N);


    }
    /**
     * Changes value of N in Model object on values from textfield in Tab2
     * to recalculate charts
     */
    public void changeN2(){

        N = (nValue2.getText().isEmpty())? N : Integer.parseInt(nValue2.getText());
        M.setN(N);

    }
    /**
     * Changes value of minN in Model object on values from textfield in Tab3
     * to recalculate charts
     */
    public void changeMinN(){
        int minNVal =(minN.getText().isEmpty())? N : Integer.parseInt(minN.getText());
        M.setMinN(minNVal);
    }

    /**
     * Changes value of maxN in Model object on values from textfield in Tab3
     * to recalculate charts
     */
    public void changeMaxN(){
        int maxNVal = (maxN.getText().isEmpty())? N : Integer.parseInt(maxN.getText());
        M.setMaxN(maxNVal);
    }

    /**
     * This methods create new View object for new Tab in order to get values from other checkboxes
     */

    public void onTab1(){
        V = new View(exact, euler, ieuler,rungeKutta, draw);
    }

    public void onTab2(){
        V = new View(exact2, euler2, ieuler2,rungeKutta2, drawError);
    }

    public void onTab3(){
        V = new View(exact3, euler3, ieuler3,rungeKutta3, truncationError);
    }



                                        /*      FXML objects           */


                                        /*<for tab №1 Function chart>*/
    @FXML
    private LineChart<String, Number> functionChart;
    @FXML
    private CheckBox exact;
    @FXML
    private CheckBox euler;
    @FXML
    private CheckBox ieuler;
    @FXML
    private CheckBox rungeKutta;
    @FXML
    private Button draw;
    @FXML
    private TextField nValue;

                                        /*<for tab №2 Error chart>*/
    @FXML
    private  LineChart<String, Number> errorChart;
    @FXML
    private CheckBox exact2;
    @FXML
    private CheckBox euler2;
    @FXML
    private CheckBox ieuler2;
    @FXML
    private CheckBox rungeKutta2;
    @FXML
    private Button drawError;
    @FXML
    private TextField nValue2;
                                        /*<for tab №3 Truncation Error chart>*/

    @FXML
    private  LineChart<String, Number> truncationErrorChart;
    @FXML
    private CheckBox exact3;
    @FXML
    private CheckBox euler3;
    @FXML
    private CheckBox ieuler3;
    @FXML
    private CheckBox rungeKutta3;
    @FXML
    private Button truncationError;
    @FXML
    private TextField minN;
    @FXML
    private  TextField maxN;
}
