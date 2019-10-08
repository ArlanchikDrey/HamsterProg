package ru.programminglearning.com.hamsterProg.BasicsContent.Сycles;

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
import ru.programminglearning.com.project123456.R;

public class FragmentCycleTestWhile extends Fragment {

    private View view;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loop_test_while,container,false);

        fragmentManager = getFragmentManager();
        initText();
        onClick();

        return view;
    }

    private void initText(){
        TextView textView = view.findViewById(R.id.textWhileExample);
        textView.setText(Html.fromHtml(getString(R.string.whileTest)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextForDecs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.editTextWhileTest);

                if (editText.getText().toString().equals("4")){
                    setValueDatabase();
                    final BottomNavigationDrawerFragment bottomNavigationView = new BottomNavigationDrawerFragment();
                    bottomNavigationView.show(fragmentManager,"Tag");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomNavigationView.dismiss();
                            CyclesActivity.viewPager.setCurrentItem(2);
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
                if (value.equals("0/2")){
                    databaseReference.setValue("1/2");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
