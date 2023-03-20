package com.example.kapk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Fragment extends Fragment {
    private Profile profile;
    private DatabaseReference reference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private TextView Mobileno;
    private TextView secondMobileno;
    private TextView Address;
    private TextView edit;
    private ConstraintLayout profilelayout,createProfilelayout;



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
     * Use this factory method to create a new instance of-
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        final TextView name = view.findViewById(R.id.profile_name);
        final TextView email = view.findViewById(R.id.profile_email);
        reference = FirebaseDatabase.getInstance().getReference();
        profilelayout = view.findViewById(R.id.profilelayout);
        createProfilelayout = view.findViewById(R.id.createprofilelayout);
        final ProgressBar progressBar = view.findViewById(R.id.progressbar5);
            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();
            if (user != null) {
                name.setText(user.getDisplayName());
                email.setText(user.getEmail());
            }
            final EditText mobileno = view.findViewById(R.id.profile_edit_mobileNo);
            final EditText address = view.findViewById(R.id.profile_edit_address);
            final EditText secondmobileno = view.findViewById(R.id.profile_edit_mobileNoSecond);
            final TextView save = view.findViewById(R.id.profile_Save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    mobileno.setError(null);
                    address.setError(null);
                    secondmobileno.setError(null);
                    String mobilenoText = mobileno.getText().toString();
                    String addressText = address.getText().toString();
                    String SecondmobilenoText = secondmobileno.getText().toString();
                    // reference= FirebaseDatabase.getInstance().getReference();
                    if (TextUtils.isEmpty(mobilenoText)) {
                        mobileno.setError("Enter a mobile no.");
                    } else if (TextUtils.isEmpty(addressText)) {
                        address.setError("Enter a Address");
                    } else if (TextUtils.isEmpty(SecondmobilenoText)) {
                        secondmobileno.setError(("Enter a Second Mobile no."));
                    } else {
                        profile = new Profile();
                        profile.setMobileno(mobilenoText);
                        profile.setAddress(addressText);
                        profile.setSecondmobileno(SecondmobilenoText);
                        reference.child("Profiles").child(user.getUid()).setValue(profile)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getActivity(), "Pofile set Successfully", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(), "Pofile set Unsuccessfully", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }


                }
            });

            Mobileno = view.findViewById(R.id.primarymobileno1);

            secondMobileno = view.findViewById(R.id.secondarymobileno1);

            Address = view.findViewById(R.id.address1);
            edit = view.findViewById(R.id.profile_Edit);
       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilelayout.setVisibility(View.GONE);
                createProfilelayout.setVisibility(View.VISIBLE);
                Address.setText(profile.getAddress());
                mobileno.setText(profile.getMobileno());
                secondmobileno.setText(profile.getSecondmobileno());
            }
        });*/


            reference.child("Profiles").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                private Profile profiles;

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        profile = snapshot.getValue(Profile.class);
                        Mobileno.setText(profile.getMobileno());
                        secondMobileno.setText(profile.getSecondmobileno());
                        Address.setText(profile.getAddress());
                        profilelayout.setVisibility(View.VISIBLE);
                        createProfilelayout.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);


                    } else {
                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                        profilelayout.setVisibility(View.GONE);
                        createProfilelayout.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "No data found error", Toast.LENGTH_SHORT).show();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profilelayout.setVisibility(View.GONE);
                    createProfilelayout.setVisibility(View.VISIBLE);
                    Address.setText(profile.getAddress());
                    mobileno.setText(profile.getMobileno());
                    secondmobileno.setText(profile.getSecondmobileno());
                }
            });
            return view;

        }
    }
