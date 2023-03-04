package com.example.travel.ui.cities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.Adapters.CityAdapter;
import com.example.travel.Models.CityItem;
import com.example.travel.R;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {


    private ArrayList<CityItem> cityItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cities, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CityAdapter(cityItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        cityItems.add(new CityItem(R.drawable.cntower, "CN Tower","Toronto","0","The CN Tower is a 553.3 m-high concrete communications and observation tower in downtown Toronto, Ontario, Canada. Built on the former Railway Lands, it was completed in 1976. Its name \"CN\" referred to Canadian National, the railway company that built the tower. "));
        cityItems.add(new CityItem(R.drawable.naigra, "Niagra Falls","Canada","1","Niagara Falls : waterfalls on the Niagara River that consist of two principal parts separated by Goat Island: the Horseshoe Falls adjoin the western (Canadian) bank and fall 158 feet (47 m); the American Falls adjoin the eastern (US) bank and fall 167 feet (50 m)"));
        cityItems.add(new CityItem(R.drawable.youge, "Yonge-Dundas","Canada","2","Yonge-Dundas Square is a unique focal point of the downtown Toronto community. The Square is designated for use as a public open space and as an event venue that can accommodate events of various sizes. "));
        cityItems.add(new CityItem(R.drawable.casa, "Casa Loma","Canada","3","Experience the elegance and splendor of the Edwardian era at Casa Loma, the only full sized castle in North America and Toronto’s premier historic attraction brought to you by the Liberty Entertainment Group. Explore secret passageways, elaborately decorated rooms with authentic period furnishings or take in the breathtaking view of Toronto from one of the towers."));
        cityItems.add(new CityItem(R.drawable.zoo, "Toronto Zoo","Toronto","4","With more than 5,000 animals in our care, representing 450 species, the Toronto Zoo is committed to fighting extinction and connecting people to wildlife. Explore award-winning indoor tropical pavilions, including the African Rainforest, home to our baby Western lowland gorilla and pygmy hippo calf. Plus, enjoy daily keeper talks, seasonal animal shows and children’s areas including Splash Island and Kids Zoo. "));
        cityItems.add(new CityItem(R.drawable.sciense, "Ontario Science","Canada","5","Where questions spark discovery! The Ontario Science Centre invites you to an exciting visit full of exploration, adventure and innovation! Visitors of all ages can explore more than 500 interactive exhibits, take in live science demonstrations, check out a real-life rain forest and a science arcade and discover the wonders of the galaxy in the Space Hall, home to Toronto’s only public planetarium. "));



        return root;
    }
}
