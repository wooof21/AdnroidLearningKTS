package com.adnroidlearningkts.dependencyinjection.retrofit.model.config

import com.adnroidlearningkts.dependencyinjection.retrofit.model.pojo.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostsInterface {

    /**
     * When the return type is configured to be the data type (e.g. List<Post>),
     *      Retrofit automatically extracts the body of the successful HTTP response and converts it
     * If the HTTP request is not successful, Retrofit will throw an HttpException or a similar exception.
     * Would need to handle these exceptions in the calling code using a try-catch block.
     *
     * When Retrofit is configured to return Response<T>, it wraps the result of the
     *      HTTP call within a retrofit2.Response object. This Response object provides
     *      detailed information about the HTTP response.
     * Error Handling: The Response object itself indicates whether the call was successful or not
     * through methods like isSuccessful(). If isSuccessful() returns false, then can get the error body using errorBody().
     *
     * Accessing Response Details:
     *      - isSuccessful(): Checks if the HTTP status code is in the 200-300 range.
     *      - code(): Gets the HTTP status code (e.g., 200, 404, 500)
     *      - body(): Gets the parsed response body if the call was successful and isSuccessful() is true.
     *          This will be List<Post>
     *      - errorBody(): Gets the response body if the call was not successful and isSuccessful() is false.
     *      - headers(): Gets the response headers.
     *      - message(): Gets the HTTP status message.
     */
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}