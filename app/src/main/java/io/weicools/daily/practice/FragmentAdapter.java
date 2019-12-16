package io.weicools.daily.practice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Create by weicools on 2017/12/30.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
  private List<Fragment> mFragments;
  private List<String> mTitles;

  public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
    super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
