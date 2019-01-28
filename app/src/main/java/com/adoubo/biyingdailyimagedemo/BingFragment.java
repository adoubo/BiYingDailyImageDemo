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
import android.widget.ImageView;
import android.widget.TextView;

import com.adoubo.biyingdailyimagedemo.bean.BingBean;
import com.adoubo.biyingdailyimagedemo.bean.OpenApiBean;
import com.adoubo.biyingdailyimagedemo.bean.TestBingBean;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.adoubo.biyingdailyimagedemo.bean.OpenApiBean.RETURN_BING;

/**
 * @author: adoubo
 * @date: 2019/1/27
 * Description:
 */
public class BingFragment extends Fragment {

    private static final String TAG = BingFragment.class.getSimpleName();

    private ImageView imageView;
    private TextView textView;
    private BingHandler handler = new BingHandler();
    private TestBingBean testBingBean = new TestBingBean();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bing, container, false);
        if (null == savedInstanceState) {
            imageView = view.findViewById(R.id.imageView);
            textView = view.findViewById(R.id.copyright_textView);
            getBingJson();
        }
        return view;
    }

    private void getBingJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = OkHttpHelper.getInstance().get(OpenApiBean.API_BING);
                    Log.i(TAG, result);
                    parseJson(result);
                    Message msg = Message.obtain();
                    msg.what = RETURN_BING;
                    msg.obj = testBingBean;
                    handler.handleMessage(msg);
                } catch (IOException ioe) {
                    Log.i("cwx", "IOException: " + ioe);
                }

            }
        }).start();
    }

    private TestBingBean parseJson(String jsonStr) {
        try {
            JSONObject imagesRoot = new JSONObject(jsonStr);
            JSONArray array = imagesRoot.getJSONArray("images");
            for (int i = 0; i < array.length(); i++) {
                JSONObject img = array.getJSONObject(i);
                testBingBean.setStartdate(img.getString("startdate"));
                testBingBean.setUrl(img.getString("url"));
                testBingBean.setCopyright(img.getString("copyright"));
                testBingBean.setCopyrightlink(img.getString("copyrightlink"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return testBingBean;
    }

    class BingHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            if (msg.what == RETURN_BING) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        TestBingBean myTestBingBean = (TestBingBean) msg.obj;
                        Picasso.get().load(myTestBingBean.getImageUrl()).into(imageView);
                        textView.setText(myTestBingBean.getCopyright());
                    }
                });
            }
        }
    }
}
