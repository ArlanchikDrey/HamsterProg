package ru.programminglearning.com.hamsterProg.BasicsContent.Successfully;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.programminglearning.com.project123456.R;

public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottomsheet,container,false);

        ImageView imageView = view.findViewById(R.id.imageViewSheet);

        Glide.with(view).
                load(R.drawable.success_score_icons).into(imageView);

        return view;
    }

    @Override
    public int getTheme() {
       return R.style.BottomSheetDialogTheme;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new BottomSheetDialog(requireContext(),getTheme());
        return dialog;
    }
}
