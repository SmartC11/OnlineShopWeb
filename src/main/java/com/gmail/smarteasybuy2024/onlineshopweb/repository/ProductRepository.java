package com.gmail.smarteasybuy2024.onlineshopweb.repository;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 根据一组 ID 查找产品
    List<Product> findByIdIn(List<Long> productIds);

    // 按类型搜索产品
    @Query("SELECT product FROM Product product WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'productName' THEN UPPER(product.productName) " +
            "   WHEN :searchType = 'category' THEN UPPER(product.category) " +
            "   ELSE UPPER(product.description) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY product.price ASC")
    Page<Product> searchProducts(String searchType, String text, Pageable pageable);

    // 根据多条件筛选产品
    @Query("SELECT product FROM Product product " +
            "WHERE (coalesce(:categories, null) IS NULL OR product.category IN :categories) " +
            "AND (coalesce(:priceStart, null) IS NULL OR product.price BETWEEN :priceStart AND :priceEnd) " +
            "AND (coalesce(:releaseYears, null) IS NULL OR product.releaseYear IN :releaseYears) " +
            "AND (:productName IS NULL OR LOWER(product.productName) LIKE LOWER(CONCAT('%', :productName, '%'))) " +
            "AND (:specifications IS NULL OR LOWER(product.specifications) LIKE LOWER(CONCAT('%', :specifications, '%'))) " +
            "ORDER BY product.price ASC")
    Page<Product> getProductsByFilterParams(
            List<String> categories,
            Integer priceStart,
            Integer priceEnd,
            List<Integer> releaseYears,
            String productName,
            String specifications,
            Pageable pageable);

    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);

}
