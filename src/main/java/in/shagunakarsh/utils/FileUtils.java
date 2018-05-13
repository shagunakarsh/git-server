package in.shagunakarsh.utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

    private String rootPath;

    public FileUtils(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<File> getFileList(String path) {
        File file = new File(path);
        File []files = file.listFiles();
        return Arrays.asList(files);
    }

    public Map<String, Boolean> getFileMap(String repo) {
        String path = rootPath + File.separator + repo;
        return getFileMap(getFileList(path), repo);
    }

    public Map<String, Boolean> getFileMap(String repo, String file) {
        String path = rootPath + repo + File.separator + file;
        return getFileMap(getFileList(path), repo);
    }

    public Map<String, Boolean> getFileMap(List<File> files, String repo) {
        Map<String, Boolean> fileMap = new HashMap<>();
        for(File file: files) {
            String relPath = getRelPath(file.getAbsolutePath(), repo);
            if(file.isDirectory()) {
                fileMap.put(relPath, true);
            } else {
                fileMap.put(relPath, false);
            }
        }
        return fileMap;
    }

    public String getRelPath(String absPath, String repo) {
        String repoPath = rootPath + repo;
        return absPath.substring(repoPath.length()+1);
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
