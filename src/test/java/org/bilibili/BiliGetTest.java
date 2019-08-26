package org.bilibili;

import org.bilibili.get.BiliBiliHelper;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Jim
 */
public class BiliGetTest {
    private BiliBiliHelper biliBiliHelper;

    @Before
    public void init() throws Exception {
        Path path = Paths.get("/Users/jimcao/test/video/");
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
        biliBiliHelper = new BiliBiliHelper(Paths.get("/Users/jimcao/test/video/"));

    }

    @Test
    public void testDownloadByUserId() throws Exception{
        biliBiliHelper.downloadForUid("123996559");
    }

    @Test
    public void testDownloadByAid() throws Exception {
        biliBiliHelper.downloadForAid("av19645863");
    }
}
