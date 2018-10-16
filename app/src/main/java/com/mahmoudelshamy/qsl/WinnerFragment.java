package com.mahmoudelshamy.qsl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devspark.appmsg.AppMsg;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import datamodels.Cache;
import datamodels.Constants;
import datamodels.Team;
import json.WinnerHandler;
import utils.InternetUtil;
import utils.ViewUtil;
import views.ProgressFragment;

/**
 * Created by Shamyyoun on 2/24/2015.
 */
public class WinnerFragment extends ProgressFragment {
    private AppCompatActivity mActivity;
    private NetworkController mNetworkController;
    private TextView mTextTitle;
    private ImageView mImageDefault;
    private ImageView mImageLogo;
    private Team mWinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initComponents(rootView);

        return rootView;
    }

    /**
     * overridden abstract method, used to set content layout resource
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_winner;
    }

    /**
     * method used to initialize components
     */
    private void initComponents(View rootView) {
        mActivity = (MainActivity) getActivity();
        mNetworkController = NetworkController.getInstance(mActivity);
        mTextTitle = (TextView) rootView.findViewById(R.id.text_title);
        mImageDefault = (ImageView) rootView.findViewById(R.id.image_default);
        mImageLogo = (ImageView) rootView.findViewById(R.id.image_logo);

        // get cached data
        String response = Cache.getWinnersResponse(mActivity);
        if (response != null) {
            try {
                // parse items
                JSONObject jsonObject = new JSONObject(response);
                WinnerHandler handler = new WinnerHandler(jsonObject);
                mWinner = handler.handle();

                // set recycler adapter
                updateUI();
                showMain();

                // load from server to update items
                loadData(false);
            } catch (Exception e) {
                // load from server showing msgs
                loadData(true);
                e.printStackTrace();
            }
        } else {
            // load from server showing msgs
            loadData(true);
        }
    }

    /**
     * method, used to set data in ui
     */
    private void updateUI() {
        // set title
        mTextTitle.setText(mWinner.getTitle());
        // load logo
        if (!mWinner.getLogo().isEmpty()) {
            Picasso.with(mActivity).load(mWinner.getLogo()).into(mImageLogo, new Callback() {
                @Override
                public void onSuccess() {
                    // hide default image
                    ViewUtil.fadeView(mImageDefault, false);
                }

                @Override
                public void onError() {
                    // show default image
                    ViewUtil.fadeView(mImageDefault, true);
                }
            });
        }
    }

    /**
     * method, used to load data from server and handle ui
     */
    private void loadData(final boolean showMsgs) {
        // check internet connection
        if (!InternetUtil.isConnected(mActivity)) {
            if (showMsgs)
                // show error
                showError(R.string.no_internet_connection);
            return;
        }

        // show progress
        showProgress();

        // make & send request
        String url = AppController.getEndPoint(mActivity) + "/Season/Winner/" + AppController.SEASON_ID;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                // handle winner
                WinnerHandler handler = new WinnerHandler(jsonObject);
                mWinner = handler.handle();

                // check winner
                if (mWinner != null) {
                    // update ui
                    updateUI();
                    // cache data
                    Cache.updateWinnersResponse(mActivity, jsonObject.toString());
                    // show main view
                    showMain();
                } else {
                    if (showMsgs)
                        showError(R.string.unexpected_error_try_later);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // show error
                if (showMsgs)
                    showError(R.string.connection_error);
                volleyError.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // add headers to request
                Map<String, String> headers = new HashMap();
                headers.put("Superkoora-Api-Key", AppController.QSL_API_KEY);
                return headers;
            }
        };

        // add request to request queue
        request.setTag(Constants.VOLLEY_REQ_WINNER);
        request.setRetryPolicy(new DefaultRetryPolicy(Constants.CON_TIMEOUT_WINNER,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mNetworkController.getRequestQueue().cancelAll(Constants.VOLLEY_REQ_WINNER);
        mNetworkController.addToRequestQueue(request);
    }

    /**
     * overridden method, used to refresh content
     */
    @Override
    protected void onRefresh() {
        loadData(true);
    }

    /*
     * overridden method
     */
    @Override
    public void onDestroy() {
        // cancel all appmsgs
        AppMsg.cancelAll(mActivity);

        super.onDestroy();
    }
}
