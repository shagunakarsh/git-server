package in.shagunakarsh.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsTest {

    private FileUtils fileUtils;
    private String repo;
    private File rootDir;
    private File repoDir;
    private File file;

    @Before
    public void setUp() throws IOException {
        repo = "file-repo-test";
        rootDir = new File(System.getProperty("java.io.tmpdir"));
        repoDir = new File(rootDir, repo);
        if(!repoDir.exists()) {
            repoDir.mkdirs();
        }

        System.out.println(rootDir.getAbsolutePath());
        System.out.println(repo + " " + repoDir.getAbsolutePath());

        file = new File(repoDir.getAbsolutePath() +File.separator + "test.txt");
        file.createNewFile();

        fileUtils = new FileUtils(rootDir.getAbsolutePath());

        assertTrue(rootDir.isDirectory());
        assertTrue(repoDir.isDirectory());
        assertTrue(file.exists());
        assertTrue(file.isFile());

    }

    @Test
    public void getFileMapTest() {
        assertEquals(rootDir.getAbsolutePath(), fileUtils.getRootPath());

        Map<String, Boolean> map = fileUtils.getFileMap(repo);
        assertEquals(1,map.size());
        for(Map.Entry<String, Boolean> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        assertEquals(Boolean.FALSE, map.get(File.separator +"test.txt"));
    }

}
