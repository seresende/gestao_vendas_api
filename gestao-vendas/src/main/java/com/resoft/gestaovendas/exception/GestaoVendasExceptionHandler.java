package com.resoft.gestaovendas.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice - fica ouvindo a aplicação ao verificar excessão verica na calsse se tem tratamento
//Nesta classe vou subscrever algumas excessoes que foram lançadas
@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
	private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
	private static final String CONSTANT_VALIDATION_LENGTH = "Length";

	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = geraListaDeErros(ex.getBindingResult());

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	//Tratar excessão quando ocorrer categoria inexistente
	//tambem não tem classe para sub-escrever igual ocorreu com os outros.
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		
		String msgUser = "Recurso não encontrado";
		String msgDeveloper = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(msgUser,msgDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		
		String msgUser = "Recurso não encontrado";
		String msgDeveloper = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(msgUser,msgDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	
	
	//tenho que lancar a execessão no handle
	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request){
		
		String msgUser = ex.getMessage();
		String msgDeveloper = ex.getMessage();
		
		List<Error> errors = Arrays.asList(new Error(msgUser,msgDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	private List<Error> geraListaDeErros(BindingResult bindingResult) {

		List<Error> erros = new ArrayList<Error>();
		bindingResult.getFieldErrors().forEach(fieldError -> {
			// para o usuario a msn será aquela do bean validacion
			String msgUser = tratarMsgUser(fieldError);
			String msgDeveloper = fieldError.toString();
			erros.add(new Error(msgUser, msgDeveloper));
		});
		return erros;

	}

	private String tratarMsgUser(FieldError fieldError) {

		if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
			return fieldError.getDefaultMessage().concat(" é obrigatório.");
		}
		
		if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)) {
			return fieldError.getDefaultMessage().concat(" é obrigatório.");
		}

		// tratr o min e max das entradas
		if (fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
			return fieldError.getDefaultMessage().concat(String.format("deve ter entre %s e %s caracteres,",
					fieldError.getArguments()[2], fieldError.getArguments()[1]));
		}

		return fieldError.toString();
	}

}
