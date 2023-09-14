package com.dmbdan.foodrecipes.di

import com.dmbdan.foodrecipes.data.datasource.remote.FoodRecipeApi
import com.dmbdan.foodrecipes.helpers.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient:OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesProductsAPi(retrofit: Retrofit): FoodRecipeApi {
        return retrofit.create(FoodRecipeApi::class.java)
    }

    /*

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "products.db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesProductsDao(database: ProductDatabase):ProductDao=database.productDao()
    */

}