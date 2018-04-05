package coderoly.augustine.homepage.tab3_friends.recyclerViewModels;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oly on 12/15/16.
 */

public class TitleCreator {

    static TitleCreator _titleCreator;
    List<TitleParent> _titleParents;


    public TitleCreator(Context context){

        _titleParents = new ArrayList<>();

        for (int i = 1; i <= 100; i++){

            TitleParent title = new TitleParent( String.format("Caller #%d", i));
            _titleParents.add(title);
        }

    }




    public static TitleCreator get(Context context){

        if (_titleCreator == null)
            _titleCreator = new TitleCreator(context);

        return _titleCreator;
    }

    public List<TitleParent> getAll() {

        return _titleParents;
    }
}
