package re.ghesquie.taggedimageviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

/**
 * Created by mike on 25/11/2016.
 */

public class TagListAdapter extends FirebaseRecyclerAdapter<TagItem, TagListAdapter.ViewHolder> {

    private Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton imageView;
        public TextView titleText;
        public TextView priceText;
        public TextView infoText;
        public TextView descText;

        public ViewHolder(View v) {
            super(v);

            imageView = (ImageButton) itemView.findViewById(R.id.tag_image);
            titleText = (TextView) itemView.findViewById(R.id.tag_title_text);
            priceText = (TextView) itemView.findViewById(R.id.tag_price_text);
            infoText = (TextView) itemView.findViewById(R.id.tag_info_text);
            descText = (TextView) itemView.findViewById(R.id.tag_description_text);
        }
    }

    public TagListAdapter (DatabaseReference ref, Activity act){
        super(TagItem.class, R.layout.tag_recycler_view_item, TagListAdapter.ViewHolder.class, ref);
        activity = act;
    }

    @Override
    protected void populateViewHolder(TagListAdapter.ViewHolder holder, final TagItem model, int position) {
        Picasso.with(holder.imageView.getContext()).load(model.image).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.link));
                activity.startActivity(browserIntent);
            }
        });
        holder.titleText.setText(model.title);
        holder.priceText.setText(model.price);
        holder.infoText.setText(model.info);
        holder.descText.setText(model.desc);
    }
}
