package com.xzg.mall.common.serializer.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.xzg.mall.common.bean.Qiniu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImgJsonSerializer extends JsonSerializer<String> {

    @Autowired
    private Qiniu qiniu;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (StrUtil.isBlank(value)) {
            gen.writeString(StrUtil.EMPTY);
            return;
        }
        String[] imgs = value.split(StrUtil.COMMA);
        StringBuilder sb = new StringBuilder();
        for (String img : imgs) {
            sb.append(qiniu.getResourcesUrl()).append(img).append(StrUtil.COMMA);
        }
        sb.deleteCharAt(sb.length()-1);
        gen.writeString(sb.toString());
    }
}
