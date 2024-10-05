package com.cn.setuplibrary.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity
            , List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList == null ? 0:fragmentList.size();
    }
}
