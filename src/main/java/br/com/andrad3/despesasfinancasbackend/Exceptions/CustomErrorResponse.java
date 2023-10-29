package br.com.andrad3.despesasfinancasbackend.Exceptions;

public class CustomErrorResponse {
    private Integer status;
    private String message;

    public CustomErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
