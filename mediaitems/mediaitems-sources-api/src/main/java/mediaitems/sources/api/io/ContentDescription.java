package mediaitems.sources.api.io;

import java.util.Map;

import org.joda.time.LocalDateTime;

public interface ContentDescription {
	public String getPath();
		public String getMimeType();

	public String getMediaType();
		public Long getSize();
		public LocalDateTime getCreationTime();
		public LocalDateTime getModificationTime();
		public LocalDateTime getLastAccessTime();
		public Map<String,Object> getProperties();
}
