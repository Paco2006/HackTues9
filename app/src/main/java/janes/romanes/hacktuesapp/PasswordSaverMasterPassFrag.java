package janes.romanes.hacktuesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordSaverMasterPassFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordSaverMasterPassFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PasswordSaverMasterPassFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PasswordSaverMasterPassFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordSaverMasterPassFrag newInstance(String param1, String param2) {
        PasswordSaverMasterPassFrag fragment = new PasswordSaverMasterPassFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Button btnEnter;
    private EditText passwordInput;

    private String masterPass = "1234";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        btnEnter = getView().findViewById(R.id.btnEnter);
        passwordInput = getView().findViewById(R.id.passwordInput);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterPassword(passwordInput.getText().toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_saver_master_pass, container, false);
    }

    private void enterPassword(String pass)
    {
        if(pass == masterPass)
        {
            // Go to saved passwords
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPasswordSaverMaster, fm.findFragmentById(R.id.fragmentSavedPasswords));
        }
        else
        {
            Toast.makeText(getContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();

        }
    }
}