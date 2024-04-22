package com.GeladaoEstacio.Geladao.config;

import com.GeladaoEstacio.Geladao.entities.ItensVendidos;
import com.GeladaoEstacio.Geladao.entities.Produto;
import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.entities.Venda;
import com.GeladaoEstacio.Geladao.web.dtos.ProdutoDTO;
import com.GeladaoEstacio.Geladao.web.dtos.VendaDTO;
import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnUsuarioDTO;
import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnVendaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        // Mapeamento de Usuario para UsuarioDTO
        modelMapper.createTypeMap(Usuario.class, ReturnUsuarioDTO.class);
        // Mapeamento de ReturnUsuarioDTO para Usuario
        modelMapper.createTypeMap(ReturnUsuarioDTO.class, Usuario.class);



        //Mapeamentro
        modelMapper.createTypeMap(Produto.class, ReturnUsuarioDTO.class);
        modelMapper.createTypeMap(ReturnUsuarioDTO.class, Produto.class);
        modelMapper.createTypeMap(ProdutoDTO.class, Produto.class);

        modelMapper.createTypeMap(Venda.class, VendaDTO.class);
        modelMapper.createTypeMap(VendaDTO.class, Venda.class);
        modelMapper.createTypeMap(ReturnVendaDTO.class, Venda.class);
        modelMapper.createTypeMap(Venda.class, ReturnVendaDTO.class);






        return modelMapper;
    }
}
