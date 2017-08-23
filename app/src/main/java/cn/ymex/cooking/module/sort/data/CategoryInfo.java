package cn.ymex.cooking.module.sort.data;

import java.util.List;

/**
 * Created by ymex on 2017/8/16.
 */

public class CategoryInfo {

    private CategoryInfoItem categoryInfo;

    private List<CategoryInfoItemWarp> childs;

    public CategoryInfoItem getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfoItem categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public List<CategoryInfoItemWarp> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryInfoItemWarp> childs) {
        this.childs = childs;
    }
}
