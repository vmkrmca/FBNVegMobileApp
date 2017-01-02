package com.fortunebrains.nveg.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.fragments.AboutUsFragment;
import com.fortunebrains.nveg.fragments.CategoryFragment;
import com.fortunebrains.nveg.fragments.ContactUsFragment;
import com.fortunebrains.nveg.fragments.FeedbackFragment;
import com.fortunebrains.nveg.fragments.FragmentDrawer;
import com.fortunebrains.nveg.fragments.HelpFragment;
import com.fortunebrains.nveg.fragments.HomeFragment;
import com.fortunebrains.nveg.fragments.HotDealsFragment;
import com.fortunebrains.nveg.fragments.InviteFragment;
import com.fortunebrains.nveg.fragments.NVegFragment;
import com.fortunebrains.nveg.fragments.OffersFragment;
import com.fortunebrains.nveg.fragments.ProfileFragment;
import com.fortunebrains.nveg.fragments.RateUsFragment;
import com.fortunebrains.nveg.fragments.ShareFragment;

/**
 * Created by sree on 12/21/2016.
 */
public class ShoppingActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    Toolbar mToolbar;

    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);



        displayView(0);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new CategoryFragment();
                title = getString(R.string.title_ocategory);
                break;
            case 2:
                fragment = new HotDealsFragment();
                title = getString(R.string.title_hotdeals);
                break;
            case 3:
                fragment = new OffersFragment();
                title = getString(R.string.title_offers);
                break;
            case 4:
                fragment = new NVegFragment();
                title = getString(R.string.title_nveg);
                break;
            case 5:
                fragment = new InviteFragment();
                title = getString(R.string.title_invite);
                break;
            case 6:
                fragment = new RateUsFragment();
                title = getString(R.string.title_rate);
                break;
            case 7:
                fragment = new FeedbackFragment();
                title = getString(R.string.title_feedback);
                break;
            case 8:
                fragment = new ShareFragment();
                title = getString(R.string.title_share);
                break;
            case 9:
                fragment = new AboutUsFragment();
                title = getString(R.string.title_about);
                break;
            case 10:
                fragment = new HelpFragment();
                title = getString(R.string.title_help);
                break;
            case 11:
                fragment = new ContactUsFragment();
                title = getString(R.string.title_contact);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }


}
