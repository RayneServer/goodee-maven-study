package com.coma.study.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@PostMapping("add")
	public ModelAndView productAdd(ProductDTO productDTO) throws Exception {
		int result = productService.addProduct(productDTO);
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("resultMsg", "상품 등록에 실패했습니다.");
		mnv.addObject("url", "list");
		
		if (result > 0) {
			mnv.addObject("resultMsg", "상품 등록에 성공했습니다.");
		}
		
		mnv.setViewName("commons/result");
		
		return mnv;
	}
	
	@GetMapping("update")
	public String productUpdate(Long productNum, Model model) throws Exception {
		ProductDTO product = productService.getProductDetail(productNum);
		
		if (product != null) {
			model.addAttribute("product", product);
		}
		
		return "product/product_form";
	}
	
	@PostMapping("update")
	public String productUpdate(ProductDTO productDTO, Model model) throws Exception {
		int result = productService.updateProduct(productDTO);
		
		model.addAttribute("resultMsg", "상품 수정에 실패했습니다.");
		model.addAttribute("url", "detail?productNum=" + productDTO.getProductNum());
		
		if (result > 0) {
			model.addAttribute("resultMsg", "상품 수정에 성공했습니다.");
		}
		
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String productDelete(Long productNum, Model model) throws Exception {
		int result = productService.deleteProduct(productNum);
		
		model.addAttribute("resultMsg", "상품 삭제에 실패했습니다.");
		model.addAttribute("url", "list");
		
		if (result > 0) {
			model.addAttribute("resultMsg", "상품 삭제에 성공했습니다.");
		}
		
		return "commons/result";
	}
}
