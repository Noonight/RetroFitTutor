package com.noonight.pc.retrofittutor.network.retrofit2;

import com.noonight.pc.retrofittutor.database.tables.NumberStringTable;
import com.noonight.pc.retrofittutor.network.retrofit2.models.NumberString;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by PC on 6/28/2017.
 */

public interface API {
    @GET("/NumberString/")
    Call<List<NumberString>> getNumbersStrings();

    @POST("/NumberString/insertNumberString.php")
    @FormUrlEncoded
    Call<NumberString> insertNumberString(
            @Field(NumberStringTable.NumberString.COLUMN_NAME_NUMBER) Integer number,
            @Field(NumberStringTable.NumberString.COLUMN_NAME_TEXT) String text
    );
}
