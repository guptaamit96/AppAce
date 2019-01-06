package inmortal.amit.appace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Facebook extends AppCompatActivity {
  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("FBDATA");
        setContentView(R.layout.activity_facebook);

        webView=(WebView)findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(value);
    }
}
