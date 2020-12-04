package ua.nure.andrushchenko.lab4.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ua.nure.andrushchenko.lab4.NoteEditing;
import ua.nure.andrushchenko.lab4.R;
import ua.nure.andrushchenko.lab4.dummy.DummyContent;

public class ItemFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));
            recyclerView.setOnClickListener((RecyclerView.OnClickListener) (arg0) -> {
                Intent intent = new Intent(getActivity(), NoteEditing.class);
                intent.putExtra("CURRENT_NOTE", DummyContent.ITEMS.get(arg0.getId()));
                startActivity(intent);
                Log.v("TAG", "CLICKED row number: " + arg0);
            });
        }
        return view;
    }


}