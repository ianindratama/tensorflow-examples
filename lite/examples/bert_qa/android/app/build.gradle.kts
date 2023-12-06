plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("de.undercouch.download")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "org.tensorflow.lite.examples.bertqa"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
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
        viewBinding = true
    }
    androidResources {
        noCompress += "tflite"
    }
    namespace = "org.tensorflow.lite.examples.bertqa"
}

// import DownloadModels task
project.extra["ASSET_DIR"] = projectDir.toString() + "/src/main/assets"
project.extra["TEST_ASSETS_DIR"] = projectDir.toString() + "/src/androidTest/assets"

// Download default models; if you wish to use your own models then
// place them in the "assets" directory and comment out this line.
apply(from = "download_models.gradle")

dependencies {
    // Kotlin lang
    implementation("androidx.core:core-ktx:1.8.0")

    // App compat and UI things
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Navigation library
    val nav_version = "2.5.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // CameraX core library
    val camerax_version = "1.2.0-alpha03"
    implementation("androidx.camera:camera-core:$camerax_version")

    // CameraX Camera2 extensions
    implementation("androidx.camera:camera-camera2:$camerax_version")

    // CameraX Lifecycle library
    implementation("androidx.camera:camera-lifecycle:$camerax_version")

    // CameraX View class
    implementation("androidx.camera:camera-view:$camerax_version")

    //WindowManager
    implementation("androidx.window:window:1.1.0-alpha02")

    // Unit testing
    testImplementation("junit:junit:4.13.2")

    // Instrumented testing
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Gson library
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.google.guava:guava:28.1-android")

    // Import tensorflow library
    implementation("org.tensorflow:tensorflow-lite-task-text:0.3.0")
    // Import the GPU delegate plugin Library for GPU inference
    implementation("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")
}
