package devkng.com.blogbite.activity.profile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.activity.channel.Channel_dashbord;
import dkprojects.com.blogbite.authentication.Login_username;
import dkprojects.com.blogbite.authentication.switch_account_list.AccountListAdaptor;
import dkprojects.com.blogbite.authentication.switch_account_list.Login_accounts_list_model;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/* loaded from: classes2.dex */
public class Profile_one extends AppCompatActivity {
    private AccountListAdaptor adaptor;
    private View add_account;
    private ConstraintLayout close;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private Dialog dialog;
    private String getFb_account_name;
    private String getFb_account_profile_url;
    private String in_img_url;
    private String in_name;
    private RealmResults<Login_accounts_list_model> list;
    private ImageView profile;
    private TextView psa_name;
    private Realm realm;
    private RecyclerView recyclerView;
    private View switch_account;
    private View your_channel;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_one);
        statusBarColor();
        findId();
        onclick();
        this.in_name = this.getFb_account_name;
        this.getFb_account_name = getIntent().getStringExtra("getFb_account_name");
        this.getFb_account_profile_url = getIntent().getStringExtra("getFb_account_profile_url");
        lodeImage();
        this.psa_name.setText(this.getFb_account_name);
        Realm.init(this);
        Realm defaultInstance = Realm.getDefaultInstance();
        this.realm = defaultInstance;
        RealmResults<Login_accounts_list_model> m = defaultInstance.where(Login_accounts_list_model.class).findAll();
        m.sort("e", Sort.ASCENDING);
        this.adaptor = new AccountListAdaptor(this, m);
        m.addChangeListener(new RealmChangeListener<RealmResults<Login_accounts_list_model>>() { // from class: dkprojects.com.blogbite.activity.profile.Profile_one.1
            public void onChange(RealmResults<Login_accounts_list_model> login_accounts_list_models) {
                Profile_one.this.adaptor.notifyDataSetChanged();
            }
        });
    }

    private void lodeImage() {
        Glide.with((FragmentActivity) this).load(this.getFb_account_profile_url).placeholder((int) R.drawable.account_for_text).error(R.drawable.account_for_text).diskCacheStrategy(DiskCacheStrategy.ALL).into(this.profile);
    }

    private void findId() {
        this.close = (ConstraintLayout) findViewById(R.id.profile_close_image_contain);
        this.add_account = findViewById(R.id.profile_add_account);
        this.your_channel = findViewById(R.id.profileyour_channel);
        this.switch_account = findViewById(R.id.profile_switch_account);
        this.psa_name = (TextView) findViewById(R.id.psa_name);
        this.profile = (ImageView) findViewById(R.id.profile_swich_image);
    }

    private void onclick() {
        this.close.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.profile.-$$Lambda$Profile_one$fHrNrN7dn9dkDIk2dHvQHofRUzI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Profile_one.this.lambda$onclick$0$Profile_one(view);
            }
        });
        this.add_account.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.profile.-$$Lambda$Profile_one$fKUTNdAFqgX8BjsuEEHg6lcwnno
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Profile_one.this.lambda$onclick$1$Profile_one(view);
            }
        });
        this.your_channel.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.profile.-$$Lambda$Profile_one$ZCWv_b3ul3DlBx9BF3I-HJ1bVag
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Profile_one.this.lambda$onclick$2$Profile_one(view);
            }
        });
        this.switch_account.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.profile.-$$Lambda$Profile_one$cwWtm6owGxMfpCHvxQrPGolxhtE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Profile_one.this.lambda$onclick$3$Profile_one(view);
            }
        });
    }

    public /* synthetic */ void lambda$onclick$0$Profile_one(View view) {
        finish();
    }

    public /* synthetic */ void lambda$onclick$1$Profile_one(View view) {
        Intent i = new Intent(this, Login_username.class);
        startActivity(i);
        AppCompatDelegate.setDefaultNightMode(1);
        finish();
    }

    public /* synthetic */ void lambda$onclick$2$Profile_one(View view) {
        Intent i = new Intent(this, Channel_dashbord.class);
        i.putExtra("getFb_account_name", this.getFb_account_name);
        i.putExtra("getFb_account_profile_url", this.getFb_account_profile_url);
        startActivity(i);
        finish();
    }

    public /* synthetic */ void lambda$onclick$3$Profile_one(View view) {
        openDialog();
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

    private void openDialog() {
        Dialog dialog = new Dialog(this);
        this.dialog = dialog;
        dialog.setContentView(R.layout.dailog_switch_account);
        this.dialog.getWindow().setLayout(-1, -1);
        ImageView add = (ImageView) this.dialog.findViewById(R.id.dialog_switch_account_add);
        ImageView profile = (ImageView) this.dialog.findViewById(R.id.dsalia_imageview);
        TextView lgTe = (TextView) this.dialog.findViewById(R.id.dsalia_mailId);
        TextView lgTn = (TextView) this.dialog.findViewById(R.id.dsalia_name);
        TextView lgTnin = (TextView) this.dialog.findViewById(R.id.dsalia_name_inside);
        lgTe.setText(this.currentUser.getEmail());
        lgTn.setText(this.getFb_account_name);
        lgTnin.setText(this.getFb_account_name);
        Glide.with((FragmentActivity) this).load(this.getFb_account_profile_url).placeholder((int) R.drawable.account_for_text).error(R.drawable.account_for_text).diskCacheStrategy(DiskCacheStrategy.ALL).into(profile);
        RecyclerView rv = (RecyclerView) this.dialog.findViewById(R.id.dialog_switch_account_recycler_view);
        rv.setAdapter(this.adaptor);
        rv.setLayoutManager(new LinearLayoutManager(this.dialog.getContext()));
        add.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.profile.-$$Lambda$Profile_one$C6RF3PpbAyMcLnlrfzUEXh7Mzec
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Profile_one.this.lambda$openDialog$4$Profile_one(view);
            }
        });
        this.dialog.getWindow().setLayout(660, 1200);
        this.dialog.setCancelable(true);
        this.dialog.show();
    }

    public /* synthetic */ void lambda$openDialog$4$Profile_one(View view) {
        Intent i = new Intent(this, Login_username.class);
        startActivity(i);
        this.dialog.dismiss();
        finishAffinity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }
}