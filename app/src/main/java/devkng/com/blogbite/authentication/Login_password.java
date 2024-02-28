package devkng.com.blogbite.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import dkprojects.com.blogbite.MainActivity;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.authentication.switch_account_list.Login_accounts_list_model;
import dkprojects.com.blogbite.sqlite.DBHelper;
import io.realm.Realm;

/* loaded from: classes6.dex */
public class Login_password extends AppCompatActivity {
    private Button btn_forgot_password;
    private Button btn_next;
    private DBHelper db;
    private TextView desc;
    private String e;
    private TextInputEditText edt_password;
    private String email;
    private TextInputLayout lyt_password;
    private String p;
    private String pass;
    private ProgressBar progressBar;
    private Realm realm;
    private Boolean result_account_list = false;
    private String geError = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_password);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        AppCompatDelegate.setDefaultNightMode(1);
        setRequestedOrientation(1);
        this.e = getIntent().getStringExtra("e");
        String stringExtra = getIntent().getStringExtra("p");
        this.p = stringExtra;
        String str = this.e;
        if (str != null) {
            loginAccountInFirebase(str, stringExtra);
        }
        Realm.init(this);
        this.realm = Realm.getDefaultInstance();
        this.email = getIntent().getStringExtra("email");
        TextView textView = (TextView) findViewById(R.id.signin_pass_desc);
        this.desc = textView;
        String str2 = this.email;
        if (str2 != null) {
            textView.setText(str2);
        }
        this.progressBar = (ProgressBar) findViewById(R.id.signin_progress_pass);
        this.btn_forgot_password = (Button) findViewById(R.id.btn_signin_forget_password);
        this.btn_next = (Button) findViewById(R.id.btn_signin_pass_next);
        this.edt_password = (TextInputEditText) findViewById(R.id.edt_signin_password);
        this.lyt_password = (TextInputLayout) findViewById(R.id.lyt_signin_password);
        Button button = (Button) findViewById(R.id.btn_signin_pass_next);
        this.btn_next = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Login_password$IdsdijwbLUk3kqQRJKupLW3pe-Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Login_password.this.lambda$onCreate$0$Login_password(view);
            }
        });
        onTextChange();
    }

    public /* synthetic */ void lambda$onCreate$0$Login_password(View view) {
        onclick_next();
    }

    private void onclick_next() {
        validate_pass();
    }

    private void onTextChange() {
        this.edt_password.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Login_password.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Login_password.this.lyt_password.setError("");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Login_password.this.lyt_password.setError("");
            }
        });
    }

    private void loginAccountInFirebase(final String email, final String password) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (currentUser != null) {
            firebaseAuth.signOut();
        }
        changeInProgress(true);
        this.progressBar.setProgress(40);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Login_password$7fBi_qe04Xbb32OPAWPNv2GQHV4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Login_password.this.lambda$loginAccountInFirebase$1$Login_password(email, password, task);
            }
        });
    }

    public /* synthetic */ void lambda$loginAccountInFirebase$1$Login_password(String email, String password, Task task) {
        changeInProgress(false);
        if (task.isSuccessful()) {
            this.progressBar.setProgress(40, true);
            add_login_data(email, password);
            if (this.result_account_list.booleanValue()) {
                Intent i = new Intent(this, MainActivity.class);
                i.setFlags(67108864);
                startActivity(i);
                finish();
                return;
            }
            return;
        }
        String error = task.getException().getLocalizedMessage();
        if (error == "The password is invalid or the user does not have a password.") {
            String replaced = error.replace("The password is invalid or the user does not have a password.", "The password is invalid");
            this.geError = replaced;
        } else {
            String request_blocked = error.replace("We have blocked all requests from this device due to unusual activity. Try again later. [ Access to this account has been temporarily disabled due to many failed login attempts. You can immediately restore it by resetting your password or you can try again later. ]", "Too many attempts. Please try again later");
            this.geError = request_blocked;
        }
        this.lyt_password.setError(this.geError);
        changeInProgress(false);
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

    private boolean validate_pass() {
        this.lyt_password.setError(null);
        String password = this.edt_password.getText().toString();
        if (password.isEmpty()) {
            this.lyt_password.setError("Field can't be empty");
            return false;
        } else if (password.length() < 8) {
            this.lyt_password.setError("Password length must contain minimum 8 digits or characters ");
            return false;
        } else {
            this.lyt_password.setError(null);
            String obj = this.edt_password.getText().toString();
            this.pass = obj;
            loginAccountInFirebase(this.email, obj);
            return true;
        }
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(8);
        }
    }
}