package devkng.com.blogbite.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.curd.view.page.View_page;
import dkprojects.com.blogbite.model.Card;
import java.util.List;

/* loaded from: classes4.dex */
public class Card_adaptor extends FirestoreRecyclerAdapter<Card, Card_adaptor.NoteHolder> {
    Context context;
    List<Card> list;
    String tex = "Devkng • 1.5K views • 1 month ago";

    public Card_adaptor(FirestoreRecyclerOptions<Card> options, Context context) {
        super(options);
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindViewHolder(NoteHolder holder, final int position, final Card note) {
        holder.tvtitle.setText(note.getTitle());
        Glide.with(this.context).load(note.getThumbnailUrl()).placeholder(17301613).error(R.color.black_light).into(holder.imageView);
        holder.imageView.setImageResource(R.drawable.thumb);
        holder.tvtitle.setText(note.getTitle());
        holder.channel_views_time.setText(this.tex);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.adaptor.-$$Lambda$Card_adaptor$d3WcfLGo63OmJuidXjQRuCS5h7Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Card_adaptor.this.lambda$onBindViewHolder$0$Card_adaptor(note, position, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$Card_adaptor(Card note, int position, View v) {
        Intent i = new Intent(this.context, View_page.class);
        String channelId = note.getChannelUid();
        i.putExtra("channelUid", channelId);
        String cardId = getSnapshots().getSnapshot(position).getId();
        i.putExtra("cardId", cardId);
        this.context.startActivity(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        return new NoteHolder(itemView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class NoteHolder extends RecyclerView.ViewHolder {
        private final TextView channel_views_time;
        private ImageView imageView;
        private final TextView tvtitle;

        public NoteHolder(View itemView) {
            super(itemView);
            this.tvtitle = (TextView) itemView.findViewById(R.id.video_card_title);
            this.channel_views_time = (TextView) itemView.findViewById(R.id.video_card_channnel_views_time);
            this.imageView = (ImageView) itemView.findViewById(R.id.video_card_imageview);
        }
    }
}