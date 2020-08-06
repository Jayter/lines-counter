package org.eugenet.lines.counter.service.count;

import java.io.File;
import java.util.List;
import org.eugenet.lines.counter.domain.FileInfo;

public interface FileLinesCountingService {

    List<FileInfo> countLines(File file);
}
