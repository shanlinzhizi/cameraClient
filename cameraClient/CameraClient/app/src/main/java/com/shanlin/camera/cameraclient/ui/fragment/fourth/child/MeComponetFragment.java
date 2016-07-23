package com.shanlin.camera.cameraclient.ui.fragment.fourth.child;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.net.UserBzs;
import com.shanlin.camera.cameraclient.ui.fragment.first.HomePageFragment;
import com.shanlin.camera.cameraclient.ui.fragment.fourth.LoginActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YoKeyword on 16/6/6.
 */
public class MeComponetFragment extends BaseFragment {
    private TextView mTvBtnSettings;

    public static MeComponetFragment newInstance() {

        Bundle args = new Bundle();

        MeComponetFragment fragment = new MeComponetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_component, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvBtnSettings = (TextView) view.findViewById(R.id.tv_btn_settings);
        mTvBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserBzs.isLogin()) {
                    start(SettingsFragment.newInstance());
                }else{
                    startActivity(new Intent(v.getContext(), LoginActivity.class));
                }
            }
        });

        GridView gridView = (GridView) view.findViewById(R.id.grd_settings);
        gridView.setAdapter(new MyAdapter());

    }

    @Override
    public boolean onBackPressedSupport() {
        // 这里实际项目中推荐使用 EventBus接耦
        ((com.shanlin.camera.cameraclient.ui.fragment.fourth.MeFragment)getParentFragment()).onBackToFirstFragment();
        return true;
    }

    class MyAdapter extends BaseAdapter{
        List<ItemData> datas = generateItems();

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            Holder holder;
            if(convertView==null){


                convertView=LayoutInflater.from(getContext()).inflate(R.layout.item_me_gridview, null);
                holder=new Holder();
                holder.tv=(TextView) convertView.findViewById(R.id.tv_item);
                holder.img=(ImageView) convertView.findViewById(R.id.img_item);

                convertView.setTag(holder);

            }else{
                holder=(Holder) convertView.getTag();

            }
//        为holder中的tv和img设置内容
            holder.tv.setText(datas.get(i).name);
            holder.img.setImageResource(datas.get(i).imgResId);
//        注意  默认为返回null,必须得返回convertView视图
            return convertView;
        }
    }

    class Holder{
        public TextView tv;
        public ImageView img;
    }

    class ItemData{
        public String name;
        public Class target;
        public Integer imgResId;
    }

    private List<ItemData> generateItems(){
        List<ItemData> datas = new ArrayList<>(6);
        ItemData data1 = new ItemData();
        data1.imgResId = android.R.drawable.ic_menu_help;
        data1.name = "帮助";
        data1.target = HomePageFragment.class;
        datas.add(data1);

        ItemData data2 = new ItemData();
        data2.imgResId = R.drawable.ic_settings_grey_500_24dp;
        data2.name = "设置";
        data2.target = HomePageFragment.class;
        datas.add(data2);

        ItemData data3 = new ItemData();
        data3.imgResId = android.R.drawable.ic_dialog_email;
        data3.name = "注销";
        data3.target = HomePageFragment.class;
        datas.add(data2);

        return datas;
    }
}
