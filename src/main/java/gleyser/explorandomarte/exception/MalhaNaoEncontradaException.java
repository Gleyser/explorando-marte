package gleyser.explorandomarte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MalhaNaoEncontradaException extends Exception{

    public MalhaNaoEncontradaException(Long id) {
        super("Malha não encontrada com o id: " + id);
    }

}
