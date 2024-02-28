package devkng.com.blogbite.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.adaptor.Card_adaptor;
import dkprojects.com.blogbite.curd.view.page.View_page;
import dkprojects.com.blogbite.model.Card;
import dkprojects.com.blogbite.model.Page;
import java.util.List;

/* loaded from: classes4.dex */
public class fb_mv extends FirestoreRecyclerAdapter<Card, Card_adaptor.NoteHolder> {
    Context context;
    List<Page> list;

    public fb_mv(FirestoreRecyclerOptions<Card> options) {
        super(options);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindViewHolder(Card_adaptor.NoteHolder holder, int position, Card note) {
        switch (this.list.get(position).getViewType()) {
            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.adaptor.-$$Lambda$fb_mv$6fJ8rITj1YMt8azDLGcJP3T44J8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        fb_mv.this.lambda$onBindViewHolder$0$fb_mv(view);
                    }
                });
                return;
            case 2:
            case 3:
            default:
                return;
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$fb_mv(View v) {
        Intent i = new Intent(this.context, View_page.class);
        this.context.startActivity(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Card_adaptor.NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /* loaded from: classes4.dex */
    static class NoteHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public NoteHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.page_carde_image);
        }
    }
}