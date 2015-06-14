  package gt.edu.kinal.jmonterroso.movies;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import gt.edu.kinal.jmonterroso.movies.adapter.NavigationDrawerAdapter;
import gt.edu.kinal.jmonterroso.movies.helpers.RecyclerItemClickListener;
import gt.edu.kinal.jmonterroso.movies.helpers.UserSQLite;


  /**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FragmentDrawerListener drawerListener;
    private View containerView;

    private int ICONS[] = {R.drawable.ic_setting,R.drawable.ic_movie, R.drawable.ic_favorite, R.drawable.ic_events };
    private String TITLES[] = {"Ajustes", "Peliculas", "Favoritos", "Eventos"};
    private String NAME = null;
    private String EMAIL = null;
    private int PROFILE = R.drawable.ic_user_name;

    private UserSQLite sqLite;
    private SQLiteDatabase db;

    public NavigationDrawerFragment() {

    }

    public String UserEmail(String users){
        String email = null;
        sqLite = new UserSQLite(getActivity().getBaseContext());
        db = sqLite.getReadableDatabase();
        String Sql = "SELECT email FROM Users WHERE userName='"+users+"'";
        Cursor cu = db.rawQuery(Sql, null);
        if (cu.moveToFirst())
        {
            do {
                email = cu.getString(0);
            } while (cu.moveToNext());
        }
        db.close();

        return email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);
        String names = prefs.getString(getString(R.string.userRemembered), "");
        NAME = names;
        EMAIL = UserEmail(names);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NavigationDrawerAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }
        }));

        return v;
    }


      public void setUp(DrawerLayout drawerLaout, Toolbar toolbar, int fragmemtId) {
          this.mDrawerLayout = drawerLaout;
          this.mToolbar = toolbar;
          this.containerView = getActivity().findViewById(fragmemtId);
          mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLaout, toolbar, R.string.drawer_open, R.string.drawer_close){

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
                  mToolbar.setAlpha(1 - slideOffset / 2);
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

      public void setDrawerListener(FragmentDrawerListener jDrawerListener){
          this.drawerListener = jDrawerListener;
      }

      public interface FragmentDrawerListener{
          public void onDrawerItemSelected(View view, int position);
      }

}
