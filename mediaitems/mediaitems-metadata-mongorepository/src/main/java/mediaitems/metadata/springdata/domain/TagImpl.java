package mediaitems.metadata.springdata.domain;

import mediaitems.metadata.domain.Tag;
import mediaitems.metadata.repository.TagBuilder;

import org.springframework.data.annotation.Id;

public class TagImpl implements Tag {

	@Id
	private String key;

	private final String name;

	TagImpl(String name) {
		this.name = name;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TagImpl other = (TagImpl) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class TagImplBuilder implements TagBuilder<TagImpl> {

		private String name;

		@Override
		public TagImpl build() {
			return new TagImpl(this.name);
		}

		@Override
		public TagImplBuilder setName(String name) {
			this.name = name;
			return this;
		}

	}
}
