package coderoly.augustine.homepage.tab3_friends.recyclerViewModels.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import coderoly.augustine.R;

/**
 * Created by oly on 12/15/16.
 */

public class TitleParentViewHolder extends ParentViewHolder {

    public TextView _textView;
    public ImageButton _imageButton;

    public TitleParentViewHolder(View itemView) {
        super(itemView);
        _textView = (TextView) itemView.findViewById(R.id.parentTitle);
        _imageButton = (ImageButton) itemView.findViewById(R.id.expandButton);
    }


}
