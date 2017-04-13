package vadimCo;

import java.util.StringTokenizer;

/**
 * Created by Вадим on 12.04.2017.
 */
public class SportEquipment implements InitializedFromString, Comparable<SportEquipment> {
    private Category category;
    private String title;
    private int price;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return category +
                ". " + title +
                ". Price per day: " + price;
    }

    @Override
    public void setParametersFromString(String str){
        StringTokenizer strTok = new StringTokenizer(str,";");
        setCategory(Category.valueOf(strTok.nextToken()));
        setTitle(strTok.nextToken());
        setPrice(Integer.parseInt(strTok.nextToken()));
    }

    @Override
    public int compareTo(SportEquipment o) {
        if (title.compareTo(o.getTitle())==0)
            if (price == o.getPrice())
                return category.compareTo(o.getCategory());
            else return ((Integer)price).compareTo(o.getPrice());
        else return title.compareTo(o.getTitle());
    }
}
