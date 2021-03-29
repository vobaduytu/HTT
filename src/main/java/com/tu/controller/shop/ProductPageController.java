package com.tu.controller.shop;


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

    @GetMapping("/productPage/{id}")
    public String showProductPage(@PathVariable Long id, Model model, Pageable pageable){
        model.addAttribute("product",productService.findById(id));
        return "shop/product-page";
    }
}
