package coderoly.augustine.homepage.tab3_friends;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import coderoly.augustine.R;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleChild;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleCreator;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleParent;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.adapters.MyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3_Friends extends Fragment {

    RecyclerView recyclerView;


    // private List<ParentObject> initData() {}
    public Tab3_Friends() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.tab3_friends, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyAdapter adapter = new MyAdapter(getActivity(), initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);


        return view;

    }


    private List<ParentObject> initData() {

        String s1 = "Add to contact";
        String s2 = "Send Message";


        TitleCreator titleCreator = TitleCreator.get(getActivity());
        List<TitleParent> titles = titleCreator.getAll();

        List<ParentObject> parentObject = new ArrayList<>();



            for (TitleParent title : titles) {
                List<Object> childList = new ArrayList<>();

                for (int i = 1; i <= 10; i++) {


                    childList.add(new TitleChild(s1, s2));

            }
                title.setChildObjectList(childList);

                parentObject.add(title);

        }
        return parentObject;

    }

}