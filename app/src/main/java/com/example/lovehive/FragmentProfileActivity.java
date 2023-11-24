package com.example.lovehive;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentProfileActivity extends Fragment {

    private TextInputEditText editTextFullName;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextDateOfBirth;
    private TextInputEditText editTextGender;
    private TextInputEditText editTextAddress;
    private TextInputEditText editTextNationality;
    private TextInputEditText editTextMaritalStatus;
    private TextInputEditText editTextBio;
    private TextInputEditText editTextOccupation;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_profile, container, false);

        // Initialize Firebase components
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Initialize UI elements
        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextDateOfBirth = view.findViewById(R.id.editTextDateOfBirth);
        editTextGender = view.findViewById(R.id.editTextGender);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextNationality = view.findViewById(R.id.editTextNationality);
        editTextMaritalStatus = view.findViewById(R.id.editTextMaritalStatus);
        editTextBio = view.findViewById(R.id.editTextBio);
        editTextOccupation = view.findViewById(R.id.editTextOccupation);

        // Retrieve user details from Firebase
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d("UserProfile", "User is authenticated: " + currentUser.getUid());
            String userId = currentUser.getUid();
            DatabaseReference userRef = databaseReference.child("users").child(userId);

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Retrieve user details from the database
                        String fullName = dataSnapshot.child("fullName").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue(String.class);
                        String gender = dataSnapshot.child("gender").getValue(String.class);
                        String address = dataSnapshot.child("address").getValue(String.class);
                        String nationality = dataSnapshot.child("nationality").getValue(String.class);
                        String maritalStatus = dataSnapshot.child("maritalStatus").getValue(String.class);
                        String bio = dataSnapshot.child("bio").getValue(String.class);
                        String occupation = dataSnapshot.child("occupation").getValue(String.class);

                        // Update UI elements with user details
                        editTextFullName.setText(fullName);
                        editTextEmail.setText(email);
                        editTextDateOfBirth.setText(dateOfBirth);
                        editTextGender.setText(gender);
                        editTextAddress.setText(address);
                        editTextNationality.setText(nationality);
                        editTextMaritalStatus.setText(maritalStatus);
                        editTextBio.setText(bio);
                        editTextOccupation.setText(occupation);

                        // You may update other fields similarly
                    } else {
                        Toast.makeText(getContext(), "User data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Error retrieving user data", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.d("UserProfile", "User is not authenticated");
        }

        // Add data to Firebase when a button is clicked (you can replace this with your actual button click event)
        view.findViewById(R.id.buttonSaveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDataToFirebase();
            }
        });

        return view;
    }

    @SuppressLint("RestrictedApi")
    private void saveUserDataToFirebase() {
        // Get the data from the UI elements
        String fullName = editTextFullName.getText().toString();
        String email = editTextEmail.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String gender = editTextGender.getText().toString();
        String address = editTextAddress.getText().toString();
        String nationality = editTextNationality.getText().toString();
        String maritalStatus = editTextMaritalStatus.getText().toString();
        String bio = editTextBio.getText().toString();
        String occupation = editTextOccupation.getText().toString();

        // Create a User object
        User user = getUser(fullName, email, dateOfBirth, gender, address, nationality, maritalStatus, bio, occupation);

        // Get the current user ID
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            // Get the user ID
            String userId = currentUser.getUid();

            // Create a reference to the user's data in Firebase
            DatabaseReference userRef = databaseReference.child("users").child(userId);

            // Set the value of the user data in Firebase
            userRef.setValue(user);

            // Display a success message
            Toast.makeText(getContext(), "User data saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("UserProfile", "User is not authenticated");
            // Display an error message
            Toast.makeText(getContext(), "User is not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    private static User getUser(String fullName, String email, String dateOfBirth, String gender, String address, String nationality, String maritalStatus, String bio, String occupation) {
        User user = new User(fullName, email, dateOfBirth, gender, address, nationality, maritalStatus, bio, occupation);
        return user;
    }
}
