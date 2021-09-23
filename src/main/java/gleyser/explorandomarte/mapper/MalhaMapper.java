package gleyser.explorandomarte.mapper;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.entity.Malha;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = LocalizacaoMapper.class)
public interface MalhaMapper {
		
	MalhaMapper INSTANCE = Mappers.getMapper(MalhaMapper.class);

    Malha toModel(MalhaDTO malhaDTO);

    MalhaDTO toDTO(Malha malha);

}
