package ru.programminglearning.com.hamsterProg.Basics;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import ru.programminglearning.com.project123456.R;

public class SkillFragment extends Fragment {
    private View view;
    private TextView textViewScore,textViewSucces;
    private ImageView imageView;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(getActivity(),new Crashlytics());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_skill,container,false);

        initViews();
        setSuccesefulDatabase();
        return view;

    }

    private void initViews(){
        textViewScore = view.findViewById(R.id.textSuccesful);
        textViewSucces = view.findViewById(R.id.textSuccesful223);
        imageView = view.findViewById(R.id.imageSuccesful);


    }

    private void setSuccesefulDatabase() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double count = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    if (postSnapshot.getKey().equals("Number0"))
                    {  if (postSnapshot.getValue().equals("1/1"))
                           count += 9.09;
                       else
                           count = count;


                    }
                    if (postSnapshot.getKey().equals("Number1"))
                    {   if (postSnapshot.getValue().equals("1/2"))
                        count += 9.09;
                        else if (postSnapshot.getValue().equals("2/2"))
                        count += 18.18;
                        else
                        count = count;



                    }
                    if (postSnapshot.getKey().equals("Number2"))
                    {   if (postSnapshot.getValue().equals("1/2"))
                        count += 9.09;
                        else if (postSnapshot.getValue().equals("2/2"))
                        count += 18.18;
                        else
                        count = count;


                    }
                    if (postSnapshot.getKey().equals("Number3"))
                    {  if (postSnapshot.getValue().equals("1/1"))
                        count += 9.09;
                       else
                        count = count;


                    }
                    if (postSnapshot.getKey().equals("Number4"))
                    {   if (postSnapshot.getValue().equals("1/2"))
                        count += 9.09;
                        else if (postSnapshot.getValue().equals("2/2"))
                        count += 18.18;
                        else
                        count = count;


                    }
                    if (postSnapshot.getKey().equals("Number5"))
                    {  if (postSnapshot.getValue().equals("1/1"))
                        count += 9.09;
                       else
                        count = count;
                    }
                    if (postSnapshot.getKey().equals("Number6"))
                    {   if (postSnapshot.getValue().equals("1/2"))
                        count += 9.09;
                        else if (postSnapshot.getValue().equals("2/2"))
                        count += 18.18;
                        else
                        count = count;
                    }


                }
                int result = (int)Math.round(count);
                textViewScore.setText("Завершено " + result + " %");

                if(result == 100){
                    imageView.setImageResource(R.drawable.success_score_icons);
                    textViewSucces.setVisibility(View.VISIBLE);
                    textViewSucces.setText(user.getDisplayName() + ", поздравляем с завершением основ программирования");

                }else{
                    imageView.setImageResource(R.drawable.before_success);
                    textViewSucces.setVisibility(View.GONE);
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
