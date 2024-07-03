import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("org.jetbrains.kotlin.plugin.serialization") version  "1.8.10"
}

android {
    namespace = "com.jarica.chronogol"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jarica.chronogol"
        minSdk = 23
        targetSdk = 33
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }




    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}


// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    val nav_version = "2.7.5"
    val ktorVersion = "1.5.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //DAGGER HILT
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //NAVEGACION COMPOSE
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.4")

    //Lottie
    implementation ("com.airbnb.android:lottie-compose:6.1.0")

    //Supabase

    implementation ("io.github.jan-tennert.supabase:postgrest-kt:1.4.4")
    implementation ("io.ktor:ktor-client-android:2.3.0")
    implementation( "io.ktor:ktor-utils:2.3.5")
    implementation ("io.ktor:ktor-client-core:2.3.5")
    implementation ("io.coil-kt:coil-compose:1.3.2")
    implementation ("io.ktor:ktor-client-android:$2.3.5")

    //gson
    implementation ("com.google.code.gson:gson:2.9.0")

    //admob
    implementation ("com.google.android.gms:play-services-ads:22.5.0")






}
