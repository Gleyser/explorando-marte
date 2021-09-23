package gleyser.explorandomarte.mapper;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import gleyser.explorandomarte.entity.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocalizacaoMapper {

    LocalizacaoMapper INSTANCE = Mappers.getMapper(LocalizacaoMapper.class);

    Localizacao toModel(LocalizacaoDTO localizacaoDTO);

    LocalizacaoDTO toDTO(Localizacao localizacao);
}
