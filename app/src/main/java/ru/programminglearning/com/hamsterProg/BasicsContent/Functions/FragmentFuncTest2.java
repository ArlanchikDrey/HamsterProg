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

public class FragmentFuncTest2 extends Fragment {

    private View view;
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_func_test_2,container,false);

        initTextCodeTest();
        manager = getFragmentManager();

        Button button = view.findViewById(R.id.onNextTestFun222);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.editTextFunTest22);
                if (editText.getText().toString().equals("100")){
                    getValueFirebase();
                    final BottomNavigationDrawerFragment fragment = new BottomNavigationDrawerFragment();
                    fragment.show(manager,"Tag");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fragment.dismiss();

                        }
                    },2000);
                }
            }
        });
        return view;
    }

    private void initTextCodeTest(){
        TextView textView = view.findViewById(R.id.textFunTest22);
        textView.setText(Html.fromHtml(getString(R.string.funArgsTest)));
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
                if (str.equals("1/2")){
                    reference.setValue("2/2");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
