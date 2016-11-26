package re.ghesquie.taggedimageviewer;

import android.content.Context;
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

    private TaggedImageView taggedView;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton imageView;
        public TextView titleText;
        public TextView priceText;
        public TextView infoText;
        public TextView descText;
        private final Context context;

        public ViewHolder(View v) {
            super(v);

            context = v.getContext();
            imageView = (ImageButton) itemView.findViewById(R.id.tag_image);
            titleText = (TextView) itemView.findViewById(R.id.tag_title_text);
            priceText = (TextView) itemView.findViewById(R.id.tag_price_text);
            infoText = (TextView) itemView.findViewById(R.id.tag_info_text);
            descText = (TextView) itemView.findViewById(R.id.tag_description_text);
        }
    }

    public TagListAdapter (DatabaseReference ref, TaggedImageView view){
        super(TagItem.class, R.layout.tag_recycler_view_item, TagListAdapter.ViewHolder.class, ref);
        taggedView = view;
    }

    @Override
    protected void populateViewHolder(TagListAdapter.ViewHolder holder, TagItem model, int position) {
        Picasso.with(holder.imageView.getContext()).load(model.image).into(holder.imageView);
        holder.titleText.setText(model.title);
        holder.priceText.setText(model.price);
        holder.infoText.setText(model.info);
        holder.descText.setText(model.desc);

        // Might have issues getting added multiple times.
        taggedView.tags.add(position, model);
        taggedView.invalidate();
    }
}
