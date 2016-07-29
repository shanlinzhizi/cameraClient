package com.shanlin.camera.cameraclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.ItemData;
import com.shanlin.camera.cameraclient.ui.fragment.me.LogoutFragment;
import com.shanlin.camera.cameraclient.ui.fragment.me.child.HelpFragment;
import com.shanlin.camera.cameraclient.ui.fragment.me.child.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APhil on 16/7/29.
 */
public class MeGridAdapter extends BaseAdapter {

    List<ItemData> datas;
    Context context;
    public MeGridAdapter(Context context){
        this.context = context;
        datas = generateItems();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public ItemData getItem(int i) {
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


            convertView= LayoutInflater.from(context).inflate(R.layout.item_me_gridview, null);
            holder=new Holder();
            holder.tv=(TextView) convertView.findViewById(R.id.tv_item);
            holder.img=(ImageView) convertView.findViewById(R.id.img_item);

            convertView.setTag(holder);

        }else{
            holder=(Holder) convertView.getTag();

        }
//        为holder中的tv和img设置内容
        holder.tv.setText(datas.get(i).getName());
        holder.img.setImageResource(datas.get(i).getImgResId());
//        注意  默认为返回null,必须得返回convertView视图
        return convertView;
    }


class Holder{
    public TextView tv;
    public ImageView img;
}

    private List<ItemData> generateItems(){
        List<ItemData> datas = new ArrayList<>(6);


        ItemData data2 = new ItemData();
        data2.setImgResId( R.drawable.ic_settings_grey_500_24dp);
        data2.setName( "设置");
        data2.setTarget( SettingsFragment.class);
        datas.add(data2);

        ItemData data3 = new ItemData();
        data3.setImgResId( android.R.drawable.ic_delete);
        data3.setName(  "注销");
        data3.setTarget(LogoutFragment.class);
        datas.add(data3);

        ItemData data1 = new ItemData();
        data1.setImgResId(android.R.drawable.ic_menu_help);
        data1.setName( context.getString(R.string.help));
        data1.setTarget( HelpFragment.class);
        datas.add(data1);

        return datas;
    }
}
