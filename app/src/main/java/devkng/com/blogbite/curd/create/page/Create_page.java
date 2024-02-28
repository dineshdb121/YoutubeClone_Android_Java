package devkng.com.blogbite.curd.create.page;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.adaptor.Page_adaptor;
import dkprojects.com.blogbite.curd.publish.page.Publish_page;
import dkprojects.com.blogbite.model.Page;
import dkprojects.com.blogbite.others.Uitlity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes10.dex */
public class Create_page extends AppCompatActivity {
    private static final int SELECT_PHOTO = 100;
    private Page_adaptor adaptor;
    private ToggleButton add_audio;
    private ToggleButton add_desc;
    private ToggleButton add_img;
    private ToggleButton add_title;
    private ToggleButton add_vid;
    private String cardId;
    private String cardId_created;
    private String channelUID;
    private String channelUid;
    private String content_item_id;
    private View create_page;
    private View create_title;
    private AlertDialog dialog;
    private String getImageUri;
    private boolean item_selected = false;
    private List<Page> list_page_model;
    private ConstraintLayout page;
    private String page_contentId;
    private RecyclerView recyclerView;
    private Uri resultUri;
    private boolean sucess;
    private ConstraintLayout title;
    private ToggleButton title_bold;
    private FloatingActionButton title_done;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_page);
        findId();
        addtitle();
        retrive_data();
        this.list_page_model = new ArrayList();
        this.adaptor = new Page_adaptor(this.list_page_model, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adaptor);
        this.adaptor.add(this.list_page_model);
        this.adaptor.notifyDataSetChanged();
        this.cardId = getIntent().getStringExtra("cardId_created");
    }

    private void findId() {
        this.add_title = (ToggleButton) findViewById(R.id.cp_add_title);
        this.add_desc = (ToggleButton) findViewById(R.id.cp_add_desc);
        this.add_audio = (ToggleButton) findViewById(R.id.cp_add_audio);
        this.add_img = (ToggleButton) findViewById(R.id.cp_add_image);
        this.add_vid = (ToggleButton) findViewById(R.id.cp_add_video);
        this.create_page = findViewById(R.id.create_page_tool);
        this.title_bold = (ToggleButton) findViewById(R.id.cpt_bold);
        this.create_title = findViewById(R.id.create_title_tool);
        this.title_done = (FloatingActionButton) findViewById(R.id.cptt_fab);
        this.page = (ConstraintLayout) findViewById(R.id.page_title_txt_container);
        this.recyclerView = (RecyclerView) findViewById(R.id.view_page_recycler_view);
    }

    private void addtitle() {
        this.add_title.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.create.page.-$$Lambda$Create_page$f5NpK2lvTez5QR1mbRWOxBi09RQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_page.this.lambda$addtitle$0$Create_page(view);
            }
        });
        this.title_bold.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.create.page.-$$Lambda$Create_page$tzJ8kSHzACwOITSwpYYhX9qdCCY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_page.this.lambda$addtitle$1$Create_page(view);
            }
        });
        this.add_img.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.create.page.-$$Lambda$Create_page$hFUTWHUavYb1TgtINSw_B05DGoE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_page.this.lambda$addtitle$2$Create_page(view);
            }
        });
        this.title_done.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.create.page.-$$Lambda$Create_page$KDuhjVM3xnIu-i6O2uMBJrLh9b4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Create_page.this.lambda$addtitle$3$Create_page(view);
            }
        });
    }

    public /* synthetic */ void lambda$addtitle$0$Create_page(View view) {
        openDialog();
    }

    public /* synthetic */ void lambda$addtitle$1$Create_page(View view) {
        this.create_title.setVisibility(8);
        this.create_page.setVisibility(0);
    }

    public /* synthetic */ void lambda$addtitle$2$Create_page(View view) {
        openCamera();
    }

    public /* synthetic */ void lambda$addtitle$3$Create_page(View view) {
        this.create_page.setVisibility(8);
        this.create_title.setVisibility(0);
        this.page.setBackgroundColor(0);
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Type here...");
        View view = getLayoutInflater().inflate(R.layout.add_text_dialog, (ViewGroup) null);
        final EditText message = (EditText) view.findViewById(R.id.edt_text_Dialog);
        Button ok = (Button) view.findViewById(R.id.dialog_ok_btn);
        ok.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.curd.create.page.Create_page.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Page p = new Page();
                p.setViewType(1);
                p.setTitle(message.getText().toString());
                p.setItemCount_Order_By(Create_page.this.adaptor.getItemCount() + 1);
                p.setTimestamp(Timestamp.now());
                Create_page.this.savePageItems(p);
                Create_page.this.list_page_model.add(p);
                Create_page.this.recyclerView.smoothScrollToPosition(Create_page.this.adaptor.getItemCount());
                Create_page.this.adaptor.notifyDataSetChanged();
                Create_page.this.dialog.dismiss();
            }
        });
        builder.setView(view);
        AlertDialog create = builder.create();
        this.dialog = create;
        create.show();
    }

    private void openCamera() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setCropMenuCropButtonTitle("Select").setOutputCompressQuality(50).start(this);
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
        final Page note = new Page();
        note.setTimestamp(Timestamp.now());
        note.setItemCount_Order_By(this.adaptor.getItemCount() + 1);
        final StorageReference imagePathFB = Uitlity.storageReference().child("image - " + Arrays.hashCode(imageByte));
        imagePathFB.putBytes(imageByte).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<UploadTask.TaskSnapshot>() { // from class: dkprojects.com.blogbite.curd.create.page.Create_page.2
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagePathFB.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() { // from class: dkprojects.com.blogbite.curd.create.page.Create_page.2.1
                    public void onSuccess(Uri uri) {
                        Create_page.this.getImageUri = uri.toString();
                        note.setImageUrl(uri.toString());
                        Page page = note;
                        Page page2 = note;
                        page.setViewType(4);
                        Create_page.this.list_page_model.add(note);
                        Create_page.this.savePageItems(note);
                        Create_page.this.adaptor.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePageItems(Page p) {
        String Content_item_id = Uitlity.Content_item_id();
        this.content_item_id = Content_item_id;
        p.setContent_item_id(Content_item_id);
        DocumentReference documentReference = Uitlity.getCollectionReferenceForDatabasePage().document(this.cardId).collection(FirebaseAnalytics.Param.CONTENT).document(this.content_item_id);
        documentReference.set(p).addOnCompleteListener(new OnCompleteListener() { // from class: dkprojects.com.blogbite.curd.create.page.-$$Lambda$Create_page$q-BJAc8-J2tvZa4Pu72hn7HgfWI
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Create_page.this.lambda$savePageItems$4$Create_page(task);
            }
        });
    }

    public /* synthetic */ void lambda$savePageItems$4$Create_page(Task task) {
        if (task.isSuccessful()) {
            Uitlity.showToast(this, "added Sucessfully");
            this.recyclerView.smoothScrollToPosition(this.adaptor.getItemCount() + 1);
            this.sucess = true;
            return;
        }
        Uitlity.showToast(this, "error in adding");
    }

    private void retrive_data() {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, Publish_page.class);
        i.putExtra("cardId", this.cardId);
        startActivity(i);
    }
}