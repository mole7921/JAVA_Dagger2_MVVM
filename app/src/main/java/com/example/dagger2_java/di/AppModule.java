package com.example.dagger2_java.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.dagger2_java.R;
import com.example.dagger2_java.model.User;
import com.example.dagger2_java.network.auth.AuthApi;
import com.example.dagger2_java.util.Constants;

import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {


    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
       return RequestOptions
               .placeholderOf(R.drawable.white_background)
               .error(R.drawable.white_background);
   }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application,RequestOptions requestOptions){
       return Glide.with(application)
               .setDefaultRequestOptions(requestOptions);
   }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
       return ContextCompat.getDrawable(application,R.drawable.logo);
   }

    @Singleton
    @Provides
    @Named("SingletonUser")
    static User someUser(){
        return new User();
    }
}


/*
 Application level dependencies for the project.
 (anything that's going to exist and not change for
 the entire lifetime of the application.)
 */