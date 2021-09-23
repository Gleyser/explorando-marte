package gleyser.explorandomarte.validation;

import gleyser.explorandomarte.exception.MalhaNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> erros = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((erro) ->{
			
			String atributoComErro = ((FieldError) erro).getField();			
			String mensagem= erro.getDefaultMessage();			
			erros.put(atributoComErro, mensagem);
		});
		return new ResponseEntity<Object>(erros, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MalhaNaoEncontradaException.class)
	public final ResponseEntity<Object> handleMalhaNotFoundException() {
		Map<String, String> erros = new HashMap<>();
		erros.put("Erro", "Malha não encontrada");
		return new ResponseEntity<Object>(erros, HttpStatus.NOT_FOUND);
	}

}
