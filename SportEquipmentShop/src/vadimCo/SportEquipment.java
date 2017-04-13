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
    public void setParametersFromString(String str) {
        StringTokenizer strTok = new StringTokenizer(str, ";");
        setCategory(Category.valueOf(strTok.nextToken()));
        setTitle(strTok.nextToken());
        setPrice(Integer.parseInt(strTok.nextToken()));
    }

    @Override
    public String getStringOfParameters() {
        return category.toString() + ';' + title + ';' + price;
    }

    @Override
    public int compareTo(SportEquipment o) {
        if (category.toString().compareTo(o.getCategory().toString()) == 0)
            if (title.compareTo(o.getTitle()) == 0)
                return ((Integer) price).compareTo(o.getPrice());
            else return title.compareTo(o.getTitle());
        else return category.toString().compareTo(o.getCategory().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportEquipment equipment = (SportEquipment) o;

        if (price != equipment.price) return false;
        if (category != equipment.category) return false;
        return !(title != null ? !title.equals(equipment.title) : equipment.title != null);

    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
