package mediaitems.metadata.repository;

import mediaitems.metadata.domain.Tag;

public interface TagRepository extends MetadataRepository<Tag> {
	@Override
	public TagBuilder<? extends Tag> createNewBuilder();

}
