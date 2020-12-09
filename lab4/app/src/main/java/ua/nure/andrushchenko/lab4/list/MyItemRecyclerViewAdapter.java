package ua.nure.andrushchenko.lab4.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ua.nure.andrushchenko.lab4.NoteEditing;
import ua.nure.andrushchenko.lab4.R;
import ua.nure.andrushchenko.lab4.service.Note;
import ua.nure.andrushchenko.lab4.service.NotesManager;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> implements Filterable {

	private final Activity activity;
	public static List<Note> mainValues;

    public MyItemRecyclerViewAdapter(List<Note> items, Context context, Activity activity) {
		mainValues = items;
		this.activity = activity;

    }

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

		public ViewHolder(View view) {
			super(view);
			mView = view;
			note_id = view.findViewById(R.id.note_id);
			note_title = view.findViewById(R.id.note_title);
			note_desc = view.findViewById(R.id.note_desc);
			note_image = view.findViewById(R.id.note_image);
			note_date = view.findViewById(R.id.note_date);
			note_importance = view.findViewById(R.id.note_importance);
			view.setOnCreateContextMenuListener(this);
		}

		@NonNull
		@Override
		public String toString() {
			return super.toString() + " '" + note_desc.getText() + "'";
		}

		@Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
			int noteId = Integer.parseInt(((TextView) ((LinearLayout) v).getChildAt(0)).getText().toString());
			menu.add(noteId, v.getId(), 0, R.string.ctx_change);
			menu.add(noteId, v.getId(), 1, R.string.ctx_delete);
		}

	}
}