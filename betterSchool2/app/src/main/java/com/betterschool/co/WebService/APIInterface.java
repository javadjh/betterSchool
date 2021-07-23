package com.betterschool.co.WebService;


import com.betterschool.co.students.insertStudent.model.insertStudentModel;
import com.betterschool.co.students.model.studentRoot;
import com.betterschool.co.students.model.students;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("students")
    Call<studentRoot> getStudents(@Query("pageId") int pageId,
                                  @Query("eachPerPage") int eachPerPage,
                                  @Query("lastName") String lastName);
    @DELETE("student/{id}")
    Call<Boolean> deleteStudent(@Path("id") String id);

    @POST("insert/student")
    Call<students> insertStudent(@Body insertStudentModel insertStudentModel);


}