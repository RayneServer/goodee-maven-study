package com.coma.study.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
	private Long productNum;
	private Long productKindNum;
	private String productName;
	private String productContent;
	private LocalDate productDate;
	private Double productRate;
	
	// join with product_kind (1:1)
	private ProductKindDTO productKindDTO;
	
	// productDate Formatter
	private String productDateToString;
	
	public void setProductDate(LocalDate productDate) {
		this.productDate = productDate;
		setProductDateToString(productDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
	}
}
