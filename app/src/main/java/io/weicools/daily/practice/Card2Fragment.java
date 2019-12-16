package io.weicools.daily.practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Card2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Card2Fragment extends Fragment {
  public Card2Fragment() { }

  public static Card2Fragment newInstance() {
    return new Card2Fragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_card2, container, false);
  }
}
