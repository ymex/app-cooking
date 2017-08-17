package cn.ymex.cooking.module.sort.data;

import java.util.List;

/**
 * Created by ymex on 2017/8/16.
 */

public class Category  {

    private CategoryInfoItem categoryInfo;

    private List<CategoryInfo> childs;

    public CategoryInfoItem getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfoItem categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public List<CategoryInfo> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryInfo> childs) {
        this.childs = childs;
    }
}
