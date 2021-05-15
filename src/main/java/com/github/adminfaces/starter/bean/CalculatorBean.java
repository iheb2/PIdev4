package com.github.adminfaces.starter.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mukesh
 */
@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class CalculatorBean {

    private int _param1;
    private int _param2;
    private String _result;
    
    /**
     * Creates a new instance of CalculatorBean
     */
    public CalculatorBean() {
    }
    
    public int getParam1() {
        return _param1;
    }

    public void setParam1(int param1) {
        _param1 = param1;
    }

    public int getParam2() {
        return _param2;
    }

    public void setParam2(int param2) {
        _param2 = param2;
    }

    public String getResult() {
        return _result;
    }
    // 

    public String add(){
        _result = Integer.toString(_param1 + _param2);
        return "";
    }

    public String substract(){
        _result = Integer.toString(_param1 - _param2);
        return "";
    }

    public String multiply(){
        _result = Integer.toString(_param1 * _param2);
        return "";
    }

    public String divide(){
        _result = _param2 == 0 ? "n/a" : Double.toString(_param1 / (double)_param2);
        return "";
    }
}