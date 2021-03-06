package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahmoudelshamy.qsl.R;

import java.util.List;

import datamodels.Assist;

/**
 * Created by Shamyyoun on 2/8/2015.
 */
public class AssistsAdapter extends RecyclerView.Adapter<AssistsAdapter.ViewHolder> {
    private Context context;
    private List<Assist> data;
    private int layoutResourceId;
    private OnItemClickListener onItemClickListener;

    public AssistsAdapter(Context context, List<Assist> data, int layoutResourceId) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Assist assist = data.get(position);

        // set data
        holder.textPosition.setText((position + 1) + "-");
        holder.textName.setText(assist.getName());
        holder.textTeamName.setText(assist.getTeamName());
        holder.textGoals.setText("" + assist.getAssistsCount());
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
        public TextView textPosition;
        public TextView textName;
        public TextView textTeamName;
        public TextView textGoals;

        public ViewHolder(View v) {
            super(v);
            textPosition = (TextView) v.findViewById(R.id.text_position);
            textName = (TextView) v.findViewById(R.id.text_name);
            textTeamName = (TextView) v.findViewById(R.id.text_teamName);
            textGoals = (TextView) v.findViewById(R.id.text_count);

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