package com.fortunebrains.nveg.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.DashBoardActivity;
import com.fortunebrains.nveg.activities.ShoppingActivity;
import com.fortunebrains.nveg.adapters.ExpandableListAdapter;
import com.fortunebrains.nveg.adapters.NavigationAdapter;
import com.fortunebrains.nveg.adapters.NavigationDrawerAdapter;
import com.fortunebrains.nveg.common.NavDrawerItem;
import com.fortunebrains.nveg.common.RoundedImageView;
import com.fortunebrains.nveg.common.SimpleDividerItemDecoration;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sree on 12/21/2016.
 */

public class FragmentDrawer extends Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    private ListView drawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;

    public FragmentDrawer() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;

    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();


        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        sharedPreferences = getActivity().getSharedPreferences("prefName", getActivity().MODE_APPEND);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        String uri = sharedPreferences.getString("URI", null);
        String name = sharedPreferences.getString("NAME", null);
        key = sharedPreferences.getInt("KEY", 0);
        drawerList = (ListView) layout.findViewById(R.id.drawerList);
        NavigationAdapter navigationAdapter = new NavigationAdapter(getActivity());
        drawerList.setAdapter(navigationAdapter);

        return layout;
    }
    int key;

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    public interface FragmentDrawerListener
    {
        public void onDrawerItemSelected(View view, int position);
    }


    public class NavigationAdapter extends BaseAdapter {

        Context context;

        public NavigationAdapter(Context context) {
            this.context = context;

        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.main_row, null);


            LinearLayout llOffers = (LinearLayout) convertView.findViewById(R.id.llOffers);
            final LinearLayout llSOffers = (LinearLayout) convertView.findViewById(R.id.llSOffers);
            LinearLayout llHotDeals = (LinearLayout) convertView.findViewById(R.id.llHotDeals);
            final LinearLayout llSHoteDeals = (LinearLayout) convertView.findViewById(R.id.llSHoteDeals);

            final TextView tvOPlus = (TextView) convertView.findViewById(R.id.tvOPlus);
            final TextView tvOMinus = (TextView) convertView.findViewById(R.id.tvOMinus);
            final TextView tvHPlus = (TextView) convertView.findViewById(R.id.tvHPlus);
            final TextView tvHMinus = (TextView) convertView.findViewById(R.id.tvHMinus);


            llOffers.setOnClickListener(new View.OnClickListener() {
                boolean count = false;

                @Override
                public void onClick(View v) {
                    if (count) {
                        count = false;
                        tvOPlus.setVisibility(View.VISIBLE);
                        tvOMinus.setVisibility(View.GONE);
                        llSOffers.setVisibility(View.GONE);
                    } else {
                        count = true;
                        Toast.makeText(context, "" + count, Toast.LENGTH_SHORT).show();
                        tvOPlus.setVisibility(View.GONE);
                        tvOMinus.setVisibility(View.VISIBLE);
                        llSOffers.setVisibility(View.VISIBLE);

                    }

                }
            });

            llHotDeals.setOnClickListener(new View.OnClickListener() {
                boolean count = false;

                @Override
                public void onClick(View v) {
                    if (count) {
                        count = false;
                        tvHPlus.setVisibility(View.VISIBLE);
                        tvHMinus.setVisibility(View.GONE);
                        llSHoteDeals.setVisibility(View.GONE);
                    } else {
                        count = true;
                        tvHPlus.setVisibility(View.GONE);
                        tvHMinus.setVisibility(View.VISIBLE);
                        llSHoteDeals.setVisibility(View.VISIBLE);

                    }

                }
            });


            TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
            TextView tvOffers = (TextView) convertView.findViewById(R.id.tvOffers);
            TextView tvNveg = (TextView) convertView.findViewById(R.id.tvNveg);
            TextView tvInvite = (TextView) convertView.findViewById(R.id.tvInvite);
            TextView tvRateUs = (TextView) convertView.findViewById(R.id.tvRateUs);
            TextView tvFeedBack = (TextView) convertView.findViewById(R.id.tvFeedBack);
            TextView tvAboutUs = (TextView) convertView.findViewById(R.id.tvAboutUs);
            TextView tvHelp = (TextView) convertView.findViewById(R.id.tvHelp);
            TextView tvContact = (TextView) convertView.findViewById(R.id.tvContact);
            TextView tvLogout = (TextView) convertView.findViewById(R.id.tvLogout);

            tvHome.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    drawerListener.onDrawerItemSelected(containerView, 0);
                    mDrawerLayout.closeDrawer(containerView);

                    Fragment fragment = new HomeFragment();
                    if (fragment != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                    }
                }
            });
            tvOffers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 3);
                    mDrawerLayout.closeDrawer(containerView);
                }
            });
            tvNveg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 4);
                    mDrawerLayout.closeDrawer(containerView);
                }
            });
            tvInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 5);
                    mDrawerLayout.closeDrawer(containerView);


                    String shareBody = "Here is the share content body";
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    getActivity().startActivity(Intent.createChooser(sharingIntent, "Sharing"));
                }
            });
            tvRateUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 6);
                    mDrawerLayout.closeDrawer(containerView);

                }
            });
            tvFeedBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 7);
                    mDrawerLayout.closeDrawer(containerView);
                    Fragment fragment = new FeedbackFragment();
                    if (fragment != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                    }
                }
            });
            tvAboutUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 8);
                    mDrawerLayout.closeDrawer(containerView);
                    Fragment fragment = new AboutUsFragment();
                    if (fragment != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                    }
                }
            });
            tvHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 9);
                    mDrawerLayout.closeDrawer(containerView);

                    Fragment fragment = new HelpFragment();
                    if (fragment != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                    }
                }
            });
            tvContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 10);
                    mDrawerLayout.closeDrawer(containerView);

                    Fragment fragment = new ContactUsFragment();
                    if (fragment != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                    }
                }
            });
            tvLogout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    drawerListener.onDrawerItemSelected(containerView, 11);
                    mDrawerLayout.closeDrawer(containerView);
                    logout();
                }
            });


            return convertView;
        }
    }

    SharedPreferences sharedPreferences;
    GoogleApiClient mGoogleApiClient;

    private void logout() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setMessage("Do You Want to Logout or Not");
        adb.setTitle("LOGOUT");
        adb.setIcon(R.mipmap.ic_launcher);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (key == 1001) {
                    FacebookSdk.sdkInitialize(getActivity());
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(getActivity(), DashBoardActivity.class));
                    getActivity().finish();

                } else {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {
                                    startActivity(new Intent(getActivity(), DashBoardActivity.class));
                                    getActivity().finish();
                                }
                            });
                }
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        adb.show();
    }

}

