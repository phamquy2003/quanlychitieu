package tlu.cse.android.ht63.quanlychitieu.Model;

public class Message {
    // Các hằng số để phân biệt tin nhắn gửi bởi người dùng và bot
    public static final String SENT_BY_ME = "me";
    public static final String SENT_BY_BOT = "bot";

    private String message;
    private String sentBy;

    // Constructor
    public Message(String message, String sentBy) {
        this.message = message;
        this.sentBy = sentBy;
    }

    // Getter cho tin nhắn
    public String getMessage() {
        return message;
    }

    // Setter cho tin nhắn
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter cho người gửi tin nhắn (me hoặc bot)
    public String getSentBy() {
        return sentBy;
    }

    // Setter cho người gửi tin nhắn
    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }
}
