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

<<<<<<< Updated upstream
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }
=======
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
		holder.mView.setOnClickListener((arg0) -> {
			Intent intent = new Intent(activity, NoteEditing.class);
			Long noteId = Long.parseLong(((TextView) ((LinearLayout) arg0).getChildAt(0)).getText().toString());
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
		holder.mItem = mainValues.get(position);
		holder.note_id.setText(String.valueOf(mainValues.get(position).getId()));
		holder.note_title.setText(mainValues.get(position).getTitle());
		holder.note_desc.setText(mainValues.get(position).getDesc());
		if (mainValues.get(position).getImage() != null) {
			holder.note_image.setImageBitmap((mainValues.get(position).getImage()));
		} else {
			holder.note_image.setImageResource(R.drawable.ic_launcher_background);
		}
		holder.note_date.setText(DateFormat.format("MM-dd hh:mm:ss a", mainValues.get(position).getDate()));

		if (mainValues.get(position).getImportance() == 0) {
			holder.note_importance.setImageResource(R.drawable.level1);
		} else if (mainValues.get(position).getImportance() == 1) {
			holder.note_importance.setImageResource(R.drawable.level2);
		} else if (mainValues.get(position).getImportance() == 2) {
			holder.note_importance.setImageResource(R.drawable.level3);
		}
		// TODO: 06.12.2020 add on context set

	}

	@Override
	public int getItemCount() {
		return mainValues.size();
	}

	@Override
	public Filter getFilter() {
		return new Filter() {
			@RequiresApi(api = Build.VERSION_CODES.KITKAT)
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				List<Note> temp = new ArrayList<>();
				String c = constraint.toString();
				if (c.length() > 3) {
					if (c.startsWith("t: ")) {
						for (Note n : NotesManager.getITEMS()) {
							if (n.getTitle().contains(c.substring(3)))
								temp.add(n);
						}
					} else if (c.startsWith("d: ")) {
						for (Note n : NotesManager.getITEMS()) {
							if (n.getDesc().contains(c.substring(3)))
								temp.add(n);
						}
					} else if (c.startsWith("y: ")) {
						for (Note n : NotesManager.getITEMS()) {
							if (n.getDate().toString().contains(c.substring(3)))
								temp.add(n);
						}
					} else if (c.startsWith("i: ")) {
						for (Note n : NotesManager.getITEMS()) {
							if (n.getImportance() == Integer.parseInt(c.substring(3)))
								temp.add(n);
						}
					}
				} else if (c.isEmpty()) {
					temp = NotesManager.getITEMS();
				} else {
					for (Note n : NotesManager.getITEMS()) {
						if (n.toDescString().contains(constraint))
							temp.add(n);
					}
				}
				FilterResults results = new FilterResults();
				results.values = temp;

				return results;
			}

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				mainValues.clear();
				mainValues.addAll((Collection<? extends Note>) results.values);
				notifyDataSetChanged();
			}
		};
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
		public final View mView;
		public final TextView note_id;
		public final TextView note_title;
		public final TextView note_desc;
		public final ImageView note_image;
		public final TextView note_date;
		public final ImageView note_importance;
		public Note mItem;
>>>>>>> Stashed changes


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