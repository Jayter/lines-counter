package org.eugenet.lines.counter.service.display;

import java.util.List;
import org.eugenet.lines.counter.domain.FileInfo;

public interface FileInfoDisplayService {

    void display(List<FileInfo> fileInfoList);
}
