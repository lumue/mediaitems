package mediaitems.metadata.springdata.repository;

import mediaitems.metadata.springdata.domain.TagImpl;
import mediaitems.metadata.springdata.domain.VideoItemImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataVideoItemRepository extends PagingAndSortingRepository<VideoItemImpl, String> {
	Page<VideoItemImpl> findByTags(TagImpl tag, Pageable pageable);
}
