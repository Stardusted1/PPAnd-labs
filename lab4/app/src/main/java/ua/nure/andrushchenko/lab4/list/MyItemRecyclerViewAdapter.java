package ua.nure.andrushchenko.lab4.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.nure.andrushchenko.lab4.NoteEditing;
import ua.nure.andrushchenko.lab4.R;
import ua.nure.andrushchenko.lab4.dummy.NotesManager;
import ua.nure.andrushchenko.lab4.service.Note;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final Activity activity;

    //declare interface
    private OnItemClicked onClick;

    private final List<Note> mValues;

    public MyItemRecyclerViewAdapter(List<Note> items, Context context, Activity activity) {
        mValues = items;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.mView.setOnClickListener((arg0) -> {
            Intent intent = new Intent(activity, NoteEditing.class);
            Long noteId = Long.parseLong(((TextView)((LinearLayout) arg0).getChildAt(0)).getText().toString());
            intent.putExtra("CURRENT_ID", noteId);
            activity.startActivityForResult(intent, 100);
            Log.v("TAG", "CLICKED row number: " + arg0);
        });
        super.onBindViewHolder(holder, position, payloads);
    }

    @NonNull
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
        holder.note_id.setText(String.valueOf(mValues.get(position).getId()));
        holder.note_title.setText(mValues.get(position).getTitle());
        holder.note_desc.setText(mValues.get(position).getDesc());
        if (mValues.get(position).getPicture() != null) {
            holder.note_image.setImageDrawable((mValues.get(position).getPicture()));
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
        public final TextView note_id;
        public final TextView note_title;
        public final TextView note_desc;
        public final ImageView note_image;
        public final TextView note_date;
        public final ImageView note_importance;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            note_id = view.findViewById(R.id.note_id);
            note_title = view.findViewById(R.id.note_title);
            note_desc = view.findViewById(R.id.note_desc);
            note_image = view.findViewById(R.id.note_image);
            note_date = view.findViewById(R.id.note_date);
            note_importance = view.findViewById(R.id.note_importance);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + note_desc.getText() + "'";
        }
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }
}