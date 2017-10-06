package model;

public class Message {
    private String message;
    private User sender;
    private String recipient; //login

    private byte type; //1 - private, 0 - public

    public void setMessage(String message) {
        this.message = message;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public void setType(byte type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }
    public User getSender() {
        return sender;
    }
    public String getRecipient() {
        return recipient;
    }
    public short getType() {
        return type;
    }

}
