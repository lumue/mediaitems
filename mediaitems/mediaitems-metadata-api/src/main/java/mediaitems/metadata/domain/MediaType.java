package mediaitems.metadata.domain;

public enum MediaType {

	DOCUMENT("DOCUMENT"),
	VIDEO("VIDEO"),
	AUDIO("AUDIO"),
	IMAGE("IMAGE"),
	UNKNOWN("UNKNOWN");

	private final String key;

	private MediaType(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static MediaType forKey(String key) {
		switch (key.toUpperCase()) {
		case "DOCUMENT":
			return DOCUMENT;
		case "VIDEO":
			return VIDEO;
		case "AUDIO":
			return AUDIO;
		case "IMAGE":
			return IMAGE;
		default:
			return UNKNOWN;
		}
	}

	public static MediaType forMimeType(String mimeType) {
		if (mimeType.startsWith("video"))
			return VIDEO;
		if (mimeType.startsWith("audio"))
			return AUDIO;
		if (mimeType.startsWith("image"))
			return IMAGE;
		if (mimeType.startsWith("text") || mimeType.startsWith("application"))
			return DOCUMENT;

		return UNKNOWN;
	}

}
