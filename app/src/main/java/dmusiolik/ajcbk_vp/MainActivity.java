package dmusiolik.ajcbk_vp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        browse();
    }

    public void reload(View view) {
        browse();
    }

    public void browse() {
        browser = (WebView) findViewById(R.id.webViewIV);
        //browser.setHttpAuthUsernamePassword("http://ajc-bk.dyndns.org:8008", "", LoginActivity.benutzer, "testpasswort");
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        browser.getSettings().setSupportMultipleWindows(false);
        browser.getSettings().setSupportZoom(false);
        browser.setVerticalScrollBarEnabled(false);
        browser.loadUrl("http://ajc-bk.dyndns.org:8008/Vertretung-Online/");
        //Parse htmlcode
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedHttpAuthRequest(WebView view,
                                                  HttpAuthHandler handler, String host, String realm) {

                handler.proceed(LoginActivity.benutzer, LoginActivity.passwort);

            }
        });

        //webview.setWebViewClient(new HelloWebViewClient());
    }

}
