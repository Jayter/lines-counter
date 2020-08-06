package org.eugenet.lines.counter.service.count;

import org.eugenet.lines.counter.domain.FileInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class JavaFileLinesCountingServiceTest {

    private final JavaFileLinesCountingService countingService = new JavaFileLinesCountingService();

    @Test
    public void shouldCalculateCodeLinesWithoutComments() {
        // Given
        File file = loadFile("/test/scenario1/TestClass1.java");

        // When
        List<FileInfo> actualFileInfos = countingService.countLines(file);

        // Then
        FileInfo expectedFileInfo = new FileInfo("TestClass1.java", 0, 5);

        Assert.assertEquals(1, actualFileInfos.size());
        Assert.assertEquals(expectedFileInfo, actualFileInfos.get(0));
    }

    @Test
    public void shouldCalculateCodeLinesWithoutNestedComments() {
        // Given
        File file = loadFile("/test/scenario2/TestClass2.java");

        // When
        List<FileInfo> actualFileInfos = countingService.countLines(file);

        // Then
        FileInfo expectedFileInfo = new FileInfo("TestClass2.java", 0, 7);

        Assert.assertEquals(1, actualFileInfos.size());
        Assert.assertEquals(expectedFileInfo, actualFileInfos.get(0));
    }

    @Test
    public void shouldCalculateCodeLinesInAFolderIgnoringNonJavaFiles() {
        // Given
        File file = loadFile("/test/scenario3");

        // When
        List<FileInfo> actualFileInfos = countingService.countLines(file);

        // Then
        List<FileInfo> expectedFileInfos = new LinkedList<>();
        expectedFileInfos.add(new FileInfo("scenario3", 0, 12));
        expectedFileInfos.add(new FileInfo("folder1", 1, 12));
        expectedFileInfos.add(new FileInfo("folder2", 2, 7));
        expectedFileInfos.add(new FileInfo("TestClass4.java", 3, 7));
        expectedFileInfos.add(new FileInfo("TestClass3.java", 2, 5));

        Assert.assertEquals(5, actualFileInfos.size());
        Assert.assertEquals(expectedFileInfos, actualFileInfos);
    }

    private File loadFile(String fileName) {
        return new File(JavaFileLinesCountingServiceTest.class.getResource(fileName).getFile());
    }

}
