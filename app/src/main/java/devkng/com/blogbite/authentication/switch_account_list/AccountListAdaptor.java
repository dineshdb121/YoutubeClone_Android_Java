package devkng.com.blogbite.authentication.switch_account_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import dkprojects.com.blogbite.MainActivity;
import dkprojects.com.blogbite.R;
import io.realm.RealmResults;

/* loaded from: classes4.dex */
public class AccountListAdaptor extends RecyclerView.Adapter<AccountListAdaptor.MyViewHolder> {
    Context context;
    String e;
    String img;
    RealmResults<Login_accounts_list_model> list;
    String p;

    public AccountListAdaptor(Context context, RealmResults<Login_accounts_list_model> list) {
        this.context = context;
        this.list = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.dialog_switch_accounts_list_item, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Login_accounts_list_model model = (Login_accounts_list_model) this.list.get(position);
        holder.e.setText(model.getE());
        this.e = model.getE();
        this.p = model.getP();
        FirebaseFirestore.getInstance().collection("Accounts").document(model.getE()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() { // from class: dkprojects.com.blogbite.authentication.switch_account_list.AccountListAdaptor.2
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String fb_name = documentSnapshot.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                    AccountListAdaptor.this.img = documentSnapshot.getString("photo");
                    holder.n.setText(fb_name);
                    Glide.with(AccountListAdaptor.this.context).load(AccountListAdaptor.this.img).placeholder((int) R.drawable.account_for_text).error(R.drawable.account_for_text).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img);
                    return;
                }
                holder.n.setText("error in loading");
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: dkprojects.com.blogbite.authentication.switch_account_list.AccountListAdaptor.1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception e) {
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.switch_account_list.-$$Lambda$AccountListAdaptor$cmP04xVQ-xNprplRsgRj0dECAF0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountListAdaptor.this.lambda$onBindViewHolder$1$AccountListAdaptor(model, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$AccountListAdaptor(Login_accounts_list_model model, View view) {
        for (int n = 0; n < 2; n++) {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseAuth.getInstance().signOut();
            if (currentUser == null) {
                firebaseAuth.signInWithEmailAndPassword(model.getE(), model.getP()).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.authentication.switch_account_list.-$$Lambda$AccountListAdaptor$VrPtrLHRexi3q0f7C1sjXvHn1QU
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        AccountListAdaptor.this.lambda$onBindViewHolder$0$AccountListAdaptor(task);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$AccountListAdaptor(Task task) {
        if (task.isSuccessful()) {
            Intent i = new Intent(this.context, MainActivity.class);
            i.setFlags(67108864);
            this.context.startActivity(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* loaded from: classes4.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView e;
        ImageView img;
        TextView n;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.e = (TextView) itemView.findViewById(R.id.dsali_mailId);
            this.n = (TextView) itemView.findViewById(R.id.dsalia_name_inside);
            this.img = (ImageView) itemView.findViewById(R.id.dsali_imageview);
        }
    }
}