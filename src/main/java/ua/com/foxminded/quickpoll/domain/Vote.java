package ua.com.foxminded.quickpoll.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;

@Entity
@Schema(name = "Vote")
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    private Option option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return getId() + ", " + getOption();
    }
}
