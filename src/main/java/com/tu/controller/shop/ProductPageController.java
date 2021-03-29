package com.tu.controller.shop;


import com.tu.model.Product;
import com.tu.repository.ProductRepository;
import com.tu.service.CategoryService;
import com.tu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/productPage/{id}")
    public String showProductPage(@PathVariable Long id, Model model, Pageable pageable){
        Product product = productService.findById(id).orElseThrow();
        model.addAttribute("product",product);
        model.addAttribute("categories", categoryService.showAll(pageable));
        model.addAttribute("listProduct", productRepository.findAllByCategoryId(product.getCategory().getId(),pageable));
        return "shop/product-page";
    }
}
