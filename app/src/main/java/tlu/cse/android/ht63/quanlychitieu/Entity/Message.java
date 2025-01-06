package tlu.cse.android.ht63.quanlychitieu.Entity;

public class Message {
    String username;
    String avatar;
    String datetime;
    String message;

    public Message(String username, String avatar, String datetime, String message) {
        this.username = username;
        this.avatar = avatar;
        this.datetime = datetime;
        this.message = message;
    }

    public Message()
    {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
