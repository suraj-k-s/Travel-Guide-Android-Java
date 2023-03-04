package com.example.travel.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.travel.Models.HotelItem;
import com.example.travel.Details;
import com.example.travel.ImageNicer;
import com.example.travel.R;
import com.example.travel.Helpers.TravelDB;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private ArrayList<HotelItem> hotelItems;
    private Context context;
    private TravelDB travelDB;

    public HotelAdapter(ArrayList<HotelItem> hotelItems, Context context){
        this.hotelItems = hotelItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        travelDB = new TravelDB(context);
        //Create table first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(firstStart){
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.ViewHolder holder, int position) {
        final HotelItem hotelItem= hotelItems.get(position);

        readCursorData(hotelItem,holder);
        holder.imageView.setImageBitmap(ImageNicer.decodeSampledBitmapFromResource(context.getResources(),hotelItems.get(position).getImageResourse(),300,300));
        holder.titleTextView.setText(hotelItem.getTitle());
        holder.hotelDescTextView.setText(hotelItem.getHoteldesc());
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //set on click for home countries
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("image",hotelItem.getImageResourse());
                intent.putExtra("titleTextView",hotelItem.getTitle());
                intent.putExtra("hotelDescTextView",hotelItem.getHoteldesc());

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return hotelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView hotelDescTextView;
        TextView otherTextView;
        Button favBtn;
        RelativeLayout relative;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView= itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            hotelDescTextView =itemView.findViewById(R.id.hotelDesctextView);
            relative = itemView.findViewById(R.id.relative);

            //add to fav button
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    HotelItem hotelItem = hotelItems.get(position);

                    if (hotelItem.getFavStatus().equals("0")) {
                        hotelItem.setFavStatus("1");
                        travelDB.insertIntoTheDatabase(hotelItem.getTitle(), hotelItem.getImageResourse(), hotelItem.getKey_id(), hotelItem.getFavStatus(), hotelItem.getHoteldesc());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    }else{
                        hotelItem.setFavStatus("0");
                        travelDB.remove_fav(hotelItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    }
                }

            });

        }
    }

    private void createTableOnFirstStart(){
        travelDB.insertEmpty();

        SharedPreferences prefs= context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }
    private void readCursorData(HotelItem hotelItem, ViewHolder viewHolder) {
        Cursor cursor = travelDB.read_all_data(hotelItem.getKey_id());
        SQLiteDatabase db = travelDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                @SuppressLint("Range") String item_fav_status = cursor.getString(cursor.getColumnIndex(TravelDB.FAVORITE_STATUS));
                hotelItem.setFavStatus(item_fav_status);

                //check favourite status
                if (item_fav_status !=null && item_fav_status.equals("1")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);

                }else if (item_fav_status !=null && item_fav_status.equals("0")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }
            }
        }finally {
            if (cursor !=null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }


}
