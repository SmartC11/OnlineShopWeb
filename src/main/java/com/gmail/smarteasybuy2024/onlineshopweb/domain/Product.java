package com.gmail.smarteasybuy2024.onlineshopweb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products") // 对应数据库表名
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq") // 使用序列生成主键
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "category", nullable = false) // 映射 category 字段
    private String category;

    @Column(name = "description") // 映射 description 字段
    private String description;

    @Column(name = "filename") // 映射 filename 字段
    private String filename;

    @Column(name = "price", nullable = false) // 映射 price 字段
    private Integer price;

    @Column(name = "product_name", nullable = false) // 映射 product_name 字段
    private String productName;

    @Column(name = "specifications") // 映射 specifications 字段
    private String specifications;

    @Column(name = "stock", nullable = false) // 映射 stock 字段
    private Integer stock;

    @Column(name = "release_year", nullable = false) // 映射 release_year 字段
    private Integer releaseYear;
}

