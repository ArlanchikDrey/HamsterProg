package ru.programminglearning.com.hamsterProg.BasicsContent.MathOperator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.BasicsContent.ConditionalOperator.ConditionalOperatorActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.Successfully.BottomNavigationDrawerFragment;
import ru.programminglearning.com.project123456.R;

public class FragmentMathTestIncre extends Fragment {

    private View view;
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_math_test_incre,container,false);

        manager = getFragmentManager();
        onClick();

        return view ;
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextMathTest2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.editTextMathTest);

                if (editText.getText().toString().equals("25")){
                    setValueDatabase();
                    final BottomNavigationDrawerFragment bottomNavigationView = new BottomNavigationDrawerFragment();
                    bottomNavigationView.show(manager,"Tag");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomNavigationView.dismiss();
                            Intent intent = new Intent(getActivity(), ConditionalOperatorActivity.class);
                            intent.putExtra("Keys","Условные операторы");
                            startActivity(intent);
                            getActivity().finish();
                        }
                    },2000);
                }else{
                    Snackbar.make(v,"Неверно, попробуй еще раз:)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setValueDatabase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid()).child("Number2");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1/2")){
                    reference.setValue("2/2");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
