package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Column
    private String message;
    @Column
    @Id
    private String sender;
    @Column
    private String recipient; //login

    private byte type; //1 - private, 0 - public

    public void setMessage(String message) {
        this.message = message;
    }
    public void setSender(String sender) {
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
    public String getSender() {
        return sender;
    }
    public String getRecipient() {
        return recipient;
    }
    public short getType() {
        return type;
    }

    public Message(String message, String sender, String recipient, byte type) {
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
    }
    public Message(){}
}
