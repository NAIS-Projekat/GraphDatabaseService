package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public class PopularVolunteerDTO {
    private String volunteerName;
    private Long numPosts;
    private Long totalViews;
    private Long totalLikes;

    public PopularVolunteerDTO(){

    }
    public PopularVolunteerDTO(String volunteerName, Long numPosts, Long totalViews, Long totalLikes) {
        this.volunteerName = volunteerName;
        this.numPosts = numPosts;
        this.totalViews = totalViews;
        this.totalLikes = totalLikes;
    }


    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public Long getNumPosts() {
        return numPosts;
    }

    public void setNumPosts(Long numPosts) {
        this.numPosts = numPosts;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Long totalViews) {
        this.totalViews = totalViews;
    }

    public Long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        this.totalLikes = totalLikes;
    }
}
