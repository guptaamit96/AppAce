package inmortal.amit.appace.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import inmortal.amit.appace.R;

public class SettingAdapter extends ArrayAdapter<String> {

    Activity mContext;
    int images[];
    String title[];

    public SettingAdapter(@NonNull Activity context, String[] title, int[] images) {
        super(context, 0 , title);
        mContext = context;
        this.title = title;
        this.images = images;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v= mContext.getLayoutInflater().inflate(R.layout.customlayout,null);

        ImageView imageView= (ImageView) v.findViewById(R.id.imageView);
        TextView textView= (TextView) v.findViewById(R.id.textView);

        imageView.setImageResource(images[position]);
        textView.setText(title[position]);

        return  v;



        // return super.getView(position,convertView,parent);
    }


}
