package io.weicools.daily.practice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Create by weicools on 2017/12/30.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
  private final List<Fragment> mFragments;
  private final List<String> mTitles;

  public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
    super(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
    mFragments = fragments;
    mTitles = titles;
  }

  @NonNull @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public int getCount() {
    return mFragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mTitles.get(position);
  }
}
