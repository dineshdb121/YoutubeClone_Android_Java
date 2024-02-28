package devkng.com.blogbite.chip.cdf_home_chip;

/* loaded from: classes16.dex */
public class Chip_model {
    private String action;
    private String name;

    public Chip_model(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public Chip_model() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}