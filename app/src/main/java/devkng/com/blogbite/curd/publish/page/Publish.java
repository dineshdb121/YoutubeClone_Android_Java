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
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.model.Card;
import dkprojects.com.blogbite.model.Page;
import dkprojects.com.blogbite.others.Uitlity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* loaded from: classes8.dex */
public class Publish extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
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
    private String channelImageUrl_fb;
    private String channelName_fb;
    private Card contentOut;
    private String desc_fb;
    String docId_;
    private String docId_fb;
    private CardView edt_Img_btn;
    private String itemCountOrderBy_fb;
    private String likeCount_fb;
    private EditText medtViewTypeSelector;
    private ProgressBar prograss_btn;
    private ProgressBar progressBar;
    private TextView progressText;
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
    private int visiblity;
    int visiblity_fb;
    boolean clicked_camera_button = true;
    boolean photoUploded = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        statusBarColor();
        this.thumb = (ImageView) findViewById(R.id.thumbnail_publish);
        this.edt_Img_btn = (CardView) findViewById(R.id.image_edit_ic);
        this.medtViewTypeSelector = (EditText) findViewById(R.id.edtViewTypeSelector);
        this.save_btn = (Button) findViewById(R.id.tool_save_btn_publish);
        this.prograss_btn = (ProgressBar) findViewById(R.id.progress_bar_tool_publish);
        this.title = (EditText) findViewById(R.id.edt_Title_Content_public);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar_publish);
        this.progressText = (TextView) findViewById(R.id.progress_txt);
        this.btn_selector = (Button) findViewById(R.id.btn_viewType_Selector);
        this.docId_ = getIntent().getStringExtra("docId_");
        this.contentOut = new Card();
        this.photoUploded = false;
        this.progressBar.setVisibility(4);
        this.progressText.setVisibility(4);
        Glide.with((FragmentActivity) this).load(setThumUrl()).placeholder((int) R.drawable.thumb).into(this.thumb);
        AssinViewType();
        if (this.visiblity_fb == 0) {
            private_menu_click();
        }
        this.uidFromAdNote = getIntent().getStringExtra("desc");
        this.edt_Img_btn.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$kfDv0Otw4nAdjTtWuiaC07XduTU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Publish.this.lambda$onCreate$0$Publish(view);
            }
        });
        this.btn_selector.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$I1mURY-RFl9TrzW_ccXtCrHx38Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Publish.this.selectViewType(view);
            }
        });
        this.save_btn.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$IAIefEvzPEKu4oUQKX1AEMCDiH8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Publish.this.lambda$onCreate$1$Publish(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$Publish(View view) {
        this.clicked_camera_button = true;
        pickCropImage();
    }

    public /* synthetic */ void lambda$onCreate$1$Publish(View view) {
        this.photoUploded = true;
        changeInProgress(true);
    }

    private void retriveDataFromFB_In() {
        FirebaseFirestore.getInstance().collection("USERS").document(this.uidFromAdNote).collection("ContentOut").document(this.docId_).collection("ContentIn").orderBy("itemCount_Order_By", Query.Direction.ASCENDING).get().addOnSuccessListener(C$$Lambda$Publish$cGlizBKlAywj0Y48FBYfb83CY2E.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$retriveDataFromFB_In$2(QuerySnapshot queryDocumentSnapshots) {
        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
        for (DocumentSnapshot ds : dsList) {
            int dataSize = 0 + 1;
            if (dataSize != dsList.size()) {
                Page page = (Page) ds.toObject(Page.class);
            }
        }
    }

    private void save_DataToPublicContentIn(Page modelClass) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("PUBLIC").document(this.docId_).collection("In").document();
        documentReference.set(modelClass).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$LMCaCJtHJ0qt7jJtmpPNb9kwR8w
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Publish.this.lambda$save_DataToPublicContentIn$3$Publish(task);
            }
        });
    }

    public /* synthetic */ void lambda$save_DataToPublicContentIn$3$Publish(Task task) {
        if (!task.isSuccessful()) {
            Uitlity.showToast(this, "Failed while adding note");
            Toast.makeText(this, "", 0).show();
        }
    }

    private void lodeData_andCopy() {
        Uitlity.getCollectionReferenceForDatabasePage().document(this.docId_).collection("ContentIn").orderBy("itemCount_Order_By", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$pkdZz2N8mlKZh550y7xAkTZnnyw
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                Publish.this.lambda$lodeData_andCopy$4$Publish((QuerySnapshot) obj);
            }
        });
    }

    public /* synthetic */ void lambda$lodeData_andCopy$4$Publish(QuerySnapshot queryDocumentSnapshots) {
        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
        for (DocumentSnapshot ds : dsList) {
            dsList.size();
            Page modelClass = (Page) ds.toObject(Page.class);
            save_DataToPublic_conten_in(modelClass);
        }
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
                    uplodeImage(imageByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 204) {
                result.getError();
            }
        }
    }

    private void uplodeImage(byte[] imageByte) {
        this.progressText.setVisibility(0);
        this.progressBar.setVisibility(0);
        final StorageReference imagePathFB = Uitlity.storageReference().child("image - " + Arrays.hashCode(imageByte));
        imagePathFB.putBytes(imageByte).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<UploadTask.TaskSnapshot>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish.2
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagePathFB.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish.2.1
                    public void onSuccess(Uri uri) {
                        Publish.this.ThumbUrlFromGallary = uri.toString();
                        Publish.this.clicked_camera_button = true;
                        Publish.this.contentOut.setThumbnailUrl(Publish.this.ThumbUrlFromGallary);
                        Publish.this.progressBar.setVisibility(4);
                        Publish.this.progressText.setText("Uploaded 100%");
                    }
                });
            }
        }).addOnProgressListener((OnProgressListener) new OnProgressListener<UploadTask.TaskSnapshot>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish.1
            public void onProgress(UploadTask.TaskSnapshot snapshot) {
                double progress = (snapshot.getBytesTransferred() * 100) / snapshot.getTotalByteCount();
                Publish.this.progressBar.setProgress((int) progress);
                Publish.this.progressText.setText(progress + "%");
                if (progress == 100.0d) {
                    Publish.this.photoUploded = true;
                }
            }
        });
    }

    String setThumUrl() {
        String str = this.ThumbUrlFromGallary;
        if (str == null) {
            return this.thumbnailUrl_fb;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AssinViewType() {
        int i = this.visiblity_fb;
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
            this.prograss_btn.setVisibility(0);
            this.save_btn.setVisibility(8);
            return;
        }
        this.prograss_btn.setVisibility(8);
        this.save_btn.setVisibility(0);
    }

    private void savePublish() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        this.strDate = formatter.format(date);
        this.contentOut.setContentPublishQueueId(this.docId_);
        this.contentOut.setTitle(title_time());
        this.contentOut.setDescription(this.desc_fb);
        this.contentOut.setTIME(this.strDate);
        this.contentOut.setThumbnailUrl(setThumUrl());
        this.contentOut.setVisibility(this.visiblity);
        if (this.visiblity == 1) {
            save_DataToPrivate(this.contentOut);
        } else {
            save_DataToPublic(this.contentOut);
        }
    }

    private void publicDataCopy() {
        this.contentOut.setTitle(this.title_fb);
        this.contentOut.setDescription(this.desc_fb);
        this.contentOut.setContentPublishQueueId(this.docId_fb);
        this.contentOut.setVisibility(this.visiblity_fb);
        this.contentOut.setViewType(this.viewType_fb);
        this.contentOut.setChannelName(this.channelName_fb);
        this.contentOut.setChannelImageUrl(this.channelImageUrl_fb);
        this.contentOut.setItemCount_Order_By(Integer.parseInt(this.itemCountOrderBy_fb));
        this.contentOut.setLikesCount(this.likeCount_fb);
        this.contentOut.setViewType(this.viewType_fb);
        this.contentOut.setViewsCount(String.valueOf(this.viewCount_fb));
        this.contentOut.setTIME(String.valueOf(this.time_fb));
    }

    String title_time() {
        String title_from_edt = this.title.getText().toString();
        if (title_from_edt.isEmpty()) {
            return Uitlity.timeStampToString(Timestamp.now());
        }
        return title_from_edt;
    }

    private void lodeData() {
        FirebaseFirestore.getInstance().collection("USERS").document(this.uidFromAdNote).collection("ContentOut").document(this.docId_).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish.4
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    Publish.this.title.setText("error in loading");
                    return;
                }
                Publish.this.title_fb = documentSnapshot.getString(Publish.KEY_TITLE);
                Publish.this.desc_fb = documentSnapshot.getString(Publish.KEY_DESCRIPTION);
                Publish.this.visiblity_fb = documentSnapshot.getLong("visibility").intValue();
                Publish.this.thumbnailUrl_fb = documentSnapshot.getString(Publish.KEY_THUMBNAIL_URL);
                Publish.this.setThumUrl();
                Publish.this.title.setText(Publish.this.title_fb);
                Publish.this.AssinViewType();
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: dkprojects.com.blogbite.curd.publish.page.Publish.3
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception e) {
            }
        });
    }

    private void save_DataToPublic_conten_in(Page note) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("PUBLIC").document(this.docId_).collection("In").document();
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$Nmi3qsmLkSxDj5LjXzJcG1FaB8M
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Publish.this.lambda$save_DataToPublic_conten_in$5$Publish(task);
            }
        });
    }

    public /* synthetic */ void lambda$save_DataToPublic_conten_in$5$Publish(Task task) {
        if (task.isSuccessful()) {
            changeInProgress(false);
            finish();
            return;
        }
        Uitlity.showToast(this, "Failed while adding note");
        Toast.makeText(this, "", 0).show();
    }

    private void save_DataToPrivate(Card note) {
        DocumentReference documentReference = Uitlity.getCollectionReferenceForDatabasePage().document(this.docId_);
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$2Za0DSJ0DHG8-31ijJjs4oLaYMo
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Publish.this.lambda$save_DataToPrivate$6$Publish(task);
            }
        });
    }

    public /* synthetic */ void lambda$save_DataToPrivate$6$Publish(Task task) {
        if (task.isSuccessful()) {
            changeInProgress(false);
            finish();
            return;
        }
        Uitlity.showToast(this, "Failed while adding note");
        Toast.makeText(this, "", 0).show();
    }

    private void save_DataToPublic(Card note) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("PUBLIC").document(this.docId_);
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.publish.page.-$$Lambda$Publish$DK1_PGH_F6MeOahaedMNJWb4RQs
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Publish.this.lambda$save_DataToPublic$7$Publish(task);
            }
        });
    }

    public /* synthetic */ void lambda$save_DataToPublic$7$Publish(Task task) {
        if (task.isSuccessful()) {
            changeInProgress(false);
            return;
        }
        Uitlity.showToast(this, "Failed while adding note");
        Toast.makeText(this, "", 0).show();
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

    private void setThumImageView() {
        this.clicked_camera_button = true;
        this.thumb.setImageURI(this.resultUri);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        lodeData();
        setThumImageView();
        AssinViewType();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}