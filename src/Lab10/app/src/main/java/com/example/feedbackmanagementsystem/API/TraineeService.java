package com.example.feedbackmanagementsystem.API;

import com.example.feedbackmanagementsystem.Trainee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TraineeService {
    String TRAINEES = "trainee";

    @GET(TRAINEES)
    Call<List<Trainee>> getALLTrainees();

    @GET(TRAINEES + "/{id}")
    Call<Trainee> getTrainees(@Path("id") Object id);

    @POST(TRAINEES)
    Call<Trainee> createTrainees(@Body Trainee trainee);

    @PUT(TRAINEES + "/{id}")
    Call<Trainee> updateTrainee(@Path("id") Object id, @Body Trainee trainee);

    @DELETE(TRAINEES + "/{id}")
    Call<Void> deleteTrainee(@Path("id") Object id);
}
