package gleyser.explorandomarte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ColisaoException extends Exception{
    public ColisaoException() {
        super();
    }
}
