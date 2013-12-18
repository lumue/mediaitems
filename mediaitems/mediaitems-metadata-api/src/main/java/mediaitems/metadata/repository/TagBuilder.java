package mediaitems.metadata.repository;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.domain.Tag;

public interface TagBuilder<T extends Tag> extends Builder<T> {
	public TagBuilder<? extends T> setName(String name);
}
