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

    /**
     参数1（157.47900）：弹幕出现的时间，以秒数为单位
     参数2（1）：弹幕的模式，1-3 滚动弹幕，4 底端弹幕，5顶端弹幕，6 逆向弹幕，7 精准定位，8 高级弹幕
     参数3（25）：字号 （12非常小，16特小，18小，25中，36大，45很大，64特别大）
     参数4（16777215）：字体的颜色；这串数字是十进制表示；通常软件中使用的是十六进制颜色码；
                e.g:
                白色   
                RGB值：(255,255,255)     
                十进制值：16777215      
                十六进制值：#FFFFFF
     参数5（1548340494）：unix时间戳，从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数
     参数6（0）：弹幕池，0普通池，1字幕池，2特殊池 【目前特殊池为高级弹幕专用】
     参数7（389b20da）：发送者的ID，用于“屏蔽此弹幕的发送者”功能
     参数8（11114024647262210）：弹幕在弹幕数据库中rowID 用于“历史弹幕”功能。
     */
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
