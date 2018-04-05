package coderoly.augustine.homepage;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import coderoly.augustine.MainActivity;
import coderoly.augustine.R;
import coderoly.augustine.auth.AddUserToDatabase;
import coderoly.augustine.errors.ConnectionErrors;

import static coderoly.augustine.R.id.parent;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users");

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Handle_Action_Bar_Tabs();
        Handle_Navigation_Drawer();

        //AddUserToDatabase addUserToDatabase = new AddUserToDatabase();



        Check_User_In_Database(dbRef);




    }

    private void Check_User_In_Database(final DatabaseReference myRef) {

        final FirebaseUser signedUser = FirebaseAuth.getInstance().getCurrentUser();

        String userEmail = signedUser.getEmail();
        userEmail = userEmail.replace("@", "%at%");
        final String encodedEmail = userEmail.replace(".", "%fullStop%");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot = dataSnapshot.child(encodedEmail);

                String emailAddress = dataSnapshot.child("Email_Address").getValue(String.class);
                String username = dataSnapshot.child("Username").getValue(String.class);
                String phone = dataSnapshot.child("Phone").getValue(String.class);
                String diocese = dataSnapshot.child("Diocese").getValue(String.class);

                Toast.makeText(HomeActivity.this, emailAddress + username + phone + diocese , Toast.LENGTH_LONG).show();

                if (emailAddress != null && username != null && phone != null && diocese != null ) {

                    return;
                }


                Fragment fragment= new AddUserToDatabase();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.drawer_layout, fragment).commit();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                new ConnectionErrors().An_Error_Occurred_Try_Again_Later(HomeActivity.this);

            }
        });
    }


    private void Handle_Navigation_Drawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        Toast.makeText(getBaseContext(), String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_gallery:
                        Toast.makeText(getBaseContext(), String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_slideshow:
                        Toast.makeText(getBaseContext(), String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_manage:
                        Toast.makeText(getBaseContext(), String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_share:
                        Toast.makeText(getBaseContext(), String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_send:
                        Toast.makeText(getBaseContext(), String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();

                        break;
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    public void Handle_Action_Bar_Tabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Notifications"));
        tabLayout.addTab(tabLayout.newTab().setText("Readings"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(getBaseContext(), "Openning Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(false);
        }
    }





}