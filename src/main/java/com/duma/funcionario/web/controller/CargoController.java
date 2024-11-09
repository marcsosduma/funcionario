package com.duma.funcionario.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duma.funcionario.domain.Cargo;
import com.duma.funcionario.domain.Departamento;
import com.duma.funcionario.service.CargoService;
import com.duma.funcionario.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {    

    @Autowired 
    private CargoService cargoService;

    @Autowired 
    private DepartamentoService departamentoService;


    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo){
        return "/cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("cargos", cargoService.buscarTodos());
        return "/cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Cargo cargo, RedirectAttributes attr){
        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso");
        return "redirect:/cargos/cadastrar";
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos(){
        return departamentoService.buscarTodos();
    }

    
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cargo", cargoService.buscarPorId(id));
        return "/cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Cargo cargo, RedirectAttributes attr) {
        cargoService.editar(cargo);
        attr.addFlashAttribute("success", "Cargo editado com sucesso");
        return "redirect:/cargos/cadastrar";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        if (cargoService.cargoTemFuncionarios(id)){
            model.addAttribute("fail","Cargo não removido. Possui funcionário(s) vinculado(s).");
        }else{
            cargoService.excluir(id);
            model.addAttribute("success","Cargo removido com sucesso.");
        }
        return listar(model);
    }  

}
