package janes.romanes.hacktuesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckSiteSec extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckSiteSec() {
        // Required empty public constructor
    }

    public static CheckSiteSec newInstance(String param1, String param2) {
        CheckSiteSec fragment = new CheckSiteSec();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Button scanBtn;
    String secure = "The website is secure  ✅";
    String insecure = "⚠️The website isn't secure  ⚠️";
    String error = "This website can't be reached!";
    String existence = "The URL does not exist ❌";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        scanBtn = getView().findViewById(R.id.button);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    searchURL();
                } catch (IOException e) {
                    ((TextView) getView().findViewById(R.id.output)).setText(error);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_site_sec, container, false);
    }

    public void searchURL() throws IOException {
        EditText text = getView().findViewById(R.id.search);
        String input = text.getText().toString();
        URL url;
        HttpURLConnection huc = null;
        try {
            url = new URL(input);
            huc = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            ((TextView) getView().findViewById(R.id.output)).setText(error);
        }
        int responseCode = huc.getResponseCode();
        if(responseCode!=404){
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ':') {
                    ((TextView) getView().findViewById(R.id.output)).setText(insecure);
                    break;
                }
                if (input.charAt(i) == 's') {
                    ((TextView) getView().findViewById(R.id.output)).setText(secure);
                    break;
                }
            }
        }else ((TextView) getView().findViewById(R.id.output)).setText(existence);
    }
}
