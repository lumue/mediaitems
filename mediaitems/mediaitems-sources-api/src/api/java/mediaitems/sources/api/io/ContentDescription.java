package mediaitems.sources.api.io;

import java.util.Map;

import org.joda.time.LocalDateTime;

public interface ContentDescription {
		public String getName();
		public String getMimeType();
		public Long getSize();
		public LocalDateTime getCreationTime();
		public LocalDateTime getModificationTime();
		public LocalDateTime getLastAccessTime();
		public Map<String,Object> getProperties();
}
