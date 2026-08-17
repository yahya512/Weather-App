plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.safearg)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    namespace = "com.example.weatherapp"
    36.also { compileSdk = it }

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 27
        this.targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
kotlin {
    jvmToolchain(11)
}
dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    //Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    //Swipe to refresh
    implementation(libs.androidx.swiperefreshlayout)
    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    //Retrofit
    implementation(libs.retrofit)
    //Geson Convertor
    implementation(libs.retrofit2.converter.gson)
    //OkHttp
    implementation(libs.okhttp)
    //Interceptor
    implementation(libs.logging.interceptor)
    //Glide
    implementation(libs.glide)
    //Chucker
    debugImplementation(libs.library)
    releaseImplementation(libs.chucker.library.no.op)
}