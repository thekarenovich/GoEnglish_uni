package Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "tutorials")
public class Tutorials {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tutorial_id")
    private long id;

    @ColumnInfo(name = "theme_id")
    private String theme;

    @ColumnInfo(name = "text_id")
    private String text;

    @Ignore
    public Tutorials() {

    }

    public Tutorials(long id, String theme, String text) {
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

