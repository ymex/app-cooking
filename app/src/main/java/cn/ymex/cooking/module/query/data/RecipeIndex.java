package cn.ymex.cooking.module.query.data;

import java.util.List;

/**
 * Created by ymex on 2017/8/24.
 */

public class RecipeIndex {

    /**
     * ctgIds : ["0010001007","0010001025"]
     * ctgTitles : 荤菜,煮
     * menuId : 00100010070000017755
     * name : 盐水牛腱肉凉菜
     * recipe : {}
     * thumbnail : http://f2.mob.com/null/2015/08/19/1439945969074.jpg
     */

    private String ctgTitles;
    private String menuId;
    private String name;
    private Recipe recipe;
    private String thumbnail;
    private List<String> ctgIds;

    public String getCtgTitles() {
        return ctgTitles;
    }

    public void setCtgTitles(String ctgTitles) {
        this.ctgTitles = ctgTitles;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getCtgIds() {
        return ctgIds;
    }

    public void setCtgIds(List<String> ctgIds) {
        this.ctgIds = ctgIds;
    }

}
