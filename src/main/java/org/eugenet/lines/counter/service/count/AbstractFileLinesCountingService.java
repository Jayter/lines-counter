package org.eugenet.lines.counter.service.count;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.eugenet.lines.counter.domain.FileInfo;

public abstract class AbstractFileLinesCountingService implements FileLinesCountingService {

    @Override
    public List<FileInfo> countLines(File file) {
        List<FileInfo> list = new LinkedList<>();
        if (file.isDirectory()) {
            countLinesInDirectory(file, list, 0);
        } else {
            countLinesInFile(file, list, 0);
        }
        return list;
    }

    protected int countLinesInDirectory(File folder, List<FileInfo> list, int depth) {
        FileInfo info = new FileInfo(folder.getName(), depth);
        list.add(info);

        int totalCount = 0;
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                totalCount += countLinesInDirectory(file, list, depth + 1);
            } else {
                totalCount += countLinesInFile(file, list, depth + 1);
            }
        }
        info.setCount(totalCount);

        return totalCount;
    }

    protected abstract int countLinesInFile(File folder, List<FileInfo> list, int depth);

}
