package com.coma.study.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("list")
	public void productList(Model model) throws Exception {
		List<ProductDTO> productList = productService.getProductList();
		
		if (productList.size() > 0) {
			model.addAttribute("productList", productList);
		}
	}
	
	@GetMapping("detail")
	public void productDetail(Long productNum, Model model) throws Exception {
		ProductDTO product = productService.getProductDetail(productNum);
		
		if (product != null) {
			model.addAttribute("product", product);
		}
	}
	
	@GetMapping("add")
	public String productAdd() throws Exception {
		return "product/product_form";
	}
	
	@GetMapping("update")
	public String productUpdate() throws Exception {
		return "product/product_form";
	}
}
