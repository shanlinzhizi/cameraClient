package com.shanlin.camera.cameraclient.ui.fragment.shop.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.ui.fragment.shop.child.child.ContentFragment;
import com.shanlin.camera.cameraclient.ui.fragment.shop.child.child.MenuListFragment;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/4.
 */
public class ShopFragment extends BaseFragment {
    public static final String TAG = ShopFragment.class.getSimpleName();

    private Toolbar mToolbar;

    public static ShopFragment newInstance() {
        Bundle args = new Bundle();

        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        initView(view, savedInstanceState);
        return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        mToolbar.setTitle("商店");
        initToolbarMenu(mToolbar);

        if (savedInstanceState == null) {
            ArrayList<String> listMenus = new ArrayList<>();
            listMenus.add("Camera 1");
            listMenus.add("Camera 2");
            listMenus.add("Camera 3");
            listMenus.add("Camera 4");
            listMenus.add("Camera 5");
            listMenus.add("Camera 6");
            listMenus.add("Camera 7");
            listMenus.add("Camera 8");
            listMenus.add("Camera 9");
            listMenus.add("Camera 10");
            listMenus.add("Camera 11");
            listMenus.add("Camera 12");
            listMenus.add("Camera 13");
            listMenus.add("Camera 14");
            listMenus.add("Camera 15");
            listMenus.add("Camera 16");

            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            loadRootFragment(R.id.fl_list_container, menuListFragment);
            replaceLoadRootFragment(R.id.fl_content_container, ContentFragment.newInstance("Camera 1"), false);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        // ContentFragment是ShopFragment的栈顶子Fragment,会先调用ContentFragment的onBackPressedSupport方法
        Toast.makeText(_mActivity, "onBackPressedSupport-->返回false,交给上层处理!", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchContentFragment(ContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }
}
