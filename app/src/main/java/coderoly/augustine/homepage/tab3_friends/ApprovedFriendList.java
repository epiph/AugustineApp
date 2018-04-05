package coderoly.augustine.homepage.tab3_friends;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import coderoly.augustine.R;

public class ApprovedFriendList extends ArrayAdapter<String> {

    Activity context;
    String[] username;
    String[] status;
    int[] imageId;

    public ApprovedFriendList(Activity context, String[] username, String[] status, int[] imageId) {
        super(context, R.layout.tab3_friends_active_chats_list_row, username );

        this.context = context;
        this.username = username;
        this.status = status;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.tab3_friends_approved_friends_list_row, null, true);

        TextView textView1 = (TextView) rowView.findViewById(R.id.tv_ApprovedFriends_username);
        TextView textView2 = (TextView) rowView.findViewById(R.id.tv_ApprovedFriends_status);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_ApprovedFriends_pic);

        textView1.setText(username[position]);
        textView2.setText(status[position]);
        imageView.setImageResource(imageId[position]);

        return rowView;
    }


}

