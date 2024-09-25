package pairmatching.domain;

import java.util.Objects;

public class Crew {
    private Course course;
    private String name;

    private Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public static Crew of(Course course, String name) {
        return new Crew(course, name);
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Crew)) {
            return false;
        }
        if (((Crew) obj).name.equals(name) && ((Crew) obj).course.equals(course)) {
            return true;
        }
        return false;
    }
}
