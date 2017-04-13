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
}
