package mediaitems.metadata.springdata.repository;

import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.springdata.domain.MediaItemImpl;
import mediaitems.metadata.springdata.domain.TagImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataMediaItemRepository extends
		PagingAndSortingRepository<MediaItemImpl, String> {

	Page<MediaItemImpl> findByMediaType(MediaType mediaType, Pageable pageable);

	Page<MediaItemImpl> findByTags(TagImpl tag, Pageable pageable);

}
