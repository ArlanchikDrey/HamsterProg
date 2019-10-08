package ru.programminglearning.com.hamsterProg.BasicsContent.Functions;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.BasicsContent.Successfully.BottomNavigationDrawerFragment;
import ru.programminglearning.com.project123456.R;

public class FragmentFuncTest extends Fragment {
    private View view;
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_func_test,container,false);
        manager = getFragmentManager();
        initTextFunTest();
        onClick();
        return view;
    }

    private void initTextFunTest(){
        TextView textView = view.findViewById(R.id.textFunTest);
        textView.setText(Html.fromHtml(getString(R.string.textFunTest)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextFunc2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.editTextFunTest);
                if (editText.getText().toString().toLowerCase().equals("char")){
                     getValueFirebase();

                    final BottomNavigationDrawerFragment fragment = new BottomNavigationDrawerFragment();
                    fragment.show(manager,"Tag");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fragment.dismiss();
                            FunctionsActivity.viewPager.setCurrentItem(2);
                        }
                    },2000);

                }
            }
        });
    }

    private void getValueFirebase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase
                .getInstance()
                .getReference("Users")
                .child(user.getUid()).child("Number6");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object str = dataSnapshot.getValue(Object.class);
                if (str.equals("0/2")){
                    reference.setValue("1/2");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
