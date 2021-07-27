package com.betterschool.co.WebService;


import com.betterschool.co.classContainer.classes.model.ClassRoot;
import com.betterschool.co.classContainer.classes.model.insertClassModel;
import com.betterschool.co.classContainer.classes.model.setExamModel;
import com.betterschool.co.classContainer.model.ClassContainerRoot;
import com.betterschool.co.classContainer.model.insertClassContainer;
import com.betterschool.co.classContainer.moveStudent.model.moveStudentModel;
import com.betterschool.co.classContainer.moveStudent.model.moveStudentModelSingle;
import com.betterschool.co.classPackage.attendance.model.attendanceRoot;
import com.betterschool.co.classPackage.exam.model.exams;
import com.betterschool.co.classPackage.exam.model.insertExamModel;
import com.betterschool.co.classPackage.mainPageClass.model.mainClassStudentsRoot;
import com.betterschool.co.classPackage.mainPageClass.showSingleExam.model.updateExamModel;
import com.betterschool.co.classPackage.model.teachersClassesRoot;
import com.betterschool.co.classPackage.startClass.model.insertAttendanceModel;
import com.betterschool.co.login.model.LoginAction;
import com.betterschool.co.login.model.loginModel;
import com.betterschool.co.semesters.model.semesters;
import com.betterschool.co.semesters.model.semestersRoot;
import com.betterschool.co.students.insertStudent.model.insertStudentModel;
import com.betterschool.co.students.model.studentRoot;
import com.betterschool.co.students.model.students;
import com.betterschool.co.teachers.insertTeacher.model.insertTeacher;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.teachers.model.teachersRoot;

import java.util.List;

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

    @GET("teachers")
    Call<teachersRoot> getTeachers(@Query("pageId") int pageId,
                                   @Query("eachPerPage") int eachPerPage,
                                   @Query("lastName") String lastName,
                                   @Query("melliCode") String melliCode);

    @DELETE("teacher/{id}")
    Call<Boolean> deleteTeacher(@Path("id") String id);

    @POST("insert/teacher")
    Call<teachers> insertTeacher(@Body insertTeacher insertTeacher);

    @GET("teacher/{id}")
    Call<teachers> getSingleTeacher(@Path("id") String id);

    @POST("update/teacher")
    Call<Boolean> updateTeacher(@Body insertTeacher insertTeacher);

    @GET("classcontainers")
    Call<List<ClassContainerRoot>> getClassContainer();

    @GET("allstudent")
    Call<List<students>> getAllStudent(@Query("grade") String grade);

    @POST("insert/classcontainer")
    Call<Boolean> insertClassContainer(@Body insertClassContainer insertClassContainer);

    @GET("allteachers")
    Call<List<teachers>> getAllTeachers();

    @GET("classes")
    Call<List<ClassRoot>> getContainersClass(@Query("classContainer") String classContainer);

    @POST("insert/class")
    Call<Boolean> insertClass(@Body insertClassModel insertClassModel);

    @GET("semesters")
    Call<semestersRoot> getSemesters();

    @POST("insert/semester")
    Call<Boolean> insertSemester();

    @GET("move/student/{id}")
    Call<moveStudentModelSingle> getMoveSingle(@Path("id") String id);

    @POST("move/student")
    Call<Boolean> moveStudent(@Body moveStudentModel moveStudentModel);

    @POST("class/exam")
    Call<Boolean> setExam(@Body setExamModel setExamModel);

    @POST("login")
    Call<loginModel> login(@Body LoginAction loginAction);

    @GET("teachers/classes")
    Call<List<teachersClassesRoot>> getTeachersClass();

    @GET("teachers/classes/{id}")
    Call<mainClassStudentsRoot> getMainClassPageStudents(@Path("id") String id);

    @POST("insert/attendance")
    Call<Boolean> insertAttendance (@Body insertAttendanceModel insertAttendanceModel);

    @GET("attendances/{id}")
    Call<List<attendanceRoot>> getAttendance(@Path("id") String id);

    @GET("attendance/{id}")
    Call<attendanceRoot> getSingleAttendance(@Path("id") String id);

    @GET("exams/{id}")
    Call<List<exams>> getExams(@Path("id") String id);

    @POST("update/exam")
    Call<Boolean> updateStudentsScoreExam (@Body updateExamModel updateExamModel);

    @GET("exam/{id}")
    Call<exams> getSingleExam(@Path("id") String id);

    @POST("insert/exam")
    Call<Boolean> insertExam (@Body insertExamModel insertExamModel);

    @GET("exams/class/{id}")
    Call<List<exams>> getClassExams(@Path("id") String id);
}