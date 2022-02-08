package com.better.apitest.dto;


public class VariableDto {
    /* 变量名 */
    private String name;

    /* 变量值 */
    private String value;
    public VariableDto(){

    }

    public VariableDto(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
