package ru.programminglearning.com.hamsterProg.BasicsContent.Сycles;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.BasicsContent.Successfully.BottomNavigationDrawerFragment;
import ru.programminglearning.com.hamsterProg.BasicsContent.Array.ArrayActivity;
import ru.programminglearning.com.project123456.R;

public class FragmentCycleTestFor extends Fragment {
    private View view;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loop_test_for,container,false);

        fragmentManager = getFragmentManager();
        initText();
        onClick();

        return view;
    }

    private void initText(){
        TextView textView = view.findViewById(R.id.textForExample);
        textView.setText(Html.fromHtml(getString(R.string.ForTest)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextFor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.editTextForTest);

                if (editText.getText().toString().equals("10")){
                    setValueDatabase();
                    final BottomNavigationDrawerFragment bottomNavigationView = new BottomNavigationDrawerFragment();
                    bottomNavigationView.show(fragmentManager,"Tag");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomNavigationView.dismiss();
                            Intent intent = new Intent(getActivity(), ArrayActivity.class);
                            intent.putExtra("Keys","Массивы");
                            startActivity(intent);

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
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid()).child("Number4");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1/2")){
                    databaseReference.setValue("2/2");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
