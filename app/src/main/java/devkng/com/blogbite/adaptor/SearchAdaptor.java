package devkng.com.blogbite.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.activity.Search.SearchResults;
import dkprojects.com.blogbite.model.Search_model;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SearchAdaptor extends RecyclerView.Adapter<SearchAdaptor.ExampleViewHolder> implements Filterable {
    private Context context;
    private List<Search_model> exampleList;
    private List<Search_model> exampleListFull;
    private Filter examplefilter = new Filter() { // from class: dkprojects.com.blogbite.adaptor.SearchAdaptor.1
        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            List<Search_model> filterlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterlist.addAll(SearchAdaptor.this.exampleListFull);
            } else {
                String pattrn = constraint.toString().toLowerCase().trim();
                for (Search_model item : SearchAdaptor.this.exampleListFull) {
                    if (item.getSearched_text().toLowerCase().contains(pattrn)) {
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
            SearchAdaptor.this.exampleList.clear();
            SearchAdaptor.this.exampleList.addAll((List) results.values);
            SearchAdaptor.this.notifyDataSetChanged();
        }
    };

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return this.examplefilter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        ExampleViewHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView.findViewById(R.id.search_suggestion_tv);
        }
    }

    public SearchAdaptor(Context context, List<Search_model> exampleList) {
        this.exampleList = exampleList;
        this.context = context;
        this.exampleListFull = new ArrayList(exampleList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_suggestion_item, parent, false);
        return new ExampleViewHolder(v);
    }

    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final Search_model currentItem = this.exampleList.get(position);
        holder.tv.setText(currentItem.getSearched_text());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.adaptor.-$$Lambda$SearchAdaptor$9XvPRY1OIxjBmkmkMY3TEBCsujA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchAdaptor.this.lambda$onBindViewHolder$0$SearchAdaptor(currentItem, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$SearchAdaptor(Search_model currentItem, View view) {
        Intent i = new Intent(this.context, SearchResults.class);
        i.putExtra("text", currentItem.getSearched_text());
        this.context.startActivity(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.exampleList.size();
    }
}