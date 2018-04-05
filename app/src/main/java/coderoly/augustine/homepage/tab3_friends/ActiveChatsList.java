package coderoly.augustine.homepage.tab3_friends;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import coderoly.augustine.R;

public class ActiveChatsList extends ArrayAdapter<String> {

    Activity context;
    String[] username;
    String[] message;
    String[] time;
    int[] imageId;

    public ActiveChatsList(Activity context, String[] username, String[] message, String[] time, int[] imageId) {
        super(context, R.layout.tab3_friends_active_chats_list_row, username );

        this.context = context;
        this.username = username;
        this.message = message;
        this.time = time;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.tab3_friends_active_chats_list_row, null, true);

        TextView textView1 = (TextView) rowView.findViewById(R.id.tv_ActiveChats_username);
        TextView textView2 = (TextView) rowView.findViewById(R.id.tv_ActiveChats_time);
        TextView textView3 = (TextView) rowView.findViewById(R.id.tv_ActiveChats_message);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_ActiveChats_pic);

        textView1.setText(username[position]);
        textView2.setText(time[position]);
        textView3.setText(message[position]);
        imageView.setImageResource(imageId[position]);

        return rowView;
    }


}

