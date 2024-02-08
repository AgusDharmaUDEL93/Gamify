package com.udeldev.core.di

import android.app.Application
import androidx.room.Room
import com.udeldev.core.common.Constants
import com.udeldev.core.data.repository.FavoriteRepositoryImpl
import com.udeldev.core.domain.repository.FavoriteRepository
import com.udeldev.core.domain.use_case.FavoriteUseCases
import com.udeldev.core.domain.use_case.GetAllFavoriteUseCase
import com.udeldev.core.local.favorite.FavoriteDatabase
import com.udeldev.core.network.rawg_api.RawgAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("udel".toCharArray())
    private val factory = SupportFactory(passphrase)

    @Provides
    @Singleton
    fun providesGameApi(): RawgAPI {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeader = req.newBuilder()
                .build()
            chain.proceed(requestHeader)
        }
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/KgyOSpsq6+nlxUBonR1zCRB7+Fg5tEsMluevNjtOGcY=")
            .add(hostname, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc==")
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .certificatePinner(certificatePinner)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RawgAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesFavoriteDatabase(app: Application): FavoriteDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteDatabase::class.java,
            FavoriteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    @Singleton
    fun providesFavoriteRepository(db: FavoriteDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(db.favoriteDao)
    }

    @Provides
    @Singleton
    fun providesFavoriteUseCase(repository: FavoriteRepository): FavoriteUseCases {
        return FavoriteUseCases(
            getAllFavoriteUseCase = GetAllFavoriteUseCase(repository)
        )
    }


}