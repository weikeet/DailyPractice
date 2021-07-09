package io.weicools.daily.practice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weicools
 * @date 2021.07.09
 */
public class FragmentAdapter2 extends FragmentStateAdapter {
  private List<Fragment> fragmentList = new ArrayList<>();

  public void updateFragmentList(List<Fragment> fragmentList) {
    this.fragmentList.clear();
    this.fragmentList.addAll(fragmentList);
  }

  public FragmentAdapter2(@NonNull Fragment fragment) {
    super(fragment);
  }

  public FragmentAdapter2(@NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }

  public FragmentAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
    super(fragmentManager, lifecycle);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    return fragmentList.get(position);
  }

  @Override public int getItemCount() {
    return fragmentList.size();
  }
}
