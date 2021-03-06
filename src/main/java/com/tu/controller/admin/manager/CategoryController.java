package com.tu.controller.admin.manager;


import com.tu.model.Category;
import com.tu.model.Product;
import com.tu.repository.CategoryRepository;
import com.tu.repository.ProductRepository;
import com.tu.service.CategoryService;
import com.tu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public String showList(Model model, Pageable pageable) {
        model.addAttribute("list", categoryService.showAll(pageable));
        return "admin/manager/category/list-category";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/manager/category/add-category";
    }

    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes attributes) {
        try {
            if (result.hasErrors()) {
                return "admin/manager/category/add-category";
            }
            String name = category.getName();
            List<Category> categoryTemps = categoryRepository.findByName(name);
            if (categoryTemps == null || categoryTemps.isEmpty()) {
                categoryService.save(category);
                attributes.addFlashAttribute("mess", "Th??m m???i th??nh c??ng...!!!");
            } else {
                attributes.addFlashAttribute("mess", "T??n Category ???? t???n t???i");
            }
            return "redirect:/category";
        } catch (Exception e) {
            attributes.addFlashAttribute("mess", "Rrororo");
            return "redirect:/category";
        }

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "admin/manager/category/edit-category";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes attributes) {
        try {
            if (result.hasErrors()) {
                return "admin/manager/category/edit-category";
            }

            List<Category> categories = categoryRepository.findAll();
            for (Category category11 : categories) {
                if ((category.getName()).equals(category11.getName()) && (category.getId()) != category11.getId()) {
                    attributes.addFlashAttribute("mess", "T??n ???? t???n t???i...!!!");
                    return "redirect:/category";
                }
            }
            categoryService.save(category);
            attributes.addFlashAttribute("mess", "Thay ?????i th??nh c??ng...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("mess", "x??a th??nh c??ng");
        return "redirect:/category";
    }


    @GetMapping("/showDeleteCategory")
    public String showDelete(Model model, Pageable pageable) {
        model.addAttribute("list", categoryRepository.findAllByDeletedIsTrue(pageable));
        return "admin/manager/category/list-delete-category";
    }

    @GetMapping("/reset/{id}")
    public String reset(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findById(id).orElseThrow();
        category.setDeleted(false);
        categoryService.save(category);
//        // loi ve list product co cung cat id
//        List<Product> product = productRepository.findAllByCategoryId(id);
//        // chay vong lap qua list
//        for (Product product1 : product) {
//                product1.setDeleted(false);
//                productRepository.save(product1);
//        }
//        // set isDelete cua moi cai thanh false
//
//        // save all
        redirectAttributes.addFlashAttribute("mess", "kh??i ph???c th??nh c??ng");
        return "redirect:/category/showDeleteCategory";
    }
}
