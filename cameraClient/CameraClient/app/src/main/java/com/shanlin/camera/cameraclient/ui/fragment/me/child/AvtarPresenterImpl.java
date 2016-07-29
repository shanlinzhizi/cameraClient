package com.shanlin.camera.cameraclient.ui.fragment.me.child;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.MediaStore;

import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.net.UserBzs;
import com.shanlin.camera.cameraclient.ui.fragment.me.LoginActivity;

import java.io.File;

/**
 * Created by APhil on 16/7/29.
 */
public class AvtarPresenterImpl implements AvatarPresenter {

    Context context;
    private ViewInteractive viewInteractive;

    public AvtarPresenterImpl(Context context,ViewInteractive viewInteractive){
        this.context = context;
        this.viewInteractive = viewInteractive;
    }

    public void gotoLogin(){
//        if( !UserBzs.isLogin()){
            context.startActivity(new Intent(context,LoginActivity.class));
//        }
    }

    public void gotoGalley(){
        Intent intent = new Intent(Intent.ACTION_PICK);// 打开相册
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        File tempFile = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "Photo");
        intent.putExtra("output", Uri.fromFile(tempFile));
        context.startActivity(intent);
    }

    public void gotoShare(){
        String msgTitle = "share test";
        String msgText = "test share";
        String activityTitle = "all people";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain"); // 纯文本
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }

    public void gotoContacts(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(Contacts.People.CONTENT_URI);
        context.startActivity(intent);
    }

    @Override
    public void requestUser() {
        AppUser user = UserBzs.getAppUser();
        if(user == null){
            gotoLogin();
            return;
        }

        UserBzs.autoLogin();
        viewInteractive.onUserBack(user);
    }

    @Override
    public void gotoEditUser() {

    }


}
