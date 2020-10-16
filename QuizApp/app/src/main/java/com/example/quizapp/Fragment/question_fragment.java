package com.example.quizapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.Model.Question;
import com.example.quizapp.Model.SharedViewModel;
import com.example.quizapp.R;
import com.example.quizapp.Result_Activity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class question_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BUNDLE_SCORE = "SCORE";

    // TODO: Rename and change types of parameters
    private int score;
    private int number;
    private int temp;
    private String question_text;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private ArrayList<Question> question_list;
    private SharedViewModel sharedViewModel;
    private Question question;
    private LinearLayout layoutRoot;
    private TextView tv_question_number;
    private TextView tv_question;
    private RadioGroup radioGroup;
    private RadioButton rb_Option_A;
    private RadioButton rb_Option_B;
    private RadioButton rb_Option_C;
    private RadioButton rb_Option_D;
    private RadioButton rb_answer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (question_list == null) {
            question_list = sharedViewModel.getQuestionList().getValue();
        }
        temp = sharedViewModel.getIndex().getValue();
        question = question_list.get(temp);
        number = question.getNumber();
        question_text = question.getQuestion();
        optionA = question.getOptionA();
        optionB = question.getOptionB();
        optionC = question.getOptionC();
        optionD = question.getOptionD();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutRoot = (LinearLayout) view.findViewById(R.id.layoutRootFragment);
        tv_question_number = (TextView) view.findViewById(R.id.tv_question_number);
        tv_question = (TextView) view.findViewById(R.id.tv_question);
        rb_Option_A = (RadioButton) view.findViewById(R.id.option_A);
        rb_Option_B = (RadioButton) view.findViewById(R.id.option_B);
        rb_Option_C = (RadioButton) view.findViewById(R.id.option_C);
        rb_Option_D = (RadioButton) view.findViewById(R.id.option_D);
        Button button = (Button) view.findViewById(R.id.button);
        tv_question_number.setText("Soal " + sharedViewModel.getNumber().getValue());
        tv_question.setText(question_text);
        rb_Option_A.setText(optionA);
        rb_Option_B.setText(optionB);
        rb_Option_C.setText(optionC);
        rb_Option_D.setText(optionD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == -1){
                    Toast.makeText(getActivity(), "Please choose your answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (sharedViewModel.getIndex().getValue() + 1 <= 9){
                        rb_answer = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
                        sharedViewModel.setIndex(temp+1);
                        sharedViewModel.setNumber(sharedViewModel.getNumber().getValue() + 1);
                        question_fragment fragment = new question_fragment();
                        ft.replace(R.id.container, fragment);
                        ft.commit();
                        if (question.getAnswer().equalsIgnoreCase(rb_answer.getText().toString())){
                            score = sharedViewModel.getScore().getValue() + 10;
                            sharedViewModel.setScore(score);
                        }
                    }
                    else {
                        rb_answer = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
                        if (question.getAnswer().equals(rb_answer.getText())){
                            score = sharedViewModel.getScore().getValue() + 10;
                            sharedViewModel.setScore(score);
                        }
                        Intent intent = new Intent(getActivity(), Result_Activity.class);
                        intent.putExtra(BUNDLE_SCORE, sharedViewModel.getScore().getValue());
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            }
        });
    }
}