package coderoly.augustine.homepage.tab3_friends.recyclerViewModels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import coderoly.augustine.R;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleChild;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleParent;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.viewHolder.TitleChildViewHolder;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.viewHolder.TitleParentViewHolder;

/**
 * Created by oly on 12/15/16.
 */

public class MyAdapter extends ExpandableRecyclerAdapter<TitleParentViewHolder, TitleChildViewHolder> {

    LayoutInflater inflater;

    public MyAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);

        inflater = LayoutInflater.from(context);


    }






    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.tab3_pending_friends_list_row, viewGroup, false);
        return new TitleParentViewHolder(view);

    }

    @Override
    public TitleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.tab3_pending_friends_list_children, viewGroup, false);
        return new TitleChildViewHolder(view);

    }

    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {

        TitleParent title = (TitleParent) o ;
        titleParentViewHolder._textView.setText(title.getTitle());

    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, int i, Object o) {

        TitleChild title = (TitleChild) o ;
        titleChildViewHolder.option1.setText(title.getOption1());
        titleChildViewHolder.option2.setText(title.getOption2());

    }
}
