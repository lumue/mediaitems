package mediaitems.http.service.content;

import java.io.IOException;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.repository.MediaItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentStreamingController {

	@Autowired(required = true)
	MediaItemRepository mediaItemRepository;

	@RequestMapping("content/get/{mediaitemKey}")
	@ResponseBody
	public FileSystemResource streamContent(@PathVariable String mediaitemKey)
			throws IOException {

		MediaItem mediaItem = mediaItemRepository.get(mediaitemKey);

		return new FileSystemResource(mediaItem.getContentLocation().getUrl());
	}
}