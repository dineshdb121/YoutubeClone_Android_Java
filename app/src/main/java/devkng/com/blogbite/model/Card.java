package devkng.com.blogbite.model;

/* loaded from: classes3.dex */
public class Card {
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 3;
    public static final int UNLISTED = 2;
    private String CardId;
    private String ChannelImageUrl;
    private String ChannelName;
    private String ChannelUid;
    private String Description;
    private int ItemCount_Order_By;
    private String LikesCount;
    private int SavedTime;
    private String Search;
    private String TIME;
    private int Thumbnail;
    private String ThumbnailUrl;
    private String Title;
    private String UserUid;
    private String ViewsCount;
    private int Visibility;
    private int viewType;

    public Card() {
    }

    public Card(String TIME, int savedTime, int itemCount_Order_By, String title, String description, String cardId, int thumbnail, String thumbnailUrl, int viewType, int visibility, String viewsCount, String likesCount, String channelName, String channelImageUrl) {
        this.TIME = TIME;
        this.SavedTime = savedTime;
        this.ItemCount_Order_By = itemCount_Order_By;
        this.Title = title;
        this.Description = description;
        this.CardId = cardId;
        this.Thumbnail = thumbnail;
        this.ThumbnailUrl = thumbnailUrl;
        this.viewType = viewType;
        this.Visibility = visibility;
        this.ViewsCount = viewsCount;
        this.LikesCount = likesCount;
        this.ChannelName = channelName;
        this.ChannelImageUrl = channelImageUrl;
    }

    public String getTIME() {
        return this.TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public int getSavedTime() {
        return this.SavedTime;
    }

    public void setSavedTime(int savedTime) {
        this.SavedTime = savedTime;
    }

    public int getItemCount_Order_By() {
        return this.ItemCount_Order_By;
    }

    public void setItemCount_Order_By(int itemCount_Order_By) {
        this.ItemCount_Order_By = itemCount_Order_By;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getSearch() {
        return this.Search;
    }

    public void setSearch(String search) {
        this.Search = search;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getCardId() {
        return this.CardId;
    }

    public void setCardId(String cardId) {
        this.CardId = cardId;
    }

    public int getThumbnail() {
        return this.Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.Thumbnail = thumbnail;
    }

    public String getThumbnailUrl() {
        return this.ThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.ThumbnailUrl = thumbnailUrl;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getVisibility() {
        return this.Visibility;
    }

    public void setVisibility(int visibility) {
        this.Visibility = visibility;
    }

    public String getViewsCount() {
        return this.ViewsCount;
    }

    public void setViewsCount(String viewsCount) {
        this.ViewsCount = viewsCount;
    }

    public String getLikesCount() {
        return this.LikesCount;
    }

    public void setLikesCount(String likesCount) {
        this.LikesCount = likesCount;
    }

    public String getChannelName() {
        return this.ChannelName;
    }

    public void setChannelName(String channelName) {
        this.ChannelName = channelName;
    }

    public String getChannelImageUrl() {
        return this.ChannelImageUrl;
    }

    public void setChannelImageUrl(String channelImageUrl) {
        this.ChannelImageUrl = channelImageUrl;
    }

    public String getUserUid() {
        return this.UserUid;
    }

    public void setUserUid(String userUid) {
        this.UserUid = userUid;
    }

    public String getChannelUid() {
        return this.ChannelUid;
    }

    public void setChannelUid(String channelUid) {
        this.ChannelUid = channelUid;
    }

    public void setContentPublishQueueId(String docId_fb) {
    }
}