package com.baijia.live.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baijiahulian.live.ui.LiveSDKWithUI;

/**
 * Created by Shubo on 2017/3/20.
 */

public class EntryActivity extends AppCompatActivity {
    //view
    private EditText etAudioRate;
    private TextView tvIsAecSupported;
    private RadioGroup rgAecSetting;
    private Button btnConfirmSetting;

    //data
    private String code;
    private SharedPreferences sp;
    private int sampleRate;
    private boolean enableAec = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        sp = getSharedPreferences("live_temp", Context.MODE_PRIVATE);
        code = sp.getString("code", "nl8t1h");
        ((EditText) findViewById(R.id.activity_entry_join_code)).setText(code);

        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = ((EditText) findViewById(R.id.activity_entry_join_code)).getText().toString();
                String name = ((EditText) findViewById(R.id.activity_entry_name)).getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("code", code);
                editor.apply();

                LiveSDKWithUI.enterRoom(EntryActivity.this, code, name, null);

            }
        });

        initView();
        initListener();
        initData();
    }

    private void initView() {
        etAudioRate = (EditText) findViewById(R.id.et_audio_record_sample_rate);
        tvIsAecSupported = (TextView) findViewById(R.id.tv_audio_is_aec_supported);
        rgAecSetting = (RadioGroup) findViewById(R.id.rg_audio_debug_enable_aec);
        btnConfirmSetting = (Button) findViewById(R.id.btn_audio_setting_confirm);
    }

    private void initListener() {
        rgAecSetting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.aec_yes) {
                    enableAec = true;
                } else {
                    enableAec = false;
                }
            }
        });
        btnConfirmSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etAudioRate.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    sampleRate = 0;
                } else {
                    sampleRate = Integer.valueOf(s);
                }
//                LivePlayer.setAudiorecordSampleRate(sampleRate);
//                LivePlayer.enbaleAEC(enableAec);
                Toast.makeText(EntryActivity.this, "已设置音频信息，采样率：" + String.valueOf(sampleRate) + "  是否开硬件消回音：" + enableAec, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData() {
//        if (LivePlayer.isAecSupported()) {
//            tvIsAecSupported.setText("当前手机是否支持硬件消除回音: 支持");
//        } else {
//            tvIsAecSupported.setText("当前手机是否支持硬件消除回音: 不支持");
//        }
    }
}
