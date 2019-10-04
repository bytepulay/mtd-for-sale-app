package asia.nainglintun.myinthidar.ToDo;


import java.io.Serializable;

public class CardTodo implements Serializable {
    private String custom_card_list;
    private int imageId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public CardTodo(String custom_card_list, int imageId) {

        this.custom_card_list = custom_card_list;
        this.imageId = imageId;
    }

    public String getCustom_card_list() {
        return custom_card_list;
    }

    public void setCustom_card_list(String custom_card_list) {
        this.custom_card_list = custom_card_list;
    }
}
