package com.twsihan.extras.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Pagination<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer currentPage = 1; // 当前页码

    private Integer pageCount = 0; // 页码总数

    private Integer pageSize = 20; // 每页多少行数据

    private Integer totalCount = 0; // 数据行总数;

    private List<T> list; // 数据
}
