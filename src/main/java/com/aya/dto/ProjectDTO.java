package com.aya.dto;
import com.aya.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectEndDate;
    private String projectDetail;
    private Status projectStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int completeTaskCounts;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int unfinishedTaskCounts;


}
