package devkng.com.blogbite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.firebase.auth.FirebaseAuth;


public class Splashscreen extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        setRequestedOrientation(1);
        statusBarColor();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        new Handler().postDelayed(new Runnable() { // from class: dkprojects.com.blogbite.-$$Lambda$Splashscreen$qfOZm14AHxsPSbw2FOnvlftBago
            @Override // java.lang.Runnable
            public final void run() {
                Splashscreen.this.lambda$onCreate$0$Splashscreen(currentUser);
            }
        }, 2500L);
    }

    public /* synthetic */ void lambda$onCreate$0$Splashscreen(FirebaseUser currentUser) {
        if (currentUser == null) {
            Intent intent = new Intent(this, Login_username.class);
            intent.setFlags(536870912);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
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