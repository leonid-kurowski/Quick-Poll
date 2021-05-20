package ua.com.foxminded.quickpoll.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;

@Entity
@Schema(name = "Option")
public class Option {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "OPTION_VALUE")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getId() + "," + getValue();
    }
}
