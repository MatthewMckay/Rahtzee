package com.matthewmckay.rahtzee;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by matthewmckay on 11/1/15.
 */
public class MsgArrayAdapter extends ArrayAdapter<Message>{
    private final Context context;
    private Message lastClickedMsg;
    private Integer lastPosition;


    public MsgArrayAdapter(Context context, List<Message> contacts){
        super(context, R.layout.chat_row_recieved, contacts);
        this.context = context;
        lastClickedMsg = null;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        final Message msg = getItem(position);

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.chat_row_recieved, parent, false);


            //initialize viewHolder
            viewHolder = new ViewHolder();
            viewHolder.intials = (TextView)convertView.findViewById(R.id.initials);
            viewHolder.content = (TextView)convertView.findViewById(R.id.msg_content);
            viewHolder.newContent = (EditText)convertView.findViewById(R.id.newContent);
            viewHolder.status = (TextView)convertView.findViewById(R.id.status);
            viewHolder.date = (TextView)convertView.findViewById(R.id.date);
            viewHolder.time = (TextView)convertView.findViewById(R.id.time);
            viewHolder.statusPanel = (LinearLayout)convertView.findViewById(R.id.statusPanel);
            viewHolder.buttonPanel = (LinearLayout)convertView.findViewById(R.id.msgButtonPanel);



            convertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        msg.setExpanded(-1);
                    }
                }
            });

            convertView.setTag(viewHolder);
        }
        else {
            //recycle view
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //update the item view

        int color;
        if (position % 2 == 0) color = ContextCompat.getColor(context, R.color.darkerGray);
        else color = ContextCompat.getColor(context, R.color.darkestGray);
        viewHolder.intials.setBackgroundColor(color);
        viewHolder.content.setBackgroundColor(color);
        viewHolder.newContent.setBackgroundColor(color);
        viewHolder.buttonPanel.setBackgroundColor(color);
        if (msg.isExpanded() == 1) viewHolder.buttonPanel.setVisibility(View.VISIBLE);
        else viewHolder.buttonPanel.setVisibility(View.GONE);

        //if its a new message
        if (msg.getSenderId() == -1){
            viewHolder.intials.setVisibility(View.INVISIBLE);
            viewHolder.content.setVisibility(View.GONE);
            viewHolder.newContent.setVisibility(View.VISIBLE);
            viewHolder.statusPanel.setVisibility(View.INVISIBLE);
        } else {
            if (msg.getSenderId() == CurrentUser.getId()) {
                viewHolder.intials.setVisibility(View.INVISIBLE);
                viewHolder.content.setVisibility(View.VISIBLE);
                viewHolder.newContent.setVisibility(View.GONE);
                viewHolder.statusPanel.setVisibility(View.VISIBLE);
                viewHolder.statusPanel.setBackgroundColor(color);
            } else {
                viewHolder.intials.setVisibility(View.VISIBLE);
                viewHolder.content.setVisibility(View.VISIBLE);
                viewHolder.newContent.setVisibility(View.GONE);
                viewHolder.statusPanel.setVisibility(View.VISIBLE);
                viewHolder.statusPanel.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                viewHolder.intials.setText(msg.getSenderInitials());
            }

            viewHolder.content.setText(msg.getContent());
            viewHolder.status.setText(msg.getStatus());
            viewHolder.date.setText(msg.getDate());
            viewHolder.time.setText(msg.getTime());

            if (msg.getMsgType().equals("ANNOUNCEMENT")){
                viewHolder.content.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (lastClickedMsg != null){
                            lastClickedMsg.setExpanded(-1);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
            }
            else {
                viewHolder.content.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (lastClickedMsg != null) {
                            if (lastClickedMsg == msg){
                                msg.setExpanded(msg.isExpanded()*-1);
                            }
                            else {
                                lastClickedMsg.setExpanded(-1);
                                msg.setExpanded(1);
                            }
                        }
                        else {
                            msg.setExpanded(msg.isExpanded()*-1);
                            if (lastClickedMsg != null) lastClickedMsg.setExpanded(-1);
                        }
                        lastClickedMsg = msg;
                        notifyDataSetChanged();
                        return false;
                    }
                });

                viewHolder.content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lastClickedMsg != null) {
                            if (lastClickedMsg == msg){
                                msg.setExpanded(msg.isExpanded()*-1);
                            }
                            else {
                                lastClickedMsg.setExpanded(-1);
                                msg.setExpanded(1);
                            }
                        }
                        else {
                            msg.setExpanded(msg.isExpanded()*-1);
                            if (lastClickedMsg != null) lastClickedMsg.setExpanded(-1);
                        }
                        lastClickedMsg = msg;
                        notifyDataSetChanged();
                    }
                });

                convertView.findViewById(R.id.reply).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        msg.setExpanded(-1);
                        notifyDataSetChanged();
                    }
                });

                convertView.findViewById(R.id.collapse).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        msg.setExpanded(-1);
                        notifyDataSetChanged();
                    }
                });
            }
        }
        return convertView;
    }
    private static class ViewHolder {
        TextView intials;
        TextView content;
        EditText newContent;
        TextView status;
        TextView date;
        TextView time;
        LinearLayout statusPanel;
        LinearLayout buttonPanel;
    }
}
