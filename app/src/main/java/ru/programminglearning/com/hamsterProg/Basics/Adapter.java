package ru.programminglearning.com.hamsterProg.Basics;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.programminglearning.com.hamsterProg.BasicsContent.Array.ArrayActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.BasicProgramming.BasicProgramming;
import ru.programminglearning.com.hamsterProg.BasicsContent.ConditionalOperator.ConditionalOperatorActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.Functions.FunctionsActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.MathOperator.MathOperatorActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables.TypeActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.Сycles.CyclesActivity;
import ru.programminglearning.com.project123456.R;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String KEY = "Keys";

    public static final String[] list = {"Основные термины"
            ,"Переменные.Типы данных","Арифметические операторы",
            "Условные операторы","Циклы","Массивы","Функции"};

    private ArrayList<String> listCompeled = new ArrayList<>();

    private ArrayList<String> lists = new ArrayList<>();

    Adapter(ArrayList<String> listCompled){
        this.listCompeled = listCompled;

        for(int i = 0;i<list.length;i++){
            lists.add(i,"Parent");
        }
    }

    @Override
    public int getItemViewType(int position) {
          if (lists.get(position).equals("Parent")){
              return 0;
          }else if(lists.get(position).equals("Child")){
              return 1;
          }

          return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0){
            return new ViewHolderParent(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_list,viewGroup,false));
        }else{
            return new ViewHolderChild(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_list_replace,viewGroup,false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            switch (viewHolder.getItemViewType()){
                case 0:
                    ((ViewHolderParent)viewHolder).textView.setText(list[i]);
                    ((ViewHolderParent)viewHolder).textViewCompleted.setText(listCompeled.get(i) + "  Завершено");
                    onClickListenerParent(((ViewHolderParent)viewHolder),i);
                    break;
                case 1:
                    ((ViewHolderChild)viewHolder).textView.setText(list[i]);
                    ((ViewHolderChild)viewHolder).textViewCompleted.setText(listCompeled.get(i) + "  Завершено");
                    onClickListenerChild(((ViewHolderChild)viewHolder),i);
                    onClickNextIntent(((ViewHolderChild)viewHolder),i);
                    break;
            }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    private void onClickListenerParent(final ViewHolderParent viewHolder,final int position ){
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    lists.set(position,"Child");
                    notifyItemChanged(position);

            }
        });
    }

    private void onClickListenerChild(final ViewHolderChild viewHolder,final int position ){
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(position);
                lists.set(position,"Parent");
            }
        });
    }

    private void onClickNextIntent(final ViewHolderChild viewHolder, final int pos){
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = viewHolder.itemView.getContext();
                switch (pos){
                    case 0:
                        startActivity(context,BasicProgramming.class,pos);
                        break;
                    case 1:
                        startActivity(context, TypeActivity.class,pos);
                        break;
                    case 2:
                        startActivity(context, MathOperatorActivity.class,pos);
                        break;
                    case 3:
                        startActivity(context, ConditionalOperatorActivity.class,pos);
                        break;
                    case 4:
                        startActivity(context, CyclesActivity.class,pos);
                        break;
                    case 5:
                        startActivity(context, ArrayActivity.class,pos);
                        break;
                    case 6:
                        startActivity(context, FunctionsActivity.class,pos);
                        break;
                    default:
                        Toast.makeText(context, "Упс", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void startActivity(Context context,Class<?> classes,int pos){
        Intent intent = new Intent(context, classes);
        intent.putExtra(KEY,list[pos]);
        context.startActivity(intent);
    }


    class ViewHolderParent extends RecyclerView.ViewHolder  {
         TextView textView,textViewCompleted;
         RelativeLayout layout;
         ImageView imageView;
        ViewHolderParent(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageArrow);
            layout = itemView.findViewById(R.id.basic_container);
            textView = itemView.findViewById(R.id.headName);
            textViewCompleted = itemView.findViewById(R.id.textCompled);


        }

    }

    class ViewHolderChild extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView,textViewCompleted;
        ImageButton imageButton;
        ViewHolderChild(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.themeViewReplace);
            textView = itemView.findViewById(R.id.headNameReplace);
            textViewCompleted = itemView.findViewById(R.id.textCompled);
            imageButton = itemView.findViewById(R.id.btnImageView);
        }
    }

}
