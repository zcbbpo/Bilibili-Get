package org.bilibili.get.util;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.bilibili.get.dto.BulletCommentDTO;

import java.lang.reflect.Type;

/**
 * @author Jim
 */
public class InfoValueDeserializer implements ObjectDeserializer {

    public InfoValueDeserializer() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        String str = parser.parseObject(String.class);
        String[] arrayStr = StringUtils.split(str, ",");
        Integer time = Double.valueOf(arrayStr[0]).intValue();
        String userId = String.valueOf(Long.parseLong(arrayStr[6], 16));
        BulletCommentDTO.CommentInfoDTO commentInfoDTO = new BulletCommentDTO.CommentInfoDTO();
        commentInfoDTO.setTime(time);
        commentInfoDTO.setUserId(userId);
        return (T)commentInfoDTO;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

}
