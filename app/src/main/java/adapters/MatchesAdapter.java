package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahmoudelshamy.qsl.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import datamodels.Match;
import datamodels.MatchesGroup;
import utils.DateUtil;
import utils.ViewUtil;

public class MatchesAdapter extends ArrayAdapter<Match> {
    private Context context;
    private int layoutResourceId;
    private MatchesGroup matchesGroup;
    private final LayoutInflater inflater;

    public MatchesAdapter(Context context, int layoutResourceId, MatchesGroup matchesGroup) {
        super(context, layoutResourceId, matchesGroup.getMatches());
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.matchesGroup = matchesGroup;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.layoutRoot = row;
            holder.imageDefault1 = (ImageView) row.findViewById(R.id.image_default1);
            holder.imageLogo1 = (ImageView) row.findViewById(R.id.image_logo1);
            holder.textTitle1 = (TextView) row.findViewById(R.id.text_title1);
            holder.textStatus = (TextView) row.findViewById(R.id.text_status);
            holder.imageDefault2 = (ImageView) row.findViewById(R.id.image_default2);
            holder.imageLogo2 = (ImageView) row.findViewById(R.id.image_logo2);
            holder.textTitle2 = (TextView) row.findViewById(R.id.text_title2);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        // set row color according to position
        holder.layoutRoot.setBackgroundColor(
                context.getResources().getColor((position % 2) == 0 ? R.color.matches_item_bg1 : R.color.matches_item_bg2));

        // set basic data
        Match match = matchesGroup.getMatches().get(position);
        holder.textTitle1.setText(match.getTeam1().getTitle());
        holder.textTitle2.setText(match.getTeam2().getTitle());
        // check status and set status text
        if (match.getStatus().equals(Match.STATUS_NOT_STARTED) || match.getStatus().equals(Match.STATUS_SOON)) {
            // show time
            holder.textStatus.setText(DateUtil.convertToString(match.getDateTime(), "hh:mm"));
        } else if (match.getStatus().equals(Match.STATUS_FULL_TIME)) {
            // show goals and finished
            holder.textStatus.setText(match.getGoals1() + " - " + match.getGoals2() + "\n" + context.getString(R.string.finished));
        } else {
            // match is running
            // show goals
            holder.textStatus.setText(match.getGoals1() + " - " + match.getGoals2());
        }

        // load logos
        String logo1 = match.getTeam1().getLogo();
        if (!logo1.isEmpty()) {
            Picasso.with(context).load(logo1).into(holder.imageLogo1, new Callback() {
                @Override
                public void onSuccess() {
                    // hide default image
                    ViewUtil.fadeView(holder.imageDefault1, false);
                }

                @Override
                public void onError() {
                    // show default image
                    ViewUtil.fadeView(holder.imageDefault1, true);
                }
            });
        }
        String logo2 = match.getTeam2().getLogo();
        if (!logo2.isEmpty()) {
            Picasso.with(context).load(logo2).into(holder.imageLogo2, new Callback() {
                @Override
                public void onSuccess() {
                    // hide default image
                    ViewUtil.fadeView(holder.imageDefault2, false);
                }

                @Override
                public void onError() {
                    // show default image
                    ViewUtil.fadeView(holder.imageDefault2, true);
                }
            });
        }

        return row;
    }

    static class ViewHolder {
        View layoutRoot;
        ImageView imageDefault1;
        ImageView imageLogo1;
        TextView textTitle1;
        TextView textStatus;
        ImageView imageDefault2;
        ImageView imageLogo2;
        TextView textTitle2;
    }
}
