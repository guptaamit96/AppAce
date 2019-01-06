package inmortal.amit.appace;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import inmortal.Retrofit.ImageResponse;
import inmortal.Retrofit.WebServiceRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicFragment extends Fragment {

    ImageView play;
    private SeekBar volumeSeekbar;
    ImageButton mBack,mNext;
    public static MediaPlayer mediaPlayer;
    ImageView mArtistImage;
    TextView tx;
    PlayerState playerState;
    private AudioManager audioManager = null;
    private ProgressBar progressDialog;
    Thread t;
    private boolean isPlayerStart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_music, container, false);
        initView(view);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setMusicActions();
        initControls();
        return view;
}
  private void setMusicActions() {
    if(mediaPlayer==null) {

        progressDialog.setVisibility(View.VISIBLE);

        mediaPlayer = new MediaPlayer();

    }

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              play.performClick();
          }
      },2000);

    mBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(isPlayerStart){
                if(Constant.getConstant(getActivity()).getDefaultSelectedPosition() == 0){
                    Constant.getConstant(getActivity()).setDefaultSelectedURl("http://aceofjacks.primcast.com:6096/;stream.mp3");
                    playerState.isPaused = false;
                    mediaPlayer.pause();
                    setMusicActions();
                }else {
                    Constant.getConstant(getActivity()).setDefaultSelectedURl("http://aceofjacks5.primcast.com:6106/;stream.mp3");
                    playerState.isPaused = false;
                    mediaPlayer.pause();
                    setMusicActions();
                }
            }else {
                Toast.makeText(getActivity(), "Please wait media player is preparing", Toast.LENGTH_SHORT).show();
            }
        }
    });

    mNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(isPlayerStart){
                if(Constant.getConstant(getActivity()).getDefaultSelectedPosition() == 0){
                    Constant.getConstant(getActivity()).setDefaultSelectedURl("http://aceofjacks5.primcast.com:6106/;stream.mp3");
                    playerState.isPaused = false;
                    mediaPlayer.pause();
                    setMusicActions();
                }else {
                    Constant.getConstant(getActivity()).setDefaultSelectedURl("http://aceofjacks.primcast.com:6096/;stream.mp3");
                    playerState.isPaused = false;
                    mediaPlayer.pause();
                    setMusicActions();
                }

                //i.putExtra("stringURL","http://aceofjacks.primcast.com:6096/;stream.mp3");
                //i.putExtra("http://aceofjacks5.primcast.com:6106/;stream.mp3","stringURL");
            }
        }
    });


    if(playerState==null) {
        playerState=new PlayerState();
    }

    play.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!mediaPlayer.isPlaying()) {
                playMusicInit();
                t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (!isInterrupted()) {
                                Thread.sleep(2000);
                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        public void run() {

                                            new Test().execute();

                                            if (!Updatevalue.Name.equalsIgnoreCase("") || !Updatevalue.Artist.equalsIgnoreCase("")) {
                                                tx.setText("Song Title :" + " " + Updatevalue.Name + "\nArtist Name :" + " " + Updatevalue.Artist);
                                                hitLoginApi(Updatevalue.Artist);
                                            }
                                        }
                                    });
                                }
                                catch (Exception e){
                                    e.printStackTrace();Thread.sleep(50000);
                                }
                            }

                        } catch (InterruptedException e) {
                        }
                    }
                };

                t.start();

            }else {
                play.setImageResource(R.drawable.ply);
                mediaPlayer.pause();
                playerState.isPaused = true;
                playerState.isPlaying = false;
            }

        }
    });

    
}

    private void hitLoginApi(final String artistName) {
        //  progressDialog.show();
        WebServiceRequest.getInstance().hitLOGIN(
                artistName,
                new Callback<ImageResponse>() {
                    @Override
                    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            try {
                                if (response.body().getArtist().getImage().get(5).get_$Text81().equalsIgnoreCase("")) {
                                    Picasso.with(getContext()).load(R.drawable.applogo).into(mArtistImage);
                                } else {
                                    Picasso.with(getContext()).load(response.body().getArtist().getImage().get(5).get_$Text81()).into(mArtistImage);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageResponse> call, Throwable t) {
                        Log.d("error",t.getMessage());
                    }
                });
    }

    private void playMusicInit()
    {
        progressDialog.setVisibility(View.VISIBLE);

        if (!playerState.isPaused) {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //Try to play music/audio from url
            try {

                mediaPlayer.setDataSource(Constant.getConstant(getActivity()).getDefaultSelectedURl());

                //mediaPlayer.prepareAsync();
                mediaPlayer.prepare();

                // Start playing audio from http url
                mediaPlayer.start();
                isPlayerStart =true;
                //play.setEnabled(true);
                playerState.isPlaying = true;
                playerState.isPaused = false;

                play.setImageResource(R.drawable.p);
//                play.setVisibility(View.VISIBLE);
                progressDialog.setVisibility(View.INVISIBLE);

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Toast.makeText(getContext(), "Buffring Complete", Toast.LENGTH_SHORT).show();
                    }
                });

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //Toast.makeText(getContext(),"End",Toast.LENGTH_SHORT).show();

                    }
                });

            } catch (IOException e) {
                // Catch the exception
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } else {
            mediaPlayer.start();

            //play.setEnabled(true);
            playerState.isPlaying = true;
            playerState.isPaused = false;

            play.setImageResource(R.drawable.p);
            play.setVisibility(View.VISIBLE);
            progressDialog.setVisibility(View.INVISIBLE);

        }

    }

    private void initView(View view){
        volumeSeekbar=(SeekBar) view.findViewById(R.id.seekbar);
        play = (ImageView) view. findViewById(R.id.butn);
        tx = (TextView) view.findViewById(R.id.tv1);
        mArtistImage=(ImageView)view.findViewById(R.id.artistImage);
        progressDialog = (ProgressBar) view.findViewById(R.id.progressbar);
        mBack=(ImageButton) view.findViewById(R.id.rewind);
        mNext=(ImageButton) view.findViewById(R.id.forword);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mediaPlayer.isPlaying()){
            play.setImageResource(R.drawable.p);
        }else {
            play.setImageResource(R.drawable.ply);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initControls(){
        try {
            audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Test extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(Constant.getConstant(getActivity()).getDefaultSelectedURl());
                ParsingHeaderData streaming = new ParsingHeaderData();
                ParsingHeaderData.TrackData trackData = streaming.getTrackDetails(url);
                Log.d("Song Artist Name ", trackData.artist);
                Log.d("Song Artist Title", trackData.title);
                Updatevalue.Artist = trackData.artist;
                Updatevalue.Name = trackData.title;
                // mTitel.setText("Song Title :"+" "+Updatevalue.Name+"\nArtist Name :"+" "+Updatevalue.Artist);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    }
