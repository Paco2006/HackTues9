package janes.romanes.hacktuesapp;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordSaverMasterPassFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PasswordSaverMasterPassFrag() {

    }

    public static PasswordSaverMasterPassFrag newInstance(String param1, String param2) {
        PasswordSaverMasterPassFrag fragment = new PasswordSaverMasterPassFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button btnEnter, btnChangePass;
    private EditText passwordInput;

    public static String masterPass = "1234";

    Activity activity;
    View parentHolder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_password_saver_master_pass, container, false);
        btnEnter = parentHolder.findViewById(R.id.btnEnter);
        btnChangePass = parentHolder.findViewById(R.id.btnChangePass);
        passwordInput = parentHolder.findViewById(R.id.passwordInput);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterPassword(passwordInput.getText().toString());
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return parentHolder;
    }

    private void enterPassword(String pass)
    {
        if(pass.compareTo(masterPass) == 0)
        {
            Navigation.findNavController(getView()).navigate(R.id.toPassSavNot);
        }
        else
        {
            Toast.makeText(getContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeMasterPassword()
    {

    }
}