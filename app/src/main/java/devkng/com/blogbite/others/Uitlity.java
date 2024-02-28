package devkng.com.blogbite.others;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.text.SimpleDateFormat;
import java.util.Objects;

/* loaded from: classes10.dex */
public class Uitlity {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, 0).show();
    }

    public static CollectionReference getDBReference() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            throw new AssertionError();
        }
        return FirebaseFirestore.getInstance().collection("Database").document(currentUser.getEmail()).collection("page");
    }

    public static CollectionReference getCollectionReferenceForDatabasePage() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            throw new AssertionError();
        }
        return FirebaseFirestore.getInstance().collection("Database").document((String) Objects.requireNonNull(currentUser.getEmail())).collection("page").document(currentUser.getEmail()).collection("card");
    }

    public static final String getChannelUid() {
        FirebaseUser cu = FirebaseAuth.getInstance().getCurrentUser();
        if (cu == null) {
            throw new AssertionError();
        }
        return cu.getEmail();
    }

    public static String CardDocId() {
        return getDocumentReference().getId();
    }

    public static String Content_item_id() {
        return getDocumentReference().getId();
    }

    public static DocumentReference getDocumentReference() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            throw new AssertionError();
        }
        return FirebaseFirestore.getInstance().collection("USERS").document(currentUser.getUid()).collection("Card").document();
    }

    public static StorageReference storageReference() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseStorage.getInstance().getReference().child(currentUser.getEmail()).child("Uplods");
    }

    public static String timeStampToString(Timestamp timestamp) {
        return new SimpleDateFormat("dd/mm/yy").format(timestamp.toDate());
    }
}