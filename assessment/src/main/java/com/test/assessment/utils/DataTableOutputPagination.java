package com.test.assessment.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataTableOutputPagination<T> implements Serializable {


    private long draw;

    private long recordsFiltered;

    private List<T> data;

    private long recordsTotal;
}
