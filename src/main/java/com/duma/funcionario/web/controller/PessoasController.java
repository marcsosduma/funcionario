package com.duma.funcionario.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
import com.duma.funcionario.repository.AreaRepository;
import com.duma.funcionario.repository.AtuacaoRepository;
import com.duma.funcionario.repository.PessoaRepositoryImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {    

    @Autowired
    private PessoaRepositoryImpl pessoaRepositoryImpl;
    @Autowired
    private AtuacaoRepository atuacaoRepository;
    @Autowired
    private AreaRepository areaRepository;

    @GetMapping("/pre_pesquisar")
    public String pre_pesquisar(ModelMap model){
        model.addAttribute("pessoa", new PessoaFiltroForm());
        model.addAttribute("areas", areaRepository.listGrandeArea());
        model.addAttribute("subareas", areaRepository.listSubArea());
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
        model.addAttribute("areas", areaRepository.listGrandeArea());
        if(filtro.getArea()!=null)
            model.addAttribute("subareas", areaRepository.listSubAreaPorGrandArea(Long.valueOf(filtro.getArea())));
        else
            model.addAttribute("subareas", areaRepository.listSubArea());
        return "/pessoa/pesquisa";
    }

    @GetMapping("/subareas/{areaId}")
    @ResponseBody
    public List<Area> buscarSubArea(@PathVariable Long areaId) {
        return areaRepository.listSubAreaPorGrandArea(areaId);
    }

    @GetMapping("/subareas")
    @ResponseBody
    public List<Area> buscarSubArea() {
        return areaRepository.listSubArea();
    }

    @GetMapping("/listar_json")
    @ResponseBody
    public Map<String, Object> listar_json(
        @RequestParam(name = "draw", required = false) Integer draw,
        @RequestParam(name = "start", required = false) Integer start,
        @RequestParam(name = "length", required = false) Integer length,
        @RequestParam(name = "search[value]", required = false) String searchValue,
        // Note a mudança para capturar todos os parâmetros de ordenação
        @RequestParam Map<String, String> allParams,
        HttpSession session) {

        int page = (start != null && length != null) ? start / length : 0;

        Sort sort = Sort.unsorted();
        List<Order> orders = new ArrayList<>();
        String[] colunas = {"codigo", "nome", "cpf", "unidadeVinculo.nome", "nome", "departamento", "email"};

        // Itera sobre os parâmetros para encontrar todas as colunas de ordenação
        for (int i = 0; i < colunas.length; i++) {
            String columnKey = "order[" + i + "][column]";
            String dirKey = "order[" + i + "][dir]";

            if (allParams.containsKey(columnKey) && allParams.containsKey(dirKey)) {
                Integer orderColumn = Integer.parseInt(allParams.get(columnKey));
                String orderDir = allParams.get(dirKey);

                if (orderColumn < colunas.length) {
                    String property = colunas[orderColumn];
                    Sort.Direction direction = Sort.Direction.fromString(orderDir);
                    orders.add(new Order(direction, property));
                }
            } else {
                // Interrompe o loop se não houver mais colunas de ordenação
                break;
            }
        }

        if (!orders.isEmpty()) {
            sort = Sort.by(orders);
        }
        
        PageRequest pageable = PageRequest.of(page, (length != null) ? length : 10, sort);

        PessoaFiltroForm filtro = (PessoaFiltroForm) session.getAttribute("PessoaFiltroForm");
        if(filtro == null){
            filtro = new PessoaFiltroForm();
        }
        
        Page<Pessoa> pagina = pessoaRepositoryImpl.buscarComFiltro(filtro, pageable);

        List<List<Object>> data = new ArrayList<>();
        for (Pessoa p : pagina.getContent()) {
            List<Atuacao> atuacoes = atuacaoRepository.findByPessoaId(p.getCodigo());
            StringBuilder areas = new StringBuilder("<ul>");
            atuacoes.forEach(atuacao -> areas.append("<li>").append(atuacao.getArea().getDescricao()).append("</li>"));
            areas.append("</ul>");
            
            String link = "<a href='/pessoa/listar_json'>"+p.getNome()+"</a>";
            String acao = "<input type='radio' name='listaPessoasSelecionadas' value='"+p.getCodigo()+"'/>";
            
            List<Object> linha = new ArrayList<>();
            linha.add(link);
            linha.add(p.getCpfFormatado());
            linha.add((p.getUnidadeVinculo() != null) ? p.getUnidadeVinculo().getNome() : "");
            linha.add(p.getDepartamento());
            linha.add(areas);
            linha.add(acao);
            data.add(linha);
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("draw", draw);
        resultado.put("recordsTotal", pessoaRepositoryImpl.contarTodos());
        resultado.put("recordsFiltered", pagina.getTotalElements());
        resultado.put("data", data);

        return resultado;
    }
}
