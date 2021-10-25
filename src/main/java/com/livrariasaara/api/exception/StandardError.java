package com.livrariasaara.api.exception;



import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) //não vai incluir propriedades null
@Getter
@Setter
@NoArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private OffsetDateTime dateTime;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<Campo> campos;
    
   


	public StandardError(OffsetDateTime dateTime, Integer status, String error, String message,
			String path) {
		super();
		this.dateTime = dateTime;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	@AllArgsConstructor
	@Getter
	public static class Campo {
		private String name;
		private String message;
	}
    
  
}
