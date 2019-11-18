package com.example.paijoov1.Fragment_for_Main;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.paijoov1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment {

    private WebView webView;

    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);
        webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://edition.cnn.com/");
        //webView.loadUrl("https://www.bbc.com/");
        webView.loadUrl("https://www.bangkokpost.com/");

        return view;
    }

}
