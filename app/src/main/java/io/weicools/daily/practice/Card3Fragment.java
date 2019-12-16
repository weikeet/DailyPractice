package io.weicools.daily.practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/**
 * @author weicools
 */
public class Card3Fragment extends Fragment {
  public Card3Fragment() { }

  public static Card3Fragment newInstance() {
    return new Card3Fragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_card3, container, false);
  }
}
