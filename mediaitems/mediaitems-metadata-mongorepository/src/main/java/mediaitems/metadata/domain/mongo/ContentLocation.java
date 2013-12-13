package mediaitems.metadata.domain.mongo;

import org.springframework.data.annotation.Id;

public class ContentLocation {
	
	@Id
	private String key;
	
	private String url;

	public ContentLocation(String url) {
		setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}
	
	
	
}
