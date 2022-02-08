package com.better.apitest.dto;

import java.util.List;

public class ApiBodyDto {
    /** flag */
    String flag;

    /** Form Data */
    List<FormDataDto> formData;

    /** Raw */
    RawDto  raw;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<FormDataDto> getFormData() {
        return formData;
    }

    public void setFormData(List<FormDataDto> formData) {
        this.formData = formData;
    }

    public RawDto getRaw() {
        return raw;
    }

    public void setRaw(RawDto raw) {
        this.raw = raw;
    }
}
