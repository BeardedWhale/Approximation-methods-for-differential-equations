package sample;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

import javax.swing.*;

import javafx.scene.control.Button;
import java.awt.event.ActionListener;

/**
 * View abstraction
 * It has several boolean fields which determine what charts are required to draw
 */
public class View {



    private CheckBox exact;
    private CheckBox euler;
    private CheckBox ieuler;
    private CheckBox rungeKutta;



    private Button draw;

    public boolean isExactRequired(){
        return exact.isSelected();
    }

    public boolean isEulerRquired(){
        return euler.isSelected();
    }

    public boolean isIEulerRequierd(){
        return  ieuler.isSelected();
    }

    public boolean isRungeKuttaRequired(){
        return rungeKutta.isSelected();
    }



    public View(CheckBox exact, CheckBox euler, CheckBox ieuler, CheckBox rungeKutta, Button draw) {
        this.exact = exact;
        this.euler = euler;
        this.ieuler = ieuler;
        this.rungeKutta = rungeKutta;
        this.draw = draw;
    }
}
