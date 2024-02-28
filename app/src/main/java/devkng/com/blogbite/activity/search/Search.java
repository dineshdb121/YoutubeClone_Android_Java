package devkng.com.blogbite.activity.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.adaptor.SearchAdaptor;
import dkprojects.com.blogbite.model.Search_model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes10.dex */
public class Search extends AppCompatActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    SearchAdaptor adapter;
    private ImageView back;
    private ImageView close;
    private CollectionReference collref;
    private int count;
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private TextInputEditText edt_search;
    private String fb_no_of_time;
    private String fb_search;
    private FirebaseFirestore firestore;
    private ArrayList<Search_model> list;
    private RecyclerView rv;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        this.back = (ImageView) findViewById(R.id.search_back);
        TextInputEditText textInputEditText = (TextInputEditText) findViewById(R.id.edt_search);
        this.edt_search = textInputEditText;
        textInputEditText.setSingleLine(true);
        this.edt_search.setImeOptions(3);
        this.close = (ImageView) findViewById(R.id.search_close);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        this.firestore = firebaseFirestore;
        this.collref = firebaseFirestore.collection("Search");
        onTextChange();
        ime_click();
        this.back.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$Search$B_yrWlaZZe0n54WLVXHXd8Q3SAo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Search.this.lambda$onCreate$0$Search(view);
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$Search$LS91Tkpv2949IJXoDlWlJf-ktNk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Search.this.lambda$onCreate$1$Search(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$Search(View view) {
        finish();
    }

    public /* synthetic */ void lambda$onCreate$1$Search(View view) {
        this.edt_search.setText((CharSequence) null);
    }

    private void fillExampleList() {
        this.list = new ArrayList<>();
        String st = this.edt_search.getText().toString().toLowerCase().trim();
        FirebaseFirestore.getInstance().collection("Search").orderBy("searched_text").startAt(st).endAt(st + "\uf8ff").get().addOnSuccessListener(new OnSuccessListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$Search$6f5Pq-zyXbNowDI35yay4c9jgFA
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                Search.this.lambda$fillExampleList$2$Search((QuerySnapshot) obj);
            }
        });
    }

    public /* synthetic */ void lambda$fillExampleList$2$Search(QuerySnapshot queryDocumentSnapshots) {
        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
        for (DocumentSnapshot ds : dsList) {
            dsList.size();
            Search_model modelClass = (Search_model) ds.toObject(Search_model.class);
            if (modelClass == null) {
                throw new AssertionError();
            }
            this.list.add(modelClass);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_sujession_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    private void load_Data() {
        this.list = new ArrayList<>();
        this.edt_search.getText().toString().toLowerCase().trim();
        FirebaseFirestore.getInstance().collection("Search").orderBy("searched_text").get().addOnSuccessListener(new OnSuccessListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$Search$p97_uWuswJrk1W_rve-Nb0JTUuw
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                Search.this.lambda$load_Data$3$Search((QuerySnapshot) obj);
            }
        });
    }

    public /* synthetic */ void lambda$load_Data$3$Search(QuerySnapshot queryDocumentSnapshots) {
        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
        for (DocumentSnapshot ds : dsList) {
            dsList.size();
            Search_model modelClass = (Search_model) ds.toObject(Search_model.class);
            if (modelClass == null) {
                throw new AssertionError();
            }
            this.list.add(modelClass);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_sujession_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.adapter = new SearchAdaptor(this, this.list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    private void ime_click() {
        this.edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: dkprojects.com.blogbite.activity.Search.Search.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3 || i == 6 || i == 0) {
                    Search.this.searchAction();
                    Search.this.list.clear();
                    Search.this.lodeData();
                    return true;
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchAction() {
        Search_model s = new Search_model();
        String text = this.edt_search.getText().toString();
        s.setSearched_text(text);
        s.setNo_of_time(1);
        save_search(s);
        this.list.clear();
        load_Data();
        Intent i = new Intent(this, SearchResults.class);
        i.putExtra("text", this.edt_search.getText().toString());
        startActivity(i);
    }

    private void onTextChange() {
        this.edt_search.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.activity.Search.Search.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Search.this.close.setVisibility(4);
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Search.this.close.setVisibility(0);
                if (Search.this.adapter.getItemCount() >= 0) {
                    Search.this.adapter.getFilter().filter(charSequence);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void save_search(Search_model searchModel) {
        DocumentReference dr1 = FirebaseFirestore.getInstance().collection("Search").document(String.valueOf(this.edt_search.getText().toString().hashCode()));
        dr1.set(searchModel).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.activity.Search.-$$Lambda$Search$Ed_MKFr7VtqMaPxsiJwhrvi1KLA
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Search.this.lambda$save_search$4$Search(task);
            }
        });
        DocumentReference dr2 = FirebaseFirestore.getInstance().collection("Search").document(String.valueOf(this.edt_search.getText().toString().hashCode())).collection("no_of_users").document((String) Objects.requireNonNull(this.currentUser.getEmail()));
        HashMap<String, String> h = new HashMap<>();
        h.put("user", this.currentUser.getEmail());
        dr2.set(h).addOnCompleteListener($$Lambda$Search$9i79soLbnnxp88FmR0fySITm17s.INSTANCE);
        DocumentReference dr3 = FirebaseFirestore.getInstance().collection("Search").document(String.valueOf(this.edt_search.getText().toString().hashCode())).collection("no_of_times").document();
        HashMap<String, String> h1 = new HashMap<>();
        h.put("user", this.currentUser.getEmail());
        dr3.set(h1).addOnCompleteListener($$Lambda$Search$pagFhlJRNRG0jRp7x2wWQp4ZDe8.INSTANCE);
    }

    public /* synthetic */ void lambda$save_search$4$Search(Task task) {
        if (task.isSuccessful()) {
            this.edt_search.setText((CharSequence) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lodeData() {
        for (int i = 0; i < 2; i++) {
            FirebaseFirestore.getInstance().collection("Search_model").document(this.edt_search.getText().toString()).collection("no_of_users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { // from class: dkprojects.com.blogbite.activity.Search.Search.3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Search.this.count = task.getResult().size();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        load_Data();
        this.edt_search.requestFocus();
        getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        finish();
    }
}