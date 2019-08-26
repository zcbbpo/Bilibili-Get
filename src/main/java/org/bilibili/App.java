package org.bilibili;

import org.bilibili.get.BiliBiliHelper;

import java.nio.file.Paths;

/**
 * @author Jim
 */
public class App {
    public static void main(String[] args) throws Exception {

        BiliBiliHelper biliBiliHelper = new BiliBiliHelper(Paths.get("/Users/jimcao/test/video/"));
        //{"name": "C jun", "id": 4162287}, {"name": "hanying", "id": 1315101}
        //258844831 国家宝藏
        biliBiliHelper.downloadForUid("258844831");
    }
}
