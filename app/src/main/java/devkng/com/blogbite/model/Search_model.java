package devkng.com.blogbite.model;

/* loaded from: classes3.dex */
public class Search_model {
    public static final int SEARCHES = 2;
    public static final int USER = 1;
    private String from;
    private int no_of_time;
    private int no_of_user;
    private String searched_text;

    public Search_model(String searched_text, int no_of_time, int no_of_user, String from) {
        this.searched_text = searched_text;
        this.no_of_time = no_of_time;
        this.no_of_user = no_of_user;
        this.from = from;
    }

    public Search_model() {
    }

    public String getSearched_text() {
        return this.searched_text;
    }

    public void setSearched_text(String searched_text) {
        this.searched_text = searched_text;
    }

    public int getNo_of_time() {
        return this.no_of_time;
    }

    public void setNo_of_time(int no_of_time) {
        this.no_of_time = no_of_time;
    }

    public int getNo_of_user() {
        return this.no_of_user;
    }

    public void setNo_of_user(int no_of_user) {
        this.no_of_user = no_of_user;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}