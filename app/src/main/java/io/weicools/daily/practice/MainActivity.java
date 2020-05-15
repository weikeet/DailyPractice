package io.weicools.daily.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.LayoutInflaterCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import io.weicools.daily.practice.material.ToolbarTestActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weicools
 */
public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

  Toolbar mToolbar;
  TabLayout mTabLayout;
  ViewPager mViewPager;
  FloatingActionButton mFabMain;
  NavigationView mNavView;
  DrawerLayout mDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), object : LayoutInflater.Factory2 {
    //   private var sum: Double = 0.0
    //   override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
    //       // 测量构建单个View耗时: 1s = 1000ms / 1ms = 1000us / 1us = 1000ns / 1ns = 1000ps
    //       val (view, duration) = measureTimedValue { delegate.createView(parent, name, context, attrs) }
    //       sum += duration.inMilliseconds
    //       Log.d(TEST_TAG, "view=${view?.let { it::class.simpleName }} duration=${duration}  sum=${sum}")
    //   return view
    //   }
    //
    //   override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
    //   return null
    //   }
    // })

    LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflater.Factory2() {
      private float sumTimeUs;

      @Override public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        long startTimeNs = System.nanoTime();

        View view = getDelegate().createView(parent, name, context, attrs);
        float costUs = (System.nanoTime() - startTimeNs) / 1000f;

        sumTimeUs += costUs;

        String viewName = view == null ? "Null" : view.getClass().getSimpleName();
        Log.d("TEST_TAG", "Factory2 onCreateView view=" + viewName + ", costUs=" + costUs + ", sumTimeMs=" + sumTimeUs / 1000f);
        return view;
      }

      @Nullable @Override public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
      }
    });
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

  private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
    @Override
    public void onPageSelected(int position) {
      if (position == 2) {
        mFabMain.show();
      } else {
        mFabMain.hide();
      }
    }
  };

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.nav_header:
        mDrawerLayout.closeDrawer(GravityCompat.START);
        break;
      case R.id.fab_main:
        startActivity(new Intent(this, ToolbarTestActivity.class));
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
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
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
