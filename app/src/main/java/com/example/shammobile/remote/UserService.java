package com.example.shammobile.remote;

import com.example.shammobile.models.Announcement;
import com.example.shammobile.models.Applicants;
import com.example.shammobile.models.Attendance;
import com.example.shammobile.models.AttendanceHome;
import com.example.shammobile.models.ChangePassword;
import com.example.shammobile.models.LoginRequest;
import com.example.shammobile.models.LoginResponse;
import com.example.shammobile.models.RegistrationStatus;
import com.example.shammobile.models.Students;
import com.example.shammobile.models.StudentsGrades;
import com.example.shammobile.models.StudentsSchedule;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.models.TeachersSchedule;
import com.example.shammobile.models.Ticket;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface UserService {

    @GET("api/announcementmobile")
    Call<Announcement>getAnnouncement();

    @POST("api/validateEmail")
    Call<Ticket>getEmail(@Body Ticket ticket);

    @POST("api/applicantStatus")
    Call<Ticket>getTicket(@Body Ticket ticket);


    //LOGINNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
    @POST("api/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginrequest);


    //APPLICANTSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    @GET("api/checkopenregistration")
    Call<RegistrationStatus>getRegistrationStatus();

    @Multipart
    @POST("api/applicantsmobile")
    Call<Applicants>addApplicants(@PartMap() Map<String, RequestBody> applicants);


    //TEACHERSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    @GET("api/teachersmobile/{id}")
    Call<Teachers>getTeacher(@Path("id") String id);
    @Multipart
    @POST("api/teachersmobilepicture")
    Call<Teachers>editTeacherpicture(@PartMap() Map<String, RequestBody> teachers);

    @PUT("api/teachersmobileinfo/{id}")
    Call<Teachers>editTeacherinfo(@Path("id") String id, @Body Teachers teachers);

    @PUT("api/teachersmobilebackground/{id}")
    Call<Teachers>editTeacherbackground(@Path("id") String id, @Body Teachers teachers);

    @GET("api/teachersmobileschedule/{id}")
    Call<TeachersSchedule>getTeacherschedule(@Path("id") String id);

    @GET("api/teachersadvisee/{id}")
    Call<Students>getTeacheradvisee(@Path("id") String id);

    @PUT("api/teacherschangepassword/{id}")
    Call<ChangePassword>editTeacherpassword(@Path("id") String id,@Body ChangePassword changepassword);

    //ATTENDANCEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
    @GET("api/attendancehomemobile/{id}")
    Call<AttendanceHome>getAttendancehome(@Path("id") String id);

    @GET("api/attendancemobile/{id}")
    Call<Students>getAttendance(@Path("id") String id);

    @POST("api/attendancemobile")
    Call<Attendance>addAttendance(@Body Attendance attendance);


    //STUDENTSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    @GET("api/studentsmobile/{id}")
    Call<Students>getStudent(@Path("id") String id);

    @Multipart
    @POST("api/studentsmobilepicture")
    Call<Students>editStudentpicture(@PartMap() Map<String, RequestBody> students);

    @PUT("api/studentsmobileinfo/{id}")
    Call<Students>editStudentinfo(@Path("id") String id, @Body Students students);

    @PUT("api/studentsmobileguardian/{id}")
    Call<Students>editStudentguardian(@Path("id") String id, @Body Students students);

    @GET("api/studentsmobileschedule/{id}")
    Call<StudentsSchedule>getStudentschedule(@Path("id") String id);

    @GET("api/studentsmobilegrade/{id}")
    Call<StudentsGrades>getStudentsgrade(@Path("id") String id);

    @PUT("api/studentschangepassword/{id}")
    Call<ChangePassword>editStudentpassword(@Path("id") String id,@Body ChangePassword changepassword);
}
