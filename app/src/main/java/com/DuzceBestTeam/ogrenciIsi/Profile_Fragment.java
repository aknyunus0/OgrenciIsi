package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Fragment extends Fragment {
    private  Button editprofile;
    private Context context;
    private TextView Konum;
    private TextView profile_name;
    private TextView Üniversite;
    private TextView hakkindatext;
    private TextView uzmanlikalantext;
    private TextView bolum;
    private TextView Egitimduzeyi;
    Button signOut;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_Fragment newInstance(String param1, String param2) {
        Profile_Fragment fragment = new Profile_Fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile_, container, false);
        // Inflate the layout for this fragment
        editprofile=view.findViewById(R.id.editprofile);
        context=view.getContext();
        profile_name = view.findViewById(R.id.profile_name);
        Konum = view.findViewById(R.id.Konum);
        Üniversite = view.findViewById(R.id.Üniversite);
        hakkindatext = view.findViewById(R.id.hakkindatext);
        uzmanlikalantext = view.findViewById(R.id.uzmanlikalantext);
        bolum = view.findViewById(R.id.bolum);
        Egitimduzeyi = view.findViewById(R.id.Egitimduzeyi);
        signOut = view.findViewById(R.id.signOut);

        Konum.setText(User.userLocation);
        Üniversite.setText(User.userUniversity);
        hakkindatext.setText(User.userAbout);
        uzmanlikalantext.setText(User.userExpert);
        bolum.setText(User.userDepartman);
        Egitimduzeyi.setText(User.userGrade);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),LoginPage.class));
            }
        });


        profile_name.setText(User.userName + " " + User.userSurName);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileEdit.class);

                startActivity(intent);
            }
        });




       return view;


    }

}
