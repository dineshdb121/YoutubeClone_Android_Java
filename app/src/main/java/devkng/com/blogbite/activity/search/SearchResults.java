package devkng.com.blogbite.activity.search;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.adaptor.Search_result_adaptor;
import dkprojects.com.blogbite.model.Card;
import dkprojects.com.blogbite.others.Uitlity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class SearchResults extends AppCompatActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Search_result_adaptor adapter;
    private ImageView back;
    private ImageView close;
    private ImageView filter;
    private ArrayList<Card> list;
    private RecyclerView rv;
    private String s;
    private EditText search;
    private int size;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        statusBarColor();
        this.back = (ImageView) findViewById(R.id.searchResult_back);
        this.close = (ImageView) findViewById(R.id.searchResult_close);
        this.filter = (ImageView) findViewById(R.id.searchResult_filter);
        this.rv = (RecyclerView) findViewById(R.id.searchResult_sujession_recyclerview);
        this.search = (EditText) findViewById(R.id.edt_searchResult);
        this.s = getIntent().getStringExtra("text");
        this.search.setSingleLine(true);
        this.back.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$SearchResults$t8vc2O4V10lMe_9nlfxe5rPoc_A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchResults.this.lambda$onCreate$0$SearchResults(view);
            }
        });
        loop();
        String str = this.s;
        if (str != null) {
            this.search.setText(str);
            searchResults();
            return;
        }
        finish();
    }

    public /* synthetic */ void lambda$onCreate$0$SearchResults(View view) {
        finish();
    }

    private void loop() {
        new Handler().postDelayed(new Runnable() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$SearchResults$D1w60GGT9M878Ke8zmJUlCAIjk0
            @Override // java.lang.Runnable
            public final void run() {
                SearchResults.this.lambda$loop$1$SearchResults();
            }
        }, 2500L);
    }

    public /* synthetic */ void lambda$loop$1$SearchResults() {
        if (this.size != 0) {
            load_Data();
        }
    }

    private void searchResults() {
        load_Data();
    }

    private void load_Data() {
        this.list = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("Public").get().addOnSuccessListener(new OnSuccessListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$SearchResults$4nSPBeXc1d984-2AHL4S9HEMUV8
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                SearchResults.this.lambda$load_Data$2$SearchResults((QuerySnapshot) obj);
            }
        });
    }

    public /* synthetic */ void lambda$load_Data$2$SearchResults(QuerySnapshot queryDocumentSnapshots) {
        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
        for (DocumentSnapshot ds : dsList) {
            this.size = dsList.size();
            Card modelClass = (Card) ds.toObject(Card.class);
            if (modelClass == null) {
                throw new AssertionError();
            }
            this.list.add(modelClass);
            Uitlity.showToast(this, String.valueOf(this.size) + this.list.size());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchResult_sujession_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.adapter = new Search_result_adaptor(this, this.list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
        if (this.size == this.list.size()) {
            filtered();
        }
    }

    private void filtered() {
        this.adapter.getFilter().filter(this.s);
        this.adapter.notifyDataSetChanged();
    }

    private void statusBarColor() {
        int currentNightMode = getResources().getConfiguration().uiMode & 48;
        switch (currentNightMode) {
            case 16:
                Window window = getWindow();
                View view = window.getDecorView();
                new WindowInsetsControllerCompat(window, view).setAppearanceLightStatusBars(true);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        load_Data();
        this.s = getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}