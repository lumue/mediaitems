package mediaitems.metadata.domain;

import org.joda.time.Duration;

public interface VideoItem extends MediaItem {

	public interface VideoItemBuilder<T extends VideoItem> extends
			MediaItemBuilder<T> {
		
		public VideoItemBuilder<T> setVideoCodec(String codec);

		public VideoItemBuilder<T> setAudioCodec(String codec);

		public VideoItemBuilder<T> setLength(Duration duration);
		
	}

	public String getVideoCodec();

	public String getAudioCodec();

	public Duration getLength();
}
