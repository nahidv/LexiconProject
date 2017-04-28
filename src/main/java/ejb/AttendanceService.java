package ejb;

import domain.AttendanceDomain;
import domain.RegisterDomain;
import jpa.Attendance;

import java.util.List;

/**
 * Created by Nahid on 2017-04-13.
 */
public interface AttendanceService {
    void addAttendance(AttendanceDomain attendance);
    void updateAttendance(AttendanceDomain attendance);
    AttendanceDomain getAttendance(Long id);
    void remove(Long id);
    List<AttendanceDomain> getAttendances();
    List<AttendanceDomain>getStudentsPerCourse(Integer id);
    List<AttendanceDomain>getThisAttendance();
    List<AttendanceDomain>getCourseAttendance();
    List<AttendanceDomain>getCourseRegister();
    List<AttendanceDomain>getStudentRegister();
}
