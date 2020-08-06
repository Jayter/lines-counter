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
    private static final String JAVA_FILE_EXTENSION = ".java";
    private static final String EMPTY_LINE = "";

    private boolean isInsideMultiLineComment;

    @Override
    protected int countLinesInFile(File file, List<FileInfo> list, int depth) {
        int count = (int) count(file);

        list.add(new FileInfo(file.getName(), depth, count));

        return count;
    }

    @Override
    protected boolean isFileSupported(File file) {
        return file.getName().endsWith(JAVA_FILE_EXTENSION);
    }

    private long count(File file) {
        try {
            return Files.lines(file.toPath())
                    .map(String::trim)
                    .map(this::removeComment)
                    .filter(string -> !string.isEmpty())
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String removeComment(String line) {
        if (isInsideMultiLineComment) {
            if (line.contains(MULTI_LINE_COMMENT_END)) {
                isInsideMultiLineComment = false;
                return removeComment(line.substring(line.indexOf(MULTI_LINE_COMMENT_END) + 2).trim());
            }
            return EMPTY_LINE;
        }

        if (line.startsWith(SINGLE_LINE_COMMENT_START)) {
            return EMPTY_LINE;
        }

        if (line.startsWith(MULTI_LINE_COMMENT_START)) {
            isInsideMultiLineComment = true;
            return removeComment(line.substring(line.indexOf(MULTI_LINE_COMMENT_START) + 2).trim());
        }

        return line;
    }

}
