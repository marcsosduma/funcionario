package com.duma.funcionario.web.conversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.duma.funcionario.domain.Funcionario;
import com.duma.funcionario.service.FuncionarioService;

@Component
public class StringToFuncionarioConversor  implements Converter<String, Funcionario> {

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public Funcionario convert(String text){
        if(text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text);
        return funcionarioService.buscarPorId(id);
    }

} 