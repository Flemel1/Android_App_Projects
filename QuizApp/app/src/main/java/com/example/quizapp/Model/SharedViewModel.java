package com.example.quizapp.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    MutableLiveData<ArrayList<Question>> question_list = new MutableLiveData<>();
    MutableLiveData<Integer> mIndex = new MutableLiveData<>(0);
    MutableLiveData<Integer> number = new MutableLiveData<>(1);
    MutableLiveData<Integer> mScore = new MutableLiveData<>(0);

    public void setQuestionList(ArrayList<Question> param){
        question_list.setValue(param);
    }

    public LiveData<ArrayList<Question>> getQuestionList(){
        return question_list;
    }

    public void setIndex(int index){
        mIndex.setValue(index);
    }

    public void setNumber(int n){
        number.setValue(n);
    }

    public LiveData<Integer> getNumber(){
        return number;
    }

    public LiveData<Integer> getIndex(){
        return mIndex;
    }

    public void setScore(int score){
        mScore.setValue(score);
    }

    public LiveData<Integer> getScore(){
        return mScore;
    }
}
