package com.adoubo.biyingdailyimagedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adoubo.biyingdailyimagedemo.bean.BingBean;
import com.adoubo.biyingdailyimagedemo.bean.OpenApiBean;
import com.adoubo.biyingdailyimagedemo.bean.TestIcibaBean;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.adoubo.biyingdailyimagedemo.bean.OpenApiBean.RETURN_BING;
import static com.adoubo.biyingdailyimagedemo.bean.OpenApiBean.RETURN_ICIBA;

/**
 * @author: adoubo
 * @date: 2019/1/27
 * Description:
 */
public class IcibaFragment extends Fragment {

    private static final String TAG = IcibaFragment.class.getSimpleName();

    private TextView contentTextView;
    private TextView noteTextView;
    private TextView translationTextView;
    private TestIcibaBean testIcibaBean = new TestIcibaBean();
    private IcibaHandler handler = new IcibaHandler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iciba, container, false);
        if (null == savedInstanceState) {
            contentTextView = view.findViewById(R.id.iciba_content_textView);
            noteTextView = view.findViewById(R.id.iciba_note_textView);
            translationTextView = view.findViewById(R.id.iciba_translation_textView);
            getIcibaJson();
        }
        return view;
    }

    private void getIcibaJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = OkHttpHelper.getInstance().get(OpenApiBean.API_ICIBA);
                    Log.i(TAG, result);
                    parseJson(result);
                    Message msg = Message.obtain();
                    msg.what = RETURN_ICIBA;
                    msg.obj = testIcibaBean;
                    handler.handleMessage(msg);
                } catch (IOException ioe) {
                    Log.i("cwx", "IOException: " + ioe);
                }

            }
        }).start();
    }

    private TestIcibaBean parseJson(String jsonStr) {
        try {
            JSONObject icibaJson = new JSONObject(jsonStr);
            testIcibaBean.setContent(icibaJson.getString("content"));
            testIcibaBean.setNote(icibaJson.getString("note"));
            testIcibaBean.setTranslation(icibaJson.getString("translation"));
            testIcibaBean.setPicture(icibaJson.getString("picture"));
            testIcibaBean.setPicture2(icibaJson.getString("picture2"));
            testIcibaBean.setFenxiang_img(icibaJson.getString("fenxiang_img"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return testIcibaBean;
    }

    class IcibaHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            if (msg.what == RETURN_ICIBA) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        TestIcibaBean myTestIcibaBean = (TestIcibaBean) msg.obj;
                        contentTextView.setText(myTestIcibaBean.getContent());
                        noteTextView.setText(myTestIcibaBean.getNote());
                        translationTextView.setText(myTestIcibaBean.getTranslation());
                    }
                });
            }
        }
    }
}
