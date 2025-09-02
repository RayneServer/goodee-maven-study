package dev.coma.study.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productDAO;
	
	public List<ProductDTO> getProductList() throws Exception {
		return productDAO.selectProductList();
	}

	public ProductDTO getProductDetail(Long productNum) throws Exception {
		return productDAO.selectProductDetail(productNum);
	}

	public int addProduct(ProductDTO productDTO) throws Exception {
		return productDAO.insertProduct(productDTO);
	}

	public int updateProduct(ProductDTO productDTO) throws Exception {
		return productDAO.updateProduct(productDTO);
	}

	public int deleteProduct(Long productNum) throws Exception {
		return productDAO.deleteProduct(productNum);
	}
}
