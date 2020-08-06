package org.eugenet.lines.counter.service.count;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.eugenet.lines.counter.domain.FileInfo;

public class JavaFileLinesCountingService extends AbstractFileLinesCountingService {

    private static final String MULTI_LINE_COMMENT_START = "/*";
    private static final String MULTI_LINE_COMMENT_END = "*/";
    private static final String SINGLE_LINE_COMMENT_START = "//";

    private boolean isInsideMultiLineComment;

    @Override
    protected int countLinesInFile(File file, List<FileInfo> list, int depth) {
        int count = (int) count(file);

        list.add(new FileInfo(file.getName(), depth, count));

        return count;
    }

    public long count(File file) {
        try {
            return Files.lines(file.toPath())
                        .filter(line -> shouldCount(line.trim()))
                        .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean shouldCount(String line) {
        if (line.startsWith(SINGLE_LINE_COMMENT_START)) {
            return false;
        }
        String updatedLine = removeMultiLineComment(line).trim();
        return !updatedLine.isEmpty();
    }

    private String removeMultiLineComment(String line) {
        if (!isInsideMultiLineComment) {
            int startIndex = line.indexOf(MULTI_LINE_COMMENT_START);
            if (startIndex == 0) {
                isInsideMultiLineComment = true;
                return removeMultiLineComment(line.substring(startIndex + 2).trim());
            }
        } else {
            int endIndex = line.indexOf(MULTI_LINE_COMMENT_END);
            if (endIndex >= 0) {
                isInsideMultiLineComment = false;
                return removeMultiLineComment(line.substring(endIndex + 2).trim());
            }
            return "";
        }

        return line;
    }

}
