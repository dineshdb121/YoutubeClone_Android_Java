package devkng.com.blogbite.curd.publish.page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.model.Card;
import dkprojects.com.blogbite.others.Uitlity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/* loaded from: classes8.dex */
public class Publish_page extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String KEY_CHANNEL_NAME = "channelName";
    private static final String KEY_CHANNEL_URL = "channelImageUrl";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DOCID = "docId";
    private static final String KEY_ITEM_COUNT_ORDER = "itemCount_Order";
    private static final String KEY_LIKE_COUNT = "likesCount";
    private static final String KEY_SAVED_TIME = "savedTime";
    private static final String KEY_THUMBNAIL = "thumbnail";
    private static final String KEY_THUMBNAIL_URL = "thumbnailUrl";
    private static final String KEY_TIME = "time";
    private static final String KEY_TITLE = "title";
    private static final String KEY_VIEWCOUNT = "viewsCount";
    private static final String KEY_VIEWTYPE = "viewType";
    private static final String KEY_VISIBLITY = "visibility";
    private static final int SELECT_PHOTO = 100;
    String ThumbUrlFromGallary;
    private Button btn_selector;
    String cardId;
    private String channelImageUrl_fb;
    private String channelName_fb;
    String channelUid;
    private Card contentOut;
    private FirebaseUser currentUser;
    private String desc_fb;
    private String docId_fb;
    private CardView edt_Img_btn;
    private String itemCountOrderBy_fb;
    private String likeCount_fb;
    private EditText medtViewTypeSelector;
    private ProgressBar progressBar;
    private TextView progressText;
    private ProgressBar progress_btn;
    Uri resultUri;
    private Button save_btn;
    private String savedTime_fb;
    int selected;
    String strDate;
    private ImageView thumb;
    private String thumbnailUrl_fb;
    private String thumbnail_fb;
    private int time_fb;
    private EditText title;
    private String title_fb;
    String uidFromAdNote;
    private int viewCount_fb;
    private int viewType_fb;
    int visibility_fb;
    private int visiblity;
    boolean clicked_camera_button = true;
    boolean photoUploded = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_page);
        statusBarColor();
        this.thumb = (ImageView) findViewById(R.id.thumbnail_publish);
        this.edt_Img_btn = (CardView) findViewById(R.id.image_edit_ic);
        this.medtViewTypeSelector = (EditText) findViewById(R.id.edtViewTypeSelector);
        this.save_btn = (Button) findViewById(R.id.tool_save_btn_publish);
        this.progress_btn = (ProgressBar) findViewById(R.id.progress_bar_tool_publish);
        this.title = (EditText) findViewById(R.id.edt_Title_Content_public);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar_publish);
        this.progressText = (TextView) findViewById(R.id.progress_txt);
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
        this.btn_selector = (Button) findViewById(R.id.btn_viewType_Selector);
        this.cardId = getIntent().getStringExtra("cardId");
        this.contentOut = new Card();
        this.photoUploded = false;
        this.progressBar.setVisibility(4);
        this.progressText.setVisibility(4);
        AssignViewType();
        if (this.visibility_fb == 0) {
            public_menu_click();
        }
        this.uidFromAdNote = getIntent().getStringExtra("desc");
        this.edt_Img_btn.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish_page$0fyX1CiC7YP13kseX_6g4tsxUM0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Publish_page.this.lambda$onCreate$0$Publish_page(view);
            }
        });
        this.btn_selector.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish_page$QQyY7b3poocLhdO0LBx1DfAXw-o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Publish_page.this.selectViewType(view);
            }
        });
        this.save_btn.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish_page$K_IsRw8h336zcNJBkttiiY79qGo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Publish_page.this.lambda$onCreate$1$Publish_page(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$Publish_page(View view) {
        this.clicked_camera_button = true;
        pickCropImage();
    }

    public /* synthetic */ void lambda$onCreate$1$Publish_page(View view) {
        this.photoUploded = true;
        savePublish();
        changeInProgress(true);
    }

    private void pickCropImage() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(16, 9).setCropMenuCropButtonTitle("Select").setOutputCompressQuality(50).start(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                this.resultUri = result.getUri();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), this.resultUri);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
                    byte[] imageByte = stream.toByteArray();
                    uploadImage(imageByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 204) {
                result.getError();
            }
        }
    }

    private void uploadImage(byte[] imageByte) {
        this.progressText.setVisibility(0);
        this.progressBar.setVisibility(0);
        final StorageReference imagePathFB = Uitlity.storageReference().child("image - " + Arrays.hashCode(imageByte));
        imagePathFB.putBytes(imageByte).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<UploadTask.TaskSnapshot>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish_page.2
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagePathFB.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish_page.2.1
                    public void onSuccess(Uri uri) {
                        Publish_page.this.ThumbUrlFromGallary = uri.toString();
                        Publish_page.this.clicked_camera_button = true;
                        Publish_page.this.contentOut.setThumbnailUrl(Publish_page.this.ThumbUrlFromGallary);
                        Publish_page.this.progressBar.setVisibility(4);
                        Publish_page.this.progressText.setText("Uploaded 100%");
                    }
                });
            }
        }).addOnProgressListener((OnProgressListener) new OnProgressListener<UploadTask.TaskSnapshot>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish_page.1
            public void onProgress(UploadTask.TaskSnapshot snapshot) {
                double progress = (snapshot.getBytesTransferred() * 100) / snapshot.getTotalByteCount();
                Publish_page.this.progressBar.setProgress((int) progress);
                Publish_page.this.progressText.setText(progress + "%");
                if (progress == 100.0d) {
                    Publish_page.this.photoUploded = true;
                }
            }
        });
    }

    String setTitle() {
        String title_from_edt = this.title.getText().toString();
        if (title_from_edt.isEmpty()) {
            return Uitlity.timeStampToString(Timestamp.now());
        }
        return title_from_edt;
    }

    String setThumbnailUrl() {
        String str = this.ThumbUrlFromGallary;
        if (str == null) {
            return this.thumbnailUrl_fb;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AssignViewType() {
        int i = this.visibility_fb;
        if (i != 1) {
            if (i != 2) {
                public_menu_click();
                return;
            } else {
                unlisted_menu_click();
                return;
            }
        }
        private_menu_click();
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            this.progress_btn.setVisibility(0);
            this.save_btn.setVisibility(8);
            return;
        }
        this.progress_btn.setVisibility(8);
        this.save_btn.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectViewType(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.viewtype_menu);
        popupMenu.show();
    }

    @Override // android.widget.PopupMenu.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        this.selected = itemId;
        switch (itemId) {
            case R.id.private_menu /* 2131362356 */:
            case R.id.save_btn_item /* 2131362398 */:
                private_menu_click();
                return true;
            case R.id.public_menu /* 2131362378 */:
                public_menu_click();
                return true;
            case R.id.unlisted_menu /* 2131362572 */:
                unlisted_menu_click();
                return true;
            default:
                private_menu_click();
                return false;
        }
    }

    private void private_menu_click() {
        this.visiblity = 1;
        this.medtViewTypeSelector.setText(" PRIVATE ");
        this.medtViewTypeSelector.setCompoundDrawablesWithIntrinsicBounds(R.drawable.private_icon, 0, R.drawable.expand_more, 0);
    }

    private void unlisted_menu_click() {
        this.visiblity = 2;
        this.medtViewTypeSelector.setText(" UNLISTED ");
        this.medtViewTypeSelector.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unlisted_icon, 0, R.drawable.expand_more, 0);
    }

    private void public_menu_click() {
        this.visiblity = 3;
        this.medtViewTypeSelector.setText(" PUBLIC ");
        this.medtViewTypeSelector.setCompoundDrawablesWithIntrinsicBounds(R.drawable.public_icon, 0, R.drawable.expand_more, 0);
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

    private void savePublish() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        this.strDate = formatter.format(date);
        this.contentOut.setCardId(this.cardId);
        this.contentOut.setChannelUid(Uitlity.getChannelUid());
        this.contentOut.setTitle(setTitle());
        this.contentOut.setDescription(this.desc_fb);
        this.contentOut.setTIME(this.strDate);
        this.contentOut.setSearch(setTitle());
        this.contentOut.setThumbnailUrl(setThumbnailUrl());
        this.contentOut.setVisibility(this.visiblity);
        if (this.visiblity == 1) {
            save_Private(this.contentOut);
            return;
        }
        save_Public(this.contentOut);
        save_Private(this.contentOut);
    }

    private void save_Private(Card note) {
        DocumentReference documentReference = Uitlity.getCollectionReferenceForDatabasePage().document(this.cardId);
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish_page$er1m3im3xLac-q0rCiK2BQu8w-Y
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Publish_page.this.lambda$save_Private$2$Publish_page(task);
            }
        });
    }

    public /* synthetic */ void lambda$save_Private$2$Publish_page(Task task) {
        if (task.isSuccessful()) {
            changeInProgress(false);
            finish();
            return;
        }
        Uitlity.showToast(this, "Failed while adding note");
        Toast.makeText(this, "", 0).show();
    }

    private void save_Public(Card note) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Public").document(this.cardId);
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish_page$edTfsU445EKsoJm4HIunUm5IqKs
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Publish_page.this.lambda$save_Public$3$Publish_page(task);
            }
        });
    }

    public /* synthetic */ void lambda$save_Public$3$Publish_page(Task task) {
        if (task.isSuccessful()) {
            changeInProgress(false);
            finish();
            return;
        }
        Uitlity.showToast(this, "Failed while adding note");
        Toast.makeText(this, "", 0).show();
    }

    private void loadDataFromCard() {
        FirebaseFirestore.getInstance().collection("Database").document((String) Objects.requireNonNull(this.currentUser.getEmail())).collection("page").document(this.currentUser.getEmail()).collection("card").document(this.cardId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish_page.4
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    Publish_page.this.title.setText("error in loading");
                    return;
                }
                Publish_page.this.title_fb = documentSnapshot.getString(Publish_page.KEY_TITLE);
                Publish_page.this.desc_fb = documentSnapshot.getString(Publish_page.KEY_DESCRIPTION);
                Publish_page.this.visibility_fb = documentSnapshot.getLong("visibility").intValue();
                Publish_page.this.thumbnailUrl_fb = documentSnapshot.getString(Publish_page.KEY_THUMBNAIL_URL);
                Publish_page.this.setThumbnailUrl();
                Publish_page.this.title.setText(Publish_page.this.title_fb);
                Publish_page.this.AssignViewType();
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish_page.3
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception e) {
            }
        });
    }
}