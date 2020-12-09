package ua.nure.andrushchenko.lab4.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ua.nure.andrushchenko.lab4.NoteEditing;
import ua.nure.andrushchenko.lab4.R;
import ua.nure.andrushchenko.lab4.service.NotesManager;

public class ItemFragment extends Fragment {

	public static MyItemRecyclerViewAdapter adapter;
	public static RecyclerView list;


	public ItemFragment() {
	}

	public static ItemFragment newInstance() {
		ItemFragment fragment = new ItemFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onContextItemSelected(@NonNull MenuItem item) {
		Long noteId = (long) item.getGroupId();
		switch (item.getOrder()) {
			case 0: {
				Intent intent = new Intent(getActivity(), NoteEditing.class);
				intent.putExtra("CURRENT_ID", noteId);
				getActivity().startActivity(intent);
			}
			case 1: {
				NotesManager.deleteItem(NotesManager.getItemMap().get(noteId));
				adapter.notifyDataSetChanged();
			}
		}


		return super.onContextItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		adapter.notifyDataSetChanged();
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_item_list, container, false);
		adapter = new MyItemRecyclerViewAdapter(NotesManager.getITEMS(), getContext(), getActivity());
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			recyclerView.setLayoutManager(new LinearLayoutManager(context));
			Log.v("TAG", "CLICKED row number: ");
			recyclerView.setAdapter(adapter);
			registerForContextMenu(recyclerView);
		}
		return view;
	}


}