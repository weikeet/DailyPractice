package com.weiwei.practice.common.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/**
 * @author weicools
 * @date 2017/12/30
 */
@SuppressWarnings("deprecation")
public class FragmentAdapter extends FragmentPagerAdapter {
  private final List<FragmentData> fragmentDataList;

  public FragmentAdapter(FragmentManager fm, List<FragmentData> fragmentDataList) {
    super(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
    this.fragmentDataList = fragmentDataList;
  }

  public FragmentAdapter(FragmentManager fm, int behavior, List<FragmentData> fragmentDataList) {
    super(fm, behavior);
    this.fragmentDataList = fragmentDataList;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return fragmentDataList.get(position).getFragment();
  }

  @Override
  public int getCount() {
    return fragmentDataList.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return fragmentDataList.get(position).getTitle();
  }
}
