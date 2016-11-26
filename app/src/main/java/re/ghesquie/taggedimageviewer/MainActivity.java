package re.ghesquie.taggedimageviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference itemRef;
    MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        itemRef = FirebaseDatabase.getInstance().getReference().child("rooms");

        adapter = new MainListAdapter(itemRef);
//
//        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
////            @Override
////            public void onItemRangeInserted(int positionStart, int itemCount) {
////                //addBar.setExpanded(false);
////            }
//        });
        recyclerView.setAdapter(adapter);
    }
//
//
//    public void openRoom(View v) {
//        Intent myIntent = new Intent(MainActivity.this, FullscreenActivity.class);
//        myIntent.putExtra("key", adapter.getAdapterPosition()); //Optional parameters
//        MainActivity.this.startActivity(myIntent);
//    }
}
