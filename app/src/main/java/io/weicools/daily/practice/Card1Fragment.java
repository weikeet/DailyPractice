package io.weicools.daily.practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.weicools.daily.practice.greenDao.GreenDaoActivity;
import io.weicools.daily.practice.room.ui.RoomActivity;

/**
 * @author weicools
 */
public class Card1Fragment extends Fragment implements View.OnClickListener {
  Button mBtnTestGreenDao;
  Button mBtnTestRoom;

  private Activity mActivity;

  public Card1Fragment() { }

  public static Card1Fragment newInstance() {
    return new Card1Fragment();
  }

  @Override public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    mActivity = (Activity) context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_card1, container, false);

    mBtnTestGreenDao = view.findViewById(R.id.btn_test_green_dao);
    mBtnTestRoom = view.findViewById(R.id.btn_test_room);

    return view;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBtnTestGreenDao.setOnClickListener(this);
    mBtnTestRoom.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    Intent intent = new Intent();
    switch (view.getId()) {
      case R.id.btn_test_green_dao:
        intent.setClass(mActivity, GreenDaoActivity.class);
        startActivity(intent);
        break;
      case R.id.btn_test_room:
        intent.setClass(mActivity, RoomActivity.class);
        startActivity(intent);
      default:
        break;
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
