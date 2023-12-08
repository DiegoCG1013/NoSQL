package io.bootify.no_s_q_l.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppDTO {

    @Size(max = 255)
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

}
