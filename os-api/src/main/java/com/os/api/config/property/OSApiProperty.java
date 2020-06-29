package com.os.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("osapiproperty")
public class OSApiProperty {
	
	private String originPermitida = "http://localhost:4200";
	private final Seguranca seguranca = new Seguranca();
	
	
	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
	
	public Seguranca getSeguranca() {
		
		return this.seguranca;
	}
	
	
	public static class Seguranca {
		
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
	}
	

}
