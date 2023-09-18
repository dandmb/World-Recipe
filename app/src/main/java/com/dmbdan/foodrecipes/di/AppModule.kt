package com.dmbdan.foodrecipes.di

import android.content.Context
import androidx.room.Room
import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeDao
import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeDatabase
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteDao
import com.dmbdan.foodrecipes.data.datasource.remote.FoodRecipeApi
import com.dmbdan.foodrecipes.helpers.Constants.Companion.BASE_URL
import com.dmbdan.foodrecipes.helpers.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
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


    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesRecipeDao(database: RecipeDatabase): RecipeDao = database.recipesDao()

    @Singleton
    @Provides
    fun providesFavoriteRecipeDao(database: RecipeDatabase): FavoriteDao =
        database.favoriteRecipesDao()
}
