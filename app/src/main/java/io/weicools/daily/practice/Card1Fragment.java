package io.weicools.daily.practice;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.weicools.daily.practice.greenDao.GreenDaoActivity;
import io.weicools.daily.practice.room.ui.RoomActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Card1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Card1Fragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.btn_test_green_dao)
    Button mBtnTestGreenDao;
    Unbinder unbinder;
    @BindView(R.id.btn_test_room)
    Button mBtnTestRoom;

    private String mParam1;
    private String mParam2;

    public Card1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Card1Fragment.
     */
    public static Card1Fragment newInstance(String param1, String param2) {
        Card1Fragment fragment = new Card1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card1, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBtnTestGreenDao.setOnClickListener(this);
        mBtnTestRoom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_test_green_dao:
                intent.setClass(getContext(), GreenDaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_room:
                intent.setClass(getContext(), RoomActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
