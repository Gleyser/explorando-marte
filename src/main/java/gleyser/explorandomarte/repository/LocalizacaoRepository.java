package gleyser.explorandomarte.repository;

import gleyser.explorandomarte.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    Localizacao findByCoordenadaXAndCoordenadaY(Integer coordenadaX, Integer coordenadaY);

}
