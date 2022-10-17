package Model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class TutorialsModel {

    private long id;
    private String theme;
    private String text;

    public TutorialsModel() {

    }

    public TutorialsModel(long id, String theme, String text) {
        this.id = id;
        this.theme = theme;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
