package ru.programminglearning.com.hamsterProg.Basics;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ru.programminglearning.com.project123456.R;

public class BasicFragment extends Fragment {
    private View view;
    private ArrayList<String> listCompeled = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basic, container, false);

        getConnectDatabase();
        return view;
    }

    public void getConnectDatabase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users");
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < 9; i++) {
                    Object value = dataSnapshot.child("Number" + String.valueOf(i)).getValue();
                    listCompeled.add(String.valueOf(value));
                }
                getRecyclerView();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void getRecyclerView() {

        RecyclerView recyclerView = view.findViewById(R.id.recyclerList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new Adapter(listCompeled));


    }


}
