package coderoly.augustine.homepage;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import coderoly.augustine.homepage.tab3_friends.Tab3_Friends;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter (FragmentManager fm, int numberOfTabs){

        super(fm);

        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                Tab1_Notifs tab1_notifs = new Tab1_Notifs();
                return tab1_notifs;
            case 1:
                Tab2_Readings tab2_readings = new Tab2_Readings();
                return tab2_readings;
            case 2:
                Tab3_Friends tab3_friends = new Tab3_Friends();
                return tab3_friends;
            default:
                return null;

        }
    }

    @Override
    public int getCount(){
        return tabCount;
    }



/*
findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) { FirebaseAuth.getInstance().signOut(); Snackbar.make(view, "Signed Out Successfully", Snackbar.LENGTH_LONG).setAction("Okay", new View.OnClickListener() {
@Override
public void onClick(View view) { /* TODO: 12/10/16   Do something * / }
}).show(); startActivity(new Intent(HomeActivity.this, MainActivity.class)); }
});
*/



}
