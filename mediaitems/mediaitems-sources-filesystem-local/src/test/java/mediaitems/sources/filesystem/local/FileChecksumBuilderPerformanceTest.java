package mediaitems.sources.filesystem.local;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import mediaitems.sources.filesystem.local.TestCategories.PerformanceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(PerformanceTest.class)
public class FileChecksumBuilderPerformanceTest {

	private FileChecksumBuilder checksumBuilder;

	private final static List<Integer> BLOCKSIZES = Arrays.<Integer> asList(new Integer[] { 32 * 1024, 64 * 1024, 128 * 1024, 512 * 1024,
			1024 * 1024 });

	@Before
	public void setup() {
		this.checksumBuilder = new FileChecksumBuilder("SHA-1");
	}

	@Test
	public void test() throws IOException {
		final StringBuilder resultBuilder = new StringBuilder("blocksize;filesize;checksum;time;b/s\n");
		Path testfilepath = FileSystems.getDefault().getPath("sampledata");
		Files.list(testfilepath).forEach((filename) -> testBuildChecksumForFile(filename, resultBuilder));
		System.out.println(resultBuilder.toString());
	}

	private void testBuildChecksumForFile(Path path, StringBuilder resultBuilder) {

		// ein durchlauf trocken, sonst ist die erste blocksize wegen diskcache
		// und diskhead position im nachteil
		try {
			checksumBuilder.buildChecksum(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		BLOCKSIZES.forEach((blocksize) -> {
			try {
				long start = System.currentTimeMillis();
				String checksum = checksumBuilder.buildChecksum(path, blocksize);
				long end = System.currentTimeMillis();
				long total = (end==start)?1:end - start;

				assertNotNull("checksum was null", checksum);

				BasicFileAttributes attrs;

				attrs = Files.<BasicFileAttributes> readAttributes(path, BasicFileAttributes.class);
				long filesize = attrs.size();

				resultBuilder.append(blocksize + ";" + filesize + ";" + checksum + ";" + total + ";" + filesize / total + "\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

	}
}
