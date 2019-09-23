package com.xzg.mall.common.util;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PageAdapter{

    private int begin;

    private int end;

    public PageAdapter(Page page) {
        int[] startEnd = PageUtil.transToStartEnd((int) page.getCurrent(), (int) page.getSize());
        this.begin = startEnd[0];
        this.end = startEnd[1];
    }
}
