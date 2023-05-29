package com.readme.sections.dto;

import com.readme.sections.requestObject.RequestNovelId;
import com.readme.sections.requestObject.RequestUpdateScheduleIdList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateScheduleIdListDTO {
    private List<RequestNovelId> requestNovelIdList;

    public UpdateScheduleIdListDTO(RequestUpdateScheduleIdList requestUpdateScheduleIdList) {
        this.requestNovelIdList = requestUpdateScheduleIdList.getRequestNovelIdList();
    }
}
