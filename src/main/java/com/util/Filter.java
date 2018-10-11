package com.util;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
public class Filter {

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date dateFlight;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date dateFrom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date dateTo;

    @Getter
    @Setter
    private String cityFrom;

    @Getter
    @Setter
    private String cityTo;

    @Getter
    @Setter
    private String model;

}
