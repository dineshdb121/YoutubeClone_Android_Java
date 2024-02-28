package devkng.com.blogbite.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.model.Page;
import java.util.List;

/* loaded from: classes4.dex */
public class Page_adaptor extends RecyclerView.Adapter {
    Context context;
    int layoutSelected = 0;
    List<Page> list;

    public void add(List<Page> list) {
        this.list = list;
    }

    public Page_adaptor(List<Page> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        switch (this.list.get(position).getViewType()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return -1;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View layout_one = LayoutInflater.from(this.context).inflate(R.layout.page_title_item, parent, false);
                return new Title_text_viewholder(layout_one);
            case 2:
            case 3:
            case 4:
                View layout_four = LayoutInflater.from(this.context).inflate(R.layout.page_image_item, parent, false);
                return new ReciverImageViewHolder(layout_four);
            default:
                return null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (this.list.get(position).getViewType()) {
            case 1:
                final Page model = this.list.get(position);
                String title_txt = this.list.get(position).getTitle();
                this.list.get(position).getContent_item_id();
                ((Title_text_viewholder) holder).set_text(title_txt);
                holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.adaptor.Page_adaptor.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Page page = model;
                        page.setSelected(Boolean.valueOf(!page.isSelected().booleanValue()));
                        holder.itemView.setBackgroundColor(model.isSelected().booleanValue() ? -7829368 : 0);
                    }
                });
                return;
            case 2:
            case 3:
            default:
                return;
            case 4:
                final Page m = this.list.get(position);
                String img_link = this.list.get(position).getImageUrl();
                Glide.with(this.context).load(img_link).placeholder(17301613).error(17301624).diskCacheStrategy(DiskCacheStrategy.ALL).into(((ReciverImageViewHolder) holder).imageView);
                holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.adaptor.Page_adaptor.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Page page = m;
                        page.setSelected(Boolean.valueOf(!page.isSelected().booleanValue()));
                        holder.itemView.setBackgroundColor(m.isSelected().booleanValue() ? -7829368 : 0);
                    }
                });
                return;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public int selected() {
        if (this.layoutSelected == 0) {
            return 0;
        }
        return 1;
    }

    /* loaded from: classes4.dex */
    public static class Title_text_viewholder extends RecyclerView.ViewHolder {
        private final TextView tv_sm;

        public Title_text_viewholder(View itemView) {
            super(itemView);
            this.tv_sm = (TextView) itemView.findViewById(R.id.page_title_txt);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set_text(String text) {
            this.tv_sm.setText(text);
        }
    }

    /* loaded from: classes4.dex */
    class ReciverImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ReciverImageViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.page_imageview);
        }
    }
}