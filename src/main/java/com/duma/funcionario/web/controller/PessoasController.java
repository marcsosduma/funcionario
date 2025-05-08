package com.duma.funcionario.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duma.funcionario.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {    

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("pessoas", pessoaService.buscarTodos());
        return "/pessoa/lista";
    }

}
