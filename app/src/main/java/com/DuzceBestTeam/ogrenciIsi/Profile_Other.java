package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Other extends Fragment {
    private  Button other_editprofile;
    private Context context;
    private TextView other_Konum;
    private TextView other_profile_name;
    private TextView other_hakkindatext;
    private TextView other_uzmanlikalantext;
    ShapeableImageView other_profileImage;
    Button other_signOut;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_Other() {
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
    public static Profile_Other newInstance(String param1, String param2) {
        Profile_Other fragment = new Profile_Other();
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

        View view=inflater.inflate(R.layout.fragment_profile_other, container, false);
        // Inflate the layout for this fragment
        other_editprofile=view.findViewById(R.id.other_editprofile);
        context=view.getContext();
        other_profile_name = view.findViewById(R.id.other_profile_name);
        other_Konum = view.findViewById(R.id.other_Konum);

        other_hakkindatext = view.findViewById(R.id.other_hakkindatext);
        other_uzmanlikalantext = view.findViewById(R.id.other_uzmalikAlani);

        other_profileImage = view.findViewById(R.id.other_profile_image1);
        other_signOut = view.findViewById(R.id.other_signOut);

        other_Konum.setText(User.userLocation);
        other_uzmanlikalantext.setText(User.userExpert);

        other_hakkindatext.setText(User.userAbout);


        other_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),LoginPage.class));
            }
        });

        Uri uri=Uri.parse(User.userprofileimage);
        Glide
                .with(context)
                .load(uri) // the uri you got from Firebase
                .centerCrop()
                .into(other_profileImage); //Your imageView variable


        other_profile_name.setText(User.userName + " " + User.userSurName);

        other_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileEdit_Other.class);

                startActivity(intent);
            }
        });

        return view;


    }

}
