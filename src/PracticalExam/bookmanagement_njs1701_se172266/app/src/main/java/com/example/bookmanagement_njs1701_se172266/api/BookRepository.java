package com.example.bookmanagement_njs1701_se172266.api;

public class BookRepository {
    public static ApiService getBookService() {
        return ApiClient.getClient().create(ApiService.class);
    }
}
