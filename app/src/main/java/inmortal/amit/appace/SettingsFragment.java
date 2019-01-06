package inmortal.amit.appace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import inmortal.amit.appace.adapter.SettingAdapter;

//import com.inmortaltech.aceapp.adapter.SettingAdapter;

public class SettingsFragment extends Fragment {

    ListView lv;

    int images[] = {R.drawable.fb,
                    R.drawable.insta,
                    R.drawable.twitter,
                    R.drawable.e,
                    R.drawable.call,
                    R.drawable.websitelogo
    };

    String[] text = {"Facebook", "Instagram", "Twitter", "Email", "Contact:+447961977707","Website"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


      ListView  lv = (ListView) view.findViewById(R.id.listView);
      lv.setAdapter(new SettingAdapter(getActivity(),text,images));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent intent = new Intent(getActivity(), Facebook.class);
                    intent.putExtra("FBDATA", "https://www.facebook.com/aceofjacksent");
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(), Facebook.class);
                    intent.putExtra("FBDATA", "https://www.instagram.com/aceofjacks/");
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getActivity(), Facebook.class);
                    intent.putExtra("FBDATA", "https://twitter.com/aceofjacks");
                    startActivity(intent);
                } else if (position == 3) {
                    // Intent intent = new Intent(getActivity(), Facebook.class);
                    // intent.putExtra("FBDATA","https://www.gmail.com");
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "info@aceofjacks.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                    getActivity().startActivity(Intent.createChooser(emailIntent, null));
                    // startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(getActivity(), Facebook.class);
                    String phone = "+447961977707";
                    intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                }
                else if (position == 5) {
                    Intent intent = new Intent(getActivity(), Facebook.class);
                    intent.putExtra("FBDATA", "http://aceofjacks.com/contact");
                    startActivity(intent);
                }
            }
        });

        return view;

        }
}
