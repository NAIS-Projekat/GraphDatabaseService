package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import java.time.LocalDateTime;

public class VolunteerDTO {
    private Long id;
    private String email;
    private boolean accountPrivacy;

    public VolunteerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountPrivacy() {
        return accountPrivacy;
    }

    public void setAccountPrivacy(boolean accountPrivacy) {
        this.accountPrivacy = accountPrivacy;
    }
}
