package br.com.dextra.feign;

import java.util.List;
import java.util.Map;

import br.com.dextra.dto.HouseResource;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface HouseClient {
	
	@RequestLine("GET /houses/{houseId}")
	List<HouseResource> findByHouse(@Param("houseId") String idHouse, @QueryMap Map<String, String> options);

}
