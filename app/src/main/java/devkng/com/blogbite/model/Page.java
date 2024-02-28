package devkng.com.blogbite.model;

import com.google.firebase.Timestamp;

/* loaded from: classes3.dex */
public class Page {
    public static final int LAYOUT_FOUR = 4;
    public static final int LAYOUT_ONE = 1;
    public static final int LAYOUT_THREE = 3;
    public static final int LAYOUT_TWO = 2;
    private int ItemCount_Order_By;
    public String content_item_id;
    private int image;
    private String imageUrl;
    private Boolean isSelected = false;
    public Timestamp timestamp;
    private String title;
    private String title_TextStyle;
    private String title_textColor;
    private String title_textSize;
    public int viewType;

    public Page(int viewType, String message) {
        this.viewType = viewType;
        this.title = message;
    }

    public Page(int viewType, byte image) {
        this.viewType = viewType;
        this.image = image;
    }

    public Page(String title, String title_TextStyle, String title_textColor, String title_textSize) {
        this.title = title;
        this.title_TextStyle = title_TextStyle;
        this.title_textColor = title_textColor;
        this.title_textSize = title_textSize;
    }

    public Page() {
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getItemCount_Order_By() {
        return this.ItemCount_Order_By;
    }

    public void setItemCount_Order_By(int itemCount_Order_By) {
        this.ItemCount_Order_By = itemCount_Order_By;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_TextStyle() {
        return this.title_TextStyle;
    }

    public void setTitle_TextStyle(String title_TextStyle) {
        this.title_TextStyle = title_TextStyle;
    }

    public String getTitle_textColor() {
        return this.title_textColor;
    }

    public void setTitle_textColor(String title_textColor) {
        this.title_textColor = title_textColor;
    }

    public String getTitle_textSize() {
        return this.title_textSize;
    }

    public void setTitle_textSize(String title_textSize) {
        this.title_textSize = title_textSize;
    }

    public String getContent_item_id() {
        return this.content_item_id;
    }

    public void setContent_item_id(String content_item_id) {
        this.content_item_id = content_item_id;
    }

    public Boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(Boolean selected) {
        this.isSelected = selected;
    }
}