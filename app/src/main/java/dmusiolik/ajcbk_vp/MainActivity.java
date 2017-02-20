package dmusiolik.ajcbk_vp;

import android.app.AlertDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
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
        displaytoast("Neues Datenblatt angefordert.");
    }

    public void browse() {
        //Setze Parameter für Webbrowser
        browser = (WebView) findViewById(R.id.webViewIV);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        browser.getSettings().setSupportMultipleWindows(false);
        browser.getSettings().setSupportZoom(false);
        browser.setVerticalScrollBarEnabled(false);
        browser.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        browser.getSettings().setAllowFileAccess( true );
        browser.getSettings().setAppCacheEnabled( true );
        browser.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        if ( !isNetworkAvailable() ) { // loading offline
            //browser.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ONLY );
            displaytoast("Zeige Offline version an!");
        }

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
                displaytoast("Erledigt!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Toast funktion
    public void displaytoast(String msg) {
        Toast.makeText(MainActivity.this,
                msg, Toast.LENGTH_SHORT).show();
    }

    //Check if there is a connection to the Internet
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
