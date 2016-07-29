package com.shanlin.camera.cameraclient.ui.fragment.me.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.adapter.MeGridAdapter;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.entity.ItemData;
import com.shanlin.camera.cameraclient.net.UserBzs;


/**
 * Created by YoKeyword on 16/6/6.
 */
public class AvatarFragment extends BaseFragment implements View.OnClickListener , ViewInteractive{

    public static AvatarFragment newInstance() {

        Bundle args = new Bundle();

        AvatarFragment fragment = new AvatarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView mImgUser;
    private LinearLayout mLayoutComponet;
    private LinearLayout mLayoutPhotos;
    private LinearLayout mLayoutShared;
    private LinearLayout mLayoutFriend;
    private ImageView mImgModify;

    private AvatarPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avatar_top, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if( presenter != null){
            presenter = null;
        }
    }

    private void initView(View view){
        mLayoutComponet = (LinearLayout) view.findViewById(R.id.ll_me_other);
        mLayoutPhotos = (LinearLayout) view.findViewById(R.id.ll_photo);
        mLayoutShared = (LinearLayout) view.findViewById(R.id.ll_share);
        mLayoutFriend = (LinearLayout) view.findViewById(R.id.ll_my_friend);
        mImgUser = (ImageView) view.findViewById(R.id.img_user);
        mImgModify = (ImageView) view.findViewById(R.id.btn_modify);

        mLayoutPhotos.setOnClickListener(this);
        mLayoutShared.setOnClickListener(this);
        mLayoutFriend.setOnClickListener(this);
        mImgUser.setOnClickListener(this);
        mImgModify.setOnClickListener(this);

        presenter = new AvtarPresenterImpl(getActivity(),this);
        presenter.requestUser();

        final GridView gridView = (GridView) view.findViewById(R.id.grd_settings);
        gridView.setAdapter(new MeGridAdapter(gridView.getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemData itemData = (ItemData) gridView.getAdapter().getItem(position);

                try {
                    startWithPop(itemData.getTarget().newInstance());
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if( presenter == null){
            presenter = new AvtarPresenterImpl(getActivity(),this);
        }
        switch (v.getId()){
            case R.id.img_user:
                presenter.gotoLogin();
                break;
            case R.id.ll_photo:
                presenter.gotoGalley();
                break;
            case R.id.ll_share:
                presenter.gotoShare();
                break;
            case R.id.ll_my_friend:
                presenter.gotoContacts();
                break;
            case R.id.btn_modify:
                presenter.gotoEditUser();
                startWithPop(EditUserInfoFragment.newInstance());
                break;
        }
    }

    @Override
    public void onUserBack(AppUser user) {

        if( user == null || !UserBzs.isLogin()){
            mLayoutComponet.setVisibility(View.GONE);
            mImgModify.setVisibility(View.GONE);
        }else{
            mLayoutComponet.setVisibility(View.VISIBLE);
            mImgModify.setVisibility(View.VISIBLE);
            mImgUser.setImageResource(user.getUserImg());
        }
    }
}
