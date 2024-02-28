package devkng.com.blogbite.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.curd.view.page.View_page;
import dkprojects.com.blogbite.model.Card;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class Search_result_adaptor extends RecyclerView.Adapter<Search_result_adaptor.ExampleViewHolder> implements Filterable {
    private Context context;
    private List<Card> exampleList;
    private List<Card> exampleListFull;
    String tex = "Devkng • 1.5K views • 1 month ago";
    private Filter examplefilter = new Filter() { // from class: dkprojects.com.blogbite.adaptor.Search_result_adaptor.1
        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            List<Card> filterlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterlist.addAll(Search_result_adaptor.this.exampleListFull);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Card item : Search_result_adaptor.this.exampleListFull) {
                    if (item.getSearch().toLowerCase().contains(pattern)) {
                        filterlist.add(item);
                    }
                }
            }
            Filter.FilterResults filterResults = new Filter.FilterResults();
            filterResults.values = filterlist;
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            Search_result_adaptor.this.exampleList.clear();
            Search_result_adaptor.this.exampleList.addAll((List) results.values);
            Search_result_adaptor.this.notifyDataSetChanged();
        }
    };

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return this.examplefilter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        private final TextView channel_views_time;
        private ImageView imageView;
        private final TextView tvtitle;

        ExampleViewHolder(View itemView) {
            super(itemView);
            this.tvtitle = (TextView) itemView.findViewById(R.id.video_card_title);
            this.channel_views_time = (TextView) itemView.findViewById(R.id.video_card_channnel_views_time);
            this.imageView = (ImageView) itemView.findViewById(R.id.video_card_imageview);
        }
    }

    public Search_result_adaptor(Context context, ArrayList<Card> exampleList) {
        this.exampleList = exampleList;
        this.context = context;
        this.exampleListFull = new ArrayList(exampleList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        return new ExampleViewHolder(v);
    }

    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final Card note = this.exampleList.get(position);
        holder.tvtitle.setText(note.getTitle());
        Glide.with(this.context).load(note.getThumbnailUrl()).placeholder(17301613).error(R.color.black_light).into(holder.imageView);
        holder.imageView.setImageResource(R.drawable.thumb);
        holder.channel_views_time.setText(this.tex);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.adaptor.-$$Lambda$Search_result_adaptor$ZD22zdImWU1uHhE011tDsr6Kaw0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Search_result_adaptor.this.lambda$onBindViewHolder$0$Search_result_adaptor(note, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$Search_result_adaptor(Card note, View v) {
        Intent i = new Intent(this.context, View_page.class);
        String cardId = note.getCardId();
        String channelId = note.getChannelUid();
        i.putExtra("channelUid", channelId);
        i.putExtra("cardId", cardId);
        this.context.startActivity(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.exampleList.size();
    }

    /* loaded from: classes4.dex */
    static class NoteHolder extends RecyclerView.ViewHolder {
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