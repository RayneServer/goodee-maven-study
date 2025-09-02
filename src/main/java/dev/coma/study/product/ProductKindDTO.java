package dev.coma.study.product;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductKindDTO {
	private Long productKindNum;
	private String productKindName;
	
	// join with product (1:N)
	private List<ProductDTO> productDTOList;
}
