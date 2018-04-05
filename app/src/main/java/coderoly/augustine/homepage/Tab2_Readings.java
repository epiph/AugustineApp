package coderoly.augustine.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import coderoly.augustine.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2_Readings extends Fragment {


    public Tab2_Readings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab2_readings, container, false);
    }

}
