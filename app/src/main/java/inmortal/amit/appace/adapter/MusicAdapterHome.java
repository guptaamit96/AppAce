package inmortal.amit.appace.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.ArrayList;

import inmortal.amit.appace.Constant;
import inmortal.amit.appace.MusicFragment;
import inmortal.amit.appace.R;
import inmortal.amit.appace.RadioFragment;

public class MusicAdapterHome extends RecyclerView.Adapter <MusicAdapterHome.MyViewHolder>{

    ArrayList<Integer> imageArray;
    FragmentActivity fragmentActivity;
    Bundle bundle;
    Fragment fragment;

    public MusicAdapterHome(FragmentActivity fragmentActivity,ArrayList<Integer> imageArray) {
        this.imageArray = imageArray;
        this.fragmentActivity=fragmentActivity;
        bundle=new Bundle();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageMusic;
        public MyViewHolder(View v) {
            super(v);
            initViews();
        }

        void initViews(){
            imageMusic = (ImageView) itemView.findViewById(R.id.image_music);
        }

        void setAction(){
            imageMusic.setImageResource(imageArray.get(getAdapterPosition()));
            imageMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()==0){
                        fragment =new MusicFragment();
                        /*bundle.putString("stringUrlKey","http://aceofjacks.primcast.com:6096/;stream.mp3");
                        fragment.setArguments(bundle);*/

                        Constant.getConstant(fragmentActivity).setDefaultSelectedURl("http://aceofjacks.primcast.com:6096/;stream.mp3");
                        Constant.getConstant(fragmentActivity).setDefaultSelectedPosition(getAdapterPosition());

                        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                                fragment).commit();
                        /*Intent i = new Intent(v.getContext(),MusicFragment.class);
                        i.putExtra("stringURL","http://aceofjacks.primcast.com:6096/;stream.mp3");
                        v.getContext().startActivity(i);*/
                      //  itemView.getContext().startActivity(browserIntent);
                    }else {

                        fragment =new MusicFragment();
                        /*bundle.putString("stringUrlKey","http://aceofjacks5.primcast.com:6106/;stream.mp3");
                        fragment.setArguments(bundle);*/

                        Constant.getConstant(fragmentActivity).setDefaultSelectedURl("http://aceofjacks5.primcast.com:6106/;stream.mp3");
                        Constant.getConstant(fragmentActivity).setDefaultSelectedPosition(getAdapterPosition());

                        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                                fragment).commit();
                        /*Intent i = new Intent(v.getContext(),MusicFragment.class);
                        i.putExtra("http://aceofjacks5.primcast.com:6106/;stream.mp3","stringURL");
                        v.getContext().startActivity(i);*/
                        //itemView.getContext().startActivity(browserIntent);
                    }
                }
            });
        }
    }

    @Override
    public MusicAdapterHome.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_music,parent,false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setAction();
    }

    @Override
    public int getItemCount() {
        return imageArray.size();
    }
}
