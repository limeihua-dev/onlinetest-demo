package com.dujiaoshou.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationVO<T> {

    //分页中的数据，问题
    private List<T> data;
    //显示上一页
    private boolean showPrevious;
    //显示第一页
    private boolean showFirstPage;
    //显示下一页
    private boolean showNext;
    //显示最后一页
    private boolean showEndPage;
    //当前页码
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    //总页数
    private Integer totalPage;

    //totalPage--总页数，page-当前页数
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        //往pages添加每个页面的页码
        //首先添加当前页
        pages.add(page);
        //然后添加当前页的前三页和后三页
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }

}
