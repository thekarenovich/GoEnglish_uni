package Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile")
public class ProfileDB {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "profile_id")
    private long id;
    @ColumnInfo(name = "username_id")
    private String username;
    @ColumnInfo(name = "email_id")
    private String email;
    @ColumnInfo(name = "password_id")
    private String password;
    @ColumnInfo(name = "image_id")
    private String image;

    @Ignore
    public ProfileDB() {
    }

    public ProfileDB(long id, String username, String email, String password, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}