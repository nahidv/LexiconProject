package domain;

import java.util.Date;

/**
 * Created by Nahid on 2017-03-22.
 */
public class CourseDomain {
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;

    public CourseDomain(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate= startDate;
        this.endDate = endDate;
    }

    public CourseDomain(int id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate= startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate()   {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

