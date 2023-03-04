package com.example.travel.ui.favourites;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.Adapters.FavAdapter;
import com.example.travel.Helpers.TravelDB;
import com.example.travel.Models.FavItem;
import com.example.travel.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private TravelDB travelDB;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favourite, container, false);


        travelDB = new TravelDB(getActivity());
        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        loadData();
        
        return root;
    }

    private void loadData() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = travelDB.getReadableDatabase();
        Cursor cursor = travelDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {

                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(TravelDB.ITEM_TITLE));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(TravelDB.KEY_ID));
                @SuppressLint("Range") int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(TravelDB.ITEM_IMAGE)));
                @SuppressLint("Range") String hoteldesc = cursor.getString(cursor.getColumnIndex(TravelDB.HOTEL_DESC));

                FavItem favItem = new FavItem(title, id, image, hoteldesc);
                favItemList.add(favItem);
            }
        }finally {
            if (cursor !=null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favAdapter = new FavAdapter(getActivity(), favItemList);

        recyclerView.setAdapter(favAdapter);
    }
}
