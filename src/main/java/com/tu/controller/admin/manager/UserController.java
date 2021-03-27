package com.tu.controller.admin.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @GetMapping("")
    public String showList() {
        return "user-list";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "user-add";
    }

    @PostMapping("/add")
    public String doAdd() {
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String doEditForm() {
        return "user-edit";
    }

    @PostMapping("/edit")
    public String doEdit() {
        return "user-edit";
    }

    @GetMapping("/delete/{id}")
    public String doDelete() {
        return "user-list";
    }
}
