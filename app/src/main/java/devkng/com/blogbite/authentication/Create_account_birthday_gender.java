package devkng.com.blogbite.authentication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import dkprojects.com.blogbite.R;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class Create_account_birthday_gender extends AppCompatActivity {
    private Button btn_next;
    private TextInputEditText edt_date;
    private AutoCompleteTextView edt_gender;
    private AutoCompleteTextView edt_month;
    private TextInputEditText edt_year;
    private TextInputLayout lyt_contain;
    private TextInputLayout lyt_date;
    private TextInputLayout lyt_gender;
    private TextInputLayout lyt_month;
    private TextInputLayout lyt_year;
    private String name;
    private ProgressBar progressBar;
    private TextView tv_gender;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_birthday_gender);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        AppCompatDelegate.setDefaultNightMode(1);
        setRequestedOrientation(1);
        this.name = getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
        setContentView(R.layout.create_account_birthday_gender);
        this.btn_next = (Button) findViewById(R.id.btn_create_account_birthday_next);
        this.edt_date = (TextInputEditText) findViewById(R.id.edt_date);
        this.lyt_date = (TextInputLayout) findViewById(R.id.lyt__contain);
        this.edt_month = (AutoCompleteTextView) findViewById(R.id.edt_month);
        this.lyt_month = (TextInputLayout) findViewById(R.id.lyt_month);
        this.edt_year = (TextInputEditText) findViewById(R.id.edt_year);
        this.lyt_year = (TextInputLayout) findViewById(R.id.lyt_year);
        this.edt_gender = (AutoCompleteTextView) findViewById(R.id.edt_gender);
        this.lyt_gender = (TextInputLayout) findViewById(R.id.lyt_gender);
        this.lyt_contain = (TextInputLayout) findViewById(R.id.lyt_contain);
        this.tv_gender = (TextView) findViewById(R.id.txt_error_gender);
        this.progressBar = (ProgressBar) findViewById(R.id.create_account_birthday_prrogress);
        this.btn_next.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.authentication.-$$Lambda$Create_account_birthday_gender$NLa5s-2F_9zz03zrUbl6AzWve4I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_account_birthday_gender.this.lambda$onCreate$0$Create_account_birthday_gender(view);
            }
        });
        onTextChange();
        GenderSpinner();
        monthSpinner();
    }

    public /* synthetic */ void lambda$onCreate$0$Create_account_birthday_gender(View view) {
        validate_bd();
    }

    private void onTextChange() {
        this.edt_date.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_birthday_gender.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_birthday_gender.this.clearError();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.edt_month.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_birthday_gender.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_birthday_gender.this.clearError();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_birthday_gender.this.clearError();
            }
        });
        this.edt_year.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_birthday_gender.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_birthday_gender.this.clearError();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_birthday_gender.this.clearError();
            }
        });
        this.edt_gender.addTextChangedListener(new TextWatcher() { // from class: dkprojects.com.blogbite.authentication.Create_account_birthday_gender.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Create_account_birthday_gender.this.clearError();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Create_account_birthday_gender.this.clearError();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearError() {
        this.lyt_contain.setErrorEnabled(false);
        this.lyt_contain.setError("");
        this.lyt_date.setError("");
        this.lyt_month.setError("");
        this.lyt_year.setError("");
        this.lyt_gender.setError("");
        this.tv_gender.setVisibility(4);
        this.lyt_contain.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(8);
        }
    }

    private Boolean validate_bd() {
        Calendar.getInstance();
        String date = this.edt_date.getText().toString();
        String month = this.edt_month.getText().toString();
        String year = this.edt_year.getText().toString();
        String gender = this.edt_gender.getText().toString();
        String all = date + month + year + gender;
        if (all.isEmpty()) {
            this.lyt_contain.setError(" ! Please fill in a complete date of birth");
            this.lyt_date.setError(" ");
            this.lyt_month.setError(" ");
            this.lyt_year.setError(" ");
            this.lyt_gender.setError(" Please select your gender");
            this.tv_gender.setVisibility(0);
            return false;
        } else if (date.isEmpty()) {
            this.lyt_month.setError(" ");
            this.lyt_year.setError(" ");
            this.lyt_gender.setError(" ");
            this.tv_gender.setVisibility(0);
            this.lyt_gender.setError(" Please select your gender");
            this.lyt_contain.setErrorEnabled(false);
            this.lyt_contain.setHelperText(" ");
            return false;
        } else if (month.isEmpty()) {
            this.lyt_month.setError(" ");
            this.lyt_year.setError(" ");
            this.lyt_gender.setError(" ");
            this.tv_gender.setVisibility(0);
            this.lyt_gender.setError(" Please select your gender");
            this.lyt_contain.setHelperText(" Please fill month & year. of your birthday ");
            return false;
        } else if (year.isEmpty()) {
            this.lyt_year.setError(" ");
            this.lyt_gender.setError(" ");
            this.tv_gender.setVisibility(0);
            this.lyt_gender.setError(" Please select your gender");
            this.lyt_contain.setError(" Please fill year. your birthday ");
            return false;
        } else if (gender.isEmpty()) {
            this.lyt_gender.setErrorEnabled(true);
            this.tv_gender.setVisibility(0);
            this.lyt_gender.setError(" Please select your gender");
            return false;
        } else {
            this.progressBar.setProgress(50);
            changeInProgress(true);
            onclick_next();
            return true;
        }
    }

    private void onclick_next() {
        String date = this.edt_date.getText().toString().trim();
        String month = this.edt_month.getText().toString().trim();
        String year = this.edt_year.getText().toString().trim();
        String gender = this.edt_gender.getText().toString().trim();
        Intent i = new Intent(this, Create_account_username.class);
        i.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name);
        i.putExtra("date", date);
        i.putExtra("month", month);
        i.putExtra("year", year);
        i.putExtra("gender", gender);
        startActivity(i);
    }

    private void GenderSpinner() {
        String[] gender = {"Male", "Female", "Others"};
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(this, (int) R.layout.spinner_dropdown, gender);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edt_gender);
        autoCompleteTextView.setAdapter(adapterMonth);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: dkprojects.com.blogbite.authentication.Create_account_birthday_gender.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    private void monthSpinner() {
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December "};
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(this, (int) R.layout.spinner_dropdown, month);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edt_month);
        autoCompleteTextView.setAdapter(adapterMonth);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: dkprojects.com.blogbite.authentication.Create_account_birthday_gender.6
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}