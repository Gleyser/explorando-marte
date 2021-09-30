package gleyser.explorandomarte.mapper;


import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.entity.Sonda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = LocalizacaoMapper.class)
public interface SondaMapper {

    SondaMapper INSTANCE = Mappers.getMapper(SondaMapper.class);

    Sonda toModel(SondaDTO sondaDTO);

    @Mapping(source = "malha.id", target = "idMalha")
    SondaDTO toDTO(Sonda sonda);
}
