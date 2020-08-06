package org.eugenet.lines.counter.service.display;

import java.util.List;
import org.eugenet.lines.counter.domain.FileInfo;

public class ConsoleFileInfoDisplayService implements FileInfoDisplayService {

    @Override
    public void display(List<FileInfo> fileInfoList) {
        fileInfoList.stream()
                    .map(this::formatLine)
                    .forEach(System.out::println);
    }

    private String formatLine(FileInfo fileInfo) {
        StringBuilder line = getPrefix(fileInfo.getDepth());

        line.append(fileInfo.getName());
        line.append(" : ");
        line.append(fileInfo.getCount());

        return line.toString();
    }

    private StringBuilder getPrefix(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append("\t");
        }
        return result;
    }
}
