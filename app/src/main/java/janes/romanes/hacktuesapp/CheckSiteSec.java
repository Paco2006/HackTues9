package janes.romanes.hacktuesapp;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.net.URL;

public class CheckSiteSec extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CheckSiteSec() {
    }

    public static CheckSiteSec newInstance(String param1, String param2) {
        CheckSiteSec fragment = new CheckSiteSec();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String secure = "The website is secure  ✅";
    String insecure = "⚠️The website isn't secure  ⚠️";
    String existence = "The URL does not exist ❌";
    String sslCheck = "Untrusted SSL certificate!!!";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Activity activity;

    View parentHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentHolder = inflater.inflate(R.layout.fragment_check_site_sec, container, false);

        Button scanBtn = parentHolder.findViewById(R.id.button);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    searchURL();
                } catch (IOException e) {
                    ((TextView) parentHolder.findViewById(R.id.output)).setText(existence);
                }
            }
        });
        return parentHolder;
    }

    public static boolean isValid(String url)
    {
        try
        {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public void searchURL() throws IOException {
        EditText text = parentHolder.findViewById(R.id.search);
        String input = text.getText().toString();
        if(isValid(input)){
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ':') {
                    ((TextView) parentHolder.findViewById(R.id.output)).setText(insecure);
                    break;
                }
                if (input.charAt(i) == 's') {
                    if(SSLCertificateChecker.isCertificateTrustable(input)){
                        ((TextView) parentHolder.findViewById(R.id.output)).setText(secure);
                        break;
                    }else{
                        ((TextView) parentHolder.findViewById(R.id.output)).setText(insecure);
                        ((TextView) parentHolder.findViewById(R.id.SSL)).setText(sslCheck);
                        break;
                    }
                }
            }
        }else ((TextView) parentHolder.findViewById(R.id.output)).setText(existence);
    }
}
