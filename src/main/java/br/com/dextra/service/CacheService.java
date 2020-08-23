package br.com.dextra.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheService.class);
	
	@Autowired
	private CacheManager cacheManager;
	
	private void evictAllCaches() {
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	@Scheduled(fixedRate = 6000)
	public void evictAllcachesAtIntervals() {
		
		LOG.info("Starting Clear Cache");
		
	    evictAllCaches();
	}

}
