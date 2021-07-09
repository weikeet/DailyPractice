package io.weicools.daily.practice.widget.viewpager;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import io.weicools.daily.practice.R;
import java.util.ArrayList;

/**
 * @author weicools
 * @date 2021.07.05
 */
class ViewPagerActivity extends AppCompatActivity {
  private ViewPager viewPager;

  private Fragment1 fragment1;
  private Fragment2 fragment2;
  private Fragment3 fragment3;
  private ArrayList<Fragment> fragmentList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPager = new ViewPager(this);

    fragment1 = new Fragment1();
    fragment2 = new Fragment2();
    fragment3 = new Fragment3();

    fragmentList = new ArrayList<>();
    fragmentList.add(fragment1);
    fragmentList.add(fragment2);
    fragmentList.add(fragment3);

    viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
  }

  public class MyViewPagerAdapter extends FragmentPagerAdapter {
    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
      super(fm, behavior);
    }

    // @Nullable
    // @Override
    // public Parcelable saveState() {
    //     return null;
    // }

    @Override
    public Fragment getItem(int arg0) {
      return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
      return fragmentList.size();
    }
  }
}
