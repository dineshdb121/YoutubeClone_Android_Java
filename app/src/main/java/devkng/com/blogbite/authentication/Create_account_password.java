package devkng.com.blogbite.authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;
import dkprojects.com.blogbite.MainActivity;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.authentication.switch_account_list.Login_accounts_list_model;
import dkprojects.com.blogbite.model.Account;
import dkprojects.com.blogbite.others.Uitlity;
import io.realm.Realm;
import java.util.Objects;

/* loaded from: classes6.dex */
public class Create_account_password extends AppCompatActivity {
    private String account_uid;
    private FirebaseAuth auth;
    private Button btn_next;
    private CountryCodePicker ccp;
    private String date;
    private TextInputEditText edt_password;
    private String email;
    private String gender;
    private TextInputLayout lyt_password;
    private String month;
    private String name;
    private String pass;
    private ProgressBar progressBar;
    private Realm realm;
    private String year;
    private Boolean result_account_list = false;
    private Boolean isExist = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_password);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        AppCompatDelegate.setDefaultNightMode(1);
        setRequestedOrientation(12);
        this.name = getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.date = getIntent().getStringExtra("date");
        this.month = getIntent().getStringExtra("month");
        this.year = getIntent().getStringExtra("year");
        this.gender = getIntent().getStringExtra("gender");
        this.email = getIntent().getStringExtra("email");
        Realm.init(this);
        this.realm = Realm.getDefaultInstance();
        this.auth = FirebaseAuth.getInstance();
        this.edt_password = (TextInputEditText) findViewById(R.id.edt_create_account_password);
        this.lyt_password = (TextInputLayout) findViewById(R.id.lyt_create_account_password);
        this.progressBar = (ProgressBar) findViewById(R.id.create_account_password_prrogress);
        this.btn_next = (Button) findViewById(R.id.btn_create_account_password_next);
        this.ccp = (CountryCodePicker) findViewById(R.id.ccp);
        this.btn_next.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Create_account_password$N9OMC1rswvKHwX3d_GKW0Mz3oRo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_account_password.this.lambda$onCreate$0$Create_account_password(view);
            }
        });
        this.pass = this.edt_password.getText().toString();
        onTextChange();
    }

    public /* synthetic */ void lambda$onCreate$0$Create_account_password(View view) {
        validate_password();
    }

    private void onTextChange() {
        this.edt_password.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_password.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_password.this.lyt_password.setError("");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_password.this.lyt_password.setError("");
            }
        });
    }

    private Boolean validate_password() {
        if (this.pass.isEmpty()) {
            this.pass = this.edt_password.getText().toString();
        }
        if (this.pass.isEmpty()) {
            this.lyt_password.setError(" Enter password");
            this.edt_password.setError(null);
            return false;
        } else if (this.pass.length() < 8) {
            this.lyt_password.setError(" Password length must contain minimum 8 digits or characters");
            this.pass = "";
            return false;
        } else {
            this.edt_password.setError(null);
            create_account_and_save();
            changeInProgress(true);
            this.progressBar.setProgress(40);
            return true;
        }
    }

    private void create_account_and_save() {
        final String accountCreatedDeviceModel = Build.MODEL;
        final String accountCreatedSDK = Build.VERSION.SDK;
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(this.email, this.pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { // from class: dkprojects.com.blogbite.authentication.Create_account_password.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Create_account_password.this.progressBar.setProgress(60);
                    Uitlity.showToast(Create_account_password.this, "Account created");
                    Create_account_password.this.account_uid = ((FirebaseUser) Objects.requireNonNull(firebaseAuth.getCurrentUser())).getUid();
                    Account a = new Account();
                    a.setDmail(Create_account_password.this.email);
                    a.setName(Create_account_password.this.name);
                    a.setDate(Create_account_password.this.date);
                    a.setMonth(Create_account_password.this.month);
                    a.setYear(Create_account_password.this.year);
                    a.setCountry(Create_account_password.this.ccp.getDefaultCountryName());
                    a.setGender(Create_account_password.this.gender);
                    a.setAccount_uid(Create_account_password.this.account_uid);
                    a.setAccount_created_sdk(accountCreatedSDK);
                    a.setAccount_created_device(accountCreatedDeviceModel);
                    a.setAccount_created_using(Create_account_password.this.getResources().getString(R.string.app_name));
                    Create_account_password.this.save_account(a);
                    return;
                }
                Uitlity.showToast(Create_account_password.this, task.getException().getLocalizedMessage());
            }
        });
    }

    private void add_login_data(String email, String password) {
        this.realm.beginTransaction();
        Login_accounts_list_model login_list = (Login_accounts_list_model) this.realm.createObject(Login_accounts_list_model.class);
        login_list.setE(email);
        login_list.setP(password);
        this.realm.commitTransaction();
        this.result_account_list = true;
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void save_account(Account account) {
        if (this.email != null) {
            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Accounts").document(this.email);
            documentReference.set(account).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Create_account_password$QSG17ky5l7-4Lccvs4l0mJkVJSU
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    Create_account_password.this.lambda$save_account$1$Create_account_password(task);
                }
            });
        }
    }

    public /* synthetic */ void lambda$save_account$1$Create_account_password(Task task) {
        if (task.isSuccessful()) {
            if (this.result_account_list.booleanValue()) {
                sign_in();
                return;
            }
            return;
        }
        this.lyt_password.setError(task.getException().getMessage());
    }

    private void sign_in() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(this.email, this.pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // from class: dkprojects.com.blogbite.authentication.Create_account_password.3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Create_account_password.this.progressBar.setProgress(80);
                    Create_account_password.this.changeInProgress(false);
                    Intent i = new Intent(Create_account_password.this, MainActivity.class);
                    i.setFlags(67108864);
                    Create_account_password.this.startActivity(i);
                    Create_account_password.this.finish();
                    return;
                }
                Create_account_password.this.changeInProgress(false);
                Create_account_password.this.lyt_password.setError(task.getException().getLocalizedMessage());
                Uitlity.showToast(Create_account_password.this, task.getException().getLocalizedMessage());
            }
        });
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(8);
        }
    }
}