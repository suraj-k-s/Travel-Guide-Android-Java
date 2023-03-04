package com.example.travel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.travel.CityDetails;
import com.example.travel.Models.CityItem;
import com.example.travel.ImageNicer;
import com.example.travel.R;
import com.example.travel.Helpers.TravelDB;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>  {


    // List to store all the  details
        private ArrayList<CityItem> cityItems;
        private Context context;
        private TravelDB travelDB;

    // Counstructor for the Class
        public CityAdapter(ArrayList<CityItem> cityItems, Context context){
            this.cityItems = cityItems;
            this.context = context;
        }


    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the
        @NonNull
        @Override
        public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            travelDB = new TravelDB(context);
            //Create table first
            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart",true);
            if(firstStart){
                createTableOnFirstStart();
            }


            // Inflate the layout view you have created for the list rows here
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cityitem,
                    parent,false);
            return new CityAdapter.ViewHolder(view);



        }


    // This method is called when binding the data to the views being created in RecyclerView
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final CityItem cityItem= cityItems.get(position);

            readCursorData(cityItem,holder);
            // Set the data to the views here
            //Image pixel problem solved
            holder.imageView1.setImageBitmap(ImageNicer.decodeSampledBitmapFromResource(context.getResources(),cityItems.get(position).getImageResourse1(),100,100));
            holder.title1TextView.setText(cityItem.getTitle1());
            holder.citydescTextView.setText(cityItem.getCitydesc());


            //set click listners to indvidual items in the viewholder
            holder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {   //set on click for cities list
                    Intent intent = new Intent(context, CityDetails.class);
                    intent.putExtra("image1",cityItem.getImageResourse1());
                    intent.putExtra("title1TextView",cityItem.getTitle1());
                    intent.putExtra("countryNameTextView",cityItem.getHotelname());
                    intent.putExtra("citydescTextView",cityItem.getCitydesc());



                    context.startActivity(intent);
                }
            });
        }



        @Override
        public int getItemCount() {
            return cityItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView1;
            TextView title1TextView;
            TextView countryNameTextView;
            TextView citydescTextView;
            RelativeLayout relative;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView1 = itemView.findViewById(R.id.imageView1);
                title1TextView= itemView.findViewById(R.id.title1TextView);
                citydescTextView = itemView.findViewById(R.id.citydescTextView);
                relative = itemView.findViewById(R.id.relative);


            }
        }

        private void createTableOnFirstStart(){
            travelDB.insertEmpty();

            SharedPreferences prefs= context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart",false);
            editor.apply();
        }
        private void readCursorData(CityItem cityItem, CityAdapter.ViewHolder viewHolder) {
            Cursor cursor = travelDB.read_all_city_data(cityItem.getKey_id1());
            SQLiteDatabase db = travelDB.getReadableDatabase();

            }



    }
