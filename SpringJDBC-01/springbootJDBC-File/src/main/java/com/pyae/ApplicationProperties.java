package com.pyae;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
	
	private RepositoryConfig sql = new RepositoryConfig();
	
	@Data
	public static class RepositoryConfig{
		private String divisionSelect;
		private String divisionGroupby;
		private String districtSelect;
		private String districtGroupby;
		private String townshipSelect;
		
	}

}
