package org.eugenet.lines.counter.domain;

import java.util.Objects;

public class FileInfo {

    public FileInfo(String name, int depth, int count) {
        this.name = name;
        this.depth = depth;
        this.count = count;
    }

    public FileInfo(String name, int depth) {
        this.name = name;
        this.depth = depth;
    }

    private String name;
    private int depth;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return depth == fileInfo.depth &&
                count == fileInfo.count &&
                Objects.equals(name, fileInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depth, count);
    }
}
