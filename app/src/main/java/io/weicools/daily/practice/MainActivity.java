package io.weicools.daily.practice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weicools
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

  Toolbar mToolbar;
  TabLayout mTabLayout;
  ViewPager mViewPager;
  FloatingActionButton mFabMain;
  NavigationView mNavView;
  DrawerLayout mDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = findViewById(R.id.toolbar);
    mTabLayout = findViewById(R.id.tab_layout_main);
    mViewPager = findViewById(R.id.view_pager_main);
    mFabMain = findViewById(R.id.fab_main);
    mNavView = findViewById(R.id.nav_view);
    mDrawerLayout = findViewById(R.id.drawer_layout);

    initView();
    initViewPager();
  }

  private void initView() {
    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    mNavView.setNavigationItemSelectedListener(this);
    mNavView.setItemIconTintList(null);

    View headerView = mNavView.getHeaderView(0);
    LinearLayout navHeader = headerView.findViewById(R.id.nav_header);
    navHeader.setOnClickListener(this);

    mFabMain.setOnClickListener(this);
  }

  private void initViewPager() {
    List<String> titles = new ArrayList<>();
    titles.add(getString(R.string.tab_title_main_1));
    titles.add(getString(R.string.tab_title_main_2));
    titles.add(getString(R.string.tab_title_main_3));
    mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
    mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
    mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));

    List<Fragment> fragments = new ArrayList<>();
    fragments.add(new Card1Fragment());
    fragments.add(new Card2Fragment());
    fragments.add(new Card3Fragment());

    mViewPager.setOffscreenPageLimit(2);

    FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
    mViewPager.setAdapter(mFragmentAdapter);
    mTabLayout.setupWithViewPager(mViewPager);

    mViewPager.addOnPageChangeListener(mPageChangeListener);
  }

  private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
      if (position == 2) {
        mFabMain.show();
      } else {
        mFabMain.hide();
      }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
  };

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.nav_header:
        // Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        // startActivity(intent);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        break;
      case R.id.fab_main:
        Snackbar.make(view, getString(R.string.main_snack_bar), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.main_snack_bar_action), view1 -> { }).show();
      default:
        break;
    }
  }

  @Override
  public void onBackPressed() {
    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
