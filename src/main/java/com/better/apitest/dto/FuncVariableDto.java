package com.better.apitest.dto;


public class FuncVariableDto {
    private String name;
    private String func;
    private String param1;
    private String param2;

    public FuncVariableDto() {
    }

    public FuncVariableDto(String name, String func, String param1, String param2) {
        this.name = name;
        this.func = func;
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
