package mediaitems.data.domain;

import org.springframework.data.annotation.Id;

public class ContentLocation {
	
	@Id
	private String key;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
