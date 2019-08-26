package org.bilibili.get.constants;

/**
 * @author Jim
 */
public class ApiConsts {
    /**
     * https://space.bilibili.com/ajax/member/getSubmitVideos?mid=4162287&pagesize=30&tid=0&page=1&keyword=&order=pubdate
     */
    public static final String ALL_VIDEO_LIST_API_URL = "https://space.bilibili.com/ajax/member/getSubmitVideos";

    /**
     * https://api.bilibili.com/x/web-interface/archive/stat?aid=52914112
     */
    public static final String VIDEO_INFO_V1_API_URL = "https://api.bilibili.com/x/web-interface/archive/stat";

    /**
     * https://api.bilibili.com/view?type=jsonp&appkey=8e9fc618fbd41e28&id=52914112
     */
    public static final String VIDEO_INFO_V2_API_URL = "https://api.bilibili.com/view";

    /**
     * https://www.bilibili.com/widget/getPageList?aid=52914112
     */
    public static final String VIDEO_CID_GET_API_URL = "https://www.bilibili.com/widget/getPageList";

    /**
     * https://comment.bilibili.com/92583592.xml
     * https://comment.bilibili.com/{cid}.xml
     */
    public static final String BULLET_COMMENT_XML_API_URL = "https://comment.bilibili.com/";

    /**
     * https://api.bilibili.com/x/player/playurl?avid=53207142&cid=93089269&qn=16&type=&otype=json&fnver=0&fnval=16
     */
    public static final String VIDEO_X_PLAY_URL = "https://api.bilibili.com/x/player/playurl";

    /**
     * https://interface.bilibili.com/v2/playurl?appkey=iVGUTjsxvpLeuDCf&cid=1600871&otype=json&qn=80&quality=80&type=&sign=359f72b937d59b38ec309b473e4d456a
     */
    public static final String VIDEO_V2_PLAY_URL = "https://interface.bilibili.com/v2/playurl";

}
