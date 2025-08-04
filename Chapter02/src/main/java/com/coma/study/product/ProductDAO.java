package com.coma.study.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {

	List<ProductDTO> selectProductList() throws Exception;
	ProductDTO selectProductDetail(Long productNum) throws Exception;
}
