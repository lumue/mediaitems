package mediaitems.metadata.springdata.domain;

import org.springframework.data.annotation.Id;

class ContentLocation implements mediaitems.metadata.domain.ContentLocation {

	@Id
	private String key;

	private String url;

	public ContentLocation(String url) {
		setUrl(url);
	}

	@Override
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getKey() {
		return key;
	}

}
