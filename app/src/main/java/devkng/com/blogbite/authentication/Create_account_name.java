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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import dkprojects.com.blogbite.R;

/* loaded from: classes6.dex */
public class Create_account_name extends AppCompatActivity {
    private Button btn_next;
    private TextInputEditText edt_firstname;
    private TextInputEditText edt_lastname;
    private TextInputLayout lyt_firstname;
    private TextInputLayout lyt_lastname;
    private ProgressBar progressBar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_name);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        AppCompatDelegate.setDefaultNightMode(1);
        setRequestedOrientation(1);
        this.btn_next = (Button) findViewById(R.id.btn_create_account_name_next);
        this.edt_firstname = (TextInputEditText) findViewById(R.id.edt_firstname);
        this.lyt_firstname = (TextInputLayout) findViewById(R.id.lyt_create_account_firstname);
        this.edt_lastname = (TextInputEditText) findViewById(R.id.edt_lastname);
        this.lyt_lastname = (TextInputLayout) findViewById(R.id.lyt_create_account_lastname);
        this.progressBar = (ProgressBar) findViewById(R.id.create_account_name_prrogress);
        this.btn_next.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Create_account_name$KX9_tlsfZFOyuIRNWUP3bnV5Vic
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_account_name.this.lambda$onCreate$0$Create_account_name(view);
            }
        });
        onTextChange();
    }

    public /* synthetic */ void lambda$onCreate$0$Create_account_name(View view) {
        validate_name();
    }

    private void onTextChange() {
        this.edt_firstname.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_name.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_name.this.changeInProgress(false);
                Create_account_name.this.lyt_firstname.setErrorEnabled(false);
                Create_account_name.this.lyt_lastname.setErrorEnabled(false);
                Create_account_name.this.lyt_firstname.setError("");
                Create_account_name.this.lyt_lastname.setError("");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_name.this.changeInProgress(false);
                Create_account_name.this.lyt_firstname.setErrorEnabled(false);
                Create_account_name.this.lyt_lastname.setErrorEnabled(false);
                Create_account_name.this.lyt_firstname.setError("");
                Create_account_name.this.lyt_lastname.setError("");
            }
        });
        this.edt_lastname.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_name.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_name.this.lyt_firstname.setErrorEnabled(false);
                Create_account_name.this.lyt_lastname.setErrorEnabled(false);
                Create_account_name.this.lyt_lastname.setError("");
                Create_account_name.this.lyt_firstname.setError("");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_name.this.lyt_firstname.setErrorEnabled(false);
                Create_account_name.this.lyt_lastname.setErrorEnabled(false);
                Create_account_name.this.lyt_lastname.setError("");
                Create_account_name.this.lyt_firstname.setError("");
            }
        });
    }

    private boolean validate_name() {
        String firstname = this.edt_firstname.getText().toString();
        if (firstname.isEmpty()) {
            this.lyt_firstname.setErrorEnabled(true);
            this.lyt_lastname.setErrorEnabled(true);
            this.lyt_firstname.setError(" ");
            this.lyt_lastname.setError(" Field cannot be empty");
            return false;
        }
        changeInProgress(true);
        this.progressBar.setProgress(50);
        this.lyt_firstname.setError(null);
        this.lyt_lastname.setError(null);
        onclick_next();
        return true;
    }

    private void onclick_next() {
        String name = this.edt_firstname.getText().toString().trim() + this.edt_lastname.getText().toString().trim();
        Intent i = new Intent(this, Create_account_birthday_gender.class);
        i.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, name);
        startActivity(i);
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        changeInProgress(false);
    }
}