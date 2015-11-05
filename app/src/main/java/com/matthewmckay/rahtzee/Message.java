package com.matthewmckay.rahtzee;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeSet;

/**
 * Created by matthewmckay on 11/1/15.
 */
public class Message implements Parcelable{
    private String msgType;
    private int msgId;
    private int parentMsgId;
    private int muted;
    private int expanded;
    private int muteRootId;
    private int senderId;
    private String senderInitials;
    private String status;
    private String date;
    private String time;
    private String content;
    private ArrayList recipients;

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(msgType);
        out.writeInt(msgId);
        out.writeInt(parentMsgId);
        out.writeInt(muted);
        out.writeInt(expanded);
        out.writeInt(muteRootId);
        out.writeInt(senderId);
        out.writeString(senderInitials);
        out.writeString(status);
        out.writeString(date);
        out.writeString(time);
        out.writeString(content);
        out.writeList(recipients);
    }
    @Override
    public int describeContents () {
        return 0;
    }

    public static Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    Message(Parcel parcel){
        this.msgType = parcel.readString();
        this.msgId = parcel.readInt();
        this.parentMsgId = parcel.readInt();
        this.muted = parcel.readInt();
        this.expanded = parcel.readInt();
        this.muteRootId = parcel.readInt();
        this.senderId = parcel.readInt();
        this.senderInitials = parcel.readString();
        this.status = parcel.readString();
        this.date = parcel.readString();
        this.time = parcel.readString();
        this.content = parcel.readString();
        this.recipients = parcel.readArrayList(null);
    }


    Message(String msgType, int msgId, int parentMsgId, int senderId,
            String senderInitials, String status, String date,
            String time, String content, ArrayList<Integer> recipients){
        this.msgType = msgType;
        this.msgId = msgId;
        this.parentMsgId = parentMsgId;
        this.senderId = senderId;
        this.senderInitials = senderInitials;
        this.status = status;
        this.date = date;
        this.time = time;
        this.content = content;
        this.recipients = recipients;
        muteRootId = -1;
        muted = -1;
        expanded = -1;
    }

    Message(){
        senderId = -1;
        muteRootId = -1;
        muted = -1;
        expanded = -1;
    }

    public void setMsgFields(String msgType, int senderId, String senderInitials, String status, String date,
                             String time, String content, ArrayList recipients){
        this.msgType = msgType;
        this.senderId = senderId;
        this.senderInitials = senderInitials;
        this.status = status;
        this.date = date;
        this.time = time;
        this.content = content;
        this.recipients = recipients;
    }

    public void setMute(int muteRootId, int muted){
        this.muteRootId = muteRootId;
        this.muted = muted;
    }

    public void updateRecipients(ArrayList additionalRecipients){
        TreeSet tmp = new TreeSet();
        this.recipients.addAll(additionalRecipients);
        tmp.addAll(this.recipients);
        this.recipients.clear();
        this.recipients.addAll(tmp);
    }

    public ArrayList getRecipients(){return recipients;}

    public String getMsgType() {
        return msgType;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderInitials() {
        return senderInitials;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int isMuted() {return muted;}

    public int getMuteRootId() {return muteRootId;}

    public int getMsgId() {return msgId;}

    public int getParentMsgId() {return parentMsgId;}

    public void setExpanded(int expanded) {this.expanded = expanded;}

    public int isExpanded() {return expanded;}
}
