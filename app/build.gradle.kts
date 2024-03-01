plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.practicaandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.practicaandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
    buildFeatures {
        viewBinding = true
    }
    tasks.withType<Test>{
        useJUnitPlatform()
    }
}

val roomVersion = "2.6.1"
val retrofitVersion = "2.9.0"
val lifecycleVersion = "2.7.0"
val daggerHiltVersion = "2.50"
val retromockVersion = "1.1.0"
val mockitoVersion = "5.2.1"

dependencies {
    implementation("co.infinum:retromock:$retromockVersion")
    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    kapt("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.room:room-ktx:$roomVersion")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}