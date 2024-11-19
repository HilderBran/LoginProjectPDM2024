package com.example.loginprojectguia6.UI.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.example.loginprojectguia6.Models.GastoDia;
import com.example.loginprojectguia6.Repositories.DailyExpensesRepository;

import java.util.ArrayList;
import java.util.List;

public class DailyExpenseVM extends ViewModel {
    private MutableLiveData<List<GastoDia>> expenseLiveData;
    private DailyExpensesRepository repository;
    public DailyExpenseVM(){
        expenseLiveData = new MutableLiveData<>();
        repository = new DailyExpensesRepository();
    }
    public void addDailyExpense(GastoDia mainObject, OnSuccessListener<DocumentReference> onSuccess, OnFailureListener
            onFailure) {
        repository.addDailyExpense(mainObject, onSuccess, onFailure);
    }
    public void finalizeBudget(String budgetId, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        repository.endBudget(budgetId, onSuccess, onFailure);
    }
    public LiveData<List<GastoDia>> getDailyExpensesLiveData() {
        return expenseLiveData;
    }
    public void listenForExpensesChanges(String presupuestoId) {
        repository.listenForExpensesChanges(presupuestoId, (querySnapshot, e) -> {
            if (e != null) {
                return;
            }
            List<GastoDia> gastosDias = new ArrayList<>();
            for (QueryDocumentSnapshot document : querySnapshot) {
                GastoDia mainObject = document.toObject(GastoDia.class);
                gastosDias.add(mainObject);
            }
            expenseLiveData.postValue(gastosDias);
        });
    }
}

