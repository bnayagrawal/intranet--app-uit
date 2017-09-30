package xyz.bnayagrawal.android.icontest;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Bundle savedInstanceState;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        this.savedInstanceState = savedInstanceState;
        setFragment();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_dashboard);
    }

    // TODO: (OPTIONAL) ADD CUSTOME SLIDE ANIMATION WHEN LAUNCHING THIS ACTIVITY

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if(count == 1)
                Toast.makeText(this, "Press back button once again to exit!", Toast.LENGTH_SHORT).show();
            if(count == 0)
                super.onBackPressed();
            else
                getSupportFragmentManager().popBackStack();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setCheckedItem(id);
        switch (id) {
            case R.id.nav_dashboard : {
                if (findViewById(R.id.fragment_container) != null) {
                    //check if it the fragment is already being display
                    FragmentDashboard tf =  (FragmentDashboard)getSupportFragmentManager().findFragmentByTag("DASHBOARD_FRAGMENT");
                    if(tf != null && tf.isVisible())
                        break;//means this fragment is currently being displayed

                    if (savedInstanceState == null) {
                        //clear the back stack
                        int count =  getSupportFragmentManager().getBackStackEntryCount();
                        for(int i = 0; i < count; ++i) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }
                        Toast.makeText(this, "Press back button once again to exit!", Toast.LENGTH_SHORT).show();

                        //create the fragment
                        drawer.closeDrawer(GravityCompat.START);
                        FragmentDashboard fragment = new FragmentDashboard ();
                        fragment.setArguments(getIntent().getExtras());
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, fragment,"DASHBOARD_FRAGMENT").commit();
                    }
                }
                break;
            }
            case R.id.nav_anouncements:
                break;
            case R.id.nav_assignments:
                break;
            case R.id.nav_notice:
                break;
            case R.id.nav_events: {
                if (findViewById(R.id.fragment_container) != null) {
                    //check if it the fragment is already being display
                    FragmentEvents tf =  (FragmentEvents)getSupportFragmentManager().findFragmentByTag("EVENTS_FRAGMENT");
                    if(tf != null && tf.isVisible())
                        break; //means this fragment is currently being displayed
                    if (savedInstanceState == null) {
                        drawer.closeDrawer(GravityCompat.START);
                        FragmentEvents fragment = new FragmentEvents();
                        fragment.setArguments(getIntent().getExtras());
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.replace(R.id.fragment_container, fragment,"EVENTS_FRAGMENT").addToBackStack(null).commit();
                    }
                }
                break;
            }
            case R.id.nav_news:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_logout:
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setToolbar() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        } else {
            drawer.setDrawerListener(null);
        }
    }

    protected void setFragment() {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            //clear backstack
            getSupportFragmentManager().popBackStackImmediate();

            // Create a new Fragment to be placed in the activity layout
            FragmentDashboard fragment = new FragmentDashboard();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            fragment.setArguments(getIntent().getExtras());

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // Add the fragment to the 'fragment_container' FrameLayout
            ft.replace(R.id.fragment_container, fragment, "DASHBOARD_FRAGMENT").commit();
        }
    }
}
