package com.clouds.domain;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/10 18:00
 * @Version V1.0
 */
public class FileInfo {
    private String filename;
    private String filePath;
    private long fileSize;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileInfo(String filename, String filePath, long fileSize) {
        this.filename = filename;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public FileInfo() {
    }
}
