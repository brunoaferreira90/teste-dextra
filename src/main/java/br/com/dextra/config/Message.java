package br.com.dextra.config;

import lombok.Data;

@Data
public class Message<T> {
	
	private final CorrelationId id;
	private final T payload;
	

}
