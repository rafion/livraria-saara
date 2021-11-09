package com.livrariasaara.api.exception;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.livrariasaara.domain.exception.BusinessRuleException;
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.exception.DataIntegrityException;
import com.livrariasaara.domain.exception.ObjectNoContentException;
import com.livrariasaara.domain.exception.ObjectNotFoundException;


@ControllerAdvice //tratamento global da classse, de erros no spring boot
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired	
	private MessageSource messageSource;
	
	//metodo usado nos erros captados no @Valid referente a validação dos campos no objeto
	//BindingResult br pega o erro padrão primeiro e não executa o @valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<StandardError.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new StandardError.Campo(nome, mensagem));
		}
		
		StandardError erro = new StandardError();
		erro.setStatus(status.value());
		erro.setDateTime(OffsetDateTime.now());
		erro.setMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		erro.setCampos(campos);
		
		return handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	//tratamento local de exceção
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
       
    	StandardError err = new StandardError(
    			OffsetDateTime.now(),
        		HttpStatus.NOT_FOUND.value(),
        		"Recurso não encontrado",
        		e.getMessage(),
        		request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    
    @ExceptionHandler(ObjectNoContentException.class)
    public ResponseEntity<StandardError> objectNoContent(ObjectNoContentException e, HttpServletRequest request) {
       
    	StandardError err = new StandardError(
    			OffsetDateTime.now(),
        		HttpStatus.NO_CONTENT.value(),
        		"Registro não encontrado",
        		e.getMessage(),
        		request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
       
    	StandardError err = new StandardError(
    			OffsetDateTime.now(),
        		HttpStatus.BAD_REQUEST.value(),
        		"Integridade de dados",
        		e.getMessage(),
        		request.getRequestURI());
    	
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<StandardError> constraint(ConstraintException e, HttpServletRequest request) {
        
    	StandardError err = new StandardError(
    			OffsetDateTime.now(),
        		HttpStatus.BAD_REQUEST.value(),
        		"Restrição de dados",
        		e.getMessage(),
        		request.getRequestURI());
    	
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardError> businessRule(BusinessRuleException e, HttpServletRequest request) {
       
    	StandardError err = new StandardError(
    			OffsetDateTime.now(),
        		//System.currentTimeMillis(),
        		HttpStatus.CONFLICT.value(),
        		"Regra de negócio",
        		e.getMessage(),
        		request.getRequestURI());
    	
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
            
    }

}
