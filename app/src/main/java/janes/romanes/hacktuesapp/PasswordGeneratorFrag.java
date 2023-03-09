package janes.romanes.hacktuesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordGeneratorFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordGeneratorFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PasswordGeneratorFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PasswordGeneratorFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordGeneratorFrag newInstance(String param1, String param2) {
        PasswordGeneratorFrag fragment = new PasswordGeneratorFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private EditText usernameEditText, passwordEditText;
    private Button regeneratePassBtn, savePassBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        usernameEditText = getView().findViewById(R.id.usernameEditText);
        passwordEditText = getView().findViewById(R.id.passwordEditText);
        regeneratePassBtn = getView().findViewById(R.id.regeneratePassBtn);
        savePassBtn = getView().findViewById(R.id.savePassBtn);

        passwordEditText.setText(generatePassword(16));

        regeneratePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePassword(16);
            }
        });

        savePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_generator, container, false);
    }

    private String generatePassword(int size)
    {
        String str = "";
        String setOfChars = "abcdefghijklmnopqrstuvwxyz1234567890-/@&*"; // This variable can be modified with added checkboxes

        Random rand = new Random();

        if(size <= 0)
        {
            // Send toast msg
            return null;
        }

        for(int i = 0; i < size; i++)
        {
            str += setOfChars.charAt(rand.nextInt(setOfChars.length()));
        }

        return str;
    }

    private void saveUser(String username, String password)
    {
        SavedPasswordsFrag.users.add(new User(username, password));
        // Return to fragment with saved passwords
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentPasswordGenerator, fm.findFragmentById(R.id.fragmentSavedPasswords));
    }
}