package cn.ymex.cooking.module.query.data;

import java.util.List;

/**
 * Created by ymex on 2017/8/24.
 */

public class QueryContent {

    /**
     * curPage : 1
     * list : []
     * total : 17758
     */

    private int curPage;
    private int total;
    private List<RecipeIndex> list;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecipeIndex> getList() {
        return list;
    }

    public void setList(List<RecipeIndex> list) {
        this.list = list;
    }
}
