package devkng.com.blogbite.model;

/* loaded from: classes3.dex */
public class Account {
    private String Country;
    private String account_created_device;
    private String account_created_sdk;
    private String account_created_using;
    private String account_uid;
    private String date;
    private String dmail;
    private String gender;
    private String month;
    private String name;
    private String photo;
    private String year;

    public Account(String dmail, String name, String date, String month, String year, String gender, String photo, String country, String account_created_using, String account_created_device, String account_created_sdk, String account_uid) {
        this.dmail = dmail;
        this.name = name;
        this.date = date;
        this.month = month;
        this.year = year;
        this.gender = gender;
        this.photo = photo;
        this.Country = country;
        this.account_created_using = account_created_using;
        this.account_created_device = account_created_device;
        this.account_created_sdk = account_created_sdk;
        this.account_uid = account_uid;
    }

    public Account() {
    }

    public String getDmail() {
        return this.dmail;
    }

    public void setDmail(String dmail) {
        this.dmail = dmail;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCountry() {
        return this.Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public String getAccount_created_using() {
        return this.account_created_using;
    }

    public void setAccount_created_using(String account_created_using) {
        this.account_created_using = account_created_using;
    }

    public String getAccount_created_device() {
        return this.account_created_device;
    }

    public void setAccount_created_device(String account_created_device) {
        this.account_created_device = account_created_device;
    }

    public String getAccount_created_sdk() {
        return this.account_created_sdk;
    }

    public void setAccount_created_sdk(String account_created_sdk) {
        this.account_created_sdk = account_created_sdk;
    }

    public String getAccount_uid() {
        return this.account_uid;
    }

    public void setAccount_uid(String account_uid) {
        this.account_uid = account_uid;
    }
}