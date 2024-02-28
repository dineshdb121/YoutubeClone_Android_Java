package devkng.com.blogbite.curd.view.page;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.adaptor.Page_adaptor;
import dkprojects.com.blogbite.interfaces.RecyclerViewInterface;
import dkprojects.com.blogbite.model.Page;
import dkprojects.com.blogbite.others.Uitlity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class View_page extends AppCompatActivity {
    private Page_adaptor adaptor;
    private String cardId;
    private String channelUid;
    private List<Page> list_page_model;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerViewInterface recyclerViewInterface;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page);
        statusBarColor();
        this.cardId = getIntent().getStringExtra("cardId");
        this.channelUid = getIntent().getStringExtra("channelUid");
        this.recyclerView = (RecyclerView) findViewById(R.id.view_page_recycler_view);
        this.progressBar = (ProgressBar) findViewById(R.id.view_page_progressbar);
        this.list_page_model = new ArrayList();
        this.adaptor = new Page_adaptor(this.list_page_model, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adaptor);
        this.adaptor.add(this.list_page_model);
        this.adaptor.notifyDataSetChanged();
        if (this.list_page_model.size() == 0) {
            this.progressBar.setVisibility(0);
        }
        new Handler().postDelayed(new Runnable() { // from class: dkprojects.com.blogbite.curd.view.page.-$$Lambda$View_page$tZh6NkZLkUAHhhiK7OO4QpLmXnc
            @Override // java.lang.Runnable
            public final void run() {
                View_page.this.lambda$onCreate$0$View_page();
            }
        }, 2500L);
        FirebaseFirestore.getInstance().collection("Database").document(this.channelUid).collection("page").document(this.channelUid).collection("card").document(this.cardId).collection(FirebaseAnalytics.Param.CONTENT).orderBy("itemCount_Order_By", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener() { // from class: dkprojects.com.blogbite.curd.view.page.-$$Lambda$View_page$S2sFF1A-k5UCw4isKPCDEQAgQIY
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                View_page.this.lambda$onCreate$1$View_page((QuerySnapshot) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$View_page() {
        if (this.list_page_model.size() == 0) {
            this.progressBar.setVisibility(0);
            Uitlity.showToast(this, "Error in Loading...!\n Swipe down to refresh");
        }
    }

    /* renamed from: dkprojects.com.blogbite.curd.view.page.View_page$1 */
    /* loaded from: classes5.dex */
    class AnonymousClass1 implements OnFailureListener {
        AnonymousClass1() {
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(Exception e) {
            Uitlity.showToast(View_page.this, "error");
        }
    }

    public /* synthetic */ void lambda$onCreate$1$View_page(QuerySnapshot queryDocumentSnapshots) {
        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
        for (DocumentSnapshot ds : dsList) {
            Page modelClass = (Page) ds.toObject(Page.class);
            this.list_page_model.add(modelClass);
            this.adaptor.notifyDataSetChanged();
            this.list_page_model.size();
            this.progressBar.setVisibility(8);
        }
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
}