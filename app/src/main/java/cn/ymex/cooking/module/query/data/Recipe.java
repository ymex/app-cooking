package cn.ymex.cooking.module.query.data;

/**
 * Created by ymex on 2017/8/24.
 */

public class Recipe {

    /**
     * img : http://f2.mob.com/null/2015/08/19/1439946002812.jpg
     * ingredients : ["牛腱肉一斤、鲜青红辣椒各两个、老块一块、花椒一大匙、香葱四棵、白糖一大匙、香油一大匙、料酒三大匙、盐适量。"]
     * method : [{"img":"http://f2.mob.com/null/2015/08/19/1439946002990.jpg","step":"1.老姜切片；葱切段。"},{"img":"http://f2.mob.com/null/2015/08/19/1439946003297.jpg","step":"2.去掉牛腱肉表面的筋膜，洗净后加入盐、糖、花椒、一半葱段、一半姜片、一大匙料酒，抹匀后盖上保鲜膜，放冰箱冷藏室码味四小时以上。"},{"img":"http://f2.mob.com/null/2015/08/19/1439946003573.jpg","step":"3.鲜青红椒剁末，放小碗里，加入盐、味精、半汤勺纯净水或凉开水，调成盐水味料。"},{"img":"http://f2.mob.com/null/2015/08/19/1439946003723.jpg","step":"4.将牛肉对剖成两半放入沸水锅中，再放盐、剩下的姜片、葱段、料酒。加盖用大火烧沸后改小火煮约一小时至软。"},{"img":"http://f2.mob.com/null/2015/08/19/1439946004044.jpg","step":"5.将牛肉捞出晾凉后切成薄片。整齐地摆放在盘子中。"},{"step":"6.浇上盐水味料即可食用。"}]
     * sumary : 盐水牛腱肉属于清爽凉菜，主要原料是牛腱，口味是香，工艺是煮，难度属于中级。
     * title : 怎样做盐水牛腱肉凉菜
     */

    private String img;
    private String ingredients;
    private String method;
    private String sumary;
    private String title;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
