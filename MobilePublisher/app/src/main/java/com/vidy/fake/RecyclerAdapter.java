package com.vidy.fake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicho.fakepublisherdashboard.R;
import com.squareup.picasso.Picasso;
import com.vidy.fake.datamodel.search.Datum;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Datum> items;
    private int selectedItem = -1;
    private Listener listener;

    public interface Listener {
        void updateClipId(String clipId);
    }

    public RecyclerAdapter(Context context, List<Datum> items, Listener listener) {
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_video, parent, false);
        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        Datum item = items.get(position);
        holder.textView.setText(""+item.getDuration());
        Picasso.with(context)
                .load(item.getFiles().getLandscapeImage240().getUrl())
                .into(holder.imageView);
        holder.border.setVisibility(position==selectedItem ? View.VISIBLE: View.INVISIBLE);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(position);
                if(listener!=null) {
                    listener.updateClipId(items.get(selectedItem).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Datum> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Datum getDatumAtSelectedItem() {
        return items.get(selectedItem);
    }

    private void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView textView;
        ImageView border;

        private RecyclerViewHolder(final View v) {
            super(v);
            view = v;
            imageView = v.findViewById(R.id.imageview);
            textView = v.findViewById(R.id.textview_duration_value);
            border = v.findViewById(R.id.border);
        }
    }
}