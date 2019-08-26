package org.bilibili;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author Jim
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String result = "【央视/文化\\】《国家宝藏》王希孟《千里江山图》卷街采 bilibili12月3日24点正片上线.mp4";
        System.out.println(result);
        result = StringUtils.replace(result, "\\", ":");
        System.out.println(result);
        result = StringUtils.replace(result, "/", ":");
        System.out.println(result);
    }
}
