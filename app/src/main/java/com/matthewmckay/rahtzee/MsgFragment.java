package com.matthewmckay.rahtzee;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.matthewmckay.rahtzee.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class MsgFragment extends ListFragment {
    private List<Message> messages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            ArrayList msgs = savedInstanceState.getParcelableArrayList("myKey");
            if (msgs != null){
                setListAdapter(new MsgArrayAdapter(getActivity(), msgs));
            }
        }
        else {
            CurrentUser.setId(1);
            messages = new ArrayList<>();

            TreeSet<Integer> recipientSort = new TreeSet<>();
            recipientSort.add(1);
            recipientSort.add(2);
            recipientSort.add(3);

            ArrayList recipients = new ArrayList();
            recipients.addAll(recipientSort);

            Message first = new Message("RECEIVED", 1, -1, 2, "WW", "RECEIVED", "10/24/15", "20:45",
                    "Hey where did everyone go yesterday. I waited for an hour at the park!", recipients);
            Message second = new Message("ANNOUNCEMENT", 2, -1, 1, "BM", "SENT", "10/24/15", "20:49",
                    "Keep this chat area free from personal issues!", recipients);
            Message third = new Message("RECEIVED", 3, 1, 3, "BH", "RECEIVED", "10/24/15", "21:12",
                    "I'd tell you but, the boss said to keep your mommy issues to yourself. :P", recipients);
            Message a = new Message("RECEIVED", 1, -1, 2, "WW", "RECEIVED", "10/24/15", "20:45",
                    "Hey where did everyone go yesterday. I waited for an hour at the park!", recipients);
            Message b = new Message("RECEIVED", 2, -1, 1, "BM", "SENT", "10/24/15", "20:49",
                    "Keep this chat area free from personal issues!", recipients);
            Message c = new Message("RECEIVED", 3, 1, 3, "BH", "RECEIVED", "10/24/15", "21:12",
                    "I'd tell you but, the boss said to keep your mommy issues to yourself. :P", recipients);
            Message d = new Message("RECEIVED", 1, -1, 2, "WW", "RECEIVED", "10/24/15", "20:45",
                    "Hey where did everyone go yesterday. I waited for an hour at the park!", recipients);
            Message e = new Message("RECEIVED", 2, -1, 1, "BM", "SENT", "10/24/15", "20:49",
                    "Keep this chat area free from personal issues!", recipients);
            Message f = new Message("RECEIVED", 3, 1, 3, "BH", "RECEIVED", "10/24/15", "21:12",
                    "I'd tell you but, the boss said to keep your mommy issues to yourself. :P", recipients);
            Message g = new Message("RECEIVED", 1, -1, 2, "WW", "RECEIVED", "10/24/15", "20:45",
                    "Hey where did everyone go yesterday. I waited for an hour at the park!", recipients);
            Message h = new Message("RECEIVED", 2, -1, 1, "BM", "SENT", "10/24/15", "20:49",
                    "Keep this chat area free from personal issues!", recipients);
            Message i = new Message("RECEIVED", 3, 1, 3, "BH", "RECEIVED", "10/24/15", "21:12",
                    "I'd tell you but, the boss said to keep your mommy issues to yourself. :P", recipients);
            Message j = new Message("RECEIVED", 1, -1, 2, "WW", "RECEIVED", "10/24/15", "20:45",
                    "Hey where did everyone go yesterday. I waited for an hour at the park!", recipients);
            Message k = new Message("RECEIVED", 2, -1, 1, "BM", "SENT", "10/24/15", "20:49",
                    "Keep this chat area free from personal issues!", recipients);
            Message l = new Message("RECEIVED", 3, 1, 3, "BH", "RECEIVED", "10/24/15", "21:12",
                    "I'd tell you but, the boss said to keep your mommy issues to yourself. :P", recipients);
            messages.add(first);
            messages.add(second);
            messages.add(third);

            messages.add(a);
            messages.add(b);
            messages.add(c);

            messages.add(d);
            messages.add(e);
            messages.add(f);
            messages.add(g);
            messages.add(h);
            messages.add(i);

            messages.add(j);
            messages.add(k);
            messages.add(l);
            setListAdapter(new MsgArrayAdapter(getActivity(), messages));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        return inflater.inflate(R.layout.message_list_view, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        //getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
//        Message message = messages.get(position);
 //       Toast.makeText(getActivity(), message.getSenderInitials(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle saveState){
        super.onSaveInstanceState(saveState);
        ArrayList msgs = new ArrayList();
        int count = getListAdapter().getCount();
        for (int i = 0; i < count; ++i){
            msgs.add(getListAdapter().getItem(i));
        }
        saveState.putParcelableArrayList("myKey", msgs);
    }

}
