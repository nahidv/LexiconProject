package ejb;

import domain.TeachingDomain;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Nahid on 2017-04-05.
 */
@Local
public interface TeachingService {
    void addTeaching(TeachingDomain teaching);
    void updateTeaching(TeachingDomain teaching);
    TeachingDomain getTeaching(Long id);
    void removeTeaching(Long id);
    List<TeachingDomain> getTeachings();
    List<TeachingDomain>getThisTeaching();
    //public List<TeachingDomain>getTeachingsFirstNameContain(String filter);
}
