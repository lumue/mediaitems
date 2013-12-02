package mediaitems.data.domain;


public enum MediaType {
	
	DOCUMENT("DOCUMENT"),VIDEO("VIDEO"),AUDIO("AUDIO");
	
	private final String key;
	
	private MediaType(String key)
	{
		this.key=key;
	}

	public String getKey() {
		return key;
	}
	
	
}
