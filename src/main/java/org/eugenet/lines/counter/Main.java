package org.eugenet.lines.counter;

import java.io.File;
import java.util.List;
import org.eugenet.lines.counter.domain.FileInfo;
import org.eugenet.lines.counter.service.count.FileLinesCountingService;
import org.eugenet.lines.counter.service.count.JavaFileLinesCountingService;
import org.eugenet.lines.counter.service.display.ConsoleFileInfoDisplayService;
import org.eugenet.lines.counter.service.display.FileInfoDisplayService;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || args[0].isEmpty()) {
            throw new IllegalArgumentException("Input file is not specified");
        }

        File file = new File(args[0]);

        FileLinesCountingService countingService = new JavaFileLinesCountingService();
        FileInfoDisplayService displayService = new ConsoleFileInfoDisplayService();

        try {
            List<FileInfo> list = countingService.countLines(file);
            displayService.display(list);
        } catch (Exception e) {
            System.out.println("Unexpected exception happened during processing of file.");
        }

    }
}
