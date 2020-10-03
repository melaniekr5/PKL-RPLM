package com.shap.shap_office.model;

public class area {
    private String id;
    private String nama;
    private String detail;

    public area(String id, String nama, String detail) {
        this.id = id;
        this.nama = nama;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
