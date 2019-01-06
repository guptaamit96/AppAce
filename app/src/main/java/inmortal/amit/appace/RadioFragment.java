package inmortal.amit.appace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;


import inmortal.amit.appace.adapter.MusicAdapterHome;

public class RadioFragment extends Fragment {

    private RecyclerView musicList;
    ArrayList<Integer> imageArray=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_radio, container, false);
        initView(v);

        imageArray.add(R.drawable.jacksradio);
        imageArray.add(R.drawable.xtra);

        setAction();

        return v;
    }

    private void setAction() {
        musicList.setAdapter(new MusicAdapterHome(getActivity(),imageArray));
    }

    private void initView(View view) {
        musicList = (RecyclerView) view.findViewById(R.id.music_list);
        musicList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
