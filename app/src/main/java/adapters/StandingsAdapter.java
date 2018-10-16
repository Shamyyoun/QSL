package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahmoudelshamy.qsl.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import datamodels.Standing;
import utils.ViewUtil;

/**
 * Created by Shamyyoun on 2/8/2015.
 */
public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.ViewHolder> {
    private Context context;
    private List<Standing> data;
    private int layoutResourceId;
    private OnItemClickListener onItemClickListener;

    public StandingsAdapter(Context context, List<Standing> data, int layoutResourceId) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Standing standing = data.get(position);

        // set data
        holder.textTitle.setText(standing.getTeam().getTitle());
        holder.textGP.setText("" + standing.getPlayed());
        holder.textW.setText("" + standing.getWon());
        holder.textD.setText("" + standing.getDrawn());
        holder.textL.setText("" + standing.getLost());
        holder.textPts.setText("" + standing.getPoints());
        holder.textGS.setText("" + standing.getGoalsFor());
        holder.textGC.setText("" + standing.getGoalsAgainst());
        holder.textGD.setText("" + standing.getGoalsDifference());

        // load logo
        String logo = standing.getTeam().getLogo();
        if (!logo.isEmpty()) {
            Picasso.with(context).load(logo).into(holder.imageLogo, new Callback() {
                @Override
                public void onSuccess() {
                    // hide default image
                    ViewUtil.fadeView(holder.imageDefault, false);
                }

                @Override
                public void onError() {
                    // show default image
                    ViewUtil.fadeView(holder.imageDefault, true);
                }
            });
        }
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
        public ImageView imageDefault;
        public ImageView imageLogo;
        public TextView textTitle;
        public TextView textGP;
        public TextView textW;
        public TextView textD;
        public TextView textL;
        public TextView textPts;
        public TextView textGS;
        public TextView textGC;
        public TextView textGD;

        public ViewHolder(View v) {
            super(v);
            imageDefault = (ImageView) v.findViewById(R.id.image_default);
            imageLogo = (ImageView) v.findViewById(R.id.image_logo);
            textTitle = (TextView) v.findViewById(R.id.text_title);
            textGP = (TextView) v.findViewById(R.id.text_gp);
            textW = (TextView) v.findViewById(R.id.text_w);
            textD = (TextView) v.findViewById(R.id.text_d);
            textL = (TextView) v.findViewById(R.id.text_l);
            textPts = (TextView) v.findViewById(R.id.text_pts);
            textGS = (TextView) v.findViewById(R.id.text_gs);
            textGC = (TextView) v.findViewById(R.id.text_gc);
            textGD = (TextView) v.findViewById(R.id.text_gd);

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