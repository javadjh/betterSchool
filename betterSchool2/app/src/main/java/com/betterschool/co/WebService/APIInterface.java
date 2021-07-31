package com.betterschool.co.WebService;


import com.betterschool.co.classContainer.classes.model.ClassRoot;
import com.betterschool.co.classContainer.classes.model.insertClassModel;
import com.betterschool.co.classContainer.classes.model.setExamModel;
import com.betterschool.co.classContainer.model.ClassContainerRoot;
import com.betterschool.co.classContainer.model.classes;
import com.betterschool.co.classContainer.model.insertClassContainer;
import com.betterschool.co.classContainer.moveStudent.model.moveStudentModel;
import com.betterschool.co.classContainer.moveStudent.model.moveStudentModelSingle;
import com.betterschool.co.deputyNote.insertDeputyNote.model.deputyNote;
import com.betterschool.co.deputyNote.insertDeputyNote.model.violations;
import com.betterschool.co.deputyNote.model.deputyNoteRoot;
import com.betterschool.co.disciplineScore.model.disciplineModel;
import com.betterschool.co.disciplineScore.model.insertDisciplineModel;
import com.betterschool.co.letters.model.letters;
import com.betterschool.co.news.model.news;
import com.betterschool.co.news.model.newsRoot;
import com.betterschool.co.studentsClassPackage.exam.model.studentsExam;
import com.betterschool.co.teacherClassPackage.attendance.model.attendanceRoot;
import com.betterschool.co.teacherClassPackage.exam.model.exams;
import com.betterschool.co.teacherClassPackage.exam.model.insertExamModel;
import com.betterschool.co.teacherClassPackage.mainPageClass.model.mainClassStudentsRoot;
import com.betterschool.co.teacherClassPackage.mainPageClass.showSingleExam.model.updateExamModel;
import com.betterschool.co.teacherClassPackage.model.teachersClassesRoot;
import com.betterschool.co.teacherClassPackage.note.model.notes;
import com.betterschool.co.teacherClassPackage.startClass.model.insertAttendanceModel;
import com.betterschool.co.teacherClassPackage.studentDetail.model.studentDetailRoot;
import com.betterschool.co.login.model.LoginAction;
import com.betterschool.co.login.model.loginModel;
import com.betterschool.co.semesters.model.semestersRoot;
import com.betterschool.co.students.insertStudent.model.insertStudentModel;
import com.betterschool.co.students.model.studentRoot;
import com.betterschool.co.students.model.students;
import com.betterschool.co.teacherClassPackage.teachersFile.model.teachersFile;
import com.betterschool.co.teachers.insertTeacher.model.insertTeacher;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.teachers.model.teachersRoot;
import com.betterschool.co.weeklySchedule.model.weeklyScheduleModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("notes/{id}")
    Call<List<notes>> getNotes(@Path("id") String id);

    @POST("insert/note")
    Call<Boolean> insertNote (@Body notes insertNote);

    @GET("student/detail")
    Call<studentDetailRoot> getStudentDetail(@Query("classId") String classId,
                                             @Query("id") String id);


    @GET("students/classes")
    Call<List<classes>> getStudentsClass();

    @GET("teachers/letter")
    Call<List<letters>> getTeachersLetter(@Query("type") String type);

    @GET("teachers/letter/{id}")
    Call<letters> getSingleTeachersLetter(@Path("id") String id);

    @GET("student/dashboard")
    Call<studentDetailRoot> getStudentsStudentDetail(@Query("classId") String classId);

    @GET("news")
    Call<newsRoot> getNews(@Query("pageId") int pageId,
                           @Query("eachPerPage") int eachPerPage);

    @Multipart
    @POST("insert/news")
    Call<Boolean> insertNews(@Part MultipartBody.Part bodyFile , @Part("title") RequestBody title, @Part("description") RequestBody description);

    @GET("classcontainer/student")
    Call<weeklyScheduleModel> getStudentClassContainer();

    @GET("students/exam")
    Call<studentsExam> getStudentsExam();

    @GET("teachers/file/{id}")
    Call<List<teachersFile>> getTeachersFile(@Path("id") String id);

    @Multipart
    @POST("insert/teacher/file")
    Call<Boolean> insertTeachersFile(@Part MultipartBody.Part bodyFile , @Part("title") RequestBody title,
                             @Part("classContainerId") RequestBody classContainerId,
                             @Part("classId") RequestBody classId);

    @GET("note/deputies")
    Call<deputyNoteRoot> getDeputiesNote(@Query("pageId") int pageId,
                                         @Query("eachPerPage") int eachPerPage,
                                         @Query("lastName") String lastName);

    @GET("violations")
    Call<List<violations>> getViolations();

    @GET("deputy/students")
    Call<studentRoot> getDeputyStudent(
            @Query("pageId") int pageId,
            @Query("eachPerPage") int eachPerPage,
            @Query("lastName") String lastName);

    @POST("insert/note/deputy")
    Call<Boolean> insertDeputyNote (@Body deputyNote deputyNote);

    @POST("insert/violation")
    Call<Boolean> insertViolation (@Body violations violations);

    @GET("students/discipline/{id}")
    Call<disciplineModel> getStudentsDiscipline (@Path("id") String id);

    @POST("insert/discipline")
    Call<Boolean> insertDiscipline (@Body insertDisciplineModel insertDisciplineModel);
}