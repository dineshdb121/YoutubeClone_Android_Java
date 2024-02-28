package devkng.com.blogbite.model;

/* loaded from: classes3.dex */
public class Public {
    private int cp_ContentRating;
    private String cp_contentCreatorUid;
    private String cp_contentInId;
    private String cp_contentOutId;
    private String cp_contentPublicDocId;

    public Public(String cp_contentPublicDocId, String cp_contentCreatorUid, String cp_contentOutId, String cp_contentInId, int cp_ContentRating) {
        this.cp_contentPublicDocId = cp_contentPublicDocId;
        this.cp_contentCreatorUid = cp_contentCreatorUid;
        this.cp_contentOutId = cp_contentOutId;
        this.cp_contentInId = cp_contentInId;
        this.cp_ContentRating = cp_ContentRating;
    }

    public Public() {
    }

    public String getCp_contentPublicDocId() {
        return this.cp_contentPublicDocId;
    }

    public void setCp_contentPublicDocId(String cp_contentPublicDocId) {
        this.cp_contentPublicDocId = cp_contentPublicDocId;
    }

    public String getCp_contentCreatorUid() {
        return this.cp_contentCreatorUid;
    }

    public void setCp_contentCreatorUid(String cp_contentCreatorUid) {
        this.cp_contentCreatorUid = cp_contentCreatorUid;
    }

    public String getCp_contentOutId(String docId) {
        return this.cp_contentOutId;
    }

    public void setCp_contentOutId(String cp_contentOutId) {
        this.cp_contentOutId = cp_contentOutId;
    }

    public String getCp_contentInId() {
        return this.cp_contentInId;
    }

    public void setCp_contentInId(String cp_contentInId) {
        this.cp_contentInId = cp_contentInId;
    }

    public int getCp_ContentRating() {
        return this.cp_ContentRating;
    }

    public void setCp_ContentRating(int cp_ContentRating) {
        this.cp_ContentRating = cp_ContentRating;
    }
}