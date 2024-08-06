package com.example.bookmyshow.dtos;



public class Response {
    private ResponseStatus status;
    private String errorMessage;

    public Response(ResponseStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static Response getFailedResponse(String errorMessage) {
        return new Response(ResponseStatus.FAILURE,errorMessage);
    }

    public static Response getSuccessResponse() {
        return new Response(ResponseStatus.SUCCESS,null);
    }
}
