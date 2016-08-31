package dmusiolik.ajcbk_vp;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public WebView browser;

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
        //Setze Parameter für Webbrowser
        browser = (WebView) findViewById(R.id.webViewIV);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        browser.getSettings().setSupportMultipleWindows(false);
        browser.getSettings().setSupportZoom(false);
        browser.setVerticalScrollBarEnabled(false);
        browser.loadUrl("http://ajc-bk.dyndns.org:8008/Vertretung-Online/");
        //Parse htmlcode -> Sehr viel Arbeit mit der Mommentanet API -- Vielleicht mal in der Zukunft

        //Navigiere zu...
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                //Über
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
                alertDialog.setTitle("Über");
                alertDialog.setMessage("Dies ist Freie Software unter der GPLv3\nErstellt durch Darius Musiolik 2k16\nhttps://www.GitHub.com/MrFlyingToasterman");
                alertDialog.show();
                return true;
            case R.id.make_mrpropper:
                WebView brow = (WebView) findViewById(R.id.webViewIV);
                brow.clearCache(true);
                //Bereit Benachrichtigung
                Toast.makeText(this, "Erledigt", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
