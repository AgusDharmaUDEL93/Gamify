import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.udeldev.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {


    // Retrofit
    api(libs.bundles.retrofit)

    // Coil
    api(libs.bundles.coil)

    // Room
    api(libs.bundles.room)
    ksp(libs.bundles.ksp.room)

    // Dagger-hilt
    implementation(libs.bundles.hilt)
    ksp(libs.bundles.ksp.hilt)

    // Encription
    implementation(libs.bundles.sqlcipher)

    // Main Package
    implementation(libs.bundles.android.x)

    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    implementation(libs.bundles.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.test)

    debugImplementation(libs.bundles.debug)
}