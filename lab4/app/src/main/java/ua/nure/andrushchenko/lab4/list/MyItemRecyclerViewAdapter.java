package ua.nure.andrushchenko.lab4.list;

import android.graphics.Bitmap;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.nure.andrushchenko.lab4.R;
import ua.nure.andrushchenko.lab4.service.Note;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;

    public MyItemRecyclerViewAdapter(List<Note> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.note_title.setText(mValues.get(position).getTitle());
        holder.note_desc.setText(mValues.get(position).getDesc());
        if (mValues.get(position).getPicture() != null) {
            holder.note_image.setImageBitmap(Bitmap.createBitmap(mValues.get(position).getPicture()));
        } else {
            holder.note_image.setImageResource(R.drawable.ic_launcher_background);
        }
        DateFormat.format("yyyy-MM-dd hh:mm:ss a", mValues.get(position).getDate());
        holder.note_date.setText(DateFormat.format("yyyy-MM-dd hh:mm:ss a", mValues.get(position).getDate()));

        if (mValues.get(position).getImportance() == 0) {
            holder.note_importance.setImageResource(R.drawable.level1);
        } else if (mValues.get(position).getImportance() == 1) {
            holder.note_importance.setImageResource(R.drawable.level2);
        } else if (mValues.get(position).getImportance() == 2) {
            holder.note_importance.setImageResource(R.drawable.level3);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView note_title;
        public final TextView note_desc;
        public final ImageView note_image;
        public final TextView note_date;
        public final ImageView note_importance;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            note_title = (TextView) view.findViewById(R.id.note_title);
            note_desc = (TextView) view.findViewById(R.id.note_desc);
            note_image = (ImageView) view.findViewById(R.id.note_image);
            note_date = (TextView) view.findViewById(R.id.note_date);
            note_importance = (ImageView) view.findViewById(R.id.note_importance);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + note_desc.getText() + "'";
        }
    }
}