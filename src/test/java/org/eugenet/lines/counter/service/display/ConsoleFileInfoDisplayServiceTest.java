package org.eugenet.lines.counter.service.display;

import org.eugenet.lines.counter.domain.FileInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class ConsoleFileInfoDisplayServiceTest {

    private ConsoleFileInfoDisplayService displayService = new ConsoleFileInfoDisplayService();

    @Test
    public void shouldDisplayListOfInfosWithCorrectFormatting() throws IOException {
        // Given

        List<FileInfo> fileInfos = new LinkedList<>();
        fileInfos.add(new FileInfo("scenario3", 0, 12));
        fileInfos.add(new FileInfo("folder1", 1, 12));
        fileInfos.add(new FileInfo("folder2", 2, 7));
        fileInfos.add(new FileInfo("TestClass4.java", 3, 7));
        fileInfos.add(new FileInfo("TestClass3.java", 2, 5));

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // When
        displayService.display(fileInfos);

        // Then
        bo.flush();
        String[] allWrittenLines = new String(bo.toByteArray()).split(System.getProperty("line.separator"));

        String[] expectedLines = new String[]{
                "scenario3 : 12",
                "  folder1 : 12",
                "    folder2 : 7",
                "      TestClass4.java : 7",
                "    TestClass3.java : 5"
        };

        Assert.assertArrayEquals(expectedLines, allWrittenLines);
    }
}