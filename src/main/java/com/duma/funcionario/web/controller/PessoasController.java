package com.duma.funcionario.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {    

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("pessoas", pessoaService.buscarTodos());
        return "/pessoa/lista1";
    }


    @GetMapping("/pre_listar")
    public String pre_listar(ModelMap model){
        return "/pessoa/listaJson";
    }

    @GetMapping("/listar_json")
    @ResponseBody
    public Map<String, Object> listar_json(
            @RequestParam(name = "draw", required = false) Integer draw,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "length", required = false) Integer length,
            @RequestParam(name = "search[value]", required = false) String searchValue,
            @RequestParam(name = "order[0][column]", required = false) Integer orderColumn,
            @RequestParam(name = "order[0][dir]", required = false) String orderDir) {

        int page = (start != null && length != null) ? start / length : 0;

        Sort sort = Sort.unsorted();
        if (orderColumn != null && orderDir != null) {
            String[] colunas = {"codigo", "nome", "cpf", "email"};
            sort = Sort.by(Sort.Direction.fromString(orderDir), colunas[orderColumn]);
        }

        PageRequest pageable = PageRequest.of(page, (length != null) ? length : 10, sort);

        String termo = (searchValue != null) ? searchValue : "";
        Page<Pessoa> pagina = pessoaService.buscarComFiltro(termo, pageable);

        List<List<Object>> data = new ArrayList<>();
        for (Pessoa p : pagina.getContent()) {
            List<Object> linha = new ArrayList<>();
            linha.add(p.getCodigo());
            linha.add(p.getNome());
            linha.add(p.getCpfFormatado());
            linha.add(p.getEmail());
            data.add(linha);
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("draw", draw);
        resultado.put("recordsTotal", pessoaService.buscarTodos().size()); // total geral
        resultado.put("recordsFiltered", pagina.getTotalElements()); // total filtrado
        resultado.put("data", data);

        return resultado;
    }
}
