package com.gas.model;

import java.util.ArrayList;
import java.util.List;

public class Chart {
    private List<String> labels = new ArrayList<String>();
    private List<Dataset> datasets = new ArrayList<Dataset>();

    public List<String> getLabels() {
        return labels;
    }
    public List<Dataset> getDatasets() {
        return datasets;
    }
}