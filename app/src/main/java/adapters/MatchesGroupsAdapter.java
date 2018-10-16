package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mahmoudelshamy.qsl.R;

import java.util.List;

import datamodels.MatchesGroup;
import utils.DateUtil;

/**
 * Created by Shamyyoun on 2/8/2015.
 */
public class MatchesGroupsAdapter extends RecyclerView.Adapter<MatchesGroupsAdapter.ViewHolder> {
    private Context context;
    private List<MatchesGroup> data;
    private int layoutResourceId;
    private OnItemClickListener onItemClickListener;

    public MatchesGroupsAdapter(Context context, List<MatchesGroup> data, int layoutResourceId) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MatchesGroup matchesGroup = data.get(position);

        // get date and format it
        String day = DateUtil.convertToString(matchesGroup.getDate(), "dd");
        if (day.startsWith("0")) {
            day = day.replaceFirst("0", "");
        }
        String month = DateUtil.convertToString(matchesGroup.getDate(), "MM");
        if (month.startsWith("0")) {
            month = month.replaceFirst("0", "");
        }
        String year = DateUtil.convertToString(matchesGroup.getDate(), "yyyy");

        // set date
        String date = day + "/" + month + "/" + year;
        holder.textDate.setText(date);

        // set matches list view
        MatchesAdapter matchesAdapter = new MatchesAdapter(context, R.layout.list_matches_item, matchesGroup);
        holder.listView.setAdapter(matchesAdapter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textDate;
        public ListView listView;

        public ViewHolder(View v) {
            super(v);

            textDate = (TextView) v.findViewById(R.id.text_date);
            listView = (ListView) v.findViewById(R.id.list_matches);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
}