package br.com.dextra.service;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public abstract class RestService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestService.class);
	
	@Autowired
	private RestTemplate restTemplate;

	public abstract String getUrl();

	public abstract String getToken();

	public String buildUrlWithKey(String path, Map<String, Object> uriVariables) {

		String fullUrl = !ObjectUtils.isEmpty(path) ? getUrl() + path : getUrl();

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(fullUrl);

		if (!ObjectUtils.isEmpty(uriVariables)) {
			uriBuilder.uriVariables(uriVariables);
		}

		uriBuilder.queryParam("key", getToken());

		return uriBuilder.toUriString();
	}

	public Object get(String urlWithKey, Class<?> object){

		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<String> response = restTemplate.exchange(urlWithKey, HttpMethod.GET, entity, String.class);

			LOG.info("Executando chamada Rest para ", urlWithKey);
			
			if(response.getStatusCode().is2xxSuccessful()) {
				return new ObjectMapper().readValue(response.getBody(), object);
			} 
			
			return null;
			
		} catch (Exception e) {
			
			//Tratamento genérico pois a API está retornando 200 com um objeto diferente do esperado ao invés de um objeto vazio

			LOG.info("Problema ao chamada pesquisa ", urlWithKey);

			return null;
		}

	}

}
