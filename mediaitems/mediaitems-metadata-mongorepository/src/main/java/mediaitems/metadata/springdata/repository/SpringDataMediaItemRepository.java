package mediaitems.metadata.springdata.repository;

import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.springdata.domain.MediaItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataMediaItemRepository extends
		PagingAndSortingRepository<MediaItem, String> {

	Page<MediaItem> findByMediaType(MediaType mediaType, Pageable pageable);

}
