package devkng.com.blogbite.authentication;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import dkprojects.com.blogbite.R;

/* loaded from: classes6.dex */
public class Create_account_username extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btn_next;
    private String date;
    private TextInputEditText edt_email;
    private String email;
    private String gender;
    private Boolean isExist = false;
    private TextInputLayout lyt_email;
    private String month;
    private String name;
    private ProgressBar progressBar;
    private String year;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_username);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        AppCompatDelegate.setDefaultNightMode(1);
        setRequestedOrientation(12);
        this.name = getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.date = getIntent().getStringExtra("date");
        this.month = getIntent().getStringExtra("month");
        this.year = getIntent().getStringExtra("year");
        this.gender = getIntent().getStringExtra("gender");
        this.auth = FirebaseAuth.getInstance();
        this.edt_email = (TextInputEditText) findViewById(R.id.edt_create_account_username);
        this.lyt_email = (TextInputLayout) findViewById(R.id.lyt_create_account_username);
        this.progressBar = (ProgressBar) findViewById(R.id.create_account_username_progressbar);
        Button button = (Button) findViewById(R.id.btn_create_account_username_next);
        this.btn_next = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Create_account_username$0ptBcvPQ9iI9ON8U7OJyQSH1BSY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_account_username.this.lambda$onCreate$0$Create_account_username(view);
            }
        });
        onTextChange();
    }

    public /* synthetic */ void lambda$onCreate$0$Create_account_username(View view) {
        validate_username();
    }

    private void onTextChange() {
        this.edt_email.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_username.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_username.this.lyt_email.setError("");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_username.this.lyt_email.setError("");
            }
        });
    }

    private Boolean validate_username() {
        this.lyt_email.setErrorEnabled(true);
        this.lyt_email.setError(null);
        String username = this.edt_email.getText().toString();
        if (username.isEmpty()) {
            this.lyt_email.setErrorEnabled(true);
            this.lyt_email.setError("⚠ Field cannot be empty");
            return false;
        } else if (username.length() < 5 || username.length() >= 30) {
            this.lyt_email.setErrorEnabled(true);
            this.lyt_email.setError("⚠ Sorry, your username must be between 5 and 30 character long");
            return false;
        } else if (username.matches(".*[^a-z^0-9].*")) {
            this.lyt_email.setErrorEnabled(true);
            this.lyt_email.setError("⚠ Sorry, only letters (a-z),numbers (0-9) are allowed ");
            return false;
        } else {
            this.email = this.edt_email.getText().toString() + "@dmail.com";
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

    private Boolean checkEmail() {
        changeInProgress(true);
        this.progressBar.setProgress(40);
        String str = this.email;
        if (str != null) {
            this.auth.fetchSignInMethodsForEmail(str).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() { // from class: dkprojects.com.blogbite.authentication.Create_account_username.2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<SignInMethodQueryResult> task) {
                    Create_account_username.this.progressBar.setProgress(60);
                    boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                    if (!isNewUser) {
                        Create_account_username.this.isExist = true;
                        Create_account_username.this.changeInProgress(false);
                        Create_account_username.this.lyt_email.setError(null);
                        Create_account_username.this.progressBar.setProgress(80);
                        Create_account_username.this.lyt_email.setError(" That username is taken. Try Another");
                        return;
                    }
                    Create_account_username.this.progressBar.setProgress(80);
                    Create_account_username.this.changeInProgress(false);
                    Create_account_username.this.isExist = false;
                    Create_account_username.this.onclick_next();
                }
            });
        }
        return this.isExist;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onclick_next() {
        if (this.name.isEmpty()) {
            this.name = getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
        }
        Intent i = new Intent(this, Create_account_password.class);
        i.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name);
        i.putExtra("date", this.date);
        i.putExtra("month", this.month);
        i.putExtra("year", this.year);
        i.putExtra("gender", this.gender);
        i.putExtra("email", this.email);
        startActivity(i);
    }
}