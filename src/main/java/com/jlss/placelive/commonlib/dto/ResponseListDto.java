package com.jlss.placelive.commonlib.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseListDto<T> {
    private Boolean success;
    private T data;
    private PaginatedDto paginatedDto;
    private String errorCode;
    private String errorMessage;

    public ResponseListDto(Boolean success, T data, PaginatedDto paginatedDto, String errorCode, String errorMessage) {
        this.success = success;
        this.data = data;
        this.paginatedDto = paginatedDto;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
