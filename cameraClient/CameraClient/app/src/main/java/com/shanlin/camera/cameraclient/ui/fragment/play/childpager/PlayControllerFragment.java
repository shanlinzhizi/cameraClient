package com.shanlin.camera.cameraclient.ui.fragment.play.childpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.event.PlayControllEvent;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by APhil on 16/7/21.
 */
public class PlayControllerFragment extends SupportFragment implements View.OnClickListener{

    public static PlayControllerFragment newInstance(){

        return new PlayControllerFragment();
    }

    private Button vCapture;
    private Button btnPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_controller, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        vCapture = (Button) view.findViewById(R.id.btn_capture);
        btnPhone = (Button) view.findViewById(R.id.btn_phone);
        vCapture.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_capture:
                EventBus.getDefault().post(new PlayControllEvent(1));
                break;

            case R.id.btn_phone:
                EventBus.getDefault().post(new PlayControllEvent(2));
                break;
        }
    }


}
