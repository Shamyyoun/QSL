package com.mahmoudelshamy.qsl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.devspark.appmsg.AppMsg;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.AssistsAdapter;
import datamodels.Assist;
import datamodels.Cache;
import datamodels.Constants;
import json.AssistsHandler;
import utils.InternetUtil;
import views.ProgressFragment;

/**
 * Created by Shamyyoun on 2/24/2015.
 */
public class AssistsFragment extends ProgressFragment {
    private AppCompatActivity mActivity;
    private NetworkController mNetworkController;
    private View mRecyclerHeader;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private AssistsAdapter mAdapter;
    private List<Assist> mAssists;

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
        return R.layout.fragment_assists;
    }

    /**
     * method used to initialize components
     */
    private void initComponents(View rootView) {
        mActivity = (MainActivity) getActivity();
        mNetworkController = NetworkController.getInstance(mActivity);
        mRecyclerHeader = rootView.findViewById(R.id.layout_recyclerHeader);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mAssists = new ArrayList<>();

        // customize recycler view
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        // get cached data
        String response = Cache.getAssistsResponse(mActivity);
        if (response != null) {
            try {
                // parse items
                JSONArray jsonArray = new JSONArray(response);
                AssistsHandler handler = new AssistsHandler(jsonArray);
                mAssists = handler.handle();

                // set recycler adapter
                setRecyclerAdapter();
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
     * method, used to set recycler adapter
     */
    private void setRecyclerAdapter() {
        mAdapter = new AssistsAdapter(mActivity, mAssists, R.layout.recycler_professionals_item);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerHeader.setVisibility(View.VISIBLE);
    }

    /**
     * method, used to load data from server and handle it recycler view
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
        String url = AppController.getEndPoint(mActivity) + "/Season/Players-Assits/"+AppController.SEASON_ID+"/0/99999999999999";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                // handle items
                AssistsHandler handler = new AssistsHandler(jsonArray);
                mAssists = handler.handle();

                // check items
                if (mAssists != null) {
                    // check list size
                    if (mAssists.size() == 0) {
                        if (showMsgs)
                            // show empty
                            showEmpty(R.string.no_top_assists);
                    } else {
                        // set recycler adapter
                        setRecyclerAdapter();
                        // cache data
                        Cache.updateAssistsResponse(mActivity, jsonArray.toString());
                        // show main view
                        showMain();
                    }
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
        request.setTag(Constants.VOLLEY_REQ_ASSISTS);
        request.setRetryPolicy(new DefaultRetryPolicy(Constants.CON_TIMEOUT_ASSISTS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mNetworkController.getRequestQueue().cancelAll(Constants.VOLLEY_REQ_ASSISTS);
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
