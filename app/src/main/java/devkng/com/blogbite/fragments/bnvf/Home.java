package devkng.com.blogbite.fragments.bnvf;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.activity.Search.Search;
import dkprojects.com.blogbite.activity.profile.Profile_one;
import dkprojects.com.blogbite.adaptor.Card_adaptor;
import dkprojects.com.blogbite.model.Card;
import dkprojects.com.blogbite.others.Uitlity;

/* loaded from: classes7.dex */
public class Home extends Fragment {
    Card card;
    private Card_adaptor card_adaptor;
    private FirebaseUser currentUser;
    private String getFb_account;
    private String getFb_account_name;
    private String getFb_account_profile_url;
    private ImageView img_toolbar;
    private ConstraintLayout menu_profile_contain;
    private ConstraintLayout menu_search_contain;
    private ImageView profile;
    private RecyclerView recyclerView;
    private ImageView search;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.bnvf_home, container, false);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.view_page_recycler_view);
        this.profile = (ImageView) view.findViewById(R.id.tool_profile_menu);
        this.search = (ImageView) view.findViewById(R.id.profile_close_imgview);
        this.menu_search_contain = (ConstraintLayout) view.findViewById(R.id.profile_close_image);
        this.menu_profile_contain = (ConstraintLayout) view.findViewById(R.id.tool_profile_menu_contain);
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
        lodeData();
        setupRecyclerView();
        set_toolbar_menu();
        return view;
    }

    private void set_toolbar_menu() {
        lodeData();
        lodeImage();
        this.menu_profile_contain.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.fragments.bnvf.-$$Lambda$Home$_iGCuiWDuWD5uJ_dwfvBEpTCiSw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Home.this.lambda$set_toolbar_menu$0$Home(view);
            }
        });
        this.menu_search_contain.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.fragments.bnvf.-$$Lambda$Home$AOu_RriiHhLdtI8oe284aIlW1Cc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Home.this.lambda$set_toolbar_menu$1$Home(view);
            }
        });
    }

    public /* synthetic */ void lambda$set_toolbar_menu$0$Home(View view) {
        Intent i = new Intent(getActivity(), Profile_one.class);
        lodeData();
        i.putExtra("getFb_account_name", this.getFb_account_name);
        i.putExtra("getFb_account_profile_url", this.getFb_account_profile_url);
        startActivity(i);
    }

    public /* synthetic */ void lambda$set_toolbar_menu$1$Home(View view) {
        Intent i = new Intent(getActivity(), Search.class);
        startActivity(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lodeImage() {
        Glide.with(getContext()).load(this.getFb_account_profile_url).placeholder((int) R.drawable.account_for_text).error(R.drawable.account_for_text).diskCacheStrategy(DiskCacheStrategy.ALL).into(this.profile);
    }

    private void lodeData() {
        FirebaseFirestore.getInstance().collection("Accounts").document(this.currentUser.getEmail()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() { // from class: dkprojects.com.blogbite.fragments.bnvf.Home.2
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    Home.this.getFb_account_name = "error in loading";
                    Uitlity.showToast(Home.this.getContext(), "error in loading");
                    return;
                }
                Home.this.getFb_account_name = documentSnapshot.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                Home.this.getFb_account_profile_url = documentSnapshot.getString("photo");
                Home.this.lodeImage();
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: dkprojects.com.blogbite.fragments.bnvf.Home.1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception e) {
            }
        });
    }

    private void setupRecyclerView() {
        Query query = Uitlity.getCollectionReferenceForDatabasePage();
        FirestoreRecyclerOptions<Card> options = new FirestoreRecyclerOptions.Builder().setQuery(query, Card.class).build();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Card_adaptor card_adaptor = new Card_adaptor(options, getContext());
        this.card_adaptor = card_adaptor;
        this.recyclerView.setAdapter(card_adaptor);
        this.recyclerView.setHasFixedSize(true);
        this.card_adaptor.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.card_adaptor.startListening();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.card_adaptor.startListening();
    }
}