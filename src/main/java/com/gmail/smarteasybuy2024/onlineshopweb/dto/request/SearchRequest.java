package com.gmail.smarteasybuy2024.onlineshopweb.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private Integer price = 0;
    private Integer priceMin = 0;         // 最低价格筛选
    private Integer priceMax;             // 最高价格筛选
    private List<String> categories;      // 产品类别
    private List<Integer> releaseYear;     // 发布年份筛选
    private String productName;           // 产品名称关键词
    private String specifications;        // 规格关键词

    private String searchType;
    private String text;                  // 通用搜索文本（多字段模糊匹配用）

}
