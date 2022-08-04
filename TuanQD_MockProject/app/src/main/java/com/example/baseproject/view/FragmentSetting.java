package com.example.baseproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentSettingBinding;

public class FragmentSetting extends Fragment {

    public FragmentSetting() {
        // Required empty public constructor
    }

    public static FragmentSetting newInstance(String param1, String param2) {
        FragmentSetting fragment = new FragmentSetting();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSettingBinding fragmentSettingBinding= FragmentSettingBinding.inflate(inflater);
        // Inflate the layout for this fragment
        return fragmentSettingBinding.getRoot();
    }
}