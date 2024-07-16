package com.example.bookmanagement_njs1701_se172266.api;

import com.example.bookmanagement_njs1701_se172266.model.Author;
import com.example.bookmanagement_njs1701_se172266.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    String BOOKS = "Book";
    String AUTHORS = "Author";

    @GET(BOOKS)
    Call<List<Book>> getAllBooksents();

    @POST(BOOKS)
    Call<Book> addBook(@Body Book book);

    @PUT(BOOKS + "/{id}")
    Call<Book> updateBook(@Path("id") long id, @Body Book book);

    @DELETE(BOOKS + "/{id}")
    Call<Book> deleteBook(@Path("id") long id);

    //

    @GET(AUTHORS)
    Call<List<Author>> getAllAuthors();

    @POST(AUTHORS)
    Call<Author> addAuthor(@Body Author author);

    @PUT(AUTHORS + "/{id}")
    Call<Author> updateAuthor(@Path("id") long id, @Body Author author);

    @DELETE(AUTHORS + "/{id}")
    Call<Author> deleteAuthor(@Path("id") long id);
}
