package com.rxandroid.redmarttask.data.src.network;

import com.rxandroid.redmarttask.util.AppConstants;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Singleton;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.GzipSource;
import okio.Okio;

/**
 * Created by anukalp on 19/1/17.
 */

/**
 * Logging interceptors for verifying and logging response and time taken in httprequest
 */
@Singleton
public class RequestLoggingInterceptor implements Interceptor {
    private Logger logger = Logger.getLogger("RequestInterceptor");

    @Override

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        long t1 = System.nanoTime();
        logger.info(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logger.info(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }

    private Response unzip(final Response response) throws IOException {
        try {
            String headerValue = null != response.headers() ? response.headers().get("Content-Type") : null;
            if (response.body() == null || ("application/x-font-woff;charset=UTF-8").equals(headerValue) || ("image/jpeg;charset=UTF-8").equals(headerValue)) {
                return response;
            }
            String contentEncoding = response.header(AppConstants.CONTENT_ENCODING);
            // handling for gzip data
            if (contentEncoding == null || !contentEncoding.equalsIgnoreCase(AppConstants.GZIP)) {
                return response;
            }

            GzipSource responseBody = new GzipSource(response.body().source());
            Headers strippedHeaders = response.headers().newBuilder()
                    .removeAll("Content-Encoding")
                    .removeAll("Content-Length")
                    .build();
            return response.newBuilder()
                    .headers(strippedHeaders)
                    .body(new RealResponseBody(strippedHeaders, Okio.buffer(responseBody)))
                    .build();
        } catch (Exception e) {

        }
        return response;
    }
}