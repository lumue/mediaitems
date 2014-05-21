package mediaitems.configuration.sources.api.model;

import java.net.URI;

import mediaitems.common.domain.api.Builder;

public interface ContentSource {

	public String getId();

	public String getName();

	public void setName(String name);

	public URI getUrl();

	public static class ContentSourceImpl implements ContentSource {

		private final String id;
		private String name;
		private final URI url;

		private ContentSourceImpl(String id, String name, URI url) {
			super();
			this.id = id;
			this.name = name;
			this.url = url;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ContentSourceImpl other = (ContentSourceImpl) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return ContentSourceImpl.class.getTypeName()+"[id=" + id + ", name=" + name
					+ ", path=" + getUrl() + "]";
		}

		@Override
		public URI getUrl() {
			return this.url;
		}

	}

	public static class ContentSourceBuilder implements Builder<ContentSource> {
		private String name;
		private URI url;
		private String key;

		public ContentSourceBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ContentSourceBuilder setUrl(URI url) {
			this.url = url;
			return this;
		}

		@Override
		public ContentSource build() {
			return new ContentSourceImpl(key, name,
					url);
		}

		public void setKey(String key) {
			this.key = key;
		}
	}

}
