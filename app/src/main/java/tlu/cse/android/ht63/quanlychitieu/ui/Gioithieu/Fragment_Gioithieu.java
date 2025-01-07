package tlu.cse.android.ht63.quanlychitieu.ui.Gioithieu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.BTL_Quanlychitieu.R;

public class Fragment_Gioithieu extends Fragment {

    WebView webView;
    public Fragment_Gioithieu() {
    }
    public static Fragment_Gioithieu newInstance(String param1, String param2) {
        Fragment_Gioithieu fragment = new Fragment_Gioithieu();
        return fragment;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = (WebView) view.findViewById(R.id.webview);
        webView.setWebViewClient(new Mywebviewclient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://cafef.vn/thi-truong-chung-khoan.chn");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__gioithieu, container, false);
    }
}

class Mywebviewclient extends WebViewClient{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }
}