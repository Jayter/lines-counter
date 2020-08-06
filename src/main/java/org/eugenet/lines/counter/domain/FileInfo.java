package org.eugenet.lines.counter.domain;

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
}
