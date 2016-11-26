package re.ghesquie.taggedimageviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

/**
 * Created by mike on 22/11/2016.
 */

public class MainListAdapter extends FirebaseRecyclerAdapter<TaggedImageItem, MainListAdapter.roomViewHolder> {

    private DatabaseReference roomsRef;

    public static class roomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private final Context context;
        public ImageView roomImage;

        public roomViewHolder(View v){
            super(v);

            context = v.getContext();
            roomImage = (ImageView) itemView.findViewById(R.id.roomImage);
            roomImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, FullscreenActivity.class);
            intent.putExtra("index", getAdapterPosition());
            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    public MainListAdapter (DatabaseReference ref){
        super(TaggedImageItem.class, R.layout.room_recycler_view_item, MainListAdapter.roomViewHolder.class, ref);
        roomsRef = ref;
    }

    @Override
    public roomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_recycler_view_item,parent,false);
        return new roomViewHolder(v);
    }

    protected void populateViewHolder(final roomViewHolder holder, final TaggedImageItem model, final int position) {
        Picasso.with(holder.roomImage.getContext()).load(model.image).into(holder.roomImage);
    }
}
