package com.reza.dinosaurus.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelDino> data;

    public String getKode(){
        return kode;
    }

    public String getPesan(){
        return pesan;
    }

    public List<ModelDino> getData(){
        return data;
    }
}