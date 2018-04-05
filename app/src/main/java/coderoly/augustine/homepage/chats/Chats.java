package coderoly.augustine.homepage.chats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import coderoly.augustine.R;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleChild;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleCreator;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.TitleParent;
import coderoly.augustine.homepage.tab3_friends.recyclerViewModels.adapters.MyAdapter;

public class Chats extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter) recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // final String s = getIntent().getStringExtra("username").toString();
      //  Toast.makeText(Chats.this, s , Toast.LENGTH_LONG).show();

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this, initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);

    }

    private List<ParentObject> initData() {
        TitleCreator titleCreator = TitleCreator.get(this);
        List<TitleParent> titles = titleCreator.getAll();

        List<ParentObject> parentObject = new ArrayList<>();

        for (TitleParent title:titles){
            List<Object> childList = new ArrayList<>();
            childList.add(new TitleChild("Add to contact", "Send Message"));
            title.setChildObjectList(childList);

            parentObject.add(title);
        }

        return parentObject;
    }
}
