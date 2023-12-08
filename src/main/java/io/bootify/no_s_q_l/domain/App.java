package io.bootify.no_s_q_l.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("apps")
@Getter
@Setter
public class App {

    @NotNull
    @Id
    private String app;

    @NotNull
    @Size(max = 255)
    private String category;

    @NotNull
    @Size(max = 255)
    private String rating;

    @NotNull
    @Size(max = 255)
    private String reviews;

    @NotNull
    @Size(max = 255)
    private String size;

    @NotNull
    @Size(max = 255)
    private String installs;

    @NotNull
    @Size(max = 255)
    private String type;

    @NotNull
    @Size(max = 255)
    private String price;

    @NotNull
    @Size(max = 255)
    private String contentRating;

    @NotNull
    @Size(max = 255)
    private String genres;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
