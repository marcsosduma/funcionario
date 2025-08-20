package com.duma.funcionario.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duma.funcionario.domain.Area;
import com.duma.funcionario.domain.Atuacao;
import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;
import com.duma.funcionario.service.AreaService;
import com.duma.funcionario.service.AtuacaoService;
import com.duma.funcionario.service.PessoaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {    

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private AtuacaoService atuacaoService;
    @Autowired
    private AreaService areaService;

    @GetMapping("/pre_pesquisar")
    public String pre_pesquisar(ModelMap model){
        model.addAttribute("pessoa", new PessoaFiltroForm());
        model.addAttribute("areas", areaService.buscarArea());
        model.addAttribute("subareas", areaService.buscarPorSubAreaPorArea(null));
        return "/pessoa/pesquisa";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(PessoaFiltroForm filtro, HttpSession session, ModelMap model){
        session.setAttribute("PessoaFiltroForm", filtro);
        return "/pessoa/listaJson";
    }

    @GetMapping("/editar_pesquisa")
    public String editar_pesquisa(HttpSession session, ModelMap model){
        PessoaFiltroForm filtro = (PessoaFiltroForm) session.getAttribute("PessoaFiltroForm");
        if(filtro == null)
            filtro = new PessoaFiltroForm();
        model.addAttribute("pessoa", filtro);
        return "/pessoa/pesquisa";
    }

    @GetMapping("/subareas/{areaId}")
    @ResponseBody
    public List<Area> buscarSubArea(@PathVariable Long areaId) {
        return areaService.buscarPorSubAreaPorArea(areaId);
    }

    @GetMapping("/subareas")
    @ResponseBody
    public List<Area> buscarSubArea() {
        return areaService.buscarPorSubAreaPorArea(null);
    }

    @GetMapping("/listar_json")
    @ResponseBody
    public Map<String, Object> listar_json(
            @RequestParam(name = "draw", required = false) Integer draw,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "length", required = false) Integer length,
            @RequestParam(name = "search[value]", required = false) String searchValue,
            @RequestParam(name = "order[0][column]", required = false) Integer orderColumn,
            @RequestParam(name = "order[0][dir]", required = false) String orderDir,
            HttpSession session) {

        int page = (start != null && length != null) ? start / length : 0;

        Sort sort = Sort.unsorted();
        if (orderColumn != null && orderDir != null) {
            String[] colunas = {"codigo", "nome", "cpf", "unidadeVinculo.nome","nome", "departamento", "email"};
            sort = Sort.by(Sort.Direction.fromString(orderDir), colunas[orderColumn]);
        }

        PageRequest pageable = PageRequest.of(page, (length != null) ? length : 10, sort);

        PessoaFiltroForm filtro = (PessoaFiltroForm) session.getAttribute("PessoaFiltroForm");
        if(filtro==null){
            filtro = new PessoaFiltroForm();
        }
        Page<Pessoa> pagina = pessoaService.buscarComFiltro(filtro, pageable);

        List<List<Object>> data = new ArrayList<>();
        for (Pessoa p : pagina.getContent()) {
            List<Atuacao> atuacaoes = atuacaoService.bucarPorCodigoPessoa(p.getCodigo());
            StringBuilder areas = new StringBuilder("<ul>");
            for(Iterator<Atuacao> a = atuacaoes.iterator(); a.hasNext();){
                Atuacao atuacao = a.next();
                areas.append("<li>"+atuacao.getArea().getDescricao()+"</li>");
            }
            areas.append("</ul>");
            String link = "<a href='/pessoa/listar_json'>"+p.getNome()+"</a>";
            String acao = "<input type='radio' name='listaPessoasSelecionadas' value='"+p.getCodigo()+"'/>";
            List<Object> linha = new ArrayList<>();
            linha.add(link);
            linha.add(p.getCpfFormatado());
            linha.add((p.getUnidadeVinculo()!=null)?p.getUnidadeVinculo().getNome():"");
            linha.add(p.getDepartamento());
            linha.add(areas);
            linha.add(acao);
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
