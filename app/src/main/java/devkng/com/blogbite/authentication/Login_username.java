package devkng.com.blogbite.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
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
import com.google.firebase.auth.SignInMethodQueryResult;
import dkprojects.com.blogbite.R;
import javax.annotation.Nonnull;

/* loaded from: classes6.dex */
public class Login_username extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btn_create_account;
    private Button btn_next;
    private TextInputEditText edt_email;
    private Boolean isExist = false;
    private TextInputLayout lyt_email;
    private ProgressBar progressBar;
    private TextView tv_hint;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_username);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        AppCompatDelegate.setDefaultNightMode(1);
        setRequestedOrientation(1);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.signin_progress_user);
        this.progressBar = progressBar;
        progressBar.setMax(100);
        this.tv_hint = (TextView) findViewById(R.id.tv_signin_hint);
        this.btn_create_account = (Button) findViewById(R.id.btn_signin_create_account);
        this.btn_next = (Button) findViewById(R.id.btn_signin_user_next);
        this.edt_email = (TextInputEditText) findViewById(R.id.edt_signin_username);
        this.lyt_email = (TextInputLayout) findViewById(R.id.lyt_signin_username);
        this.edt_email.setInputType(524288);
        this.auth = FirebaseAuth.getInstance();
        this.btn_next.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Login_username$ttHNTUB0Bwsrg_rNiLIcd74MvBs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Login_username.this.lambda$onCreate$0$Login_username(view);
            }
        });
        this.btn_create_account.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Login_username$BLTKesdbWRTNjrhKapCvVPw7_C4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Login_username.this.lambda$onCreate$1$Login_username(view);
            }
        });
        onTextChange();
    }

    public /* synthetic */ void lambda$onCreate$0$Login_username(View view) {
        validate_email();
    }

    public /* synthetic */ void lambda$onCreate$1$Login_username(View view) {
        onclick_create_account();
    }

    private void onTextChange() {
        this.edt_email.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Login_username.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Login_username.this.lyt_email.setError("");
                Login_username.this.tv_hint.setVisibility(4);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Login_username.this.tv_hint.setVisibility(4);
                Login_username.this.lyt_email.setError("");
            }
        });
    }

    private Boolean checkEmail() {
        changeInProgress(true);
        this.progressBar.setProgress(40);
        String email = this.edt_email.getText().toString();
        this.auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() { // from class: dkprojects.com.blogbite.authentication.Login_username.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(@Nonnull Task<SignInMethodQueryResult> task) {
                Login_username.this.progressBar.setProgress(60);
                boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                if (isNewUser) {
                    Login_username.this.progressBar.setProgress(80);
                    Login_username.this.changeInProgress(false);
                    Login_username.this.isExist = false;
                    Login_username.this.lyt_email.setError(" Couldn't find your Dmail Account");
                    return;
                }
                Login_username.this.isExist = true;
                Login_username.this.lyt_email.setError(null);
                Login_username.this.changeInProgress(false);
                Login_username.this.onclick_next();
            }
        });
        return this.isExist;
    }

    private boolean validate_email() {
        this.lyt_email.setError(null);
        String email = this.edt_email.getText().toString();
        if (email.isEmpty()) {
            this.lyt_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.lyt_email.setError(" Enter a valid email ");
            return false;
        } else if (email.contains("@gmail.com")) {
            this.lyt_email.setError(" Couldn't find your dmail Account ");
            this.tv_hint.setText(R.string.otheremailHint);
            this.tv_hint.setVisibility(0);
            return false;
        } else if (email.contains("@email.com")) {
            this.lyt_email.setError(" Couldn't find your Dmail Account ");
            this.tv_hint.setText(R.string.otheremailHint);
            this.tv_hint.setVisibility(0);
            return false;
        } else {
            this.lyt_email.setError(null);
            checkEmail();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void onclick_next() {
        String email = this.edt_email.getText().toString();
        Intent i = new Intent(this, Login_password.class);
        i.putExtra("email", email);
        startActivity(i);
    }

    private void onclick_create_account() {
        Intent i = new Intent(this, Create_account_name.class);
        startActivity(i);
    }
}