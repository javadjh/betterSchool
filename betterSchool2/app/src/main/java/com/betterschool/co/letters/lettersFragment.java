package com.betterschool.co.letters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.letters.adapter.CustomAdapterLetter;
import com.betterschool.co.letters.model.letters;
import com.betterschool.co.letters.model.lettersModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lettersFragment extends Fragment {
    String type;
    RecyclerView recyLetters;
    public lettersFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letters, container, false);
        recyLetters = view.findViewById(R.id.recyLetters);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();

    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<lettersModel>> call = apiInterface.getLetters(type);
        call.enqueue(new Callback<List<lettersModel>>() {
            @Override
            public void onResponse(Call<List<lettersModel>> call, Response<List<lettersModel>> response) {
                if(response.code()==200) {
                    recyLetters.setAdapter(new CustomAdapterLetter(response.body(), getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<lettersModel>> call, Throwable t) {

            }
        });
    }
}